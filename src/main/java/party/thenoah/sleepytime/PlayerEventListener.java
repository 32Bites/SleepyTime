package party.thenoah.sleepytime;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getServer;

public class PlayerEventListener implements Listener {
    JavaPlugin plugin;

    public PlayerEventListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerEnterBed(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (!isPlayerAlreadySleeping(player.getName()) && world.getTime() >= 12786) {
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                if (player.isSleeping()) {
                    plugin.getServer().getScheduler().runTask(plugin, () -> {
                        if (world.hasStorm()) {
                            world.setWeatherDuration(0);
                        }
                        world.setTime(0);
                    });
                }
            });
        }
    }

    public boolean isPlayerAlreadySleeping(String name) {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            boolean playerSleeping = player.isSleeping();
            if (playerSleeping && !player.getName().equals(name)) {
                return playerSleeping;
            }
        }
        return false;
    }
}
