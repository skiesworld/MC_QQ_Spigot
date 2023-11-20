package com.github.theword.event;

public class SpigotPlayerCommandPreprocessEvent extends SpigotMessageEvent {

    public SpigotPlayerCommandPreprocessEvent(String serverName, SpigotPlayer player, String command) {
        super(serverName, "PlayerCommandPreprocessEvent", "player_command", player, command);
    }
}
