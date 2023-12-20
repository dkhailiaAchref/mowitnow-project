package mowitnow.entities;


public class Params {
	public static final String INCORRECT_DATA_ERROR = "données incorrectes";
	public static final String ERROR_FILE_NOT_FOUND = "fichier inexistant";
	public static final String INCORRECT_ORIENTATION = "orientation incorrecte";
	public static final String INCORRECT_INSTRUCTION = "instruction incorrecte";
	public static final String INCORRECT_POSITION = "position incorrecte";
	private Params(){}
	
	/**
	 * orientations possibles
	 * @author sgmira
	 *
	 */
	public static enum Orientation {
		NORTH('N', "Nord"),
		EAST('E', "est"),
		WEST('W', "ouest"),
		SOUTH('S', "sud");

		private char codeOrientation;
		private String labelOrientation;
		
		private Orientation(char codeOrientation, String labelOrientation){
			this.codeOrientation = codeOrientation;
			this.labelOrientation = labelOrientation;
		}
		public char getCodeOrientation() {
			return codeOrientation;
		}
		public String getLabelOrientation() {
			return labelOrientation;
		}
	}
	
	/**
	 * instructions possibles
	 * @author sgmira
	 *
	 */
	public static enum InstructionMower {
		RIGHT('D', "Pivoter à droite"),
		LEFT('G', "Pivoter à gauche"),
		MOVE_FORWARD('A', "Avancer");
		
		private String labelInstruction;
		private char codeInstruction;
		
		private InstructionMower(char pCodeInstruction, String libelleInstruction) {
			this.labelInstruction = libelleInstruction;
			this.codeInstruction = pCodeInstruction;
		}
		
		public String getLabelInstruction() {
			return labelInstruction;
		}
		public char getCodeInstruction() {
			return codeInstruction;
		}

	}
}