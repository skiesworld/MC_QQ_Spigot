package com.github.theword.event;

public class SpigotPlayerQuitEvent extends SpigotNoticeEvent {

    public SpigotPlayerQuitEvent(String serverName, SpigotPlayer player) {
        super(serverName, "PlayerQuitEvent", "quit", player);
    }
}
