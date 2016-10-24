package model;
/**
 * Created by Sheng on 9/27/16.
 * Worker to implement the required functionality
 */
public class Worker extends User {

    /**
     * Create new worker
     */
    public Worker(String id, String fname, String lname, String email, AccountType type) {
        super(id, fname, lname, email, type);
    }
}
