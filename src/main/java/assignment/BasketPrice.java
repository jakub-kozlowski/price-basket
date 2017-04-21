package assignment;

import assignment.offer.Offer;

import java.math.BigDecimal;
import java.util.List;

public class BasketPrice {
    ItemPricing itemPricing;
    List<Item> items;
    List<Offer> offers;

    public BasketPrice(List<Item> items, ItemPricing itemPricing, List<Offer> offers) {
        this.items = items;
        this.itemPricing = itemPricing;
        this.offers = offers;
    }

    public BigDecimal getTotal() {
        return getSubTotal().subtract(getOffersDiscount());
    }

    private BigDecimal getOffersDiscount() {
        return offers.stream()
                .filter(offer -> offer.doesApply(items))
                .map(offer -> offer.getDiscount(items))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getSubTotal() {
        return items.stream()
                .map(itemPricing::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
