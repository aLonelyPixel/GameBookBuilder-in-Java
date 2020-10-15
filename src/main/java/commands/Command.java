package commands;

/**
 * This abstract class defines the main structure of a Command which upon initilisation
 * while be further complemented with different overrides depending on the type of Command
 * 
 * @author Andrea Dal Molin
 *
 */
public abstract class Command {

	private final String name;
	private final String description;

	/**
	 * Creates a command based on a name and a description
	 * 
	 * @param name, the name to be given to the Command
	 * @param description, the description to be given to the Command
	 */
	public Command(final String name, final String description) {
		this.name = name;
		this.description = description;
	}

	/**
	 * Getter for the name of the command
	 * @return the name of the Command
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter for the description of the Command
	 * @return the description of the Command
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Returns true only if the Command's name matches (ignoring case) the name parameter
	 * @param name, the name to be compared to the Command's name
	 * @return true if the Command's name and name match, false otherwise
	 */
	public boolean hasName(final String name) {
		return (this.name.compareToIgnoreCase(name) == 0);
	}

	/**
	 * Abstract method that will be completed for each individual type of Command
	 */
	public abstract void execute();
	
}
