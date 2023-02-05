package com.fho4565.overlay;

import com.fho4565.define.ScoreboardDisplayOption;
import com.fho4565.main.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class scoreboardOverlay {
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void eventHandler(RenderGameOverlayEvent.Pre event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            Player player = Minecraft.getInstance().player;
            IntegratedServer server = Minecraft.getInstance().getSingleplayerServer();
            if(player != null && server != null && Utils.SCOREBOARD_DISPLAY_OPTIONS.stream().noneMatch(Objects::isNull)){
                for (ScoreboardDisplayOption s : Utils.SCOREBOARD_DISPLAY_OPTIONS) {
                    if(s.isVisible()){
                        Minecraft.getInstance().font.draw(event.getMatrixStack(), s.getNote() + Utils.getScore(server, player.getName().getString(), server.getScoreboard().getObjective(s.getObjectiveName())), s.getDisplayX(), s.getDisplayY(), -1);
                    }
                }
            }
        }
    }
}
