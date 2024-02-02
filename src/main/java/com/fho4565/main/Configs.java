package com.fho4565.main;

import net.minecraftforge.common.ForgeConfigSpec;

public class Configs {
    public static final ForgeConfigSpec.ConfigValue<String> keycd1;
    public static final ForgeConfigSpec.ConfigValue<String> keycd2;
    public static final ForgeConfigSpec.ConfigValue<String> keycd3;
    public static final ForgeConfigSpec.ConfigValue<String> keycd4;
    public static final ForgeConfigSpec.ConfigValue<String> keycd5;
    public static final ForgeConfigSpec.ConfigValue<String> keycd6;
    public static final ForgeConfigSpec COMMON_CONFIG;
    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("KeyBoard commands").push("keyCd");
        keycd1 = COMMON_BUILDER.define("cd1","/say key1");
        keycd2 = COMMON_BUILDER.define("cd2","/say key2");
        keycd3 = COMMON_BUILDER.define("cd3","/say key3");
        keycd4 = COMMON_BUILDER.define("cd4","/say key4");
        keycd5 = COMMON_BUILDER.define("cd5","/say key5");
        keycd6 = COMMON_BUILDER.define("cd6","/say key6");
        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
