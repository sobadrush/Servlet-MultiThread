package com.neutec.synclock.test;

public class TestWizard {

    public static void main(String[] args) {
        Wizard wizard = new Wizard();
        Thread thr1 = new Thread(wizard);
        thr1.start();
        Thread thr2 = new Thread(wizard);
        thr2.start();
    }

}
