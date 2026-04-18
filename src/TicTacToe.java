import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel= new JPanel();
    JPanel boardPanel = new JPanel();

    boolean gameOver = false;
    int turns = 0;  

    JButton[][] board = new JButton [3][3];
    String player1 = "X";
    String player2 = "O";
    String currentPlayer = player1;

        TicTacToe(){
            frame.setVisible(true);
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
            frame.add(textPanel,BorderLayout.NORTH);

            boardPanel.setLayout(new GridLayout(3,3));
            boardPanel.setBackground(Color.LIGHT_GRAY);
            

            frame.add(boardPanel);

            for(int r=0; r<3; r++){
                for(int c=0; c<3; c++){
                    JButton tile = new JButton();
                    board[r][c] = tile;
                    board[r][c].setFont(new Font("Arial", Font.BOLD, 100));
                    boardPanel.add(board[r][c]);

                    tile.setBackground(Color.black);
                    tile.setForeground(Color.white);
                    tile.setFocusable(false);

                    tile.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            JButton tile= (JButton) e.getSource();

                            if (gameOver) return;

                            if(tile.getText()==""){
                            tile.setText(currentPlayer);
                                turns++;
                                checkWinner(); 
                                if (!gameOver){
                            currentPlayer = currentPlayer==player1 ? player2 : player1;
                            textLabel.setText( "tour du joueur " + currentPlayer);
                        }
                    }
                        }
                    });
                  
                            }
                        }
        }   

     void checkWinner(){
        //horizontal
        for(int r=0; r<3; r++){
                    if(board[r][0].getText() == "") continue;

                    if(board[r][0].getText() == board[r][1].getText() && board[r][0].getText() == board[r][2].getText()){
                        for(int i=0; i<3; i++){
                            setWinner(board[r][i]);
                        }
                        gameOver = true;
                        return;
                    }
                }


              //vertical
        for(int c=0; c<3; c++){
            if(board[0][c].getText() == "") continue;

            if(board[0][c].getText() == board[1][c].getText() && board[0][c].getText() == board[2][c].getText()){
                for(int i=0; i<3; i++){
                    setWinner(board[i][c]);}
                gameOver = true;
                return;
            }
        }
 
        //diagonal
        if(board[0][0].getText() ==  board[1][1].getText() &&
           board[1][1].getText() == board[2][2].getText()
           && board[0][0].getText() != ""){
            for(int i=0; i<3; i++){
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        //anti-diagonal
        if(board[0][2].getText() ==  board[1][1].getText() &&
           board[1][1].getText() == board[2][0].getText()
           && board[0][2].getText() != ""){
            for(int i=0; i<3; i++){
                setWinner(board[i][2-i]);
            }
            gameOver = true;
            return;
        }

        if(turns == 9){
            for(int r=0; r<3; r++){
                for(int c=0; c<3; c++){
                    setTie(board[r][c]);    
                }
            }
        }

        }

      void setWinner(JButton tile){
                    tile.setBackground(Color.green);
                    textLabel.setText("le joueur " + currentPlayer + " a gagné !");
            }    
       void setTie(JButton tile){
                    tile.setForeground(Color.orange);
                    textLabel.setText("Match nul !");
            }        
}