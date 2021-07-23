package com.yblab.chat.service;

import com.yblab.chat.dto.ChatRoomDto;
import com.yblab.chat.model.ChatRoom;
import com.yblab.chat.model.enums.ChatRoomMemberJoinConnEnum;
import com.yblab.chat.model.ChatRoomMemberJoin;
import com.yblab.chat.model.Member;
import com.yblab.chat.model.enums.ChatRoomStatusEnum;
import com.yblab.chat.repository.ChatRoomMemberJoinRepository;
import com.yblab.chat.repository.ChatRoomRepository;
import com.yblab.chat.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ChatRoomMemberJoinRepository chatRoomMemberJoinRepository;


    /**
     * @apiNote STATUS가 Open('O')인 Chat room list.
     * @param status ChatRoom status.
     * @return ChatRoom List.
     */
    public List<ChatRoom> findAllByStatus(char status){
        return chatRoomRepository.findAllByStatus(status);
    }

    public ChatRoom findById(Long id){
        return chatRoomRepository.findById(id).orElse(null);
    }

    public ChatRoom save(ChatRoomDto newChatRoomDto){
        /**
         * @TODO: toEntity(), toDto() 필요
         */
        ChatRoom newChatRoom = newChatRoomDto.toEntity();
        List<Member> members = memberRepository.findAllById(newChatRoomDto.getMember_ids());
        newChatRoom.setChatRoomMemberJoins(setChatRoomMemberJoinsByMembers(newChatRoom, members));
        newChatRoom.setStatus(ChatRoomStatusEnum.OPEN.getValue());
        return chatRoomRepository.save(newChatRoom);
    }

    public ChatRoom update(ChatRoomDto chatRoomDto, Long id) {
        /**
         * @TODO: ChatRoomMemberJoin 기존 데이터와 중복되는 데이터를 감지하여 내비두고 새로운 데이터만 update
         */

        ChatRoom aChatRoom = chatRoomDto.toEntity();
        List<Member> members = memberRepository.findAllById(chatRoomDto.getMember_ids());

        return chatRoomRepository.findById(id)
                .map(chatRoom -> {
                    chatRoom.setRoom_name(aChatRoom.getRoom_name());
                    chatRoom.setRoom_topic(aChatRoom.getRoom_topic());
                    chatRoom.setRoom_desc(aChatRoom.getRoom_desc());
                    chatRoom.setChatRoomMemberJoins(setChatRoomMemberJoinsByMembers(chatRoom, members));
                    return chatRoomRepository.save(chatRoom);
                })
                .orElseGet( ()-> {
                    aChatRoom.setId(id);
                    return chatRoomRepository.save(aChatRoom);
                });
    }

    private List<ChatRoomMemberJoin> setChatRoomMemberJoinsByMembers(ChatRoom chatRoom, List<Member> members){
        List<ChatRoomMemberJoin> chatRoomMemberJoins = new ArrayList<>();
        for (Member member : members){
            ChatRoomMemberJoin chatRoomMemberJoin = new ChatRoomMemberJoin();
            chatRoomMemberJoin.setChatRoom(chatRoom);
            chatRoomMemberJoin.setMember(member);
            chatRoomMemberJoin.setConnection(ChatRoomMemberJoinConnEnum.JOIN.getValue());

            chatRoomMemberJoins.add(chatRoomMemberJoin);
        }
        return chatRoomMemberJoins;
    }

    /**
     * @apiNote ChatRoom 테이블의 status 값 C(Close)로 변경
     * @param id chatRoom ID
     * @return ChatRoom Object
     */
    public ChatRoom closeChatRoom(Long id){
        ChatRoom chatRoom = findById(id);
        chatRoom.setStatus(ChatRoomStatusEnum.CLOSE.getValue());
        return chatRoomRepository.save(chatRoom);
    }
}
