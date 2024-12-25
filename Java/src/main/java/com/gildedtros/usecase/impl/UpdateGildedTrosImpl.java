package com.gildedtros.usecase.impl;

import com.gildedtros.domain.GildedTros;
import com.gildedtros.domain.Item;
import com.gildedtros.factory.ItemUpdaterFactory;
import com.gildedtros.usecase.UpdateGildedTros;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UpdateGildedTrosImpl implements UpdateGildedTros {
    private final ItemUpdaterFactory itemUpdaterFactory;

    public UpdateGildedTrosImpl(ItemUpdaterFactory itemUpdaterFactory) {
        this.itemUpdaterFactory = itemUpdaterFactory;
    }

    @Override
    public GildedTros dailyUpdateItems(GildedTros gildedTros) {
        List<Item> updatedItems = gildedTros.getItems().stream()
                .filter(Objects::nonNull)
                .map(item -> this.itemUpdaterFactory.getUpdater(item).update(item))
                .collect(Collectors.toList());

        return new GildedTros(updatedItems);
    }
}