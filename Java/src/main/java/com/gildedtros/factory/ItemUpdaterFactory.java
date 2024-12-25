package com.gildedtros.factory;

import com.gildedtros.domain.Item;

public interface ItemUpdaterFactory {
    ItemUpdater getUpdater(Item item);
}
