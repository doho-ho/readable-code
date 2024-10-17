package cleancode.studycafe.tobe.model.seatpass;

import cleancode.studycafe.tobe.model.StudyCafePassType;

public class HourlySeatPass implements SeatPass {

  private final int duration;
  private final int price;
  private final double discountRate;

  private HourlySeatPass(int duration, int price, double discountRate) {
    this.duration = duration;
    this.price = price;
    this.discountRate = discountRate;
  }

  public static HourlySeatPass of(int duration, int price, double discountRate) {
    return new HourlySeatPass(duration, price, discountRate);
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
