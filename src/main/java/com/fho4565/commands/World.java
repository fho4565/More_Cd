package com.fho4565.commands;

import com.fho4565.main.Utils;
import net.minecraft.commands.Commands;

import java.util.Objects;


public class World {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("world")
                        .then(Commands.literal("spawnX").executes(context -> {
                            Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.world.spawnPoint.success","X", String.valueOf(context.getSource().getLevel().getLevelData().getXSpawn()));
                            return context.getSource().getLevel().getLevelData().getXSpawn();
                        }))
                        .then(Commands.literal("spawnY").executes(context -> {
                            Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.world.spawnPoint.success","Y", String.valueOf(context.getSource().getLevel().getLevelData().getYSpawn()));
                            return context.getSource().getLevel().getLevelData().getYSpawn();
                        }))
                        .then(Commands.literal("spawnZ").executes(context -> {
                            Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.world.spawnPoint.success","Z", String.valueOf(context.getSource().getLevel().getLevelData().getZSpawn()));
                            return context.getSource().getLevel().getLevelData().getZSpawn();
                        }))
                        .then(Commands.literal("version").executes(context -> {
                            String ver = context.getSource().getServer().getServerVersion();
                            String[] split = ver.split("\\.");
                            int version = 0;
                            for (int i = 0; i < split.length; i++) {
                                version = (int) ((version + Integer.parseInt(split[i])) * 1000/java.lang.Math.pow(10, i+1));
                            }
                            Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.world.version.success",ver);
                            return version;
                        }))
                        .then(Commands.literal("tickTime").executes(context -> {
                            double tickTime = (mean(Objects.requireNonNull(context.getSource().getServer().getTickTime(context.getSource().getLevel().dimension()))) * 1.0E-6D);
                            Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.world.tickTime.success", String.valueOf(tickTime));
                            return (int) tickTime;
                        }))
                        .then(Commands.literal("tps").executes(context -> {
                            double tps = (java.lang.Math.min(1000.0 / (mean(Objects.requireNonNull(context.getSource().getServer().getTickTime(context.getSource().getLevel().dimension()))) * 1.0E-6D), 20));
                            Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.world.tps.success", String.valueOf(tps));
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
