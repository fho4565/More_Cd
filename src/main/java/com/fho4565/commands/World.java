package com.fho4565.commands;

import com.fho4565.main.Utils;
import net.minecraft.commands.Commands;
import net.minecraft.world.level.storage.LevelResource;


public class World {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("world")
                        .then(Commands.literal("spawnX").executes(context -> {
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.world.spawnPoint.success", "X", String.valueOf(context.getSource().getLevel().getLevelData().getXSpawn()));
                            return context.getSource().getLevel().getLevelData().getXSpawn();
                        }))
                        .then(Commands.literal("spawnY").executes(context -> {
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.world.spawnPoint.success", "Y", String.valueOf(context.getSource().getLevel().getLevelData().getYSpawn()));
                            return context.getSource().getLevel().getLevelData().getYSpawn();
                        }))
                        .then(Commands.literal("spawnZ").executes(context -> {
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.world.spawnPoint.success", "Z", String.valueOf(context.getSource().getLevel().getLevelData().getZSpawn()));
                            return context.getSource().getLevel().getLevelData().getZSpawn();
                        }))
                        .then(Commands.literal("getWorldPath").executes(context -> {
                            String path = context.getSource().getServer().getWorldPath(new LevelResource("")).toFile().getAbsolutePath().replace("\\.", "");
                            Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.world.getWorldPath", path);
                            System.out.println(path + "\\mcd\\keybindCommands.data");
                            return 1;
                        }))
        );
    }
}