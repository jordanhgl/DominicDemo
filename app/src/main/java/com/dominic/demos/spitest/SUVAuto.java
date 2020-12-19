package com.dominic.demos.spitest;

@AutoService(ICar.class)
public class SUVAuto implements ICar {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void run() {

    }
}
