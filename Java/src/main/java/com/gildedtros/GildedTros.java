package com.gildedtros;

class GildedTros {
    Item[] items;

    public GildedTros(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.name) {
                case "Good Wine":
                    updateGoodWine(item);
                    break;
                case "Backstage passes for Re:Factor":
                case "Backstage passes for HAXX":
                    updateBackstagePass(item);
                    break;
                case "B-DAWG Keychain":
                    // B-DAWG Keychain does not degrade in quality or change sellIn
                    break;
                default:
                    updateRegularItem(item);
                    break;
            }

            if (!"B-DAWG Keychain".equals(item.name)) {
                item.sellIn--;
            }

            if (item.sellIn < 0) {
                handleExpiredItem(item);
            }
        }
    }

    private void updateGoodWine(Item item) {
        increaseQuality(item);
    }

    private void updateBackstagePass(Item item) {
        increaseQuality(item);
        if (item.sellIn < 11) {
            increaseQuality(item);
        }
        if (item.sellIn < 6) {
            increaseQuality(item);
        }
    }

    private void updateRegularItem(Item item) {
        decreaseQuality(item);
    }

    private void handleExpiredItem(Item item) {
        switch (item.name) {
            case "Good Wine":
                increaseQuality(item);
                break;
            case "Backstage passes for Re:Factor":
            case "Backstage passes for HAXX":
                item.quality = 0;
                break;
            default:
                decreaseQuality(item);
                break;
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }
}