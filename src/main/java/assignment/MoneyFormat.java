package assignment;

import java.math.BigDecimal;
import java.util.Locale;

public class MoneyFormat {

    static {
        Locale.setDefault(Locale.UK);
    }

    public String format(BigDecimal money) {
        return money.compareTo(BigDecimal.ONE) >= 0 ?
                String.format("\u00A3%.2f", money)
                : String.format("%.0fp", money.movePointRight(2));
    }
}
