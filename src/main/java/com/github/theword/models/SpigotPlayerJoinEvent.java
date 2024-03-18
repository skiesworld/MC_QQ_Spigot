package com.github.theword.models;

public class SpigotPlayerJoinEvent extends SpigotNoticeEvent {

    public SpigotPlayerJoinEvent(SpigotPlayer player) {
        super("PlayerJoinEvent", "join", player);
    }

}
