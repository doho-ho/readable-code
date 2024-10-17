package cleancode.studycafe.tobe.model.seatpass;

import cleancode.studycafe.tobe.model.StudyCafePassType;

public interface SeatPass {
  boolean isEqualsType(StudyCafePassType type);
  int getTotalPrice();
  int getDiscountPrice();
  boolean hasDiscountPrice();
  boolean canChooseLockerPass();
  int getDuration();
  StudyCafePassType getType();
}
