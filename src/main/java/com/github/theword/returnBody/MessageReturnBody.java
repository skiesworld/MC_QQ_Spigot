package com.github.theword.returnBody;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageReturnBody {

    @SerializedName("message_list")
    private List<MsgItem> messageList;

    public MessageReturnBody() {
    }

    public MessageReturnBody(List<MsgItem> message) {
        this.messageList = message;
    }

    public List<MsgItem> getMessage() {
        return messageList;
    }

    public void setMessage(List<MsgItem> message) {
        this.messageList = message;
    }
}
