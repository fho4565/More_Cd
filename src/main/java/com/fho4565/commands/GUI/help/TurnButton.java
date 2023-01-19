package com.fho4565.commands.GUI.help;

import com.fho4565.main.Main;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TurnButton {
    private final int buttonID, x, y, z;

    public TurnButton(FriendlyByteBuf buffer) {
        this.buttonID = buffer.readInt();
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
    }

    public static void buffer(TurnButton message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.buttonID);
        buffer.writeInt(message.x);
        buffer.writeInt(message.y);
        buffer.writeInt(message.z);
    }

    public static void handler(TurnButton message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            Player entity = context.getSender();
            int buttonID = message.buttonID;
            int x = message.x;
            int y = message.y;
            int z = message.z;
            handleButtonAction(Objects.requireNonNull(entity), buttonID, x, y, z);
        });
        context.setPacketHandled(true);
    }

    public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
        Level world = entity.level;
        if (!world.hasChunkAt(new BlockPos(x, y, z)))
            return;
        switch (buttonID) {
            case 0 -> {
                if (1 < HelpScreen.index) {
                    HelpScreen.index--;
                } else {
                    HelpScreen.index = HelpScreen.maxPage;
                }
            }
            case 1 -> {
                if (HelpScreen.index < HelpScreen.maxPage) {
                    HelpScreen.index++;
                } else {
                    HelpScreen.index = 1;
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        Main.addNetworkMessage(TurnButton.class, TurnButton::buffer, TurnButton::new, TurnButton::handler);
    }
}

