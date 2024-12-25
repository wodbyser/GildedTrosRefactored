package com.gildedtros;

import com.gildedtros.domain.GildedTros;
import com.gildedtros.domain.Item;
import com.gildedtros.factory.impl.ItemUpdaterFactoryImpl;
import com.gildedtros.usecase.UpdateGildedTros;
import com.gildedtros.usecase.impl.UpdateGildedTrosImpl;

import java.util.Arrays;

public class TexttestFixture {
    public static void main(String[] args) {
        UpdateGildedTros updater = new UpdateGildedTrosImpl(new ItemUpdaterFactoryImpl());

        System.out.println("AXXES CODE KATA - GILDED TROS");

        Item[] items = new Item[] {
                new Item("Ring of Cleansening Code", 10, 20),
                new Item("Good Wine", 2, 0),
                new Item("Elixir of the SOLID", 5, 7),
                new Item("B-DAWG Keychain", 0, 80),
                new Item("B-DAWG Keychain", -1, 80),
                new Item("Backstage passes for Re:Factor", 15, 20),
                new Item("Backstage passes for Re:Factor", 10, 49),
                new Item("Backstage passes for HAXX", 5, 49),
                // these smelly items do not work properly yet
                new Item("Duplicate Code", 3, 6),
                new Item("Long Methods", 3, 6),
                new Item("Ugly Variable Names", 3, 6) };

        GildedTros app = new GildedTros(Arrays.asList(items));

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app = updater.dailyUpdate(app);
        }
    }

}
