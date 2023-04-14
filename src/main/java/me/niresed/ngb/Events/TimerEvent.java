package me.niresed.ngb.Events;

import org.bukkit.event.HandlerList;
import org.bukkit.event.server.PluginEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class TimerEvent extends PluginEvent {
    private static final HandlerList handlers = new HandlerList();

    public TimerEvent(@NotNull Plugin plugin) {
        super(plugin);
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
