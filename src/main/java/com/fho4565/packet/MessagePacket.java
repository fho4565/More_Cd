package com.fho4565.packet;

import com.fho4565.main.Configs;
import com.fho4565.main.Utils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessagePacket {
    private final String message;

    public MessagePacket(String message) {
        this.message = message;
    }

    public static void encode(MessagePacket packet, FriendlyByteBuf buffer) {
        buffer.writeUtf(packet.message);
    }

    public static MessagePacket decode(FriendlyByteBuf buffer) {
        return new MessagePacket(buffer.readUtf(32767));
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Utils.logger.info(this.message);
            ServerPlayer sender = ctx.get().getSender();
            if(sender!=null){
                switch (this.message) {
                    case "1" -> Utils.executeCommand(sender, Configs.keycd1.get());
                    case "2" -> Utils.executeCommand(sender, Configs.keycd2.get());
                    case "3" -> Utils.executeCommand(sender, Configs.keycd3.get());
                    case "4" -> Utils.executeCommand(sender, Configs.keycd4.get());
                    case "5" -> Utils.executeCommand(sender, Configs.keycd5.get());
                    case "6" -> Utils.executeCommand(sender, Configs.keycd6.get());
                }
            }
        });

        ctx.get().setPacketHandled(true);
    }
}
