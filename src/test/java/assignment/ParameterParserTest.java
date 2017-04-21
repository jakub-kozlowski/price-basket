package assignment;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParameterParserTest {

    ParameterParser unit;

    @Before
    public void setup() {
        unit = new ParameterParser();
    }

    @Test
    public void parserTranslatesStringParametersToItems() {
        List<Item> items = unit.parse(null);
        assertThat(items, is(Collections.emptyList()));

        items = unit.parse("Apples", "Milk");
        assertThat(items, is(Arrays.asList(Item.APPLES, Item.MILK)));
    }

    @Test
    public void whenUnknownItem_hasUnknownItems_returnsTrue() {
        String unknownItem = "Bananas";
        assertFalse(Arrays.stream(Item.values()).anyMatch(item -> item.name().equalsIgnoreCase(unknownItem)));

        unit.parse(unknownItem);
        assertTrue(unit.hasUnknownItems());
    }
}