import java.util.LinkedList;
import java.util.List;

public class QuoteLibrary{
    private List<QuoteAuto> AutoList;
    private List<QuoteHome> HomeList;

    public QuoteLibrary(){
        this.AutoList = new LinkedList<>();
        this.HomeList = new LinkedList<>();
    }

    public QuoteLibrary(LinkedList<QuoteAuto> autoList, LinkedList<QuoteHome> homeList) {
        this.AutoList = autoList;
        this.HomeList = homeList;
    }

    public List<QuoteAuto> getAutoList(){
        return AutoList;
    }
    public void setAutoList(List<QuoteAuto> AutoList){
        this.AutoList = AutoList;
    }
    public List<QuoteHome> getHomeList(){
        return HomeList;
    }
    public void setHomeList(List<QuoteHome> HomeList){
        this.HomeList = HomeList;
    }

}
