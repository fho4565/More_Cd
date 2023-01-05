package com.fho4565.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class Player {
    @SubscribeEvent
    public static void onServerStaring(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(
                Commands.literal("player")
                        .then(Commands.argument("player",EntityArgument.player())
                                .then(Commands.literal("set").then(Commands.literal("build").then(Commands.argument("option", BoolArgumentType.bool()).executes(context -> {
                                    boolean option = BoolArgumentType.getBool(context, "option");
                                    MutableComponent canBuild = new TextComponent("玩家").append(EntityArgument.getPlayer(context,"player").getName()).append("现在可以建造");
                                    MutableComponent cannotBuild = new TextComponent("玩家").append(EntityArgument.getPlayer(context,"player").getName()).append("现在不可以建造");
                                    if(context.getSource().getPlayerOrException().getAbilities().mayBuild != option){
                                        context.getSource().getPlayerOrException().getAbilities().mayBuild = option;
                                        context.getSource().getPlayerOrException().onUpdateAbilities();
                                        if(option){
                                            context.getSource().sendSuccess(canBuild,false);
                                        }else{
                                            context.getSource().sendSuccess(cannotBuild,false);
                                        }
                                        return 1;
                                    }else if(context.getSource().getPlayerOrException().getAbilities().mayBuild == option){
                                        MutableComponent alreadyCanBuild = new TextComponent("玩家").append(EntityArgument.getPlayer(context,"player").getName()).append("已经可以建造");
                                        MutableComponent alreadyCannotBuild = new TextComponent("玩家").append(EntityArgument.getPlayer(context,"player").getName()).append("已经不可以建造");
                                        if(option){
                                            context.getSource().sendFailure(alreadyCanBuild);
                                        }else{
                                            context.getSource().sendFailure(alreadyCannotBuild);
                                        }
                                        return 0;
                                    }
                                    return 0;
                                }))))
                                .then(Commands.literal("get").then(Commands.literal("build").executes(context -> {
                                    MutableComponent canBuild = new TextComponent("玩家").append(EntityArgument.getPlayer(context,"player").getName()).append("现在可以建造");
                                    MutableComponent cannotBuild = new TextComponent("玩家").append(EntityArgument.getPlayer(context,"player").getName()).append("现在不可以建造");
                                    if (context.getSource().getPlayerOrException().getAbilities().mayBuild){
                                        context.getSource().sendSuccess(canBuild,false);
                                        return 1;
                                    }else{
                                        context.getSource().sendSuccess(cannotBuild,false);
                                        return 0;
                                    }
                                })))
                        )
        );
    }

}
