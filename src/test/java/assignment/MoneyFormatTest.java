package assignment;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class MoneyFormatTest {
    MoneyFormat unit;

    @Before
    public void setup() {
        unit = new MoneyFormat();
    }

    @Test
    public void onePoundIsDisplayedWithPoundSign() {
        assertEquals("\u00A31.00", unit.format(BigDecimal.ONE));
    }

    @Test
    public void pencesAreDisplayedWithP() {
        assertEquals("0p", unit.format(BigDecimal.ZERO));
        assertEquals("10p", unit.format(new BigDecimal("0.10")));
    }
}