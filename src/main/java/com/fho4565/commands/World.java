package com.fho4565.commands;

import com.fho4565.main.Utils;
import net.minecraft.commands.Commands;

import java.util.Objects;


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
                        .then(Commands.literal("majorVersion").executes(context -> {
                            int ver = Integer.parseInt(context.getSource().getServer().getServerVersion().split("\\.")[0]);
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.world.majorVersion.success", String.valueOf(ver));
                            return ver;
                        }))
                        .then(Commands.literal("subVersion").executes(context -> {
                            int ver = Integer.parseInt(context.getSource().getServer().getServerVersion().split("\\.")[1]);
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.world.subVersion.success", String.valueOf(ver));
                            return ver;
                        }))
                        .then(Commands.literal("revisedVersion").executes(context -> {
                            int ver = Integer.parseInt(context.getSource().getServer().getServerVersion().split("\\.")[2]);
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.world.revisedVersion.success", String.valueOf(ver));
                            return ver;
                        }))
                        .then(Commands.literal("tickTime").executes(context -> {
                            double tickTime = (mean(Objects.requireNonNull(context.getSource().getServer().getTickTime(context.getSource().getLevel().dimension()))) * 1.0E-6D);
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.world.tickTime.success", String.valueOf(tickTime));
                            return (int) tickTime;
                        }))
                        .then(Commands.literal("tps").executes(context -> {
                            double tps = (java.lang.Math.min(1000.0 / (mean(Objects.requireNonNull(context.getSource().getServer().getTickTime(context.getSource().getLevel().dimension()))) * 1.0E-6D), 20));
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.world.tps.success", String.valueOf(tps));
                            return (int) tps;
                        }))
        );
    }

    private static long mean(long[] values) {
        long sum = 0L;
        for (long v : values)
            sum += v;
        return sum / values.length;
    }

}
