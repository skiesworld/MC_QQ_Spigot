package com.github.theword;


import org.bukkit.configuration.file.FileConfiguration;

import static com.github.theword.MCQQ.instance;

public class ConfigReader {

    /**
     * 获取配置文件信息
     */
    static FileConfiguration config = instance.getConfig();

    /**
     * 获取是否启用插件
     *
     * @return boolean Enable
     */
    static boolean getEnable() {
        return config.getBoolean("enable_mc_qq", true);
    }

    /**
     * 获取服务器名
     *
     * @return String serverName
     */
    public static String getServerName() {
        return config.getString("server_name", "Server");
    }

    /**
     * 获取链接
     *
     * @return int Port
     */
    static String getWsUrl() {
        return config.getString("websocket_url", "ws://127.0.0.1:8080/minecraft/ws");
    }

    /**
     * 获取链接
     *
     * @return int Port
     */
    static boolean getEnableReconnectMsg() {
        return config.getBoolean("enable_reconnect_msg", true);
    }

    /**
     * 获取是否启用命令监听
     *
     * @return boolean Command
     */
    static boolean getCommandMessage() {
        return getEnable() && config.getBoolean("command_message", false);
    }

    /**
     * 获取是否启用 死亡事件 推送
     *
     * @return boolean deathMessage
     */
    static boolean getDeathMessage() {
        return getEnable() && config.getBoolean("death_message", true);
    }

    /**
     * 获取是否启用 加入/退出 推送
     *
     * @return boolean JoinQuit
     */
    static boolean getJoinQuit() {
        return getEnable() && config.getBoolean("join_quit", true);
    }

}
