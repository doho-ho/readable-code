package cleancode.studycafe.tobe.lockerpass;

import cleancode.studycafe.tobe.pass.Pass;
import java.util.List;

public class LockerPasses {

  private final List<LockerPass> passes;

  private LockerPasses(List<LockerPass> passes) {
    this.passes = passes;
  }

  public static LockerPasses from(List<LockerPass> passes) {
    return new LockerPasses(passes);
  }

  public LockerPass getBy(Pass pass) {
    return passes.stream()
        .filter(option -> option.isEqualDuration(pass.getDuration()))
        .filter(option -> option.isEqualType(pass.getType()))
        .findFirst()
        .orElseGet(EmptyLockerPass::of);
  }
}
