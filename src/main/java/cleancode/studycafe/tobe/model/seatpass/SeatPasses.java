package cleancode.studycafe.tobe.model.seatpass;

import cleancode.studycafe.tobe.model.StudyCafePassType;
import java.util.List;

public class SeatPasses {

  public static final int MINIMUM_INDEX = 0;
  private final List<SeatPass> passes;

  private SeatPasses(List<SeatPass> passes) {
    this.passes = passes;
  }

  public static SeatPasses from(List<SeatPass> passes) {
    return new SeatPasses(passes);
  }

  public SeatPasses filtered(StudyCafePassType type) {
    return from(passes.stream()
        .filter(it -> it.isEqualsType(type))
        .toList());
  }

  public int getSize() {
    return passes.size();
  }

  public SeatPass get(int index) {
    if (isIndexMoreOrEqualsThan(MINIMUM_INDEX)) {
      return passes.get(index);
    }
    throw new IllegalArgumentException("잘못된 위치입니다.");
  }

  private boolean isIndexMoreOrEqualsThan(int index) {
    return getSize() >= index;
  }
}
