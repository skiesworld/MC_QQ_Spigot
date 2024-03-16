package com.github.theword.event;

import com.google.gson.annotations.SerializedName;

import static com.github.theword.MCQQ.config;


public class SpigotEvent {

    @SerializedName("server_name")
    private final String serverName = config.getServerName();

    @SerializedName("event_name")
    private String eventName;

    @SerializedName("post_type")
    private String postType;

    @SerializedName("sub_type")
    private String subType;

    private final int timestamp = (int) (System.currentTimeMillis() / 1000);

    public SpigotEvent(String eventName, String postType, String subType) {
        this.eventName = eventName;
        this.postType = postType;
        this.subType = subType;
    }
}
