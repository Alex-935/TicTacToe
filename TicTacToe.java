import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TicTacToe {

    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;
    
    TicTacToe() {

        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {

                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Ariel", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();

                        if (tile.getText().equals("")) {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                            
                        }
                    }
                });
            }
        }

        frame.setVisible(true);
    };

    void checkWinner() {

        for (int r = 0; r < 3; r++) {
            //horizontal
            if (board[r][0].getText().equals(currentPlayer) && board[r][1].getText().equals(currentPlayer) && board[r][2].getText().equals(currentPlayer)) {
                setWinner(board[r][0]);
                setWinner(board[r][1]);
                setWinner(board[r][2]);
                gameOver = true;
                return;
            }

            //vertical
            if (board[0][r].getText().equals(currentPlayer) && board[1][r].getText().equals(currentPlayer) && board[2][r].getText().equals(currentPlayer)) {
                setWinner(board[0][r]);
                setWinner(board[1][r]);
                setWinner(board[2][r]);
                gameOver = true;
                return;
            }

            //diagonals
            if (board[0][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)) {
                setWinner(board[0][0]);
                setWinner(board[1][1]);
                setWinner(board[2][2]);
                gameOver = true;
                return;                
            }
            if (board[0][2].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][0].getText().equals(currentPlayer)) {
                setWinner(board[0][2]);
                setWinner(board[1][1]);
                setWinner(board[2][0]);
                gameOver = true;
                return;                
            }
        }
        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    noWinner(board[r][c]);
                }
            }
            gameOver = true;
        }
    };

    void setWinner(JButton tile) {
        tile.setForeground(Color.black);
        tile.setBackground(Color.green);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    void noWinner(JButton tile) {
        tile.setForeground(Color.black);
        tile.setBackground(Color.orange);
        textLabel.setText("Tie!");
    }
}
