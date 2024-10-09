package cleancode.minesweeper.tobe.cell;

public class NumberCell implements Cell {

  private final CellState cellState = CellState.initialize();
  private int nearbyLandMineCount;

  public NumberCell(int count) {
    super();
    this.nearbyLandMineCount = count;
  }

  @Override
  public boolean isLandMine() {
    return false;
  }

  @Override
  public boolean hasLandMineCount() {
    return true;
  }

  @Override
  public String getSign() {
    if (cellState.isOpened()) {
      return String.valueOf(nearbyLandMineCount);
    }

    if (cellState.isFlagged()) {
      return FLAG_SIGN;
    }
    return UNCHECKED_SIGN;
  }

  @Override
  public void flag() {
    cellState.flag();
  }

  @Override
  public void open() {
    cellState.open();
  }

  @Override
  public boolean isChecked() {
    return cellState.isChecked();
  }

  @Override
  public boolean isOpened() {
    return cellState.isOpened();
  }
}
