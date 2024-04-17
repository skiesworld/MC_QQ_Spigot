package com.github.theword.mcqq.commands;

import com.github.theword.mcqq.commands.subCommands.ReconnectCommand;
import com.github.theword.mcqq.commands.subCommands.ReloadCommand;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommandManager implements TabExecutor {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new ReloadCommand());
        subCommands.add(new ReconnectCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0) {
            commandSender.sendMessage("-------------------");
            for (SubCommand subCommand : getSubCommands()) {
                commandSender.sendMessage(subCommand.getUsage() + "---" + subCommand.getDescription());
            }
            commandSender.sendMessage("-------------------");
            return true;
        } else {
            for (SubCommand subCommand : getSubCommands()) {
                if (subCommand.getName().equalsIgnoreCase(args[0])) {
                    return subCommand.onCommand(commandSender, args);
                }

            }
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("mcqq")) {
            if (args.length == 1) {
                return new CommandManager().getSubCommands().stream().map(SubCommand::getName).collect(Collectors.toList());
            } else if (args.length == 2) {
                for (SubCommand subCommand : getSubCommands()) {
                    if (subCommand.getName().equalsIgnoreCase(args[1])) {
                        return subCommand.getSubCommands(commandSender, args);
                    }
                }
            }
        }
        return new ArrayList<>();
    }
}
