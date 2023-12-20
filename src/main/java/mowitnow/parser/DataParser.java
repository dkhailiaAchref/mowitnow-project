package mowitnow.parser;

import mowitnow.entities.Params.InstructionMower;
import mowitnow.entities.Params.Orientation;

/**
 * class contenant les méthodes permettant de valider le format des lignes dans le fichier.
 * @author sgmira
 *
 */
public class DataParser {

	private DataParser(){

	}

	/**
	 * parser la position de la tondeuse et son orientation
	 * La position et l'orientation sont fournies sous la forme de 2 chiffres et une lettre,
	 * séparés par un espace
	 * @param tondeuse
	 * @return true si la ligne des positions est correcte, false sinon
	 */
	public static boolean parseMower(String tondeuse){
		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append(Orientation.NORTH.getCodeOrientation())
			.append("|").append(Orientation.SOUTH.getCodeOrientation())
			.append("|").append(Orientation.EAST.getCodeOrientation())
			.append("|").append(Orientation.WEST.getCodeOrientation());
		return tondeuse.matches("(\\d+) (\\d+) (" + stringBuilder.toString()+")");
	}
	
	/**
	 * parser la ligne des instructions
	 * les instructions sont une suite de caractères(G, D, A) sans espaces
	 * @param instructions
	 * @return true si la ligne des instructions est correcte, false sinon
	 */
	public static boolean parseListInstruction(String instructions){
		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append("(").append(InstructionMower.MOVE_FORWARD.getCodeInstruction())
		.append("|").append(InstructionMower.RIGHT.getCodeInstruction())
		.append("|").append(InstructionMower.LEFT.getCodeInstruction())
		.append(")+");

		return instructions.matches(stringBuilder.toString());
	}

	/**
	 * parser la position de la pelouse
	 * la position de la pelouse est sous forme de 2 chiffres séparés par espace
	 * @param pelouse
	 * @return true si la ligne des instructions est correcte, false sinon
	 */
	public static boolean parseLawn(String pelouse){
		return pelouse.matches("(\\d+) (\\d+)");
	}
}