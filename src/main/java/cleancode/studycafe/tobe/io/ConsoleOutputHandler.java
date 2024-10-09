package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.lockerpass.LockerPass;

import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.pass.Pass;
import cleancode.studycafe.tobe.pass.StudyCafePasses;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ConsoleOutputHandler implements OutputHandler {

  @Override
  public void showWelcomeMessage() {
    System.out.println("*** 프리미엄 스터디카페 ***");
  }

  @Override
  public void showAnnouncement() {
    System.out.println("* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)");
    System.out.println("* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)");
    System.out.println();
  }

  @Override
  public void askPassTypeSelection() {
    System.out.println("사용하실 이용권을 선택해 주세요.");

    String menuText = Arrays.stream(StudyCafePassType.values())
        .map(it -> String.format("%s. %s", ConsoleStudyCafePassTypeConverter.convert(it),
            it.getOutputText()))
        .collect(Collectors.joining(" | "));

    System.out.println(menuText);
  }

  @Override
  public void showPassListForSelection(StudyCafePasses passes) {
    System.out.println();
    System.out.println("이용권 목록");
    for (int index = 0; index < passes.getSize(); index++) {
      Pass pass = passes.get(index);
      System.out.println(String.format("%s. ", index + 1) + pass);
    }
  }

  @Override
  public void askLockerPass(LockerPass lockerPass) {
    System.out.println();
    String askMessage = String.format("사물함을 이용하시겠습니까? (%s)", lockerPass);

    System.out.println(askMessage);
    System.out.println("1. 예 | 2. 아니오");
  }

  @Override
  public void showPassOrderSummary(Pass selectedPass, LockerPass lockerPass) {
    System.out.println();
    System.out.println("이용 내역");
    System.out.println("이용권: " + selectedPass);
    if (lockerPass != LockerPass.EMPTY_LOCKER) {
      System.out.println("사물함: " + lockerPass);
    }

    if (selectedPass.hasDiscountPrice()) {
      System.out.println("이벤트 할인 금액: " + selectedPass.getDiscountPrice() + "원");
    }

    int totalPrice = selectedPass.getTotalPrice() + lockerPass.getTotalPrice();
    System.out.println("총 결제 금액: " + totalPrice + "원");
    System.out.println();
  }

  @Override
  public void showExceptionMessage(Exception e) {
    System.out.println(e.getMessage());
  }

  @Override
  public void showSimpleMessage(String message) {
    System.out.println(message);
  }

}
