package com.devConnor.customMobLoot.managers;

import com.devConnor.customMobLoot.CustomMobLoot;
import com.devConnor.customMobLoot.instances.MobDrop;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.EntityType;

import java.util.HashMap;

public class MobLootManager {

    private final CustomMobLoot customMobLoot;
    private final HashMap<EntityType, MobDrop> mobDrops;

    @Setter
    private boolean customDropsEnabled;

    @Setter
    @Getter
    private boolean allDropsEnabled;

    public MobLootManager(CustomMobLoot customMobLoot) {
        this.customMobLoot = customMobLoot;
        this.mobDrops = ConfigManager.getCustomDropEntities();

        this.customDropsEnabled = true;
        this.allDropsEnabled = true;
    }

    public MobDrop getMobDrop(EntityType entityType) {
        return mobDrops.get(entityType);
    }

    public boolean areCustomDropsEnabled() {
        return allDropsEnabled && customDropsEnabled;
    }
}
