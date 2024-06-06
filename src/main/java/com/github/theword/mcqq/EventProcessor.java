package com.github.theword.mcqq;

import com.github.theword.mcqq.eventModels.spigot.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

import static com.github.theword.mcqq.utils.Tool.config;
import static com.github.theword.mcqq.utils.Tool.sendMessage;


class EventProcessor implements Listener {
    /**
     * 监听玩家聊天
     */
    @EventHandler
    void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!event.isCancelled() && config.isEnableChatMessage()) {
            SpigotAsyncPlayerChatEvent spigotAsyncPlayerChatEvent = new SpigotAsyncPlayerChatEvent(getSpigotPlayer(event.getPlayer()), event.getMessage());
            sendMessage(spigotAsyncPlayerChatEvent);
        }
    }

    /**
     * 监听玩家死亡事件
     */
    @EventHandler
    void onPlayerDeath(PlayerDeathEvent event) {
        if (config.isEnableDeathMessage()) {
            SpigotPlayerDeathEvent spigotPlayerDeathEvent = new SpigotPlayerDeathEvent(getSpigotPlayer(event.getEntity()), event.getDeathMessage());
            sendMessage(spigotPlayerDeathEvent);
        }
    }

    /**
     * 监听玩家加入事件
     */
    @EventHandler
    void onPlayerJoin(PlayerJoinEvent event) {
        if (config.isEnableJoinMessage()) {
            SpigotPlayerJoinEvent spigotPlayerJoinEvent = new SpigotPlayerJoinEvent(getSpigotPlayer(event.getPlayer()));
            sendMessage(spigotPlayerJoinEvent);
        }
    }

    /**
     * 监听玩家离开事件
     */
    @EventHandler
    void onPlayerQuit(PlayerQuitEvent event) {
        if (config.isEnableQuitMessage()) {
            SpigotPlayerQuitEvent spigotPlayerQuitEvent = new SpigotPlayerQuitEvent(getSpigotPlayer(event.getPlayer()));
            sendMessage(spigotPlayerQuitEvent);
        }
    }

    @EventHandler
    void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (config.isEnableCommandMessage()) {
            String command = event.getMessage();
            if (!(command.startsWith("/l ") || command.startsWith("/login ") || command.startsWith("/register ") || command.startsWith("/reg ") || command.startsWith("mcqq "))) {
                command = command.replaceFirst("/", "");
                SpigotPlayerCommandPreprocessEvent spigotPlayerCommandPreprocessEvent = new SpigotPlayerCommandPreprocessEvent(getSpigotPlayer(event.getPlayer()), command);
                sendMessage(spigotPlayerCommandPreprocessEvent);
            }
        }
    }

    SpigotPlayer getSpigotPlayer(Player player) {
        SpigotPlayer spigotPlayer = new SpigotPlayer();
        spigotPlayer.setUuid(player.getUniqueId().toString());
        spigotPlayer.setNickname(player.getName());
        spigotPlayer.setDisplayName(player.getDisplayName());
        spigotPlayer.setPlayerListName(player.getDisplayName());
        spigotPlayer.setAddress((Objects.requireNonNull(player.getAddress()).toString()));
        spigotPlayer.setHealthScale(player.getHealthScale());
        spigotPlayer.setExp(player.getExp());
        spigotPlayer.setTotalExp(player.getTotalExperience());
        spigotPlayer.setLevel(player.getLevel());
        spigotPlayer.setLocale(player.getLocale());
        spigotPlayer.setPing(player.getPing());
        spigotPlayer.setPlayerTime(player.getPlayerTime());
        spigotPlayer.setPlayerTimeRelative(player.isPlayerTimeRelative());
        spigotPlayer.setPlayerTimeOffset(player.getPlayerTimeOffset());
        spigotPlayer.setWalkSpeed(player.getWalkSpeed());
        spigotPlayer.setFlySpeed(player.getFlySpeed());
        spigotPlayer.setAllowFlight(player.getAllowFlight());
        spigotPlayer.setSprinting(player.isSprinting());
        spigotPlayer.setSneaking(player.isSneaking());
        spigotPlayer.setFlying(player.isFlying());
        spigotPlayer.setOp(player.isOp());
        return spigotPlayer;
    }
}
