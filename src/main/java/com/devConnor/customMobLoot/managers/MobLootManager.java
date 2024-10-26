package com.devConnor.customMobLoot.managers;

import com.devConnor.customMobLoot.CustomMobLoot;
import com.devConnor.customMobLoot.instances.MobDrop;
import org.bukkit.entity.EntityType;

import java.util.HashMap;

public class MobLootManager {

    private final CustomMobLoot customMobLoot;
    private final HashMap<EntityType, MobDrop> mobDrops;

    public MobLootManager(CustomMobLoot customMobLoot) {
        this.customMobLoot = customMobLoot;
        this.mobDrops = ConfigManager.getCustomDropEntities();
    }

    public MobDrop getMobDrop(EntityType entityType) {
        return mobDrops.get(entityType);
    }
}
