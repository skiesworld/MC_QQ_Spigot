package com.github.theword.event;

public class SpigotPlayerDeathEvent extends SpigotMessageEvent {

    public SpigotPlayerDeathEvent(String serverName, SpigotPlayer player, String message) {
        super(serverName, "PlayerDeathEvent", "death", player, message);
    }
}
