package pt.ipp.isep.dei.esoft.project.domain;

import java.time.Year;

/**
 * This class represents a Date object. It extends java.util.Date.
 * It has three fields: day, month, and year.
 */
public class Date extends java.util.Date {

    int day;
    int month;
    int year;

    /**
     * Constructs a Date object with the specified day, month, and year.
     * If the date is not valid, it throws an IllegalArgumentException.
     *
     * @param day   the day of the date
     * @param month the month of the date
     * @param year  the year of the date
     */
    public Date(int day, int month, int year) {
        if (!isValid(day, month, year)) {
            throw new IllegalArgumentException("Invalid date.");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Constructs a Date object with the specified date in long format.
     * If the date is not valid, it throws an IllegalArgumentException.
     *
     * @param date the date in long format
     */
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

    /**
     * Checks if the specified day, month, and year form a valid date.
     *
     * @param day   the day of the date
     * @param month the month of the date
     * @param year  the year of the date
     * @return true if the date is valid, false otherwise
     */
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

    /**
     * Checks if the specified year is a leap year.
     *
     * @param year the year to check
     * @return true if the year is a leap year, false otherwise
     */
    private boolean isLeap(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    /**
     * Checks if this Date is equal to the specified Date.
     *
     * @param other the Date to compare with
     * @return true if this Date is equal to the other Date, false otherwise
     */
    public boolean isEqual(Date other) {
        return this.day == other.day && this.month == other.month && this.year == other.year;
    }

    /**
     * Checks if this Date is after the specified Date.
     *
     * @param other the Date to compare with
     * @return true if this Date is after the other Date, false otherwise
     */
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

    /**
     * Returns a clone of this Date.
     *
     * @return a clone of this Date
     */
    public Date clone() {
        return new Date(this.day, this.month, this.year);
    }

    /**
     * Returns a string representation of this Date.
     *
     * @return a string representation of this Date
     */
    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    /**
     * Returns a new Date that adds the specified number of years to this Date.
     *
     * @param i the number of years to add
     * @return a new Date that adds the specified number of years to this Date
     */
    public Date plusYears(int i) {
        return new Date(this.day, this.month, this.year + i);
    }

    public boolean isValid() {
        return isValid(this.day, this.month, this.year);
    }
}
