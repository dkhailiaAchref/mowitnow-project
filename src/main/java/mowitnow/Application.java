package mowitnow;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import mowitnow.exception.ExceptionMower;
import mowitnow.exception.RuntimeExceptionMower;
import mowitnow.services.MainService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * run this main (SpringBootApplication)
 *  will alow to starting  Root WebApplication: with embeded tomcat server
 *  Tomcat(10.1.8) will be initialized and started on port(s): 8080 (http) with context path ''
 *   this will alow to test directly on navigator with endPoint ( http://localhost:8080/mowitnow/launch )
 *
 *  => input
 *   "./src/main/resources/" + "file.txt"
 *  => result
 *   [
 * "1 3 N",
 * "5 1 E"
 * ]
 */
@SpringBootApplication
public class Application {

	public static List<String> resultsList;
	private static final Logger log = Logger.getLogger("Application");

	public static void main(String[] args) throws ExceptionMower {
		SpringApplication.run(Application.class, args);
		// first launch processing on startup , with default test file ("./src/main/resources/file.txt")
		run();
	}
	public static void run (String ... filePath) throws ExceptionMower {

		File f1 = filePath.length > 0 ? new File(filePath[0]) : new File("./src/main/resources/file.txt");
		File f2 = filePath.length > 1 ? new File(filePath[1]) : new File("./src/main/resources/file.txt");

		try {
			MainService mainService = new MainService();
			log.info("first launch processing on startup ");
			resultsList = mainService.process(f1);
			if (filePath.length > 1) {
				resultsList = mainService.process(f2);
			}
		} catch (RuntimeExceptionMower e) {
			throw new RuntimeExceptionMower(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ExceptionMower e) {
		throw new ExceptionMower(e.getMessage());
	}
	}
}