package com.github.theword.mcqq.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface SpigotSubCommand extends SubCommand {

    boolean onCommand(CommandSender commandSender, String[] args);

    List<String> getSubCommands(CommandSender commandSender, String[] args);

    String getPrefix();

}
