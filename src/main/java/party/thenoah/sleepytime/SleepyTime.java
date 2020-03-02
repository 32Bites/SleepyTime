package party.thenoah.sleepytime;

import org.bukkit.plugin.java.JavaPlugin;

public final class SleepyTime extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerEventListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
