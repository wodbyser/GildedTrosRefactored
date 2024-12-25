package com.gildedtros.factory.impl;

import com.gildedtros.domain.Item;
import com.gildedtros.factory.ItemUpdater;
import com.gildedtros.factory.service.ItemUpdaterHelper;

public class BackstagePassUpdater implements ItemUpdater {
    private final ItemUpdaterHelper itemUpdaterHelper;

    private static final int UPPER_THRESHOLD = 10;
    private static final int LOWER_THRESHOLD = 5;

    public BackstagePassUpdater(ItemUpdaterHelper itemUpdaterHelper) {
        this.itemUpdaterHelper = itemUpdaterHelper;
    }

    @Override
    public Item update(Item item) {
        itemUpdaterHelper.increaseQuality(item);
        if (item.sellIn <= UPPER_THRESHOLD) {
            itemUpdaterHelper.increaseQuality(item);
        }
        if (item.sellIn <= LOWER_THRESHOLD) {
            itemUpdaterHelper.increaseQuality(item);
        }
        item.sellIn--;
        if (item.sellIn < 0) {
            item.quality = 0;
        }
        return item;
    }
}
