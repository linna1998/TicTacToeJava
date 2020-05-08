package core;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeImpl implements TicTacToe {
  public static final int GRID_SIZE = 3;

  public static final String[] PLAYER = {"X", "O"};

  private int currentPlayerIndex = 0;
  private List<GameChangeListener> listenerList = new ArrayList<>();
  private String[][] board = new String[GRID_SIZE][GRID_SIZE];

  public TicTacToeImpl() {
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        board[i][j] = "";
      }
    }
  }

  @Override
  public void addGameChangeListener(GameChangeListener listener) {
    listenerList.add(listener);
  }

  @Override
  public String getCurrentPlayer() {
    return PLAYER[currentPlayerIndex];
  }

  @Override
  public int getGridSize() {
    return GRID_SIZE;
  }

  @Override
  public void putPiece(int row, int col) {
    board[row][col] = PLAYER[currentPlayerIndex];

    if (isGameEnded(row, col)) {
      for (GameChangeListener listener : listenerList) {
        listener.gameEnd(PLAYER[currentPlayerIndex]);
      }
      return;
    }

    currentPlayerIndex = 1 - currentPlayerIndex;
  }

  private boolean isSameRow(int row) {
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = i + 1; j < GRID_SIZE; j++) {
        if (!board[row][i].equals(board[row][j])) return false;
      }
    }
    return true;
  }

  private boolean isSameCol(int col) {
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = i + 1; j < GRID_SIZE; j++) {
        if (!board[i][col].equals(board[j][col])) return false;
      }
    }
    return true;
  }

  private boolean isSameDiagonal() {
    boolean[] flag = {true, true};

    if (board[(GRID_SIZE - 1) / 2][(GRID_SIZE - 1) / 2].equals("")) return false;
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = i + 1; j < GRID_SIZE; j++) {
        if (!board[i][i].equals(board[j][j])) flag[0] = false;
        if (!board[i][GRID_SIZE - 1 - i].equals(board[j][GRID_SIZE - 1 - j])) flag[1] = false;
      }
    }
    return flag[0] || flag[1];
  }

  private boolean isGameEnded(int row, int col) {

    if (isSameRow(row)) return true;

    if (isSameCol(col)) return true;

    return isSameDiagonal();
  }
}
