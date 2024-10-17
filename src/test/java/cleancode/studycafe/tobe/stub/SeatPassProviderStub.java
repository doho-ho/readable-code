package cleancode.studycafe.tobe.stub;

import cleancode.studycafe.tobe.model.seatpass.SeatPasses;
import cleancode.studycafe.tobe.provider.SeatPassProvider;
import java.util.List;

public class SeatPassProviderStub implements SeatPassProvider {

  @Override
  public SeatPasses getSeatPasses() {
    return SeatPasses.from(List.of());
  }
}
