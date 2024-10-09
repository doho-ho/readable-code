package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.ConsoleInputHandler;
import cleancode.studycafe.tobe.io.ConsoleOutputHandler;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.io.StudyCafeRepository;

public class StudyCafeApplication {

    public static void main(String[] args) {

        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();
        StudyCafeRepository repository = new StudyCafeFileHandler();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(inputHandler, outputHandler, repository);
        studyCafePassMachine.run();
    }

}
