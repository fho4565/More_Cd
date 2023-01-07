package com.fho4565.commands;

import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ObjectiveArgument;
import net.minecraft.commands.arguments.ScoreHolderArgument;
import net.minecraft.network.chat.TextComponent;


public class Math {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("math").requires(s -> s.hasPermission(2))
                        .then(Commands.literal("log")
                                .then(Commands.argument("baseHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("baseScore", ObjectiveArgument.objective()).executes(context -> {
                                            double result = java.lang.Math.log10(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "baseHolder"), ObjectiveArgument.getObjective(context, "baseScore")));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        })))
                                .then(Commands.literal("num")
                                        .then(Commands.argument("baseNum", DoubleArgumentType.doubleArg()).executes(context -> {
                                            double result = java.lang.Math.log10(DoubleArgumentType.getDouble(context, "baseNum"));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        }))))
                        .then(Commands.literal("loge")
                                .then(Commands.argument("baseHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("baseScore", ObjectiveArgument.objective()).executes(context -> {
                                            double result = java.lang.Math.log(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "baseHolder"), ObjectiveArgument.getObjective(context, "baseScore")));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        })))
                                .then(Commands.literal("num")
                                        .then(Commands.argument("baseNum", DoubleArgumentType.doubleArg()).executes(context -> {
                                            double result = java.lang.Math.log(DoubleArgumentType.getDouble(context, "baseNum"));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        }))))
                        .then(Commands.literal("tanh")
                                .then(Commands.argument("baseHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("baseScore", ObjectiveArgument.objective()).executes(context -> {
                                            double result = java.lang.Math.tanh(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "baseHolder"), ObjectiveArgument.getObjective(context, "baseScore")));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        })))
                                .then(Commands.literal("num")
                                        .then(Commands.argument("baseNum", DoubleArgumentType.doubleArg()).executes(context -> {
                                            double result = java.lang.Math.tanh(DoubleArgumentType.getDouble(context, "baseNum"));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        }))))
                        .then(Commands.literal("cosh")
                                .then(Commands.argument("baseHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("baseScore", ObjectiveArgument.objective()).executes(context -> {
                                            double result = java.lang.Math.cosh(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "baseHolder"), ObjectiveArgument.getObjective(context, "baseScore")));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        })))
                                .then(Commands.literal("num")
                                        .then(Commands.argument("baseNum", DoubleArgumentType.doubleArg()).executes(context -> {
                                            double result = java.lang.Math.cosh(DoubleArgumentType.getDouble(context, "baseNum"));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        }))))
                        .then(Commands.literal("sinh")
                                .then(Commands.argument("baseHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("baseScore", ObjectiveArgument.objective()).executes(context -> {
                                            double result = java.lang.Math.sinh(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "baseHolder"), ObjectiveArgument.getObjective(context, "baseScore")));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        })))
                                .then(Commands.literal("num")
                                        .then(Commands.argument("baseNum", DoubleArgumentType.doubleArg()).executes(context -> {
                                            double result = java.lang.Math.sinh(DoubleArgumentType.getDouble(context, "baseNum"));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        }))))

                        .then(Commands.literal("atan")
                                .then(Commands.argument("baseHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("baseScore", ObjectiveArgument.objective()).executes(context -> {
                                            double result = java.lang.Math.atan(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "baseHolder"), ObjectiveArgument.getObjective(context, "baseScore")));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        })))
                                .then(Commands.literal("num")
                                        .then(Commands.argument("baseNum", DoubleArgumentType.doubleArg()).executes(context -> {
                                            double result = java.lang.Math.atan(DoubleArgumentType.getDouble(context, "baseNum"));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        }))))
                        .then(Commands.literal("acos")
                                .then(Commands.argument("baseHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("baseScore", ObjectiveArgument.objective()).executes(context -> {
                                            double result = java.lang.Math.acos(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "baseHolder"), ObjectiveArgument.getObjective(context, "baseScore")));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        })))
                                .then(Commands.literal("num")
                                        .then(Commands.argument("baseNum", DoubleArgumentType.doubleArg()).executes(context -> {
                                            double result = java.lang.Math.acos(DoubleArgumentType.getDouble(context, "baseNum"));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        }))))
                        .then(Commands.literal("asin")
                                .then(Commands.argument("baseHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("baseScore", ObjectiveArgument.objective()).executes(context -> {
                                            double result = java.lang.Math.asin(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "baseHolder"), ObjectiveArgument.getObjective(context, "baseScore")));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        })))
                                .then(Commands.argument("baseNum", DoubleArgumentType.doubleArg()).executes(context -> {
                                    double result = java.lang.Math.asin(DoubleArgumentType.getDouble(context, "baseNum"));
                                    context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                    return (int) result;
                                })))
                        .then(Commands.literal("tan")
                                .then(Commands.argument("baseHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("baseScore", ObjectiveArgument.objective()).executes(context -> {
                                            double result = java.lang.Math.tan(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "baseHolder"), ObjectiveArgument.getObjective(context, "baseScore")));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        })))
                                .then(Commands.literal("num")
                                        .then(Commands.argument("baseNum", DoubleArgumentType.doubleArg()).executes(context -> {
                                            double result = java.lang.Math.tan(DoubleArgumentType.getDouble(context, "baseNum"));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        }))))
                        .then(Commands.literal("cos")
                                .then(Commands.argument("baseHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("baseScore", ObjectiveArgument.objective()).executes(context -> {
                                            double result = java.lang.Math.cos(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "baseHolder"), ObjectiveArgument.getObjective(context, "baseScore")));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        })))
                                .then(Commands.literal("num")
                                        .then(Commands.argument("baseNum", DoubleArgumentType.doubleArg()).executes(context -> {
                                            double result = java.lang.Math.cos(DoubleArgumentType.getDouble(context, "baseNum"));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        }))))
                        .then(Commands.literal("sin")
                                .then(Commands.argument("baseHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("baseScore", ObjectiveArgument.objective()).executes(context -> {
                                            double result = java.lang.Math.sin(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "baseHolder"), ObjectiveArgument.getObjective(context, "baseScore")));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        })))
                                .then(Commands.argument("baseNum", DoubleArgumentType.doubleArg()).executes(context -> {
                                    double result = java.lang.Math.sin(DoubleArgumentType.getDouble(context, "baseNum"));
                                    context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                    return (int) result;
                                })))
                        .then(Commands.literal("max")
                                .then(Commands.literal("num")
                                        .then(Commands.argument("a", DoubleArgumentType.doubleArg())
                                                .then(Commands.argument("b", DoubleArgumentType.doubleArg()).executes(context -> {
                                                    double result = java.lang.Math.max(DoubleArgumentType.getDouble(context, "a"), DoubleArgumentType.getDouble(context, "b"));
                                                    context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                                    return (int) result;
                                                }))))
                                .then(Commands.argument("aHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("a", ObjectiveArgument.objective())
                                                .then(Commands.argument("bHolder", ScoreHolderArgument.scoreHolder())
                                                        .then(Commands.argument("b", ObjectiveArgument.objective()).executes(context -> {
                                                            double result = java.lang.Math.max(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "aHolder"), ObjectiveArgument.getObjective(context, "a")),
                                                                    Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "bHolder"), ObjectiveArgument.getObjective(context, "b")));
                                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                                            return (int) result;
                                                        }))))))
                        .then(Commands.literal("min")
                                .then(Commands.literal("num")
                                        .then(Commands.argument("a", DoubleArgumentType.doubleArg())
                                                .then(Commands.argument("b", DoubleArgumentType.doubleArg()).executes(context -> {
                                                    double result = java.lang.Math.min(DoubleArgumentType.getDouble(context, "a"), DoubleArgumentType.getDouble(context, "b"));
                                                    context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                                    return (int) result;
                                                }))))
                                .then(Commands.argument("aHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("a", ObjectiveArgument.objective())
                                                .then(Commands.argument("bHolder", ScoreHolderArgument.scoreHolder())
                                                        .then(Commands.argument("b", ObjectiveArgument.objective()).executes(context -> {
                                                            double result = java.lang.Math.min(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "aHolder"), ObjectiveArgument.getObjective(context, "a")),
                                                                    Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "bHolder"), ObjectiveArgument.getObjective(context, "b")));
                                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                                            return (int) result;
                                                        }))))))
                        .then(Commands.literal("abs")
                                .then(Commands.argument("baseHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("baseScore", ObjectiveArgument.objective()).executes(context -> {
                                            double result = java.lang.Math.abs(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "baseHolder"), ObjectiveArgument.getObjective(context, "baseScore")));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        })))
                                .then(Commands.literal("num")
                                        .then(Commands.argument("baseNum", DoubleArgumentType.doubleArg()).executes(context -> {
                                            double result = java.lang.Math.abs(DoubleArgumentType.getDouble(context, "baseNum"));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        }))))
                        .then(Commands.literal("pow")
                                .then(Commands.literal("num")
                                        .then(Commands.argument("baseNum", DoubleArgumentType.doubleArg())
                                                .then(Commands.argument("exponent", DoubleArgumentType.doubleArg()).executes(context -> {
                                                    double result = java.lang.Math.pow(DoubleArgumentType.getDouble(context, "baseNum"), DoubleArgumentType.getDouble(context, "exponent"));
                                                    context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                                    return (int) result;
                                                }))))
                                .then(Commands.argument("baseHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("baseScore", ObjectiveArgument.objective())
                                                .then(Commands.argument("exponentHolder", ScoreHolderArgument.scoreHolder())
                                                        .then(Commands.argument("exponentScore", ObjectiveArgument.objective()).executes(context -> {
                                                            double result = java.lang.Math.pow(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "baseHolder"), ObjectiveArgument.getObjective(context, "baseScore")),
                                                                    Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "exponentHolder"), ObjectiveArgument.getObjective(context, "exponentScore")));
                                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                                            return (int) result;
                                                        }))))))
                        .then(Commands.literal("sqrt")
                                .then(Commands.argument("baseHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("baseScore", ObjectiveArgument.objective()).executes(context -> {
                                            double result = java.lang.Math.sqrt(Utils.getScore(context.getSource(), ScoreHolderArgument.getName(context, "baseHolder"), ObjectiveArgument.getObjective(context, "baseScore")));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        })))
                                .then(Commands.literal("num")
                                        .then(Commands.argument("baseNum", DoubleArgumentType.doubleArg()).executes(context -> {
                                            double result = java.lang.Math.sqrt(DoubleArgumentType.getDouble(context, "baseNum"));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        })))
                        )
        );
    }
}
