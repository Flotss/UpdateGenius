package com.flotss.updateallyourprograms.utils;

public class SmartReturn {
    private final String value;
    private final int index;

    public SmartReturn(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

}
