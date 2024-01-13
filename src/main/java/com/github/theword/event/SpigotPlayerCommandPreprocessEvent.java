package com.github.theword.event;

public class SpigotPlayerCommandPreprocessEvent extends SpigotMessageEvent {

    public SpigotPlayerCommandPreprocessEvent(SpigotPlayer player, String command) {
        super("PlayerCommandPreprocessEvent", "player_command", player, command);
    }
}
