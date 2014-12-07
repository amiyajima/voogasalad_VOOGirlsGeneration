package authoring.eventeditor;

import gamedata.events.Condition;
import gamedata.events.GlobalAction;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import utilities.ClassGrabber;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;

public class NewActionController implements Initializable{

        //TODO: How to pass in the list of all Conditions?
        
        @FXML
        private ChoiceBox<String> actionChoiceBox;
        @FXML
        private ScrollPane editorScrollPane;
        
        private List<Class> actionList;
        
        private GlobalAction myGlobalAction;
        private Consumer<GlobalAction> myDoneLambda;
        
        @Override
        public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
                
                //TODO: Remove print stack traces. Add in error windows
                actionList = new ArrayList<>();
                try {
                        actionList = Arrays.asList(ClassGrabber.getClasses("gamedata.events.globalaction"));
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                
                List<String> displayList = new ArrayList<>();
                for(Class<?> c: actionList){
                        displayList.add(c.toString());
                }
                displayList = trimClassList(displayList);
                
                actionChoiceBox.getItems().addAll(displayList);
                
                actionChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                                (observable, oldValue, selectedType) -> showActionEditorPane());
        }

        private List<String> trimClassList(List<String> actionList) {
                List<String> displayList = new ArrayList<>();
                for(String s: actionList){
                        String trimmed = trimClassPaths(s);
                        displayList.add(trimmed);
                }
                return displayList;
        }
        
        private void showActionEditorPane(){
                int idx = actionChoiceBox.getSelectionModel().getSelectedIndex();
                Class<?> c = actionList.get(idx);
                
                /**
                 * If statements to choose which Condition Editor to pull up
                 */
                if("class gamedata.events.ConditionEquals".equals(c.getSuperclass().toString())){
                }
                
                if(myGlobalAction==null){
                        editorScrollPane.setContent(new ActionEditorPane(myDoneLambda));
                }
                else{
                        editorScrollPane.setContent(new ActionEditorPane(myDoneLambda, myGlobalAction));
                }
        }
        
        /**
         * Removes the classpath prefixes for each Condition name
         * @param s
         */
        private String trimClassPaths(String s){
                int idx = 0;
                for(int i=s.length()-1; i>=0; i--){
                        if(s.charAt(i)=='.'){
                                idx = i;
                                break;
                        }
                }
                String trimmed = s.substring(idx+1);
                return trimmed;
        }

        public void loadLambda(Consumer<GlobalAction> okLambda) {
                myDoneLambda = okLambda;
        }
        
        /**
         * Loads the data of a Condition into the editor.
         * Used when editing a previous condition, rather than creating a new one from scratch
         * @param entry
         */
        public void loadEntryCondition(GlobalAction entry){
                myGlobalAction = entry;
        }
}
