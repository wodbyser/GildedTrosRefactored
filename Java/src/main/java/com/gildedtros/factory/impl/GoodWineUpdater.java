package com.gildedtros.factory.impl;

import com.gildedtros.domain.Item;
import com.gildedtros.factory.ItemUpdater;
import com.gildedtros.factory.service.ItemUpdaterHelper;

public class GoodWineUpdater implements ItemUpdater {
    private final ItemUpdaterHelper itemUpdaterHelper;

    public GoodWineUpdater(ItemUpdaterHelper itemUpdaterHelper) {
        this.itemUpdaterHelper = itemUpdaterHelper;
    }

    @Override
    public Item update(Item item) {
        itemUpdaterHelper.increaseQuality(item);
        item.sellIn--;
        if (item.sellIn < 0) {
            itemUpdaterHelper.increaseQuality(item);
        }
        return item;
    }
}
