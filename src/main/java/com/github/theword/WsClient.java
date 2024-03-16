package com.github.theword;

import com.github.theword.utils.HandleWebsocketMessage;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import static com.github.theword.MCQQ.*;
import static com.github.theword.MCQQ.config;
import static com.github.theword.utils.Tool.unicodeEncode;

public class WsClient extends WebSocketClient {
    private int reconnectTimes = 1;
    private final Timer timer = new Timer();

    public WsClient(String websocketUrl) throws URISyntaxException {
        this(new URI(websocketUrl));
    }

    public WsClient(URI uri) {
        super(uri);
        addHeader("x-self-name", unicodeEncode(config.getServerName()));
    }

    /**
     * 连接打开时
     *
     * @param serverHandshake ServerHandshake
     */
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        LOGGER.info(String.format("[MC_QQ] 已成功连接至 %s WebSocket 服务器。", getURI()));
        reconnectTimes = 1;
        LOGGER.warning(String.format("[MC_QQ] %s 的重连次数已重置", getURI()));
    }

    /**
     * 收到消息时触发
     * 向服务器游戏内公屏发送信息
     */
    @Override
    public void onMessage(String message) {
        if (config.isEnableMcQQ()) {
            try {
                new HandleWebsocketMessage().handleWebSocketJson(message);
            } catch (Exception e) {
                LOGGER.warning(String.format("[MC_QQ] 解析来自 %s 的 WebSocket 消息时出现异常", getURI()));
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭时
     *
     * @param code   关闭码
     * @param reason 关闭信息
     * @param remote 是否关闭
     */
    @Override
    public void onClose(int code, String reason, boolean remote) {
        if (remote && reconnectTimes <= config.getReconnectMaxTimes()) {
            reconnectWebsocket();
        }
    }

    public void reconnectWebsocket() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                reconnect();
            }
        };
        timer.schedule(timerTask, config.getReconnectInterval() * 1000L);
    }

    @Override
    public void reconnect() {
        if (config.isEnableReconnectMessage()) {
            LOGGER.info(String.format("[MC_QQ] %s 的 WebSocket 连接已断开，尝试第 %d 次重连...", getURI(), reconnectTimes));
        }
        reconnectTimes++;
        super.reconnect();
    }

    /**
     * 触发异常时
     *
     * @param exception 所有异常
     */
    @Override
    public void onError(Exception exception) {
        LOGGER.warning(String.format("[MC_QQ] %s 的 WebSocket 连接出现异常：%s", getURI(), exception.getMessage()));
        if (exception instanceof ConnectException && exception.getMessage().equals("Connection refused: connect") && reconnectTimes <= config.getReconnectMaxTimes()) {
            reconnectWebsocket();
        }
    }

    public Timer getTimer() {
        return timer;
    }

    /**
     * 发送消息
     *
     * @param message 消息
     */
    public void sendMessage(String message) {
        if (isOpen()) {
            send(message);
        } else {
            LOGGER.info(String.format("[MC_QQ] %s 的 WebSocket 连接未打开，消息发送失败", getURI()));
        }
    }
}
