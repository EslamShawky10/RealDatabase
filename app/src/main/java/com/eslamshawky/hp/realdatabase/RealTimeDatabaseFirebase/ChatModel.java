package com.eslamshawky.hp.realdatabase.RealTimeDatabaseFirebase;

public class ChatModel {

    private  String textChat;

    public ChatModel(String textChat) {
        this.textChat = textChat;
    }


    public String getTextChat() {
        return textChat;
    }

    public void setTextChat(String textChat) {
        this.textChat = textChat;
    }
}
