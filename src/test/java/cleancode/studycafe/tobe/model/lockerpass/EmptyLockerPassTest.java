package cleancode.studycafe.tobe.model.lockerpass;

import static org.assertj.core.api.Assertions.assertThat;

import cleancode.studycafe.tobe.model.StudyCafePassType;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

class EmptyLockerPassTest {

  @DisplayName("빈 락커는 가격이 0원이다.")
  @Test
  void getTotalPrice() {
      // given
    EmptyLockerPass emptyLockerPass = EmptyLockerPass.of();

    // when
    int totalPrice = emptyLockerPass.getTotalPrice();

    // then
    assertThat(totalPrice).isZero();
  }

  @DisplayName("빈 락커는 타입 비교에서 항상 다름을 반환한다.")
  @ParameterizedTest
  @EnumSource(value = StudyCafePassType.class)
  void notEqualType(StudyCafePassType type) {
    // given
    EmptyLockerPass emptyLockerPass = EmptyLockerPass.of();

    // when
    boolean isEqualType = emptyLockerPass.isEqualType(type);

    // then
    assertThat(isEqualType).isFalse();
  }

  @DisplayName("빈 락커는 이용권 기간 비교에서 항상 다름을 반환한다.")
  @ParameterizedTest
  @MethodSource
  void notEqualDuration(int duration) {
    // given
    EmptyLockerPass emptyLockerPass = EmptyLockerPass.of();

    // when
    boolean isEqualDuration = emptyLockerPass.isEqualDuration(duration);

    // then
    assertThat(isEqualDuration).isFalse();
  }

  static Stream<Arguments> notEqualDuration() {
    return Stream.of(
        Arguments.of(0),
        Arguments.of(10),
        Arguments.of(100),
        Arguments.of(9999)
    );
  }

}