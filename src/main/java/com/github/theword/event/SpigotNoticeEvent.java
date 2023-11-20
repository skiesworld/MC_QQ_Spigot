package com.github.theword.event;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SpigotNoticeEvent extends SpigotEvent {

    private SpigotPlayer player;


    public SpigotNoticeEvent(String serverName, String eventName, String subType, SpigotPlayer player) {
        super(serverName, eventName, "notice", subType);
        this.player = player;
    }
}
