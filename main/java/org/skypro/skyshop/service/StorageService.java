package org.skypro.skyshop.service;

import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchResult;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.search.Searchable;
import org.springframework.stereotype.Service;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.controller.ShopController;
import java.util.*;


@Service
public class StorageService {
    private final Map<UUID, Product> productStorage;
    private final Map<UUID,Article> articleStorage;

    public StorageService() {
        this.productStorage = new HashMap<>();
        this.articleStorage = new HashMap<>();
        initializeTestData();
    }

    private void initializeTestData() {
        Product milk = new SimpleProduct("АВПАП",123,UUID.randomUUID());
        Article article1 = new Article("lfmld","доставка",UUID.randomUUID());


        productStorage.put(milk.getId(),milk);
        articleStorage.put(article1.getId(), article1);

    }
    public Collection<Product> getAllProducts () {
        return productStorage.values();
    }
    public Collection<Article> getAllArticles () {
        return articleStorage.values();
    }



    public Collection<Searchable> getAllSearchables() {
        Collection<Searchable> result = new ArrayList<>();
        result.addAll(articleStorage.values());
        result.addAll(productStorage.values());
        return result;
    }

    }


