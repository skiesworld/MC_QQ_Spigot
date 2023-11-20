package com.github.theword.event;

import com.google.gson.annotations.SerializedName;

public class SpigotPlayer {

    private String uuid;
    private String nickname;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("player_list_name")
    private String playerListName;
    private String address;

    @SerializedName("is_health_scaled")
    private boolean isHealthScaled;
    @SerializedName("health_scale")
    private double healthScale;

    private float exp;

    @SerializedName("total_exp")
    private int totalExp;
    private int level;

    private String locale;

    private int ping;
    @SerializedName("player_time")
    private long playerTime;
    @SerializedName("is_player_time_relative")
    private boolean isPlayerTimeRelative;
    @SerializedName("player_time_offset")
    private long playerTimeOffset;
    @SerializedName("walk_speed")
    private float walkSpeed;
    @SerializedName("fly_speed")
    private float flySpeed;
    @SerializedName("allow_flight")
    private boolean allowFlight;
    @SerializedName("is_sprinting")
    private boolean isSprinting;
    @SerializedName("is_sneaking")
    private boolean isSneaking;
    @SerializedName("is_flying")
    private boolean isFlying;
    @SerializedName("is_op")
    private boolean isOp;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPlayerListName() {
        return playerListName;
    }

    public void setPlayerListName(String playerListName) {
        this.playerListName = playerListName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isHealthScaled() {
        return isHealthScaled;
    }

    public void setHealthScaled(boolean healthScaled) {
        this.isHealthScaled = healthScaled;
    }

    public double getHealthScale() {
        return healthScale;
    }

    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }

    public float getExp() {
        return exp;
    }

    public void setExp(float exp) {
        this.exp = exp;
    }

    public int getTotalExp() {
        return totalExp;
    }

    public void setTotalExp(int totalExp) {
        this.totalExp = totalExp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public int getPing() {
        return ping;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }

    public long getPlayerTime() {
        return playerTime;
    }

    public void setPlayerTime(long playerTime) {
        this.playerTime = playerTime;
    }

    public boolean isPlayerTimeRelative() {
        return isPlayerTimeRelative;
    }

    public void setPlayerTimeRelative(boolean playerTimeRelative) {
        this.isPlayerTimeRelative = playerTimeRelative;
    }

    public long getplayerTimeOffset() {
        return playerTimeOffset;
    }

    public void setplayerTimeOffset(long playerTimeOffset) {
        this.playerTimeOffset = playerTimeOffset;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }

    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    public float getFlySpeed() {
        return flySpeed;
    }

    public void setFlySpeed(float flySpeed) {
        this.flySpeed = flySpeed;
    }

    public boolean isAllowFlight() {
        return allowFlight;
    }

    public void setAllowFlight(boolean allowFlight) {
        this.allowFlight = allowFlight;
    }

    public boolean isSprinting() {
        return isSprinting;
    }

    public void setSprinting(boolean sprinting) {
        this.isSprinting = sprinting;
    }

    public boolean isSneaking() {
        return isSneaking;
    }

    public void setSneaking(boolean sneaking) {
        this.isSneaking = sneaking;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public void setFlying(boolean flying) {
        this.isFlying = flying;
    }

    public boolean isOp() {
        return isOp;
    }

    public void setOp(boolean op) {
        this.isOp = op;
    }

    public SpigotPlayer() {
    }

    public SpigotPlayer(String uuid, String nickname, String displayName, String playerListName, String address, boolean isHealthScaled, double healthScale, float exp, int totalExp, int level, String locale, int ping, long playerTime, boolean isPlayerTimeRelative, long playerTimeOffset, float walkSpeed, float flySpeed, boolean allowFlight, boolean isSprinting, boolean isSneaking, boolean isFlying, boolean isOp) {
        this.uuid = uuid;
        this.nickname = nickname;
        this.displayName = displayName;
        this.playerListName = playerListName;
        this.address = address;
        this.isHealthScaled = isHealthScaled;
        this.healthScale = healthScale;
        this.exp = exp;
        this.totalExp = totalExp;
        this.level = level;
        this.locale = locale;
        this.ping = ping;
        this.playerTime = playerTime;
        this.isPlayerTimeRelative = isPlayerTimeRelative;
        this.playerTimeOffset = playerTimeOffset;
        this.walkSpeed = walkSpeed;
        this.flySpeed = flySpeed;
        this.allowFlight = allowFlight;
        this.isSprinting = isSprinting;
        this.isSneaking = isSneaking;
        this.isFlying = isFlying;
        this.isOp = isOp;
    }
}
