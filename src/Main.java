import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Main implements ActionListener {

    JFrame frame;
    JTextField numDisplay;

    JPanel panel1;
    JPanel panel2;

    JPanel historyPanel;
    JScrollBar test;

    JTextArea textArea1;
    JTextArea textArea2;

    JButton[] numberKeys = new JButton[10];
    JButton[] functionKeys = new JButton[17];

    JButton add, subtract, multiply, divide, equal;
    JButton decimal, negative, clear, delete, pi, clearHistory;
    JButton tangent, sine, cosine, log, square, squareRoot;

    Double result, num1, num2;
    char operator;

    Font myCustomFont = new Font("Cooper Black", Font.BOLD,24);
    Font myFont = new Font("Roboto", Font.BOLD,32);

    Main() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(750, 650);
        frame.setLayout(null);

        numDisplay = new JTextField();
        numDisplay.setBounds(25, 200, 312, 50);

        numDisplay.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.gray));

        numDisplay.setFont(myFont);
        numDisplay.setEditable(false);

        numDisplay.setHorizontalAlignment(0);

        // ----------------------- //

        add = new JButton("+");
        subtract = new JButton("-");
        multiply = new JButton("x");
        divide = new JButton("÷");

        equal = new JButton("=");
        clearHistory = new JButton(" CLEAR HISTORY ");

        decimal = new JButton(".");
        delete = new JButton("DELETE");
        clear = new JButton("CLEAR");

        negative = new JButton("(-)");
        pi = new JButton("π");
        square = new JButton("²");
        squareRoot = new JButton("√");

        log = new JButton("log");
        tangent = new JButton("tan");
        sine = new JButton("sin");
        cosine = new JButton("cos");

        // ----------------------- //

        functionKeys[0] = add;
        functionKeys[1] = subtract;
        functionKeys[2] = multiply;
        functionKeys[3] = divide;
        functionKeys[4] = equal;
        functionKeys[5] = decimal;
        functionKeys[6] = negative;
        functionKeys[7] = clear;
        functionKeys[8] = delete;
        functionKeys[9] = pi;
        functionKeys[10] = square;
        functionKeys[11] = squareRoot;
        functionKeys[12] = log;
        functionKeys[13] = sine;
        functionKeys[14] = cosine;
        functionKeys[15] = tangent;
        functionKeys[16] = clearHistory;

        // ----------------------- //

        for (int i = 0; i < 17; i++) {
            functionKeys[i].addActionListener(this);
            functionKeys[i].setFont(myCustomFont);
            functionKeys[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberKeys[i] = new JButton(String.valueOf(i));
            numberKeys[i].addActionListener(this);
            numberKeys[i].setFont(myCustomFont);
            numberKeys[i].setFocusable(false);
        }

        // ----------------------- //

        panel1 = new JPanel();
        panel1.setBounds(25, 290, 312, 300);

        panel1.setLayout(new GridLayout(4, 4, 5, 5));
        panel1.setBackground(Color.lightGray); //! Sets the colour to gray !

        // ----------------------- //

        panel2 = new JPanel();
        panel2.setBounds(25, 25, 312, 100);

        panel2.setLayout(new GridLayout(2, 4, 2, 2));
        panel2.setBackground(Color.lightGray);

        // ----------------------- //

        historyPanel = new JPanel();
        historyPanel.setBounds(370, 90, 350, 450);

        historyPanel.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.darkGray));
        historyPanel.setBackground(Color.lightGray);

        // ----------------------- //

        textArea2 = new JTextArea();
        textArea2.setBounds(400, 90, 345, 445);

        textArea2.setFont(myCustomFont);
        textArea2.setBorder(BorderFactory.createTitledBorder("History"));

        textArea2.setOpaque(false);
        textArea2.setEditable(false);

        textArea2.setText("                                           ");
        historyPanel.add(textArea2);
        // ----------------------- //

        test = new JScrollBar();
        test.setBounds(350, 90, 15, 450);

        test.setEnabled(false);

        // ----------------------- //

        textArea1 = new JTextArea();
        textArea1.setBounds(370,25,350,50);

        textArea1.setBorder(BorderFactory.createBevelBorder(0, Color.darkGray, Color.lightGray));
        textArea1.setBackground(Color.black);

        textArea1.setForeground(new Color(139, 64, 0));

        textArea1.setText("   Custom Calculator");
        textArea1.setFont(myFont);

        textArea1.setFocusable(false);
        textArea1.setEditable(false);

        // ----------------------- //

        panel2.add(log);
        panel2.add(tangent);
        panel2.add(sine);
        panel2.add(cosine);

        panel2.add(pi);
        panel2.add(negative);
        panel2.add(square);
        panel2.add(squareRoot);

        // ----------------------- //

        clear.setBounds(30, 150, 150, 40);
        delete.setBounds(180, 150, 150, 40);
        clearHistory.setBounds(385, 550, 320, 40);

        // ----------------------- //

        panel1.add(numberKeys[1]);
        panel1.add(numberKeys[2]);
        panel1.add(numberKeys[3]);
        panel1.add(add);

        panel1.add(numberKeys[4]);
        panel1.add(numberKeys[5]);
        panel1.add(numberKeys[6]);
        panel1.add(subtract);

        panel1.add(numberKeys[7]);
        panel1.add(numberKeys[8]);
        panel1.add(numberKeys[9]);
        panel1.add(multiply);

        panel1.add(decimal);
        panel1.add(numberKeys[0]);
        panel1.add(equal);
        panel1.add(divide);

        // ----------------------- //

        frame.add(textArea1);
        frame.add(test);
        frame.add(historyPanel);

        frame.add(clear);
        frame.add(delete);
        frame.add(clearHistory);

        frame.add(panel2);
        frame.add(panel1);
        frame.add(numDisplay);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }

    public static void main(String[] args) {
        Main main = new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberKeys[i]) {
                numDisplay.setText(numDisplay.getText().concat(String.valueOf(i)));
                // Set the text --> Of textField -
                // -> To get the text already present (like in "2") -
                // -> And join the new number (e.g : "1") --> With the old one ("21") ||
            }
        }

        if (e.getSource() == decimal) {
            numDisplay.setText(numDisplay.getText().concat(decimal.getText()));
            // Set the text --> Of textField -
            // -> To get the number already present (like in "2") -
            // -> And join the decimal (".") --> With the number ("2.1") ||
        }

        if (e.getSource() == add) {
            num1 = Double.parseDouble(numDisplay.getText());
            operator = '+';
            numDisplay.setText("");
        }
        if (e.getSource() == subtract) {
            num1 = Double.parseDouble(numDisplay.getText());
            operator = '-';
            numDisplay.setText("");
        }
        if (e.getSource() == multiply) {
            num1 = Double.parseDouble(numDisplay.getText());
            operator = 'x';
            numDisplay.setText("");
        }
        if (e.getSource() == divide) {
            num1 = Double.parseDouble(numDisplay.getText());
            operator = '÷';
            numDisplay.setText("");
        }

        if (e.getSource() == equal) {
            if (operator == 'π' || operator == '²' || operator == '√') {
                for (int i = 0; i < numDisplay.getText().length(); i++) {
                    if (numDisplay.getText().charAt(i) == 'π' || numDisplay.getText().charAt(i) == '²' || numDisplay.getText().charAt(i) == '√') {
                        num2 = Double.parseDouble(charRemoveAt(numDisplay.getText(), i));
                    }
                }
            }
            else {
                num2 = Double.parseDouble(numDisplay.getText());
            }

            switch (operator) {
                case '+' -> result = num1 + num2; // In Addition
                case '-' -> result = num1 - num2; // For Subtraction
                case 'x' -> result = num1 * num2; // For Multiplication
                case '÷' -> result = num1 / num2; // For Division

                case 'π' -> result = num1 * Math.PI;
                case '²' -> result = (num1 * num1);
                case '√' -> result = Math.sqrt(num1);
            }

            DecimalFormat dNum = new DecimalFormat("0.000000000");

            dNum.setRoundingMode(RoundingMode.HALF_EVEN);
            result = Double.parseDouble(dNum.format(result));

            if (operator == 'π' || operator == '²') {
                textArea2.setText(textArea2.getText() + "\n" + num1 + " " + operator + " = " + result);
            } else if (operator == '√') {
                textArea2.setText(textArea2.getText() + "\n" + operator + num1 + " = " + result);
            } else {
                textArea2.setText(textArea2.getText() + "\n" + num1 + " " + operator + " " + num2 + " = " + result);
            }

            numDisplay.setText(result.toString());
            num1 = result;
        }

        if (e.getSource() == clear) {
            numDisplay.setText("");
        }

        if (e.getSource() == clearHistory) {
            textArea2.setText("                                           ");
        }

        if (e.getSource() == delete){
            String string = numDisplay.getText();
            numDisplay.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                numDisplay.setText(numDisplay.getText() + string.charAt(i));
            }
        }

        if (e.getSource() == negative){
            Double temp = Double.parseDouble(numDisplay.getText());
            temp *= -1;
            numDisplay.setText(String.valueOf(temp));
        }

        // ---------------------- //

        if (e.getSource() == pi){
            num1 = Double.parseDouble(numDisplay.getText());
            operator = 'π';
            numDisplay.setText(numDisplay.getText() + "π");
        }
        if (e.getSource() == square){
            num1 = Double.parseDouble(numDisplay.getText());
            operator = '²';
            numDisplay.setText(numDisplay.getText() + "²");
        }
        if (e.getSource() == squareRoot){
            num1 = Double.parseDouble(numDisplay.getText());
            operator = '√';
            numDisplay.setText("√" + numDisplay.getText());
        }

        if (e.getSource() == tangent){
            Double temp = Double.parseDouble(numDisplay.getText());
            temp = Math.tan(temp);
            numDisplay.setText(String.valueOf(temp));
        }
        if (e.getSource() == sine){
            Double temp = Double.parseDouble(numDisplay.getText());
            temp = Math.sin(temp);
            numDisplay.setText(String.valueOf(temp));
        }
        if (e.getSource() == cosine){
            Double temp = Double.parseDouble(numDisplay.getText());
            temp = Math.cos(temp);
            numDisplay.setText(String.valueOf(temp));
        }

        if (e.getSource() == log){
            Double temp = Double.parseDouble(numDisplay.getText());
            temp = Math.log10(temp);
            numDisplay.setText(String.valueOf(temp));
        }
    }
}

// Just In Case I Forget And Delete It !
// textArea2.setText("                                           ");