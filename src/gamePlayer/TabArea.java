package gamePlayer;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabArea{
    
    private TabPane tabPane;
    
    public TabPane setUpTabArea(){
        tabPane = new TabPane();
        setUpControlTab();
        setUpStatsTab();
        
        return tabPane;
    }
    
    public void setUpControlTab() {
        makeTab("Control");
        
    }
    
    public void setUpStatsTab() {
        makeTab("Stats");
    }
    
    public void makeTab(String tabLabel) {
        Tab tab = new Tab();
        tab.setText(tabLabel);
        tabPane.getTabs().add(tab);
    }
}
