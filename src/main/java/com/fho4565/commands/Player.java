package com.fho4565.commands;

import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;


public class Player {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("player").requires(s -> s.hasPermission(2))
                        .then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.literal("set")
                                        .then(Commands.literal("canFly").then(Commands.argument("option", BoolArgumentType.bool()).executes(context -> {
                                            boolean option = BoolArgumentType.getBool(context, "option");
                                            MutableComponent canFly = new TextComponent("玩家").append(EntityArgument.getPlayer(context, "player").getName()).append("现在可以飞行");
                                            MutableComponent cannotFly = new TextComponent("玩家").append(EntityArgument.getPlayer(context, "player").getName()).append("现在不可以飞行");
                                            if (context.getSource().getPlayerOrException().getAbilities().mayfly != option) {
                                                context.getSource().getPlayerOrException().getAbilities().mayfly = option;
                                                context.getSource().getPlayerOrException().onUpdateAbilities();
                                                if (option) {
                                                    context.getSource().sendSuccess(canFly, false);
                                                } else {
                                                    context.getSource().sendSuccess(cannotFly, false);
                                                }
                                                return 1;
                                            } else if (context.getSource().getPlayerOrException().getAbilities().mayfly == option) {
                                                MutableComponent alreadyCanFly = new TextComponent("玩家").append(EntityArgument.getPlayer(context, "player").getName()).append("已经可以飞行");
                                                MutableComponent alreadyCannotFly = new TextComponent("玩家").append(EntityArgument.getPlayer(context, "player").getName()).append("已经不可以飞行");
                                                if (option) {
                                                    context.getSource().sendFailure(alreadyCanFly);
                                                } else {
                                                    context.getSource().sendFailure(alreadyCannotFly);
                                                }
                                                return 0;
                                            }else{
                                                return 0;
                                            }
                                        })))
                                        .then(Commands.literal("canPlaceBlock").then(Commands.argument("option", BoolArgumentType.bool()).executes(context -> {
                                            boolean option = BoolArgumentType.getBool(context, "option");
                                            MutableComponent canBuild = new TextComponent("玩家").append(EntityArgument.getPlayer(context, "player").getName()).append("现在可以建造");
                                            MutableComponent cannotBuild = new TextComponent("玩家").append(EntityArgument.getPlayer(context, "player").getName()).append("现在不可以建造");
                                            if (context.getSource().getPlayerOrException().getAbilities().mayBuild != option) {
                                                context.getSource().getPlayerOrException().getAbilities().mayBuild = option;
                                                context.getSource().getPlayerOrException().onUpdateAbilities();
                                                if (option) {
                                                    context.getSource().sendSuccess(canBuild, false);
                                                } else {
                                                    context.getSource().sendSuccess(cannotBuild, false);
                                                }
                                                return 1;
                                            } else if (context.getSource().getPlayerOrException().getAbilities().mayBuild == option) {
                                                MutableComponent alreadyCanBuild = new TextComponent("玩家").append(EntityArgument.getPlayer(context, "player").getName()).append("已经可以建造");
                                                MutableComponent alreadyCannotBuild = new TextComponent("玩家").append(EntityArgument.getPlayer(context, "player").getName()).append("已经不可以建造");
                                                if (option) {
                                                    context.getSource().sendFailure(alreadyCanBuild);
                                                } else {
                                                    context.getSource().sendFailure(alreadyCannotBuild);
                                                }
                                                return 0;
                                            }else{
                                                return 0;
                                            }
                                        }))))
                                .then(Commands.literal("get")
                                        .then(Commands.literal("canFly").executes(context -> {
                                            MutableComponent canFly = new TextComponent("玩家").append(EntityArgument.getPlayer(context, "player").getName()).append("现在可以飞行");
                                            MutableComponent cannotFly = new TextComponent("玩家").append(EntityArgument.getPlayer(context, "player").getName()).append("现在不可以飞行");
                                            if (context.getSource().getPlayerOrException().getAbilities().mayfly) {
                                                context.getSource().sendSuccess(canFly, false);
                                                return 1;
                                            } else {
                                                context.getSource().sendSuccess(cannotFly, false);
                                                return 0;
                                            }
                                        }))
                                        .then(Commands.literal("canPlaceBlock").executes(context -> {
                                    MutableComponent canBuild = new TextComponent("玩家").append(EntityArgument.getPlayer(context, "player").getName()).append("现在可以建造");
                                    MutableComponent cannotBuild = new TextComponent("玩家").append(EntityArgument.getPlayer(context, "player").getName()).append("现在不可以建造");
                                    if (context.getSource().getPlayerOrException().getAbilities().mayBuild) {
                                        context.getSource().sendSuccess(canBuild, false);
                                        return 1;
                                    } else {
                                        context.getSource().sendSuccess(cannotBuild, false);
                                        return 0;
                                    }
                                }))))
        );
    }

}
