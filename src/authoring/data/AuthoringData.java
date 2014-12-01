package authoring.data;

import java.util.List;

/**
 * Interface for data stored in the authoring environment
 * to allow for ease of saving into JSON
 * @author Jennie Ju, Rica Zhang
 *
 * @param <T> - Type of object to be stored in data
 */
public interface AuthoringData<T> {

	/**
	 * Adds a variable number of objects of type T to data
	 */
	public void add(T...args);
	
	/**
	 * Removes a variable number of objects of type T from data
	 */
	public void remove(T...args);
	
	/**
	 * Clears the data
	 */
	public void clear();
	
	/**
	 * Method so Game Data can get the data about the objects of type T
	 */
	public List<T> get();
}
