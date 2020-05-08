package gui;

import core.GameChangeListener;
import core.TicTacToe;
import core.TicTacToeImpl;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements GameChangeListener {
  TicTacToe ticTacToe = new TicTacToeImpl();
  JButton button[][];

  GamePanel() {

    ticTacToe.addGameChangeListener(this);

    int gridSize = ticTacToe.getGridSize();
    setLayout(new GridLayout(gridSize, gridSize));
    button = new JButton[gridSize][gridSize];

    for (int row = 0; row < gridSize; row++) {
      for (int col = 0; col < gridSize; col++) {
        button[row][col] = new JButton("");
        int finalRow = row;
        int finalCol = col;
        button[row][col].addActionListener(e -> {
          button[finalRow][finalCol].setText(ticTacToe.getCurrentPlayer());
          ticTacToe.putPiece(finalRow, finalCol);
        });
        add(button[row][col]);
      }
    }
  }

  @Override
  public void gameEnd(String winner) {
    int gridSize = ticTacToe.getGridSize();

    for (int row = 0; row < gridSize; row++) {
      for (int col = 0; col < gridSize; col++) {
        button[row][col].setText(winner + " WINS");
        button[row][col].setEnabled(false);
      }
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      // add frame and set its closing operation
      JFrame frame = new JFrame("Tic Tac Toe!");
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      frame.add(new GamePanel());

      //display the JFrame
      frame.pack();
      frame.setResizable(true);
      frame.setVisible(true);
    });
  }
}
