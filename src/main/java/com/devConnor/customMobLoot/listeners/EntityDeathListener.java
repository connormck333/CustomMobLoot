package com.devConnor.customMobLoot.listeners;

import com.devConnor.customMobLoot.instances.Drop;
import com.devConnor.customMobLoot.instances.MobDrop;
import com.devConnor.customMobLoot.managers.MobLootManager;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {

    private final MobLootManager mobLootManager;

    public EntityDeathListener(MobLootManager mobLootManager) {
        this.mobLootManager = mobLootManager;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        Entity entity = e.getEntity();
        MobDrop mobDrop = mobLootManager.getMobDrop(entity.getType());
        if (mobDrop == null) {
            return;
        }

        // Set new loot drop
        Drop drop = mobDrop.getRandomDrop();
        e.getDrops().clear();
        if (drop != null) {
            e.getDrops().add(drop.getItemStack());
        }

        // Set xp drop
        if (mobDrop.shouldChangeXpDrop()) {
            e.setDroppedExp(mobDrop.getXpDrop());
        }
    }
}
