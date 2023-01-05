package com.fho4565.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class Hurt {
    @SubscribeEvent
    public static void onServerStaring(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(
                Commands.literal("hurt")
                        .then(Commands.argument("entity", EntityArgument.entities())
                                .then(Commands.argument("damage", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            if (context.getArgument("damage", Integer.class) <= 0) {
                                                context.getSource().sendFailure(new TextComponent("你不能对实体造成" + context.getArgument("damage", Integer.class) + "点伤害"));
                                                return 0;
                                            }
                                            for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                entity.hurt(DamageSource.GENERIC, context.getArgument("damage", Integer.class));
                                                TextComponent t = new TextComponent("对");
                                                t.append(entity.getName()).append(new TextComponent("造成了" + IntegerArgumentType.getInteger(context, "damage") + "点伤害"));
                                                context.getSource().sendSuccess(t, false);
                                            }
                                            return context.getArgument("damage", Integer.class);
                                        })
                                )
                        )
        );
    }

}
