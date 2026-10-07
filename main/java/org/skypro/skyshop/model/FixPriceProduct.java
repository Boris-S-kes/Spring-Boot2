package org.skypro.skyshop.model;


import java.util.UUID;

public class FixPriceProduct extends Product{
    private static final int fixPrice1 = 888;
    private final UUID id;

    public FixPriceProduct(String name,UUID id) {
        super(name,id);
        this.id = UUID.randomUUID();

    }
    @Override
    public int getPrice() {
        return fixPrice1;
    }
    @Override
    public boolean isSpecial() {
        return true;
    }
    @Override
    public String toString() {
        return getName() + "Фиксированная цена" + fixPrice1;
    }
    @Override
    public UUID getId() {
        return id;
    }
}


