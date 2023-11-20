package com.github.theword.event;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
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
