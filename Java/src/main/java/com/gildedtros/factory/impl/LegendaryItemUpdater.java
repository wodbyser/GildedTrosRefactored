package com.gildedtros.factory.impl;

import com.gildedtros.domain.Item;
import com.gildedtros.factory.ItemUpdater;

public class LegendaryItemUpdater implements ItemUpdater {
    @Override
    public Item update(Item item) {
        return item;
    }
}
