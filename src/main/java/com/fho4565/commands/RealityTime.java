package com.fho4565.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Calendar;


@Mod.EventBusSubscriber
public class RealityTime {
    @SubscribeEvent
    public static void onServerStaring(RegisterCommandsEvent event) {
        Calendar c = Calendar.getInstance();
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(
                Commands.literal("realityTime").requires(s -> s.hasPermission(2))
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
