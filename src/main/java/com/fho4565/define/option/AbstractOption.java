package com.fho4565.define.option;

public abstract class AbstractOption {
    public enum OptionType {
        ScoreboardDisplayOption,
        RenderableOption
    }
    public static final String OPTION_SEPARATOR = "=-=";
    public abstract String toSaveable();
}
