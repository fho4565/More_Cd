package com.fho4565.commands;

import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;


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
                                                    Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.canFly.enabled",EntityArgument.getPlayer(context, "player").getName().getString());
                                                } else {
                                                    Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.canFly.disabled",EntityArgument.getPlayer(context, "player").getName().getString());
                                                }
                                                return 1;
                                            } else if (context.getSource().getPlayerOrException().getAbilities().mayfly == option) {
                                                if (option) {
                                                    Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.canFly.enabledA",EntityArgument.getPlayer(context, "player").getName().getString());
                                                } else {
                                                    Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.canFly.disabledA",EntityArgument.getPlayer(context, "player").getName().getString());
                                                }
                                                return 0;
                                            }else{
                                                return 0;
                                            }
                                        })))
                                        .then(Commands.literal("canPlaceBlock").then(Commands.argument("option", BoolArgumentType.bool()).executes(context -> {
                                            boolean option = BoolArgumentType.getBool(context, "option");
                                            if (context.getSource().getPlayerOrException().getAbilities().mayBuild != option) {
                                                context.getSource().getPlayerOrException().getAbilities().mayBuild = option;
                                                context.getSource().getPlayerOrException().onUpdateAbilities();
                                                if (option) {
                                                    Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.canPlaceBlock.enabled",EntityArgument.getPlayer(context, "player").getName().getString());
                                                } else {
                                                    Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.canPlaceBlock.disabled",EntityArgument.getPlayer(context, "player").getName().getString());
                                                }
                                                return 1;
                                            } else if (context.getSource().getPlayerOrException().getAbilities().mayBuild == option) {
                                                if (option) {
                                                    Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.canPlaceBlock.enabledA",EntityArgument.getPlayer(context, "player").getName().getString());
                                                } else {
                                                    Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.canPlaceBlock.disabledA",EntityArgument.getPlayer(context, "player").getName().getString());
                                                }
                                                return 0;
                                            }else{
                                                return 0;
                                            }
                                        }))))
                                .then(Commands.literal("get")
                                        .then(Commands.literal("canFly").executes(context -> {
                                            if (context.getSource().getPlayerOrException().getAbilities().mayfly) {
                                                Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.canFly.enabled",EntityArgument.getPlayer(context, "player").getName().getString());
                                                return 1;
                                            } else {
                                                Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.canFly.disabled",EntityArgument.getPlayer(context, "player").getName().getString());
                                                return 0;
                                            }
                                        }))
                                        .then(Commands.literal("canPlaceBlock").executes(context -> {
                                    if (context.getSource().getPlayerOrException().getAbilities().mayBuild) {
                                        Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.canPlaceBlock.enabled",EntityArgument.getPlayer(context, "player").getName().getString());
                                        return 1;
                                    } else {
                                        Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.canPlaceBlock.disabled",EntityArgument.getPlayer(context, "player").getName().getString());
                                        return 0;
                                    }
                                }))))
        );
    }

}
