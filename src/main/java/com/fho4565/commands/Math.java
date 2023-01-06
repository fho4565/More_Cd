package com.fho4565.commands;

import com.fho4565.main.Utils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ObjectiveArgument;
import net.minecraft.commands.arguments.ScoreHolderArgument;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class Math {
    @SubscribeEvent
    public static void onServerStaring(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(
                Commands.literal("math").requires(s -> s.hasPermission(2))
                        .then(Commands.literal("pow")
                                .then(Commands.argument("baseNum", DoubleArgumentType.doubleArg())
                                        .then(Commands.argument("exponent", DoubleArgumentType.doubleArg()).executes(context -> {
                                            double result = java.lang.Math.pow(DoubleArgumentType.getDouble(context, "baseNum"), DoubleArgumentType.getDouble(context, "exponent"));
                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                            return (int) result;
                                        })))
                                .then(Commands.argument("baseHolder", ScoreHolderArgument.scoreHolder())
                                        .then(Commands.argument("baseScore", ObjectiveArgument.objective())
                                                .then(Commands.argument("exponentHolder",ScoreHolderArgument.scoreHolder())
                                                        .then(Commands.argument("exponentScore", ObjectiveArgument.objective()).executes(context -> {
                                                            double result = java.lang.Math.pow(Utils.getScore(context.getSource(),ScoreHolderArgument.getName(context,"baseHolder"),ObjectiveArgument.getObjective(context,"baseScore")),
                                                                    Utils.getScore(context.getSource(),ScoreHolderArgument.getName(context,"exponentHolder"),ObjectiveArgument.getObjective(context,"exponentScore")));
                                                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                                                            return (int) result;
                                                        }))))))
                        .then(Commands.literal("sqrt").then(Commands.argument("a", DoubleArgumentType.doubleArg()).executes(context -> {
                            double result = java.lang.Math.sqrt(DoubleArgumentType.getDouble(context, "a"));
                            context.getSource().sendSuccess(new TextComponent("结果为" + result), false);
                            return (int) result;
                        })))
        );
    }
}
