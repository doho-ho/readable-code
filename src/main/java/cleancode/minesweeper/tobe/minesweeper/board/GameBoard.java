package cleancode.minesweeper.tobe.minesweeper.board;

import cleancode.minesweeper.tobe.minesweeper.board.cell.Cell;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.cell.Cells;
import cleancode.minesweeper.tobe.minesweeper.board.cell.EmptyCell;
import cleancode.minesweeper.tobe.minesweeper.board.cell.LandMineCell;
import cleancode.minesweeper.tobe.minesweeper.board.cell.NumberCell;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPositions;
import cleancode.minesweeper.tobe.minesweeper.board.position.RelativePosition;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.GameLevel;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class GameBoard {

  private final Cell[][] board;
  private final int landMineCount;
  private GameStatus gameStatus;

  public GameBoard(GameLevel gameLevel) {
    int rowSize = gameLevel.getRowSize();
    int colSize = gameLevel.getColSize();
    landMineCount = gameLevel.getLandMineCount();
    board = new Cell[rowSize][colSize];

    initializeGameStatus();
  }

  // 상태 변경
  public void initializeGame() {
    initializeGameStatus();
    CellPositions cellPositions = CellPositions.from(board);

    initializedEmptyCells(cellPositions);

    List<CellPosition> landMinePositions = cellPositions.extractRandPositions(landMineCount);
    initializeLandMineCells(landMinePositions);

    List<CellPosition> numberPositionCandidates = cellPositions.subtract(landMinePositions);
    initializeNumberCells(numberPositionCandidates);
  }

  public void openAt(CellPosition cellPosition) {
    if (isLandMineCellAt(cellPosition)) {
      openOneCellAt(cellPosition);
      changeGameStatusToLose();
      return;
    }

    openSurroundedCells(cellPosition);
    checkIfGameIsOver();
  }

  public void flagAt(CellPosition cellPosition) {
    Cell cell = findCell(cellPosition);
    cell.flag();
    checkIfGameIsOver();
  }

  // 판별
  public boolean isInvalidCellPosition(CellPosition cellPosition) {
    int rowSize = getRowSize();
    int colSize = getColSize();
    return cellPosition.isRowIndexMoreThanOrEqual(rowSize)
        || cellPosition.isColIndexMoreThanOrEqual(colSize);
  }

  public boolean isLandMineCellAt(CellPosition cellPosition) {
    Cell cell = findCell(cellPosition);
    return cell.isLandMine();
  }

  public boolean isInProgress() {
    return gameStatus == GameStatus.IN_PROGRESS;
  }

  public boolean isWinStatus() {
    return gameStatus == GameStatus.WIN;
  }

  public boolean isLoseStatus() {
    return gameStatus == GameStatus.LOSE;
  }

  // 조회
  public CellSnapshot getSnapshot(CellPosition cellPosition) {
    Cell cell = findCell(cellPosition);
    return cell.getSnapshot();
  }

  public int getRowSize() {
    return board.length;
  }

  public int getColSize() {
    return board[0].length;
  }

  private void initializeGameStatus() {
    gameStatus = GameStatus.IN_PROGRESS;
  }

  private void initializedEmptyCells(CellPositions cellPositions) {
    List<CellPosition> allPositions = cellPositions.getPositions();
    for (CellPosition allPosition : allPositions) {
      updateCellAt(allPosition, new EmptyCell());
    }
  }

  private void initializeLandMineCells(List<CellPosition> landMinePositions) {
    for (CellPosition allPosition : landMinePositions) {
      updateCellAt(allPosition, new LandMineCell());
    }
  }

  private void initializeNumberCells(List<CellPosition> numberPositionCandidates) {
    for (CellPosition candidatePosition : numberPositionCandidates) {
      int count = countNearbyLandMines(candidatePosition);
      if (count != 0) {
        updateCellAt(candidatePosition, new NumberCell(count));
      }
    }
  }

  private void updateCellAt(CellPosition landMinePosition, Cell cell) {
    board[landMinePosition.getRowIndex()][landMinePosition.getColIndex()] = cell;
  }

  private void openOneCellAt(CellPosition cellPosition) {
    Cell cell = findCell(cellPosition);
    cell.open();
  }

  private void openSurroundedCells(CellPosition cellPosition) {
    Deque<CellPosition> deque = new ArrayDeque<>();
    deque.push(cellPosition);

    while (!deque.isEmpty()) {
      openAndPushCellAt(deque);
    }
  }

  private void openAndPushCellAt(Deque<CellPosition> deque) {
    CellPosition currentCellPosition = deque.pop();
    if (isOpenedCell(currentCellPosition)) {
      return;
    }
    if (isLandMineCellAt(currentCellPosition)) {
      return;
    }

    openOneCellAt(currentCellPosition);

    if (doesCellHasLandMineCount(currentCellPosition)) {
      return;
    }

    List<CellPosition> surroundedPositions = calculateSurroundedPositions(currentCellPosition,
        getRowSize(), getColSize());
    for (CellPosition surroundedPosition : surroundedPositions) {
      deque.push(surroundedPosition);
    }
  }

  private List<CellPosition> calculateSurroundedPositions(CellPosition cellPosition,
      int rowSize,
      int colSize) {
    return RelativePosition.SURROUNDED_POSITIONS.stream()
        .filter(cellPosition::canCalculatePositionBy)
        .map(cellPosition::calculatePositionBy)
        .filter(position -> position.isRowIndexLessThan(rowSize))
        .filter(position -> position.isColIndexLessThan(colSize))
        .toList();
  }

  private int countNearbyLandMines(CellPosition cellPosition) {

    int rowSize = board.length;
    int colSize = board[0].length;

    return (int) calculateSurroundedPositions(cellPosition, rowSize, colSize).stream()
        .filter(this::isLandMineCellAt)
        .count();
  }

  private boolean isAllCellChecked() {
    Cells cells = Cells.from(board);
    return cells.isAllChecked();
  }

  private void checkIfGameIsOver() {
    if (isAllCellChecked()) {
      changeGameStatusToWin();
    }
  }

  private void changeGameStatusToWin() {
    gameStatus = GameStatus.WIN;
  }


  private void changeGameStatusToLose() {
    gameStatus = GameStatus.LOSE;
  }

  private boolean doesCellHasLandMineCount(CellPosition cellPosition) {
    return findCell(cellPosition).hasLandMineCount();
  }

  private boolean isOpenedCell(CellPosition cellPosition) {
    return findCell(cellPosition).isOpened();
  }

  private Cell findCell(CellPosition cellPosition) {
    return board[cellPosition.getRowIndex()][cellPosition.getColIndex()];
  }


}
