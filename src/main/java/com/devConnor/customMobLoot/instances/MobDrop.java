package com.devConnor.customMobLoot.instances;

import com.devConnor.customMobLoot.managers.ConfigManager;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.Random;

public class MobDrop {

    private final EntityType entityType;
    private final XpDrop xpDrop;
    private final ArrayList<Drop> drops;
    private final double totalChance;


    public MobDrop(EntityType entityType) {
        this.entityType = entityType;
        this.xpDrop = ConfigManager.getXpDropByEntity(this.entityType);
        this.drops = ConfigManager.getDropsByEntity(this.entityType);

        double chance = 0;
        for (Drop drop : this.drops) {
            chance += drop.getChance();
        }
        if (chance < 100) {
            this.totalChance = 100;
        } else {
            this.totalChance = chance;
        }
    }

    public Drop getRandomDrop() {
        Random random = new Random();
        double index = random.nextDouble(totalChance);

        double prevChance = 0;
        double currentChance = 0;
        for (Drop drop : drops) {
            currentChance += drop.getChance();
            if (index >= prevChance && index < currentChance) {
                return drop;
            }
            prevChance += drop.getChance();
        }

        return null;
    }

    public int getXpDrop() {
        return xpDrop.getXpDrop();
    }

    public boolean shouldChangeXpDrop() {
        return xpDrop != null && xpDrop.getMinimumDrop() >= 0 && xpDrop.getMaximumDrop() >= 0;
    }
}
