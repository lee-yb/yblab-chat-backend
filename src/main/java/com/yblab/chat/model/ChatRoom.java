package com.yblab.chat.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@ToString(callSuper = true)
public class ChatRoom extends BaseEntity{
    @NotNull
    private String room_name;
    private String room_topic;
    private String room_desc;
    public char status;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<ChatRoomMemberJoin> chatRoomMemberJoins;

//    @ManyToMany(mappedBy = "chatRooms")
//    List<Member> members;

}
