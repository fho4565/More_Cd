package com.fho4565.main;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.Scoreboard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Iterator;


public class Utils {
    public static final String MODID = "more_cd";
    public static final Logger logger = LogManager.getLogger();
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
    public static Tag getData(CommandContext<CommandSourceStack> commandContext,String resourceLocation, String path) throws CommandSyntaxException {
        Collection<Tag> tags = NbtPathArgument.getPath(commandContext,path).get(commandContext.getSource().getServer().getCommandStorage().get(ResourceLocationArgument.getId(commandContext,resourceLocation)));
        Iterator<Tag> iterator = tags.iterator();
        return iterator.next();
    }
    public static void setData(CommandContext<CommandSourceStack> commandContext,String sourceResourceLocation, String sourcePath,Tag tag) throws CommandSyntaxException {
        NbtPathArgument.getPath(commandContext,sourcePath).set(commandContext.getSource().getServer().getCommandStorage().get(ResourceLocationArgument.getId(commandContext, sourceResourceLocation)), () -> tag);
    }
    public static void sendCdFeedback(CommandContext<net.minecraft.commands.CommandSourceStack> commandContext, String content){
        commandContext.getSource().sendSuccess(new TextComponent(content), false);
    }
    public static void sendCdFeedback(CommandContext<net.minecraft.commands.CommandSourceStack> commandContext, String content, Boolean aborted){
        if(!aborted){
            commandContext.getSource().sendSuccess(new TextComponent(content), false);
        }else{
            commandContext.getSource().sendFailure(new TextComponent(content));
        }
    }
}
