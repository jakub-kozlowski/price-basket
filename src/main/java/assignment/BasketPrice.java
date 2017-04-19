package assignment;

import java.math.BigDecimal;
import java.util.List;

public class BasketPrice {
    ItemPricing itemPricing;
    List<Item> items;

    public BasketPrice(List<Item> items, ItemPricing itemPricing) {
        this.items = items;
        this.itemPricing = itemPricing;
    }

    public BigDecimal getTotal() {
        return getSubTotal();
    }

    public BigDecimal getSubTotal() {
        return items.stream()
                .map(item -> itemPricing.getPrice(item))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
