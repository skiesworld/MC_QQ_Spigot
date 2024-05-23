package com.github.theword.mcqq.commands.subCommands;

import com.github.theword.mcqq.commands.CommandManager;
import com.github.theword.mcqq.commands.SpigotSubCommand;
import com.github.theword.mcqq.commands.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand extends HelpCommandAbstract implements SpigotSubCommand {
    @Override
    public boolean onCommand(CommandSender commandSender, String[] args) {
        commandSender.sendMessage("-------------------");
        for (SubCommand subCommand : new CommandManager().getSpigotSubCommandList()) {
            commandSender.sendMessage(subCommand.getUsage() + "---" + subCommand.getDescription());
        }
        commandSender.sendMessage("-------------------");
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
