package com.github.theword;


import com.github.theword.commands.CommandManager;
import com.github.theword.constant.WebsocketConstantMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;


public final class MCQQ extends JavaPlugin {
    public static Logger LOGGER;
    public static JavaPlugin instance;
    public static Config config;
    public static List<WsClient> wsClientList = new ArrayList<>();

    @Override
    public void onLoad() {
        LOGGER = this.getLogger();
        config = new Config(false);
    }

    @Override
    public void onEnable() {
        instance = this;
        LOGGER.info(WebsocketConstantMessage.WEBSOCKET_RUNNING);

        config.getWebsocketUrlList().forEach(url -> {
            try {
                WsClient wsClient = new WsClient(url);
                wsClient.connect();
                wsClientList.add(wsClient);
            } catch (URISyntaxException e) {
                LOGGER.warning(String.format(WebsocketConstantMessage.WEBSOCKET_ERROR_URI_SYNTAX_ERROR, url));
            }
        });
        Bukkit.getPluginManager().registerEvents(new EventProcessor(), this);
        Objects.requireNonNull(getCommand("mcqq")).setExecutor(new CommandManager());
    }

    @Override
    public void onDisable() {
        wsClientList.forEach(
                wsClient -> {
                    wsClient.getTimer().cancel();
                    wsClient.close(
                            1000,
                            String.format(WebsocketConstantMessage.WEBSOCKET_CLOSING, wsClient.getURI())
                    );
                }
        );
    }

}
