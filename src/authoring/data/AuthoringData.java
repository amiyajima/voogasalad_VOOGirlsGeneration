package authoring.data;

/**
 * Interface for data stored in the authoring environment
 * to allow for ease of saving into JSON
 * @author Jennie Ju
 *
 * @param <T> - Type of object to be stored in data
 */
public interface AuthoringData<T> {

	/**
	 * Adds a new object of type T to data
	 */
	public void add(T arg);
	
	/**
	 * Removes an object of type T from data
	 */
	public void remove(T arg);
	
	/**
	 * Clears the data
	 */
	public void clear();
}
