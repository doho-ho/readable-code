package cleancode.studycafe.tobe.lockerpass;

import cleancode.studycafe.tobe.model.StudyCafePassType;

public abstract class LockerPassFactory {

  private LockerPassFactory() {
    throw new UnsupportedOperationException("잘못된 요청입니다.");
  }

  public static LockerPass create(StudyCafePassType type, int duration, int price) {
    return switch (type) {
      case HOURLY -> hourlyPass(duration, price);
      case WEEKLY -> weeklyPass(duration, price);
      case FIXED -> fixedPass(duration, price);
    };
  }

  private static HourlyLockerPass hourlyPass(int duration, int price) {
    return HourlyLockerPass.of(duration, price);
  }

  private static WeeklyLockerPass weeklyPass(int duration, int price) {
    return WeeklyLockerPass.of(duration, price);
  }

  private static FixedLockerPass fixedPass(int duration, int price) {
    return FixedLockerPass.of(duration, price);
  }
}
