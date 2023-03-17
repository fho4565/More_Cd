package com.fho4565.save;

import com.fho4565.define.option.AbstractOption;
import com.fho4565.init.InitOptions;
import com.fho4565.main.Utils;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.fho4565.main.Utils.WORLD_PATH;

@Mod.EventBusSubscriber
public class SaveOptions {
    public static boolean saved = false;
    @SubscribeEvent
    public static void onWorldSave(WorldEvent.Save event) throws IOException {
        if(!saved){
            save(Utils.SCOREBOARD_DISPLAY_OPTIONS, new File(WORLD_PATH + "\\mcd\\scoreboardOptions"));
            saved = true;
        }
        InitOptions.read = false;
        System.out.println("saved");
    }

    public static <T extends AbstractOption> void save(ArrayList<T> options, File f) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        for (int i = 0; i < options.size(); i++) {
            if(i != options.size()-1){
                if (options.get(i) != null) {
                    writer.write(options.get(i).toSaveable());
                    writer.newLine();
                    writer.flush();
                }
            }else{
                writer.write(options.get(i).toSaveable());
                writer.flush();
            }
        }
        writer.close();
        Utils.SCOREBOARD_DISPLAY_OPTIONS.clear();
    }
}
