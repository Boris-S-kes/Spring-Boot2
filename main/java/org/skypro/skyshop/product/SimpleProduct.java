package org.skypro.skyshop.product;

import org.skypro.skyshop.product.Product;
import java.util.UUID;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(String name, int price, UUID id) {
        super(name, id);
        if (price <= 0) {
            throw new IllegalArgumentException(
                    " цена должна быть строго больше 0.");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }


    @Override
    public String toString() {
        return getName() + ":" + getPrice();
    }
    }

