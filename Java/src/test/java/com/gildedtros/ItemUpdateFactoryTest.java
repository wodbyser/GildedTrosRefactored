package com.gildedtros;

import com.gildedtros.domain.Item;
import com.gildedtros.factory.ItemUpdater;
import com.gildedtros.factory.impl.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemUpdateFactoryTest {
    private final ItemUpdaterFactoryImpl itemUpdaterFactory = new ItemUpdaterFactoryImpl();

    @Test
    void testGetUpdaterForWineItem() {
        // Given
        Item wineItem = new Item("Good Wine", 10, 40);

        // When
        ItemUpdater updater = itemUpdaterFactory.getUpdater(wineItem);

        // Then
        assertNotNull(updater);
        assertTrue(updater instanceof GoodWineUpdater);
    }

    @Test
    void testGetUpdaterForBackstageRefactorItem() {
        // Given
        Item backstageItem = new Item("Backstage passes for Re:Factor", 10, 40);

        // When
        ItemUpdater updater = itemUpdaterFactory.getUpdater(backstageItem);

        // Then
        assertNotNull(updater);
        assertTrue(updater instanceof BackstagePassUpdater);
    }

    @Test
    void testGetUpdaterForLegendaryItem() {
        // Given
        Item legendaryItem = new Item("B-DAWG Keychain", 10, 40);

        // When
        ItemUpdater updater = itemUpdaterFactory.getUpdater(legendaryItem);

        // Then
        assertNotNull(updater);
        assertTrue(updater instanceof LegendaryItemUpdater);
    }

    @Test
    void testGetUpdaterForSmellyItem() {
        // Given
        Item smellyItem = new Item("Ugly Variable Names", 10, 40);

        // When
        ItemUpdater updater = itemUpdaterFactory.getUpdater(smellyItem);

        // Then
        assertNotNull(updater);
        assertTrue(updater instanceof SmellyItemUpdater);
    }

    @Test
    void testGetUpdaterForRegularItem() {
        // Given
        Item regularItem = new Item("RegularItem", 10, 40);

        // When
        ItemUpdater updater = itemUpdaterFactory.getUpdater(regularItem);

        // Then
        assertNotNull(updater);
        assertTrue(updater instanceof RegularItemUpdater);
    }
}
