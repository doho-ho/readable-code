package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.lockerpass.LockerPasses;
import cleancode.studycafe.tobe.pass.StudyCafePasses;

public interface StudyCafeRepository {

  LockerPasses getLockerPass();

  StudyCafePasses getStudyCafePass();
}
