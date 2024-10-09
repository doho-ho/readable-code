package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.lockerpass.LockerPass;
import cleancode.studycafe.tobe.lockerpass.LockerPassFactory;
import cleancode.studycafe.tobe.lockerpass.LockerPasses;
import cleancode.studycafe.tobe.pass.Pass;
import cleancode.studycafe.tobe.pass.PassFactory;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.pass.StudyCafePasses;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeFileHandler implements StudyCafeRepository {

  public static final int PASS_TYPE_INDEX = 0;
  public static final int DURATION_INDEX = 1;
  public static final int PRICE_INDEX = 2;
  public static final int DISCOUNT_RATE_INDEX = 3;
  public static final String LINE_SPLIT_REGEX = ",";

  @Override
  public StudyCafePasses getStudyCafePass() {
    try {
      List<String> lines = Files.readAllLines(
          Paths.get("src/main/resources/cleancode/studycafe/pass-list.csv"));
      List<Pass> studyCafePasses = new ArrayList<>();
      for (String line : lines) {
        String[] values = line.split(LINE_SPLIT_REGEX);
        StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[PASS_TYPE_INDEX]);
        int duration = Integer.parseInt(values[DURATION_INDEX]);
        int price = Integer.parseInt(values[PRICE_INDEX]);
        double discountRate = Double.parseDouble(values[DISCOUNT_RATE_INDEX]);

        Pass pass = PassFactory.create(studyCafePassType, duration, price, discountRate);
        studyCafePasses.add(pass);
      }

      return StudyCafePasses.from(studyCafePasses);
    } catch (IOException e) {
      throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
    }
  }

  @Override
  public LockerPasses getLockerPass() {
    try {
      List<String> lines = Files.readAllLines(
          Paths.get("src/main/resources/cleancode/studycafe/locker.csv"));
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
