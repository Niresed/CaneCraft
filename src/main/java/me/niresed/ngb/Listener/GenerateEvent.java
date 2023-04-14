package me.niresed.ngb.Listener;

import me.niresed.ngb.Events.NGBEvent;
import me.niresed.ngb.Main.NGB;
import me.niresed.ngb.Utils.NGBUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class GenerateEvent implements Listener {
    private static final Plugin plugin = NGB.getPlugin(NGB.class);
    public int count = 0;
    @EventHandler
    public void GenerateBlock(NGBEvent event){
        System.out.println("Hello");
        if (count < plugin.getConfig().getInt("how many zones")){
            count += 1;
        } else {
            count = 1;
        }
        Location location = NGBUtils.generateLocation(count);
        if(location.getY() != 0){
            Block block = location.getBlock();
            block.setType(Material.SUGAR_CANE);
        }
    }
}
