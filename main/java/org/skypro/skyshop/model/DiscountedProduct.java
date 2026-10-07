package org.skypro.skyshop.model;

import java.util.UUID;




public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discount;
    private final UUID id;

    public DiscountedProduct(String name, int basePrice, int discount, UUID id) {
        super(name, id);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("цена должна быть строго больше 0.");
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException
                    ("процент должен быть числом в диапазоне от 0 до 100 включительно");

        }
        this.basePrice = basePrice;
        this.discount = discount;
        this.id = id;
    }

    @Override
    public int getPrice() {
        return basePrice - (basePrice * discount / 100);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ":" + getPrice() + ":" + discount + "% скидка";
    }


    @Override
    public UUID getId() {
        return id;
    }
}






