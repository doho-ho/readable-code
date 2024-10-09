package cleancode.studycafe.tobe.pass;

import cleancode.studycafe.tobe.model.StudyCafePassType;

public abstract class PassFactory {

  private PassFactory() {
    throw new UnsupportedOperationException("잘못된 요청입니다.");
  }

  public static Pass create(StudyCafePassType type, int duration, int price, double discountRate) {
    return switch (type) {
      case HOURLY -> hourlyPass(duration, price, discountRate);
      case WEEKLY -> weeklyPass(duration, price, discountRate);
      case FIXED -> fixedPass(duration, price, discountRate);
    };
  }

  private static HourlyPass hourlyPass(int duration, int price, double discountRate) {
    return HourlyPass.of(duration, price, discountRate);
  }

  private static WeeklyPass weeklyPass(int duration, int price, double discountRate) {
    return WeeklyPass.of(duration, price, discountRate);
  }

  private static FixedPass fixedPass(int duration, int price, double discountRate) {
    return FixedPass.of(duration, price, discountRate);
  }
}
