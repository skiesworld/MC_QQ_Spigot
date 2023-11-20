package com.github.theword;

import com.github.theword.returnBody.MessageReturnBody;
import com.github.theword.returnBody.MinecraftReturnBody;
import com.google.gson.Gson;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import static com.github.theword.MC_QQ.instance;
import static com.github.theword.parse.ParseJsonToClass.parseMessageToTextComponent;

public class Utils {

    /**
     * 定义方法 Say()
     * 向服务器后台发送信息
     */
    public static void say(String msg) {
        CommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage("[MC_QQ] " + msg);
    }

    /**
     * 来自 NoneBot 的 JSON 消息的处理
     */
    static void parseWebSocketJson(String message) {
        // 组合消息

        Gson gson = new Gson();
        MinecraftReturnBody minecraftReturnBody = gson.fromJson(message, MinecraftReturnBody.class);

        switch (minecraftReturnBody.getApi()) {
            case "broadcast":
                TextComponent textComponent = parseMessageToTextComponent(gson.fromJson(minecraftReturnBody.getData(), MessageReturnBody.class));
                instance.getServer().spigot().broadcast(textComponent);
        }
    }


    /**
     * 字符串转为 unicode 编码
     *
     * @param string 字符串
     * @return unicode编码
     */
    static String unicodeEncode(String string) {
        char[] utfBytes = string.toCharArray();
        StringBuilder unicodeBytes = new StringBuilder();
        for (char utfByte : utfBytes) {
            String hexB = Integer.toHexString(utfByte);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes.append("\\u").append(hexB);
        }
        return unicodeBytes.toString();
    }
}
