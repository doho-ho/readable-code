package cleancode.studycafe.tobe.io.provider;

import cleancode.studycafe.tobe.model.lockerpass.LockerPass;
import cleancode.studycafe.tobe.model.lockerpass.LockerPassFactory;
import cleancode.studycafe.tobe.model.lockerpass.LockerPasses;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.provider.LockerPassProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LockerPassFileReader implements LockerPassProvider {

  public static final int PASS_TYPE_INDEX = 0;
  public static final int DURATION_INDEX = 1;
  public static final int PRICE_INDEX = 2;

  public static final String LINE_SPLIT_REGEX = ",";
  public static final String LOCKER_LIST_CSV_PATH = "src/main/resources/cleancode/studycafe/locker.csv";

  @Override
  public LockerPasses getLockerPasses() {
    try {
      List<String> lines = Files.readAllLines(
          Paths.get(LOCKER_LIST_CSV_PATH));
      List<LockerPass> lockerPasses = new ArrayList<>();
      for (String line : lines) {
        String[] values = line.split(LINE_SPLIT_REGEX);
        StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[PASS_TYPE_INDEX]);
        int duration = Integer.parseInt(values[DURATION_INDEX]);
        int price = Integer.parseInt(values[PRICE_INDEX]);

        lockerPasses.add(LockerPassFactory.create(studyCafePassType, duration, price));
      }

      return LockerPasses.from(lockerPasses);
    } catch (IOException e) {
      throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
    }
  }
}
