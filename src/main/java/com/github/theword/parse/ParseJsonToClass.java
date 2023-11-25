package com.github.theword.parse;

import com.github.theword.returnBody.returnModle.MyBaseComponent;
import com.github.theword.returnBody.returnModle.MyTextComponent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;

import static com.github.theword.Utils.say;

public class ParseJsonToClass {

    /**
     * 将 MyBaseComponent 转换为 TextComponent
     *
     * @param myBaseComponentList 消息列表
     * @return TextComponent
     */
    public static TextComponent parseMessageToTextComponent(List<? extends MyBaseComponent> myBaseComponentList) {
        TextComponent component = new TextComponent();
        StringBuilder msgLogText = new StringBuilder();

        for (MyBaseComponent myBaseComponent : myBaseComponentList) {
            TextComponent msgComponent = new TextComponent();

            // 配置 BaseComponent 基本属性
            msgComponent.setText(myBaseComponent.getText());
            msgComponent.setColor(getColor(myBaseComponent.getColor()));
            msgComponent.setBold(myBaseComponent.isBold());
            msgComponent.setItalic(myBaseComponent.isItalic());
            msgComponent.setUnderlined(myBaseComponent.isUnderlined());
            msgComponent.setStrikethrough(myBaseComponent.isStrikethrough());
            msgComponent.setObfuscated(myBaseComponent.isObfuscated());

            // 配置 TextComponent 额外属性
            if (myBaseComponent instanceof MyTextComponent) {
                MyTextComponent myTextComponent = (MyTextComponent) myBaseComponent;
                if (myTextComponent.getClickEvent() != null) {
                    ClickEvent.Action tempAction = null;
                    switch (myTextComponent.getClickEvent().getAction()) {
                        case "open_url":
                            tempAction = ClickEvent.Action.OPEN_URL;
                            break;
                        case "open_file":
                            tempAction = ClickEvent.Action.OPEN_FILE;
                            break;
                        case "run_command":
                            tempAction = ClickEvent.Action.RUN_COMMAND;
                            break;
                        case "suggest_command":
                            tempAction = ClickEvent.Action.SUGGEST_COMMAND;
                            break;
                        case "change_page":
                            tempAction = ClickEvent.Action.CHANGE_PAGE;
                            break;
                        default:
                            break;
                    }
                    ClickEvent clickEvent = new ClickEvent(tempAction, myTextComponent.getClickEvent().getValue());
                    msgComponent.setClickEvent(clickEvent);
                }
                // TODO 悬浮事件待完善
                if (myTextComponent.getHoverEvent() != null) {
                    HoverEvent hoverEvent = null;
                    switch (myTextComponent.getHoverEvent().getAction()) {
                        case "show_text":
                            TextComponent textComponent = parseMessageToTextComponent(myTextComponent.getHoverEvent().getBaseComponentList());
                            BaseComponent[] baseComponent = new BaseComponent[]{textComponent};
                            hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, baseComponent);
                            break;
                        case "show_item":
//                            hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_ITEM, new Item());
                            break;
                        case "show_entity":
//                            hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_ENTITY, new Entity());
                            break;
                        default:
                            break;
                    }
                    msgComponent.setHoverEvent(hoverEvent);
                }
            }


            component.addExtra(msgComponent);
            if (!myBaseComponent.getText().equals("[MC_QQ] ")) {
                msgLogText.append(myBaseComponent.getText());
            }
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

}
