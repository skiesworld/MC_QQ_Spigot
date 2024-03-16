package com.github.theword;

import com.github.theword.models.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


import static com.github.theword.MCQQ.config;
import static com.github.theword.utils.Tool.*;

class EventProcessor implements Listener {
    /**
     * 监听玩家聊天
     */
    @EventHandler
    void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!event.isCancelled() && config.isEnableChatMessage()) {
            SpigotAsyncPlayerChatEvent spigotAsyncPlayerChatEvent = new SpigotAsyncPlayerChatEvent(getSpigotPlayer(event.getPlayer()), event.getMessage());
            sendMessage(getEventJson(spigotAsyncPlayerChatEvent));
        }
    }

    /**
     * 监听玩家死亡事件
     */
    @EventHandler
    void onPlayerDeath(PlayerDeathEvent event) {
        if (config.isEnableDeathMessage()) {
            SpigotPlayerDeathEvent spigotPlayerDeathEvent = new SpigotPlayerDeathEvent(getSpigotPlayer(event.getEntity()), event.getDeathMessage());
            sendMessage(getEventJson(spigotPlayerDeathEvent));
        }
    }

    /**
     * 监听玩家加入事件
     */
    @EventHandler
    void onPlayerJoin(PlayerJoinEvent event) {
        if (config.isEnableJoinMessage()) {
            SpigotPlayerJoinEvent spigotPlayerJoinEvent = new SpigotPlayerJoinEvent(getSpigotPlayer(event.getPlayer()));
            sendMessage(getEventJson(spigotPlayerJoinEvent));
        }
    }

    /**
     * 监听玩家离开事件
     */
    @EventHandler
    void onPlayerQuit(PlayerQuitEvent event) {
        if (config.isEnableQuitMessage()) {
            SpigotPlayerQuitEvent spigotPlayerQuitEvent = new SpigotPlayerQuitEvent(getSpigotPlayer(event.getPlayer()));
            sendMessage(getEventJson(spigotPlayerQuitEvent));
        }
    }

    @EventHandler
    void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (config.isEnableCommandMessage()) {
            String command = event.getMessage();
            if (!(command.startsWith("/l ") || command.startsWith("/login ") || command.startsWith("/register ") || command.startsWith("/reg ") || command.startsWith("mcqq "))) {
                command = command.replaceFirst("/", "");
                SpigotPlayerCommandPreprocessEvent spigotPlayerCommandPreprocessEvent = new SpigotPlayerCommandPreprocessEvent(getSpigotPlayer(event.getPlayer()), command);
                sendMessage(getEventJson(spigotPlayerCommandPreprocessEvent));
            }
        }
    }
}
