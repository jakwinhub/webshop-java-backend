package com.demoprojekt.webshop.service;

import com.demoprojekt.webshop.exceptions.IdNotFoundException;
import com.demoprojekt.webshop.model.OrderPositionResponse;
import com.demoprojekt.webshop.model.ProductCreateRequest;
import com.demoprojekt.webshop.model.ProductResponse;
import com.demoprojekt.webshop.repository.OrderPositionRepository;
import com.demoprojekt.webshop.repository.OrderRepository;
import com.demoprojekt.webshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ShoppingCartServiceTest {

    private ProductRepository productRepository;
    private ShoppingCartService service;

    @BeforeEach
    public void setupTests() {
        productRepository = new ProductRepository();
        service = new ShoppingCartService(
                new OrderRepository(),
                new OrderPositionRepository(),
                productRepository
        );
    }

    @Test
    public void testThat_calculateSumForEmptyCart_returnsDeliveryCost() {
        // given

        // when
        Long result = service.calculateSumForCart(
                new ArrayList<OrderPositionResponse>(),
                500
        );

        // then
        assertThat(result).isEqualTo(500);
    }

    @Test
    public void testThat_calculateSumWithTwoProduct_sumsPriceOfProducts() {
        // given
        ProductResponse savedProduct1 = saveProduct(1000);
        ProductResponse savedProduct2 = saveProduct(2000);

        List<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition(orderPositions, savedProduct1, 1);
        addOrderPosition(orderPositions, savedProduct2, 4);

        // when
        Long result = service.calculateSumForCart(orderPositions, 500);

        // then
        assertThat(result).isEqualTo(9500);
    }

    @Test
    public void testThat_calculateSumWithNegativeProduct_throwsException() {
        // given
        ProductResponse savedProduct1 = saveProduct(1000);
        ProductResponse savedProduct2 = saveProduct(2000);

        List<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition(orderPositions, savedProduct1, 1);
        addOrderPosition(orderPositions, savedProduct2, -4);

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            service.calculateSumForCart(orderPositions, 500);
        });
    }

    @Test
    public void testThat_calculateSumWithNotExistingProduct_ThrowsException() {
        // given
        ProductResponse notSavedProduct = new ProductResponse("", "", "", 1000, new ArrayList<>());

        List<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition(orderPositions, notSavedProduct, 1);

        // then
        assertThrows(IdNotFoundException.class, () -> {
            // when
            service.calculateSumForCart(orderPositions, 500);
        });
    }


    private void addOrderPosition(List<OrderPositionResponse> orderPositions, ProductResponse savedProduct, int quantity) {
        orderPositions.add(
                new OrderPositionResponse(
                        "1",
                        "order-id",
                        savedProduct.getId(),
                        quantity
                )
        );
    }

    private ProductResponse saveProduct(int price) {
        ProductResponse savedProduct = productRepository.save(
                new ProductCreateRequest(
                        "",
                        "",
                        price,
                        new ArrayList<>()
                )
        );
        return savedProduct;
    }

}
