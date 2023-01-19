package com.fho4565.commands;

import com.fho4565.main.Utils;
import net.minecraft.commands.Commands;

import java.util.Calendar;


public class RealityTime {
    public static void register()  {
        CommandRegister.dispatcher.register(
                Commands.literal("realityTime")
                        .then(Commands.literal("second").executes(context -> {
                            Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.realityTime.second.success", String.valueOf(Calendar.getInstance().get(Calendar.SECOND)));
                            return Calendar.getInstance().get(Calendar.SECOND);
                        }))
                        .then(Commands.literal("minute").executes(context -> {
                            Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.realityTime.minute.success", String.valueOf(Calendar.getInstance().get(Calendar.MINUTE)));
                            return Calendar.getInstance().get(Calendar.MINUTE);
                        }))
                        .then(Commands.literal("hour12").executes(context -> {
                            Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.realityTime.hour12.success", String.valueOf(Calendar.getInstance().get(Calendar.HOUR)));
                            return Calendar.getInstance().get(Calendar.HOUR);
                        }))
                        .then(Commands.literal("hour24").executes(context -> {
                            Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.realityTime.hour24.success", String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)));
                            return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                        }))
                        .then(Commands.literal("day").executes(context -> {
                            Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.realityTime.day.success", String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
                            return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                        }))
                        .then(Commands.literal("month").executes(context -> {
                            Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.realityTime.month.success", String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1));
                            return Calendar.getInstance().get(Calendar.MONTH);
                        }))
                        .then(Commands.literal("year").executes(context -> {
                            Utils.sendTCdFeedback(context,"mcd.com.fho4565.command.realityTime.year.success", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                            return Calendar.getInstance().get(Calendar.YEAR);
                        }))
        );
    }

}
