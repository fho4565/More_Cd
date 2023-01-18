package com.fho4565.commands;

import com.fho4565.main.Utils;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataString {
    public static void register() {
        CommandRegister.dispatcher.register(
                Commands.literal("string").requires(s -> s.hasPermission(2))
                        .then(Commands.argument("sourceTarget", ResourceLocationArgument.id())
                                .then(Commands.argument("sourcePath", NbtPathArgument.nbtPath())
                                        .then(Commands.literal("unicodeEncode").then(Commands.argument("targetTarget", ResourceLocationArgument.id()).then(Commands.argument("targetPath", NbtPathArgument.nbtPath()).executes(context -> {
                                            String str = Utils.getData(context, "sourceTarget", "sourcePath").getAsString();
                                                char[] utfBytes = str.toCharArray();
                                                StringBuilder unicodeBytes = new StringBuilder();
                                            for (char utfByte : utfBytes) {
                                                String hexB = Integer.toHexString(utfByte);
                                                if (hexB.length() <= 2) {
                                                    hexB = "00" + hexB;
                                                }
                                                unicodeBytes.append("\\u").append(hexB);
                                            }
                                            Utils.setData(context,"targetTarget", "targetPath",StringTag.valueOf(unicodeBytes.toString()));
                                                return 1;
                                        }))))
                                        .then(Commands.literal("unicodeDecode").then(Commands.argument("targetTarget", ResourceLocationArgument.id()).then(Commands.argument("targetPath", NbtPathArgument.nbtPath()).executes(context -> {
                                            String str = Utils.getData(context, "sourceTarget", "sourcePath").getAsString();
                                                Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
                                                Matcher matcher = pattern.matcher(str);
                                                char ch;
                                                while (matcher.find()) {
                                                    ch = (char) Integer.parseInt(matcher.group(2), 16);
                                                    str = str.replace(matcher.group(1), ch + "");
                                                }
                                            Utils.setData(context,"targetTarget", "targetPath",StringTag.valueOf(str));
                                            return 1;
                                        }))))
                                        .then(Commands.literal("equals").then(Commands.argument("targetTarget", ResourceLocationArgument.id()).then(Commands.argument("targetPath", NbtPathArgument.nbtPath()).executes(context -> {
                                            String str = Utils.getData(context, "sourceTarget", "sourcePath").getAsString();
                                            String str_ = Utils.getData(context, "targetTarget", "targetPath").getAsString();
                                            if(str.equals(str_)){
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.string.match.success");
                                                return 1;
                                            } else {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.string.match.failed", true);
                                                return 0;
                                            }
                                        }))))
                                        .then(Commands.literal("concat").then(Commands.argument("anotherString", StringArgumentType.string()).then(Commands.argument("targetTarget", ResourceLocationArgument.id()).then(Commands.argument("targetPath", NbtPathArgument.nbtPath()).executes(context -> {
                                            String string = Utils.getData(context, "sourceTarget", "sourcePath").getAsString();
                                            String another = StringArgumentType.getString(context, "anotherString");
                                            Utils.setData(context, "targetTarget", "targetPath", StringTag.valueOf(string.concat(another)));
                                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.string.concat.success");
                                            return 1;
                                        })))))
                                        .then(Commands.literal("startWith").then(Commands.argument("anotherString", StringArgumentType.string()).executes(context -> {
                                            String string = Utils.getData(context, "sourceTarget", "sourcePath").getAsString();
                                            String another = StringArgumentType.getString(context, "anotherString");
                                            if (string.startsWith(another)) {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.string.match.success");
                                                return 1;
                                            } else {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.string.match.failed", true);
                                                return 0;
                                            }
                                        })))
                                        .then(Commands.literal("endWith").then(Commands.argument("anotherString", StringArgumentType.string()).executes(context -> {
                                            String string = Utils.getData(context, "sourceTarget", "sourcePath").getAsString();
                                            String another = StringArgumentType.getString(context, "anotherString");
                                            if (string.endsWith(another)) {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.string.match.success");
                                                return 1;
                                            } else {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.string.match.failed", true);
                                                return 0;
                                            }
                                        })))
                                        .then(Commands.literal("contains").then(Commands.argument("anotherString", StringArgumentType.string()).executes(context -> {
                                            String string = Utils.getData(context, "sourceTarget", "sourcePath").getAsString();
                                            String another = StringArgumentType.getString(context, "anotherString");
                                            if (string.contains(another)) {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.string.match.success");
                                                return 1;
                                            } else {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.string.match.failed", true);
                                                return 0;
                                            }
                                        })))
                                        .then(Commands.literal("match").then(Commands.argument("regularExpression", StringArgumentType.string()).executes(context -> {
                                            String line = Utils.getData(context, "sourceTarget", "sourcePath").getAsString();
                                            String regex = StringArgumentType.getString(context, "regularExpression");
                                            if (Pattern.compile(regex).matcher(line).find()) {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.string.match.success");
                                                return 1;
                                            } else {
                                                Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.string.match.failed", true);
                                                return 0;
                                            }
                                        })))
                                        .then(Commands.literal("splitToArray").then(Commands.argument("targetTarget", ResourceLocationArgument.id()).then(Commands.argument("targetPath", NbtPathArgument.nbtPath()).executes(context -> {
                                                            ListTag tags = new ListTag();
                                                            char[] chars = Utils.getData(context, "sourceTarget", "sourcePath").getAsString().toCharArray();
                                                            for (Character c : chars) {
                                                                tags.add(StringTag.valueOf(String.valueOf(c)));
                                                            }
                                                            Utils.setData(context, "targetTarget", "targetPath", tags);
                                                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.string.splitToArray.success");
                                                            return 1;
                                                        }))))
                                        .then(Commands.literal("combineToString").then(Commands.argument("targetTarget", ResourceLocationArgument.id()).then(Commands.argument("targetPath", NbtPathArgument.nbtPath()).executes(context -> {
                                            ListTag chars = (ListTag) Utils.getData(context, "sourceTarget", "sourcePath");
                                            StringBuilder stringBuilder = new StringBuilder();
                                            chars.forEach(tag -> stringBuilder.append(tag.getAsString()));
                                            Utils.setData(context, "targetTarget", "targetPath", StringTag.valueOf(stringBuilder.toString()));
                                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.string.combineToString.success");
                                            return 1;
                                        }))))
                                        .then(Commands.literal("format").then(Commands.argument("targetTarget", ResourceLocationArgument.id()).then(Commands.argument("targetPath", NbtPathArgument.nbtPath()).then(Commands.argument("args",StringArgumentType.string()).executes(context -> {
                                            String data = Utils.getData(context, "sourceTarget", "sourcePath").getAsString();
                                            Object[] args = StringArgumentType.getString(context, "args").split(",");
                                            String formatted = String.format(data, args);
                                            Utils.setData(context, "targetTarget", "targetPath", StringTag.valueOf(formatted));
                                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.string.format.success");
                                            return 1;
                                        })))))
                                        .then(Commands.literal("toString").then(Commands.argument("targetTarget", ResourceLocationArgument.id()).then(Commands.argument("targetPath", NbtPathArgument.nbtPath()).executes(context -> {
                                            Utils.setData(context, "targetTarget", "targetPath", StringTag.valueOf(Utils.getData(context, "sourceTarget", "sourcePath").getAsString()));
                                            Utils.sendTCdFeedback(context, "mcd.com.fho4565.command.string.toString.success");
                                            return 1;
                                        }))))
                                )));
    }

}
