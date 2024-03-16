package com.github.theword.models;


public class SpigotNoticeEvent extends SpigotEvent {

    private SpigotPlayer player;


    public SpigotNoticeEvent(String eventName, String subType, SpigotPlayer player) {
        super(eventName, "notice", subType);
        this.player = player;
    }
}
