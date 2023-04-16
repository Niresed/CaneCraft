package me.niresed.ngb.Utils;

import me.niresed.ngb.Main.NGB;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class ClayUtils {
    private static final Plugin plugin = NGB.getPlugin(NGB.class);
    public static Location generateLocation(String zone, World world) {
        ArrayList<Integer> coordinate = (ArrayList<Integer>) plugin.getConfig().getIntegerList(zone);
        Location location = generateRandomLocation(world, coordinate);
        for (int i = 0; i < 30; i++){
            boolean isLocationSafeCheck = isLocationSafe(location);
            if (!(isLocationSafeCheck)){
                location = generateRandomLocation(world, coordinate);
            } else {
                location = getHighestBlockYATWater(location);
                if (location != null){
                    return location;
                }
                break;
            }
        }
        return null;
    }

    private static Location generateRandomLocation(World world, ArrayList<Integer> coordinate){
        int minX = coordinate.get(1), minZ = coordinate.get(2);
        int maxX = coordinate.get(3), maxZ = coordinate.get(4);

        int x = (int) Math.floor(Math.random() * (maxX - minX + 1) + minX);
        int y;
        int z = (int) Math.floor(Math.random() * (maxZ - minZ + 1) + minZ);

        Location location = new Location(world, x, 0, z);
        y = location.getWorld().getHighestBlockYAt(location) + 1;
        location.setY(y);

        return location;
    }

    private static boolean isLocationSafe(Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);
        Block above2x = location.getWorld().getBlockAt(x, y + 2, z);

        return !(!(below.getType() == Material.WATER) || (block.getType().isSolid()) || (above.getType().isSolid()) || (above2x.getType().isSolid()));
    }

    private static Location getHighestBlockYATWater(Location location){
        Block block;
        Block above;
        Block below;
        int x;
        int y;
        int z;
        for (int i = 0; i < 30; i++){
            x = (int) location.getX();
            y = (int) location.getY();
            z = (int) location.getZ();

            below = location.getWorld().getBlockAt(x, y - 1, z);
            block = location.getWorld().getBlockAt(x, y, z);
            above = location.getWorld().getBlockAt(x, y + 1, z);
            location.setY(y - 1);
            if (below.isSolid() && block.getType() == Material.WATER && !(above.getType() == Material.AIR)){
                return block.getLocation();
            }
        }
        return null;
    }
}
