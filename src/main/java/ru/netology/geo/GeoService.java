package ru.netology.geo;

import ru.netology.entity.Location;

public interface GeoService {

    Location byIp(String s);

    Location byCoordinates(double latitude, double longitude);
}
