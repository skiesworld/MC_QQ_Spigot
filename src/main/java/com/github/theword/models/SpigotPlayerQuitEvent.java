package com.github.theword.models;

public class SpigotPlayerQuitEvent extends SpigotNoticeEvent {

    public SpigotPlayerQuitEvent(SpigotPlayer player) {
        super("PlayerQuitEvent", "quit", player);
    }
}
