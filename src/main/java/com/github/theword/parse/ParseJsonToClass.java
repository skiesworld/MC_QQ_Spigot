package com.github.theword.parse;

import com.github.theword.returnBody.MessageReturnBody;
import com.github.theword.returnBody.returnModle.MsgItem;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

import static com.github.theword.Utils.say;

public class ParseJsonToClass {

    /**
     * 将 MessageReturnBody 转换为 TextComponent
     *
     * @param messageReturnBody MessageReturnBody
     * @return TextComponent
     */
    public static TextComponent parseMessageToTextComponent(MessageReturnBody messageReturnBody) {
        TextComponent component = new TextComponent("[MC_QQ] ");
        component.setColor(ChatColor.YELLOW);
        StringBuilder msgLogText = new StringBuilder();

        for (MsgItem msgItem : messageReturnBody.getMessageList()) {
            TextComponent msgComponent = new TextComponent();
            msgComponent.setText(msgItem.getMsgText());
            msgComponent.setColor(getColor(msgItem.getColor()));
            if (msgItem.getActionEvent() != null) {
                msgComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, msgItem.getActionEvent().getClickEventUrl()));
                msgComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(msgItem.getActionEvent().getHoverEventText())));
            }
            component.addExtra(msgComponent);
            msgLogText.append(msgItem.getMsgText());
        }
        say(msgLogText.toString());
        return component;
    }

    /**
     * @param color 颜色
     * @return ChatColor 对象
     */
    static ChatColor getColor(String color) {
        switch (color) {
            case "black":
                return ChatColor.BLACK;
            case "dark_blue":
                return ChatColor.DARK_BLUE;
            case "dark_green":
                return ChatColor.DARK_GREEN;
            case "dark_aqua":
                return ChatColor.DARK_AQUA;
            case "dark_red":
                return ChatColor.DARK_RED;
            case "dark_purple":
                return ChatColor.DARK_PURPLE;
            case "gold":
                return ChatColor.GOLD;
            case "gray":
                return ChatColor.GRAY;
            case "dark_gray":
                return ChatColor.DARK_GRAY;
            case "blue":
                return ChatColor.BLUE;
            case "green":
                return ChatColor.GREEN;
            case "aqua":
                return ChatColor.AQUA;
            case "red":
                return ChatColor.RED;
            case "light_purple":
                return ChatColor.LIGHT_PURPLE;
            case "yellow":
                return ChatColor.YELLOW;
            case "white":
            default:
                return ChatColor.WHITE;
        }

    }


    public static void parseMessageToSendTitle() {

    }
}
