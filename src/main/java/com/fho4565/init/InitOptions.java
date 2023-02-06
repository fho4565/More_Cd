package com.fho4565.init;

import com.fho4565.define.option.AbstractOption;
import com.fho4565.define.option.ScoreboardDisplayOption;
import com.fho4565.save.SaveOptions;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.fho4565.main.Utils.*;

@Mod.EventBusSubscriber
public class InitOptions {
    public static boolean read = false;
    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) throws IOException {
        if (event.getWorld().getServer() != null) {
            WORLD_PATH = event.getWorld().getServer().getWorldPath(new LevelResource("")).toFile().getAbsolutePath();
            initOptionFiles();
        }
    }

    public static void initOptionFiles() throws IOException {
        File mcdBaseDir = new File(WORLD_PATH + "\\mcd");
        File scoreboardOptions = new File(WORLD_PATH + "\\mcd\\scoreboardOptions");
        File memory = new File(WORLD_PATH + "\\mcd\\memory");
        if (!mcdBaseDir.exists()) {
            if (mcdBaseDir.mkdir()) {
                LOGGER.info(WORLD_PATH + "\\mcd is not exist,created!");
            }
        }
        if (!scoreboardOptions.exists()) {
            if (scoreboardOptions.createNewFile()) {
                LOGGER.info(WORLD_PATH + "\\mcd\\scoreboardOptions is not exist,created!");
            }
        }
        if (!memory.exists()) {
            if (memory.mkdir()) {
                LOGGER.info(WORLD_PATH + "\\mcd\\memory is not exist,created!");
            }
        }
        if(!read){
            read(scoreboardOptions);
            System.out.println(SCOREBOARD_DISPLAY_OPTIONS);
            read = true;
        }
        SaveOptions.saved = false;
    }

    private static void read(File f) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        String line;
        if (f.getAbsolutePath().equals(WORLD_PATH + "\\mcd\\scoreboardOptions")) {
            while ((line = bufferedReader.readLine()) != null) {
                SCOREBOARD_DISPLAY_OPTIONS.add(toScoreboardDisplayOption(line));
            }
        }
    }

    private static ScoreboardDisplayOption toScoreboardDisplayOption(String s) {
        String[] str = s.split(AbstractOption.OPTION_SEPARATOR);
        if (str[0].equals("ScoreboardDisplayOption")) {
            if(!SCOREBOARD_DISPLAY_OPTIONS.isEmpty()) {
                for (ScoreboardDisplayOption sdo : SCOREBOARD_DISPLAY_OPTIONS) {
                    if (sdo == null || !sdo.getObjectiveName().equals(str[1])) {
                        return new ScoreboardDisplayOption(str[1], Integer.parseInt(str[2]), Integer.parseInt(str[3]), str[4],Boolean.parseBoolean(str[5]));
                    }
                }
            }else{
                return new ScoreboardDisplayOption(str[1], Integer.parseInt(str[2]), Integer.parseInt(str[3]), str[4],Boolean.parseBoolean(str[5]));
            }
        }
        return new ScoreboardDisplayOption("?", 0, 0, "note?");
    }
}