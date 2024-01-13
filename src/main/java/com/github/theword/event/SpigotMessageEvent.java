package com.github.theword.event;

import com.google.gson.annotations.SerializedName;


public class SpigotMessageEvent extends SpigotEvent {

    @SerializedName("message_id")
    private final String messageId = "";

    private SpigotPlayer player;

    private String message;


    public SpigotMessageEvent(String eventName, String subType, SpigotPlayer player, String message) {
        super(eventName, "message", subType);
        this.message = message;
        this.player = player;
    }
}
