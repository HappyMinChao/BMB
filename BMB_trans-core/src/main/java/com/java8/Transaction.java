package com.java8;

/**
 * Transaction
 *
 * @author : minchao.du
 * @description :
 * @date : 2018/3/27
 */
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int values;

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", values=" + values +
                '}';
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValues() {
        return values;
    }

    public Transaction(Trader trader, int year, int values) {
    
        this.trader = trader;
        this.year = year;
        this.values = values;
    }
}
