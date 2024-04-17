package com.github.theword.mcqq.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommand {
    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract String getUsage();

    public abstract boolean onCommand(CommandSender commandSender, String[] args);

    public abstract List<String> getSubCommands(CommandSender commandSender, String[] args);

}
