package cleancode.studycafe.tobe.model.seatpass;

import cleancode.studycafe.tobe.model.StudyCafePassType;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SeatPassesTest {

  @DisplayName("좌석 이용권 컬렉션에서 특정 위치의 좌석이용권을 조회할 수 있다.")
  @Test
  void get() {
    // given
    WeeklySeatPass weeklySeatPass = WeeklySeatPass.of(10, 1000, 0.0);
    SeatPasses sut = SeatPasses.from(List.of(weeklySeatPass));
    int targetIndex = 0;

    // when
    SeatPass actual = sut.get(targetIndex);

    // then
    Assertions.assertThat(actual).isEqualTo(weeklySeatPass);
  }

  @DisplayName("좌석 이용권 컬렉션에서 특정 위치의 좌석을 조회할 때 보유한 좌석보다 큰 값을 조회할 수 없다.")
  @Test
  void indexMustNotGreaterThanSize() {
    // given
    SeatPasses sut = SeatPasses.from(List.of());

    // when && then
    Assertions.assertThatThrownBy(() -> sut.get(1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("잘못된 위치입니다.");
  }

  @DisplayName("좌석 이용권 컬렉션에서 특정 위치의 좌석을 조회할 때 음수의 위치를 조회할 수 없다.")
  @Test
  void indexMustNotNegative() {
    // given
    SeatPasses sut = SeatPasses.from(List.of());

    // when && then
    Assertions.assertThatThrownBy(() -> sut.get(-1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("잘못된 위치입니다.");
  }

  @DisplayName("좌석 이용권 컬렉션에서 같은 유형의 좌석 이용권을 필터링 할 수 있다.")
  @Test
  void filtered() {
      // given
    SeatPasses sut = SeatPasses.from(
        List.of(HourlySeatPass.of(10, 1000, 0.0), WeeklySeatPass.of(10, 1000, 0.0)));

    // when
    SeatPasses filtered = sut.filtered(StudyCafePassType.HOURLY);

    // then
    Assertions.assertThat(filtered.getSize()).isEqualTo(1);
  }
}