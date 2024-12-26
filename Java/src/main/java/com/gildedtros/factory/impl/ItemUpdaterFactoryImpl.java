package com.gildedtros.factory.impl;

import com.gildedtros.domain.Item;
import com.gildedtros.domain.ItemType;
import com.gildedtros.factory.ItemUpdater;
import com.gildedtros.factory.ItemUpdaterFactory;
import com.gildedtros.factory.service.ItemUpdaterHelper;

import java.util.Objects;

public class ItemUpdaterFactoryImpl implements ItemUpdaterFactory {
    private final ItemUpdaterHelper itemUpdaterHelper;

    public ItemUpdaterFactoryImpl(ItemUpdaterHelper itemUpdaterHelper) {
        if (itemUpdaterHelper == null) {
            throw new IllegalArgumentException("itemUpdaterHelper cannot be null");
        }
        this.itemUpdaterHelper = itemUpdaterHelper;
    }

    @Override
    public ItemUpdater getUpdater(Item item) {
        ItemType itemName = ItemType.fromItemName(item.name);

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
