package assignment;

import assignment.offer.ApplesOffer;
import assignment.offer.Offer;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class BasketPriceTest {

    BasketPrice unit;
    ItemPricing itemPricing;
    List<Offer> offers;

    @Before
    public void setup() {
        itemPricing = new ItemPricing();
        offers = Collections.emptyList();
    }

    @Test
    public void whenNoItems_BasketTotalIsZero() {
        unit = new BasketPrice(Collections.emptyList(), itemPricing, offers);

        assertThat(unit.getTotal(), is(BigDecimal.ZERO));
    }

    @Test
    public void whenNoOffers_BasketTotalIsSameAsSubtotal() {
        unit = new BasketPrice(Arrays.asList(Item.APPLES, Item.BREAD, Item.MILK, Item.SOUP), itemPricing, offers);

        assertThat(unit.getTotal(), is(not(BigDecimal.ZERO)));
        assertThat(unit.getTotal(), is(unit.getSubTotal()));
    }

    @Test
    public void whenAppleOffer_ApplesHaveDiscount() {
        offers = Collections.singletonList(new ApplesOffer(itemPricing));
        unit = new BasketPrice(Collections.singletonList(Item.APPLES), itemPricing, offers);

        assertThat(unit.getTotal(), is(not(BigDecimal.ZERO)));
        assertThat(unit.getSubTotal(), is(itemPricing.getPrice(Item.APPLES)));
        assertThat(unit.getTotal(), is(unit.getSubTotal().multiply(new BigDecimal("0.90"))));
    }
}