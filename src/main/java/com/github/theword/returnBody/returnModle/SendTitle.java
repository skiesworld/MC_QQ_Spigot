package com.github.theword.returnBody.returnModle;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SendTitle {

    private String title;

    private String subtitle;

    private int fadein;

    private int stay;

    private int fadeout;

}
