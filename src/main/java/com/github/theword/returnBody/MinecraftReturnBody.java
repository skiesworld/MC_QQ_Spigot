package com.github.theword.returnBody;

import java.util.List;

public class MinecraftReturnBody {

    private List<MsgItem> message;

    public List<MsgItem> getMessage() {
        return message;
    }

    public void setMessage(List<MsgItem> message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MinecraftReturnBody{" +
                "message=" + message +
                '}';
    }
}
