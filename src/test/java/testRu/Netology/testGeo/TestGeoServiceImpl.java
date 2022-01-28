package testRu.Netology.testGeo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

import static ru.netology.geo.GeoServiceImpl.*;

public class TestGeoServiceImpl {
    GeoServiceImpl geoService = new GeoServiceImpl();

    @ParameterizedTest
    @MethodSource("sourse")
    public void testLocationByIp(String ip, Location expected) {

        //then:
        Location result = geoService.byIp(ip);

        //when:
        Assertions.assertEquals(expected, result);

    }

    public static Stream<Arguments> sourse() {
        return Stream.of(Arguments.of(LOCALHOST, new Location(null, null, null, 0)),
                Arguments.of(MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.", new Location("New York", Country.USA, null, 0)));

    }
}
