package me.niresed.ngb.Utils;

import me.niresed.ngb.Main.NGB;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class NGBUtils extends JavaPlugin implements Listener {
    private final Random random = new Random();

    // plugin
    private static final Plugin plugin = NGB.getPlugin(NGB.class);
    // coordinate, in config.yml
    private static final ArrayList<Integer> coordinate = (ArrayList<Integer>) plugin.getConfig().getIntegerList("zone");
    // trueBlocks, blocks on which can spawn reeds
    private static final HashSet<Material> trueBlocks = new HashSet<>();

    private static Location location;

    static {
        trueBlocks.add(Material.SAND);
        trueBlocks.add(Material.GRASS_BLOCK);
        trueBlocks.add(Material.DIRT);
    }

    public static Location generateLocation(){
        World world = Bukkit.getWorld("world");
        Location location1 = new Location(world, 0, 0, 0);
        Location location = generateRandomLocation(world);
        for (int i = 0; i < 30; i++){
            boolean isLocationSafeCheck = isLocationSafe(location);
            boolean isLocationHasWaterInNearby = placeSugarCane(location.getWorld().getBlockAt((int) location.getX(), (int) location.getY() - 1, (int) location.getZ()));
            if (!(isLocationSafeCheck) || !(isLocationHasWaterInNearby)){
                location = generateRandomLocation(world);
            } else{
                return location;
            }
        }
        return location;
    }

    private static Location generateRandomLocation(World world){
        Random random = new Random();
        int minX = coordinate.get(0), minZ = coordinate.get(1);
        int maxX = coordinate.get(2), maxZ = coordinate.get(3);
        int x = (int) Math.floor(Math.random() * (maxX - minX + 1) + minX);
        int y = 0;
        int z = (int) Math.floor(Math.random() * (maxZ - minZ + 1) + minZ);
        Location location = new Location(world, x, y, z);
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
        return !(!(trueBlocks.contains(below.getType())) || (block.getType().isSolid()) || (above.getType().isSolid()));
    }

    private static boolean placeSugarCane(Block block) {
        Block[] blocks = {
                block.getRelative(BlockFace.WEST),
                block.getRelative(BlockFace.EAST),
                block.getRelative(BlockFace.NORTH),
                block.getRelative(BlockFace.SOUTH),
        };
        for (Block block1 : blocks){
            if (block1.getType() == Material.WATER){
                return true;
            }
        }
        return false;
    }
}
