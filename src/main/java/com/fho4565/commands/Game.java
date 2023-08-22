package com.fho4565.commands;

import com.fho4565.main.Utils;
import net.minecraft.commands.Commands;

import java.util.Objects;


public class Game {
    private static long mean(long[] values) {
        long sum = 0L;
        for (long v : values)
            sum += v;
        return sum / values.length;
    }
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("game").requires(s -> s.hasPermission(2))
                        .then(Commands.literal("majorVersion").executes(context -> {
                            int ver = Integer.parseInt(context.getSource().getServer().getServerVersion().split("\\.")[0]);
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.game.majorVersion", String.valueOf(ver));
                            return ver;
                        }))
                        .then(Commands.literal("subVersion").executes(context -> {
                            int ver = Integer.parseInt(context.getSource().getServer().getServerVersion().split("\\.")[1]);
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.game.subVersion", String.valueOf(ver));
                            return ver;
                        }))
                        .then(Commands.literal("revisedVersion").executes(context -> {
                            int ver = Integer.parseInt(context.getSource().getServer().getServerVersion().split("\\.")[2]);
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.game.revisedVersion", String.valueOf(ver));
                            return ver;
                        }))
                        .then(Commands.literal("tickTime").executes(context -> {
                            double tickTime = (mean(Objects.requireNonNull(context.getSource().getServer().getTickTime(context.getSource().getLevel().dimension()))) * 1.0E-6D);
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.game.tickTime", String.valueOf(tickTime));
                            return (int) tickTime;
                        }))
                        .then(Commands.literal("tps").executes(context -> {
                            double tps = (java.lang.Math.min(1000.0 / (mean(Objects.requireNonNull(context.getSource().getServer().getTickTime(context.getSource().getLevel().dimension()))) * 1.0E-6D), 20));
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.game.tps", String.valueOf(tps));
                            return (int) tps;
                        }))
                        .then(Commands.literal("freeMemory").executes(context -> {
                            long mem = Runtime.getRuntime().freeMemory();
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.game", String.valueOf(mem));
                            return (int) mem;
                        }))
                        .then(Commands.literal("totalMemory").executes(context -> {
                            long mem = Runtime.getRuntime().totalMemory();
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.game", String.valueOf(mem));
                            return (int) mem;
                        }))
                        .then(Commands.literal("maxMemory").executes(context -> {
                            long mem = Runtime.getRuntime().maxMemory();
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.game", String.valueOf(mem));
                            return (int) mem;
                        }))
                        .then(Commands.literal("gc").executes(context -> {
                            System.gc();
                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.game.gc");
                            return 1;
                        }))
        );
    }

}
