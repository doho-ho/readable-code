package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Beginner;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.minesweeper.Minesweeper;

public class GameApplication {

  public static void main(String[] args) {
    GameConfig gameConfig = new GameConfig(
        new Beginner(),
        new ConsoleInputHandler(),
        new ConsoleOutputHandler()
    );

    Minesweeper minesweeper = new Minesweeper(gameConfig);
    minesweeper.run();
  }
}
