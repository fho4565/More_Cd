package com.fho4565.commands;

import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;


public class Hurt {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("hurt").requires(s -> s.hasPermission(2))
                        .then(Commands.argument("entity", EntityArgument.entities())
                                .then(Commands.argument("damage", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int damage = IntegerArgumentType.getInteger(context,"damage");
                                            if (damage <= 0) {
                                                Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.hurt.failed",true, String.valueOf(damage));
                                                return 0;
                                            }
                                            for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                entity.hurt(DamageSource.GENERIC, damage);
                                                Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.hurt.success",entity.getName().getString(),String.valueOf(damage));
                                            }
                                            return IntegerArgumentType.getInteger(context,"damage");
                                        })
                                )
                        )
        );
    }

}
