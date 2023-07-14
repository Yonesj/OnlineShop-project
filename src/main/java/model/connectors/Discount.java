package model.connectors;

import java.time.LocalDate;

public class Discount {
    private double percent;
    private LocalDate expireDate;
    private int capacity;
    private String code;

    public Discount(double percent, int capacity, LocalDate expireDate) {
        this.percent = percent;
        this.expireDate = expireDate;
        this.capacity = capacity;
        codeGenerator();
    }

    private void codeGenerator(){
        String alphabetDecimal = "abcdefghijklmnopqrstuvqxyz" + "0123456789";

        StringBuilder codeHolder = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            int index = (int)(alphabetDecimal.length() * Math.random());
            codeHolder.append(alphabetDecimal.charAt(index));
        }

        setCode(codeHolder.toString());
    }

    @Override
    public String toString(){
        return String.format("Discount: " + getCode() + "\tExpiration Date: " + getExpireDate() +
                "\tPercent: " + getPercent() + "\tCapacity: " + getCapacity());
    }

    //getters
    public double getPercent() {
        return percent;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getCode() {
        return code;
    }


    //setters
    public void setPercent(double percent) {
        this.percent = percent;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    private void setCode(String code) {
        this.code = code;
    }
}
