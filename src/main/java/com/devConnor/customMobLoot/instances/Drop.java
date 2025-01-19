package com.devConnor.customMobLoot.instances;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Drop {

    @Getter
    private final Material material;

    @Getter
    private final double chance;

    private final int minimumDrop;
    private final int maximumDrop;

    public Drop(Material material, double chance, int minimumDrop, int maximumDrop) {
        this.material = material;
        this.chance = chance;
        this.minimumDrop = minimumDrop;
        this.maximumDrop = maximumDrop;
    }

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(material);

        Random random = new Random();
        int amount = random.nextInt((maximumDrop - minimumDrop) + 1) + minimumDrop;
        itemStack.setAmount(amount);

        return itemStack;
    }
}
