package com.fho4565.commands;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;


public class helpMcd {
    private static final TextComponent help = new TextComponent(
            """
                    §6/hurt§d————§b对实体造成伤害
                    §6/string§d————§b关于字符串的修改
                    §6/helpMcd§d————§b获取模组命令介绍
                    §6/explode§d————§b创造爆炸
                    §6/math§d————§b数学运算
                    §6/player§d————§b获取或更改玩家能力
                    §6/random§d————§b获取随机数
                    §6/realityTime§d————§b获取现实时间
                    §6/ride§d————§b骑乘实体
                    §6/run§d————§b获取命令存储中字符串并将其当作命令执行
                    §6/world§d————§b获取世界信息
                    §2命令的具体用法还请使用/help [<命令名称>]获取"""
    );
    public static void register()  {
        CommandRegister.dispatcher.register(
                Commands.literal("helpMcd").executes(context -> {
                    context.getSource().sendSuccess(help, false);
                    return 1;
                })
        );
    }

}
