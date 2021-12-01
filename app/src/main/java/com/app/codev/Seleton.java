package com.app.codev;


import java.util.ArrayList;

public class Seleton {
    private static Seleton sInstance;

    public static synchronized Seleton getInstance() {
        if (sInstance == null) {
            sInstance = new Seleton();
        }
        return sInstance;
    }

    public int saveTotoRotate = 0;
    public int totalRotate = 1;

    public String msvn = "";
    public String diadiem = "";

    public ArrayList<Integer> list;

    public Boolean isHaveGift() {
        return totalRotate <= saveTotoRotate;
    }

    public void addIDGift(int indexGift) {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (list.size() == 0) {
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0);
        }
        list.set(indexGift, list.get(indexGift) + 1);
    }

    public void addRotate() {
        totalRotate = totalRotate + 1;
    }

    public void reset() {
        saveTotoRotate = 0;
        totalRotate = 0;
        if (list != null) {
            list.clear();
        }
    }


    int typegame = 0;

    public int getTypeGame() {
        typegame = (typegame + 1) % 2;
        return typegame;
    }

}
