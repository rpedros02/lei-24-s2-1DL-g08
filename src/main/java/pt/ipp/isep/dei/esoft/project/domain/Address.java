package pt.ipp.isep.dei.esoft.project.domain;

public class Address {

    private String street;
    private int streetNumber;
    private String postalCode;
    private String city;
    private String district;

    public void Address(String street, int streetNumber, String postalCode, String city, String district) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.district = district;
    }



    private enum PostalCodeValidationResults {
        EMPTY,
        VALID,
        CONTAIN_LETTERS,
        INVALID_FORMAT
    }

    private static final int POSTAL_CODE_TOTAL_DIGITS = 8;

    private static final char POSTAL_CODE_SEPARATOR = '-';


    /**
     * Constructs an Address object with the given parameters.
     *
     * @param street       the street of the address
     * @param streetNumber the street number of the address
     * @param postalCode   the postal code of the address
     * @param city         the city of the address
     * @param district     the district of the address
     */
    public Address(String street, int streetNumber, String postalCode, String city, String district) {
        setStreet(street);
        setStreetNumber(streetNumber);
        setPostalCode(postalCode);
        setCity(city);
        setDistrict(district);
    }

    public String getStreet() {
        return street;
    }

    /**
     * Sets the street of the address after validation.
     * If the street to be set is not valid, throws exception.
     *
     * @param street the street to be set
     * @throws IllegalArgumentException if the street is empty
     */
    public void setStreet(String street) {
        if (validateStreet(street)) {
            this.street = street;
        } else {
            throw new IllegalArgumentException("Street must not be empty!");
        }
    }

    /**
     * Returns the street number of the address.
     *
     * @return the street number
     */
    public int getStreetNumber() {
        return streetNumber;
    }

    /**
     * Sets the street number of the address after validation.
     * If the street number to be set is not valid, throws exception.
     *
     * @param streetNumber the street number to be set
     * @throws IllegalArgumentException if the street number is not positive
     */
    public void setStreetNumber(int streetNumber) {
        if (validateStreetNumber(streetNumber)) {
            this.streetNumber = streetNumber;
        } else {
            throw new IllegalArgumentException("Street number must be a positive number!");
        }
    }

    /**
     * Returns the postal code of the address.
     *
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the address after validation.
     * If the postal code is not valid, throws specific exception based on error that occured.
     *
     * @param postalCode the postal code to be set
     * @throws IllegalArgumentException if the postal code is invalid
     */
    public void setPostalCode(String postalCode) {
        PostalCodeValidationResults postalCodeValidationResults = validatePostalCode(postalCode);
        switch (postalCodeValidationResults) {
            case EMPTY:
                throw new IllegalArgumentException("Postal code must not be empty");
            case INVALID_FORMAT:
                throw new IllegalArgumentException("Postal code must follow the format XXXX-XXX");
            case CONTAIN_LETTERS:
                throw new IllegalArgumentException("Postal code must not contain letters");
            case VALID:
                this.postalCode = postalCode;
                break;
        }
    }

    /**
     * Returns the city of the address.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the address after validation.
     * If the city to be set is not valid, throws exception.
     *
     * @param city the city to be set
     * @throws IllegalArgumentException if the city is empty
     */
    public void setCity(String city) {
        if (validateCity(city)) {
            this.city = city;
        } else {
            throw new IllegalArgumentException("City must not be empty");
        }
    }

    /**
     * Returns the district of the address.
     *
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets the district of the address after validation.
     * If the district to be set is not valid, throws exception.
     *
     * @param district the district to be set
     * @throws IllegalArgumentException if the district is empty
     */
    public void setDistrict(String district) {
        if (validateDistrict(district)) {
            this.district = district;
        } else {
            throw new IllegalArgumentException("District must not be empty");
        }
    }

    /**
     * Returns a string representation of the address.
     *
     * @return a string representation of the address in the format:
     *         "Address{street='[street]', streetNumber=[streetNumber], postalCode='[postalCode]', city='[city]', district='[district]'}"
     */
    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", streetNumber=" + streetNumber +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                '}';
    }

    /**
     * Validates if the street is not null or empty.
     *
     * @param street the street to be validated
     * @return the logical state of the validation. True if street is validated (not null and not empty), false otherwise
     */
    private static boolean validateStreet(String street) {
        return street != null && !street.trim().isEmpty();
    }

    /**
     * Validates if street number is valid.
     *
     * @param number the street number to be validated
     * @return true if the street number is a positive intefer, false otherwise
     */
    private static boolean validateStreetNumber(int number) {
        return number > 0;
    }

    /**
     * Validates if the Postal Code is valid with the format XXXX-XXX (where X is an integer).
     * Return enumerated type VALID when the postal code is valid. Possible results when the postal code is not valid include: EMPTY, INVALID_FORMAT, and CONTAIN_LETTERS.
     *
     * @param postalCode the postal code to be validated
     * @return an enumerate type depending on the result
     */
    private static PostalCodeValidationResults validatePostalCode(String postalCode) {

        if (postalCode == null || postalCode.trim().isEmpty()) {
            return PostalCodeValidationResults.EMPTY;
        }

        if (postalCode.length() != POSTAL_CODE_TOTAL_DIGITS || postalCode.charAt(4) != POSTAL_CODE_SEPARATOR) {
            return PostalCodeValidationResults.INVALID_FORMAT;
        }

        char[] postalCodeByLetters = postalCode.toCharArray();

        for (int i = 0; i < 4; i++) {
            if (!Character.isDigit(postalCodeByLetters[i])) {
                return PostalCodeValidationResults.CONTAIN_LETTERS;
            }
        }

        for (int i = 5; i < postalCodeByLetters.length; i++) {
            if (!Character.isDigit(postalCodeByLetters[i])) {
                return PostalCodeValidationResults.CONTAIN_LETTERS;
            }
        }
        return PostalCodeValidationResults.VALID;
    }

    /**
     * Validates if City is not null or empty.
     *
     * @param city the city to be validated
     * @return true if the city is not null or empty, false otherwise
     */
    private static boolean validateCity(String city) {
        return city != null && !city.trim().isEmpty();
    }

    /**
     * Validates if District is not null or empty.
     *
     * @param district the district to be validated
     * @return true if the district is not null or empty, false otherwise
     */
    private static boolean validateDistrict(String district) {
        return district != null && !district.trim().isEmpty();
    }


}