package cleancode.studycafe.tobe.model.seatpass;

import cleancode.studycafe.tobe.model.StudyCafePassType;

public class WeeklySeatPass implements SeatPass {

  private final int duration;
  private final int price;
  private final double discountRate;

  private WeeklySeatPass(int duration, int price, double discountRate) {
    this.duration = duration;
    this.price = price;
    this.discountRate = discountRate;
  }

  public static WeeklySeatPass of(int duration, int price, double discountRate) {
    return new WeeklySeatPass(duration, price, discountRate);
  }

  @Override
  public boolean isEqualsType(StudyCafePassType type) {
    return StudyCafePassType.WEEKLY == type;
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
    return StudyCafePassType.WEEKLY;
  }

  @Override
  public String toString() {
    return String.format("%s주권 - %d원", duration, price);
  }
}
