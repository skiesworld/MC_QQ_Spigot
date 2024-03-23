package com.github.theword.models.spigot;

import com.github.theword.eventModels.base.BasePlayerJoinEvent;

public class SpigotPlayerJoinEvent extends BasePlayerJoinEvent {

    public SpigotPlayerJoinEvent( SpigotPlayer player) {
        super( "PlayerJoinEvent", player);
    }

}
