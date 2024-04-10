package com.github.theword.commands.subCommands;

import com.github.theword.commands.SubCommand;
import com.github.theword.constant.CommandConstantMessage;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.github.theword.utils.Tool.wsClientList;

public class ReconnectCommand extends SubCommand {
    @Override
    public String getName() {
        return "reconnect";
    }

    @Override
    public String getDescription() {
        return "重新连接 Websocket Clients.";
    }

    @Override
    public String getSyntax() {
        return "/mcqq reconnect";
    }

    @Override
    public String getUsage() {
        return "使用：/mcqq reconnect [all]";
    }

    @Override
    public boolean onCommand(CommandSender commandSender, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reconnect")) {
                return reconnectWebsocketClient(commandSender, false);
            } else {
                commandSender.sendMessage(getUsage());
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("all")) {
                return reconnectWebsocketClient(commandSender, true);
            } else {
                commandSender.sendMessage(getUsage());
            }
        } else {
            commandSender.sendMessage(getUsage());
        }
        return false;
    }

    @Override
    public List<String> getSubCommands(CommandSender commandSender, String[] args) {
        return new ArrayList<String>() {{
            add("all");
        }};
    }

    private boolean reconnectWebsocketClient(CommandSender commandSender, boolean all) {
        if (all) {
            commandSender.sendMessage(CommandConstantMessage.RECONNECT_ALL_CLIENT);
        } else {
            commandSender.sendMessage(CommandConstantMessage.RECONNECT_NOT_OPEN_CLIENT);
        }
        AtomicInteger opened = new AtomicInteger();
        wsClientList.forEach(wsClient -> {
            if (all || !wsClient.isOpen()) {
                wsClient.reconnectWebsocket();
                commandSender.sendMessage(String.format(CommandConstantMessage.RECONNECT_MESSAGE, wsClient.getURI()));
            } else {
                opened.getAndIncrement();
            }
        });
        if (opened.get() == wsClientList.size()) {
            commandSender.sendMessage(CommandConstantMessage.RECONNECT_NO_CLIENT_NEED_RECONNECT);
        }
        commandSender.sendMessage(CommandConstantMessage.RECONNECTED);
        return true;
    }
}
