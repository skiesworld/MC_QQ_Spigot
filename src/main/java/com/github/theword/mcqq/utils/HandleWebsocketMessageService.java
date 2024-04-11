package com.github.theword.mcqq.utils;

import com.github.theword.constant.WebsocketConstantMessage;
import com.github.theword.returnBody.ActionbarReturnBody;
import com.github.theword.returnBody.BaseReturnBody;
import com.github.theword.returnBody.MessageReturnBody;
import com.github.theword.returnBody.SendTitleReturnBody;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import static com.github.theword.mcqq.MCQQ.instance;
import static com.github.theword.utils.Tool.logger;

public class HandleWebsocketMessageService implements HandleWebsocketMessage {

    private final ParseJsonToEvent parseJsonToEvent = new ParseJsonToEvent();

    /**
     * 来自 NoneBot 的 JSON 消息的处理
     */
    public void handleWebSocketJson(String message) {
        // 组合消息
        Gson gson = new Gson();
        BaseReturnBody baseReturnBody = gson.fromJson(message, BaseReturnBody.class);
        JsonElement data = baseReturnBody.getData();
        switch (baseReturnBody.getApi()) {
            case "broadcast":
                MessageReturnBody messageList = gson.fromJson(data, MessageReturnBody.class);
                TextComponent textComponent = parseJsonToEvent.parseMessageToTextComponent(messageList.getMessageList());
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
                TextComponent actionTextComponent = parseJsonToEvent.parseMessageToTextComponent(actionMessageList.getMessageList());
                for (Player player : instance.getServer().getOnlinePlayers()) {
                    sendActionBar(player, actionTextComponent);
                }
                break;
            default:
                logger.warn(WebsocketConstantMessage.WEBSOCKET_UNKNOWN_API + baseReturnBody.getApi());
                break;
        }
    }

    void sendActionBar(Player player, TextComponent textComponent) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, textComponent);
    }
}
