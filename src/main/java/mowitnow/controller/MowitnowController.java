package mowitnow.controller;


import mowitnow.exception.ExceptionMower;
import mowitnow.services.MainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * test on EndPoint ( Get )
 * http://localhost:8080/mowitnow/launch
 * result
 * [
 * "1 3 N",
 * "5 1 E"
 * ]
 */
@RestController
@RequestMapping("mowitnow")
public class MowitnowController {

    final MainService mainService ;
    private static final Logger log = Logger.getLogger("MowitnowController");

    public MowitnowController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping(value = "/launch", produces = "application/json")
    public List<String>  launchProcessing(@RequestParam(value = "./src/main/resources/file.txt",required = false) String ... filePath ) {
        try {
            return launch(filePath);
        } catch (ExceptionMower | IOException e) {
            throw new RuntimeException(e);
        }
    }

     public List<String>  launch (String ... filePath ) throws ExceptionMower, IOException {
       List<String> resultsList;
         File f1=null,f2 = null;
         if (filePath!=null) {
             f1 = filePath.length > 0 ? new File(filePath[0]) : new File("./src/main/resources/file.txt");
             f2 = filePath.length > 1 ? new File(filePath[1]) : new File("./src/main/resources/file.txt");
         }
         try {
            resultsList = mainService.process(f1==null?new File("./src/main/resources/file.txt"):f1);
             if (filePath!=null && filePath.length > 1) {
                 resultsList = mainService.process(f2==null?new File("./src/main/resources/file.txt"):f2);
             }
        } catch (ExceptionMower e) {
            try {
                throw new ExceptionMower(e.getMessage());
            } catch (ExceptionMower ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultsList;
    }
}