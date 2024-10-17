package cleancode.studycafe.tobe.stub;

import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.lockerpass.LockerPass;
import cleancode.studycafe.tobe.model.seatpass.SeatPass;
import cleancode.studycafe.tobe.model.seatpass.SeatPasses;

public class OutputHandlerStub implements OutputHandler {

  @Override
  public void showWelcomeMessage() {
  }

  @Override
  public void showAnnouncement() {

  }

  @Override
  public void askPassTypeSelection() {

  }

  @Override
  public void showPassListForSelection(SeatPasses passes) {

  }

  @Override
  public void askLockerPass(LockerPass lockerPass) {

  }

  @Override
  public void showPassOrderSummary(SeatPass selectedPass, LockerPass lockerPass) {

  }

  @Override
  public void showExceptionMessage(Exception e) {

  }

  @Override
  public void showSimpleMessage(String message) {

  }
}
