package com.fho4565.commands.GUI;

import com.fho4565.commands.GUI.help.HelpMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.IContainerFactory;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Menus {
    private static final List<MenuType<?>> REGISTRY = new ArrayList<>();
    public static final MenuType<HelpMenu> HELP = register("help", HelpMenu::new);

    private static <T extends AbstractContainerMenu> MenuType<T> register(String registryName, IContainerFactory<T> containerFactory) {
        MenuType<T> menuType = new MenuType<>(containerFactory);
        menuType.setRegistryName(registryName);
        REGISTRY.add(menuType);
        return menuType;
    }


    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
        event.getRegistry().registerAll(REGISTRY.toArray(new MenuType[0]));
    }
}