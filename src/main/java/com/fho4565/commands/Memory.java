package com.fho4565.commands;

import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
//TODO 没有完成的命令：可以读取存储任意数据
public class Memory {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("memory").requires(s -> s.hasPermission(2))
                        .then(Commands.literal("RAM")
                                .then(Commands.literal("read")
                                        .then(Commands.argument("namespace", StringArgumentType.string())
                                                .suggests(Utils.createCommandSuggestion(new String[]{"a","b"}))
                                                .executes(Memory::ramRead)))
                                .then(Commands.literal("write"))


                        )
                        .then(Commands.literal("DISK")
                                .then(Commands.literal("read")
                                        .then(Commands.argument("namespace", StringArgumentType.string())
                                                .suggests(Utils.createCommandSuggestion(new String[]{"a","b"}))
                                                .executes(Memory::diskRead)))
                                .then(Commands.literal("write"))

                        )
        );
    }

    private static int ramRead(CommandContext<CommandSourceStack> context) {
        Utils.sendCdFeedback(context,"RAM-"+StringArgumentType.getString(context,"namespace"));
        return 1;
    }

    private static int diskRead(CommandContext<CommandSourceStack> context) {
        Utils.sendCdFeedback(context,"DISK-"+StringArgumentType.getString(context,"namespace"));
        return 1;
    }
}