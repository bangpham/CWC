package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sheng on 9/27/16.
 * Represents a single account in the system
 *
 * Information Holder
 *
 */
public class Account {

    private String address = ""; //the address of the user
    private String phone = ""; //the phone number of the user
    private final StringProperty _id = new SimpleStringProperty();
    private final StringProperty _fname = new SimpleStringProperty();
    private final StringProperty _lname = new SimpleStringProperty();
    private final StringProperty _email = new SimpleStringProperty();
    private final StringProperty _phone = new SimpleStringProperty();
    private final StringProperty _address = new SimpleStringProperty();
    private final ObjectProperty<AccountType> _type = new SimpleObjectProperty<>();

    public static final Map<String, Account> accountList = new HashMap<String, Account>();


    /**
     * a method to get the ID of the user
     * @return the ID of the user
     */
    public String getId() {return _id.get();}

    /**
     * a method to set the user ID
     * @param id the String to be set as the ID
     */
    public void setId(String id) { _id.set(id); }

    /**
     * a method to get the first name of the user
     * @return the first name of the user
     */
    public String getFname() {return _fname.get();}

    /**
     * a method to set the first name of the user
     * @param fname the first name of the user
     */
    public void setFname(String fname) { _fname.set(fname); }

    /**
     * a method to get the last name of the User
     * @return the last name of the user
     */
    public String getLname() {return _lname.get(); }

    /**
     * a method to set the last name of the User
     * @param lname the last name of the user
     */
    public void setLname(String lname) { _lname.set(lname); }

    /**
     * a method to get the e-mail address of the user
     * @return the user's e-mail address
     */
    public String getEmail() {return _email.get();}

    /**
     * a method to set the e-mail address of the User
     * @param email the e-mail to be set
     */
    public void setEmail(String email) { _email.set(email); }


    /**
     * a method to get the type of the User
     * types include: User, Worker, Manager, Admin
     * @return the type of User
     */
    public AccountType getType() {return _type.get();}

    /**
     * a method to set the user account type
     * @param type the Enum of the account type
     */
    public void setType(AccountType type) { _type.set(type); }

    /**
     * @return the address
     */
    public String getAddress() {
        return _address.get();
    }

    /**
     * @return the phone number
     */
    public String getPhone() {
        return _phone.get();
    }
    /**
     * a method to we haven't used yet
     * @return the type from the combo box in M3
     */
    public ObjectProperty getAccountTypeProperty() { return _type; }

    /**
     * A  the default constructor of the user
     */
    public Account(String id, String fname, String lname, String email, AccountType type) {

     this(id, fname, lname, email, type, "", "");

    }
    /**
     * the constructor of user if the user included their phone number
     * and address
     */
    public Account(String id, String fname, String lname, String email, AccountType type, String phone, String address) {

        _id.set(id);
        _fname.set(fname);
        _lname.set(lname);
        _email.set(email);
        _type.set(type);
        _phone.set(phone);
        _address.set(address);
    }

    /**
     * a method to create the account of the user
     * @param id the ID of the user
     * @return the newly created account
     */
    public static Account of(String id) {
        Account account = accountList.get(id);
        if (account == null) {
            account = new Account(id);
            accountList.put(id, account);
        }
        return account;
    }

    /**
     * a method to set the ID of the account
     * @param id the new ID of the user
     */
    private Account(String id) {
        _id.set(id);
    }

    /**
     * a method to get the map that holds the users
     * @return the map of the users
     */
    public Map<String, Account> getAccountList() {
        return accountList;
    }

    /**
     * a method to easily toString the account
     * @return the String created from the account
     */
    public String toString() {
        return _id.get();
    }


    /**
     * a method to set phone number of the account
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }


    /**
     * a method to set address of the account
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }


}
