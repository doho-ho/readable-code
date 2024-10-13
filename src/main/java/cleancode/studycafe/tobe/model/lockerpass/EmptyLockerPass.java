package cleancode.studycafe.tobe.model.lockerpass;

import cleancode.studycafe.tobe.model.StudyCafePassType;

public class EmptyLockerPass implements LockerPass {

  private static final String EMPTY_STRING = "";

  public static EmptyLockerPass of() {
    return new EmptyLockerPass();
  }

  @Override
  public int getTotalPrice() {
    return 0;
  }

  @Override
  public boolean isEqualDuration(int duration) {
    return false;
  }

  @Override
  public boolean isEqualType(StudyCafePassType type) {
    return false;
  }

  @Override
  public String toString() {
    return EMPTY_STRING;
  }
}
