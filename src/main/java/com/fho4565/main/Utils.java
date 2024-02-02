package com.fho4565.main;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.Scoreboard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;

public class Utils {
    public static final String MODID = "more_cd";
    public static Logger logger = LogManager.getLogger();

    /**
     * @param playerName 玩家名字
     * @param objective  要获取的计分板名字
     */
    public static int getScore(MinecraftServer server, String playerName, Objective objective) {
        Scoreboard scoreboard = server.getScoreboard();
        if (!scoreboard.hasPlayerScore(playerName, objective)) {
            return 0;
        } else {
            Score score = scoreboard.getOrCreatePlayerScore(playerName, objective);
            return score.getScore();
        }
    }

    public static Tag getData(CommandContext<CommandSourceStack> commandContext, String resourceLocation, String path) throws CommandSyntaxException {
        Collection<Tag> tags = NbtPathArgument.getPath(commandContext, path)
                .get(commandContext.getSource().getServer().getCommandStorage()
                        .get(ResourceLocationArgument.getId(commandContext, resourceLocation)));
        Iterator<Tag> iterator = tags.iterator();
        return iterator.next();
    }

    public static void setData(CommandContext<CommandSourceStack> commandContext, String sourceResourceLocation, String sourcePath, Tag tag) throws CommandSyntaxException {
        NbtPathArgument.getPath(commandContext, sourcePath).set(commandContext.getSource().getServer().getCommandStorage().get(ResourceLocationArgument.getId(commandContext, sourceResourceLocation)), () -> tag);
    }

    public static void sendTCdFeedback(CommandContext<net.minecraft.commands.CommandSourceStack> commandContext, String key, String... strings) {
        commandContext.getSource().sendSuccess(new TranslatableComponent(key, (Object[]) strings), false);
    }

    public static void sendTCdFeedback(CommandContext<net.minecraft.commands.CommandSourceStack> commandContext, String key, Boolean aborted, String... strings) {
        if (!aborted) {
            commandContext.getSource().sendSuccess(new TranslatableComponent(key, (Object[]) strings), false);
        } else {
            commandContext.getSource().sendFailure(new TranslatableComponent(key, (Object[]) strings));
        }
    }

    public static void sendCdFeedback(CommandContext<net.minecraft.commands.CommandSourceStack> commandContext, String content) {
        commandContext.getSource().sendSuccess(new TextComponent(content), false);
    }

    public static CompletableFuture<Suggestions> getCommandSuggestions(SuggestionsBuilder builder, String[] string) {
        for (String s:string) builder.suggest(s);
        return builder.buildFuture();
    }
    public static void sendCdFeedback(CommandContext<net.minecraft.commands.CommandSourceStack> commandContext, String content, Boolean aborted) {
        if (!aborted) {
            commandContext.getSource().sendSuccess(new TextComponent(content), false);
        } else {
            commandContext.getSource().sendFailure(new TextComponent(content));
        }
    }
    public static void executeCommand(ServerPlayer player, String command) {
        CommandSourceStack commandSourceStack = player.createCommandSourceStack();
        player.getServer().getCommands().performCommand(commandSourceStack,command);
    }


}
