package com.github.theword;


import com.github.theword.commands.CommandManager;
import com.github.theword.constant.BaseConstant;
import com.github.theword.constant.WebsocketConstantMessage;
import com.github.theword.utils.Config;
import com.github.theword.utils.HandleWebsocketMessageService;
import com.github.theword.websocket.WsClient;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

import static com.github.theword.utils.Tool.*;

public final class MCQQ extends JavaPlugin {
    public static JavaPlugin instance;

    @Override
    public void onLoad() {
        logger = LoggerFactory.getLogger("");
        handleWebsocketMessage = new HandleWebsocketMessageService();
        config = new Config(false);
        wsClientList = new ArrayList<>();
        logger.info(BaseConstant.INITIALIZED);
    }

    @Override
    public void onEnable() {
        instance = this;
        logger.info(WebsocketConstantMessage.WEBSOCKET_RUNNING);
        config.getWebsocketUrlList().forEach(websocketUrl -> {
            try {
                WsClient wsClient = new WsClient(new URI(websocketUrl));
                wsClient.connect();
                wsClientList.add(wsClient);
            } catch (URISyntaxException e) {
                logger.warn(String.format(WebsocketConstantMessage.WEBSOCKET_ERROR_URI_SYNTAX_ERROR, websocketUrl));
            }
        });
        Bukkit.getPluginManager().registerEvents(new EventProcessor(), this);
        Objects.requireNonNull(getCommand("mcqq")).setExecutor(new CommandManager());
    }

    @Override
    public void onDisable() {
        wsClientList.forEach(
                wsClient -> wsClient.stopWithoutReconnect(
                        1000,
                        String.format(WebsocketConstantMessage.WEBSOCKET_CLOSING, wsClient.getURI())
                )
        );
    }

}
