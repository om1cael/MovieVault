package com.om1cael.services;

public class InputParserService {
    public int validateNumberInput(int inputNumber, int min, int max, boolean receiveRaw) {
        if(receiveRaw || (inputNumber >= min && inputNumber <= max)) {
            return inputNumber;
        }

        return inputNumber < min ? min : max;
    }
}
