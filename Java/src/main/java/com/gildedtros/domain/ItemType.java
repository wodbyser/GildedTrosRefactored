package com.gildedtros.domain;

public enum ItemType {
    WINE("Good Wine"),
    BACKSTAGE_REFACTOR("Backstage passes for Re:Factor"),
    BACKSTAGE_HAXX("Backstage passes for HAXX"),
    BDAWG_KEYCHAIN("B-DAWG Keychain"),
    DUPLICATE_CODE("Duplicate Code"),
    LONG_METHODS("Long Methods"),
    UGLY_VARIABLE_NAMES("Ugly Variable Names"),
    REGULAR_ITEM("regular_item");

    private final String itemName;

    ItemType(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public static ItemType fromItemName(String itemName) {
        for (ItemType type : ItemType.values()) {
            if (type.getItemName().equals(itemName)) {
                return type;
            }
        }
        return REGULAR_ITEM;
    }
}