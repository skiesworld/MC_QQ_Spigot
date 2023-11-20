package com.github.theword.returnBody.returnModle;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MsgItem {
    @SerializedName("msg_text")
    private String msgText;
    private String color;

    @SerializedName("action_event")
    private ActionEvent actionEvent;

}
