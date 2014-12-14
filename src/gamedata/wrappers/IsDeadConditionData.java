package gamedata.wrappers;

/**
 * Data wrapper for the IsDeadCondition
 * @author annamiyajima
 *
 */
public class IsDeadConditionData {
    String myHealthName;
    String myDescription;
    
    public IsDeadConditionData(String healthName, String description){
        myHealthName = healthName;
        myDescription = description;
    }
    
    public String toString(){
        return "IsDead: health name = " + myHealthName + " descrption = " + myDescription; 
    }
}
