package com.fho4565.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.atomic.AtomicInteger;


@Mod.EventBusSubscriber
public class Random {
    @SubscribeEvent
    public static void onServerStaring(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        java.util.Random r = new java.util.Random();
        AtomicInteger result = new AtomicInteger();
        dispatcher.register(
                Commands.literal("random").executes(context -> {
                    result.set(r.nextInt());
                    context.getSource().sendSuccess(new TextComponent("已生成随机数："+result.get()), false);
                    return result.get();
                }).then(Commands.argument("minValue",IntegerArgumentType.integer()).executes(context -> {
                    result.set(r.nextInt(IntegerArgumentType.getInteger(context,"minValue"),Integer.MAX_VALUE));
                    context.getSource().sendSuccess(new TextComponent("已生成随机数："+result.get()), false);
                    return result.get();
                }).then(Commands.argument("maxValue",IntegerArgumentType.integer()).executes(context ->{
                    result.set(r.nextInt(IntegerArgumentType.getInteger(context,"minValue"),IntegerArgumentType.getInteger(context,"maxValue")));
                    context.getSource().sendSuccess(new TextComponent("已生成随机数："+result.get()), false);
                    return result.get();
                })))
        );
    }

}
