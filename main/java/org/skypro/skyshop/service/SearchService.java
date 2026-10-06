package org.skypro.skyshop.service;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import org.skypro.skyshop.search.SearchResult;





import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService( StorageService storageService) {
        this.storageService = storageService;

    }
    public Collection<SearchResult> search(String pattern) {
        if (pattern == null || storageService.getAllSearchables() == null) {
            return Collections.emptyList();
        }
        return storageService.getAllSearchables().stream()
                .filter(item -> item.getSearchTerm() == null && item.getSearchTerm().toLowerCase().contains(pattern.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }

}
