package com.gildedtros.factory.impl;

import com.gildedtros.domain.Item;
import com.gildedtros.factory.ItemUpdater;
import com.gildedtros.factory.service.ItemUpdaterHelper;

public class SmellyItemUpdater implements ItemUpdater {
    private final ItemUpdaterHelper itemUpdaterHelper;

    public SmellyItemUpdater(ItemUpdaterHelper itemUpdaterHelper) {
        this.itemUpdaterHelper = itemUpdaterHelper;
    }

    @Override
    public Item update(Item item) {
        itemUpdaterHelper.decreaseQuality(item, 2); // Degrade twice as fast
        item.sellIn--;
        if (item.sellIn < 0) {
            itemUpdaterHelper.decreaseQuality(item, 2);
        }
        return item;
    }
}
