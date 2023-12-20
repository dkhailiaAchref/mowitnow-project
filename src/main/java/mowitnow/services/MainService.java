package mowitnow.services;

import mowitnow.exception.ExceptionMower;
import mowitnow.entities.Params;
import mowitnow.parser.MowerParser;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@Service
public class MainService {

    private static final Logger log = Logger.getLogger("MainService");

    /**
     * lecture/validation du fichier et lancement des tondeuses
     *
     * @param fichier
     * @throws ExceptionMower
     * @throws IOException
     * @return List<String> : position des tondeuses
     */
    public List<String> process(File fichier)
            throws ExceptionMower, IOException {
        if (!fichier.isFile()) {
            throw new ExceptionMower(Params.ERROR_FILE_NOT_FOUND);
        } else {
            MowerParser parser = new MowerParser();
            Scanner scanner = new Scanner(fichier);
            try {
                processFirstLine(parser, scanner);
                return processNextLine(parser, scanner);
            } finally {
                scanner.close();
            }
        }
    }

    /**
     * traiter la premiere ligne du fichier
     * @param parser
     * @param scanner
     * @throws ExceptionMower : erreur si le fichier contients moins de 2 lignes
     */
    protected void processFirstLine(MowerParser parser, Scanner scanner)
            throws ExceptionMower {
        if (scanner.hasNext()) { //true
            parser.setLawn(scanner.nextLine()); //5 5 1 2 N GAGAGAGAA 3 3 E AADAADADDA
        }
        if (!scanner.hasNext()) {
            throw new ExceptionMower(
                    Params.INCORRECT_DATA_ERROR);
        }
    }

    /**
     *
     * @param parser
     * @param scanner
     * @return la position des tondeuses
     * @throws ExceptionMower
     */
    private List<String> processNextLine(MowerParser parser,
                                         Scanner scanner) throws ExceptionMower {
        List<String> listePositions = new ArrayList<>();
        log.info("resultat du traitement");
        log.info("X | Y | Orientation");
        while (scanner.hasNext()) {
            // lecture de la positon initiale de la tondeuse
            parser.setMower(scanner.nextLine());
            if (scanner.hasNext()) {
                parser.setInstructions(scanner.nextLine());
                listePositions.add(parseAndProcess(parser));
            } else {
                throw new ExceptionMower(Params.INCORRECT_DATA_ERROR);
            }
        }
        return listePositions;
    }

    /**
     * Parser et executer le traitement de la tondeuse
     * @param parser : l'objet contenant les informations de la tondeuse
     * @throws ExceptionMower
     */
    private String parseAndProcess(MowerParser parser)
            throws ExceptionMower {

        if (!parser.executeParse()) {
            throw new ExceptionMower(Params.INCORRECT_DATA_ERROR);
        } else {

            MowerProcessor traitement = new MowerProcessor();
            traitement.setLawn(LineFormatter
                    .formatLineLawn(parser.getLawn()));
            traitement.setPositionMower(LineFormatter
                    .formatLineMower(parser.getMower()));
            traitement.setListInstruction(LineFormatter
                    .formatLineInstruction(parser.getInstructions()));
            traitement.executerInstructions();
            log.info(traitement.toString());
            return traitement.toString();
        }
    }
}
