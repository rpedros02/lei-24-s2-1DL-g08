package pt.ipp.isep.dei.esoft.project.domain;

import java.time.Year;

public class Date extends java.util.Date {

    int day;
    int month;
    int year;

    public Date(int day, int month, int year) {
        if (!isValid(day, month, year)) {
            throw new IllegalArgumentException("Invalid date.");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String date) {
        if (!isValid(Integer.parseInt(date.substring(0, 2)), Integer.parseInt(date.substring(3, 5)), Integer.parseInt(date.substring(6, 10))) || date.length() != 10) {
            throw new IllegalArgumentException("Invalid date.");
        }
        this.day = Integer.parseInt(date.substring(0, 2));
        this.month = Integer.parseInt(date.substring(3, 5));
        this.year = Integer.parseInt(date.substring(6, 10));
    }

    private boolean isValid(int day, int month, int year) {
        boolean flag = false;
        if (year < Year.now().getValue() - 100 || year > Year.now().getValue() + 100) {
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

    public boolean isAfter(Date other) {
        if (this.year > other.year) {
            return true;
        } else if (this.year == other.year) {
            if (this.month > other.month) {
                return true;
            } else if (this.month == other.month) {
                return this.day > other.day;
            }
        }
        return false;
    }

    public Date clone() {
        return new Date(this.day, this.month, this.year);
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    public Date plusYears(int i) {
        return new Date(this.day, this.month, this.year + i);
    }
}
