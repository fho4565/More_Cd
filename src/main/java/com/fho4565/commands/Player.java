package com.fho4565.commands;

import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;


public class Player {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("player").requires(s -> s.hasPermission(2))
                        .then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.literal("set")
                                        .then(Commands.literal("canFly").then(Commands.argument("option", BoolArgumentType.bool()).executes(context -> {
                                            boolean option = BoolArgumentType.getBool(context, "option");
                                            if (context.getSource().getPlayerOrException().getAbilities().mayfly != option) {
                                                context.getSource().getPlayerOrException().getAbilities().mayfly = option;
                                                context.getSource().getPlayerOrException().onUpdateAbilities();
                                                if (option) {
                                                    Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.player.canFly.enabled", EntityArgument.getPlayer(context, "player").getName().getString());
                                                } else {
                                                    Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.player.canFly.disabled", EntityArgument.getPlayer(context, "player").getName().getString());
                                                }
                                                return 1;
                                            } else if (context.getSource().getPlayerOrException().getAbilities().mayfly == option) {
                                                if (option) {
                                                    Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.player.canFly.enabledA", EntityArgument.getPlayer(context, "player").getName().getString());
                                                } else {
                                                    Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.player.canFly.disabledA", EntityArgument.getPlayer(context, "player").getName().getString());
                                                }
                                                return 0;
                                            } else {
                                                return 0;
                                            }
                                        })))
                                        .then(Commands.literal("canPlaceBlock").then(Commands.argument("option", BoolArgumentType.bool()).executes(context -> {
                                            boolean option = BoolArgumentType.getBool(context, "option");
                                            if (context.getSource().getPlayerOrException().getAbilities().mayBuild != option) {
                                                context.getSource().getPlayerOrException().getAbilities().mayBuild = option;
                                                context.getSource().getPlayerOrException().onUpdateAbilities();
                                                if (option) {
                                                    Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.player.canPlaceBlock.enabled", EntityArgument.getPlayer(context, "player").getName().getString());
                                                } else {
                                                    Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.player.canPlaceBlock.disabled", EntityArgument.getPlayer(context, "player").getName().getString());
                                                }
                                                return 1;
                                            } else if (context.getSource().getPlayerOrException().getAbilities().mayBuild == option) {
                                                if (option) {
                                                    Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.player.canPlaceBlock.enabledA", EntityArgument.getPlayer(context, "player").getName().getString());
                                                } else {
                                                    Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.player.canPlaceBlock.disabledA", EntityArgument.getPlayer(context, "player").getName().getString());
                                                }
                                                return 0;
                                            } else {
                                                return 0;
                                            }
                                        }))))
                                .then(Commands.literal("get")
                                        .then(Commands.literal("canFly").executes(context -> {
                                            if (context.getSource().getPlayerOrException().getAbilities().mayfly) {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.player.canFly.enabled", EntityArgument.getPlayer(context, "player").getName().getString());
                                                return 1;
                                            } else {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.player.canFly.disabled", EntityArgument.getPlayer(context, "player").getName().getString());
                                                return 0;
                                            }
                                        }))
                                        .then(Commands.literal("canPlaceBlock").executes(context -> {
                                            if (context.getSource().getPlayerOrException().getAbilities().mayBuild) {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.player.canPlaceBlock.enabled", EntityArgument.getPlayer(context, "player").getName().getString());
                                                return 1;
                                            } else {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.player.canPlaceBlock.disabled", EntityArgument.getPlayer(context, "player").getName().getString());
                                                return 0;
                                            }
                                        })))
                                .then(Commands.literal("rightClick")
                                        .then(Commands.argument("pos", BlockPosArgument.blockPos()).executes(context -> {
                                            ServerLevel level = context.getSource().getLevel();
                                            ServerPlayer player = EntityArgument.getPlayer(context, "player");
                                            BlockPos pos = new BlockPos(BlockPosArgument.getLoadedBlockPos(context, "pos"));
                                            level.getBlockState(pos).use(level, player, InteractionHand.MAIN_HAND, new BlockHitResult(new Vec3(pos.getX(), pos.getY(), pos.getZ()), Direction.UP, pos, false));
                                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.player.rightClick.success", pos.toShortString(), player.getName().getString());
                                            return 1;
                                        })))
                                .then(Commands.literal("currentXp")
                                        .executes(context -> {
                                            ServerPlayer player = EntityArgument.getPlayer(context, "player");

                                            Utils.sendCdFeedback(context,player.totalExperience+"|||");
                                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.player.currentXp",
                                                    EntityArgument.getPlayer(context, "player").getName().getString(),
                                                    String.valueOf(player.experienceProgress));
                                            return (int) player.experienceProgress;
                                        }))

                        ));
    }

}
