import java.util.LinkedList;

/**
 * 'BLUEPRINT'
 * Class for customer records from db
 */

public class Customer {

    // VARIABLES
    private int id;
    private String nameLast;
    private String nameFirst;
    private LinkedList<QuoteAuto> autoList = new LinkedList<>();
    private LinkedList<QuoteHome> homeList = new LinkedList<>();

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

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public void setAutoList(LinkedList<QuoteAuto> autoList) {
        this.autoList = autoList;
    }

    public void setHomeList(LinkedList<QuoteHome> homeList) { this.homeList = homeList; }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getNameLast() {
        return nameLast;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public LinkedList<QuoteAuto> getAutoList() {
        return autoList;
    }

    public LinkedList<QuoteHome> getHomeList() {
        return homeList;
    }

    // METHODS

    public void addQuoteAuto(QuoteAuto quoteAuto) {
        if (quoteAuto != null) {
            autoList.add(quoteAuto);
        }
    }

    public void addQuoteHome(QuoteHome quoteHome) {
        if (quoteHome != null) {
            homeList.add(quoteHome);
        }
    }

}
