package com.github.theword;

import com.github.theword.event.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


import static com.github.theword.MCQQ.config;
import static com.github.theword.Utils.*;

class EventProcessor implements Listener {
    /**
     * 监听玩家聊天
     */
    @EventHandler
    void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!event.isCancelled() && config.isEnableChatMessage()) {
            SpigotAsyncPlayerChatEvent spigotAsyncPlayerChatEvent = new SpigotAsyncPlayerChatEvent(getSpigotPlayer(event.getPlayer()), event.getMessage());
            wsClient.sendMessage(getEventJson(spigotAsyncPlayerChatEvent));
        }
    }

    /**
     * 监听玩家死亡事件
     */
    @EventHandler
    void onPlayerDeath(PlayerDeathEvent event) {
        if (config.isEnableDeathMessage()) {
            SpigotPlayerDeathEvent spigotPlayerDeathEvent = new SpigotPlayerDeathEvent(getSpigotPlayer(event.getEntity()), event.getDeathMessage());
            wsClient.sendMessage(getEventJson(spigotPlayerDeathEvent));
        }
    }

    /**
     * 监听玩家加入事件
     */
    @EventHandler
    void onPlayerJoin(PlayerJoinEvent event) {
        if (config.isEnableJoinMessage()) {
            SpigotPlayerJoinEvent spigotPlayerJoinEvent = new SpigotPlayerJoinEvent(getSpigotPlayer(event.getPlayer()));
            wsClient.sendMessage(getEventJson(spigotPlayerJoinEvent));
        }
    }

    /**
     * 监听玩家离开事件
     */
    @EventHandler
    void onPlayerQuit(PlayerQuitEvent event) {
        if (config.isEnableQuitMessage()) {
            SpigotPlayerQuitEvent spigotPlayerQuitEvent = new SpigotPlayerQuitEvent(getSpigotPlayer(event.getPlayer()));
            wsClient.sendMessage(getEventJson(spigotPlayerQuitEvent));
        }
    }

    @EventHandler
    void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (config.isEnableCommandMessage()) {
            String command = event.getMessage();
            if (!(command.startsWith("/l ") || command.startsWith("/login ") || command.startsWith("/register ") || command.startsWith("/reg "))) {
                command = command.replaceFirst("/", "");
                SpigotPlayerCommandPreprocessEvent spigotPlayerCommandPreprocessEvent = new SpigotPlayerCommandPreprocessEvent(getSpigotPlayer(event.getPlayer()), command);
                wsClient.sendMessage(getEventJson(spigotPlayerCommandPreprocessEvent));
            }
        }
    }
}
