package com.devConnor.customMobLoot;

import com.devConnor.customMobLoot.listeners.EntityDeathListener;
import com.devConnor.customMobLoot.listeners.MobLootCommand;
import com.devConnor.customMobLoot.managers.MobLootManager;
import com.devConnor.customMobLoot.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomMobLoot extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigManager.setupConfig(this);

        MobLootManager mobLootManager = new MobLootManager(this);
        Bukkit.getPluginManager().registerEvents(new EntityDeathListener(mobLootManager), this);

        getCommand("mobloot").setExecutor(new MobLootCommand(mobLootManager));
    }
}
