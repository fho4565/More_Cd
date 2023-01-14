package com.fho4565.commands;

import com.fho4565.main.Utils;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;

public class Run {
    public static void register()  {
        CommandRegister.dispatcher.register(
                Commands.literal("run").requires(s -> s.hasPermission(2))
                        .then(Commands.argument("target", ResourceLocationArgument.id())
                                .then(Commands.argument("path",NbtPathArgument.nbtPath()).executes(context -> {
                                    String cmd;
                                    if (!(cmd = Utils.getData(context,"target" ,"path").getAsString()).equals("")){
                                        context.getSource().getServer().getCommands().performCommand(context.getSource(),cmd);
                                        return 1;
                                    }else{
                                        Utils.sendCdFeedback(context,"你不能执行空命令！",true);
                                        return 0;
                                    }
                                }))));
    }

}
