package com.fho4565.commands;

import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class Hurt {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("hurt").requires(s -> s.hasPermission(2))
                        .then(Commands.argument("entity", EntityArgument.entities())
                                .then(Commands.argument("damage", IntegerArgumentType.integer())
                                        .executes(context -> hurtEntity(context, DamageSource.GENERIC))
                                        .then(Commands.literal("entity")
                                                .then(Commands.argument("sourceEntity", EntityArgument.entity())
                                                        .executes(context -> hurtEntity(context, DamageSource.mobAttack((LivingEntity) EntityArgument.getEntity(context, "sourceEntity"))))))
                                        .then(Commands.literal("damageSource")
                                                .then(Commands.literal(DamageSource.IN_FIRE.getMsgId()).executes(context -> hurtEntity(context, DamageSource.IN_FIRE)))
                                                .then(Commands.literal(DamageSource.LIGHTNING_BOLT.getMsgId()).executes(context -> hurtEntity(context, DamageSource.LIGHTNING_BOLT)))
                                                .then(Commands.literal(DamageSource.ON_FIRE.getMsgId()).executes(context -> hurtEntity(context, DamageSource.ON_FIRE)))
                                                .then(Commands.literal(DamageSource.LAVA.getMsgId()).executes(context -> hurtEntity(context, DamageSource.LAVA)))
                                                .then(Commands.literal(DamageSource.HOT_FLOOR.getMsgId()).executes(context -> hurtEntity(context, DamageSource.HOT_FLOOR)))
                                                .then(Commands.literal(DamageSource.IN_WALL.getMsgId()).executes(context -> hurtEntity(context, DamageSource.IN_WALL)))
                                                .then(Commands.literal(DamageSource.CRAMMING.getMsgId()).executes(context -> hurtEntity(context, DamageSource.CRAMMING)))
                                                .then(Commands.literal(DamageSource.DROWN.getMsgId()).executes(context -> hurtEntity(context, DamageSource.DROWN)))
                                                .then(Commands.literal(DamageSource.STARVE.getMsgId()).executes(context -> hurtEntity(context, DamageSource.STARVE)))
                                                .then(Commands.literal(DamageSource.CACTUS.getMsgId()).executes(context -> hurtEntity(context, DamageSource.CACTUS)))
                                                .then(Commands.literal(DamageSource.FALL.getMsgId()).executes(context -> hurtEntity(context, DamageSource.FALL)))
                                                .then(Commands.literal(DamageSource.FLY_INTO_WALL.getMsgId()).executes(context -> hurtEntity(context, DamageSource.FLY_INTO_WALL)))
                                                .then(Commands.literal(DamageSource.OUT_OF_WORLD.getMsgId()).executes(context -> hurtEntity(context, DamageSource.OUT_OF_WORLD)))
                                                .then(Commands.literal(DamageSource.GENERIC.getMsgId()).executes(context -> hurtEntity(context, DamageSource.GENERIC)))
                                                .then(Commands.literal(DamageSource.MAGIC.getMsgId()).executes(context -> hurtEntity(context, DamageSource.MAGIC)))
                                                .then(Commands.literal(DamageSource.WITHER.getMsgId()).executes(context -> hurtEntity(context, DamageSource.WITHER)))
                                                .then(Commands.literal(DamageSource.ANVIL.getMsgId()).executes(context -> hurtEntity(context, DamageSource.ANVIL)))
                                                .then(Commands.literal(DamageSource.FALLING_BLOCK.getMsgId()).executes(context -> hurtEntity(context, DamageSource.FALLING_BLOCK)))
                                                .then(Commands.literal(DamageSource.DRAGON_BREATH.getMsgId()).executes(context -> hurtEntity(context, DamageSource.DRAGON_BREATH)))
                                                .then(Commands.literal(DamageSource.DRY_OUT.getMsgId()).executes(context -> hurtEntity(context, DamageSource.DRY_OUT)))
                                                .then(Commands.literal(DamageSource.SWEET_BERRY_BUSH.getMsgId()).executes(context -> hurtEntity(context, DamageSource.SWEET_BERRY_BUSH)))
                                                .then(Commands.literal(DamageSource.FREEZE.getMsgId()).executes(context -> hurtEntity(context, DamageSource.FREEZE)))
                                                .then(Commands.literal(DamageSource.FALLING_STALACTITE.getMsgId()).executes(context -> hurtEntity(context, DamageSource.FALLING_STALACTITE)))
                                                .then(Commands.literal(DamageSource.STALAGMITE.getMsgId()).executes(context -> hurtEntity(context, DamageSource.STALAGMITE)))
                                        )
                                )
                        )
        );
    }

    private static int hurtEntity(CommandContext<CommandSourceStack> context, DamageSource damageSource) throws CommandSyntaxException {
        int damage = IntegerArgumentType.getInteger(context, "damage");
        if (damage <= 0) {
            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
            return 0;
        }
        for (Entity entity : EntityArgument.getEntities(context, "entity")) {
            entity.hurt(damageSource, damage);
            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
        }
        return IntegerArgumentType.getInteger(context, "damage");
    }

}
