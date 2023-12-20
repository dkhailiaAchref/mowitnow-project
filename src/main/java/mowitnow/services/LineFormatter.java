package mowitnow.services;

import java.util.ArrayList;
import java.util.List;

import mowitnow.entities.Coordinates;
import mowitnow.entities.Params.InstructionMower;
import mowitnow.entities.Params.Orientation;
import mowitnow.entities.Lawn;
import mowitnow.entities.MowerPosition;

public class LineFormatter {

	private static final String STRING_SPACE = " ";

	private LineFormatter(){

	}

	/**
	 * récuperer la position de la tondeuse qui est définit par ses coordonnées et son orientation
	 * @param ligneTondeuse : ligne de la position de la tondeuse ( ex : 2 3 G)
	 * @return l'objet qui définit la position dela tondeuse
	 */
	public static MowerPosition formatLineMower(String ligneTondeuse){
		String[] elts = ligneTondeuse.split(STRING_SPACE);
		Coordinates pCoordonneesTondeuse = new Coordinates(Integer.valueOf(elts[0]), Integer.valueOf(elts[1]));
		Orientation orientationTondeuse = getOrientation(elts[2].charAt(0));
		return new MowerPosition(pCoordonneesTondeuse, orientationTondeuse);
	}

	/**
	 * récuperer l'objet Pelouse contenant les coordonnées limites de la pelouse
	 * @param lignePelouse : ligne de la pelouse ( ex : 2 3)
	 * @return l'objet qui définit la limite de la pelouse
	 */
	public static Lawn formatLineLawn(String lignePelouse){
		String[] elts = lignePelouse.split(STRING_SPACE);
		return new Lawn(new Coordinates(Integer.valueOf(elts[0]), Integer.valueOf(elts[1])));
	}

	/**
	 * récuperer une liste d'enum InstructionTondeuse correspondante à la ligne d'instruction
	 * @param ligneInstruction : suite d'instruction ( ex : GDAGD)
	 * @return une liste d'enum InstrctionTondeuse
	 */
	public static List<InstructionMower> formatLineInstruction(String ligneInstruction){
		List<InstructionMower> listInstruction = new ArrayList<InstructionMower>();
		for(char instruction :ligneInstruction.toCharArray()){
			listInstruction.add(getInstruction(instruction));
		}
		return listInstruction;
	}

	/**
	 * récuperer un enum Orientation correspondant au caractère de l'orientation
	 * @param cOrientation : caractère de l'orientation (E, W, N, S)
	 * @return l'enum correspondant à l'orientation 
	 */
	public static Orientation getOrientation(char cOrientation){
		for(Orientation orientation : Orientation.values()) {
			if (orientation.getCodeOrientation() == cOrientation){
				return orientation;
			}
		}
		return null;
	}

	/**
	 * récuperer un enum InstructionTondeuse correspondant au caractère d'instrction
	 * @param cInstruction : caractère de l'instruction (A, G, D)
	 * @return l'enum correspondant à l'instruction 
	 */
	public static InstructionMower getInstruction(char cInstruction){
		for(InstructionMower instruction : InstructionMower.values()) {
			if (instruction.getCodeInstruction() == cInstruction) {
				return instruction;
			}
		}
		return null;
	}
}