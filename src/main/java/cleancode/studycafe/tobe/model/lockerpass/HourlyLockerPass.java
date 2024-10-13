package cleancode.studycafe.tobe.model.lockerpass;

import cleancode.studycafe.tobe.model.StudyCafePassType;

public class HourlyLockerPass implements LockerPass {

  private final int duration;
  private final int price;

  private HourlyLockerPass(int duration, int price) {
    this.duration = duration;
    this.price = price;
  }

  public static HourlyLockerPass of(int duration, int price) {
    return new HourlyLockerPass(duration, price);
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
    return StudyCafePassType.HOURLY == type;
  }

  @Override
  public String toString() {
    return String.format("%s시간권 - %d원", duration, price);
  }
}
