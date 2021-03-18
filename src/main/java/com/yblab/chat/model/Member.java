package com.yblab.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@ToString(callSuper = true)
public class Member extends BaseEntity{
    @NotNull
    private String name;
    @NotNull
    private String password;
    private String phone;
    private String email;
    private String bio;

//    @ManyToMany
//    @JsonIgnore
//    @JoinTable(
//            name = "chat_room_member_join",
//            joinColumns = @JoinColumn(name = "member_id"),
//            inverseJoinColumns = @JoinColumn(name = "room_id")
//    )
//    List<ChatRoom> chatRooms;

//    @OneToMany(mappedBy = "member")
//    @JsonIgnore
//    private List<ChatRoomMemberJoin> chatRoomMemberJoins;
}
