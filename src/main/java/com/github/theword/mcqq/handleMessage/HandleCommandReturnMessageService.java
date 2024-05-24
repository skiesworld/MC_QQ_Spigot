package com.github.theword.mcqq.handleMessage;

import org.bukkit.command.CommandSender;

public class HandleCommandReturnMessageService implements HandleCommandReturnMessage {
    @Override
    public void handleCommandReturnMessage(Object object, String message) {
        CommandSender commandSender = (CommandSender) object;
        commandSender.sendMessage(message);
    }
}
