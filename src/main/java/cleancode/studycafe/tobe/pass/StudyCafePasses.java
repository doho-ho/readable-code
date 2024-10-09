package cleancode.studycafe.tobe.pass;

import cleancode.studycafe.tobe.model.StudyCafePassType;
import java.util.List;

public class StudyCafePasses {

  public static final int MINIMUM_INDEX = 0;
  private final List<Pass> passes;

  private StudyCafePasses(List<Pass> passes) {
    this.passes = passes;
  }

  public static StudyCafePasses from(List<Pass> passes) {
    return new StudyCafePasses(passes);
  }

  public StudyCafePasses filtered(StudyCafePassType type) {
    return from(passes.stream()
        .filter(it -> it.isEqualsType(type))
        .toList());
  }

  public int getSize() {
    return passes.size();
  }

  public Pass get(int index) {
    if (isIndexMoreOrEqualsThan(MINIMUM_INDEX)) {
      return passes.get(index);
    }
    throw new IllegalArgumentException("잘못된 위치입니다.");
  }

  private boolean isIndexMoreOrEqualsThan(int index) {
    return getSize() >= index;
  }
}
