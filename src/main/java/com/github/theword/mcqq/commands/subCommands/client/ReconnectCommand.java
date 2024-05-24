package com.github.theword.mcqq.commands.subCommands.client;

import com.github.theword.mcqq.commands.SpigotSubCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

import static com.github.theword.mcqq.utils.Tool.websocketManager;

public class ReconnectCommand extends ReconnectCommandAbstract implements SpigotSubCommand {

    @Override
    public boolean onCommand(CommandSender commandSender, String[] args) {
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("reconnect")) {
                websocketManager.reconnectWebsocketClients(false, commandSender);
                return true;
            }
        } else if (args.length == 3) {
            if (args[2].equalsIgnoreCase("all")) {
                websocketManager.reconnectWebsocketClients(true, commandSender);
                return true;
            }
        }
        commandSender.sendMessage(getUsage());
        return false;
    }

    @Override
    public List<String> getSubCommands(CommandSender commandSender, String[] args) {
        return new ArrayList<String>() {{
            add("all");
        }};
    }

    @Override
    public String getPrefix() {
        return "client";
    }
}
