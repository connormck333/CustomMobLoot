package com.devConnor.customMobLoot.instances;

import lombok.Getter;

import java.util.Random;

@Getter
public class XpDrop {

    private final int minimumDrop;
    private final int maximumDrop;

    public XpDrop(int minimumDrop, int maximumDrop) {
        this.minimumDrop = minimumDrop;
        this.maximumDrop = maximumDrop;
    }

    public int getXpDrop() {
        return new Random().nextInt((maximumDrop - minimumDrop) + 1) + minimumDrop;
    }
}
