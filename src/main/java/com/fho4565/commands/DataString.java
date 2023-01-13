package com.fho4565.commands;

import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;


public class DataString {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("string").requires(s -> s.hasPermission(2))
                        .then(Commands.argument("sourceTarget", ResourceLocationArgument.id())
                                .then(Commands.argument("sourcePath",NbtPathArgument.nbtPath())
                                        .then(Commands.literal(""))))
        );
    }

}
