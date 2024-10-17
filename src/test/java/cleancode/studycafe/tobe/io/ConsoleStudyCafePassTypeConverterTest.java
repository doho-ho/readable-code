package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class ConsoleStudyCafePassTypeConverterTest {

  private final List<String> inputStrings = List.of(
      ConsoleStudyCafePassTypeConverter.HOURLY_STRING,
      ConsoleStudyCafePassTypeConverter.WEEKLY_STRING,
      ConsoleStudyCafePassTypeConverter.FIXED_STRING
  );

  @DisplayName("지정되지 않은 유저 입력이 들어올 경우 오류를 발생시킨다.")
  @Test
  void notSpecificatedInput() {
    // given
    String userInput = "4";

    // when
    Assertions.assertThat(inputStrings).doesNotContain(userInput);

    // then
    Assertions.assertThatThrownBy(() -> ConsoleStudyCafePassTypeConverter.convert(userInput))
        .isInstanceOf(AppException.class)
        .hasMessage("잘못된 입력입니다.");
  }

  @ParameterizedTest
  @EnumSource(value = StudyCafePassType.class)
  void convertTypeToString(StudyCafePassType type) {
    // given
    // when
    String actual = ConsoleStudyCafePassTypeConverter.convert(type);

    // then
    Assertions.assertThat(inputStrings).contains(actual);
  }

}