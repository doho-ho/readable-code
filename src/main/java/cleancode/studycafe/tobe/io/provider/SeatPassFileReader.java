package cleancode.studycafe.tobe.io.provider;

import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.provider.SeatPassProvider;
import cleancode.studycafe.tobe.model.seatpass.PassFactory;
import cleancode.studycafe.tobe.model.seatpass.SeatPass;
import cleancode.studycafe.tobe.model.seatpass.SeatPasses;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SeatPassFileReader implements SeatPassProvider {

  public static final int PASS_TYPE_INDEX = 0;
  public static final int DURATION_INDEX = 1;
  public static final int PRICE_INDEX = 2;
  public static final int DISCOUNT_RATE_INDEX = 3;

  public static final String LINE_SPLIT_REGEX = ",";
  public static final String PASS_LIST_CSV_PATH = "src/main/resources/cleancode/studycafe/pass-list.csv";

  @Override
  public SeatPasses getSeatPasses() {
    try {
      List<String> lines = Files.readAllLines(
          Paths.get(PASS_LIST_CSV_PATH));
      List<SeatPass> studyCafePasses = new ArrayList<>();
      for (String line : lines) {
        String[] values = line.split(LINE_SPLIT_REGEX);
        StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[PASS_TYPE_INDEX]);
        int duration = Integer.parseInt(values[DURATION_INDEX]);
        int price = Integer.parseInt(values[PRICE_INDEX]);
        double discountRate = Double.parseDouble(values[DISCOUNT_RATE_INDEX]);

        SeatPass pass = PassFactory.create(studyCafePassType, duration, price, discountRate);
        studyCafePasses.add(pass);
      }

      return SeatPasses.from(studyCafePasses);
    } catch (IOException e) {
      throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
    }
  }
}
