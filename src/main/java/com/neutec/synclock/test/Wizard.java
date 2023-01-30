package com.neutec.synclock.test;

import java.text.MessageFormat;
import java.util.function.Consumer;

/**
 * 魔法師類別
 */
public class Wizard extends Thread {

    public void thunder() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " - 雷擊!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - END");
        }
    }

    @Override
    public void run() {
        this.thunder();
    }

}
