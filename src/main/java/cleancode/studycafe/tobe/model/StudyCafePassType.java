package cleancode.studycafe.tobe.model;

public enum StudyCafePassType {

    HOURLY("시간 이용권(자유석)", "시간 단위 이용권"),
    WEEKLY("주단위 이용권(자유석)", "주 단위 이용권"),
    FIXED("3. 1인 고정석","1인 고정석"),
  ;

    private final String outputText;
    private final String description;

    StudyCafePassType(String outputText, String description) {
      this.outputText = outputText;
      this.description = description;
    }

  public String getOutputText() {
    return outputText;
  }
}
