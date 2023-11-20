package com.github.theword.event;

import com.google.gson.annotations.SerializedName;

public class SpigotMessageEvent extends SpigotEvent {

    @SerializedName("message_id")
    private final String messageId = "";

    private SpigotPlayer player;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SpigotPlayer getPlayer() {
        return player;
    }

    public void setPlayer(SpigotPlayer player) {
        this.player = player;
    }

    public SpigotMessageEvent(String serverName, String eventName, String subType, SpigotPlayer player, String message) {
        super(serverName, eventName, "message", subType);
        this.message = message;
        this.player = player;
    }
}
