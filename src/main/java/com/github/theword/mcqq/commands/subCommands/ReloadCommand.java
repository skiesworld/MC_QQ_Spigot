package com.github.theword.mcqq.commands.subCommands;

import com.github.theword.mcqq.commands.SubCommand;
import com.github.theword.constant.CommandConstantMessage;
import com.github.theword.constant.WebsocketConstantMessage;
import com.github.theword.utils.Config;
import com.github.theword.websocket.WsClient;
import org.bukkit.command.CommandSender;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.github.theword.utils.Tool.config;
import static com.github.theword.utils.Tool.wsClientList;

public class ReloadCommand extends SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "重载配置文件并重新连接所有 Websocket Client";
    }

    @Override
    public String getSyntax() {
        return "/mcqq reload";
    }

    @Override
    public String getUsage() {
        return "使用：/mcqq reload";
    }

    @Override
    public boolean onCommand(CommandSender commandSender, String[] args) {
        config = new Config(false);
        commandSender.sendMessage(CommandConstantMessage.RELOAD_CONFIG);
        wsClientList.forEach(wsClient -> {
            if (!wsClient.isClosed() && !wsClient.isClosing()) {
                commandSender.sendMessage(String.format(CommandConstantMessage.RELOAD_CLOSE_WEBSOCKET_CLIENT, wsClient.getURI()));
                wsClient.close();
            }
            wsClient.getTimer().cancel();
        });
        wsClientList.clear();
        commandSender.sendMessage(CommandConstantMessage.RELOAD_CLEAR_WEBSOCKET_CLIENT_LIST);

        config.getWebsocketUrlList().forEach(websocketUrl -> {
            try {
                WsClient wsClient = new WsClient(new URI(websocketUrl));
                wsClient.connect();
                wsClientList.add(wsClient);
            } catch (URISyntaxException e) {
                commandSender.sendMessage(String.format(WebsocketConstantMessage.WEBSOCKET_ERROR_URI_SYNTAX_ERROR, websocketUrl));
            }
        });
        commandSender.sendMessage(CommandConstantMessage.RELOADED);
        return true;
    }

    @Override
    public List<String> getSubCommands(CommandSender commandSender, String[] args) {
        return new ArrayList<>();
    }
}
