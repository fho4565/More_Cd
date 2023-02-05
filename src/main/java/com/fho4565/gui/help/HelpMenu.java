package com.fho4565.gui.help;

import com.fho4565.gui.Menus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class HelpMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public final Level world;
    public final Player entity;
    public int x, y, z;
    private final Map<Integer, Slot> customSlots = new HashMap<>();

    public HelpMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(Menus.HELP, id);
        this.entity = inv.player;
        this.world = inv.player.level;
        BlockPos pos;
        if (extraData != null) {
            pos = extraData.readBlockPos();
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
        }
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    public Map<Integer, Slot> get() {
        return customSlots;
    }
}