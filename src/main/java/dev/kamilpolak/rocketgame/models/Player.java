package dev.kamilpolak.rocketgame.models;

import java.util.ArrayList;
import java.util.Collection;

public class Player {
    private int money;
    private final Collection<IPlayerListener> listeners = new ArrayList<>();

    public Player(int money) {
        this.money = money;
    }

    public Player() {
        this(0);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        int oldMoney = this.money;
        this.money = money;
        notifyMoneyChanged(oldMoney, money);
    }

    public void subtractMoney(int money) {
        setMoney(this.money - money);
    }

    public void addMoney(int money) {
        setMoney(this.money + money);
    }

    public void addListener(IPlayerListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IPlayerListener listener) {
        listeners.remove(listener);
    }

    private void notifyMoneyChanged(int oldMoney, int newMoney) {
        for(IPlayerListener listener: listeners) {
            listener.moneyChanged(oldMoney, newMoney);
        }
    }
}
