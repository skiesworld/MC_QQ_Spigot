package com.github.theword.mcqq.commands.subCommands;

import com.github.theword.mcqq.commands.SpigotSubCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

import static com.github.theword.mcqq.utils.Tool.websocketManager;

public class ReloadCommand extends ReloadCommandAbstract implements SpigotSubCommand {
    @Override
    public boolean onCommand(CommandSender commandSender, String[] args) {
        websocketManager.reloadWebsocket(false, commandSender);
        return true;
    }

    @Override
    public List<String> getSubCommands(CommandSender commandSender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public String getPrefix() {
        return null;
    }
}
