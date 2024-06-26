package pt.ipp.isep.dei.esoft.project.domain;
/**
 * The Address class represents a physical address.
 * It includes properties for the street, street number, postal code, city, and district.
 * It also includes methods for setting these properties with validation.
 */
public class Address {

    /**
     * The street of the address.
     */
    private String street;

    /**
     * The street number of the address.
     */
    private int streetNumber;

    /**
     * The postal code of the address.
     */
    private String postalCode;

    /**
     * The city of the address.
     */
    private String city;

    /**
     * The district of the address.
     */
    private String district;

    /**
     * Constructs an Address object by copying the properties of another Address object.
     *
     * @param address The Address object to copy.
     */
    public Address(Address address) {
        this.street = address.street;
        this.streetNumber = address.streetNumber;
        this.postalCode = address.postalCode;
        this.city = address.city;
        this.district = address.district;
    }

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

    /**
     * The PostalCodeValidationResults enum represents the possible results of postal code validation.
     */
    private enum PostalCodeValidationResults {
        EMPTY,
        VALID,
        CONTAIN_LETTERS,
        INVALID_FORMAT
    }

    /**
     * The total number of digits in a valid postal code.
     */
    private static final int POSTAL_CODE_TOTAL_DIGITS = 8;

    /**
     * The separator character in a valid postal code.
     */
    private static final char POSTAL_CODE_SEPARATOR = '-';

    /**
     * Retrieves the street of the address.
     *
     * @return The street of the address.
     */
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