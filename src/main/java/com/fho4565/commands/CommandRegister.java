package com.fho4565.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommandRegister {
    public static CommandDispatcher<CommandSourceStack> dispatcher;

    @SubscribeEvent
    public static void onServerStaring(RegisterCommandsEvent event) {
        dispatcher = event.getDispatcher();
        Hurt.register();
        MathCommand.register();
        Player.register();
        Random.register();
        RealityTime.register();
        Ride.register();
        Run.register();
        World.register();
        HelpMcd.register();
        DataString.register();
        Explode.register();
        Game.register();
        KeyBinding.register();
    }
}
