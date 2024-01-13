package com.github.theword.event;

public class SpigotPlayerJoinEvent extends SpigotNoticeEvent {

    public SpigotPlayerJoinEvent(SpigotPlayer player) {
        super("PlayerJoinEvent", "join", player);
    }

}
