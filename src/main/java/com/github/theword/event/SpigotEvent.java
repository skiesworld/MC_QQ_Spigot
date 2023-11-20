package com.github.theword.event;

import com.google.gson.annotations.SerializedName;

public class SpigotEvent {

    @SerializedName("server_name")
    private String serverName;

    @SerializedName("event_name")
    private String eventName;

    @SerializedName("post_type")
    private String postType;

    @SerializedName("sub_type")
    private String subType;

    private final Integer timestamp = (int) (System.currentTimeMillis() / 1000);

    public Integer getTime() {
        return timestamp;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public SpigotEvent() {
    }

    public SpigotEvent(String serverName, String eventName, String postType, String subType) {
        this.serverName = serverName;
        this.eventName = eventName;
        this.postType = postType;
        this.subType = subType;
    }
}
