
/**
 * Name: Zachary Supina, Abigail Poropatich
 * Date Submitted: 4/04/2023
 * Section: 1
 * Assignment Name: Model View Controller (Lab 11)
 */
package cpsc2150.banking.controllers;

/**
 * This interface is the Controller that partners with IMortgageView
 *
 * Defines:
 *		View: The IMortgageView
 *
 * Initialization ensures: View != NULL
 */
public interface IMortgageController {

    /**
     * This will handle the processing of a mortgage application
     *
     * @post [ Mortgage application will be processed and result will be
     * rendered to the user ]
     */
    void submitApplication();
}

