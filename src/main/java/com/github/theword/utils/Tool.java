package com.github.theword.utils;

import com.github.theword.models.SpigotEvent;
import com.github.theword.models.SpigotPlayer;
import com.google.gson.Gson;
import org.bukkit.entity.Player;

import java.util.Objects;

import static com.github.theword.MCQQ.config;
import static com.github.theword.MCQQ.wsClientList;

public class Tool {
    /**
     * @param player 玩家
     * @return SpigotPlayer 对象
     */
    public static SpigotPlayer getSpigotPlayer(Player player) {
        return new SpigotPlayer(
                player.getUniqueId().toString(),
                player.getName(),
                player.getDisplayName(),
                player.getDisplayName(),
                Objects.requireNonNull(player.getAddress()).toString(),
                player.isHealthScaled(),
                player.getHealthScale(),
                player.getExp(),
                player.getTotalExperience(),
                player.getLevel(),
                player.getLocale(),
                player.getPing(),
                player.getPlayerTime(),
                player.isPlayerTimeRelative(),
                player.getPlayerTimeOffset(),
                player.getWalkSpeed(),
                player.getFlySpeed(),
                player.getAllowFlight(),
                player.isSprinting(),
                player.isSneaking(),
                player.isFlying(),
                player.isOp()
        );
    }

    /**
     * 字符串转为 unicode 编码
     *
     * @param string 字符串
     * @return unicode编码
     */
    public static String unicodeEncode(String string) {
        char[] utfBytes = string.toCharArray();
        StringBuilder unicodeBytes = new StringBuilder();
        for (char utfByte : utfBytes) {
            String hexB = Integer.toHexString(utfByte);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes.append("\\u").append(hexB);
        }
        return unicodeBytes.toString();
    }

    /**
     * 获取事件的 json 字符串
     *
     * @param event 事件
     * @return json 字符串
     */
    public static String getEventJson(SpigotEvent event) {
        Gson gson = new Gson();
        return gson.toJson(event);
    }

    public static void sendMessage(String message) {
        if (config.isEnableMcQQ()) {
            wsClientList.forEach(
                    wsClient -> {
                        if (wsClient.isOpen()) {
                            wsClient.sendMessage(message);
                        }
                    }
            );
        }
    }
}
