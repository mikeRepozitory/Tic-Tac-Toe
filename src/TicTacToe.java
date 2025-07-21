import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {

    int boardWith = 600;
    int boadHeith = 650; //50px for the test panel top

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardJPanel = new JPanel();    

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";

    int playerOScore = 0;
    int playerXScore = 0;
    
    String currectPlayer = playerX;

    boolean gameOver = false;
    int turns =  0;

    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWith, boadHeith);
        frame.setLocationRelativeTo(null); //open in the cenetr of the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        //frame.getContentPane().add(textPanel);
        //frame.add(textPanel, BorderLayout.NORTH);
        frame.add(textPanel, BorderLayout.NORTH);        
       // frame.setVisible(true);


       boardJPanel.setLayout(new GridLayout(3, 3));
       boardJPanel.setBackground(Color.darkGray);
       frame.add(boardJPanel);


       for( int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                JButton tile = new JButton();
                board[r][c] = tile;
                boardJPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                //tile.setText(currectPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == ""){
                            tile.setText(currectPlayer);
                            turns++;

                            checkWinner();
                            if(!gameOver){
                                currectPlayer = currectPlayer == playerX ? playerO : playerX;
                                textLabel.setText( currectPlayer + "'s turn.");                                
                            }

                        }
                    }
                });
            }
        }


    }
            void checkWinner(){
                //horizantal
                for (int r = 0; r < 3; r++){
                    if (board[r][0].getText() == "") continue;
                    
                    if (board[r][0].getText() == board[r][1].getText() &&
                        board[r][1].getText() == board[r][2].getText()) {
                            for (int i = 0; i < 3; i++){
                                setWinner(board[r][i]);
                            }
                            gameOver = true;
                            return;
                        }
                }

                // vertical
                for (int c = 0; c < 3; c++){
                    if (board[0][c].getText() == "") continue;

                    if (board[0][c].getText() == board[1][c].getText() &&
                        board[1][c].getText() == board[2][c].getText()){

                        for(int i = 0; i < 3; i++ ){
                            setWinner(board[i][c]);
                        }
                        gameOver = true;
                        return;
                    }
                }

                //diagionally 
                if (board[0][0].getText() == board[1][1].getText() &&
                    board[1][1].getText() == board[2][2].getText() &&
                    board[0][0].getText() != ""){
                    for(int i = 0; i < 3;  i++){
                        setWinner(board[i][i]);
                    }
                    gameOver = true;
                    return;
                }


                //anti-diagionally 
                if(board[0][2].getText() == board[1][1].getText() &&
                   board[1][1].getText() == board[2][0].getText() &&
                   board[0][2].getText() != ""){
                    for (int i = 0; i<3; i++) {
	                    setWinner(board[i][2-i]);
}
                    // setWinner(board[0][2]);
                    // setWinner(board[1][1]);
                    // setWinner(board[2][0]);
                    gameOver = true;
                }

               //tie check 
               if (turns == 9) {
                    for (int i = 0; i < 3; i++){
                        for (int c = 0; c < 3; c++) {
                            setTie(board[i][c]);
                        }         
                    }
                    gameOver = true;

               }
        }

        void setWinner(JButton tile){
            tile.setForeground(Color.green);
            tile.setBackground(Color.gray);
            textLabel.setText(currectPlayer + " is the winner!");
        }

        void setTie(JButton tile){
            tile.setForeground(Color.orange);
            tile.setBackground(Color.gray);
            textLabel.setText("Tie!");
        }
}
