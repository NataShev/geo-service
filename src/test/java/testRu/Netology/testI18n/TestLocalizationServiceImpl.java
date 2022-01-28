package testRu.Netology.testI18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

public class TestLocalizationServiceImpl {
    LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @ParameterizedTest
    @MethodSource("sourse")
    public void testLocale(Country con, String expected) {


        //then:
        String result = localizationService.locale(con);
        //when:
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> sourse() {
        return Stream.of(Arguments.of(Country.RUSSIA, "Добро пожаловать"), Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome"), Arguments.of(Country.BRAZIL, "Welcome"));
    }
}
