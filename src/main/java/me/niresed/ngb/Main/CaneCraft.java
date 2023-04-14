package me.niresed.ngb.Main;

import me.niresed.ngb.Events.TimerEvent;
import me.niresed.ngb.Listener.GenerateEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class CaneCraft extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new GenerateEvent(), this);
        timer();
    }
    public void timer(){
        long countZones = this.getConfig().getLong("how many zones");
        BukkitScheduler scheduler = getServer().getScheduler();
        TimerEvent event = new TimerEvent(this);
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.getServer().getPluginManager().callEvent(event);
            }
        }, 1200L / countZones, 1200L / countZones);
    }
    @Override
    public void onDisable() {
    }
}