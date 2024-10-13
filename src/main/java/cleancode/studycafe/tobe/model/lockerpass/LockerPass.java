package cleancode.studycafe.tobe.model.lockerpass;

import cleancode.studycafe.tobe.model.StudyCafePassType;

public interface LockerPass {

  EmptyLockerPass EMPTY_LOCKER = EmptyLockerPass.of();

  int getTotalPrice();

  boolean isEqualDuration(int duration);
  boolean isEqualType(StudyCafePassType type);

}
