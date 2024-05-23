package com.github.theword.mcqq.handleMessage;

import com.github.theword.mcqq.returnBody.ActionbarReturnBody;
import com.github.theword.mcqq.returnBody.MessageReturnBody;
import com.github.theword.mcqq.returnBody.SendTitleReturnBody;
import com.github.theword.mcqq.utils.ParseJsonToEvent;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import static com.github.theword.mcqq.MCQQ.instance;

public class HandleApiService implements HandleApi {

    private final ParseJsonToEvent parseJsonToEvent = new ParseJsonToEvent();

    @Override
    public void handleBroadcastMessage(MessageReturnBody messageReturnBody) {
        TextComponent textComponent = parseJsonToEvent.parseMessageToTextComponent(messageReturnBody.getMessageList());
        instance.getServer().spigot().broadcast(textComponent);
    }

    @Override
    public void handleSendTitleMessage(SendTitleReturnBody sendTitleReturnBody) {
        for (Player player : instance.getServer().getOnlinePlayers()) {
            player.sendTitle(
                    sendTitleReturnBody.getSendTitle().getTitle(),
                    sendTitleReturnBody.getSendTitle().getSubtitle(),
                    sendTitleReturnBody.getSendTitle().getFadein(),
                    sendTitleReturnBody.getSendTitle().getStay(),
                    sendTitleReturnBody.getSendTitle().getFadeout()
            );
        }
    }

    @Override
    public void handleActionBarMessage(ActionbarReturnBody actionbarReturnBody) {
        TextComponent actionTextComponent = parseJsonToEvent.parseMessageToTextComponent(actionbarReturnBody.getMessageList());
        for (Player player : instance.getServer().getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, actionTextComponent);
        }
    }
}
