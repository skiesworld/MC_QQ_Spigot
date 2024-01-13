package com.github.theword.event;

public class SpigotPlayerDeathEvent extends SpigotMessageEvent {

    public SpigotPlayerDeathEvent(SpigotPlayer player, String message) {
        super("PlayerDeathEvent", "death", player, message);
    }
}
