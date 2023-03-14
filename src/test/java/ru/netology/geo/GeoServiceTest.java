package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceTest {

    static final String IP_RUSSIA = "172.123.12.19";
    static final String IP_USA = "96.123.12.19";

    GeoService geoService;
    Location location;

    @BeforeEach
    public void beforeEach() {
        geoService = new GeoServiceImpl();
    }

    @Test
    public void locationByIpRussiaTest() {
        location = geoService.byIp(IP_RUSSIA);
        Assertions.assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    public void locationByIpUsaTest() {
        location = geoService.byIp(IP_USA);
        Assertions.assertEquals(Country.USA, location.getCountry());
    }

    @Test
    public void locationByCoordinates() {
        Assertions.assertThrows(RuntimeException.class, () -> geoService.byCoordinates(100.1, 50.6));
    }
}
