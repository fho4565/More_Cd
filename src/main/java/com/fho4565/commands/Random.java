package com.fho4565.commands;

import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import java.util.concurrent.ThreadLocalRandom;

public class Random {
    public static void register() {
        LiteralArgumentBuilder<CommandSourceStack> randomCommand = Commands.literal("random")
                .executes(context -> executeRandom(context, ThreadLocalRandom.current().nextInt()));

        RequiredArgumentBuilder<CommandSourceStack, Integer> minValueArg = Commands.argument("minValue", IntegerArgumentType.integer())
                .executes(context -> executeRandom(context, ThreadLocalRandom.current().nextInt(IntegerArgumentType.getInteger(context, "minValue"), Integer.MAX_VALUE)));

        RequiredArgumentBuilder<CommandSourceStack, Integer> maxValueArg = Commands.argument("maxValue", IntegerArgumentType.integer())
                .executes(context -> executeRandom(context, ThreadLocalRandom.current().nextInt(IntegerArgumentType.getInteger(context, "minValue"), IntegerArgumentType.getInteger(context, "maxValue"))));

        minValueArg.then(maxValueArg);
        randomCommand.then(minValueArg);

        CommandRegister.dispatcher.register(randomCommand);
    }

    private static int executeRandom(CommandContext<CommandSourceStack> context, int value) {
        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.random.success", String.valueOf(value));
        return value;
    }
}