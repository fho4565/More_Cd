package com.fho4565.helpGUI;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HelpScreen extends AbstractContainerScreen<HelpMenu> {
    private static final ResourceLocation texture = new ResourceLocation("more_cd:textures/screens/help_screen.png");
    private final int x, y, z;
    private final Player entity;
    Button left;
    Button right;
    static int index = 1;
    static final int maxPage = 11;

    public HelpScreen(HelpMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 350;
        this.imageHeight = 200;
        left = new Button(this.leftPos, this.topPos, 30, 20, new TextComponent("left"), e -> TurnButton.handleButtonAction(entity, 0, x, y, z));
        right = new Button(this.leftPos, this.topPos+20, 30, 20, new TextComponent("right"), e -> TurnButton.handleButtonAction(entity, 1, x, y, z));
    }
    @Override
    public boolean isPauseScreen() {
        return true;
    }

    @Override
    public void render(@NotNull PoseStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        this.renderTooltip(ms, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull PoseStack ms, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, texture);
        blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        RenderSystem.disableBlend();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            Objects.requireNonNull(Objects.requireNonNull(this.minecraft).player).closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    public void containerTick() {
        super.containerTick();
    }

    @Override
    protected void renderLabels(@NotNull PoseStack poseStack, int mouseX, int mouseY) {
        this.font.draw(poseStack, "第"+index+"页/"+maxPage, 5, 5, -12829636);
        ArrayList<TranslatableComponent> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            TranslatableComponent t = new TranslatableComponent("mcd.com.fho4565.help.page" + index + ".line" + i);
            if (t.getString().equals(t.getKey())){
                break;
            }
            list.add(t);
        }
        MultiLineLabel.create(Minecraft.getInstance().font, List.copyOf(list)).renderLeftAlignedNoShadow(poseStack, 5, 15,10,0);
    }
    @Override
    public void onClose() {
        super.onClose();
        Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
    }

    @Override
    public void init() {
        super.init();
        Objects.requireNonNull(this.minecraft).keyboardHandler.setSendRepeatsToGui(true);
        this.addRenderableWidget(left);
        this.addRenderableWidget(right);
    }
}