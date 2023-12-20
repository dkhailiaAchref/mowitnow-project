package mowitnow.entities;

import mowitnow.entities.Params.Orientation;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class MowerPositionTest {

	@Test
	public void check_surcharge_equals() {
		
		MowerPosition positionT = new MowerPosition(new Coordinates(5, 5), Orientation.NORTH);
		MowerPosition positionTOk = new MowerPosition(new Coordinates(5, 5), Orientation.NORTH);
		MowerPosition positionTKo = new MowerPosition(new Coordinates(5, 5), Orientation.SOUTH);
		
		assertThat(positionT.equals(positionTOk)).isTrue();
		assertThat(positionT.equals(positionTKo)).isFalse();
	}

}
