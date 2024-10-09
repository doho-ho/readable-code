package cleancode.studycafe.tobe.pass;

import cleancode.studycafe.tobe.model.StudyCafePassType;

public interface Pass {
  boolean isEqualsType(StudyCafePassType type);
  int getTotalPrice();
  int getDiscountPrice();
  boolean hasDiscountPrice();
  boolean canChooseLockerPass();
  int getDuration();
  StudyCafePassType getType();
}
