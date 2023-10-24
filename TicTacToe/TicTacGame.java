import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacGame {
    private static char currentPlayer = 'X';
    private static JButton[][] buttons = new JButton[3][3];
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(3, 3));

        initializeButtons();

        frame.setVisible(true);
    }

    private static void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 48));
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                frame.add(buttons[row][col]);
            }
        }
    }

    private static class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("") && !checkWin()) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                buttons[row][col].setEnabled(false);
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';

                if (checkWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + (currentPlayer == 'X' ? 'O' : 'X') + " wins!");
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "It's a draw!");
                }
            }
        }
    }

    private static boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer))
                    && buttons[i][1].getText().equals(String.valueOf(currentPlayer))
                    && buttons[i][2].getText().equals(String.valueOf(currentPlayer))
                    || buttons[0][i].getText().equals(String.valueOf(currentPlayer))
                    && buttons[1][i].getText().equals(String.valueOf(currentPlayer))
                    && buttons[2][i].getText().equals(String.valueOf(currentPlayer)) || buttons[0][0].getText()
                    .equals(String.valueOf(currentPlayer))
                    && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                    && buttons[2][2].getText().equals(String.valueOf(currentPlayer)) || buttons[0][2].getText()
                    .equals(String.valueOf(currentPlayer))
                    && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                    && buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
}
