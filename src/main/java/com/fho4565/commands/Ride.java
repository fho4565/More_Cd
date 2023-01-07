package com.fho4565.commands;

import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.TextComponent;

import java.util.Objects;


public class Ride {
    public static void register()  {
        CommandRegister.dispatcher.register(
                Commands.literal("ride").requires(s -> s.hasPermission(2))
                        .then(Commands.argument("entity", EntityArgument.entity()).executes(context -> {
                            if(!Objects.equals(context.getSource().getEntity(), EntityArgument.getEntity(context, "entity"))){
                                context.getSource().sendSuccess(new TextComponent("正在骑乘实体"), false);
                                Objects.requireNonNull(context.getSource().getEntity()).startRiding(EntityArgument.getEntity(context, "entity"));
                                return 1;
                            }else{
                                context.getSource().sendFailure(new TextComponent("你不能骑着自己！"));
                                return 0;
                            }
                                }).then(Commands.argument("targetEntity", EntityArgument.entity()).executes(context -> {
                            if(!Objects.equals(EntityArgument.getEntity(context, "entity"), EntityArgument.getEntity(context, "targetEntity"))){
                                context.getSource().sendSuccess(new TextComponent("正在骑乘实体"), false);
                                EntityArgument.getEntity(context,"entity").startRiding(EntityArgument.getEntity(context,"targetEntity"));
                                return 1;
                            }else{
                                context.getSource().sendFailure(new TextComponent("你不能骑着自己！"));
                                return 0;
                            }
                        })))

        );
    }

}
