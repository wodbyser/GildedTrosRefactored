package com.gildedtros.domain;

import java.util.List;

public class GildedTros {
    private final List<Item> items;

    public GildedTros(List<Item> items) {
        if (items == null) {
            throw new IllegalArgumentException("Items cannot be null");
        }
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}