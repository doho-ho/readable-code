package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.pass.StudyCafePasses;
import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {

  private static final Scanner SCANNER = new Scanner(System.in);

  @Override
  public StudyCafePassType getPassTypeSelectingUserAction() {
    String userInput = SCANNER.nextLine();
    return ConsoleStudyCafePassTypeConverter.convert(userInput);
  }

  @Override
  public int getIndexFromUser(StudyCafePasses passes) {
    String userInput = SCANNER.nextLine();
    return Integer.parseInt(userInput) - 1;
  }

  @Override
  public boolean getLockerSelection() {
    String userInput = SCANNER.nextLine();
    return "1".equals(userInput);
  }

}
