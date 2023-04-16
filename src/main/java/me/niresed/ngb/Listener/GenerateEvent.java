package me.niresed.ngb.Listener;

import me.niresed.ngb.Events.TimerEvent;
import me.niresed.ngb.Main.NGB;
import me.niresed.ngb.Utils.CaneUtils;

import me.niresed.ngb.Utils.ClayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class GenerateEvent implements Listener {
    private static final Plugin plugin = NGB.getPlugin(NGB.class);
    public int count = 0;
    public int numberRepetitions = 0;
    public int zones = plugin.getConfig().getInt("how many zones");
    public int max = 3 * zones;
    public static boolean runTimer = true;
    @EventHandler
    public void GenerateBlock(TimerEvent event){
        World world = Bukkit.getWorld("world");
        if (numberRepetitions <= max){
            numberRepetitions++;
            if (runTimer){
                max = 3 * zones;
                for (int i = 1; i <= zones; i++){
                    ArrayList<Integer> coordinate = (ArrayList<Integer>) plugin.getConfig().getIntegerList("zone_" + i);
                    if (coordinate.get(0) == 0){
                        Location location = CaneUtils.generateLocation("zone_" + i, world);
                        if(location.getY() != 0){
                            location.setY(location.getY() + 1.0f);
                            Block block = location.getBlock();
                            block.setType(Material.SUGAR_CANE);
                        }
                    } else if(coordinate.get(0) == 1) {
                        Location location = ClayUtils.generateLocation("zone_" + i, world);
                        if (location != null){
                            Block block = location.getBlock();
                            block.setType(Material.CLAY);
                        }
                    }
                }
            }
        } else {
            max = 0;
            numberRepetitions--;
        }
    }
}
