package utils;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:searchData.properties"
})
public interface SearchDataConfig extends Config {
    @Key ("positiveSearch")
    String positiveSearch();

    @Key("expectedText")
    String expectedText();

    @Key("negativeSearch")
    String negativeSearch();

    @Key("expectedNegativeText")
    String expectedNegativeText();
}