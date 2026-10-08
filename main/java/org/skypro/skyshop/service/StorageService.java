package org.skypro.skyshop.service;

import java.util.Optional;
import org.skypro.skyshop.model.SimpleProduct;
import org.skypro.skyshop.model.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;
import org.skypro.skyshop.model.article.Article;

import java.util.*;


@Service
public class StorageService {
    private final Map<UUID, Product> productStorage;
    private final Map<UUID, Article> articleStorage;

    public StorageService() {
        this.productStorage = new HashMap<>();
        this.articleStorage = new HashMap<>();
        initializeTestData();
    }

    private void initializeTestData() {
        Product milk = new SimpleProduct("АВПАП", 123, UUID.randomUUID());
        Article article1 = new Article("lfmld", "доставка", UUID.randomUUID());


        productStorage.put(milk.getId(), milk);
        articleStorage.put(article1.getId(), article1);

    }

    public Collection<Product> getAllProducts() {
        return productStorage.values();
    }

    public Collection<Article> getAllArticles() {
        return articleStorage.values();
    }


    public Collection<Searchable> getAllSearchables() {
        Collection<Searchable> result = new ArrayList<>();
        result.addAll(articleStorage.values());
        result.addAll(productStorage.values());
        return result;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productStorage.get(id));
    }
}
