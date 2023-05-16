package cpsc2150.MyVector;

import java.util.Scanner;
/**
 * Name: Abigail Poropatich
 * Date Submitted: 18 April 2023
 * Section: 1
 * Assignment Name: Lab Final Exam
 */
/**
 * <p>
 * An application that uses {@link IVector}.
 * </p>
 */
public class DoubleVectorApp {

    /**
     * <p>
     * Choices for vector methods
     * </p>
     */
    private static final int ADDELEMENT_CHOICE = 1;
    private static final int REMOVEELEMENT_CHOICE = 2;
    private static final int CONTAINS_CHOICE = 3;
    private static final int GET_CHOICE = 4;
    private static final int SWAP_CHOICE = 5;
    private static final int LENGTH_CHOICE = 6;
    private static final int CLEAR_CHOICE = 7;
    private static final int EXIT_CHOICE = 8;

    /**
     * <p>
     * Choices for vector implementations
     * </p>
     */
    private static final int ARRAY_VECTOR_CHOICE = 1;
    private static final int LIST_VECTOR_CHOICE = 2;

    /**
     * <p>
     * This method is our main entry point to our application.
     * </p>
     *
     * @param args
     *            Program arguments (not used)
     */
    public static void main(String[] args) {
        IVector<Double> v;
        double val;
        int pos;
        Scanner sc = new Scanner(System.in);

        // prompt the user to choose between ArrayVector and ListVector
        int option;
        do {
            System.out.println("Enter " + ARRAY_VECTOR_CHOICE + " for array implementation or " + LIST_VECTOR_CHOICE
                    + " for List implementation");
            option = Integer.parseInt(sc.nextLine());

        } while (option != ARRAY_VECTOR_CHOICE && option != LIST_VECTOR_CHOICE);

        // User can choose between ArrayVector and ListVector
        if(option == 1)
            v = new ArrayVector<>();
        else v = new ListVector<>();

        // Print a menu of options
        printMenu();
        int input = Integer.parseInt(sc.nextLine());
        while (input != EXIT_CHOICE) {
            switch (input) {
                case ADDELEMENT_CHOICE:
                    //check that the vector is not at max capacity before trying to add another element
                    if(v.length() >= v.MAX_LENGTH){
                        System.out.println("Vector is full. Cannot add any more elements.");
                        break;
                    }
                    System.out.println("What value to add to the end of the vector?");
                    v.addElement(Double.parseDouble(sc.nextLine()));
                    break;
                case REMOVEELEMENT_CHOICE:
                    //ensure the vector is not empty before trying to access a variable at the front
                    if(v.length() == 0){
                        System.out.println("Vector is empty.");
                        break;
                    }
                    System.out.println("Value at the front: " + v.removeElement());
                    break;
                case CONTAINS_CHOICE:
                    System.out.println("What value to check if it is in the vector?");
                    val = Double.parseDouble(sc.nextLine());

                    // prints the appropriate message
                    if (v.contains(val)) {
                        System.out.println(val + " was found in the vector.");
                    } else {
                        System.out.println(val + " wasn't found in the vector.");
                    }
                    break;
                case GET_CHOICE:
                    //before the user can get a value, check that the vector is not empty
                    if(v.length() == 0){
                        System.out.println("Vector is empty.");
                        break;
                    }
                    System.out.println("What position to get from the vector?");
                    pos = Integer.parseInt(sc.nextLine());

                    //check that the entered position is valid before performing the retrieval
                    while(!(pos >= 0 && pos <= v.length())){
                        System.out.println("Please enter a valid position.");
                        pos = Integer.parseInt(sc.nextLine());
                    }

                    // get value from the specified position
                    System.out.println(v.get(pos) + " is at position " + pos + " in the vector.");
                    break;
                case SWAP_CHOICE:
                    //before the user can swap a value, check that the vector is not empty
                    if(v.length() == 0){
                        System.out.println("Vector is empty.");
                        break;
                    }
                    System.out.println("What value to swap into the vector?");
                    val = Double.parseDouble(sc.nextLine());

                    System.out.println("What position to swap to?");
                    pos = Integer.parseInt(sc.nextLine());
                    //check that the entered position is valid before performing the swap
                    while(!(pos >= 0 && pos <= v.length())){
                        System.out.println("Please enter a valid position.");
                        pos = Integer.parseInt(sc.nextLine());
                    }

                    // swap the value from the specified position
                    double valInVector = v.swap(val, pos);
                    System.out.println(valInVector + " was swapped out from position " + pos + " in the vector.");
                    break;
                case LENGTH_CHOICE:
                    System.out.println("Length of vector: " + v.length());
                    break;
                case CLEAR_CHOICE:
                    v.clear();
                    System.out.println("Vector is now empty!");
                    break;
                default:
                    System.out.println("Not a valid option!");
                    break;
            }

            // print the contents of the vector
            System.out.println("Vector is: ");
            System.out.println(v.toString());

            // print the menu and get the next option
            printMenu();
            input = Integer.parseInt(sc.nextLine());
        }
    }

    /**
     * <p>
     * This method will print the options menu.
     * </p>
     *
     * @pre none
     *
     * @post [menu will be displayed to user]
     */
    private static void printMenu() {
        System.out.println("\nSelect an option:");
        System.out.println(ADDELEMENT_CHOICE + ". Add to the end of the vector");
        System.out.println(REMOVEELEMENT_CHOICE + ". Remove from the front of the vector");
        System.out.println(CONTAINS_CHOICE + ". Checks to see if value is in the vector");
        System.out.println(GET_CHOICE + ". Get a position in the vector");
        System.out.println(SWAP_CHOICE + ". Swap a value with a position in the vector");
        System.out.println(LENGTH_CHOICE + ". Get the length of the vector");
        System.out.println(CLEAR_CHOICE + ". Clear the vector");
        System.out.println(EXIT_CHOICE + ". Quit");
    }
}