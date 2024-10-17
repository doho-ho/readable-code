package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.ConsoleInputHandler;
import cleancode.studycafe.tobe.io.ConsoleOutputHandler;
import cleancode.studycafe.tobe.io.provider.LockerPassFileReader;
import cleancode.studycafe.tobe.io.provider.SeatPassFileReader;
import cleancode.studycafe.tobe.provider.LockerPassProvider;
import cleancode.studycafe.tobe.provider.SeatPassProvider;

public class StudyCafeApplication {

  public static void main(String[] args) {

    ConsoleInputHandler inputHandler = new ConsoleInputHandler();
    ConsoleOutputHandler outputHandler = new ConsoleOutputHandler();
    LockerPassProvider lockerPassProvider = new LockerPassFileReader();
    SeatPassProvider seatPassProvider = new SeatPassFileReader();

    StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(inputHandler,
        outputHandler,
        seatPassProvider,
        lockerPassProvider);
    studyCafePassMachine.run();
  }

}
