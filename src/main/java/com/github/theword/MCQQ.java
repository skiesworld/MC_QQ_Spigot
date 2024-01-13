package com.github.theword;


import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static com.github.theword.Utils.say;
import static com.github.theword.Utils.unicodeEncode;


public final class MCQQ extends JavaPlugin {
    // 静态变量 wsClient
    static WSClient wsClient;

    // 静态变量 instance
    static JavaPlugin instance;

    // 连接次数
    static int connectTime;

    // 服务器是否打开
    static boolean serverOpen;

    // WebSocket连接头部信息
    static Map<String, String> httpHeaders;

    @Override
    public void onLoad() {
        // 如果配置文件不存在，Bukkit 会保存默认的配置
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        // 赋值插件实例
        instance = this;

        // 服务器状态设为 True
        serverOpen = true;

        // 初始化连接次数
        connectTime = 0;

        // WebSocket 头部信息
        httpHeaders = new HashMap<>();
        httpHeaders.put("x-self-name", unicodeEncode(ConfigReader.getServerName()));

        // new Ws 对象，并将配置文件中 地址 与 端口 写入
        try {
            wsClient = new WSClient();
            // 启动 WebSocket
            wsClient.connect();
        } catch (URISyntaxException e) {
            say("WebSocket 连接失败，URL 格式错误。");
        }
        // 注册事件
        Bukkit.getPluginManager().registerEvents(new EventProcessor(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        serverOpen = false;
        if (wsClient.isOpen()) {
            say("WebSocket Client 正在关闭...");
            wsClient.close();
        }
    }

}
