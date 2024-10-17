package cleancode.studycafe.tobe.model.seatpass;

import cleancode.studycafe.tobe.model.StudyCafePassType;

public abstract class PassFactory {

  private PassFactory() {
    throw new UnsupportedOperationException("잘못된 요청입니다.");
  }

  public static SeatPass create(StudyCafePassType type, int duration, int price, double discountRate) {
    return switch (type) {
      case HOURLY -> hourlyPass(duration, price, discountRate);
      case WEEKLY -> weeklyPass(duration, price, discountRate);
      case FIXED -> fixedPass(duration, price, discountRate);
      default -> throw new IllegalArgumentException("잘못된 유형입니다.");
    };
  }

  private static HourlySeatPass hourlyPass(int duration, int price, double discountRate) {
    return HourlySeatPass.of(duration, price, discountRate);
  }

  private static WeeklySeatPass weeklyPass(int duration, int price, double discountRate) {
    return WeeklySeatPass.of(duration, price, discountRate);
  }

  private static FixedSeatPass fixedPass(int duration, int price, double discountRate) {
    return FixedSeatPass.of(duration, price, discountRate);
  }
}
