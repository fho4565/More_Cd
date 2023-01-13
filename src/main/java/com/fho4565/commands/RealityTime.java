package com.fho4565.commands;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;

import java.util.Calendar;


public class RealityTime {
    public static void register()  {
        CommandRegister.dispatcher.register(
                Commands.literal("realityTime")
                        .then(Commands.literal("second").executes(context -> {
                            context.getSource().sendSuccess(new TextComponent("当前现实秒为："+ Calendar.getInstance().get(Calendar.SECOND)),false);
                            return Calendar.getInstance().get(Calendar.SECOND);
                        }))
                        .then(Commands.literal("minute").executes(context -> {
                            context.getSource().sendSuccess(new TextComponent("当前现实分钟为："+ Calendar.getInstance().get(Calendar.MINUTE)),false);
                            return Calendar.getInstance().get(Calendar.SECOND);
                        }))
                        .then(Commands.literal("hour").executes(context -> {
                            context.getSource().sendSuccess(new TextComponent("当前现实小时为："+ Calendar.getInstance().get(Calendar.HOUR_OF_DAY)),false);
                            return Calendar.getInstance().get(Calendar.SECOND);
                        }))
                        .then(Commands.literal("day").executes(context -> {
                            context.getSource().sendSuccess(new TextComponent("当前现实日为："+ Calendar.getInstance().get(Calendar.DAY_OF_MONTH)),false);
                            return Calendar.getInstance().get(Calendar.SECOND);
                        }))
                        .then(Commands.literal("month").executes(context -> {
                            context.getSource().sendSuccess(new TextComponent("当前现实月为："+ Calendar.getInstance().get(Calendar.MONTH)+1),false);
                            return Calendar.getInstance().get(Calendar.SECOND);
                        }))
                        .then(Commands.literal("year").executes(context -> {
                            context.getSource().sendSuccess(new TextComponent("当前现实年为："+ Calendar.getInstance().get(Calendar.YEAR)),false);
                            return Calendar.getInstance().get(Calendar.SECOND);
                        }))
        );
    }

}
