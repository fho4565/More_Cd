package com.fho4565.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.nbt.Tag;
import net.minecraft.server.commands.data.DataAccessor;
import net.minecraft.server.commands.data.DataCommands;
import net.minecraft.server.commands.data.StorageDataAccessor;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;


@Mod.EventBusSubscriber
public class Run {
    @SubscribeEvent
    public static void onServerStaring(RegisterCommandsEvent event) {
        DataCommands.DataProvider dataProvider = StorageDataAccessor.PROVIDER.apply("target");
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(
                Commands.literal("run").requires(s -> s.hasPermission(2))
                        .then(dataProvider.wrap(Commands.argument("nbtPath", NbtPathArgument.nbtPath()),builder->builder
                                .executes(context -> {
                                    DataAccessor accessor = dataProvider.access(context);
                                    NbtPathArgument.NbtPath path = NbtPathArgument.getPath(context,"nbtPath");
                                    List<Tag> list = path.get(accessor.getData());
                                    if(list.size() == 1){
                                        String cmd = list.get(0).getAsString();
                                        context.getSource().getServer().getCommands().performCommand(context.getSource(),cmd);
                                        return 1;
                                    }
                                    return 0;
                                })))
        );
    }

}
