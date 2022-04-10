package com.nhnacademy.parking.fee;

public class Money {
    private final int amount;

    public Money(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Money is negative.");
        }
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Money plus(Money money){
        return new Money(this.amount + money.getAmount());
    }

    public Money minus(Money money) {
        if (this.amount < money.getAmount()) {
            throw new IllegalArgumentException("Money is negative");
        }
        return new Money(this.amount - money.getAmount());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Money money = (Money) obj;
        return this.amount == money.getAmount();
    }
}
