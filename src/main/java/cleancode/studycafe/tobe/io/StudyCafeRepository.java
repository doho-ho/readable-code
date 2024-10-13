package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.lockerpass.LockerPasses;
import cleancode.studycafe.tobe.model.seatpass.SeatPasses;

public interface StudyCafeRepository {

  LockerPasses getLockerPass();

  SeatPasses getStudyCafePass();
}
