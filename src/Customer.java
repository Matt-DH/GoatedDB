/**
 * 'BLUEPRINT'
 * Class for customer records from db
 */

public class Customer {

    // VARIABLES
    private int id;
    private String nameLast;
    private String nameFirst;

    // CONSTRUCTORS
    public Customer(int id, String nameLast, String nameFirst) {
        this.id = id;
        this.nameLast = nameLast;
        this.nameFirst = nameFirst;
    }

    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public void setNameFirst() {
        this.nameFirst = nameFirst;
    }

    // GETTERS
    public int getId() {
        return this.id;
    }

    public String getNameLast() {
        return this.nameLast;
    }

    public String getNameFirst() {
        return this.nameFirst;
    }

}
