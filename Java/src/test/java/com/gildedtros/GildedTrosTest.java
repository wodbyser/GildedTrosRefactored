package com.gildedtros;

import com.gildedtros.domain.GildedTros;
import com.gildedtros.domain.Item;
import com.gildedtros.domain.ItemType;
import com.gildedtros.factory.impl.ItemUpdaterFactoryImpl;
import com.gildedtros.usecase.impl.UpdateGildedTrosImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class GildedTrosTest {
    private final UpdateGildedTrosImpl underTest = new UpdateGildedTrosImpl(new ItemUpdaterFactoryImpl());

    private Item createItem(String name, int sellIn, int quality) {
        return new Item(name, sellIn, quality);
    }

    @Test
    public void qualityDegradesTwiceAsFastAfterSellByDate() {
        List<Item> items = List.of(createItem("Regular Item", 0, 10));
        underTest.dailyUpdate(new GildedTros(items));
        Assertions.assertEquals(8, items.get(0).quality);
    }

    @Test
    public void qualityNeverGoesNegative() {
        List<Item> items = List.of(createItem("Regular Item", 5, 0));
        underTest.dailyUpdate(new GildedTros(items));
        Assertions.assertEquals(0, items.get(0).quality);
    }

    @Test
    public void goodWineIncreasesInQualityAsItAges() {
        List<Item> items = List.of(createItem(ItemType.WINE.getItemName(), 10, 10));
        underTest.dailyUpdate(new GildedTros(items));
        Assertions.assertEquals(11, items.get(0).quality);
    }

    @Test
    public void qualityIsNeverMoreThan50() {
        List<Item> items = List.of(createItem(ItemType.WINE.getItemName(), 10, 50));
        underTest.dailyUpdate(new GildedTros(items));
        Assertions.assertEquals(50, items.get(0).quality);
    }

    @Test
    public void bDawgKeychainNeverDecreasesInQualityOrSellIn() {
        List<Item> items = List.of(createItem(ItemType.BDAWG_KEYCHAIN.getItemName(), 10, 80));
        underTest.dailyUpdate(new GildedTros(items));
        Assertions.assertEquals(80, items.get(0).quality);
        Assertions.assertEquals(10, items.get(0).sellIn);
    }

    @Test
    public void backstagePassesIncreaseInQualityAsSellInApproaches() {
        List<Item> items = List.of(createItem(ItemType.BACKSTAGE_REFACTOR.getItemName(), 10, 10));
        underTest.dailyUpdate(new GildedTros(items));
        Assertions.assertEquals(12, items.get(0).quality); // Increase by 2
    }

    @Test
    public void backstagePassesIncreaseBy3When5DaysOrLess() {
        List<Item> items = List.of(createItem(ItemType.BACKSTAGE_REFACTOR.getItemName(), 5, 10));
        underTest.dailyUpdate(new GildedTros(items));
        Assertions.assertEquals(13, items.get(0).quality); // Increase by 3
    }

    @Test
    public void backstagePassesDropTo0AfterTheEvent() {
        List<Item> items = List.of(createItem(ItemType.BACKSTAGE_REFACTOR.getItemName(), 0, 10), createItem(ItemType.BACKSTAGE_HAXX.getItemName(), 0, 10));
        underTest.dailyUpdate(new GildedTros(items));
        Assertions.assertEquals(0, items.get(0).quality);
        Assertions.assertEquals(0, items.get(1).quality);
    }

    @Test
    public void sellInDecreasesForRegularItems() {
        List<Item> items = List.of(createItem("Regular Item", 10, 10));
        underTest.dailyUpdate(new GildedTros(items));
        Assertions.assertEquals(9, items.get(0).sellIn);
    }

    @Test
    public void smellyItemsDegradeTwiceAsFast() {
        List<Item> items = List.of(createItem("Duplicate Code", 5, 10));
        underTest.dailyUpdate(new GildedTros(items));
        Assertions.assertEquals(8, items.get(0).quality); // Decrease by 2
    }
}
