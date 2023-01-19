package com.fho4565.commands;

import com.fho4565.main.Utils;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;

import java.util.Objects;


public class Ride {
    public static void register()  {
        CommandRegister.dispatcher.register(
                Commands.literal("ride").requires(s -> s.hasPermission(2))
                        .then(Commands.argument("entity", EntityArgument.entity()).executes(context -> {
                            if(!Objects.equals(context.getSource().getEntity(), EntityArgument.getEntity(context, "entity"))){
                                Objects.requireNonNull(context.getSource().getEntity()).startRiding(EntityArgument.getEntity(context, "entity"));
                                Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.ride.success");
                                return 1;
                            }else{
                                Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.ride.failed",true);
                                return 0;
                            }
                                }).then(Commands.argument("targetEntity", EntityArgument.entity()).executes(context -> {
                            if(!Objects.equals(EntityArgument.getEntity(context, "entity"), EntityArgument.getEntity(context, "targetEntity"))){
                                EntityArgument.getEntity(context,"entity").startRiding(EntityArgument.getEntity(context,"targetEntity"));
                                Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.ride.successE",EntityArgument.getEntity(context, "entity").getName().getString(),EntityArgument.getEntity(context, "targetEntity").getName().getString());
                                return 1;
                            }else{
                                Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.ride.successE",true,EntityArgument.getEntity(context, "entity").getName().getString());
                                return 0;
                            }
                        })))

        );
    }

}
