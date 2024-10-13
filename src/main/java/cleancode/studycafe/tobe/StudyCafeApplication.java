package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.ConsoleInputHandler;
import cleancode.studycafe.tobe.io.ConsoleOutputHandler;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeRepository;
import cleancode.studycafe.tobe.io.provider.LockerPassFileReader;
import cleancode.studycafe.tobe.io.provider.SeatPassFileReader;
import cleancode.studycafe.tobe.provider.LockerPassProvider;
import cleancode.studycafe.tobe.provider.SeatPassProvider;

public class StudyCafeApplication {

  public static void main(String[] args) {

    LockerPassProvider lockerPassProvider = new LockerPassFileReader();
    SeatPassProvider seatPassProvider = new SeatPassFileReader();

    StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(seatPassProvider,
        lockerPassProvider);
    studyCafePassMachine.run();
  }

}
