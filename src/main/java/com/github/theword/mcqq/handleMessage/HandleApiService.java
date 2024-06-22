package com.github.theword.mcqq.handleMessage;

import com.github.theword.mcqq.returnBody.returnModle.MyBaseComponent;
import com.github.theword.mcqq.returnBody.returnModle.MyTextComponent;
import com.github.theword.mcqq.returnBody.returnModle.SendTitle;
import com.github.theword.mcqq.utils.ParseJsonToEvent;
import com.github.theword.mcqq.utils.Tool;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.java_websocket.WebSocket;

import java.util.List;

import static com.github.theword.mcqq.MCQQ.instance;

public class HandleApiService implements HandleApi {

    private final ParseJsonToEvent parseJsonToEvent = new ParseJsonToEvent();

    @Override
    public void handleBroadcastMessage(WebSocket webSocket, List<MyTextComponent> messageList) {
        TextComponent textComponent = parseJsonToEvent.parsePerMessageToTextComponent(Tool.getPrefixComponent());
        textComponent.addExtra(parseJsonToEvent.parseMessageToTextComponent(messageList));
        instance.getServer().spigot().broadcast(textComponent);
    }

    @Override
    public void handleSendTitleMessage(WebSocket webSocket, SendTitle sendTitle) {
        for (Player player : instance.getServer().getOnlinePlayers()) {
            player.sendTitle(
                    parseJsonToEvent.parseMyBaseCommentToStringWithStyle(sendTitle.getTitle()),
                    parseJsonToEvent.parseMyBaseCommentToStringWithStyle(sendTitle.getSubtitle()),
                    sendTitle.getFadein(),
                    sendTitle.getStay(),
                    sendTitle.getFadeout()
            );
        }
    }

    @Override
    public void handleActionBarMessage(WebSocket webSocket, List<MyBaseComponent> messageList) {
        TextComponent actionbarTextComponent = parseJsonToEvent.parseMessageToTextComponent(messageList);
        for (Player player : instance.getServer().getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, actionbarTextComponent);
        }
    }
}
