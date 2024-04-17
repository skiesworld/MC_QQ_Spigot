package com.github.theword.mcqq.utils;

import com.github.theword.mcqq.returnBody.returnModle.MyBaseComponent;
import com.github.theword.mcqq.returnBody.returnModle.MyTextComponent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.github.theword.mcqq.utils.Tool.logger;

public class ParseJsonToEvent {

    public TextComponent parsePerMessageToTextComponent(MyBaseComponent myBaseComponent) {
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
                ClickEvent clickEvent = getClickEvent(myTextComponent);
                msgComponent.setClickEvent(clickEvent);
            }

            if (myTextComponent.getHoverEvent() != null) {
                HoverEvent hoverEvent;
                TextComponent textComponent = parseMessageToTextComponent(myTextComponent.getHoverEvent().getBaseComponentList());
                BaseComponent[] baseComponent = new BaseComponent[]{textComponent};
                HoverEvent.Action hoverAction = null;
                switch (myTextComponent.getHoverEvent().getAction()) {
                    case "show_text":
                        hoverAction = HoverEvent.Action.SHOW_TEXT;
                        break;
                    case "show_item":
                        hoverAction = HoverEvent.Action.SHOW_ITEM;
                        break;
                    case "show_entity":
                        hoverAction = HoverEvent.Action.SHOW_ENTITY;
                        break;
                    default:
                        break;
                }
                hoverEvent = new HoverEvent(hoverAction, baseComponent);
                msgComponent.setHoverEvent(hoverEvent);
            }
        }
        return msgComponent;
    }

    @NotNull
    private ClickEvent getClickEvent(MyTextComponent myTextComponent) {
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
        return new ClickEvent(tempAction, myTextComponent.getClickEvent().getValue());
    }

    /**
     * 将 MyBaseComponent 转换为 TextComponent
     *
     * @param myBaseComponentList 消息列表
     * @return TextComponent
     */
    public TextComponent parseMessageToTextComponent(List<? extends MyBaseComponent> myBaseComponentList) {
        TextComponent component = new TextComponent();
        StringBuilder msgLogText = new StringBuilder();

        for (MyBaseComponent myBaseComponent : myBaseComponentList) {
            TextComponent msgComponent = parsePerMessageToTextComponent(myBaseComponent);
            component.addExtra(msgComponent);

            if (!myBaseComponent.getText().equals("[MC_QQ] ")) {
                msgLogText.append(myBaseComponent.getText());
            }
        }
        logger.info(msgLogText.toString());
        return component;
    }

    /**
     * @param color 颜色
     * @return ChatColor 对象
     */
    ChatColor getColor(String color) {
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
