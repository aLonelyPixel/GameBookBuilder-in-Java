package commands;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class creates a Map of Commands to be used inside the program.
 * It contains two constructors so that a CommandMap can be initialised
 * either with a Collection of Commands or with an ellipse of Commands
 * 
 * I've chosen to use a Map because of its useful key-value system. The
 * CommandMap is going to be used to store Commands, which are going to
 * be initialised at the start of the Program. Hence, we do not need the
 * add/remove features after the initialisation of a CommandMap, as they
 * are not needed for any feature of the program (the User is never going
 * to add or remove Commands). From a utility point of view, the most
 * recurrent method call is going to be the get(K) method. The different
 * Commands don't need to be ordered so we don't need a SortedMap. The risk
 * of collisions amongst Commands is extremely low, given the fact that the
 * number of different Commands is relatively low and that they are hardcoded
 * by the programmer, which is going to avoid to assign same keys/values
 * to the different Commands. Because of this, a Map is better than a List
 * or a Set or a Queue. A Queue is more suited to hold elements prior to
 * processing, a List generally allow duplicate elements which is not
 * suitable here. While Set doesn't allow duplicates and could be suitable 
 * here (particularly a HashSet) I've personally chose a Map because of 
 * its key-value system. Although it's interesting to point out that the
 * time complexity for a Map or a Set would have been identical for the
 * use we make of the Collection (add and get).
 * 
 * A HashMap implementation has been chosen because the elements of the Map
 * don't need to been ordered so there's no need for a SortedMap interface.
 * Secondly, since the get(K) method is going to be used the most, a HashMap
 * is the most efficient choice, since its theoretical time complexity is
 * O(1) (given the fact there will be no collisions. For reference, a TreeMap,
 * a different implementation of the Map interface is going to have O(log2N)
 * complexity for the get(K)/containsKey methods. TreeMap also has the same 
 * O(log2N) complexity for the put(k,v) method. A HashMap is then the most 
 * suitable choice since it has O(1) complexity for both put(k,v) and get(k),
 * which are the only methods used on the Map. 
 * 
 * As a side note, the values() method is also used for the HashMap. Its goal
 * is to return a Collection of the items contained in the Map, so that we
 * can use an Iterator on it. Its theoretical time complexity is in O(N)
 * since it's going to transversally iterate through each element.
 * 
 * @author Andrea Dal Molin
 *
 */
public class CommandMap implements Iterable<Command>{

	private final Map<String, Command> commandsMap;

	/**
	 * Constructor that is used when a Collection of Commands is sent upon
	 * initialisation.
	 * 
	 * @param commands, the Collection of Commands
	 */
	public CommandMap(final Collection<Command> commands) {
		commandsMap = new HashMap<String, Command>();
		for (final Command command : commands) {
			commandsMap.put(command.getName(), command);
		}
	}

	/**
	 * Constructor that is used when and ellipse of Commands is sent upon
	 * initialisation.
	 * 
	 * @param commands, (self-explanatory, check above)
	 */
	public CommandMap(final Command... commands) {
		commandsMap = new HashMap<String, Command>();
		for (final Command command : commands) {
			commandsMap.put(command.getName(), command);
		}
	}

	/**
	 * This get method uses the HashMap get(K) method. Because the Map calculates the 
	 * hashcode of the key, each key will have a different hashcode. This is counting 
	 * on the fact that there aren't duplicated keys, otherwise same hashcodes will
	 * be placed in the same bucket. The different Commands are organised so that there's
	 * no duplicate values. When called, the get method calculates the hashcode
	 * of the searched key and, if present in the map, will be able to return the Object
	 * searched, otherwise, it will return null. Time complexity is then O(1), since there
	 * will be no duplicate values (in that case complexity would be O(N) in the worst case)
	 * 
	 * @param key, the searched key value
	 * @return the found Command if present, null otherwise
	 */
	public Command get(final String key) {
		if (commandsMap.get(key)!=null) {
			return commandsMap.get(key);
		}
		return null;
	}

	@Override
	/**
	 * Returns a new iterator to iterate through all Commands
	 * @return an Iterator that goes through all the Commands
	 */
	public Iterator<Command> iterator() {
		return commandsMap.values().iterator();
	}
}
