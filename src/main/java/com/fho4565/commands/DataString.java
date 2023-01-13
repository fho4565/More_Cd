package com.fho4565.commands;

import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;

import java.util.regex.Pattern;


public class DataString {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("string").requires(s -> s.hasPermission(2))
                        .then(Commands.argument("sourceTarget", ResourceLocationArgument.id())
                                .then(Commands.argument("sourcePath", NbtPathArgument.nbtPath())
                                        .then(Commands.literal("equals").then(Commands.argument("targetTarget", ResourceLocationArgument.id()).then(Commands.argument("targetPath", NbtPathArgument.nbtPath()).executes(context -> {
                                            String str = Utils.getStringData(context, "sourceTarget", "sourcePath");
                                            String str_ = Utils.getStringData(context, "targetTarget", "targetPath");
                                            if(str.equals(str_)){
                                                Utils.sendCdFeedback(context, "匹配成功");
                                                return 1;
                                            } else {
                                                Utils.sendCdFeedback(context, "匹配失败", true);
                                                return 0;
                                            }
                                        }))))
                                        .then(Commands.literal("concat").then(Commands.argument("anotherString", StringArgumentType.string()).then(Commands.argument("targetTarget", ResourceLocationArgument.id()).then(Commands.argument("targetPath", NbtPathArgument.nbtPath()).executes(context -> {
                                            String string = Utils.getStringData(context, "sourceTarget", "sourcePath");
                                            String another = StringArgumentType.getString(context, "anotherString");
                                            Utils.setData(context, "targetTarget", "targetPath", StringTag.valueOf(string.concat(another)));
                                            Utils.sendCdFeedback(context, "拼接成功");
                                            return 1;
                                        })))))
                                        .then(Commands.literal("startWith").then(Commands.argument("anotherString", StringArgumentType.string()).executes(context -> {
                                            String string = Utils.getStringData(context, "sourceTarget", "sourcePath");
                                            String another = StringArgumentType.getString(context, "anotherString");
                                            if (string.startsWith(another)) {
                                                Utils.sendCdFeedback(context, "匹配成功");
                                                return 1;
                                            } else {
                                                Utils.sendCdFeedback(context, "匹配失败", true);
                                                return 0;
                                            }
                                        })))
                                        .then(Commands.literal("endWith").then(Commands.argument("anotherString", StringArgumentType.string()).executes(context -> {
                                            String string = Utils.getStringData(context, "sourceTarget", "sourcePath");
                                            String another = StringArgumentType.getString(context, "anotherString");
                                            if (string.endsWith(another)) {
                                                Utils.sendCdFeedback(context, "匹配成功");
                                                return 1;
                                            } else {
                                                Utils.sendCdFeedback(context, "匹配失败", true);
                                                return 0;
                                            }
                                        })))
                                        .then(Commands.literal("contains").then(Commands.argument("anotherString", StringArgumentType.string()).executes(context -> {
                                            String string = Utils.getStringData(context, "sourceTarget", "sourcePath");
                                            String another = StringArgumentType.getString(context, "anotherString");
                                            if (string.contains(another)) {
                                                Utils.sendCdFeedback(context, "匹配成功");
                                                return 1;
                                            } else {
                                                Utils.sendCdFeedback(context, "匹配失败", true);
                                                return 0;
                                            }
                                        })))
                                        .then(Commands.literal("match").then(Commands.argument("regularExpression", StringArgumentType.string()).executes(context -> {
                                            String line = Utils.getStringData(context, "sourceTarget", "sourcePath");
                                            String regex = StringArgumentType.getString(context, "regularExpression");
                                            if (Pattern.compile(regex).matcher(line).find()) {
                                                Utils.sendCdFeedback(context, "匹配成功");
                                                return 1;
                                            } else {
                                                Utils.sendCdFeedback(context, "匹配失败", true);
                                                return 0;
                                            }
                                        })))
                                        .then(Commands.literal("splitToArray").then(Commands.argument("targetTarget", ResourceLocationArgument.id()).then(Commands.argument("targetPath", NbtPathArgument.nbtPath()).executes(context -> {
                                                            ListTag tags = new ListTag();
                                                            char[] chars = Utils.getStringData(context, "sourceTarget", "sourcePath").toCharArray();
                                                            for (Character c : chars) {
                                                                tags.add(StringTag.valueOf(String.valueOf(c)));
                                                            }
                                                            Utils.setData(context, "targetTarget", "targetPath", tags);
                                                            Utils.sendCdFeedback(context, "字符串已分割");
                                                            return 1;
                                                        }))))

                                )));
    }

}
