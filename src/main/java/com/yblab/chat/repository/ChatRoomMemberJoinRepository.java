package com.yblab.chat.repository;

import com.yblab.chat.model.ChatRoomMemberJoin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomMemberJoinRepository extends JpaRepository<ChatRoomMemberJoin, Long> {

}
