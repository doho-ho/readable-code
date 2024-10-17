package cleancode.studycafe.tobe.stub;

import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.seatpass.SeatPasses;

public class InputHandlerStub implements InputHandler {

  @Override
  public StudyCafePassType getPassTypeSelectingUserAction() {
    return StudyCafePassType.FIXED;
  }

  @Override
  public int getIndexFromUser(SeatPasses passes) {
    return 0;
  }

  @Override
  public boolean getLockerSelection() {
    return false;
  }
}
