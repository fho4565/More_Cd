package com.fho4565.commands;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;

import java.util.Calendar;


public class RealityTime {
    public static void register()  {
        Calendar c = Calendar.getInstance();
        CommandRegister.dispatcher.register(
                Commands.literal("realityTime")
                        .then(Commands.literal("second").executes(context -> {
                            context.getSource().sendSuccess(new TextComponent("当前现实秒为："+ c.get(Calendar.SECOND)),false);
                            return c.get(Calendar.SECOND);
                        }))
                        .then(Commands.literal("minute").executes(context -> {
                            context.getSource().sendSuccess(new TextComponent("当前现实分钟为："+ c.get(Calendar.MINUTE)),false);
                            return c.get(Calendar.SECOND);
                        }))
                        .then(Commands.literal("hour").executes(context -> {
                            context.getSource().sendSuccess(new TextComponent("当前现实小时为："+ c.get(Calendar.HOUR_OF_DAY)),false);
                            return c.get(Calendar.SECOND);
                        }))
                        .then(Commands.literal("day").executes(context -> {
                            context.getSource().sendSuccess(new TextComponent("当前现实日为："+ c.get(Calendar.DAY_OF_MONTH)),false);
                            return c.get(Calendar.SECOND);
                        }))
                        .then(Commands.literal("month").executes(context -> {
                            context.getSource().sendSuccess(new TextComponent("当前现实月为："+ c.get(Calendar.MONTH)+1),false);
                            return c.get(Calendar.SECOND);
                        }))
                        .then(Commands.literal("year").executes(context -> {
                            context.getSource().sendSuccess(new TextComponent("当前现实年为："+ c.get(Calendar.YEAR)),false);
                            return c.get(Calendar.SECOND);
                        }))
        );
    }

}
