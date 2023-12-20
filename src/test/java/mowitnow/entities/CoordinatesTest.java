package mowitnow.entities;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class CoordinatesTest {

	@Test
	public void check_surcharge_equals(){
		Coordinates c1 = new Coordinates(1, 2);
		Coordinates c2 = new Coordinates(1, 2);
		assertThat(c1.equals(c2)).isTrue();
		c2 = new Coordinates(1, 3);
		assertThat(c1.equals(c2)).isFalse();
	}
	
	@Test
	public void check_coordinates_(){
		Coordinates coordonneesPelouse = new Coordinates(5,5);
		Coordinates c0 = new Coordinates(-1,1);
		Coordinates c1 = new Coordinates(1,1);
		assertThat(coordonneesPelouse.isHorsCoordonnesMax(c0)).isFalse();
		assertThat(coordonneesPelouse.isHorsCoordonnesMax(c1)).isTrue();
	}
}
