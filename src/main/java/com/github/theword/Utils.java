package com.github.theword;

import com.github.theword.event.SpigotEvent;
import com.github.theword.event.SpigotPlayer;
import com.github.theword.returnBody.ActionbarReturnBody;
import com.github.theword.returnBody.BaseReturnBody;
import com.github.theword.returnBody.MessageReturnBody;
import com.github.theword.returnBody.SendTitleReturnBody;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.net.URISyntaxException;
import java.util.Objects;

import static com.github.theword.MCQQ.*;
import static com.github.theword.parse.ParseJsonToClass.parseMessageToTextComponent;

public class Utils {

    /**
     * 来自 NoneBot 的 JSON 消息的处理
     */
    static void parseWebSocketJson(String message) {
        // 组合消息
        Gson gson = new Gson();
        BaseReturnBody baseReturnBody = gson.fromJson(message, BaseReturnBody.class);
        JsonElement data = baseReturnBody.getData();
        switch (baseReturnBody.getApi()) {
            case "broadcast":
                MessageReturnBody messageList = gson.fromJson(data, MessageReturnBody.class);
                TextComponent textComponent = parseMessageToTextComponent(messageList.getMessageList());
                instance.getServer().spigot().broadcast(textComponent);
                break;
            case "send_title":
                SendTitleReturnBody sendTitleReturnBody = gson.fromJson(data, SendTitleReturnBody.class);
                for (Player player : instance.getServer().getOnlinePlayers()) {
                    player.sendTitle(
                            sendTitleReturnBody.getSendTitle().getTitle(),
                            sendTitleReturnBody.getSendTitle().getSubtitle(),
                            sendTitleReturnBody.getSendTitle().getFadein(),
                            sendTitleReturnBody.getSendTitle().getStay(),
                            sendTitleReturnBody.getSendTitle().getFadeout()
                    );
                }
                break;
            case "actionbar":
                ActionbarReturnBody actionMessageList = gson.fromJson(data, ActionbarReturnBody.class);
                TextComponent actionTextComponent = parseMessageToTextComponent(actionMessageList.getMessageList());
                for (Player player : instance.getServer().getOnlinePlayers()) {
                    sendActionBar(player, actionTextComponent);
                }
                break;
        }
    }

    static void sendActionBar(Player player, TextComponent textComponent) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, textComponent);
    }

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
    static String unicodeEncode(String string) {
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

    public static WsClient connectWebsocket(String url) {
        try {
            WsClient wsClient = new WsClient(url);
            wsClient.connect();
            return wsClient;
        } catch (URISyntaxException e) {
            LOGGER.warning("[MC_QQ] 连接 WebSocket 失败: " + e.getMessage());
        }
        return null;
    }
}
