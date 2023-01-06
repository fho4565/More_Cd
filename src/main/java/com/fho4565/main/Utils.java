package com.fho4565.main;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.Scoreboard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Utils {
    public static final String MODID = "more_cd";
    public static final Logger LOGGER = LogManager.getLogger(Utils.class);
    /** @param playerName 玩家名字
     * @param objective 要获取的计分板名字*/
    public static int getScore(CommandSourceStack commandSourceStack, String playerName, Objective objective){
        Scoreboard scoreboard = commandSourceStack.getServer().getScoreboard();
        if (!scoreboard.hasPlayerScore(playerName, objective)) {
            return 0;
        } else {
            Score score = scoreboard.getOrCreatePlayerScore(playerName, objective);
            return score.getScore();
        }
    }
}
