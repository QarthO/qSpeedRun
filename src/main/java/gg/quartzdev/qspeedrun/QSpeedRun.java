package gg.quartzdev.qspeedrun;

import gg.quartzdev.qspeedrun.util.QPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class QSpeedRun extends JavaPlugin {

    @Override
    public void onEnable() {
        QPlugin.enable(this, true, -1);
    }

    @Override
    public void onDisable() {
        QPlugin.disable();
    }
}