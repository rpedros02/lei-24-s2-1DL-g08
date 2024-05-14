package pt.ipp.isep.dei.esoft.project.domain;

import java.time.Year;

public class Date extends java.util.Date {

    int day;
    int month;
    int year;
    String date;

    public Date(int day, int month,int year) {
        if (!isValid(day, month, year)) {
            throw new IllegalArgumentException("Invalid date.");
        }
        this.day =  day;
        this.month = month;
        this.year = year;
    }


    private boolean isValid(int day, int month, int year) {
        boolean flag = false;
        if (year < 1900 || year > Year.now().getValue()) {
            return false;
        }
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> {
                if (day > 0 && day <= 31) {
                    flag = true;
                }
            }
            case 4, 6, 9, 11 -> {
                if (day > 0 && day <= 30) {
                    flag = true;
                }
            }
            case 2 -> {
                if (isLeap(year)) {
                    if (day > 0 && day <= 29) {
                        flag = true;
                    }
                } else {
                    if (day > 0 && day <= 28) {
                        flag = true;
                    }
                }
            }
            default -> {
            }
        }
        return flag;
    }

    private boolean isLeap(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    public boolean isEqual(Date other) {
        return this.day == other.day && this.month == other.month && this.year == other.year;
    }

    public Date clone() {
        return new Date(this.day, this.month, this.year);
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

}
