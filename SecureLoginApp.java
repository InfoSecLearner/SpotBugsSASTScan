import java.util.Scanner;
import java.nio.charset.StandardCharsets;

/**
 * Author: InfoSecLearner
 * Date: 3/24/2026
 * This simple console login app demonstrates basic secure coding practices.
 */

public class SecureLoginApp
{
    /*
     * Named constants: fixed login credentials for authentication.
     *
     * Note: This is not a secure coding practice, but it keeps the app simple.
     * Hardcoded credentials should not be used in production environments.
     */

    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "SecurePass123";

    /**
     * Runs the program: gets input, validates it, authenticates, and handles errors safely.
     */

    public static void main(String[] args)
    {
        // Scanner for reading user input
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        try
        {
            // Prompt user and validate username/password input
            String username = getValidatedInput(scanner, "Enter username: ");
            String password = getValidatedInput(scanner, "Enter password: ");

            // Authenticate without revealing which field was incorrect
            if (authenticate(username, password))
            {
                System.out.println("Login successful.");
            } else
            {
                System.out.println("Your username or password is incorrect.");
            }
        } catch (Exception e)
        {
            // Display a safe error message (no stack trace or internal details)
            System.out.println("An unexpected error occurred. Please try again.");
        } finally
        {
            // Ensure scanner is closed to avoid resource leaks
            scanner.close();
        }
    }

    /**
     * Reads and validates user input
     * @param scanner The scanner object
     * @param prompt The prompt to display for the user
     * @return The user's validated input
     */

    private static String getValidatedInput(Scanner scanner, String prompt)
    {
        String input;

        System.out.print(prompt);

        // Read full line and remove whitespace
        input = scanner.nextLine().trim();

        // Loop until input contains only letters and numbers
        while (!input.matches("[A-Za-z0-9]+"))
        {
            System.out.println("The input is not valid.");
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }

        return input;
    }

    /**
     * Compares user input to the valid credentials
     * @param username The user's input for the username
     * @param password The user's input for the password
     * @return true if authentication is successful
     */

    private static boolean authenticate(String username, String password)
    {
        return username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD);
    }
}
