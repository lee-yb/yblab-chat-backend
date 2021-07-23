package com.yblab.chat.model;

import lombok.Data;

@Data
public class ChatMessage {
    public enum MessageType{
        ENTER, TALK, LEAVE
    }
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
