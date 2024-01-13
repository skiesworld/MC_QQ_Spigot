package com.github.theword;

import com.github.theword.event.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


import static com.github.theword.MCQQ.wsClient;
import static com.github.theword.Utils.getSpigotPlayer;
import static com.github.theword.Utils.getEventJson;

class EventProcessor implements Listener {
    /**
     * 监听玩家聊天
     */
    @EventHandler
    void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!event.isCancelled()) {
            SpigotAsyncPlayerChatEvent spigotAsyncPlayerChatEvent = new SpigotAsyncPlayerChatEvent(getSpigotPlayer(event.getPlayer()), event.getMessage());
            wsClient.sendMessage(getEventJson(spigotAsyncPlayerChatEvent));
        }
    }

    /**
     * 监听玩家死亡事件
     */
    @EventHandler
    void onPlayerDeath(PlayerDeathEvent event) {
        if (ConfigReader.getDeathMessage()) {
            SpigotPlayerDeathEvent spigotPlayerDeathEvent = new SpigotPlayerDeathEvent(getSpigotPlayer(event.getEntity()), event.getDeathMessage());
            wsClient.sendMessage(getEventJson(spigotPlayerDeathEvent));
        }
    }

    /**
     * 监听玩家加入事件
     */
    @EventHandler
    void onPlayerJoin(PlayerJoinEvent event) {
        if (ConfigReader.getJoinQuit()) {
            SpigotPlayerJoinEvent spigotPlayerJoinEvent = new SpigotPlayerJoinEvent(getSpigotPlayer(event.getPlayer()));
            wsClient.sendMessage(getEventJson(spigotPlayerJoinEvent));
        }
    }

    /**
     * 监听玩家离开事件
     */
    @EventHandler
    void onPlayerQuit(PlayerQuitEvent event) {
        if (ConfigReader.getJoinQuit()) {
            SpigotPlayerQuitEvent spigotPlayerQuitEvent = new SpigotPlayerQuitEvent(getSpigotPlayer(event.getPlayer()));
            wsClient.sendMessage(getEventJson(spigotPlayerQuitEvent));
        }
    }

    @EventHandler
    void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (ConfigReader.getEnable() && ConfigReader.getCommandMessage()) {
            String command = event.getMessage();
            if (!(command.startsWith("/l ") || command.startsWith("/login ") || command.startsWith("/register ") || command.startsWith("/reg "))) {
                SpigotPlayerCommandPreprocessEvent spigotPlayerCommandPreprocessEvent = new SpigotPlayerCommandPreprocessEvent(getSpigotPlayer(event.getPlayer()), event.getMessage());
                wsClient.sendMessage(getEventJson(spigotPlayerCommandPreprocessEvent));
            }
        }
    }
}
