package me.niresed.ngb.Commands;

import me.niresed.ngb.Listener.GenerateEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BlockSwitch implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("ngb.gcane")){
                if (args[0].equals("on")){
                    GenerateEvent.runTimer = true;
                } else if (args[0].equals("off")) {
                    GenerateEvent.runTimer = false;
                } else {
                    player.sendMessage(ChatColor.RED + "Wrong command");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You haven't permission for this command");
            }
        } else {
            if (args[0].equals("on")){
                GenerateEvent.runTimer = true;
            } else if (args[0].equals("off")) {
                GenerateEvent.runTimer = false;
            } else {
                Bukkit.getLogger().info(ChatColor.RED + "Wrong command");
            }
        }
        return true;
    }
}
