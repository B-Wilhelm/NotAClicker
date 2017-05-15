package com.wilhelm.notaclicker;

/**
 * @author Brett_W
 */

public class Player {
    private String pName;   // Player Name
    private String bName;   // Business Name
    private int wallet;     // Player Money
    private int level;      // Player Level
    private int exp;        // Player Exp
    private int expBound;   // Exp required for lv up

    public Player() {
        this.pName = "test";
        this.bName = "test";
        this.wallet = -1;
        this.level = -1;
        this.exp = -1;
        this.expBound = 1;
    }

    public Player(String pName, String bName) {
        this.pName = pName;
        this.bName = bName;
        this.wallet = 100;
        this.level = 0;
        this.exp = 0;
        this.expBound = 1000;
    }

    ////////////////////////////////////////////// Setters
    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public void setMoney(int money) {
        this.wallet = money;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    ////////////////////////////////////////////// Getters
    public String getpName() {
        return pName;
    }

    public String getbName() {
        return bName;
    }

    public int getMoney() {
        return wallet;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    ////////////////////////////////////////////// Helpers
    private void levelUp() {
        level++;
        expBound = 1000 + ((level) * 200);
    }
}