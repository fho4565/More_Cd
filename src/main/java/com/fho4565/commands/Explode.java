package com.fho4565.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.world.level.Explosion;


public class Explode {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("explode").requires(s -> s.hasPermission(2))
                        .then(Commands.argument("pos", BlockPosArgument.blockPos())
                                .then(Commands.argument("strength", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            context.getSource().getLevel().explode(
                                                    null,
                                                    BlockPosArgument.getLoadedBlockPos(context, "pos").getX(),
                                                    BlockPosArgument.getLoadedBlockPos(context, "pos").getY(),
                                                    BlockPosArgument.getLoadedBlockPos(context, "pos").getZ(),
                                                    IntegerArgumentType.getInteger(context, "strength"),
                                                    Explosion.BlockInteraction.BREAK);
                                            return 1;
                                        })
                                        .then(Commands.literal("destroy").executes(context -> {
                                            context.getSource().getLevel().explode(
                                                    null,
                                                    BlockPosArgument.getLoadedBlockPos(context, "pos").getX(),
                                                    BlockPosArgument.getLoadedBlockPos(context, "pos").getY(),
                                                    BlockPosArgument.getLoadedBlockPos(context, "pos").getZ(),
                                                    IntegerArgumentType.getInteger(context, "strength"),
                                                    Explosion.BlockInteraction.DESTROY);
                                            return 1;
                                        }))
                                        .then(Commands.literal("break").executes(context -> {
                                            context.getSource().getLevel().explode(
                                                    null,
                                                    BlockPosArgument.getLoadedBlockPos(context, "pos").getX(),
                                                    BlockPosArgument.getLoadedBlockPos(context, "pos").getY(),
                                                    BlockPosArgument.getLoadedBlockPos(context, "pos").getZ(),
                                                    IntegerArgumentType.getInteger(context, "strength"),
                                                    Explosion.BlockInteraction.BREAK);
                                            return 1;
                                        }))
                                        .then(Commands.literal("none").executes(context -> {
                                            context.getSource().getLevel().explode(
                                                    null,
                                                    BlockPosArgument.getLoadedBlockPos(context, "pos").getX(),
                                                    BlockPosArgument.getLoadedBlockPos(context, "pos").getY(),
                                                    BlockPosArgument.getLoadedBlockPos(context, "pos").getZ(),
                                                    IntegerArgumentType.getInteger(context, "strength"),
                                                    Explosion.BlockInteraction.NONE);
                                            return 1;
                                        }))
                                )));
    }

}
