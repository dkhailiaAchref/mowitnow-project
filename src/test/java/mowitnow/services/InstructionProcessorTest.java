package mowitnow.services;

import static org.assertj.core.api.Assertions.*;
import mowitnow.exception.ExceptionMower;
import mowitnow.entities.Coordinates;
import mowitnow.entities.Params.InstructionMower;
import mowitnow.entities.Params.Orientation;
import mowitnow.entities.MowerPosition;

import org.junit.Test;


public class InstructionProcessorTest {

	@Test
	public void coordinates_x_y_south_instruction_Turn_Right() throws ExceptionMower {
		Coordinates coordonnesMax = new Coordinates(5, 5);
		int x = 2;
		int y = 3;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees,Orientation.SOUTH);
		InstructionProcessor.executeInstruction(positionTondeuse, InstructionMower.RIGHT, coordonnesMax);
		assertThat(positionTondeuse.getMowerCoordinates()).isEqualTo(new Coordinates(x, y));
		assertThat(positionTondeuse.getMowerOrientation()).isEqualTo(Orientation.WEST);
	}
	
	
	@Test
	public void rotate_right() throws ExceptionMower {
		
		Orientation orientationSuivante = InstructionProcessor.rotateRight(Orientation.EAST);
		assertThat(orientationSuivante).isEqualTo(Orientation.SOUTH);
		
		orientationSuivante = InstructionProcessor.rotateRight(Orientation.WEST);
		assertThat(orientationSuivante).isEqualTo(Orientation.NORTH);
		
		orientationSuivante = InstructionProcessor.rotateRight(Orientation.NORTH);
		assertThat(orientationSuivante).isEqualTo(Orientation.EAST);
		
		orientationSuivante = InstructionProcessor.rotateRight(Orientation.SOUTH);
		assertThat(orientationSuivante).isEqualTo(Orientation.WEST);
	}
	
	@Test
	public void rotate_left() throws ExceptionMower {
		Orientation orientationSuivante = InstructionProcessor.rotateLeft(Orientation.EAST);
		assertThat(orientationSuivante).isEqualTo(Orientation.NORTH);
		
		orientationSuivante = InstructionProcessor.rotateLeft(Orientation.WEST);
		assertThat(orientationSuivante).isEqualTo(Orientation.SOUTH);
		
		orientationSuivante = InstructionProcessor.rotateLeft(Orientation.NORTH);
		assertThat(orientationSuivante).isEqualTo(Orientation.WEST);
		
		orientationSuivante = InstructionProcessor.rotateLeft(Orientation.SOUTH);
		assertThat(orientationSuivante).isEqualTo(Orientation.EAST);
	}

}
