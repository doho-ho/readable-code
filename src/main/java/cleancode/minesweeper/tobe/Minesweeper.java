package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;
import cleancode.minesweeper.tobe.position.CellPosition;

public class Minesweeper {

  private final GameBoard gameBoard;
  private final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();
  private final InputHandler inputHandler;
  private final OutputHandler outputHandler;
  private int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

  public Minesweeper(GameLevel gameLevel, InputHandler inputHandler, OutputHandler outputHandler) {
    this.gameBoard = new GameBoard(gameLevel);
    this.inputHandler = inputHandler;
    this.outputHandler = outputHandler;
  }

  public void run() {

    outputHandler.showGameStartComments();
    gameBoard.initializeGame();

    while (true) {
      try {
        outputHandler.showBoard(gameBoard);

        if (doesUserWinTheGame()) {
          outputHandler.showGameWinningComment();
          break;
        }
        if (doesUserLoseTheGame()) {
          outputHandler.showGameLosingComment();
          break;
        }

        CellPosition cellPosition = getCellInputFromUser();
        String userActionInput = getUserActionFormUser();
        actOnCell(cellPosition, userActionInput);
      } catch (GameException e) {
        outputHandler.showExceptionMessage(e);
      } catch (Exception e) {
        outputHandler.showSimpleMessage("프로그램에 문제가 생겼습니다.");
      }
    }
  }


  private void actOnCell(CellPosition cellPosition, String userActionInput) {
    if (doesUserChooseToPlantFlag(userActionInput)) {
      gameBoard.flagAt(cellPosition);
      checkIfGameIsOver();
      return;
    }

    if (doesUserChooseToOpenCell(userActionInput)) {
      if (gameBoard.isLandMineCellAt(cellPosition)) {
        gameBoard.openAt(cellPosition);
        changeGameStatusToLose();
        return;
      }

      gameBoard.openSurroundedCells(cellPosition);
      checkIfGameIsOver();
      return;
    }

    System.out.println("잘못된 번호를 선택하셨습니다.");
  }

  private void changeGameStatusToLose() {
    gameStatus = -1;
  }

  private boolean doesUserChooseToOpenCell(String userActionInput) {
    return userActionInput.equals("1");
  }

  private boolean doesUserChooseToPlantFlag(String userActionInput) {
    return userActionInput.equals("2");
  }

  private CellPosition getCellInputFromUser() {
    outputHandler.showCommentForSelectingCell();
    CellPosition cellPosition = inputHandler.getCellPositionFromUser();

    if (gameBoard.isInvalidCellPosition(cellPosition)) {
      throw new GameException("잘못된 좌표를 선택하셨습니다.");
    }
    return cellPosition;
  }

  private String getUserActionFormUser() {
    outputHandler.showCommentForUserAction();
    return inputHandler.getUserInput();
  }

  private boolean doesUserLoseTheGame() {
    return gameStatus == -1;
  }

  private boolean doesUserWinTheGame() {
    return gameStatus == 1;
  }

  // gameStatus를 1로 바꾸는 것이 더 중요
  private void checkIfGameIsOver() {
    if (gameBoard.isAllCellChecked()) {
      changeGameStatusToWin();
    }
  }

  private void changeGameStatusToWin() {
    gameStatus = 1;
  }
}
