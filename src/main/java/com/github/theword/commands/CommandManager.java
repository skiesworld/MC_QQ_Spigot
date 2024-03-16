package com.github.theword.commands;

import com.github.theword.commands.subCommands.ReconnectCommand;
import com.github.theword.commands.subCommands.ReloadCommand;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandManager implements TabExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new ReloadCommand());
        subCommands.add(new ReconnectCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length > 0) {
            for (SubCommand subCommand : subCommands) {
                if (subCommand.getName().equalsIgnoreCase(args[0])) {
                    return subCommand.onCommand(commandSender, args);
                }

            }
        }
        return false;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
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
