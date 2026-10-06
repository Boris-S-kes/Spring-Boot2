package org.skypro.skyshop.product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.search.Searchable;
import java.util.Objects;
import java.util.UUID;


public abstract class Product implements Searchable {
    private final String name;
    private final UUID id;


    public Product(String name, UUID id) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Назавание продукта не может быть Null ,пустым и состоять только из пробелов.");
        }
        this.name = name;
        this.id = id;

    }


    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public abstract String toString();

    @JsonIgnore
    public String getSearchTerm() {
        return getName();
    }

    @JsonIgnore
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String getStringRepresentation() {
        return Searchable.super.getStringRepresentation();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public UUID getId() {
        return id;
    }

}