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
	 * Adds an object of type T to data
	 */
	public void add(T p);
	
	/**
	 * Removes an object of type T from data
	 */
	public void remove(T p);
	
	/**
	 * Replaces an object of type T in data
	 */
	public void replace(T origEl, T newEl);
	
	/**
	 * Method so Game Data can get the data about the objects of type T
	 */
	public List<T> getData();

	
}
