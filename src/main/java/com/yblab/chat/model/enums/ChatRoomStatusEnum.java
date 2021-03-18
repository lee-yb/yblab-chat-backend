package com.yblab.chat.model.enums;

public enum ChatRoomStatusEnum {
    OPEN('O'), CLOSE('C');
    private final char value;

    ChatRoomStatusEnum(char value){
        this.value = value;
    }
    public char getValue(){
        return value;
    }
}
