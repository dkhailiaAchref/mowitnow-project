package mowitnow.parser;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class DataParserTest {
	
	@Test
	public void parse_mower(){
		assertThat(DataParser.parseMower("")).isFalse();
		assertThat(DataParser.parseMower("1 2 3")).isFalse();
		assertThat(DataParser.parseMower("12N")).isFalse();
		assertThat(DataParser.parseMower("1 2 N")).isTrue();
		assertThat(DataParser.parseMower("1 2 S")).isTrue();
	}
	
	@Test
	public void parse_list_instruction(){
		assertThat(DataParser.parseListInstruction("")).isFalse();
		assertThat(DataParser.parseListInstruction(" ")).isFalse();
		assertThat(DataParser.parseListInstruction("D G")).isFalse();
		assertThat(DataParser.parseListInstruction("GGAAAGADDAN")).isFalse();
		assertThat(DataParser.parseListInstruction("GGAAAG ADDAN")).isFalse();
		assertThat(DataParser.parseListInstruction("DGA")).isTrue();
		assertThat(DataParser.parseListInstruction("GGAAAGADDA")).isTrue();
	}
	
	@Test
	public void parse_lawn(){
		assertThat(DataParser.parseLawn("")).isFalse();
		assertThat(DataParser.parseLawn("1 2 3")).isFalse();
		assertThat(DataParser.parseLawn("123")).isFalse();
		assertThat(DataParser.parseLawn("1 2 ")).isFalse();
		assertThat(DataParser.parseLawn("1 2")).isTrue();
		assertThat(DataParser.parseLawn("1 N")).isFalse();
	}

}
