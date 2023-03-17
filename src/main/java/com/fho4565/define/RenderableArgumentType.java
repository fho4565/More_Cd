package com.fho4565.define;

import com.fho4565.main.Utils;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

public class RenderableArgumentType implements ArgumentType<String> {
    public static final DynamicCommandExceptionType ERROR_INVALID_VALUE = new DynamicCommandExceptionType((p_85470_) -> new TextComponent("unknown argument"));
    String[] suggests = new String[]{"rectangle","line","circle","string"};
    private RenderableArgumentType() {
    }
    public static RenderableArgumentType renderable() {
        return new RenderableArgumentType();
    }
    public static String getRenderable(final CommandContext<?> context, final String name) {
        return context.getArgument(name, String.class);
    }
    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        if(Arrays.stream(suggests).anyMatch(s -> reader.readUnquotedString().equals(s))){
            return reader.readString();
        }
        throw ERROR_INVALID_VALUE.create(reader.readUnquotedString());
    }
    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return Utils.createCommandSuggestion(suggests, builder);
    }

}
