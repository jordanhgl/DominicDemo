package com.dominic.demos.testlintcheck;

public class LintCheckTest {
    public void testcheckThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    public void testFloatParse() {
        try {
            Float.parseFloat("1.111");
        } catch (NumberFormatException e) {

        }
    }
}
