package com.github.theword.returnBody.returnModle;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ActionEvent {

    @SerializedName("click_event_url")
    private String clickEventUrl;

    @SerializedName("hover_event_text")
    private String hoverEventText;

}
