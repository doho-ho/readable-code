package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.seatpass.SeatPasses;

public interface InputHandler {

  StudyCafePassType getPassTypeSelectingUserAction();

  int getIndexFromUser(SeatPasses passes);

  boolean getLockerSelection();
}
