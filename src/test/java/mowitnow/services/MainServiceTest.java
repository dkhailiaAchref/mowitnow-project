package mowitnow.services;

import static mowitnow.AppConstatnts.DEFAULT_FILE_PATH;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;

import mowitnow.Application;
import mowitnow.entities.Params;

import mowitnow.exception.ExceptionMower;
import mowitnow.exception.RuntimeExceptionMower;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MainServiceTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test(expected = Exception.class)
	public void main_file_error_args() throws ExceptionMower, IOException {
		Application.run("1", "2");
	}
	
	@Test
	public void main_file_error_file_nonexist() throws RuntimeExceptionMower, ExceptionMower, IOException {
		expectedEx.expect(ExceptionMower.class);
		expectedEx.expectMessage(Params.ERROR_FILE_NOT_FOUND);
		Application.run("fichierinexistant");
	}
	
	@Test
	public void main_file_error_1_line() throws ExceptionMower, IOException {
		expectedEx.expect(ExceptionMower.class);
		expectedEx.expectMessage(Params.INCORRECT_DATA_ERROR);
		Application.run(DEFAULT_FILE_PATH + "file_line_1.txt");
	}
	
	@Test
	public void main_file_error_2_lines() throws ExceptionMower, IOException {
		expectedEx.expect(ExceptionMower.class);
		expectedEx.expectMessage(Params.INCORRECT_DATA_ERROR);
		Application.run(DEFAULT_FILE_PATH + "file_line_2.txt");
	}
	@Test
	public void main_file_missing_line_error() throws ExceptionMower, IOException {
		expectedEx.expect(ExceptionMower.class);
		expectedEx.expectMessage(Params.INCORRECT_DATA_ERROR);
		Application.run(DEFAULT_FILE_PATH + "missing_line_file.txt");

	}
	@Test
	public void main_file_error_empty_file() throws ExceptionMower, IOException {
		expectedEx.expect(ExceptionMower.class);
		expectedEx.expectMessage(Params.INCORRECT_DATA_ERROR);
		Application.run(DEFAULT_FILE_PATH + "empty_file.txt");

	}
	
	@Test
	public void main_file_error_file_completed_ko() throws ExceptionMower, IOException {
		expectedEx.expect(ExceptionMower.class);
		expectedEx.expectMessage(Params.INCORRECT_DATA_ERROR);
		Application.run(DEFAULT_FILE_PATH + "completed_file_ko.txt");
	}
	
	@Test
	public void main_fichier_erreur_completed_file() throws ExceptionMower, IOException {
		Application.run(DEFAULT_FILE_PATH + "completed_file.txt");
		assertThat(Application.resultsList).isNotNull();
		assertThat(Application.resultsList).hasSize(2).contains("1 3 N").contains("5 1 E");
		
	}
}