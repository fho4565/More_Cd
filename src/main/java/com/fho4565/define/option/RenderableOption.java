package com.fho4565.define.option;

public class RenderableOption extends AbstractOption{
    enum Type{
        none,
        rectangle,
        line,
    }
    RenderableOption.Type type = Type.none;
    int posX = 0;
    int posY = 0;
    boolean visible = true;

    public RenderableOption(Type type, int posX, int posY) {
        this.type = type;
        this.posX = posX;
        this.posY = posY;
    }

    public RenderableOption(Type type, int posX, int posY, boolean visible) {
        this.type = type;
        this.posX = posX;
        this.posY = posY;
        this.visible = visible;
    }

    @Override
    public String toSaveable() {
        return null;
    }
}
