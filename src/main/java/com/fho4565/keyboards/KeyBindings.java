package com.fho4565.keyboards;

import com.fho4565.packet.MessagePacket;
import com.fho4565.packet.PacketHandler;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class KeyBindings {
    public static final KeyMapping KEY_1 = new KeyMapping("mcd.com.fho4565.prompt.keybind1", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_J, "mcd.com.fho4565.prompt.keybinds");
    public static final KeyMapping KEY_2 = new KeyMapping("mcd.com.fho4565.prompt.keybind2", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K, "mcd.com.fho4565.prompt.keybinds");
    public static final KeyMapping KEY_3 = new KeyMapping("mcd.com.fho4565.prompt.keybind3", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_L, "mcd.com.fho4565.prompt.keybinds");
    public static final KeyMapping KEY_4 = new KeyMapping("mcd.com.fho4565.prompt.keybind4", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_I, "mcd.com.fho4565.prompt.keybinds");
    public static final KeyMapping KEY_5 = new KeyMapping("mcd.com.fho4565.prompt.keybind5", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, "mcd.com.fho4565.prompt.keybinds");
    public static final KeyMapping KEY_6 = new KeyMapping("mcd.com.fho4565.prompt.keybind6", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_M, "mcd.com.fho4565.prompt.keybinds");

    @SubscribeEvent
    public static void onClientTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            LocalPlayer player = Minecraft.getInstance().player;
            while (KEY_1.consumeClick()) {
                if (player != null) PacketHandler.sendToServer(new MessagePacket("1"));
            }
            while (KEY_2.consumeClick()) {
                if (player != null) PacketHandler.sendToServer(new MessagePacket("2"));
            }
            while (KEY_3.consumeClick()) {
                if (player != null) PacketHandler.sendToServer(new MessagePacket("3"));
            }
            while (KEY_4.consumeClick()) {
                if (player != null) PacketHandler.sendToServer(new MessagePacket("4"));
            }
            while (KEY_5.consumeClick()) {
                if (player != null) PacketHandler.sendToServer(new MessagePacket("5"));
            }
            while (KEY_6.consumeClick()) {
                if (player != null) PacketHandler.sendToServer(new MessagePacket("6"));
            }
        }
    }
}

