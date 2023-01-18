package com.fho4565.commands.memory;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.level.storage.LevelResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class LoadMemory {
    public static String load(CommandContext<CommandSourceStack> context,String name,String key) throws IOException, ClassNotFoundException {
        File f = new File(context.getSource().getServer().getWorldPath(new LevelResource("")).toAbsolutePath() +"\\data\\memory.disk");
        if(!f.exists()) {
            f.createNewFile();
        }
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        HashMap<String,Data> tmpData = (HashMap<String, Data>) ois.readObject();
        return tmpData.get(name).getData(key);
    }
}
