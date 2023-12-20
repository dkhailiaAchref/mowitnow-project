package mowitnow.entities;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class LawnTest {

	@Test
	public void check_surcharge_equals() {
		Lawn p1 = new Lawn(new Coordinates(1, 2));
		Lawn p2 = new Lawn(new Coordinates(1, 2));
		assertThat(p1.equals(p2)).isTrue();
		p2 = new Lawn(new Coordinates(1, 3));
		assertThat(p1.equals(p2)).isFalse();
	}
}
