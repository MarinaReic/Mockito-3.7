package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;

public class MessageSenderTest {

    static final String IP_RUSSIA = "172.123.12.19";
    static final String IP_USA = "96.123.12.19";
    static final String TEXT_RUSSIA = "Добро пожаловать";
    static final String TEXT_USA = "Welcome";

    MessageSender messageSender;
    GeoService geoService = Mockito.mock(GeoServiceImpl.class);
    LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
    Location location;

    @BeforeEach
    public void beforeEach() {
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    public void sendRussiaTest() {
        Map<String, String> testIp = new HashMap<String, String>();
        testIp.put(MessageSenderImpl.IP_ADDRESS_HEADER, IP_RUSSIA);
        location = new Location("Moscow", Country.RUSSIA, null, 0);
        given(geoService.byIp(IP_RUSSIA)).willReturn(location);
        given(localizationService.locale(Country.RUSSIA)).willReturn(TEXT_RUSSIA);
        Assertions.assertEquals(TEXT_RUSSIA, messageSender.send(testIp));
    }

    @Test
    public void sendUsaTest() {
        Map<String, String> testIp = new HashMap<String, String>();
        testIp.put(MessageSenderImpl.IP_ADDRESS_HEADER, IP_USA);
        location = new Location("New York", Country.USA, null, 0);
        given(geoService.byIp(IP_USA)).willReturn(location);
        given(localizationService.locale(Country.USA)).willReturn(TEXT_USA);
        Assertions.assertEquals(TEXT_USA, messageSender.send(testIp));
    }
}
