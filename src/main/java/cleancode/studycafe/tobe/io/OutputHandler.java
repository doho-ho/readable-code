package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.lockerpass.LockerPass;
import cleancode.studycafe.tobe.pass.Pass;
import cleancode.studycafe.tobe.pass.StudyCafePasses;

public interface OutputHandler {

  void showWelcomeMessage();

  void showAnnouncement();

  void askPassTypeSelection();

  void showPassListForSelection(StudyCafePasses passes);

  void askLockerPass(LockerPass lockerPass);

  void showPassOrderSummary(Pass selectedPass, LockerPass lockerPass);

  void showExceptionMessage(Exception e);

  void showSimpleMessage(String message);
}
