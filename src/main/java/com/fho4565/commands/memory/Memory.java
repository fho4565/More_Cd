package com.fho4565.commands.memory;

import com.fho4565.commands.CommandRegister;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;

public class Memory {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("memory").requires(s -> s.hasPermission(2))
                        .then(Commands.literal("RAM"))
                        .then(Commands.literal("load")
                                .then(Commands.argument("name", StringArgumentType.string())
                                        .then(Commands.argument("name", StringArgumentType.string())
                                                .then(Commands.argument("name", StringArgumentType.string())
                                                        .then(Commands.argument("name", StringArgumentType.string())

                                                        )))))
                        .then(Commands.literal("write"))
                        .then(Commands.literal("delete"))
                        .then(Commands.literal(""))
        );
    }

    enum Type {
        RAM,
        ROM,
        DISK
    }
/*.executes(context -> {
        String load;
        try {
            load = LoadMemory.load(context, StringArgumentType.getString(context, "name"), StringArgumentType.getString(context, "key"));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Byte.parseByte(load);
        }catch (NumberFormatException byteE){
            try {
                Short.parseShort(load);
            } catch (NumberFormatException ShortE) {
                try {
                    Integer.parseInt(load);
                } catch (NumberFormatException IntegerE) {
                    try {
                        Long.parseLong(load);
                    } catch (NumberFormatException LongE) {
                        try {
                            Float.parseFloat(load);
                        } catch (NumberFormatException FloatE) {
                            try {
                                Double.parseDouble(load);
                            } catch (NumberFormatException e) {
                                return 0;
                            }
                        }
                    }
                }
            }
        }
        return 1;
    })*/
}
