package dev.kamilpolak.rocketgame;

import java.util.ArrayList;
import java.util.Collection;

public class Player {
    private int money;
    private final Collection<IPlayerListener> listeners = new ArrayList<>();

    public Player(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        int oldMoney = this.money;
        this.money = money;
        notifyMoneyChanged(oldMoney, money);
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
