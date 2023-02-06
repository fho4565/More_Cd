package com.fho4565.main;

import com.fho4565.define.option.ScoreboardDisplayOption;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.Scoreboard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class Utils{
    public static final String MODID = "more_cd";
    public static final Logger LOGGER = LogManager.getLogger();
    public static String WORLD_PATH = "";
    public static String SCOREBOARD_OPTIONS_PATH = WORLD_PATH + "\\mcd\\scoreboardOptions";
    public static final ArrayList<ScoreboardDisplayOption> SCOREBOARD_DISPLAY_OPTIONS = new ArrayList<>();
    public static SuggestionProvider<CommandSourceStack> createCommandSuggestion(String[] strings){
        return SuggestionProviders.register(new ResourceLocation(""),
                (context, builder) ->
                        SharedSuggestionProvider.suggest(strings,
                                builder));
    }
    public static String getWorldPath(CommandContext<CommandSourceStack> context){
        return context.getSource().getServer().getWorldPath(new LevelResource("")).toFile().getAbsolutePath();
    }
    /** @param playerName 玩家名字
     * @param objective 要获取的计分板名字*/
    public static int getScore(MinecraftServer server, String playerName, Objective objective){
        Scoreboard scoreboard = server.getScoreboard();
        if (!scoreboard.hasPlayerScore(playerName, objective)) {
            return 0;
        } else {
            Score score = scoreboard.getOrCreatePlayerScore(playerName, objective);
            return score.getScore();
        }
    }
    public static int findScoreboardOption(String objectiveName){
        if(SCOREBOARD_DISPLAY_OPTIONS.isEmpty()){
            return -1;
        }
        for (int i = 0; i < SCOREBOARD_DISPLAY_OPTIONS.size(); i++) {
            if (SCOREBOARD_DISPLAY_OPTIONS.get(i).getObjectiveName().equals(objectiveName)) {
                return i;
            }
        }
        return -1;
    }
    public static Tag getData(CommandContext<CommandSourceStack> commandContext,String resourceLocation, String path) throws CommandSyntaxException {
        Collection<Tag> tags = NbtPathArgument.getPath(commandContext,path).get(commandContext.getSource().getServer().getCommandStorage().get(ResourceLocationArgument.getId(commandContext,resourceLocation)));
        Iterator<Tag> iterator = tags.iterator();
        return iterator.next();
    }
    public static void setData(CommandContext<CommandSourceStack> commandContext,String sourceResourceLocation, String sourcePath,Tag tag) throws CommandSyntaxException {
        NbtPathArgument.getPath(commandContext,sourcePath).set(commandContext.getSource().getServer().getCommandStorage().get(ResourceLocationArgument.getId(commandContext, sourceResourceLocation)), () -> tag);
    }
    public static void sendTCdFeedback(CommandContext<net.minecraft.commands.CommandSourceStack> commandContext, String key,String ...args){
        commandContext.getSource().sendSuccess(new TranslatableComponent(key,args), false);
    }
    public static void sendTCdFeedback(CommandContext<net.minecraft.commands.CommandSourceStack> commandContext, String key, Boolean aborted,String ...args){
        if(!aborted){
            commandContext.getSource().sendSuccess(new TranslatableComponent(key,args), false);
        }else{
            commandContext.getSource().sendFailure(new TranslatableComponent(key,args));
        }
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
