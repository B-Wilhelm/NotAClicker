package com.wilhelm.notaclicker;

/**
 * @author Brett_W
 */

class GameData {
    private final int year = 365;
    private int day;

    GameData() {
        this.day = 0;
    }

    int getYear() {
        return day/year+1;
    }

    int getDay() {
        return day%year+1;
    }
}
