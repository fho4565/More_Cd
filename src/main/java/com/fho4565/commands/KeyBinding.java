package com.fho4565.commands;

import com.fho4565.main.Configs;
import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraftforge.common.ForgeConfigSpec;

public class KeyBinding {
    public static void register() {
        registerKeyCommand(1);
        registerKeyCommand(2);
        registerKeyCommand(3);
        registerKeyCommand(4);
        registerKeyCommand(5);
        registerKeyCommand(6);
    }
public static ForgeConfigSpec.ConfigValue<String> getConfig(int i){
    switch (i) {
        case 1 -> {
            return Configs.keycd1;
        }
        case 2 -> {
            return Configs.keycd2;
        }
        case 3 -> {
            return Configs.keycd3;
        }
        case 4 -> {
            return Configs.keycd4;
        }
        case 5 -> {
            return Configs.keycd5;
        }
        case 6 -> {
            return Configs.keycd6;
        }default -> {
            return Configs.keycd1;
        }
    }
}
    private static void registerKeyCommand(int key) {
        CommandRegister.dispatcher.register(
                Commands.literal("keyCommands").requires(s -> s.hasPermission(2))
                        .then(Commands.literal("get")
                                .then(Commands.literal(String.valueOf(key))
                                        .executes(context -> {
                                            Utils.sendCdFeedback(context, getConfig(key).get());
                                            return 1;
                                        })
                                )
                        )
                        .then(Commands.literal("set")
                                .then(Commands.literal(String.valueOf(key)).then(Commands.argument("command", StringArgumentType.string())
                                        .executes(context -> {
                                            getConfig(key).set(StringArgumentType.getString(context, "command"));
                                            getConfig(key).save();
                                            Utils.sendCdFeedback(context, "succeed");
                                            return 1;
                                        })
                                )
                        )
        ));
    }
}