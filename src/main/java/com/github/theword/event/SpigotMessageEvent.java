package com.github.theword.event;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SpigotMessageEvent extends SpigotEvent {

    @SerializedName("message_id")
    private final String messageId = "";

    private SpigotPlayer player;

    private String message;


    public SpigotMessageEvent(String serverName, String eventName, String subType, SpigotPlayer player, String message) {
        super(serverName, eventName, "message", subType);
        this.message = message;
        this.player = player;
    }
}
