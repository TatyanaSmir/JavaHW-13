package ru.netology.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.exceptions.AlreadyExistsException;
import ru.netology.exceptions.NotFoundException;
import ru.netology.repository.ProductRepository;

public class ProductRepositoryTest {
    ProductRepository repo = new ProductRepository();
    Product product1 = new Book(1, "A", 40, "AAA");
    Product product2 = new Book(2, "B", 50, "BBB");
    Product product3 = new Smartphone(3, "Samsung", 5_000, "삼성");
    Product product4 = new Smartphone(4, "Iphone", 6_000, "Apple Inc.");
    Product product5 = new Smartphone(4, "Iphone5", 8_000, "Apple Inc.");


    @Test
    public void shouldNotSaveObjectWithSameId() {
        repo.save(product1);
        repo.save(product2);
        repo.save(product4);


        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(product5);
        });
    }

    @Test
    public void shouldSaveProducts() {
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);

        Product[] expected = {product1, product2, product3};
        Product[] actual = repo.getProducts();

        Assertions.assertArrayEquals(expected, actual);

    }


    @Test
    public void ShouldFindAll() {
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        repo.save(product4);


        Product[] expected = {product1, product2, product3, product4};
        Product[] actual = repo.getProducts();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveNonExistentObject() {
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        repo.save(product4);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(6);
        });

    }

    @Test
    public void shouldRemovedById() {
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        repo.removeById(3);

        Product[] expected = {product1, product2};
        Product[] actual = repo.getProducts();

        Assertions.assertArrayEquals(expected, actual);

    }


}
