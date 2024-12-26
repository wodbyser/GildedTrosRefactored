package com.gildedtros;

import com.gildedtros.domain.GildedTros;
import com.gildedtros.domain.Item;
import com.gildedtros.domain.ItemType;
import com.gildedtros.factory.impl.ItemUpdaterFactoryImpl;
import com.gildedtros.usecase.impl.UpdateGildedTrosImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class UpdateGildedTrosTest {
    private final UpdateGildedTrosImpl underTest = new UpdateGildedTrosImpl(new ItemUpdaterFactoryImpl());

    private Item createItem(String name, int sellIn, int quality) {
        return new Item(name, sellIn, quality);
    }

    @Test
    public void qualityDegradesTwiceAsFastAfterSellByDate() {
        //Given
        List<Item> items = List.of(createItem("Regular Item", 0, 10));

        //When
        underTest.dailyUpdateItems(new GildedTros(items));

        //Then
        Assertions.assertEquals(8, items.get(0).quality);
    }

    @Test
    public void qualityNeverGoesNegative() {
        //Given
        List<Item> items = List.of(createItem("Regular Item", 5, 0));

        //When
        underTest.dailyUpdateItems(new GildedTros(items));

        //Then
        Assertions.assertEquals(0, items.get(0).quality);
    }

    @Test
    public void goodWineIncreasesInQualityAsItAges() {
        //Given
        List<Item> items = List.of(createItem(ItemType.WINE.getItemName(), 10, 10));

        //When
        underTest.dailyUpdateItems(new GildedTros(items));

        //Then
        Assertions.assertEquals(11, items.get(0).quality);
    }

    @Test
    public void qualityIsNeverMoreThan50() {
        //Given
        List<Item> items = List.of(createItem(ItemType.WINE.getItemName(), 10, 50));

        //When
        underTest.dailyUpdateItems(new GildedTros(items));

        //Then
        Assertions.assertEquals(50, items.get(0).quality);
    }

    @Test
    public void bDawgKeychainNeverDecreasesInQualityOrSellIn() {
        //Given
        List<Item> items = List.of(createItem(ItemType.BDAWG_KEYCHAIN.getItemName(), 10, 80));

        //When
        underTest.dailyUpdateItems(new GildedTros(items));

        //Then
        Assertions.assertEquals(80, items.get(0).quality);
        Assertions.assertEquals(10, items.get(0).sellIn);
    }

    @Test
    public void backstagePassesIncreaseInQualityAsSellInApproaches() {
        //Given
        List<Item> items = List.of(createItem(ItemType.BACKSTAGE_REFACTOR.getItemName(), 10, 10));

        //When
        underTest.dailyUpdateItems(new GildedTros(items));

        //Then
        Assertions.assertEquals(12, items.get(0).quality); // Increase by 2
    }

    @Test
    public void backstagePassesIncreaseBy3When5DaysOrLess() {
        //Given
        List<Item> items = List.of(createItem(ItemType.BACKSTAGE_REFACTOR.getItemName(), 5, 10));

        //When
        underTest.dailyUpdateItems(new GildedTros(items));

        //Then
        Assertions.assertEquals(13, items.get(0).quality); // Increase by 3
    }

    @Test
    public void backstagePassesDropTo0AfterTheEvent() {
        //Given
        List<Item> items = List.of(createItem(ItemType.BACKSTAGE_REFACTOR.getItemName(), 0, 10), createItem(ItemType.BACKSTAGE_HAXX.getItemName(), 0, 10));

        //When
        underTest.dailyUpdateItems(new GildedTros(items));

        //Then
        Assertions.assertEquals(0, items.get(0).quality);
        Assertions.assertEquals(0, items.get(1).quality);
    }

    @Test
    public void sellInDecreasesForRegularItems() {
        //Given
        List<Item> items = List.of(createItem("Regular Item", 10, 10));

        //When
        underTest.dailyUpdateItems(new GildedTros(items));

        //Then
        Assertions.assertEquals(9, items.get(0).sellIn);
    }

    @Test
    public void smellyItemsDegradeTwiceAsFast() {
        //Given
        List<Item> items = List.of(createItem("Duplicate Code", 5, 10));

        //When
        underTest.dailyUpdateItems(new GildedTros(items));

        //Then
        Assertions.assertEquals(8, items.get(0).quality); // Decrease by 2
    }

    @Test
    public void itemsCantBeNull() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new GildedTros(null)
        );

        Assertions.assertEquals("Items cannot be null", exception.getMessage());
    }
}
