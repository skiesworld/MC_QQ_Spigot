package com.github.theword.event;

public class SpigotPlayerQuitEvent extends SpigotNoticeEvent {

    public SpigotPlayerQuitEvent(SpigotPlayer player) {
        super("PlayerQuitEvent", "quit", player);
    }
}
