package com.gildedtros.factory.service;

import com.gildedtros.domain.Item;

public class ItemUpdaterHelper {
    public void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    public void decreaseQuality(Item item, int amount) {
        if (item.quality > 0) {
            item.quality = Math.max(0, item.quality - amount);
        }
    }

    public void decreaseQuality(Item item) {
        decreaseQuality(item, 1);
    }
}
