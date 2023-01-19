package com.fho4565.commands;

import com.fho4565.commands.GUI.help.HelpMenu;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import io.netty.buffer.Unpooled;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class helpMcd {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("helpMcd").executes(context -> {
                    if(context.getInput().contains("execute")){
                        throw new SimpleCommandExceptionType(new TranslatableComponent("mcd.com.fho4565.command.run.helpMcd.failed")).create();
                    }
                    ServerLevel level = context.getSource().getLevel();
                    Vec3 position = context.getSource().getPosition();
                    Entity entity = context.getSource().getEntity();
                    if (entity == null) {
                        entity = FakePlayerFactory.getMinecraft(level);
                    }

                    execute(position.x(), position.y(), position.z(), entity);
                    return 1;
                })
        );
    }

    public static void execute(double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        if (entity instanceof ServerPlayer serverPlayer) {
            BlockPos blockPos = new BlockPos(x, y, z);
            NetworkHooks.openGui(serverPlayer, new MenuProvider() {
                @Override
                public @NotNull AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, net.minecraft.world.entity.player.@NotNull Player player) {
                    return new HelpMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(blockPos));
                }

                @Override
                public @NotNull Component getDisplayName() {
                    return new TextComponent("helpMcd");
                }
            }, blockPos);
        }
    }
}
