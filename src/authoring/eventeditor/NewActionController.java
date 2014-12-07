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
        
        private GlobalAction myAction;
        private Consumer<GlobalAction> myDoneLambda;
        
        @Override
        public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
                
                //TODO: Remove print stack traces. Add in error windows
                actionList = new ArrayList<>();
                try {
                        actionList = Arrays.asList(ClassGrabber.getClasses("gamedata.events.globalactions"));
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                
                List<String> displayList = new ArrayList<>();
                for(Class<?> c: conditionsList){
                        displayList.add(c.toString());
                }
                displayList = trimClassList(displayList);
                
                conditionChoiceBox.getItems().addAll(displayList);
                
                conditionChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                                (observable, oldValue, selectedType) -> showConditionEditorPane());
        }

        private List<String> trimClassList(List<String> conditionsList) {
                List<String> displayList = new ArrayList<>();
                for(String s: conditionsList){
                        String trimmed = trimClassPaths(s);
                        displayList.add(trimmed);
                }
                return displayList;
        }
        
        private void showConditionEditorPane(){
                int idx = conditionChoiceBox.getSelectionModel().getSelectedIndex();
                Class<?> c = conditionsList.get(idx);
                
                /**
                 * If statements to choose which Condition Editor to pull up
                 */
                if("class gamedata.events.ConditionEquals".equals(c.getSuperclass().toString())){
                }
                
                if(myCondition==null){
                        editorScrollPane.setContent(new ConditionEditorPane(myDoneLambda));
                }
                else{
                        editorScrollPane.setContent(new ConditionEditorPane(myDoneLambda, myCondition));
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

        public void loadLambda(Consumer<Condition> okLambda) {
                myDoneLambda = okLambda;
        }
        
        /**
         * Loads the data of a Condition into the editor.
         * Used when editing a previous condition, rather than creating a new one from scratch
         * @param entry
         */
        public void loadEntryCondition(Condition entry){
                myCondition = entry;
        }
}
