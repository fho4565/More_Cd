package com.fho4565.commands;

import com.fho4565.main.Utils;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.nbt.ListTag;

public class Run {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("run").requires(s -> s.hasPermission(2))
                        .then(Commands.literal("multiple")
                                .then(Commands.argument("target", ResourceLocationArgument.id())
                                        .then(Commands.argument("path", NbtPathArgument.nbtPath()).executes(context -> {
                                            if (Utils.getData(context, "target", "path").getType().getName().equals("LIST")) {
                                                ListTag cds = (ListTag) Utils.getData(context, "target", "path");
                                                cds.forEach(tag -> context.getSource().getServer().getCommands().performCommand(context.getSource(), tag.getAsString()));
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.run.multiple.success");
                                                return 1;
                                            } else {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.run.multiple.failed", true);
                                                return 0;
                                            }
                                        }))))
                        .then(Commands.literal("single")
                                .then(Commands.argument("target", ResourceLocationArgument.id())
                                        .then(Commands.argument("path", NbtPathArgument.nbtPath()).executes(context -> {
                                            String cmd;
                                            if (!(cmd = Utils.getData(context, "target", "path").getAsString()).equals("")) {
                                                context.getSource().getServer().getCommands().performCommand(context.getSource(), cmd);
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.run.single.success", true);
                                                return 1;
                                            } else {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.run.single.failed", true);
                                                return 0;
                                            }
                                        }))))
        );
    }

}
