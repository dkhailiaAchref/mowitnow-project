package mowitnow.parser;

/**
 * class permetant de valider les informations qui permettent de
 * lancer une tandeuse 
 * @author myriam
 *
 */
public class MowerParser {

	private String lawn;
	private String mower;
	private String instructions ;

	public MowerParser(){
		this.lawn = "";
		this.mower = "";
		this.instructions = "";
	}
	
	/**
	 * @return true si les informations de la tondeuse sont correctes, false sinon
	 */
	public boolean executeParse(){
		return DataParser.parseMower(mower)
				&& DataParser.parseLawn(lawn)
				&& DataParser.parseListInstruction(instructions);
	}
	
	public String getLawn() {
		return lawn;
	}

	public void setLawn(String lawn) {
		this.lawn = lawn;
	}

	public String getMower() {
		return mower;
	}

	public void setMower(String mower) {
		this.mower = mower;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
}