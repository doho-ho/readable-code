package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.stub.InputHandlerStub;
import cleancode.studycafe.tobe.stub.LockPassProviderStub;
import cleancode.studycafe.tobe.stub.OutputHandlerStub;
import cleancode.studycafe.tobe.stub.SeatPassProviderStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudyCafePassMachineTest {

  @DisplayName("이용권을 선택해 구매할 수 있다.")
  @Test
  void run() {
    // given
    StudyCafePassMachine sut = new StudyCafePassMachine(
        new InputHandlerStub(),
        new OutputHandlerStub(),
        new SeatPassProviderStub(),
        new LockPassProviderStub()
    );

    // when & then
    Assertions.assertDoesNotThrow(sut::run);
  }
}