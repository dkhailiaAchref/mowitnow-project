package mowitnow.services;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import mowitnow.exception.ExceptionMower;
import mowitnow.entities.Coordinates;
import mowitnow.entities.Params.InstructionMower;
import mowitnow.entities.Params.Orientation;
import mowitnow.entities.Lawn;
import mowitnow.entities.MowerPosition;

import org.junit.Test;


public class MowerProcessorTest {
	Coordinates coordonnesMax = new Coordinates(5, 5);
	
	@Test
	public void execute_instruction_no_instruction() throws ExceptionMower {
		int x = 0;
		int y = 0;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees, Orientation.NORTH);
		
		MowerProcessor traitemetT = new MowerProcessor();
		traitemetT.setLawn(new Lawn(coordonnesMax));
		traitemetT.setPositionMower(positionTondeuse);
		traitemetT.setListInstruction(new ArrayList<InstructionMower>());
		traitemetT.executerInstructions();
		assertThat(traitemetT.toString()).isEqualTo("0 0 N");
	}
	
	@Test
	public void execute_unitary_instruction() throws ExceptionMower {
		List<InstructionMower> listInstruction = new ArrayList<InstructionMower>();
		listInstruction.add(InstructionMower.MOVE_FORWARD);
		int x = 1;
		int y = 1;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees, Orientation.NORTH);
		MowerProcessor traitemetT = new MowerProcessor();
		traitemetT.setLawn(new Lawn(coordonnesMax));
		traitemetT.setPositionMower(positionTondeuse);
		traitemetT.setListInstruction(listInstruction);
		traitemetT.executerInstructions();
		assertThat(traitemetT.toString()).isEqualTo("1 2 N");
	}
	
	@Test
	public void execute_instruction_multiple() throws ExceptionMower {
		List<InstructionMower> listInstruction = new ArrayList<InstructionMower>();
		listInstruction.add(InstructionMower.LEFT);
		listInstruction.add(InstructionMower.MOVE_FORWARD);
		listInstruction.add(InstructionMower.LEFT);
		listInstruction.add(InstructionMower.MOVE_FORWARD);
		int x = 3;
		int y = 2;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees, Orientation.NORTH);
		MowerProcessor traitemetT = new MowerProcessor();
		traitemetT.setLawn(new Lawn(coordonnesMax));
		traitemetT.setPositionMower(positionTondeuse);
		traitemetT.setListInstruction(listInstruction);
		traitemetT.executerInstructions();
		assertThat(traitemetT.toString()).isEqualTo("2 1 S");
	}
	
	@Test
	public void execute_instruction_real_case_1() throws ExceptionMower {
		List<InstructionMower> listInstruction = new ArrayList<InstructionMower>();
		listInstruction.add(InstructionMower.LEFT);
		listInstruction.add(InstructionMower.MOVE_FORWARD);
		listInstruction.add(InstructionMower.LEFT);
		listInstruction.add(InstructionMower.MOVE_FORWARD);
		listInstruction.add(InstructionMower.LEFT);
		listInstruction.add(InstructionMower.MOVE_FORWARD);
		listInstruction.add(InstructionMower.LEFT);
		listInstruction.add(InstructionMower.MOVE_FORWARD);
		listInstruction.add(InstructionMower.MOVE_FORWARD);
		int x = 1;
		int y = 2;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees, Orientation.NORTH);
		MowerProcessor traitemetT = new MowerProcessor();
		traitemetT.setLawn(new Lawn(coordonnesMax));
		traitemetT.setPositionMower(positionTondeuse);
		traitemetT.setListInstruction(listInstruction);
		traitemetT.executerInstructions();
		assertThat(traitemetT.toString()).isEqualTo("1 3 N");
	}
	@Test
	public void execute_instruction_real_case_2() throws ExceptionMower {
		List<InstructionMower> listInstruction = new ArrayList<InstructionMower>();
		listInstruction.add(InstructionMower.MOVE_FORWARD);
		listInstruction.add(InstructionMower.MOVE_FORWARD);
		listInstruction.add(InstructionMower.RIGHT);
		listInstruction.add(InstructionMower.MOVE_FORWARD);
		listInstruction.add(InstructionMower.MOVE_FORWARD);
		listInstruction.add(InstructionMower.RIGHT);
		listInstruction.add(InstructionMower.MOVE_FORWARD);
		listInstruction.add(InstructionMower.RIGHT);
		listInstruction.add(InstructionMower.RIGHT);
		listInstruction.add(InstructionMower.MOVE_FORWARD);
		int x = 3;
		int y = 3;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees, Orientation.EAST);
		MowerProcessor traitemetT = new MowerProcessor();
		traitemetT.setLawn(new Lawn(coordonnesMax));
		traitemetT.setPositionMower(positionTondeuse);
		traitemetT.setListInstruction(listInstruction);
		traitemetT.executerInstructions();
		assertThat(traitemetT.toString()).isEqualTo("5 1 E");
	}
	
	
	@Test
	public void coordinates_x_y_north_instruction_advance() throws ExceptionMower {
		int x = 0;
		int y = 0;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees,Orientation.NORTH);
		InstructionProcessor.executeInstruction(positionTondeuse, InstructionMower.MOVE_FORWARD, coordonnesMax);
		assertThat(positionTondeuse.getMowerCoordinates()).isEqualTo(new Coordinates(x, y+1));
		assertThat(positionTondeuse.getMowerOrientation()).isEqualTo(Orientation.NORTH);
	}
	
	@Test
	public void coordinates_x_y_East_instruction_advance() throws ExceptionMower {
		int x = 0;
		int y = 0;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees,Orientation.EAST);
		InstructionProcessor.executeInstruction(positionTondeuse, InstructionMower.MOVE_FORWARD, coordonnesMax);
		assertThat(positionTondeuse.getMowerCoordinates()).isEqualTo(new Coordinates(x+1, y));
		assertThat(positionTondeuse.getMowerOrientation()).isEqualTo(Orientation.EAST);
	}
	
	@Test
	public void coordinates_x_y_South_instruction_advance() throws ExceptionMower {
		int x = 5;
		int y = 5;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees,Orientation.SOUTH);
		InstructionProcessor.executeInstruction(positionTondeuse, InstructionMower.MOVE_FORWARD, coordonnesMax);
		assertThat(positionTondeuse.getMowerCoordinates()).isEqualTo(new Coordinates(x, y-1));
		assertThat(positionTondeuse.getMowerOrientation()).isEqualTo(Orientation.SOUTH);
	}
	
	@Test
	public void coordinates_x_y_West_instruction_advance() throws ExceptionMower {
		int x = 5;
		int y = 5;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees,Orientation.WEST);
		InstructionProcessor.executeInstruction(positionTondeuse, InstructionMower.MOVE_FORWARD, coordonnesMax);
		assertThat(positionTondeuse.getMowerCoordinates()).isEqualTo(new Coordinates(x-1, y));
		assertThat(positionTondeuse.getMowerOrientation()).isEqualTo(Orientation.WEST);
	}
	@Test
	public void coordinates_x_y_West_instruction_Turn_left() throws ExceptionMower {
		int x = 2;
		int y = 3;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees,Orientation.WEST);
		InstructionProcessor.executeInstruction(positionTondeuse, InstructionMower.LEFT, coordonnesMax);
		assertThat(positionTondeuse.getMowerCoordinates()).isEqualTo(new Coordinates(x, y));
		assertThat(positionTondeuse.getMowerOrientation()).isEqualTo(Orientation.SOUTH);
	}
	
	@Test
	public void coordinates_x_y_east_instruction_Turn_left() throws ExceptionMower {
		int x = 2;
		int y = 3;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees,Orientation.EAST);
		InstructionProcessor.executeInstruction(positionTondeuse, InstructionMower.LEFT, coordonnesMax);
		assertThat(positionTondeuse.getMowerCoordinates()).isEqualTo(new Coordinates(x, y));
		assertThat(positionTondeuse.getMowerOrientation()).isEqualTo(Orientation.NORTH);
	}

	@Test
	public void coordinates_x_y_west_instruction_Turn_left() throws ExceptionMower {
		int x = 2;
		int y = 3;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees,Orientation.WEST);
		InstructionProcessor.executeInstruction(positionTondeuse, InstructionMower.LEFT, coordonnesMax);
		assertThat(positionTondeuse.getMowerCoordinates()).isEqualTo(new Coordinates(x, y));
		assertThat(positionTondeuse.getMowerOrientation()).isEqualTo(Orientation.SOUTH);
	}
	@Test
	public void coordinates_x_y_south_instruction_Turn_left() throws ExceptionMower {
		int x = 2;
		int y = 3;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees,Orientation.SOUTH);
		InstructionProcessor.executeInstruction(positionTondeuse, InstructionMower.LEFT, coordonnesMax);
		assertThat(positionTondeuse.getMowerCoordinates()).isEqualTo(new Coordinates(x, y));
		assertThat(positionTondeuse.getMowerOrientation()).isEqualTo(Orientation.EAST);
	}
	
	@Test
	public void coordinates_x_y_north_instruction_Turn_Right() throws ExceptionMower {
		int x = 2;
		int y = 3;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees,Orientation.NORTH);
		InstructionProcessor.executeInstruction(positionTondeuse, InstructionMower.RIGHT, coordonnesMax);
		assertThat(positionTondeuse.getMowerCoordinates()).isEqualTo(new Coordinates(x, y));
		assertThat(positionTondeuse.getMowerOrientation()).isEqualTo(Orientation.EAST);
	}
	
	@Test
	public void coordinates_x_y_east_instruction_Turn_Right() throws ExceptionMower {
		int x = 2;
		int y = 3;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees,Orientation.EAST);
		InstructionProcessor.executeInstruction(positionTondeuse, InstructionMower.RIGHT, coordonnesMax);
		assertThat(positionTondeuse.getMowerCoordinates()).isEqualTo(new Coordinates(x, y));
		assertThat(positionTondeuse.getMowerOrientation()).isEqualTo(Orientation.SOUTH);
	}

	@Test
	public void coordinates_x_y_west_instruction_Turn_Right() throws ExceptionMower {
		int x = 2;
		int y = 3;
		Coordinates coordonnees = new Coordinates(x, y);
		MowerPosition positionTondeuse = new MowerPosition(coordonnees,Orientation.WEST);
		InstructionProcessor.executeInstruction(positionTondeuse, InstructionMower.RIGHT, coordonnesMax);
		assertThat(positionTondeuse.getMowerCoordinates()).isEqualTo(new Coordinates(x, y));
		assertThat(positionTondeuse.getMowerOrientation()).isEqualTo(Orientation.NORTH);
	}

}