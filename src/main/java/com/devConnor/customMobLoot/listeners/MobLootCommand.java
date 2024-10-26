package com.devConnor.customMobLoot.listeners;

import com.devConnor.customMobLoot.managers.MobLootManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MobLootCommand implements CommandExecutor {

    private final MobLootManager mobLootManager;

    public MobLootCommand(MobLootManager mobLootManager) {
        this.mobLootManager = mobLootManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            if (!doesPlayerHavePermissions(player)) {
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("drops") && args.length == 3) {
            if (!args[2].equalsIgnoreCase("on") && !args[2].equalsIgnoreCase("off")) {
                return false;
            }

            boolean toggleOn = args[2].equalsIgnoreCase("on");

            if (args[1].equalsIgnoreCase("custom")) {
                mobLootManager.setCustomDropsEnabled(toggleOn);
            } else if (args[1].equalsIgnoreCase("all")) {
                mobLootManager.setAllDropsEnabled(toggleOn);
            } else {
                return false;
            }

            if (toggleOn) {
                sender.sendMessage(ChatColor.GREEN + "[CustomMobDrops] Enabled " + args[1] + " drops.");
            } else {
                sender.sendMessage(ChatColor.RED + "[CustomMobDrops] Disabled " + args[1] + " drops.");
            }

            return true;
        }

        return false;
    }

    private boolean doesPlayerHavePermissions(Player player) {
        if (player.isOp() || player.hasPermission("mobloot.admin")) {
            return true;
        }

        player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
        return false;
    }
}
