package com.github.theword.commands.subCommands;

import com.github.theword.Config;
import com.github.theword.WsClient;
import com.github.theword.commands.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

import static com.github.theword.MCQQ.*;
import static com.github.theword.MCQQ.wsClientList;
import static com.github.theword.utils.Tool.connectWebsocket;

public class ReloadCommand extends SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reload config and reconnect all client";
    }

    @Override
    public String getSyntax() {
        return "/mcqq reload";
    }

    @Override
    public boolean onCommand(CommandSender commandSender, String[] args) {
        return reload();
    }

    @Override
    public List<String> getSubCommands(CommandSender commandSender, String[] args) {
        return new ArrayList<>();
    }

    private boolean reload() {
        try {
            config = new Config(false);
            LOGGER.info("Config reloaded");
            wsClientList.forEach(wsClient -> {
                if (!wsClient.isClosed() && !wsClient.isClosing()) {
                    wsClient.close();
                }
                wsClient.getTimer().cancel();
            });
            wsClientList.clear();
            LOGGER.info("All websocket client closed");

            config.getWebsocketUrlList().forEach(websocketUrl -> {
                WsClient wsClient = connectWebsocket(websocketUrl);
                if (wsClient == null) {
                    LOGGER.warning("Failed to connect websocket: " + websocketUrl);
                } else {
                    wsClientList.add(wsClient);
                }
            });
        } catch (Exception e) {
            LOGGER.warning("[MC_QQ] Error on  reloading config : " + e.getMessage());
            return false;
        }
        return true;
    }
}
