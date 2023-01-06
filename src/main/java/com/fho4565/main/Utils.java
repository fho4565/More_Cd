package com.fho4565.main;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
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
    public static int getScore(CommandSourceStack commandSourceStack, String playerName, Objective objective) throws CommandSyntaxException {
        Scoreboard scoreboard = commandSourceStack.getServer().getScoreboard();
        if (!scoreboard.hasPlayerScore(playerName, objective)) {
            return 0;
        } else {
            Score score = scoreboard.getOrCreatePlayerScore(playerName, objective);
            return score.getScore();
        }
    }
//    public static void register(CommandDispatcher<CommandSourceStack> p_138469_) {
//        p_138469_.register(Commands.literal("scoreboard").requires((p_138552_) -> {
//            return p_138552_.hasPermission(2);
//        })
//                .then(Commands.literal("objectives")
//                        .then(Commands.literal("list").executes((p_138585_) -> {
//            return listObjectives(p_138585_.getSource());
//        }))
//                        .then(Commands.literal("add")
//                                .then(Commands.argument("objective", StringArgumentType.word())
//                                        .then(Commands.argument("criteria", ObjectiveCriteriaArgument.criteria()).executes((p_138583_) -> {
//            return addObjective(p_138583_.getSource(), StringArgumentType.getString(p_138583_, "objective"), ObjectiveCriteriaArgument.getCriteria(p_138583_, "criteria"), new TextComponent(StringArgumentType.getString(p_138583_, "objective")));
//        })
//                                                .then(Commands.argument("displayName", ComponentArgument.textComponent()).executes((p_138581_) -> {
//            return addObjective(p_138581_.getSource(), StringArgumentType.getString(p_138581_, "objective"), ObjectiveCriteriaArgument.getCriteria(p_138581_, "criteria"), ComponentArgument.getComponent(p_138581_, "displayName"));
//        })))))
//                        .then(Commands.literal("modify")
//                                .then(Commands.argument("objective", ObjectiveArgument.objective())
//                                        .then(Commands.literal("displayname")
//                                                .then(Commands.argument("displayName", ComponentArgument.textComponent()).executes((p_138579_) -> {
//            return setDisplayName(p_138579_.getSource(), ObjectiveArgument.getObjective(p_138579_, "objective"), ComponentArgument.getComponent(p_138579_, "displayName"));
//        })))
//                                        .then(createRenderTypeModify())))
//                        .then(Commands.literal("remove")
//                                .then(Commands.argument("objective", ObjectiveArgument.objective()).executes((p_138577_) -> {
//            return removeObjective(p_138577_.getSource(), ObjectiveArgument.getObjective(p_138577_, "objective"));
//        })))
//                        .then(Commands.literal("setdisplay")
//                                .then(Commands.argument("slot", ScoreboardSlotArgument.displaySlot()).executes((p_138575_) -> {
//            return clearDisplaySlot(p_138575_.getSource(), ScoreboardSlotArgument.getDisplaySlot(p_138575_, "slot"));
//        })
//                                        .then(Commands.argument("objective", ObjectiveArgument.objective()).executes((p_138573_) -> {
//            return setDisplaySlot(p_138573_.getSource(), ScoreboardSlotArgument.getDisplaySlot(p_138573_, "slot"), ObjectiveArgument.getObjective(p_138573_, "objective"));
//        })))))
//                .then(Commands.literal("players")
//                        .then(Commands.literal("list").executes((p_138571_) -> {
//            return listTrackedPlayers(p_138571_.getSource());
//        })
//                                .then(Commands.argument("target", ScoreHolderArgument.scoreHolder()).suggests(ScoreHolderArgument.SUGGEST_SCORE_HOLDERS).executes((p_138569_) -> {
//            return listTrackedPlayerScores(p_138569_.getSource(), ScoreHolderArgument.getName(p_138569_, "target"));
//        })))
//                        .then(Commands.literal("set")
//                                .then(Commands.argument("targets", ScoreHolderArgument.scoreHolders()).suggests(ScoreHolderArgument.SUGGEST_SCORE_HOLDERS)
//                                        .then(Commands.argument("objective", ObjectiveArgument.objective())
//                                                .then(Commands.argument("score", IntegerArgumentType.integer()).executes((p_138567_) -> {
//            return setScore(p_138567_.getSource(), ScoreHolderArgument.getNamesWithDefaultWildcard(p_138567_, "targets"), ObjectiveArgument.getWritableObjective(p_138567_, "objective"), IntegerArgumentType.getInteger(p_138567_, "score"));
//        })))))
//                        .then(Commands.literal("get")
//                                .then(Commands.argument("target", ScoreHolderArgument.scoreHolder()).suggests(ScoreHolderArgument.SUGGEST_SCORE_HOLDERS)
//                                        .then(Commands.argument("objective", ObjectiveArgument.objective()).executes((p_138565_) -> {
//            return getScore(p_138565_.getSource(), ScoreHolderArgument.getName(p_138565_, "target"), ObjectiveArgument.getObjective(p_138565_, "objective"));
//        }))))
//                        .then(Commands.literal("add")
//                                .then(Commands.argument("targets", ScoreHolderArgument.scoreHolders()).suggests(ScoreHolderArgument.SUGGEST_SCORE_HOLDERS)
//                                        .then(Commands.argument("objective", ObjectiveArgument.objective())
//                                                .then(Commands.argument("score", IntegerArgumentType.integer(0)).executes((p_138563_) -> {
//            return addScore(p_138563_.getSource(), ScoreHolderArgument.getNamesWithDefaultWildcard(p_138563_, "targets"), ObjectiveArgument.getWritableObjective(p_138563_, "objective"), IntegerArgumentType.getInteger(p_138563_, "score"));
//        })))))
//                        .then(Commands.literal("remove")
//                                .then(Commands.argument("targets", ScoreHolderArgument.scoreHolders()).suggests(ScoreHolderArgument.SUGGEST_SCORE_HOLDERS)
//                                        .then(Commands.argument("objective", ObjectiveArgument.objective())
//                                                .then(Commands.argument("score", IntegerArgumentType.integer(0)).executes((p_138561_) -> {
//            return removeScore(p_138561_.getSource(), ScoreHolderArgument.getNamesWithDefaultWildcard(p_138561_, "targets"), ObjectiveArgument.getWritableObjective(p_138561_, "objective"), IntegerArgumentType.getInteger(p_138561_, "score"));
//        })))))
//                        .then(Commands.literal("reset")
//                                .then(Commands.argument("targets", ScoreHolderArgument.scoreHolders()).suggests(ScoreHolderArgument.SUGGEST_SCORE_HOLDERS).executes((p_138559_) -> {
//            return resetScores(p_138559_.getSource(), ScoreHolderArgument.getNamesWithDefaultWildcard(p_138559_, "targets"));
//        })
//                                        .then(Commands.argument("objective", ObjectiveArgument.objective()).executes((p_138550_) -> {
//            return resetScore(p_138550_.getSource(), ScoreHolderArgument.getNamesWithDefaultWildcard(p_138550_, "targets"), ObjectiveArgument.getObjective(p_138550_, "objective"));
//        }))))
//                        .then(Commands.literal("enable")
//                                .then(Commands.argument("targets", ScoreHolderArgument.scoreHolders()).suggests(ScoreHolderArgument.SUGGEST_SCORE_HOLDERS)
//                                        .then(Commands.argument("objective", ObjectiveArgument.objective()).suggests((p_138473_, p_138474_) -> {
//            return suggestTriggers(p_138473_.getSource(), ScoreHolderArgument.getNamesWithDefaultWildcard(p_138473_, "targets"), p_138474_);
//        }).executes((p_138537_) -> {
//            return enableTrigger(p_138537_.getSource(), ScoreHolderArgument.getNamesWithDefaultWildcard(p_138537_, "targets"), ObjectiveArgument.getObjective(p_138537_, "objective"));
//        }))))
//                        .then(Commands.literal("operation")
//                                .then(Commands.argument("targets", ScoreHolderArgument.scoreHolders()).suggests(ScoreHolderArgument.SUGGEST_SCORE_HOLDERS)
//                                        .then(Commands.argument("targetObjective", ObjectiveArgument.objective())
//                                                .then(Commands.argument("operation", OperationArgument.operation())
//                                                        .then(Commands.argument("source", ScoreHolderArgument.scoreHolders()).suggests(ScoreHolderArgument.SUGGEST_SCORE_HOLDERS)
//                                                                .then(Commands.argument("sourceObjective", ObjectiveArgument.objective()).executes((p_138471_) -> {
//            return performOperation(p_138471_.getSource(), ScoreHolderArgument.getNamesWithDefaultWildcard(p_138471_, "targets"), ObjectiveArgument.getWritableObjective(p_138471_, "targetObjective"), OperationArgument.getOperation(p_138471_, "operation"), ScoreHolderArgument.getNamesWithDefaultWildcard(p_138471_, "source"), ObjectiveArgument.getObjective(p_138471_, "sourceObjective"));
//        })))))))));
//    }
}
