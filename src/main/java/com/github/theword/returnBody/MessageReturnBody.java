package com.github.theword.returnBody;

import java.util.List;

public class MessageReturnBody {

    private List<MsgItem> message;

    public MessageReturnBody() {
    }

    public MessageReturnBody(List<MsgItem> message) {
        this.message = message;
    }

    public List<MsgItem> getMessage() {
        return message;
    }

    public void setMessage(List<MsgItem> message) {
        this.message = message;
    }
}
