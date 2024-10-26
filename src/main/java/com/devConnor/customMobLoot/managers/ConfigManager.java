package com.devConnor.customMobLoot.managers;

import com.devConnor.customMobLoot.CustomMobLoot;
import com.devConnor.customMobLoot.instances.Drop;
import com.devConnor.customMobLoot.instances.MobDrop;
import com.devConnor.customMobLoot.instances.XpDrop;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;

public class ConfigManager {

    private static CustomMobLoot customMobLoot;
    private static FileConfiguration config;

    private static final String messagePrefix = "[CustomMobLoot] ";

    public static void setupConfig(CustomMobLoot customMobLoot) {
        ConfigManager.customMobLoot = customMobLoot;
        ConfigManager.config = customMobLoot.getConfig();

        customMobLoot.saveDefaultConfig();
    }

    public static XpDrop getXpDropByEntity(EntityType entityType) {
        ConfigurationSection section = config.getConfigurationSection("mobs." + entityType.name().toLowerCase() + ".xp");
        if (section == null) {
            return null;
        }

        try {
            int minimum = section.getInt("minimum-drop");
            System.out.println(minimum);
            System.out.println(entityType + " " + minimum);
            int maximum = section.getInt("maximum-drop");
            return new XpDrop(minimum, maximum);
        } catch (Exception e) {
            sendWarningMessage("Could not find xp drops - skipping");
            return null;
        }
    }

    public static ArrayList<Drop> getDropsByEntity(EntityType entityType) {
        ConfigurationSection section = config.getConfigurationSection("mobs." + entityType.name().toLowerCase() + ".drops");
        ArrayList<Drop> drops = new ArrayList<>();

        if (section == null) {
            return drops;
        }

        for (String item : section.getKeys(false)) {
            Material material;
            try {
                material = Material.valueOf(item.toUpperCase());
            } catch (Exception e) {
                sendWarningMessage("Could not load item [" + item + "]");
                sendWarningMessage("This item does not exist - skipping");
                continue;
            }

            try {
                double chance = section.getDouble(item + ".chance");
                int minimumDrop = section.getInt(item + ".minimum-drop");
                int maximumDrop = section.getInt(item + ".maximum-drop");
                drops.add(new Drop(material, chance, minimumDrop, maximumDrop));
            } catch (Exception e) {
                sendWarningMessage("Could not load item [" + item + "]");
                sendWarningMessage("Please make sure chance, minimum-drop and maximum-drop have been set correctly.");
            }
        }

        return drops;
    }

    public static HashMap<EntityType, MobDrop> getCustomDropEntities() {
        ConfigurationSection section = config.getConfigurationSection("mobs");
        HashMap<EntityType, MobDrop> mobDrops = new HashMap<>();

        if (section == null) {
            return mobDrops;
        }

        for (String entity : section.getKeys(false)) {
            try {
                EntityType entityType = EntityType.valueOf(entity.toUpperCase());
                if (entityType.isAlive()) {
                    mobDrops.put(entityType, new MobDrop(entityType));
                } else {
                    sendWarningMessage("'" + entity + "' is not a valid entity - skipping");
                }
            } catch (Exception e) {
                sendWarningMessage("Could not load entity [" + entity + "]");
                sendWarningMessage("This entity does not exist - skipping");
            }
        }

        return mobDrops;
    }

    private static void sendWarningMessage(String message) {
        System.out.println(messagePrefix + message);
    }
}
