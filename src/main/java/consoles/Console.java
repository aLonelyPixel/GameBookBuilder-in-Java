package consoles;

/**
 * Définit les méthodes d'interactions avec l'utilisateur.
 * */
public interface Console {
	/**
	 * Affiche un texte mis en forme à l'utilisateur, lit une ligne encodée par l'utilisateur et la retourne.
	 * 
	 * @param fmt un modèle de texte. Les règles de description du modèle sont définies dans <a href="https://docs.oracle.com/javase/10/docs/api/java/util/Formatter.html#syntax">ce document</a>.
	 * @args les arguments utilisés pour mettre en forme le texte.
	 * @return la ligne encodée par l'utilisateur sans les caractères de sauts de lignes.
	 * */
	String readLine(String fmt, Object... args);
	
	/**
	 * Affiche un texte mis en forme à l'utilisateur, lit une ligne encodée par l'utilisateur et tente de la convertir en int qu'elle retourne.
	 * La méthode répète la demande jusqu'à ce que l'utilisateur encode un texte convertible en int.
	 * 
	 * @param fmt un modèle de texte. Les règles de description du modèle sont définies dans <a href="https://docs.oracle.com/javase/10/docs/api/java/util/Formatter.html#syntax">ce document</a>.
	 * @args les arguments utilisés pour mettre en forme le texte.
	 * @return l'entier encodé par l'utilisateur.
	 * */
	int readInteger(String fmt, Object... args);
	
	/**
	 * Affiche un texte mis en forme à l'utilisateur et passe à la ligne suivante.
	 * 
	 * @param fmt un modèle de texte. Les règles de description du modèle sont définies dans <a href="https://docs.oracle.com/javase/10/docs/api/java/util/Formatter.html#syntax">ce document</a>.
	 * @args les arguments utilisés pour mettre en forme le texte.
	 */
	void printLine(String fmt, Object... args);

	/**
	 * Affiche un texte mis en forme à l'utilisateur.
	 * 
	 * @param fmt un modèle de texte. Les règles de description du modèle sont définies dans <a href="https://docs.oracle.com/javase/10/docs/api/java/util/Formatter.html#syntax">ce document</a>.
	 * @args les arguments utilisés pour mettre en forme le texte.
	 */
	void print(String fmt, Object... args);

	/**
	 * Passe à la ligne suivante.
	 */
	void printLine();

	/**
	 * Termine l'exécution de l'application.
	 */
	void exit();
}
