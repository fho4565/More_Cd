package com.fho4565.keyboards;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeybindingRegister {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> ClientRegistry.registerKeyBinding(KeyBindings.KEY_1));
        event.enqueueWork(() -> ClientRegistry.registerKeyBinding(KeyBindings.KEY_2));
        event.enqueueWork(() -> ClientRegistry.registerKeyBinding(KeyBindings.KEY_4));
        event.enqueueWork(() -> ClientRegistry.registerKeyBinding(KeyBindings.KEY_6));
        event.enqueueWork(() -> ClientRegistry.registerKeyBinding(KeyBindings.KEY_3));
        event.enqueueWork(() -> ClientRegistry.registerKeyBinding(KeyBindings.KEY_5));
    }
}
