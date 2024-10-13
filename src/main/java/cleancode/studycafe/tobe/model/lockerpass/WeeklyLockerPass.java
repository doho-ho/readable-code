package cleancode.studycafe.tobe.model.lockerpass;

import cleancode.studycafe.tobe.model.StudyCafePassType;

public class WeeklyLockerPass implements LockerPass {

  private final int duration;
  private final int price;

  private WeeklyLockerPass(int duration, int price) {
    this.duration = duration;
    this.price = price;
  }

  public static WeeklyLockerPass of(int duration, int price) {
    return new WeeklyLockerPass(duration, price);
  }

  @Override
  public int getTotalPrice() {
    return price;
  }

  @Override
  public boolean isEqualDuration(int duration) {
    return this.duration == duration;
  }

  @Override
  public boolean isEqualType(StudyCafePassType type) {
    return StudyCafePassType.WEEKLY == type;
  }

  @Override
  public String toString() {
    return String.format("%s주권 - %d원", duration, price);
  }
}
