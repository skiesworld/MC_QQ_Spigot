package com.github.theword.parse;

import com.github.theword.ConfigReader;
import com.github.theword.event.*;
import com.google.gson.Gson;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

import static com.github.theword.Utils.say;

public class ParseClassToJson {
    /**
     * @param event 事件
     * @return 事件的 Json 字符串
     */
    public static String processMessageToJson(Event event) {
        Gson gson = new Gson();

        String server_name = ConfigReader.getServerName();
        String jsonData;

        if (event instanceof AsyncPlayerChatEvent) {
            SpigotAsyncPlayerChatEvent spigotAsyncPlayerChatEvent = new SpigotAsyncPlayerChatEvent(
                    server_name,
                    getSpigotPlayer(((AsyncPlayerChatEvent) event).getPlayer()),
                    ((AsyncPlayerChatEvent) event).getMessage()
            );
            jsonData = gson.toJson(spigotAsyncPlayerChatEvent);
        } else if (event instanceof PlayerJoinEvent) {
            SpigotPlayerJoinEvent spigotPlayerJoinEvent = new SpigotPlayerJoinEvent(
                    server_name,
                    getSpigotPlayer(((PlayerJoinEvent) event).getPlayer())
            );
            jsonData = gson.toJson(spigotPlayerJoinEvent);
        } else if (event instanceof PlayerQuitEvent) {
            SpigotPlayerQuitEvent spigotPlayerQuitEvent = new SpigotPlayerQuitEvent(
                    server_name,
                    getSpigotPlayer(((PlayerQuitEvent) event).getPlayer())
            );
            jsonData = gson.toJson(spigotPlayerQuitEvent);
        } else if (event instanceof PlayerDeathEvent) {
            SpigotPlayerDeathEvent spigotPlayerDeathEvent = new SpigotPlayerDeathEvent(
                    server_name,
                    getSpigotPlayer(((PlayerDeathEvent) event).getEntity()),
                    ((PlayerDeathEvent) event).getDeathMessage()
            );
            jsonData = gson.toJson(spigotPlayerDeathEvent);
        } else if (event instanceof PlayerCommandPreprocessEvent) {
            SpigotPlayerCommandPreprocessEvent spigotPlayerCommandPreprocessEvent = new SpigotPlayerCommandPreprocessEvent(
                    server_name,
                    getSpigotPlayer(((PlayerCommandPreprocessEvent) event).getPlayer()),
                    ((PlayerCommandPreprocessEvent) event).getMessage()
            );
            jsonData = gson.toJson(spigotPlayerCommandPreprocessEvent);
        } else {
            say("未知事件: " + event.getEventName());
            jsonData = gson.toJson(event);
        }
        return jsonData;
    }

    /**
     * @param player 玩家
     * @return SpigotPlayer 对象
     */
    static SpigotPlayer getSpigotPlayer(Player player) {
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

}
