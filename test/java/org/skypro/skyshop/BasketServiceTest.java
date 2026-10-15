package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.Product;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
     private ProductBasket productBasket;
    @Mock
    private StorageService storageService;
    @InjectMocks
    private BasketService basketService;

    @Test
    void addingMissingProductThrowsException() {
        UUID invalidid = UUID.randomUUID();
        when(storageService.getProductById(invalidid)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(invalidid));
        verify(productBasket, never()).addProduct(any());
        }



        @Test
    void AddingAnExistingProduct() {
            UUID invalidid = UUID.randomUUID();
            Product product = mock(Product.class);
            when(storageService.getProductById(invalidid)).thenReturn(Optional.of(product));
            basketService.addProduct(invalidid);
            verify(productBasket, times(1)).addProduct(invalidid);
        }
        @Test
    void ReturnsAnEmptyBasketIfProductBasketIsEmpty() {
            when(productBasket.getProducts()).thenReturn(Map.of());
            UserBasket basket = basketService.getUserBasket();
            assertThat(basket.getItems()).isEmpty();
            assertThat(basket.getTotal()).isZero();
        }
        @Test
    void ReturnsAnAppropriateBasketIfThereAreItems() {
        UUID productId = UUID.randomUUID();
        int quantity = 3;
        double price = 14.5;
        Product product = mock(Product.class);
            doReturn(price).when(product).getPrice();
        when(productBasket.getProducts()).thenReturn(Map.of(productId,quantity));
        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

        UserBasket basket = basketService.getUserBasket();
            assertThat(basket.getItems()).hasSize(1);
            assertThat(basket.getItems().get(0).getProduct()).isSameAs(product);
            assertThat(basket.getItems().get(0).getQuantity()).isEqualTo(quantity);
            assertThat(basket.getTotal()).isEqualTo(price * quantity);














        }
    }

