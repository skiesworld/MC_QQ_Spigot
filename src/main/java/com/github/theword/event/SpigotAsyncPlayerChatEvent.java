package com.github.theword.event;

public class SpigotAsyncPlayerChatEvent extends SpigotMessageEvent {

    public SpigotAsyncPlayerChatEvent(SpigotPlayer player, String message) {
        super("AsyncPlayerChatEvent", "chat", player, message);
    }
}
