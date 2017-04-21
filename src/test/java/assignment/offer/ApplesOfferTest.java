package assignment.offer;

import assignment.Item;
import assignment.ItemPricing;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ApplesOfferTest {
    ApplesOffer unit;
    ItemPricing itemPricing;

    @Before
    public void setup() {
        itemPricing = new ItemPricing();
        unit = new ApplesOffer(itemPricing);
    }

    @Test
    public void appleOffer_appliesOnlyToApples() {
        assertTrue( unit.doesApply(Collections.singletonList(Item.APPLES)) );

        assertTrue( Arrays.stream(Item.values())
                .filter(item -> ! item.equals(Item.APPLES))
                .noneMatch(item -> unit.doesApply(Collections.singletonList(item))) );
    }

    @Test
    public void appleOffer_isAPercentageDiscount() {
        List<Item> basket = Arrays.asList(Item.APPLES, Item.APPLES);
        BigDecimal discount = unit.getDiscount(basket);
        assertThat(discount, is(ApplesOffer.PERCENTAGE_DISCOUNT
                .multiply(itemPricing.getPrice(Item.APPLES)
                .multiply(new BigDecimal(basket.size())
                ))));
    }
}