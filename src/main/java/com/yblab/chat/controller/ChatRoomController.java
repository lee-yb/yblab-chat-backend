package com.yblab.chat.controller;

import com.yblab.chat.dto.ChatRoomDto;
import com.yblab.chat.model.ChatRoom;
import com.yblab.chat.model.enums.ChatRoomStatusEnum;
import com.yblab.chat.repository.ChatRoomRepository;
import com.yblab.chat.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @GetMapping("/rooms")
    public List<ChatRoom> chatRooms(){
        return chatRoomService.findAllByStatus(ChatRoomStatusEnum.OPEN.getValue());
    }

    @GetMapping("/room/{id}")
    public ChatRoom chatRoom(@PathVariable Long id){
        return chatRoomService.findById(id);
    }

    @PostMapping("/room")
    public ChatRoom createChatRoom(@RequestBody ChatRoomDto newChatRoomDto) {
        return chatRoomService.save(newChatRoomDto);
//        return chatRoomRepository.save(newChatRoom);
    }

    @PutMapping("/room/{id}")
    public ChatRoom modifyChatRoom(@RequestBody ChatRoomDto chatRoomDto,
                                   @PathVariable Long id){
        return chatRoomService.update(chatRoomDto, id);
    }

    @DeleteMapping("/room/{id}")
    public ChatRoom closeChatRoom(@PathVariable Long id){
        return chatRoomService.closeChatRoom(id);
    }


}
