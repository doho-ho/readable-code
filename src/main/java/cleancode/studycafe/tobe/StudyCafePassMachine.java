package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeRepository;
import cleancode.studycafe.tobe.lockerpass.LockerPass;
import cleancode.studycafe.tobe.lockerpass.LockerPasses;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.pass.Pass;
import cleancode.studycafe.tobe.pass.StudyCafePasses;

public class StudyCafePassMachine {

  private final InputHandler inputHandler;
  private final OutputHandler outputHandler;
  private final StudyCafeRepository repository;

  public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler,
      StudyCafeRepository studyCafeRepository) {
    this.inputHandler = inputHandler;
    this.outputHandler = outputHandler;
    this.repository = studyCafeRepository;
  }

  public void run() {
    try {
      outputHandler.showWelcomeMessage();
      outputHandler.showAnnouncement();
      outputHandler.askPassTypeSelection();

      StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

      StudyCafePasses passes = getFilteredStudyCafePasses(studyCafePassType);

      outputHandler.showPassListForSelection(passes);

      Pass selectedPass = getSelectedPassFromUserInput(passes);
      LockerPass lockerPass = chooseLockerPass(selectedPass);

      outputHandler.showPassOrderSummary(selectedPass, lockerPass);

    } catch (AppException e) {
      outputHandler.showExceptionMessage(e);
    } catch (Exception e) {
      outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
    }
  }

  private LockerPass chooseLockerPass(Pass selectedPass) {
    if (selectedPass.canChooseLockerPass()) {
      LockerPass lockerPass = getLockerPasses().getBy(selectedPass);
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

  private LockerPasses getLockerPasses() {
    return repository.getLockerPass();
  }

  private StudyCafePasses getFilteredStudyCafePasses(StudyCafePassType studyCafePassType) {
    return repository.getStudyCafePass().filtered(studyCafePassType);
  }

  private Pass getSelectedPassFromUserInput(StudyCafePasses passes) {
    int indexFromUser = inputHandler.getIndexFromUser(passes);
    return passes.get(indexFromUser);
  }
}
