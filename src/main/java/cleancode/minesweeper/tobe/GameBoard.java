package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.cell.Cell;
import cleancode.minesweeper.tobe.cell.EmptyCell;
import cleancode.minesweeper.tobe.cell.LandMineCell;
import cleancode.minesweeper.tobe.cell.NumberCell;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.position.CellPosition;
import cleancode.minesweeper.tobe.position.RelativePosition;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class GameBoard {

  private Cell[][] board;
  private final int landMineCount;

  public GameBoard(GameLevel gameLevel) {
    int rowSize = gameLevel.getRowSize();
    int colSize = gameLevel.getColSize();
    landMineCount = gameLevel.getLandMineCount();
    board = new Cell[rowSize][colSize];
  }

  public boolean isInvalidCellPosition(CellPosition cellPosition) {
    int rowSize = getRowSize();
    int colSize = getColSize();
    return cellPosition.isRowIndexMoreThanOrEqual(rowSize)
        || cellPosition.isColIndexMoreThanOrEqual(colSize);
  }

  public void initializeGame() {
    int rowSize = board.length;
    int colSize = board[0].length;

    for (int row = 0; row < rowSize; row++) {
      for (int col = 0; col < colSize; col++) {
        board[row][col] = new EmptyCell();
      }
    }

    for (int i = 0; i < landMineCount; i++) {
      int landMineCol = new Random().nextInt(colSize);
      int landMinRow = new Random().nextInt(rowSize);
      LandMineCell landMineCell = new LandMineCell();
      board[landMinRow][landMineCol] = landMineCell;
    }

    for (int row = 0; row < rowSize; row++) {
      for (int col = 0; col < colSize; col++) {
        CellPosition cellPosition = CellPosition.of(row, col);
        if (isLandMineCellAt(cellPosition)) {
          continue;
        }

        int count = countNearbyLandMines(cellPosition);

        if (count == 0) {
          continue;
        }

        NumberCell numberCell = new NumberCell(count);
        board[row][col] = numberCell;
      }
    }
  }

  public boolean isLandMineCellAt(CellPosition cellPosition) {
    Cell cell = findCell(cellPosition);
    return cell.isLandMine();
  }

  private int countNearbyLandMines(CellPosition cellPosition) {

    int rowSize = board.length;
    int colSize = board[0].length;

    return (int) calculateSurroundedPositions(cellPosition, rowSize, colSize).stream()
        .filter(this::isLandMineCellAt)
        .count();
  }

  private static List<CellPosition> calculateSurroundedPositions(CellPosition cellPosition,
      int rowSize,
      int colSize) {
    return RelativePosition.SURROUNDED_POSITIONS.stream()
        .filter(cellPosition::canCalculatePositionBy)
        .map(cellPosition::calculatePositionBy)
        .filter(position -> position.isRowIndexLessThan(rowSize))
        .filter(position -> position.isColIndexLessThan(colSize))
        .toList();
  }

  public int getRowSize() {
    return board.length;
  }

  public int getColSize() {
    return board[0].length;
  }

  public String getSign(CellPosition cellPosition) {
    Cell cell = findCell(cellPosition);
    return cell.getSign();
  }

  public boolean isAllCellChecked() {
    return Arrays.stream(board)
        .flatMap(Arrays::stream)
        .allMatch(Cell::isChecked);
  }

  private Cell findCell(CellPosition cellPosition) {
    return board[cellPosition.getRowIndex()][cellPosition.getColIndex()];
  }

  public void flagAt(CellPosition cellPosition) {
    Cell cell = findCell(cellPosition);
    cell.flag();
  }

  public void openAt(CellPosition cellPosition) {
    Cell cell = findCell(cellPosition);
    cell.open();
  }

  public void openSurroundedCells(CellPosition cellPosition) {
    if (isOpenedCell(cellPosition)) {
      return;
    }
    if (isLandMineCellAt(cellPosition)) {
      return;
    }

    openAt(cellPosition);

    if (doesCellHasLandMineCount(cellPosition)) {
      return;
    }

    calculateSurroundedPositions(cellPosition, getRowSize(), getColSize())
        .forEach(this::openSurroundedCells);
  }

  private boolean doesCellHasLandMineCount(CellPosition cellPosition) {
    return findCell(cellPosition).hasLandMineCount();
  }

  private boolean isOpenedCell(CellPosition cellPosition) {
    return findCell(cellPosition).isOpened();
  }


}
