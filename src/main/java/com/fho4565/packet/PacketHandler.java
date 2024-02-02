package com.fho4565.packet;

import com.fho4565.main.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    private static int id = 0;
    public static SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Utils.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void registerPackets() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Utils.MODID, "networking"),
                () -> PROTOCOL_VERSION,
                (version) -> version.equals(PROTOCOL_VERSION),
                (version) -> version.equals(PROTOCOL_VERSION)
        );
        INSTANCE.messageBuilder(MessagePacket.class, id++)
                .encoder(MessagePacket::encode)
                .decoder(MessagePacket::decode)
                .consumer(MessagePacket::handler)
                .add();
    }

    public static void sendToServer(MessagePacket packet) {
        INSTANCE.sendToServer(packet);
    }
}
