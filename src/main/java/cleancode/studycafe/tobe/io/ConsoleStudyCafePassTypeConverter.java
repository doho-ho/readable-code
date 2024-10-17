package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.StudyCafePassType;

public abstract class ConsoleStudyCafePassTypeConverter {

  public static final String HOURLY_STRING = "1";
  public static final String WEEKLY_STRING = "2";
  public static final String FIXED_STRING = "3";

  private ConsoleStudyCafePassTypeConverter() {
    throw new UnsupportedOperationException("잘못된 요청입니다.");
  }

  public static StudyCafePassType convert(String userInput) {
    return switch (userInput) {
      case HOURLY_STRING -> StudyCafePassType.HOURLY;
      case WEEKLY_STRING -> StudyCafePassType.WEEKLY;
      case FIXED_STRING -> StudyCafePassType.FIXED;
      default -> throw new AppException("잘못된 입력입니다.");
    };
  }

  public static String convert(StudyCafePassType type) {
    return switch (type) {
      case HOURLY -> HOURLY_STRING;
      case WEEKLY -> WEEKLY_STRING;
      case FIXED -> FIXED_STRING;
      default -> throw new IllegalArgumentException("잘못된 유형입니다.");
    };
  }

}
