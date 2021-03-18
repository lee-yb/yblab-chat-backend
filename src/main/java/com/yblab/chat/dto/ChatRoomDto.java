package com.yblab.chat.dto;

import com.yblab.chat.model.ChatRoom;
import com.yblab.chat.model.enums.ChatRoomStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ChatRoomDto extends BaseDto {
    private String room_name;
    private String room_topic;
    private String room_desc;
    private List<Long> member_ids;

    //baseEntity, baseDto 에 generic 이용하여 추상화필요
    public ChatRoom toEntity(ChatRoom chatRoom){
        if (chatRoom == null){
            chatRoom = new ChatRoom();
        }
        chatRoom.setRoom_name(this.room_name);
        chatRoom.setRoom_topic(this.room_topic);
        chatRoom.setRoom_desc(this.room_desc);
        return chatRoom;
    }
}
