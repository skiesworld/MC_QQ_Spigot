package com.github.theword.returnBody;

import com.github.theword.returnBody.returnModle.MsgItem;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;
@Data
public class MessageReturnBody {

    @SerializedName("message_list")
    private List<MsgItem> messageList;

}
