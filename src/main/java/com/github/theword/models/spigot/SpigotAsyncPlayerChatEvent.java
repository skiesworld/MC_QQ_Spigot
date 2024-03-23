package com.github.theword.models.spigot;

import com.github.theword.eventModels.base.BasePlayerChatEvent;

public class SpigotAsyncPlayerChatEvent extends BasePlayerChatEvent {

    public SpigotAsyncPlayerChatEvent(SpigotPlayer player, String message) {
        super("AsyncPlayerChatEvent", "", player, message);
    }
}
