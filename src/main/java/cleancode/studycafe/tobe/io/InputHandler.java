package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.pass.StudyCafePasses;

public interface InputHandler {

  StudyCafePassType getPassTypeSelectingUserAction();

  int getIndexFromUser(StudyCafePasses passes);

  boolean getLockerSelection();
}
