package me.niresed.ngb.Listener;

import me.niresed.ngb.Events.TimerEvent;
import me.niresed.ngb.Main.NGB;
import me.niresed.ngb.Utils.MainUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class GenerateEvent implements Listener {
    private static final Plugin plugin = NGB.getPlugin(NGB.class);
    public int count = 0;
    public int numberRepetitions = 0;
    public int max = 2 * plugin.getConfig().getInt("how many zones");
    public static boolean runTimer = true;
    @EventHandler
    public void GenerateBlock(TimerEvent event){
        if (numberRepetitions <= max){
            numberRepetitions++;
            if (runTimer){
                max = 5 * plugin.getConfig().getInt("how many zones");
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
        } else {
            max = 0;
            numberRepetitions--;
        }
    }
}
