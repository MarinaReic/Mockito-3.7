package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class LocalizationServiceTest {

    static final String TEXT_RUSSIA = "Добро пожаловать";
    static final String TEXT_USA = "Welcome";

    LocalizationService localizationService;

    @BeforeEach
    public void beforeEach() {
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    public void localeRussiaTest() {
        Assertions.assertEquals(TEXT_RUSSIA, localizationService.locale(Country.RUSSIA));
    }

    @Test
    public void localeUsaTest() {
        Assertions.assertEquals(TEXT_USA, localizationService.locale(Country.USA));
    }
}
