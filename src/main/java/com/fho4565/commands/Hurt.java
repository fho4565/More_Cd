package com.fho4565.commands;

import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.IntegerArgumentType;
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
                                        .executes(context -> {
                                            int damage = IntegerArgumentType.getInteger(context, "damage");
                                            if (damage <= 0) {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                return 0;
                                            }
                                            for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                entity.hurt(DamageSource.GENERIC, damage);
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                            }
                                            return IntegerArgumentType.getInteger(context, "damage");
                                        })
                                        .then(Commands.literal("entity")
                                                .then(Commands.argument("sourceEntity", EntityArgument.entity())
                                                        .executes(context -> {
                                                            int damage = IntegerArgumentType.getInteger(context, "damage");
                                                            if (damage <= 0) {
                                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                                return 0;
                                                            }
                                                            for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                                entity.hurt(DamageSource.mobAttack((LivingEntity) EntityArgument.getEntity(context, "sourceEntity")), damage);
                                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                            }
                                                            return IntegerArgumentType.getInteger(context, "damage");
                                                        })))
                                        .then(Commands.literal("damageSource")
                                                .then(Commands.literal(DamageSource.IN_FIRE.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.IN_FIRE, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.LIGHTNING_BOLT.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.LIGHTNING_BOLT, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.ON_FIRE.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.ON_FIRE, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.LAVA.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.LAVA, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.HOT_FLOOR.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.HOT_FLOOR, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.IN_WALL.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.IN_WALL, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.CRAMMING.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.CRAMMING, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.DROWN.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.DROWN, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.STARVE.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.STARVE, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.CACTUS.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.CACTUS, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.FALL.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.FALL, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.FLY_INTO_WALL.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.FLY_INTO_WALL, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.OUT_OF_WORLD.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.OUT_OF_WORLD, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.GENERIC.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.GENERIC, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.MAGIC.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.MAGIC, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.WITHER.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.WITHER, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.ANVIL.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.ANVIL, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.FALLING_BLOCK.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.FALLING_BLOCK, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.DRAGON_BREATH.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.DRAGON_BREATH, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.DRY_OUT.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.DRY_OUT, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.SWEET_BERRY_BUSH.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.SWEET_BERRY_BUSH, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.FREEZE.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.FREEZE, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.FALLING_STALACTITE.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.FALLING_STALACTITE, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                                .then(Commands.literal(DamageSource.STALAGMITE.getMsgId()).executes(context -> {
                                                    int damage = IntegerArgumentType.getInteger(context, "damage");
                                                    if (damage <= 0) {
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.failed", true, String.valueOf(damage));
                                                        return 0;
                                                    }
                                                    for (Entity entity : EntityArgument.getEntities(context, "entity")) {
                                                        entity.hurt(DamageSource.STALAGMITE, damage);
                                                        Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.hurt.success", entity.getName().getString(), String.valueOf(damage));
                                                    }
                                                    return IntegerArgumentType.getInteger(context, "damage");
                                                }))
                                        )
                                )
                        )
        );
    }

}
