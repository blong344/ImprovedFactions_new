package io.github.toberocat.improvedfactions.spigot.gui.settings;

public class GuiSettings {
    private boolean pageArrows;
    private Runnable quitGui;

    public GuiSettings() {
        pageArrows = false;
        quitGui = null;
    }

    public boolean isPageArrows() {
        return pageArrows;
    }

    public Runnable getQuitGui() {
        return quitGui;
    }

    public GuiSettings setPageArrows(boolean pageArrows) {
        this.pageArrows = pageArrows;
        return this;
    }

    public GuiSettings setQuitGui(Runnable quitGui) {
        this.quitGui = quitGui;
        return this;
    }
}
