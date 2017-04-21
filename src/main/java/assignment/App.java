package assignment;

import assignment.offer.ApplesOffer;
import assignment.offer.Offer;
import assignment.offer.SoupAndBreadOffer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App
{
    private ParameterParser parameterParser;
    private BasketPrice basketPrice;
    private List<Item> items;
    private List<Offer> offers;
    private ItemPricing itemPricing;

    public App() {
        parameterParser = new ParameterParser();
        itemPricing = new ItemPricing();
        offers = Arrays.asList(new ApplesOffer(itemPricing), new SoupAndBreadOffer(itemPricing));
    }

    public static void main( String[] args )
    {
        new App().run(args);
    }

    private void run(String[] args) {
        items = parameterParser.parse(args);

        if( ! parameterParser.hasUnknownItems() )
            priceBasket();
        else
            outputErrorParsingParameters();
    }

    private void priceBasket() {
        basketPrice = new BasketPrice(items, itemPricing, offers);
        outputPricing();
    }

    private void outputPricing() {
        final MoneyFormat moneyFormat = new MoneyFormat();
        try {
            System.out.println("Subtotal: " + moneyFormat.format(basketPrice.getSubTotal()));
            outputOffers(moneyFormat);
            System.out.println("Total: " + moneyFormat.format(basketPrice.getTotal()));
        }
        catch( Exception e ) {
            System.out.println("Could not price basket: " + e.getMessage());
        }
    }

    private void outputOffers(MoneyFormat moneyFormat) {
        List<Offer> applicableOffers = offers.stream().filter(offer -> offer.doesApply(items)).collect(Collectors.toList());
        if( applicableOffers.isEmpty() )
            System.out.println("(No offers available)");
        else
            applicableOffers.forEach(offer -> System.out.println(offer.getName() + ": -" + moneyFormat.format(offer.getDiscount(items))));
    }

    private void outputErrorParsingParameters() {
        System.out.println("Following items are unknown: " + String.join(", ", parameterParser.getUnknownItems()));
    }

}
