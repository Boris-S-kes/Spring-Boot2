package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.Product;
import org.skypro.skyshop.model.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
     void returnsEmptyWhenStorageIsEmpty() {

        when(storageService.getAllSearchables()).thenReturn(List.of());

        List<SearchResult> results = List.copyOf(searchService.search("Test"));

        assertThat(results).isEmpty();
    }

    @Test
     void returnsEmptyWhenNoProductsMatchPattern() {
        Product product = new SimpleProduct("DFFLK", 13232, UUID.randomUUID());

        when(storageService.getAllSearchables()).thenReturn(List.of(product));

        // Исправлено: удалено ошибочное ключевое слово when
        List<SearchResult> results = List.copyOf(searchService.search("NonMatchingPattern"));

        assertThat(results).isEmpty();
    }

    @Test
      void returnsMatchingObject() {
        Product product = new SimpleProduct("TestProduct", 1323, UUID.randomUUID());
        when(storageService.getAllSearchables()).thenReturn(List.of(product));

        Collection<SearchResult> result = searchService.search("test");

        // Используются корректные методы AssertJ
        assertThat(result)
                .hasSize(1);
    }
    private Searchable searchable(String name, String searchTerm) {
        Searchable item = org.mockito.Mockito.mock(Searchable.class);
        when(item.getId()).thenReturn(UUID.randomUUID());
        when(item.getName()).thenReturn(name);
        when(item.getSearchTerm()).thenReturn(searchTerm);
        when(item.getContentType()).thenReturn("product");
        return item;
    }
    }














