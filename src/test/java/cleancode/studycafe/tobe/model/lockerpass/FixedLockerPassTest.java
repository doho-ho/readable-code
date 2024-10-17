package cleancode.studycafe.tobe.model.lockerpass;

import static org.assertj.core.api.Assertions.assertThat;

import cleancode.studycafe.tobe.model.StudyCafePassType;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;
import org.junit.jupiter.params.provider.MethodSource;

class FixedLockerPassTest {

  @DisplayName("고정권 락커는 고정권이 아닌 유형을 비교할 때 다름을 반환한다.")
  @ParameterizedTest
  @EnumSource(value = StudyCafePassType.class, mode = Mode.EXCLUDE, names = {"FIXED"})
  void notEqualType(StudyCafePassType type) {
    // given
    FixedLockerPass fixedLockerPass = FixedLockerPass.of(10, 1000);

    // when
    boolean isEqualType = fixedLockerPass.isEqualType(type);

    // then
    assertThat(isEqualType).isFalse();
  }

  @DisplayName("고정권 락커는 고정권 유형을 가진다.")
  @Test
  void equalType() {
    // given
    FixedLockerPass fixedLockerPass = FixedLockerPass.of(10, 1000);

    // when
    boolean isEqualType = fixedLockerPass.isEqualType(StudyCafePassType.FIXED);

    // then
    assertThat(isEqualType).isTrue();
  }

  @DisplayName("고정권 락커는 다른 기간을 가졌다면 부정을 반환한다.")
  @ParameterizedTest
  @MethodSource
  void notEqualDuration(int duration) {
    // given
    FixedLockerPass fixedLockerPass = FixedLockerPass.of(10, 1000);

    // when
    boolean isEqualDuration = fixedLockerPass.isEqualDuration(duration);

    // then
    assertThat(isEqualDuration).isFalse();
  }

  @DisplayName("고정권 락커는 같은 기간을 비교하면 긍정을 반환한다.")
  @Test
  void equalDuration() {
    // given
    FixedLockerPass fixedLockerPass = FixedLockerPass.of(10, 1000);

    // when
    boolean isEqualDuration = fixedLockerPass.isEqualDuration(10);

    // then
    assertThat(isEqualDuration).isTrue();
  }

  static Stream<Arguments> notEqualDuration() {
    return Stream.of(
        Arguments.of(0),
        Arguments.of(100),
        Arguments.of(9999)
    );
  }

}