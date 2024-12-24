package com.gildedtros.factory.impl;

import com.gildedtros.domain.Item;
import com.gildedtros.factory.ItemUpdater;
import com.gildedtros.factory.service.ItemUpdaterHelper;

public class RegularItemUpdater implements ItemUpdater {
    private final ItemUpdaterHelper itemUpdaterHelper;

    public RegularItemUpdater(ItemUpdaterHelper itemUpdaterHelper) {
        this.itemUpdaterHelper = itemUpdaterHelper;
    }

    @Override
    public Item update(Item item) {
        itemUpdaterHelper.decreaseQuality(item);
        item.sellIn--;
        if (item.sellIn < 0) {
            itemUpdaterHelper.decreaseQuality(item);
        }
        return item;
    }
}