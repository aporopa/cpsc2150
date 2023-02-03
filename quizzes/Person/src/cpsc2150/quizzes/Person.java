/**
 * Translate C++ class into Java class
 *
 * @author Abigail Poropatich
 * @version 1.0
 */

package cpsc2150.quizzes;

public class Person {
  private String firstName;
  private String lastName;

  /**
   * Constructor: sets firstName and lastName according to the parameter.
   *
   * @param first = the string that represents first name
   * @param last = the string that represents last name
   *
   * @pre [ first and last names are valid ]
   * @post firstName = first and lastName = last
   */
  public Person(String first, String last){
    firstName = first;
    lastName = last;
  }

  /**
   * Function to output the first name and last name in the form (with a space separating the two strings): firstName lastName
   *
   * @pre NONE
   * @post [ Output person's name to console ] AND firstName = #firstName AND lastName = #lastName
   */
  public final void print(){
    System.out.println(firstName + " " + lastName);
  }

  /**
   * Function to set firstName and lastName according to the parameters
   *
   * @param first = the string that represents first name
   * @param last = the string that represents last name
   *
   * @pre [ first and last names are valid ]
   * @post firstName = first AND lastName = last
   */
  public void setName(String first, String last){
    firstName = first;
    lastName = last;
  }

  /**
   * Function to return the first name
   * @pre NONE
   * @post getFirstName = firstName AND firstName = #firstName AND lastName = #lastName
   *
   * @return first name
   */
  public final String getFirstName(){
    return firstName;
  }

  /**
   * Function to return the last name
   * @pre NONE
   * @post getLastName = lastName AND firstName = #firstName AND lastName = #lastName
   *
   * @return last name
   */
  public final String getLastName(){
    return lastName;
  }

}
