package com.github.theword.event;


public class SpigotNoticeEvent extends SpigotEvent {

    private SpigotPlayer player;


    public SpigotNoticeEvent(String eventName, String subType, SpigotPlayer player) {
        super(eventName, "notice", subType);
        this.player = player;
    }
}
