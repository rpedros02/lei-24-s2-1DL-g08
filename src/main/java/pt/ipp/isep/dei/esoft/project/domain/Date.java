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

    public Date(long date) {
        String dateStr = String.valueOf(date);
        int day = Integer.parseInt(dateStr.substring(0, 2));
        int month = Integer.parseInt(dateStr.substring(3, 5));
        int year = Integer.parseInt(dateStr.substring(6, 10));

        if (!isValid(day, month, year) || dateStr.length() != 10) {
            throw new IllegalArgumentException("Invalid date.");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    private boolean isValid(int day, int month, int year) {
        if (year < 1900 || year > 2100) {
            return false;
        }

        if (month < 1 || month > 12) {
            return false;
        }

        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12:
                return day >= 1 && day <= 31;
            case 4, 6, 9, 11:
                return day >= 1 && day <= 30;
            case 2:

                boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                if (isLeapYear) {
                    return day >= 1 && day <= 29;
                } else {
                    return day >= 1 && day <= 28;
                }
            default:
                return false;
        }
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
