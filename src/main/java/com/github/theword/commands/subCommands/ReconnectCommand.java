package com.github.theword.commands.subCommands;

import com.github.theword.commands.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

import static com.github.theword.MCQQ.LOGGER;
import static com.github.theword.MCQQ.wsClientList;

public class ReconnectCommand extends SubCommand {
    @Override
    public String getName() {
        return "reconnect";
    }

    @Override
    public String getDescription() {
        return "Reconnect Websocket Clients.";
    }

    @Override
    public String getSyntax() {
        return "/mcqq reconnect";
    }

    @Override
    public boolean onCommand(CommandSender commandSender, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reconnect")) {
                return reconnectWebsocketClient(false);
            } else {
                LOGGER.info("Usage: /mcqq reconnect [all]");
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("all")) {
                return reconnectWebsocketClient(true);
            } else {
                LOGGER.info("Usage: /mcqq reconnect [all]");
            }
        } else {
            LOGGER.info("Usage: /mcqq reconnect [all]");
        }
        return false;
    }

    @Override
    public List<String> getSubCommands(CommandSender commandSender, String[] args) {
        return new ArrayList<>() {{
            add("all");
        }};
    }

    private boolean reconnectWebsocketClient(boolean all) {
        try {
            wsClientList.forEach(wsClient -> {
                if (all || !wsClient.isOpen()) {
                    wsClient.reconnectWebsocket();
                    LOGGER.info("Reconnect websocket client: " + wsClient.getURI());
                }
            });
        } catch (Exception e) {
            LOGGER.warning("[MC_QQ] Error on reconnecting websocket : " + e.getMessage());
            return false;
        }
        return true;
    }
}
