package ru.netology.repository;

import ru.netology.domain.Product;
import ru.netology.exceptions.AlreadyExistsException;
import ru.netology.exceptions.NotFoundException;

public class ProductRepository {
    private Product[] products = new Product[0];

    public void save(Product product) {
        int id = 0;
        if (findById(product.getId()) != null)
            throw new AlreadyExistsException(
                    "Product with ID " + id + " already exist"
            );

        //}
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;

    }

    public void removeById(int id) {
        Product product = findById(id);
        if (product == null) {
            throw new NotFoundException(
                    "ID " + id + " not found"
            );
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product item : products) {
            if (item.getId() != id) {
                tmp[copyToIndex] = item;
                copyToIndex++;
            }
        }
        products = tmp;

    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {

                return product;
            }
        }
        return null;
    }


    public Product[] getProducts() {
        return products;
    }
}