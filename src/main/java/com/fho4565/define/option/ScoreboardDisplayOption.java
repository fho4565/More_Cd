package com.fho4565.define.option;

public class ScoreboardDisplayOption extends AbstractOption {
    private String objectiveName;
    private int displayX;
    private int displayY;
    private boolean visible = true;
    private String note = "";

    public ScoreboardDisplayOption(String name, int displayX, int displayY, String note) {
        this.objectiveName = name;
        this.displayX = displayX;
        this.displayY = displayY;
        this.note = note;
    }

    public ScoreboardDisplayOption(String objectiveName, int displayX, int displayY, String note, boolean visible) {
        this.objectiveName = objectiveName;
        this.displayX = displayX;
        this.displayY = displayY;
        this.visible = visible;
        this.note = note;
    }

    public ScoreboardDisplayOption(String name, int displayX, int displayY) {
        this.objectiveName = name;
        this.displayX = displayX;
        this.displayY = displayY;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getObjectiveName() {
        return objectiveName;
    }

    public void setObjective(String name) {
        this.objectiveName = name;
    }

    public int getDisplayX() {
        return displayX;
    }

    public void setDisplayX(int displayX) {
        this.displayX = displayX;
    }

    public int getDisplayY() {
        return displayY;
    }

    public void setDisplayY(int displayY) {
        this.displayY = displayY;
    }

    @Override
    public String toSaveable() {
        return "ScoreboardDisplayOption" + OPTION_SEPARATOR +
                this.objectiveName +
                OPTION_SEPARATOR +
                this.displayX +
                OPTION_SEPARATOR +
                this.displayY +
                OPTION_SEPARATOR +
                this.note +
                OPTION_SEPARATOR +
                this.visible;
    }
}