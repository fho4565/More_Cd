package com.fho4565.commands;

import com.fho4565.define.ScoreboardDisplayOption;
import com.fho4565.main.Utils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ObjectiveArgument;
import org.jetbrains.annotations.NotNull;

import static com.fho4565.main.Utils.SCOREBOARD_DISPLAY_OPTIONS;

public class ScoreboardDisplayer {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("scoreboardDisplayer").requires(s -> s.hasPermission(2))
                        .then(Commands.literal("list").executes(listAllScoreboardDisplay()))
                        .then(Commands.argument("objective", ObjectiveArgument.objective())
                                .then(Commands.literal("set")
                                        .then(Commands.literal("position")
                                                .then(Commands.argument("posX",IntegerArgumentType.integer()).executes(ScoreboardDisplayer::setX)
                                                        .then(Commands.argument("posY",IntegerArgumentType.integer()).executes(ScoreboardDisplayer::setXY))))
                                        .then(Commands.literal("note").then(Commands.argument("note",StringArgumentType.string()).executes(ScoreboardDisplayer::setNote)))
                                        .then(Commands.literal("visible").then(Commands.argument("visible",BoolArgumentType.bool()).executes(ScoreboardDisplayer::setVisible)))
                                )
                                .then(Commands.literal("get")
                                        .then(Commands.literal("x").executes(ScoreboardDisplayer::getX))
                                        .then(Commands.literal("y").executes(ScoreboardDisplayer::getY))
                                        .then(Commands.literal("visible").executes(ScoreboardDisplayer::getVisible))
                                        .then(Commands.literal("note").executes(ScoreboardDisplayer::getNote)))
                        ));
    }

    @NotNull
    private static Command<CommandSourceStack> listAllScoreboardDisplay() {
        return context -> {
            SCOREBOARD_DISPLAY_OPTIONS.forEach(option -> {
                String objectiveName = option.getObjectiveName();
                int displayX = option.getDisplayX();
                int displayY = option.getDisplayY();
                boolean visible = option.isVisible();
                Utils.sendTCdFeedback(context,
                        "mcd.com.fho4565.command.scoreboardDisplayer.query.single." + (visible ? "visible" : "invisible"),
                        objectiveName,
                        String.valueOf(displayX),
                        String.valueOf(displayY)
                );
            });
            return 1;
        };
    }

    private static int setX(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String objectiveName = ObjectiveArgument.getObjective(context, "objective").getName();
        int index = Utils.findScoreboardOption(objectiveName);
        int displayX = IntegerArgumentType.getInteger(context, "posX");
        if (index != -1) {
            int displayY = SCOREBOARD_DISPLAY_OPTIONS.get(index).getDisplayY();
            String note = SCOREBOARD_DISPLAY_OPTIONS.get(index).getNote();
            boolean visible = SCOREBOARD_DISPLAY_OPTIONS.get(index).isVisible();
            SCOREBOARD_DISPLAY_OPTIONS.set(index, new ScoreboardDisplayOption(objectiveName, displayX, displayY, note, visible));
            Utils.sendTCdFeedback(context,
                    "mcd.com.fho4565.command.scoreboardDisplayer.change.x",
                    objectiveName,
                    String.valueOf(displayX),
                    String.valueOf(displayY)
            );
        } else {
            SCOREBOARD_DISPLAY_OPTIONS.add(new ScoreboardDisplayOption(objectiveName, displayX, 0));
            Utils.sendTCdFeedback(context,
                    "mcd.com.fho4565.command.scoreboardDisplayer.change.x",
                    objectiveName,
                    String.valueOf(displayX),
                    String.valueOf(0)
            );
        }

        return 1;
    }

    private static int setXY(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String objectiveName = ObjectiveArgument.getObjective(context, "objective").getName();
        int index = Utils.findScoreboardOption(objectiveName);
        int displayX = IntegerArgumentType.getInteger(context, "posX");
        int displayY = IntegerArgumentType.getInteger(context, "posY");
        if (index != -1) {
            String note = SCOREBOARD_DISPLAY_OPTIONS.get(index).getNote();
            boolean visible = SCOREBOARD_DISPLAY_OPTIONS.get(index).isVisible();
            SCOREBOARD_DISPLAY_OPTIONS.set(index, new ScoreboardDisplayOption(objectiveName, displayX, displayY, note, visible));
        } else {
            SCOREBOARD_DISPLAY_OPTIONS.add(new ScoreboardDisplayOption(objectiveName, displayX, displayY));
        }
        Utils.sendTCdFeedback(context,
                "mcd.com.fho4565.command.scoreboardDisplayer.change.x",
                objectiveName,
                String.valueOf(displayX),
                String.valueOf(displayY)
        );
        return 1;
    }

    private static int setXYNote(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String objectiveName = ObjectiveArgument.getObjective(context, "objective").getName();
        int index = Utils.findScoreboardOption(objectiveName);
        int displayX = IntegerArgumentType.getInteger(context, "posX");
        int displayY = IntegerArgumentType.getInteger(context, "posY");
        String note = StringArgumentType.getString(context, "note");
        if (index != -1) {
            SCOREBOARD_DISPLAY_OPTIONS.set(index, new ScoreboardDisplayOption(objectiveName, displayX, displayY, note));
        } else {
            SCOREBOARD_DISPLAY_OPTIONS.add(
                    new ScoreboardDisplayOption(objectiveName, displayX, displayY, note)
            );
        }
        Utils.sendTCdFeedback(context,
                "mcd.com.fho4565.command.scoreboardDisplayer.change.x",
                objectiveName,
                String.valueOf(displayX),
                String.valueOf(displayY)
        );
        return 1;
    }

    private static int setVisible(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String objectiveName = ObjectiveArgument.getObjective(context, "objective").getName();
        int index = Utils.findScoreboardOption(objectiveName);
        boolean visible = BoolArgumentType.getBool(context, "visible");
        if (index != -1) {
            int displayX = SCOREBOARD_DISPLAY_OPTIONS.get(index).getDisplayX();
            int displayY = SCOREBOARD_DISPLAY_OPTIONS.get(index).getDisplayY();
            String note = SCOREBOARD_DISPLAY_OPTIONS.get(index).getNote();
            SCOREBOARD_DISPLAY_OPTIONS.set(index, new ScoreboardDisplayOption(objectiveName, displayX, displayY, note, visible));
            Utils.sendTCdFeedback(context,
                    "mcd.com.fho4565.command.scoreboardDisplayer.change."+(visible?"visible":"invisible"),
                    objectiveName
            );
        } else {
            SCOREBOARD_DISPLAY_OPTIONS.add(new ScoreboardDisplayOption(objectiveName, 0, 0, "", visible));
            Utils.sendTCdFeedback(context,
                    "mcd.com.fho4565.command.scoreboardDisplayer.change."+(visible?"visible":"invisible"),
                    objectiveName
            );
        }
        return 1;
    }

    private static int setNote(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String objectiveName = ObjectiveArgument.getObjective(context, "objective").getName();
        int index = Utils.findScoreboardOption(objectiveName);
        String note = StringArgumentType.getString(context, "note");
        if (index != -1) {
            int displayX = SCOREBOARD_DISPLAY_OPTIONS.get(index).getDisplayX();
            int displayY = SCOREBOARD_DISPLAY_OPTIONS.get(index).getDisplayY();
            boolean visible = SCOREBOARD_DISPLAY_OPTIONS.get(index).isVisible();
            SCOREBOARD_DISPLAY_OPTIONS.set(index, new ScoreboardDisplayOption(objectiveName, displayX, displayY, note, visible));
        } else {
            SCOREBOARD_DISPLAY_OPTIONS.add(new ScoreboardDisplayOption(objectiveName, 0, 0, note));
        }
        Utils.sendTCdFeedback(context,
                "mcd.com.fho4565.command.scoreboardDisplayer.change.note",
                objectiveName,
                note
        );
        return 1;
    }


    private static int getX(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String objectiveName = ObjectiveArgument.getObjective(context, "objective").getName();
        int index = Utils.findScoreboardOption(objectiveName);
        if (index != -1) {
            Utils.sendTCdFeedback(context,
                    "mcd.com.fho4565.command.scoreboardDisplayer.query.x",
                    objectiveName,
                    String.valueOf(SCOREBOARD_DISPLAY_OPTIONS.get(index).getDisplayX())
            );
            return SCOREBOARD_DISPLAY_OPTIONS.get(index).getDisplayX();
        }
        return 0;
    }

    private static int getY(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String objectiveName = ObjectiveArgument.getObjective(context, "objective").getName();
        int index = Utils.findScoreboardOption(objectiveName);
        if (index != -1) {
            Utils.sendTCdFeedback(context,
                    "mcd.com.fho4565.command.scoreboardDisplayer.query.y",
                    objectiveName,
                    String.valueOf(SCOREBOARD_DISPLAY_OPTIONS.get(index).getDisplayY())
            );
            return SCOREBOARD_DISPLAY_OPTIONS.get(index).getDisplayY();
        }
        return 0;
    }

    private static int getNote(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String objectiveName = ObjectiveArgument.getObjective(context, "objective").getName();
        int index = Utils.findScoreboardOption(objectiveName);
        if (index != -1) {
            Utils.sendTCdFeedback(context,
                    "mcd.com.fho4565.command.scoreboardDisplayer.query.note",
                    objectiveName,
                    String.valueOf(SCOREBOARD_DISPLAY_OPTIONS.get(index).getNote())
            );
            return SCOREBOARD_DISPLAY_OPTIONS.get(index).getNote().length();
        }
        return 0;
    }

    private static int getVisible(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String objectiveName = ObjectiveArgument.getObjective(context, "objective").getName();
        int index = Utils.findScoreboardOption(objectiveName);
        if (index != -1) {
            Utils.sendTCdFeedback(context,
                    "mcd.com.fho4565.command.scoreboardDisplayer.query.visible."+((SCOREBOARD_DISPLAY_OPTIONS.get(index).isVisible())?"visible":"invisible"),
                    objectiveName
            );
            return (SCOREBOARD_DISPLAY_OPTIONS.get(index).isVisible()) ? 1:0;
        }
        return 0;
    }
}
