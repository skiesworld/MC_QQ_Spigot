package com.github.theword.mcqq;


import com.github.theword.mcqq.commands.CommandExecutor;
import com.github.theword.mcqq.constant.BaseConstant;
import com.github.theword.mcqq.constant.WebsocketConstantMessage;
import com.github.theword.mcqq.handleMessage.HandleApiService;
import com.github.theword.mcqq.handleMessage.HandleCommandReturnMessageService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

import static com.github.theword.mcqq.utils.Tool.initTool;
import static com.github.theword.mcqq.utils.Tool.websocketManager;

public final class MCQQ extends JavaPlugin {
    public static JavaPlugin instance;

    @Override
    public void onLoad() {
        initTool(false, new HandleApiService(), new HandleCommandReturnMessageService());
    }

    @Override
    public void onEnable() {
        instance = this;
        websocketManager.startWebsocket(false);
        Bukkit.getPluginManager().registerEvents(new EventProcessor(), this);
        Objects.requireNonNull(getCommand(BaseConstant.COMMAND_HEADER)).setExecutor(new CommandExecutor());
    }

    @Override
    public void onDisable() {
        websocketManager.stopWebsocket(1000, WebsocketConstantMessage.Client.CLOSING, null);
    }

}
