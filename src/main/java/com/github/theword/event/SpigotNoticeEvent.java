package com.github.theword.event;

public class SpigotNoticeEvent extends SpigotEvent {

    private SpigotPlayer player;

    public SpigotPlayer getPlayer() {
        return player;
    }

    public void setPlayer(SpigotPlayer player) {
        this.player = player;
    }

    public SpigotNoticeEvent(String serverName, String eventName, String subType, SpigotPlayer player) {
        super(serverName, eventName, "notice", subType);
        this.player = player;
    }
}
