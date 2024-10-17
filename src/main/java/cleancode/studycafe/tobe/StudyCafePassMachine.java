package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.lockerpass.LockerPass;
import cleancode.studycafe.tobe.model.lockerpass.LockerPasses;
import cleancode.studycafe.tobe.model.seatpass.SeatPass;
import cleancode.studycafe.tobe.model.seatpass.SeatPasses;
import cleancode.studycafe.tobe.provider.LockerPassProvider;
import cleancode.studycafe.tobe.provider.SeatPassProvider;

public class StudyCafePassMachine {

  private final InputHandler inputHandler;
  private final OutputHandler outputHandler;

  private final SeatPassProvider seatPassProvider;
  private final LockerPassProvider lockerPassProvider;

  public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler,
      SeatPassProvider seatPassProvider,
      LockerPassProvider lockerPassProvider) {
    this.inputHandler = inputHandler;
    this.outputHandler = outputHandler;
    this.seatPassProvider = seatPassProvider;
    this.lockerPassProvider = lockerPassProvider;
  }

  public void run() {
    try {
      outputHandler.showWelcomeMessage();
      outputHandler.showAnnouncement();

      SeatPass selectedPass = selectPass();
      LockerPass lockerPass = selectLockerPass(selectedPass);

      outputHandler.showPassOrderSummary(selectedPass, lockerPass);

    } catch (AppException e) {
      outputHandler.showExceptionMessage(e);
    } catch (Exception e) {
      outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
    }
  }

  private SeatPass selectPass() {
    outputHandler.askPassTypeSelection();

    StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();
    SeatPasses passCandidates = findPassCandidatesBy(studyCafePassType);

    outputHandler.showPassListForSelection(passCandidates);
    return getSelectedPassFromUserInput(passCandidates);
  }

  private LockerPass selectLockerPass(SeatPass selectedPass) {
    if (selectedPass.canChooseLockerPass()) {
      LockerPass lockerPass = getLockerSelection().findBy(selectedPass);
      if (checkLockerChosen(lockerPass)) {
        return lockerPass;
      }
    }
    return LockerPass.EMPTY_LOCKER;
  }

  private boolean checkLockerChosen(LockerPass lockerPass) {
    boolean lockerSelection;
    outputHandler.askLockerPass(lockerPass);
    lockerSelection = inputHandler.getLockerSelection();
    return lockerSelection;
  }

  private LockerPasses getLockerSelection() {
    return lockerPassProvider.getLockerPasses();
  }

  private SeatPasses findPassCandidatesBy(StudyCafePassType studyCafePassType) {
    return seatPassProvider.getSeatPasses().filtered(studyCafePassType);
  }

  private SeatPass getSelectedPassFromUserInput(SeatPasses passes) {
    int indexFromUser = inputHandler.getIndexFromUser(passes);
    return passes.get(indexFromUser);
  }
}
