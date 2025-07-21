import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {

    int boardWidth = 600;
    int boardHeight = 700; // Extra height for text and buttons

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JLabel scoreLabel = new JLabel("Score: X=0 | O=0");
    JPanel textPanel = new JPanel(new BorderLayout());
    JPanel boardPanel = new JPanel();
    JButton[][] board = new JButton[3][3];
    JButton clearButton = new JButton("Clear");

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    int playerXScore = 0;
    int playerOScore = 0;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null); // Center of the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Text Label
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 40));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        // Score Label
        scoreLabel.setBackground(Color.darkGray);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setOpaque(true);

        // Clear Button
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.setFocusable(false);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });

        textPanel.add(textLabel, BorderLayout.CENTER);
        textPanel.add(scoreLabel, BorderLayout.SOUTH);
        textPanel.add(clearButton, BorderLayout.EAST);
        frame.add(textPanel, BorderLayout.NORTH);

        // Board
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        // Initialize board
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton clickedTile = (JButton) e.getSource();
                        if (clickedTile.getText().equals("")) {
                            clickedTile.setText(currentPlayer);
                            turns++;

                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s Turn");
                            }
                        }
                    }
                });
            }
        }
    }

    void checkWinner() {
        // Horizontal check
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText().equals("")) continue;

            if (board[r][0].getText().equals(board[r][1].getText()) &&
                board[r][1].getText().equals(board[r][2].getText())) {
                for (int i = 0; i < 3; i++) setWinner(board[r][i]);
                handleWin();
                return;
            }
        }

        // Vertical check
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText().equals("")) continue;

            if (board[0][c].getText().equals(board[1][c].getText()) &&
                board[1][c].getText().equals(board[2][c].getText())) {
                for (int i = 0; i < 3; i++) setWinner(board[i][c]);
                handleWin();
                return;
            }
        }

        // Diagonal
        if (board[0][0].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][2].getText()) &&
            !board[0][0].getText().equals("")) {
            for (int i = 0; i < 3; i++) setWinner(board[i][i]);
            handleWin();
            return;
        }

        // Anti-diagonal
        if (board[0][2].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][0].getText()) &&
            !board[0][2].getText().equals("")) {
            for (int i = 0; i < 3; i++) setWinner(board[i][2 - i]);
            handleWin();
            return;
        }

        // Tie check
        if (turns == 9) {
            for (int i = 0; i < 3; i++) {
                for (int c = 0; c < 3; c++) setTie(board[i][c]);
            }
            textLabel.setText("It's a Tie!");
            gameOver = true;
        }
    }

    void handleWin() {
        textLabel.setText(currentPlayer + " Wins!");
        gameOver = true;
        if (currentPlayer.equals(playerX)) playerXScore++;
        else playerOScore++;
        updateScore();
        checkChampion();
    }

    void updateScore() {
        scoreLabel.setText("Score: X=" + playerXScore + " | O=" + playerOScore);
    }

    void checkChampion() {
        if (playerXScore == 3 || playerOScore == 3) {
            String champion = playerXScore == 3 ? "Player X" : "Player O";
            JOptionPane.showMessageDialog(frame, champion + " is the Champion with 3 wins!");
            playerXScore = 0;
            playerOScore = 0;
            updateScore();
            resetBoard();
        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
    }

    void resetBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setForeground(Color.white);
                board[r][c].setBackground(Color.darkGray);
            }
        }
        currentPlayer = playerX;
        turns = 0;
        gameOver = false;
        textLabel.setText("Tic-Tac-Toe");
    }


}
