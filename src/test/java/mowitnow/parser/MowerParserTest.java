package mowitnow.parser;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class MowerParserTest {
	
	@Test
	public void parse_tondeuse(){
		MowerParser parserTondeuse = new MowerParser();
		parserTondeuse.setInstructions("DGDGA");
		parserTondeuse.setLawn("50 5");
		parserTondeuse.setMower("1 2 N");
		assertThat(parserTondeuse.executeParse()).isTrue();
	}
	@Test
	
	public void parse_tondeuse_pelouse_incorrect(){
		MowerParser parserTondeuse = new MowerParser();
		parserTondeuse.setInstructions("DGDGA");
		parserTondeuse.setLawn("0 -1");
		parserTondeuse.setMower("1 2 N");
		assertThat(parserTondeuse.executeParse()).isFalse();
	}
	
	@Test
	public void parse_tondeuse_donnees_vide(){
		MowerParser parserTondeuse = new MowerParser();
		assertThat(parserTondeuse.executeParse()).isFalse();
	}
}
