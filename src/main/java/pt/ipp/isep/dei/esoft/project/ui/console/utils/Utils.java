package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Date;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 *     <p>
 *     Utility class.
 *     <p>
 *     Updated by Rui Silva 1231501@isep.ipp.pt
 */
/**
 * Utility class for console input and output operations.
 */
public class Utils {

    /**
     * Reads a line of text from the console.
     * @param prompt the prompt to display before reading the input
     * @return the line of text read from the console, or null if an error occurred
     */
    static public String readLineFromConsole(String prompt) {
        try {
            System.out.print("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reads an integer from the console.
     * @param prompt the prompt to display before reading the input
     * @return the integer read from the console
     */
    static public int readIntegerFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    /**
     * Reads a double from the console.
     * @param prompt the prompt to display before reading the input
     * @return the double read from the console, or -1 if an error occurred
     */
    static public double readDoubleFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);
                if(input == null)
                    return -1;
                return Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    /**
     * Reads a date from the console.
     * @param prompt the prompt to display before reading the input
     * @return the date read from the console, or null if an error occurred
     */
    static public Date readDateFromConsole(String prompt) {
        do {
            String date = readLineFromConsole(prompt);
            if (date == null)
                return null;
            String day = date.substring(0, 2);
            String month = date.substring(3, 5);
            String year = date.substring(6, 10);
            try{
                return new Date(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
            }catch (RuntimeException e){
                System.out.println("Invalid date format. Please try again.");
            }
        } while (true);
    }

    /**
     * Confirms a message with the user.
     * @param message the message to confirm
     * @return true if the user confirmed the message, false otherwise
     */
    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!Objects.requireNonNull(input).equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("s");
    }

    /**
     * Displays a list of options and prompts the user to select one.
     * @param list the list of options
     * @param header the header to display before the list of options
     * @return the selected option
     */
    static public Object showAndSelectOne(List list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    /**
     * Displays a list of options and prompts the user to select one.
     * @param list the list of options
     * @param header the header to display before the list of options
     * @return the index of the selected option
     */
    static public int showAndSelectIndex(List list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    /**
     * Displays a list of options.
     * @param list the list of options
     * @param header the header to display before the list of options
     */
    static public void showList(List list, String header) {
        System.out.println(header);

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.println("  " + index + " - " + o.toString());
        }
        //System.out.println();
        System.out.println("  0 - Return\n");
    }

    /**
     * Prompts the user to select an option from a list.
     * @param list the list of options
     * @return the selected option, or null if the user selected to return
     */
    static public Object selectsObject(List list) {
        String input;
        int value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.parseInt(input);
        } while (value < 0 || value > list.size());

        if (value == 0) {
            return null;
        } else {
            return list.get(value - 1);
        }
    }

    /**
     * Prompts the user to select an option from a list.
     * @param list the list of options
     * @return the index of the selected option
     */
    static public int selectsIndex(List list) {
        String input;
        int value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            try {
                value = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    /**
     * Prompts the user to answer a yes/no question.
     * @param header the question to ask
     * @return true if the user answered yes, false otherwise
     */
    public static boolean getBooleanAnswer(String header) {
        ArrayList<String> yes_or_no = new ArrayList<>();
        yes_or_no.add("Yes");
        yes_or_no.add("No");

        int in = showAndSelectIndex(yes_or_no, header);
        return in == 0;
    }

    /**
     * Converts a string to a date.
     * @param sDate the string to convert
     * @return the converted date
     */
    public static Date dateFromString(String sDate){
        String day = sDate.substring(0, 2);
        String month = sDate.substring(3, 5);
        String year = sDate.substring(6, 10);
        return new Date(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
    }

    /**
     * Prompts the user to input an address.
     * @return the input address
     */
    public static Address requestAddress() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Address--\n");
        System.out.println("Street: ");
        String street = input.nextLine();
        System.out.println("\nDoor Number: ");
        int doorNumber = input.nextInt();
        input.nextLine();
        System.out.println("\nPostal Code: ");
        String postalCode = input.nextLine();
        System.out.println("\nCity: ");
        String city = input.nextLine();
        System.out.println("\nDistrict: ");
        String district = input.nextLine();
        return new Address(street, doorNumber, postalCode, city, district);
    }

}