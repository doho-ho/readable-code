package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.lockerpass.LockerPass;
import cleancode.studycafe.tobe.model.seatpass.SeatPass;
import cleancode.studycafe.tobe.model.seatpass.SeatPasses;

public interface OutputHandler {

  void showWelcomeMessage();

  void showAnnouncement();

  void askPassTypeSelection();

  void showPassListForSelection(SeatPasses passes);

  void askLockerPass(LockerPass lockerPass);

  void showPassOrderSummary(SeatPass selectedPass, LockerPass lockerPass);

  void showExceptionMessage(Exception e);

  void showSimpleMessage(String message);
}
