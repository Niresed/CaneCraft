package me.niresed.ngb.Listener;

import me.niresed.ngb.Events.TimerEvent;
import me.niresed.ngb.Main.CaneCraft;
import me.niresed.ngb.Utils.MainUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class GenerateEvent implements Listener {
    private static final Plugin plugin = CaneCraft.getPlugin(CaneCraft.class);
    public int count = 0;
    public boolean runTimer = true;
    @EventHandler
    public void GenerateBlock(TimerEvent event){
        if (runTimer){
            if (count < plugin.getConfig().getInt("how many zones")){
                count += 1;
            } else {
                count = 1;
            }
            Location location = MainUtils.generateLocation(count);
            if(location.getY() != 0){
                Block block = location.getBlock();
                block.setType(Material.SUGAR_CANE);
            }
        }
    }
}
