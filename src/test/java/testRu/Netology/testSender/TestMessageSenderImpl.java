package testRu.Netology.testSender;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.startsWith;
import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;

public class TestMessageSenderImpl {

    @Test
    public void testSendsOnlyRussianText() {

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(MOSCOW_IP))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Map<String, String> headed = new HashMap<>();
        headed.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");


        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        String result = messageSender.send(headed);

        String expected = "Добро пожаловать";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSendsOnlyEnglishText() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(startsWith("96.")))
                .thenReturn(new Location("New York", Country.USA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        Map<String, String> headed = new HashMap<>();
        headed.put(MessageSenderImpl.IP_ADDRESS_HEADER, startsWith("96."));
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        String result = messageSender.send(headed);

        String expected = "Welcome";
        Assertions.assertEquals(expected, result);

    }

}
