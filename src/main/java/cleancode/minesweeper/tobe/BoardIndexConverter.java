package cleancode.minesweeper.tobe;

public class BoardIndexConverter {

  private static final char BASE_CHAR_FOR_COL = 'a';


  public int getSelectedRowIndex(String cellInput) {
    String cellInputRow = cellInput.substring(1);
    return convertRowFrom(cellInputRow);
  }

  public int getSelectedColIndex(String cellInput) {
    char cellInputCol = cellInput.charAt(0);
    return convertColFrom(cellInputCol);
  }

  private int convertRowFrom(String cellInputRow) {
    int rowIndex = Integer.parseInt(cellInputRow) - 1;
    // 굳이 Board의 모든 것을 알아야하나? 0보다만 작으면 되는거 아닐까?
    if (rowIndex < 0) {
      throw new GameException("잘못된 입력입니다.");
    }
    return rowIndex;
  }

  private int convertColFrom(char cellInputCol) {
    int colIndex = cellInputCol - BASE_CHAR_FOR_COL;
    if (colIndex < 0) {
      throw new GameException(String.format("잘못된 입력입니다. %s", cellInputCol));
    }
    return colIndex;
  }

}
