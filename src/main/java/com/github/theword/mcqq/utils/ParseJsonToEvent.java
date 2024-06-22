package com.github.theword.mcqq.utils;

import com.github.theword.mcqq.returnBody.returnModle.MyBaseComponent;
import com.github.theword.mcqq.returnBody.returnModle.MyHoverEntity;
import com.github.theword.mcqq.returnBody.returnModle.MyHoverItem;
import com.github.theword.mcqq.returnBody.returnModle.MyTextComponent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Entity;
import net.md_5.bungee.api.chat.hover.content.Item;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.github.theword.mcqq.utils.Tool.logger;

public class ParseJsonToEvent {

    public TextComponent parsePerMessageToTextComponent(MyBaseComponent myBaseComponent) {
        TextComponent msgComponent = new TextComponent();

        // 配置 BaseComponent 基本属性
        msgComponent.setText(myBaseComponent.getText());
        if (myBaseComponent.getColor() != null && !myBaseComponent.getColor().isEmpty())
            msgComponent.setColor(ChatColor.of(myBaseComponent.getColor()));
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
                HoverEvent hoverEvent = null;
                switch (myTextComponent.getHoverEvent().getAction()) {
                    case "show_text":
                        TextComponent textComponent = parseMessageToTextComponent(myTextComponent.getHoverEvent().getBaseComponentList());
                        BaseComponent[] baseComponent = new BaseComponent[]{textComponent};
                        hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(baseComponent));
                        break;
                    case "show_item":
                        MyHoverItem myHoverItem = myTextComponent.getHoverEvent().getItem();
                        ItemTag itemTag = ItemTag.ofNbt(myHoverItem.getTag());
                        Item item = new Item(String.valueOf(myHoverItem.getId()), myHoverItem.getCount(), itemTag);
                        hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_ITEM, item);
                        break;
                    case "show_entity":
                        MyHoverEntity myHoverEntity = myTextComponent.getHoverEvent().getEntity();
                        TextComponent nameComponent = parseMessageToTextComponent(myHoverEntity.getName());
                        Entity entity = new Entity(myHoverEntity.getType(), myHoverEntity.getId(), nameComponent);
                        hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_ENTITY, entity);
                        break;
                    default:
                        break;
                }
                msgComponent.setHoverEvent(hoverEvent);
            }
        }
        return msgComponent;
    }

    @NotNull
    private static ClickEvent getClickEvent(MyTextComponent myTextComponent) {
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
            case "copy_to_clipboard":
                tempAction = ClickEvent.Action.COPY_TO_CLIPBOARD;
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
        TextComponent component = parsePerMessageToTextComponent(Tool.getPrefixComponent());
        StringBuilder msgLogText = new StringBuilder();

        for (MyBaseComponent myBaseComponent : myBaseComponentList) {
            TextComponent msgComponent = parsePerMessageToTextComponent(myBaseComponent);
            component.addExtra(msgComponent);
            msgLogText.append(myBaseComponent.getText());
        }
        logger.info(msgLogText.toString());
        return component;
    }

    public String parseMyBaseCommentToStringWithStyle(List<? extends MyBaseComponent> myBaseComponentList) {
        StringBuilder message = new StringBuilder();

        for (MyBaseComponent myBaseComponent : myBaseComponentList) {
            String tempMessageSeg = "";
            if (myBaseComponent.isBold()) tempMessageSeg += ChatColor.BOLD;
            if (myBaseComponent.isItalic()) tempMessageSeg += ChatColor.ITALIC;
            if (myBaseComponent.isUnderlined()) tempMessageSeg += ChatColor.UNDERLINE;
            if (myBaseComponent.isStrikethrough()) tempMessageSeg += ChatColor.STRIKETHROUGH;
            if (myBaseComponent.isObfuscated()) tempMessageSeg += ChatColor.MAGIC;
            if (myBaseComponent.getColor() != null && !myBaseComponent.getColor().isEmpty())
                tempMessageSeg += ChatColor.of(myBaseComponent.getColor().toUpperCase());
            tempMessageSeg += myBaseComponent.getText();
            message.append(tempMessageSeg);
        }
        return message.toString();
    }
}
