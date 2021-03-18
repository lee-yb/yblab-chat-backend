package com.yblab.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class ChatRoomMemberJoin extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;
//
    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private ChatRoom chatRoom;

    // 추가 정보 입력 (ex.입장인지 퇴장인지 등)
    private char connection;
}