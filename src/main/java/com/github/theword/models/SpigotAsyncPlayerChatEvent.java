package com.github.theword.models;

public class SpigotAsyncPlayerChatEvent extends SpigotMessageEvent {

    public SpigotAsyncPlayerChatEvent(SpigotPlayer player, String message) {
        super("AsyncPlayerChatEvent", "chat", player, message);
    }
}
