package models;

import java.util.*;
import java.util.stream.Collectors;

public class Basket {
    public record BasketProduct(UUID productId, Integer quantity) {
    }

    private Map<UUID, Integer> quantities;

    public Basket() {
        this.quantities = new HashMap<UUID, Integer>();
    }

    public BasketProduct[] getProducts() {
        BasketProduct[] products = this.quantities.keySet().stream()
                .map(key -> new BasketProduct(key, this.quantities.get(key)))
                .toArray(BasketProduct[]::new);

        return products;
    }

    public void addProduct(UUID productId, Integer quantity) {
        if (quantities.get(productId) == null)
            quantities.put(productId, 0);
        quantities.put(productId, quantities.get(productId) + quantity);
    }

    public void removeProduct(UUID productId) {
        quantities.remove(productId);
    }

    public void removeProduct(UUID productId, Integer quantity) {
        if (quantities.get(productId) == null)
            return;
        int oldQuantity = quantities.get(productId);
        int newQuantity = oldQuantity - quantity;
        if (newQuantity <= 0)
            quantities.remove(productId);
        else
            quantities.put(productId, newQuantity);
    }
}
