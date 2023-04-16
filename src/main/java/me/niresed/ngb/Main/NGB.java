package me.niresed.ngb.Main;

import me.niresed.ngb.Commands.BlockSwitch;
import me.niresed.ngb.Events.TimerEvent;
import me.niresed.ngb.Listener.GenerateEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Objects;

public final class NGB extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new GenerateEvent(), this);
        Objects.requireNonNull(getCommand("BlockSwitch")).setExecutor(new BlockSwitch());

        timer();
    }
    public void timer(){
        BukkitScheduler scheduler = getServer().getScheduler();
        TimerEvent event = new TimerEvent(this);
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.getServer().getPluginManager().callEvent(event);
            }
        }, 1200L, 1200L);
    }
    @Override
    public void onDisable() {
    }
}