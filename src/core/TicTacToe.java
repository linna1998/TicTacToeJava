package core;

public interface TicTacToe {
  void addGameChangeListener(GameChangeListener listener);

  String getCurrentPlayer();

  int getGridSize();

  void putPiece(int row, int col);
}
