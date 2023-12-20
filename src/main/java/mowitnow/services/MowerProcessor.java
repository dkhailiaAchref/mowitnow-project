package mowitnow.services;
import java.util.ArrayList;
import java.util.List;

import mowitnow.exception.ExceptionMower;
import mowitnow.entities.Params.InstructionMower;
import mowitnow.entities.Lawn;
import mowitnow.entities.MowerPosition;

public class MowerProcessor {

	private Lawn lawn;
	private MowerPosition mowerPosition;
	private List<InstructionMower> listInstruction;
	
	public void setLawn(Lawn lawn) {
		this.lawn = lawn;
	}
	
	public void setPositionMower(MowerPosition positionTondeuse) {
		this.mowerPosition = positionTondeuse;
	}

	public void setListInstruction(
			List<InstructionMower> pListeInstruction) {
		this.listInstruction = pListeInstruction;
		if(pListeInstruction == null){
			listInstruction = new ArrayList<InstructionMower>();
		}
	}
	/**
	 * executer l'ensemble des insctructions par une tondeuse
	 * @throws ExceptionMower
	 */
	public void executerInstructions() throws ExceptionMower {
		for(InstructionMower instruction : listInstruction){
			InstructionProcessor.executeInstruction(mowerPosition,
					instruction, this.lawn.getPositionMax());
		}
	}

	public String toString(){
		return 	mowerPosition.getMowerCoordinates().getX()
				+ " " 
				+ mowerPosition.getMowerCoordinates().getY()
				+ " " 
				+ mowerPosition.getMowerOrientation().getCodeOrientation() ;
	}
}