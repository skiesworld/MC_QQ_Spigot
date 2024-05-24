package com.github.theword.mcqq.commands;

import com.github.theword.mcqq.commands.subCommands.ClientCommand;
import com.github.theword.mcqq.commands.subCommands.HelpCommand;
import com.github.theword.mcqq.commands.subCommands.ReloadCommand;
import com.github.theword.mcqq.commands.subCommands.ServerCommand;
import com.github.theword.mcqq.commands.subCommands.client.ReconnectCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CommandManager {
    List<SpigotSubCommand> spigotSubCommandList = new ArrayList<>();


    public CommandManager() {
        spigotSubCommandList.add(new HelpCommand());
        spigotSubCommandList.add(new ReloadCommand());
        spigotSubCommandList.add(new ClientCommand());
        spigotSubCommandList.add(new ServerCommand());

        spigotSubCommandList.add(new ReconnectCommand());
    }
}
