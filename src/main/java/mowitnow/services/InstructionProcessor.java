package mowitnow.services;

import mowitnow.exception.ExceptionMower;
import mowitnow.entities.Coordinates;
import mowitnow.entities.Params;
import mowitnow.entities.Params.InstructionMower;
import mowitnow.entities.Params.Orientation;
import mowitnow.entities.MowerPosition;

public  class InstructionProcessor {

	private InstructionProcessor(){

	}
	/**
	 * faire avancer la tondeuse
	 * @param positionTondeuse : position initiale de la tondeuse 
	 * @param coordonnesMax : coordonnees de la pelouse - coin superieur droit de la pelouse
	 * @return coordonnees : nouvelles coordonnees de la tondeuse
	 * @throws ExceptionMower
	 * 	 * 	 ** faire pivoter la tondeuse à droite
	 * 	 * 	 *
	 * 	 * 					 N
	 * 	 					 |
	 * 	 * 					 |y-----
	 * 	 * 					 |     |
	 * 	 * 	 W --------------|----x|----------- E
	 * 	 * 					 |
	 * 	 * 					 |
	 * 	 * 					 S
	 * 	 *
	 * 	 * MOVE_FORWARD => orientation = N => ( x=x , y=y+1)
	 *   * MOVE_FORWARD => orientation = S => ( x=x , y=y-1)
	 *   * MOVE_FORWARD => orientation = E => ( x=x+1 , y=y)
	 *   * MOVE_FORWARD => orientation = W=> ( x=x-1 , y=y)
	 *  */

	public static Coordinates moveforwardMower(MowerPosition positionTondeuse, Coordinates coordonnesMax) throws ExceptionMower {
		Coordinates coordonneesSuivantes = null;
		int x, y;
		switch (positionTondeuse.getMowerOrientation()) {
		case NORTH:
			x = positionTondeuse.getMowerCoordinates().getX();
			y = positionTondeuse.getMowerCoordinates().getY() + 1;
			break;
		case EAST:
			x = positionTondeuse.getMowerCoordinates().getX() + 1;
			y = positionTondeuse.getMowerCoordinates().getY();
			break;
		case SOUTH:
			x = positionTondeuse.getMowerCoordinates().getX();
			y = positionTondeuse.getMowerCoordinates().getY() - 1;
			break;
		case WEST:
			x = positionTondeuse.getMowerCoordinates().getX() - 1;
			y = positionTondeuse.getMowerCoordinates().getY();
			break;
		default:
			throw new ExceptionMower(Params.INCORRECT_POSITION);
		}
		coordonneesSuivantes = new Coordinates(x, y);
		// si les nouvelles coordonnées sont en dehors de la pelouse, on garde
		// la derniere position connue
		if (coordonneesSuivantes != null
				&& coordonnesMax.isHorsCoordonnesMax(coordonneesSuivantes)) {
			return coordonneesSuivantes;
		} else {
			return positionTondeuse.getMowerCoordinates();
		}
	}
	
	/**
	 * @param orientation : orientation initiale de la tondeuse
	 * @return nouvelle orientation 
	 * @throws ExceptionMower
	 * 	 ** faire pivoter la tondeuse à droite
	 * 	 *
	 * 					 N
	 * 					 |
	 * 					 |
	 * 	 W --------------|--------------- E
	 * 					 |
	 * 					 |
	 * 					 S
	 *
	 * RIGHT => orientation =N => orientation =E
	 * RIGHT => orientation =E => orientation =S
	 * RIGHT => orientation =S => orientation =W
	 * RIGHT => orientation =W=> orientation =N
	 */
	
	public static Orientation rotateRight(Orientation orientation) throws ExceptionMower {
		Orientation orientationSuivante = null ;
		switch (orientation){
			case NORTH : 
				orientationSuivante =  Orientation.EAST;
				break;
			case EAST : 
				orientationSuivante =  Orientation.SOUTH;
				break;
			case SOUTH : 
				orientationSuivante =  Orientation.WEST;
				break;
			case WEST : 
				orientationSuivante =  Orientation.NORTH;
				break;
			default : 
				throw new ExceptionMower(Params.INCORRECT_ORIENTATION);
		}
		return orientationSuivante;		
	}
	
	/** *
	 *  @param orientation : orientation initale de la tondeuse
	 * @return nouvelle orientation
	 * @throws ExceptionMower
	 *
	 ** pivoter la tondeuse à gauche (G)
	 *
					 N
					 |
					 |
	 W --------------|--------------- E
					 |
					 |
					 S

	 * LEFT => orientation =N => orientation =W
	 * LEFT => orientation =E => orientation =N
	 * LEFT => orientation =S => orientation =E
	 * LEFT => orientation =W=> orientation =S
	 */
	public static Orientation rotateLeft(Orientation orientation) throws ExceptionMower {
		Orientation orientationSuivante = null ;
		switch (orientation){
			case NORTH : 
				orientationSuivante =  Orientation.WEST; 
				break;
			case EAST : 
				orientationSuivante =  Orientation.NORTH; 
				break;
			case SOUTH : 
				orientationSuivante =  Orientation.EAST; 
				break;
			case WEST : 
				orientationSuivante =  Orientation.SOUTH; 
				break;
			default : 
				throw new ExceptionMower(Params.INCORRECT_ORIENTATION);
		}
		return orientationSuivante;		
	}

	/**
	 * executer une seule instruction ( A, D ou G)
	 * @param positionTondeuse
	 * @param instruction
	 * @param coordonnesMax
	 * @throws ExceptionMower
	 */
	public static void executeInstruction(MowerPosition positionTondeuse, InstructionMower instruction, Coordinates coordonnesMax) throws ExceptionMower {
		
		switch (instruction){
			case MOVE_FORWARD:
				positionTondeuse.setMowerCoordinates(InstructionProcessor.moveforwardMower(positionTondeuse, coordonnesMax));
				break;
			case RIGHT:
				positionTondeuse.setMowerOrientation(InstructionProcessor.rotateRight(positionTondeuse.getMowerOrientation()));
				break;
			case LEFT:
				positionTondeuse.setMowerOrientation(InstructionProcessor.rotateLeft(positionTondeuse.getMowerOrientation()));
				break;
			default: throw new ExceptionMower(Params.INCORRECT_INSTRUCTION);
		}
	}
}