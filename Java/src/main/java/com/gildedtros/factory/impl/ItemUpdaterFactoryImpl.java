package com.gildedtros.factory.impl;

import com.gildedtros.domain.Item;
import com.gildedtros.domain.ItemType;
import com.gildedtros.factory.ItemUpdater;
import com.gildedtros.factory.ItemUpdaterFactory;
import com.gildedtros.factory.service.ItemUpdaterHelper;

import java.util.Objects;

public class ItemUpdaterFactoryImpl implements ItemUpdaterFactory {
    @Override
    public ItemUpdater getUpdater(Item item) {
        ItemType itemName = ItemType.fromItemName(item.name);

        ItemUpdaterHelper itemUpdaterHelper = new ItemUpdaterHelper();

        switch (Objects.requireNonNull(itemName)) {
            case WINE:
                return new GoodWineUpdater(itemUpdaterHelper);
            case BACKSTAGE_REFACTOR:
            case BACKSTAGE_HAXX:
                return new BackstagePassUpdater(itemUpdaterHelper);
            case BDAWG_KEYCHAIN:
                return new LegendaryItemUpdater();
            case DUPLICATE_CODE:
            case LONG_METHODS:
            case UGLY_VARIABLE_NAMES:
                return new SmellyItemUpdater(itemUpdaterHelper);
            default:
                return new RegularItemUpdater(itemUpdaterHelper);
        }
    }
}