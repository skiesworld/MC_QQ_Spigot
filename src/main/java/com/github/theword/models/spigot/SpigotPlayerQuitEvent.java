package com.github.theword.models.spigot;

import com.github.theword.eventModels.base.BasePlayerQuitEvent;

public class SpigotPlayerQuitEvent extends BasePlayerQuitEvent {

    public SpigotPlayerQuitEvent( SpigotPlayer player) {
        super("PlayerQuitEvent", player);
    }
}
