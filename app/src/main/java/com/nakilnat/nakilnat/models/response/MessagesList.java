package com.nakilnat.nakilnat.models.response;

public class MessagesList {
    private String name;
    private String lastMessages;
    private String messageTime;

    public MessagesList(String name, String lastMessages, String messageTime) {
        this.name = name;
        this.lastMessages = lastMessages;
        this.messageTime = messageTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMessages() {
        return lastMessages;
    }

    public void setLastMessages(String lastMessages) {
        this.lastMessages = lastMessages;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
