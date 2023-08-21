package com.fho4565.commands;

import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;

import java.util.concurrent.atomic.AtomicInteger;


public class Random {
    public static void register() {
        java.util.Random r = new java.util.Random();
        AtomicInteger result = new AtomicInteger();
        CommandRegister.dispatcher.register(
                Commands.literal("random").executes(context -> {
                            result.set(r.nextInt());
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.random.success", String.valueOf(result.get()));
                            return result.get();
                        })
                        .then(Commands.argument("minValue", IntegerArgumentType.integer()).executes(context -> {
                                    result.set(r.nextInt(IntegerArgumentType.getInteger(context, "minValue"), Integer.MAX_VALUE));
                                    Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.random.success", String.valueOf(result.get()));
                                    return result.get();
                                })
                                .then(Commands.argument("maxValue", IntegerArgumentType.integer()).executes(context -> {
                                    result.set(r.nextInt(IntegerArgumentType.getInteger(context, "minValue"), IntegerArgumentType.getInteger(context, "maxValue")));
                                    Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.random.success", String.valueOf(result.get()));
                                    return result.get();
                                })))
        );
    }

}
