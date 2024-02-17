package com.github.theword;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import static com.github.theword.MCQQ.wsClient;
import static com.github.theword.MCQQ.httpHeaders;
import static com.github.theword.MCQQ.connectTime;
import static com.github.theword.MCQQ.serverOpen;
import static com.github.theword.MCQQ.LOGGER;
import static com.github.theword.Utils.parseWebSocketJson;

public class WsClient extends WebSocketClient {


    public WsClient() throws URISyntaxException {
        super(new URI(ConfigReader.getWsUrl()), httpHeaders);
    }

    /**
     * 连接打开时
     *
     * @param serverHandshake ServerHandshake
     */
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        connectTime = 0;
        LOGGER.info("已成功连接 WebSocket 服务器。");
    }

    /**
     * 收到消息时触发
     * 向服务器游戏内公屏发送信息
     */
    @Override
    public void onMessage(String message) {
        if (ConfigReader.getEnable()) {
            try {
                parseWebSocketJson(message);
            } catch (Exception e) {
                LOGGER.warning("解析消息时出现错误：" + message);
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
        if (serverOpen && wsClient != null) {
            connectTime++;
            if (ConfigReader.getEnableReconnectMsg()) {
                LOGGER.warning("WebSocket 连接已断开,正在第 " + connectTime + " 次重新连接。");
            }
            wsClient.sendPing();
        }
    }

    /**
     * 触发异常时
     *
     * @param exception 所有异常
     */
    @Override
    public void onError(Exception exception) {
        if (serverOpen && wsClient != null) {
            try {
                wsClient = new WsClient();
                Thread.sleep(3000);
                wsClient.connectBlocking();
            } catch (URISyntaxException e) {
                LOGGER.warning("WebSocket 连接失败，URL 格式错误。");
            } catch (InterruptedException e) {
                LOGGER.warning("WebSocket 连接失败，线程中断。");
            }
        }
    }

    /**
     * 发送消息
     *
     * @param message 消息
     */
    public void sendMessage(String message) {
        if (wsClient.isOpen() && ConfigReader.getEnable()) {
            wsClient.send(message);
        } else {
            LOGGER.warning("发送消息失败，没有连接到 WebSocket 服务器。");
        }
    }
}
