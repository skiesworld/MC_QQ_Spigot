package com.github.theword.event;

public class SpigotPlayerJoinEvent extends SpigotNoticeEvent {

    public SpigotPlayerJoinEvent(String serverName, SpigotPlayer player) {
        super(serverName, "PlayerJoinEvent", "join", player);
    }

}
