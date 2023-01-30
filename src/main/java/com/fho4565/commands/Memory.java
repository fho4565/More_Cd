package com.fho4565.commands;

import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class Memory {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("memory").requires(s -> s.hasPermission(2))
                        .then(Commands.literal("RAM")
                                .then(Commands.literal("read")
                                        .then(Commands.argument("namespace", StringArgumentType.string())
                                                .executes(Memory::ram)))
                                .then(Commands.literal("write"))


                        )
                        .then(Commands.literal("DISK")
                                .then(Commands.literal("read")
                                        .then(Commands.argument("namespace", StringArgumentType.string())
                                                .suggests(Utils.getCommandSuggestion(new String[]{"a","b"}))
                                                .executes(Memory::disk)))
                                .then(Commands.literal("write"))

                        )
        );
    }

    private static int ram(CommandContext<CommandSourceStack> context) {
        Utils.sendCdFeedback(context,"RAM-"+StringArgumentType.getString(context,"namespace"));
        return 1;
    }

    private static int disk(CommandContext<CommandSourceStack> context) {
        Utils.sendCdFeedback(context,"DISK-"+StringArgumentType.getString(context,"namespace"));
        return 1;
    }
}