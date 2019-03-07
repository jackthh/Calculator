package com.example.gemyni.calculatordemo;

import java.util.LinkedList;

public class IoString {
    private LinkedList<String> dataString = new LinkedList<>();
    private int previousNumSize, nextNumSize;

    void Add(String str) {
        this.dataString.add(str);
    }

    void SetData(int index, String str) {
        this.dataString.set(index, str);
    }

    String GetData() {
        // shouldn't use this.dataString.toString() cause it surrounds result by "[" and "]"
        String data = "";
        for (int i = 0; i < this.dataString.size(); i++) {
            data += this.dataString.get(i);
        }
        return data;
    }

    String GetData(int index) {
        return this.dataString.get(index);
    }

    int GetDataSize() {
        return this.dataString.size();
    }

    double GetPreviousNumber(int index) {
        int i = index - 1;
        String previousNumber = "";
        // scan for the previous number
        while (i >= 0) {
            if (this.dataString.get(i) != "*" && this.dataString.get(i) != "/" && this.dataString.get(i) != "+"
                    && this.dataString.get(i) != "-") {
                previousNumber = this.dataString.get(i) + previousNumber;
                if (i == 0) {
                    this.previousNumSize = index - i;
                }
            } else {
                // calculate size of number
                this.previousNumSize = index - i - 1;
                break;
            }
            i--;
        }
        // convert and return the previous number
        return Double.parseDouble(previousNumber);
    }

    double GetNextNumber(int index) {
        int i = index + 1;
        String nextNumber = "";
        // scan for the previous number
        while (i < this.dataString.size()) {
            if (this.dataString.get(i) != "*" && this.dataString.get(i) != "/" && this.dataString.get(i) != "+"
                    && this.dataString.get(i) != "-") {
                nextNumber += this.dataString.get(i);
                if (this.dataString.size() - i == 1) {
                    this.nextNumSize = i - index;
                }
            } else {
                // calculate size of number
                this.nextNumSize = i - index - 1;
                break;
            }
            i++;
        }
        // convert and return the previous number
        return Double.parseDouble(nextNumber);
    }

    void Clear() {
        this.dataString.clear();
    }

    void BackSpace() {
        this.dataString.removeLast();
    }

    void RemoveBesideNumbers(int index) {
        for (int i = this.nextNumSize; i >= 1; i--) {
            this.dataString.remove(index + i);
        }
        for (int i = 0; i < this.previousNumSize; i++) {
            this.dataString.remove(index - 1);
        }
        this.nextNumSize = 0;
        this.previousNumSize = 0;
    }

}
