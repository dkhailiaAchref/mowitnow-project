package mowitnow.services;
import static org.assertj.core.api.Assertions.*;
import mowitnow.entities.Coordinates;
import mowitnow.entities.Params.InstructionMower;
import mowitnow.entities.Params.Orientation;
import mowitnow.entities.Lawn;

import org.junit.Test;
public class LineFormaterTest {

	@Test
	public void format_mower_line() {
		assertThat(LineFormatter.formatLineMower("10 15 N").getMowerCoordinates()).isEqualTo(new Coordinates(10, 15));
		assertThat(LineFormatter.formatLineMower("10 15 N").getMowerOrientation()).isEqualTo(Orientation.NORTH);
	}

	@Test
	public void format_lawn_line() {
		assertThat(LineFormatter.formatLineLawn("10 15")).isEqualTo(new Lawn(new Coordinates(10, 15)));
	}

	@Test
	public void format_line_instruction() {
		assertThat(LineFormatter.formatLineInstruction("DGAD")).hasSize(4)
				.contains(InstructionMower.RIGHT)
				.contains(InstructionMower.LEFT)
				.contains(InstructionMower.MOVE_FORWARD);
	}

	@Test
	public void retrieve_orientation() {
		assertThat(LineFormatter.getOrientation('E')).isEqualTo(Orientation.EAST);
		assertThat(LineFormatter.getOrientation('N')).isEqualTo(Orientation.NORTH);
		assertThat(LineFormatter.getOrientation('S')).isEqualTo(Orientation.SOUTH);
		assertThat(LineFormatter.getOrientation('W')).isEqualTo(Orientation.WEST);
		assertThat(LineFormatter.getOrientation('a')).isNull();
	}

	@Test
	public void testGetInstruction() {
		assertThat(LineFormatter.getInstruction('A')).isEqualTo(InstructionMower.MOVE_FORWARD);
		assertThat(LineFormatter.getInstruction('D')).isEqualTo(InstructionMower.RIGHT);
		assertThat(LineFormatter.getInstruction('G')).isEqualTo(InstructionMower.LEFT);
		assertThat(LineFormatter.getInstruction(' ')).isNull();
	}

}
