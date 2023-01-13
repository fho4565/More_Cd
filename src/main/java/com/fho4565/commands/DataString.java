package com.fho4565.commands;

import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;


public class DataString {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("string").requires(s -> s.hasPermission(2))
                        .then(Commands.argument("sourceTarget", ResourceLocationArgument.id())
                                .then(Commands.argument("sourcePath",NbtPathArgument.nbtPath())
                                        .then(Commands.literal("splitToArray").then(Commands.argument("string", StringArgumentType.string()).executes(context -> {
                                                    ListTag tags = new ListTag();
                                                    char[] chars = StringArgumentType.getString(context, "string").toCharArray();
                                                    for (Character c:chars) {
                                                        tags.add(StringTag.valueOf(String.valueOf(c)));
                                                    }
                                                    Utils.setData(context,"sourceTarget","sourcePath", tags);
                                                    return 0;
                                                })))))
        );
    }

}
