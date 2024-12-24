package com.gildedtros.factory.impl;

import com.gildedtros.domain.Item;
import com.gildedtros.factory.ItemUpdater;
import com.gildedtros.factory.service.ItemUpdaterHelper;

public class BackstagePassUpdater implements ItemUpdater {
    private final ItemUpdaterHelper itemUpdaterHelper;

    public BackstagePassUpdater(ItemUpdaterHelper itemUpdaterHelper) {
        this.itemUpdaterHelper = itemUpdaterHelper;
    }

    @Override
    public Item update(Item item) {
        itemUpdaterHelper.increaseQuality(item);
        if (item.sellIn <= 10) {
            itemUpdaterHelper.increaseQuality(item);
        }
        if (item.sellIn <= 5) {
            itemUpdaterHelper.increaseQuality(item);
        }
        item.sellIn--;
        if (item.sellIn < 0) {
            item.quality = 0;
        }
        return item;
    }
}
