package com.fho4565.commands;

import com.fho4565.define.RenderableArgumentType;
import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;

public class Render {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("render").requires(s -> s.hasPermission(2))
                        .then(Commands.literal("add")
                                .then(Commands.argument("name", RenderableArgumentType.renderable())
                                        .executes(context -> {
                                            Utils.sendCdFeedback(context,RenderableArgumentType.getRenderable(context,"name"));
                                            return 1;
                                        })
                                ))
                        .then(Commands.literal("remove"))
                        .then(Commands.literal("list"))
                        .then(Commands.literal("set"))

        );
    }
}
