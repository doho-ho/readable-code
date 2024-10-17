package cleancode.studycafe.tobe.model.seatpass;

import static org.assertj.core.api.Assertions.assertThat;

import cleancode.studycafe.tobe.model.StudyCafePassType;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

class PassFactoryTest {

  @DisplayName("좌석권 유형에 따라 좌석권 객체를 반환한다.")
  @ParameterizedTest
  @MethodSource
  void create(StudyCafePassType passType, Class<SeatPass> expect) {
    // given
    // when
    SeatPass seatPass = PassFactory.create(passType, 10, 1000, 0.0);

    // then
    assertThat(seatPass).isInstanceOf(expect);
  }

  static Stream<Arguments> create() {
    return Stream.of(
        Arguments.of(StudyCafePassType.HOURLY, HourlySeatPass.class),
        Arguments.of(StudyCafePassType.WEEKLY, WeeklySeatPass.class),
        Arguments.of(StudyCafePassType.FIXED, FixedSeatPass.class)
    );
  }

  @DisplayName("모든 유형으로 좌석권 객체를 만들 수 있다.")
  @ParameterizedTest
  @EnumSource
  void createAllType(StudyCafePassType type) {
    // given
    // when
    SeatPass seatPass = PassFactory.create(type, 10, 1000, 0.0);
    // then
    assertThat(seatPass).isInstanceOf(SeatPass.class);
  }
}