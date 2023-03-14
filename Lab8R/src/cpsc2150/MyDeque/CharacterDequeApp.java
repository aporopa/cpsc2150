package cpsc2150.MyDeque;
import java.util.*;

/**
 * Name: Zachary Supina, Abigail Poropatich
 * Date Submitted: 03/10/2023
 * Section: 1
 * Assignment Name: JUnit testing (Lab 8)
 */

public class CharacterDequeApp {
    public static void main(String[] args) {
        IDeque q;
        Scanner read = new Scanner(System.in);
        String input = " ";
        Integer choice = 0;
        Character userChar = ' ';
        Integer userPos = 0;
        String menu = "\nSelect an option:\n" +
                "1. Add to the end of the Deque\n" +
                "2. Add to the front of the Deque \n" +
                "3. Remove from the front of the Deque\n" +
                "4. Remove from the end of the Deque\n" +
                "5. Peek from the front of the Deque\n" +
                "6. Peek from the end of the Deque\n" +
                "7. Insert to a position in the Deque\n" +
                "8. Remove from a position in the Deque\n" +
                "9. Get a position in the Deque\n" +
                "10. Get the length of the Deque\n" +
                "11. Clear the Deque\n" +
                "12. Quit\n";

/*
Prompt  the  user  to  pick  an  implementation  using  the  following
message:  "Enter  1  for  array  implementation  or  2  for  List
implementation"
Your code needs to make sure that they only enter either 1 or 2.
Re-print the message to prompt the user to enter it again. Once
you have gotten an answer, use it to initialize q appropriately.
*/
        //Prompt the user
        System.out.println("Enter 1 for array implementation or 2 for List implementation");
        input = read.nextLine();
        choice = Integer.parseInt(input);

        //Continue to prompt the user until either one or two is entered
        while ((choice != 1) && (choice != 2)) {
            System.out.println("Enter 1 for array implementation or 2 for List implementation");
            input = read.nextLine();
            choice = Integer.parseInt(input);
        }

        //choice one is correlated with ArrayDeque
        if (choice == 1) {
            q = new ArrayDeque();
        }

        //choice one is correlated with ListDeque
        else {
            q = new ListDeque();
        }

        //Print menu
        System.out.print(menu);
        input = read.nextLine();
        choice = Integer.parseInt(input);

        //To enter the switch, user must choose a vale
        //between 1 and 11 (inclusive)
        while (choice != 12) {
            switch (choice) {

                //if the deque is not full
                //then enqueue the users input
                case 1:
                    if (q.length() == IDeque.MAX_LENGTH) {
                        System.out.println("Deque is full!");
                    } else {
                        System.out.println("What character to enqueue to the end of the Deque?");
                        input = read.nextLine();
                        userChar = input.charAt(0);
                        q.enqueue(userChar);
                    }
                    break;

                //If the deque is not full
                //then inject a character at the front of the deque
                case 2:
                    if (q.length() == IDeque.MAX_LENGTH) {
                        System.out.println("Deque is full!");
                    } else {
                        System.out.println("What character to inject to the front of the Deque?");
                        input = read.nextLine();
                        userChar = input.charAt(0);
                        q.inject(userChar);
                    }
                    break;

                //If the deque has at least one element
                //dequeue the character at the front
                //if the deque is empty, tell the user
                case 3:
                    if (q.length() >= 1) {
                        System.out.println("Character at the front: " + q.dequeue());
                    } else {
                        System.out.println("Deque is empty!");
                    }
                    break;

                //If the deque has at least one element
                //remove the character at the end
                //if the deque is empty, tell the user
                case 4:
                    if (q.length() >= 1) {
                        System.out.println("Character at the end: " + q.removeLast());
                    } else {
                        System.out.println("Deque is empty!");
                    }
                    break;

                //If the deque has at least one element
                //allow the user to see, but not alter the first element
                case 5:
                    if (q.length() >= 1) {
                        System.out.println("Peek: " + q.peek());
                    } else {
                        System.out.println("Deque is empty!");
                    }
                    break;

                //If the deque has at least one element
                //allow the user to see, but not alter the last element
                case 6:
                    if (q.length() >= 1) {
                        System.out.println("EndOfDeque: " + q.endOfDeque());
                    } else {
                        System.out.println("Deque is empty!");
                    }
                    break;

                //Allows the user to enter an character at a specific location in the deque
                //so long at it is not full or an invalid location
                case 7:
                    if (q.length() == IDeque.MAX_LENGTH) {
                        System.out.println("Deque is full!");
                    } else {
                        System.out.println("What character to insert to the Deque?");
                        input = read.nextLine();
                        userChar = input.charAt(0);
                        System.out.println("What position to insert in?");
                        input = read.nextLine();
                        userPos = Integer.parseInt(input);
                        while ((userPos > (q.length() + 1)) || (userPos < 1)) {
                            System.out.println("Not a valid position in the Deque!");
                            System.out.println("What position to insert in?");
                            input = read.nextLine();
                            userPos = Integer.parseInt(input);
                        }
                        q.insert(userChar, userPos);
                    }
                    break;

                //Allows the user to remove a character from the deque
                //so long as it is a valid position, and the deque is not empty
                case 8:
                    if (q.length() >= 1) {
                        System.out.println("What position to remove from the Deque?");
                        input = read.nextLine();
                        userPos = Integer.parseInt(input);
                        while ((userPos > q.length()) || (userPos < 1)) {
                            System.out.println("Not a valid position in the Deque!");
                            System.out.println("What position to remove from the Deque?");
                            input = read.nextLine();
                            userPos = Integer.parseInt(input);
                        }
                        System.out.println(q.remove(userPos) + " was at position " + userPos + " in the Deque.");
                    } else {
                        System.out.println("Deque is empty!");
                    }
                    break;

                //Allows the user to pick a location in the deque
                //and see what character is there
                //so long as the deque has at least one element
                case 9:
                    if (q.length() >= 1) {
                        System.out.println("What position to get from the Deque?");
                        input = read.nextLine();
                        userPos = Integer.parseInt(input);
                        while ((userPos > q.length()) || (userPos < 1)) {
                            System.out.println("Not a valid position in the Deque!");
                            System.out.println("What position to get from the Deque?");
                            input = read.nextLine();
                            userPos = Integer.parseInt(input);
                        }
                        System.out.println(q.get(userPos) + " is at position " + userPos + " in the Deque.");
                    } else {
                        System.out.println("Deque is empty!");
                    }
                    break;

                //Prints the length of the deque
                case 10:
                    System.out.println("Length of Deque: " + q.length());
                    break;

                //Clears the deque
                case 11:
                    q.clear();
                    System.out.println("Deque is now empty!");
                    break;

                default:
                    System.out.println("Not a valid option!");
                    break;

            }

            //Prints the deque in its required format
            System.out.println("Deque is:");
            System.out.println(q.toString());
            System.out.print(menu);
            input = read.nextLine();
            choice = Integer.parseInt(input);
        }
    }
}