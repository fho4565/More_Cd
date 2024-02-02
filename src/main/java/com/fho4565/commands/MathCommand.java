package com.fho4565.commands;

import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.nbt.DoubleTag;

import java.util.function.BiFunction;

public class MathCommand {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("math").requires(s -> s.hasPermission(2))
                        .then(buildMathCommand("log", MathCommand::log10))
                        .then(buildMathCommand("loge", MathCommand::loge))
                        .then(buildMathCommand("tanh", MathCommand::tanh))
                        .then(buildMathCommand("cosh", MathCommand::cosh))
                        .then(buildMathCommand("sinh", MathCommand::sinh))
                        .then(buildMathCommand("atan", MathCommand::atan))
                        .then(buildMathCommand("asin", MathCommand::asin))
                        .then(buildMathCommand("acos", MathCommand::acos))
                        .then(buildMathCommand("tan", MathCommand::tan))
                        .then(buildMathCommand("cos", MathCommand::cos))
                        .then(buildMathCommand("sin", MathCommand::sin))
                        .then(buildMathCommand("max", MathCommand::max))
                        .then(buildMathCommand("min", MathCommand::min))
                        .then(buildMathCommand("abs", MathCommand::abs))
                        .then(buildMathCommand("pow", MathCommand::pow))
                        .then(buildMathCommand("sqrt", MathCommand::sqrt)
                        )
        );
    }

    private static ArgumentBuilder<CommandSourceStack, ?> buildMathCommand(String commandName, BiFunction<Double, Double, Double> executor) {
        return Commands.literal(commandName)
                .then(Commands.literal("score").then(buildCommand(executor)))
                .then(Commands.literal("storage").then(buildCommand(executor)))
                .then(Commands.literal("num").then(buildCommand(executor)));
    }

    private static ArgumentBuilder<CommandSourceStack, ?> buildCommand(BiFunction<Double, Double, Double> executor) {
        return Commands.argument("base", DoubleArgumentType.doubleArg())
                .executes(context -> execute(executor, context))
                .then(Commands.argument("target", ResourceLocationArgument.id())
                        .then(Commands.argument("path", NbtPathArgument.nbtPath())
                                .executes(context -> execute(executor, context))
                                .then(Commands.argument("target", ResourceLocationArgument.id())
                                        .then(Commands.argument("path", NbtPathArgument.nbtPath())
                                                .executes(context -> execute(executor, context))
                                        )
                                )
                        )
                );
    }

    private static int execute(BiFunction<Double, Double, Double> executor, CommandContext<CommandSourceStack> context) {
        try {
            double base = DoubleArgumentType.getDouble(context, "base");
            double value = Double.parseDouble(Utils.getData(context, "sourceTarget", "sourcePath").getAsString());
            double result = executor.apply(base, value);
            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.math.success", String.valueOf(result));
            if (context.getArgument("targetPath", String.class) != null) {
                Utils.setData(context, "targetTarget", "targetPath", DoubleTag.valueOf(result));
            }
            return (int) result;
        } catch (CommandSyntaxException e) {
            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.math.error", true, e.getMessage());
            return 0;
        }
    }

    private static double acos(double base, double value) {
        return Math.acos(value);
    }

    private static double tan(double base, double value) {
        return Math.tan(value);
    }

    private static double sin(double base, double value) {
        return Math.sin(value);
    }

    private static double cos(double base, double value) {
        return Math.cos(value);
    }

    private static double max(double base, double value) {
        return Math.max(base, value);
    }

    private static double min(double base, double value) {
        return Math.min(base, value);
    }

    private static double abs(double base, double value) {
        return Math.abs(value);
    }

    private static double pow(double base, double value) {
        return Math.pow(base, value);
    }

    private static double asin(double base, double value) {
        return Math.asin(value);
    }

    private static double sqrt(double base, double value) {
        return Math.sqrt(value);
    }

    private static double atan(double base, double value) {
        return Math.atan(value);
    }

    private static double sinh(double base, double value) {
        return Math.sinh(value);
    }

    private static double cosh(double base, double value) {
        return Math.cosh(value);
    }

    private static double tanh(double base, double value) {
        return Math.tanh(value);
    }

    private static double loge(double base, double value) {
        return Math.log(value);
    }

    private static double log10(double base, double value) {
        return Math.log10(value);
    }
}