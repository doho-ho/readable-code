package cleancode.studycafe.tobe.stub;

import cleancode.studycafe.tobe.model.lockerpass.LockerPasses;
import cleancode.studycafe.tobe.provider.LockerPassProvider;
import java.util.List;

public class LockPassProviderStub implements LockerPassProvider {

  @Override
  public LockerPasses getLockerPasses() {
    return LockerPasses.from(List.of());
  }
}
