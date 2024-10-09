package cleancode.studycafe.tobe.pass;

import cleancode.studycafe.tobe.model.StudyCafePassType;

public class HourlyPass implements Pass{

  private final int duration;
  private final int price;
  private final double discountRate;

  private HourlyPass(int duration, int price, double discountRate) {
    this.duration = duration;
    this.price = price;
    this.discountRate = discountRate;
  }

  public static HourlyPass of(int duration, int price, double discountRate) {
    return new HourlyPass(duration, price, discountRate);
  }

  @Override
  public boolean isEqualsType(StudyCafePassType type) {
    return StudyCafePassType.HOURLY == type;
  }

  @Override
  public boolean hasDiscountPrice() {
    return discountRate != 0.0;
  }


  @Override
  public int getTotalPrice() {
    return price - getDiscountPrice();
  }

  @Override
  public int getDiscountPrice() {
    if (hasDiscountPrice()) {
      return (int) (price * discountRate);
    }
    return 0;
  }

  @Override
  public boolean canChooseLockerPass() {
    return false;
  }

  @Override
  public int getDuration() {
    return duration;
  }

  @Override
  public StudyCafePassType getType() {
    return StudyCafePassType.HOURLY;
  }

  @Override
  public String toString() {
    return String.format("%s시간권 - %d원", duration, price);
  }
}
