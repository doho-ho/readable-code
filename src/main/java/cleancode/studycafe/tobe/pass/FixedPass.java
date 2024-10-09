package cleancode.studycafe.tobe.pass;

import cleancode.studycafe.tobe.model.StudyCafePassType;

public class FixedPass implements Pass{

  private final int duration;
  private final int price;
  private final double discountRate;

  private FixedPass(int duration, int price, double discountRate) {
    this.duration = duration;
    this.price = price;
    this.discountRate = discountRate;
  }

  public static FixedPass of(int duration, int price, double discountRate) {
    return new FixedPass(duration, price, discountRate);
  }

  @Override
  public boolean hasDiscountPrice() {
    return discountRate != 0.0;
  }

  @Override
  public boolean canChooseLockerPass() {
    return true;
  }

  @Override
  public int getDuration() {
    return duration;
  }

  @Override
  public StudyCafePassType getType() {
    return StudyCafePassType.FIXED;
  }


  @Override
  public boolean isEqualsType(StudyCafePassType type) {
    return StudyCafePassType.FIXED == type;
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
  public String toString() {
    return String.format("%s주권 - %d원", duration, price);
  }
}
