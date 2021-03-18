package com.yblab.chat.model.enums;

public enum ChatRoomMemberJoinConnEnum {
    JOIN('Y'), LEAVE('N');
    private final char value;

    ChatRoomMemberJoinConnEnum(char value){
        this.value = value;
    }
    public char getValue(){
        return value;
    }

}
