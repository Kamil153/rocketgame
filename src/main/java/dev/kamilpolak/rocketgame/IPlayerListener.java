package dev.kamilpolak.rocketgame;

import java.util.EventListener;

public interface IPlayerListener extends EventListener {
    void moneyChanged(int oldMoney, int newMoney);
}
