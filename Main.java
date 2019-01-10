package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    // Declarations
    private int clearedCount = 0;
    private Button[] button = new Button[3];
    private String[] board = new String[100];
    private String[][] groups = new String[27][];
    private String[][] relations = new String[100][];
    private TextField[] textFields = new TextField[100];
    private void groupsDecs() {
        groups[0] = new String[]{"11", "12", "13", "14", "15", "16", "17", "18", "19"};
        groups[1] = new String[]{"21", "22", "23", "24", "25", "26", "27", "28", "29"};
        groups[2] = new String[]{"31", "32", "33", "34", "35", "36", "37", "38", "39"};
        groups[3] = new String[]{"41", "42", "43", "44", "45", "46", "47", "48", "49"};
        groups[4] = new String[]{"51", "52", "53", "54", "55", "56", "57", "58", "59"};
        groups[5] = new String[]{"61", "62", "63", "64", "65", "66", "67", "68", "69"};
        groups[6] = new String[]{"71", "72", "73", "74", "75", "76", "77", "78", "79"};
        groups[7] = new String[]{"81", "82", "83", "84", "85", "86", "87", "88", "89"};
        groups[8] = new String[]{"91", "92", "93", "94", "95", "96", "97", "98", "99"};

        groups[9] = new String[]{"11", "21", "31", "41", "51", "61", "71", "81", "91"};
        groups[10] = new String[]{"12", "22", "32", "42", "52", "62", "72", "82", "92"};
        groups[11] = new String[]{"13", "23", "33", "43", "53", "63", "73", "83", "93"};
        groups[12] = new String[]{"14", "24", "34", "44", "54", "64", "74", "84", "94"};
        groups[13] = new String[]{"15", "25", "35", "45", "55", "65", "75", "85", "95"};
        groups[14] = new String[]{"16", "26", "36", "46", "56", "66", "76", "86", "96"};
        groups[15] = new String[]{"17", "27", "37", "47", "57", "67", "77", "87", "97"};
        groups[16] = new String[]{"18", "28", "38", "48", "58", "68", "78", "88", "98"};
        groups[17] = new String[]{"19", "29", "39", "49", "59", "69", "79", "89", "99"};

        groups[18] = new String[]{"11", "12", "13", "21", "22", "23", "31", "32", "33"};
        groups[19] = new String[]{"14", "15", "16", "24", "25", "26", "34", "35", "36"};
        groups[20] = new String[]{"17", "18", "19", "27", "28", "29", "37", "38", "39"};
        groups[21] = new String[]{"41", "42", "43", "51", "52", "53", "61", "62", "63"};
        groups[22] = new String[]{"44", "45", "46", "54", "55", "56", "64", "65", "66"};
        groups[23] = new String[]{"47", "48", "49", "57", "58", "59", "67", "68", "69"};
        groups[24] = new String[]{"71", "72", "73", "81", "82", "83", "91", "92", "93"};
        groups[25] = new String[]{"74", "75", "76", "84", "85", "86", "94", "95", "96"};
        groups[26] = new String[]{"77", "78", "79", "87", "88", "89", "97", "98", "99"};

        relations[11] = new String[]{"12", "13", "14", "15", "16", "17", "18", "19", "21", "22", "23", "31", "32", "33", "41", "51", "61", "71", "81", "91", "100"};
        relations[12] = new String[]{"11", "13", "14", "15", "16", "17", "18", "19", "21", "22", "23", "31", "32", "33", "42", "52", "62", "72", "82", "92", "100"};
        relations[13] = new String[]{"11", "12", "14", "15", "16", "17", "18", "19", "21", "22", "23", "31", "32", "33", "43", "53", "63", "73", "83", "93", "100"};
        relations[14] = new String[]{"11", "12", "13", "15", "16", "17", "18", "19", "24", "25", "26", "34", "35", "36", "44", "54", "64", "74", "84", "94", "100"};
        relations[15] = new String[]{"11", "12", "13", "14", "16", "17", "18", "19", "24", "25", "26", "34", "35", "36", "45", "55", "65", "75", "85", "95", "100"};
        relations[16] = new String[]{"11", "12", "13", "14", "15", "17", "18", "19", "24", "25", "26", "34", "35", "36", "46", "56", "66", "76", "86", "96", "100"};
        relations[17] = new String[]{"11", "12", "13", "14", "15", "16", "18", "19", "27", "28", "29", "37", "38", "39", "47", "57", "67", "77", "87", "97", "100"};
        relations[18] = new String[]{"11", "12", "13", "14", "15", "16", "17", "19", "27", "28", "29", "37", "38", "39", "48", "58", "68", "78", "88", "98", "100"};
        relations[19] = new String[]{"11", "12", "13", "14", "15", "16", "17", "18", "27", "28", "29", "37", "38", "39", "49", "59", "69", "79", "89", "99", "100"};

        relations[21] = new String[]{"11", "12", "13", "22", "23", "24", "25", "26", "27", "28", "29", "31", "32", "33", "41", "51", "61", "71", "81", "91", "100"};
        relations[22] = new String[]{"11", "12", "13", "21", "23", "24", "25", "26", "27", "28", "29", "31", "32", "33", "42", "52", "62", "72", "82", "92", "100"};
        relations[23] = new String[]{"11", "12", "13", "21", "22", "24", "25", "26", "27", "28", "29", "31", "32", "33", "43", "53", "63", "73", "83", "93", "100"};
        relations[24] = new String[]{"14", "15", "16", "21", "22", "23", "25", "26", "27", "28", "29", "34", "35", "36", "44", "54", "64", "74", "84", "94", "100"};
        relations[25] = new String[]{"14", "15", "16", "21", "22", "23", "24", "26", "27", "28", "29", "34", "35", "36", "45", "55", "65", "75", "85", "95", "100"};
        relations[26] = new String[]{"14", "15", "16", "21", "22", "23", "24", "25", "27", "28", "29", "34", "35", "36", "46", "56", "66", "76", "86", "96", "100"};
        relations[27] = new String[]{"17", "18", "19", "21", "22", "23", "24", "25", "26", "28", "29", "37", "38", "39", "47", "57", "67", "77", "87", "97", "100"};
        relations[28] = new String[]{"17", "18", "19", "21", "22", "23", "24", "25", "26", "27", "29", "37", "38", "39", "48", "58", "68", "78", "88", "98", "100"};
        relations[29] = new String[]{"17", "18", "19", "21", "22", "23", "24", "25", "26", "27", "28", "37", "38", "39", "49", "59", "69", "79", "89", "99", "100"};

        relations[31] = new String[]{"11", "12", "13", "21", "22", "23", "32", "33", "34", "35", "36", "37", "38", "39", "41", "51", "61", "71", "81", "91", "100"};
        relations[32] = new String[]{"11", "12", "13", "21", "22", "23", "31", "33", "34", "35", "36", "37", "38", "39", "42", "52", "62", "72", "82", "92", "100"};
        relations[33] = new String[]{"11", "12", "13", "21", "22", "23", "31", "32", "34", "35", "36", "37", "38", "39", "43", "53", "63", "73", "83", "93", "100"};
        relations[34] = new String[]{"14", "15", "16", "24", "25", "26", "31", "32", "33", "35", "36", "37", "38", "39", "44", "54", "64", "74", "84", "94", "100"};
        relations[35] = new String[]{"14", "15", "16", "24", "25", "26", "31", "32", "33", "34", "36", "37", "38", "39", "45", "55", "65", "75", "85", "95", "100"};
        relations[36] = new String[]{"14", "15", "16", "24", "25", "26", "31", "32", "33", "34", "35", "37", "38", "39", "46", "56", "66", "76", "86", "96", "100"};
        relations[37] = new String[]{"17", "18", "19", "27", "28", "29", "31", "32", "33", "34", "35", "36", "38", "39", "47", "57", "67", "77", "87", "97", "100"};
        relations[38] = new String[]{"17", "18", "19", "27", "28", "29", "31", "32", "33", "34", "35", "36", "37", "39", "48", "58", "68", "78", "88", "98", "100"};
        relations[39] = new String[]{"17", "18", "19", "27", "28", "29", "31", "32", "33", "34", "35", "36", "37", "38", "49", "59", "69", "79", "89", "99", "100"};

        relations[41] = new String[]{"11", "21", "31", "42", "43", "44", "45", "46", "47", "48", "49", "51", "52", "53", "61", "62", "63", "71", "81", "91", "100"};
        relations[42] = new String[]{"12", "22", "32", "41", "43", "44", "45", "46", "47", "48", "49", "51", "52", "53", "61", "62", "63", "72", "82", "92", "100"};
        relations[43] = new String[]{"13", "23", "33", "41", "42", "44", "45", "46", "47", "48", "49", "51", "52", "53", "61", "62", "63", "73", "83", "93", "100"};
        relations[44] = new String[]{"14", "24", "34", "41", "42", "43", "45", "46", "47", "48", "49", "54", "55", "56", "64", "65", "66", "74", "84", "94", "100"};
        relations[45] = new String[]{"15", "25", "35", "41", "42", "43", "44", "46", "47", "48", "49", "54", "55", "56", "64", "65", "66", "75", "85", "95", "100"};
        relations[46] = new String[]{"16", "26", "36", "41", "42", "43", "44", "45", "47", "48", "49", "54", "55", "56", "64", "65", "66", "76", "86", "96", "100"};
        relations[47] = new String[]{"17", "27", "37", "41", "42", "43", "44", "45", "46", "48", "49", "57", "58", "59", "67", "68", "69", "77", "87", "97", "100"};
        relations[48] = new String[]{"18", "28", "38", "41", "42", "43", "44", "45", "46", "47", "49", "57", "58", "59", "67", "68", "69", "78", "88", "98", "100"};
        relations[49] = new String[]{"19", "29", "39", "41", "42", "43", "44", "45", "46", "47", "48", "57", "58", "59", "67", "68", "69", "79", "89", "99", "100"};

        relations[51] = new String[]{"11", "21", "31", "41", "42", "43", "52", "53", "54", "55", "56", "57", "58", "59", "61", "62", "63", "71", "81", "91", "100"};
        relations[52] = new String[]{"12", "22", "32", "41", "42", "43", "51", "53", "54", "55", "56", "57", "58", "59", "61", "62", "63", "72", "82", "92", "100"};
        relations[53] = new String[]{"13", "23", "33", "41", "42", "43", "51", "52", "54", "55", "56", "57", "58", "59", "61", "62", "63", "73", "83", "93", "100"};
        relations[54] = new String[]{"14", "24", "34", "44", "45", "46", "51", "52", "53", "55", "56", "57", "58", "59", "64", "65", "66", "74", "84", "94", "100"};
        relations[55] = new String[]{"15", "25", "35", "44", "45", "46", "51", "52", "53", "54", "56", "57", "58", "59", "64", "65", "66", "75", "85", "95", "100"};
        relations[56] = new String[]{"16", "26", "36", "44", "45", "46", "51", "52", "53", "54", "55", "57", "58", "59", "64", "65", "66", "76", "86", "96", "100"};
        relations[57] = new String[]{"17", "27", "37", "47", "48", "49", "51", "52", "53", "54", "55", "56", "58", "59", "67", "68", "69", "77", "87", "97", "100"};
        relations[58] = new String[]{"18", "28", "38", "47", "48", "49", "51", "52", "53", "54", "55", "56", "57", "59", "67", "68", "69", "78", "88", "98", "100"};
        relations[59] = new String[]{"19", "29", "39", "47", "48", "49", "51", "52", "53", "54", "55", "56", "57", "58", "67", "68", "69", "79", "89", "99", "100"};

        relations[61] = new String[]{"11", "21", "31", "41", "42", "43", "51", "52", "53", "62", "63", "64", "65", "66", "67", "68", "69", "71", "81", "91", "100"};
        relations[62] = new String[]{"12", "22", "32", "41", "42", "43", "51", "52", "53", "61", "63", "64", "65", "66", "67", "68", "69", "72", "82", "92", "100"};
        relations[63] = new String[]{"13", "23", "33", "41", "42", "43", "51", "52", "53", "61", "62", "64", "65", "66", "67", "68", "69", "73", "83", "93", "100"};
        relations[64] = new String[]{"14", "24", "34", "44", "45", "46", "54", "55", "56", "61", "62", "63", "65", "66", "67", "68", "69", "74", "84", "94", "100"};
        relations[65] = new String[]{"15", "25", "35", "44", "45", "46", "54", "55", "56", "61", "62", "63", "64", "66", "67", "68", "69", "75", "85", "95", "100"};
        relations[66] = new String[]{"16", "26", "36", "44", "45", "46", "54", "55", "56", "61", "62", "63", "64", "65", "67", "68", "69", "76", "86", "96", "100"};
        relations[67] = new String[]{"17", "27", "37", "47", "48", "49", "57", "58", "59", "61", "62", "63", "64", "65", "66", "68", "69", "77", "87", "97", "100"};
        relations[68] = new String[]{"18", "28", "38", "47", "48", "49", "57", "58", "59", "61", "62", "63", "64", "65", "66", "67", "69", "78", "88", "98", "100"};
        relations[69] = new String[]{"19", "29", "39", "47", "48", "49", "57", "58", "59", "61", "62", "63", "64", "65", "66", "67", "68", "79", "89", "99", "100"};

        relations[71] = new String[]{"11", "21", "31", "41", "51", "61", "72", "73", "74", "75", "76", "77", "78", "79", "81", "82", "83", "91", "92", "93", "100"};
        relations[72] = new String[]{"12", "22", "32", "42", "52", "62", "71", "73", "74", "75", "76", "77", "78", "79", "81", "82", "83", "91", "92", "93", "100"};
        relations[73] = new String[]{"13", "23", "33", "43", "53", "63", "71", "72", "74", "75", "76", "77", "78", "79", "81", "82", "83", "91", "92", "93", "100"};
        relations[74] = new String[]{"14", "24", "34", "44", "54", "64", "71", "72", "73", "75", "76", "77", "78", "79", "84", "85", "86", "94", "95", "96", "100"};
        relations[75] = new String[]{"15", "25", "35", "45", "55", "65", "71", "72", "73", "74", "76", "77", "78", "79", "84", "85", "86", "94", "95", "96", "100"};
        relations[76] = new String[]{"16", "26", "36", "46", "56", "66", "71", "72", "73", "74", "75", "77", "78", "79", "84", "85", "86", "94", "95", "96", "100"};
        relations[77] = new String[]{"17", "27", "37", "47", "57", "67", "71", "72", "73", "74", "75", "76", "78", "79", "87", "88", "89", "97", "98", "99", "100"};
        relations[78] = new String[]{"18", "28", "38", "48", "58", "68", "71", "72", "73", "74", "75", "76", "77", "79", "87", "88", "89", "97", "98", "99", "100"};
        relations[79] = new String[]{"19", "29", "39", "49", "59", "69", "71", "72", "73", "74", "75", "76", "77", "78", "87", "88", "89", "97", "98", "99", "100"};

        relations[81] = new String[]{"11", "21", "31", "41", "51", "61", "71", "72", "73", "82", "83", "84", "85", "86", "87", "88", "89", "91", "92", "93", "100"};
        relations[82] = new String[]{"12", "22", "32", "42", "52", "62", "71", "72", "73", "81", "83", "84", "85", "86", "87", "88", "89", "91", "92", "93", "100"};
        relations[83] = new String[]{"13", "23", "33", "43", "53", "63", "71", "72", "73", "81", "82", "84", "85", "86", "87", "88", "89", "91", "92", "93", "100"};
        relations[84] = new String[]{"14", "24", "34", "44", "54", "64", "74", "75", "76", "81", "82", "83", "85", "86", "87", "88", "89", "94", "95", "96", "100"};
        relations[85] = new String[]{"15", "25", "35", "45", "55", "65", "74", "75", "76", "81", "82", "83", "84", "86", "87", "88", "89", "94", "95", "96", "100"};
        relations[86] = new String[]{"16", "26", "36", "46", "56", "66", "74", "75", "76", "81", "82", "83", "84", "85", "87", "88", "89", "94", "95", "96", "100"};
        relations[87] = new String[]{"17", "27", "37", "47", "57", "67", "77", "78", "79", "81", "82", "83", "84", "85", "86", "88", "89", "97", "98", "99", "100"};
        relations[88] = new String[]{"18", "28", "38", "48", "58", "68", "77", "78", "79", "81", "82", "83", "84", "85", "86", "87", "89", "97", "98", "99", "100"};
        relations[89] = new String[]{"19", "29", "39", "49", "59", "69", "77", "78", "79", "81", "82", "83", "84", "85", "86", "87", "88", "97", "98", "99", "100"};

        relations[91] = new String[]{"11", "21", "31", "41", "51", "61", "71", "72", "73", "81", "82", "83", "92", "93", "94", "95", "96", "97", "98", "99", "100"};
        relations[92] = new String[]{"12", "22", "32", "42", "52", "62", "71", "72", "73", "81", "82", "83", "91", "93", "94", "95", "96", "97", "98", "99", "100"};
        relations[93] = new String[]{"13", "23", "33", "43", "53", "63", "71", "72", "73", "81", "82", "83", "91", "92", "94", "95", "96", "97", "98", "99", "100"};
        relations[94] = new String[]{"14", "24", "34", "44", "54", "64", "74", "75", "76", "84", "85", "86", "91", "92", "93", "95", "96", "97", "98", "99", "100"};
        relations[95] = new String[]{"15", "25", "35", "45", "55", "65", "74", "75", "76", "84", "85", "86", "91", "92", "93", "94", "96", "97", "98", "99", "100"};
        relations[96] = new String[]{"16", "26", "36", "46", "56", "66", "74", "75", "76", "84", "85", "86", "91", "92", "93", "94", "95", "97", "98", "99", "100"};
        relations[97] = new String[]{"17", "27", "37", "47", "57", "67", "77", "78", "79", "87", "88", "89", "91", "92", "93", "94", "95", "96", "98", "99", "100"};
        relations[98] = new String[]{"18", "28", "38", "48", "58", "68", "77", "78", "79", "87", "88", "89", "91", "92", "93", "94", "95", "96", "97", "99", "100"};
        relations[99] = new String[]{"19", "29", "39", "49", "59", "69", "77", "78", "79", "87", "88", "89", "91", "92", "93", "94", "95", "96", "97", "98", "100"};
    }
    public static void main(String[] args) {
        launch(args);
    }


    // Build Form
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sudoku Guru");
        GridPane root = new GridPane();
        root.setHgap(1);
        root.setVgap(1);
        root.setAlignment(Pos.TOP_LEFT);
        // Basic setup for the Form

        for (int x = 11; x <= 99; x++) {
            if (x % 10 != 0) {
                textFields[x] = new TextField();
            }
        } // Initialize textFields

        for (int x = 11; x <= 99; x++) {
            if (x % 10 != 0) {
                int addRow = ((x - 10) / 30) * 2;
                int addCol = (((x - 1) % 10) / 3) * 2;
                root.add(textFields[x], (x % 10) + addCol, (x / 10) + addRow);
                textFields[x].setMinWidth(21.5);
                textFields[x].setMaxWidth(21.5);
                textFields[x].setText(board[x]);
            }
        } // Place textFields

        button[0] = new Button("Input");
        button[1] = new Button("Clear");
        button[2] = new Button("Solve");
        // Initialize buttons

        for (int x = 0; x < button.length; x++) {
            button[x].setMinWidth(68);
            button[x].setOnAction(this::onButtonClicked);
            root.add(button[x],1 + (x * 5),14 + (x / 3), 3, 1);
        } // Place buttons

        primaryStage.setScene(new Scene(root, 212, 500));
        primaryStage.show();
        // Display Form
    }


    // Buttons
    private void onButtonClicked(ActionEvent buttonClicked) {
        if (buttonClicked.getSource() == button[0]) { inputBoard(); }
        if (buttonClicked.getSource() == button[1]) { clearBoard(); }
        if (buttonClicked.getSource() == button[2]) { solveBoard(); }
    }


    // Board Handling
    private void inputBoard() {
        // If importing a game string then break up the string and assign it to the board array
        // If not then just assign to the board array
        if (textFields[11].getLength() == 81) {
            for (int x = 99; x >= 11; x--) {
                if (x % 10 != 0) {
                    board[x] = textFields[11].getText().substring(textFields[11].getLength() - 1);
                    textFields[11].setText(textFields[11].getText(0, textFields[11].getLength() - 1));
                }
            }
        } else {
            for (int x = 99; x >= 11; x--) {
                if (x % 10 != 0) {
                    board[x] = textFields[x].getText();
                }
            }
        }
        outputBoard();
    }
    private void outputBoard() {
        // Clears every Cell that is not a Number 1 - 9 and prints the rest to the board
        for (int x = 99; x >= 11; x--) {
            if (x % 10 != 0) {
                if (!board[x].equals("1") && !board[x].equals("2") && !board[x].equals("3") &&
                        !board[x].equals("4") && !board[x].equals("5") && !board[x].equals("6") &&
                        !board[x].equals("7") && !board[x].equals("8") && !board[x].equals("9")) {
                    board[x] = "";
                }
                textFields[x].setText(board[x]);
            }
        }
    }
    private void clearBoard() {
        for (int x = 99; x >= 11; x--) {
            if (x % 10 != 0) {
                textFields[x].clear();
            }
        }
    }


    // Output Tools
    private void assignDefaultValues() {
        // When beginning to solve the board this assigns every blank Cell an open possibility of any Number
        for (int x = 11; x <= 99; x++) {
            if (x % 10 != 0) {
                if (board[x].length() == 0) {
                    board[x] = "123456789";
                }
            }
        }
    }
    private void sysOutPrintBoard() {
        // Prints out Board
        int longest = 1;
        for (int i = 11; i <= 99; i++) {
            if (i % 10 == 0) {
                i++;
            }
            if (board[i].length() > longest) {
                longest = board[i].length();
                if (longest == 9) {
                    i = 99;
                }
            }
        } // Determines the longest string length of the possibilities of each cell

        String borderGrid = "  ";
        for (int x = 1; x <= 32 + longest * 9; x++) {
            borderGrid += "-";
        } // Assigns the top border, as well as the border after each third row, to the String borderGrid

        String colNums = "     ";
        if (longest % 2 != 0) {
            colNums = "    ";
        }
        for (int x = 1; x <= 9; x++) {
            for (int y = 1; y <= longest - 1; y++) {
                if (x == 1) {
                    if (y != longest - 1) {
                        colNums += " ";
                    }
                    y++;
                } else {
                    colNums += " ";
                }
            }
            colNums += "x" + x + "  ";
            if (x % 3 == 0) {
                colNums += " ";
            }
        } // Builds the Column number labels at the top of the printed board

        System.out.println(colNums);
        System.out.println(borderGrid);

        for (int b = 1; b <= 9; b++) {
            String rowString = b + "x|| ";
            for (int c = 1; c <= 9; c++) {
                switch (longest - board[b * 10 + c].length()) {
                    case 0:
                        rowString += board[b * 10 + c];
                        break;
                    case 1:
                        rowString += board[b * 10 + c] + " ";
                        break;
                    case 2:
                        rowString += " " + board[b * 10 + c] + " ";
                        break;
                    case 3:
                        rowString += " " + board[b * 10 + c] + "  ";
                        break;
                    case 4:
                        rowString += "  " + board[b * 10 + c] + "  ";
                        break;
                    case 5:
                        rowString += "  " + board[b * 10 + c] + "   ";
                        break;
                    case 6:
                        rowString += "   " + board[b * 10 + c] + "   ";
                        break;
                    case 7:
                        rowString += "   " + board[b * 10 + c] + "    ";
                        break;
                    case 8:
                        rowString += "    " + board[b * 10 + c] + "    ";
                        break;
                }
                if (c % 3 == 0 && c != 9) {
                    rowString += " || ";
                }
                if (c == 9) {
                    rowString += " ||";
                }
                if (c % 3 != 0) {
                    rowString += " | ";
                }
            }// Prints the possibilities for each row, one at a time
            System.out.println(rowString);
            if (b % 3 == 0) {
                System.out.println(borderGrid);
            }// Prints the borders
        }
    }
    private String convertRCBToText(int groupNum) {
        // Returns the text name of the Row/Column/Box
        if (groupNum >= 0 && groupNum <= 26) {
            switch (groupNum) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    return "Row " + (groupNum + 1);
                case 18:
                    return "the Top Left Box";
                case 19:
                    return "the Top Middle Box";
                case 20:
                    return "the Top Right Box";
                case 21:
                    return "the Middle Left Box";
                case 22:
                    return "the Center Box";
                case 23:
                    return "the Middle Right Box";
                case 24:
                    return "the Bottom Left Box";
                case 25:
                    return "the Bottom Middle Box";
                case 26:
                    return "the Bottom Right Box";
                default:
                    return "Column " + (groupNum - 8);
            }
        }
        return "missing data";
    }
    private String addCommasAndAnd(String cellNums) {
        // ex. in> "45|46|"           out> "45 and 46"
        // ex. in> "32|35|36|38|39|"  out> "32, 35, 36, 38, and 39"
        cellNums = cellNums.substring(0, cellNums.length() - 1);
        if (cellNums.substring(1, 2).equalsIgnoreCase("|")) {
            if (cellNums.length() > 3) { // Handles 3 or more numbers text to proper english
                cellNums = cellNums.replace("|", ", ");
                cellNums = cellNums.substring(0, cellNums.length() - 1) + "and " +
                        cellNums.substring(cellNums.length() - 1, cellNums.length());
            }
            if (cellNums.length() == 3) { // Handles 2 numbers text to proper english
                cellNums = cellNums.replace("|", " and ");
            }
        } else {
            if (cellNums.length() > 5) { // Handles 3 or more cells text to proper english
                cellNums = cellNums.replace("|", ", ");
                cellNums = "s " + cellNums.substring(0, cellNums.length() - 2) + "and " +
                        cellNums.substring(cellNums.length() - 2, cellNums.length());
            }
            if (cellNums.length() == 5) { // Handles 2 cells text to proper english
                cellNums = "s " + cellNums.replace("|", " and ");
            }
            if (cellNums.length() == 2) {
                cellNums = " " + cellNums;
            }
        }
        return cellNums;
    }
    private static void infoBox(String infoMessage, String titleBar) {
        // Alert infoBox
        Alert alert = new Alert(Alert.AlertType.INFORMATION, infoMessage);
        alert.setTitle(titleBar);
        alert.setHeaderText(null);
        alert.showAndWait();
    }


    // Variable Controllers
    private String getBG(int rcbNum, int location) {
        return board[getCellInt(rcbNum, location)];
    }
    private void setBG(int rcbNum, int location, String value) {
        board[Integer.parseInt(groups[rcbNum][location])] = value;
    }
    private String getRelations(int cellNumber, int location) {
        return board[Integer.parseInt(relations[cellNumber][location])];
    }
    private void setRelations(int cellNumber, int location, String value) {
        board[Integer.parseInt(relations[cellNumber][location])] = value;
    }
    private int getCellInt(int rcbNum, int location) {
        return Integer.parseInt(groups[rcbNum][location]);
    }
    private int whichBox(int rcNum, int location) {
        // Input a Row/Column number and the Location in the Row/Column
        // Output the Box number
        if (rcNum < 9) {
            return location / 3 + 18 + (rcNum / 3) * 3;
        }
        if (rcNum > 8) {
            return 3 * (location / 3) + 18 + (rcNum / 3) % 3;
        }
        return -1;
    }
    private int whichRC(int cell1, int cell2) {
        // Input 2 cell numbers
        // Output the Row or Column they are in
        if (cell1 / 10 == cell2 / 10) {
            return cell1 / 10 - 1;
        }
        if (cell1 % 10 == cell2 % 10) {
            return cell1 % 10 - 1 + 9;
        }
        return -1;
    }
    private int whichRC(int boxNum, int location1, int location2) {
        // Input the Box and 2 Locations (0 thur 8) in the Box
        // Output the Row or Column they are in
        return whichRC(getCellInt(boxNum, location1), getCellInt(boxNum, location2));
    }


    // Solve Method Tools
    private int clearRelations(int cellNumber) {
        // If a Cell only has one possibility then mark that as the solution to that Cell
        // and remove that Number as a possibility from the related Row, Column, and Box
        if ((board[cellNumber].length() == 1) && !(relations[cellNumber][20].equalsIgnoreCase("Cleared"))) {
            for (int location = 0; location <= 19; location++) {
                setRelations(cellNumber, location,
                        getRelations(cellNumber, location).replace("" + board[cellNumber], ""));
                // Removes the solved Number as a possibility from the related Cell

                if (getRelations(cellNumber, location).length() == 0) {
                    setRelations(cellNumber, location, "error");
                    System.out.println("Error in entered puzzle.  Unsolvable puzzle.");
                    infoBox("Error in entered puzzle.  \nRemoved the last possible number from cell " +
                            relations[cellNumber][location] + ".", "Unsolvable puzzle");
                    clearedCount = 80;
                } // Catches if the entered puzzle has an error that makes it unsolvable
            } // Remove the Number as a possibility from the related Row, Column, and Box

            relations[cellNumber][20] = "Cleared";
            clearedCount += 1;

            if (clearedCount != 81) {
                System.out.println("Cell " + cellNumber + " is " + board[cellNumber] + ", so remove " +
                        board[cellNumber] + " as a possibility from all related Rows Columns and Boxes.");
            } else {
                System.out.println("Cell " + cellNumber + " is " + board[cellNumber] + ", and that completes the puzzle.");
            } // If the puzzle isn't solved then print the removal plan, if it is than print the final number
            return 1;
        }
        return 0;
    }
    private boolean[] findNumberInRCB(int number, int rcbNum) {
        // Finds all the Cells within a Row/Column/Box that contain a Number
        boolean[] cells = new boolean[9];
        for (int location = 0; location <= 8; location++) {
            if (getBG(rcbNum, location).contains("" + number)) {
                cells[location] = true;
            } else {
                cells[location] = false;
            }
        }
        return cells;
    }
    private Integer[] boxSubgroups(boolean cells[], int boxNum) {
        // Find all the instances in a Box of at least 2 Cells
        // in the same Row/Column that contain the Number
        Integer[] rcNum = new Integer[6];
        for (int subGroup = 0; subGroup <= 5; subGroup++) {
            int[] subCell = new int[3];
            switch (subGroup / 3) {
                case 0:
                    for (int y = 0; y <= 2; y++) {
                        if (cells[3 * subGroup + y]) {
                            subCell[y] = 1;
                        }
                    }
                    if (subCell[0] + subCell[1] + subCell[2] >= 2) {
                        rcNum[subGroup] = whichRC(boxNum, 3 * subGroup, 3 * subGroup + 1);
                    }
                    break;
                case 1:
                    for (int y = 0; y <= 2; y++) {
                        if (cells[subGroup % 3 + 3 * y]) {
                            subCell[y] = 1;
                        }
                    }
                    if (subCell[0] + subCell[1] + subCell[2] >= 2) {
                        rcNum[subGroup] = whichRC(boxNum, subGroup % 3, subGroup % 3 + 3);
                    }
                    break;
            }
        }
        return rcNum;
    }
    private boolean significantOverlap(Integer[] rcNum, int number, int boxNum, boolean[] boxCells) {
        // If a subgroup of Cells must contain a Number then remove the Number
        // from the possibilities of the rest of the Row/Column or Box
        boolean changes = false;
        Integer[] trials = new Integer[2];
        Boolean[] tests = new Boolean[2];
        boolean[] rcCells;
        for (int x = 0; x <= 5; x++) {
            if (rcNum[x] != null) {

                rcCells = findNumberInRCB(number, rcNum[x]);
                // Find all the Cells within the Row/Column that contain the Number

                Integer[] overlappingCells = rcbOverlap(rcNum[x], boxNum);
                // Find the Cells that overlap between the Row/Column and the Box

                for (int z = 0; z <= 1; z++) {
                    boolean onlyInstance = true;

                    for (int y = 0; y <= 8; y++) {
                        if (z == 0) {
                            trials[z] = rcNum[x];
                        } else {
                            trials[z] = boxNum;
                        }
                        if (z == 0) {
                            tests[z] = rcCells[y];
                        } else {
                            tests[z] = boxCells[y];
                        }
                        if (tests[z] && getCellInt(trials[z], y) != overlappingCells[0] &&
                                getCellInt(trials[z], y) != overlappingCells[1] &&
                                getCellInt(trials[z], y) != overlappingCells[2]) {
                            onlyInstance = false;
                            y = 8;
                        }
                    }// Check if the overlapping Cells are the only instances
                    // of that number in either the Row/Column or in the Box

                    if (onlyInstance) {
                        String cellNums = "";
                        z = Math.abs(z - 1); // Flips z(0/1) to check the Box or Row/Column instead
                        for (int y = 0; y <= 8; y++) {
                            if (z == 0) {
                                trials[z] = rcNum[x];
                            } else {
                                trials[z] = boxNum;
                            }
                            if (z == 0) {
                                tests[z] = rcCells[y];
                            } else {
                                tests[z] = boxCells[y];
                            }
                            if (tests[z] && getCellInt(trials[z], y) != overlappingCells[0] &&
                                    getCellInt(trials[z], y) != overlappingCells[1] &&
                                    getCellInt(trials[z], y) != overlappingCells[2]) {
                                if (cellNums.equalsIgnoreCase("")) {
                                    sysOutPrintBoard();
                                }
                                cellNums += groups[trials[z]][y] + "|";
                                setBG(trials[z], y, getBG(trials[z], y).replace("" + number, ""));
                                if (getBG(trials[z], y).length() == 1) {
                                    clearRelations(getCellInt(trials[z], y));
                                } // If it solves a Cell in the process then clear the relations
                            }
                        }
                        z = Math.abs(z - 1); // Flips z(1/0) back so we can try it again the other way
                        if (!cellNums.equalsIgnoreCase("")) {
                            changes = true;
                            System.out.println("In " + convertRCBToText(trials[z]) + ", the number " +
                                    number + " must be in " + convertRCBToText(trials[Math.abs(z - 1)]) +
                                    ", so remove " + number + " as a possibility from cell" +
                                    addCommasAndAnd(cellNums) + ".");
                        }
                    }// If the overlapping Cells are the only instances then remove that Number
                    // from the possibilities from the rest of either the Box or Row/Column
                }
            }
        }
        return changes;
    }
    private Integer[] rcbOverlap(int rcNum, int boxNum) {
        // Finds the Cells that overlap between a Row/Column and a Box
        Integer[] cells = new Integer[3];
        for (int p = 0; p <= 8; p++) {
            for (int q = 0; q <= 8; q++) {
                if (groups[rcNum][p].equalsIgnoreCase(groups[boxNum][q])) {
                    cells[0] = getCellInt(rcNum, p);
                    cells[1] = getCellInt(rcNum, p + 1);
                    cells[2] = getCellInt(rcNum, p + 2);
                    return cells;
                }
            }
        }
        return cells;
    }
    private int[] findSingleSubset(boolean[][] cells) {
        // Finds a subset in the Row/Column/Box if there is one
        int[] returns = new int[10];
        int cap = 1_000_000_000;

        int[] cell = new int[10];
        int[] howMany = new int[10];
        for (int number = 1; number <= 9; number++) {
            cell[number] = 1;
            howMany[number] = 0;
            for (int location = 0; location <= 8; location++) {
                cell[number] *= 10;
                if (cells[number][location]) {
                    cell[number] += 1;
                    howMany[number] += 1;
                }
            }
            cell[number] -= cap;
        } // Converts boolean cells[number][location] to int cell[number] = 010_101_011
        // to add later easier and also counts how many possibilities for each number

        String comp;
        int setSize;
        for (int x = 12; x <= 6789; x++) {
            if (x == 90) {
                x = 123;
            }
            if (x == 790) {
                x = 1234;
            }
            // Skips unnecessary combinations to search through

            setSize = 4;
            if (x < 790) {
                setSize = 3;
            }
            if (x < 90) {
                setSize = 2;
            }
            // Determines setSize

            boolean check = false;
            switch (setSize) {
                case 2:
                    returns[1] = x / 10;
                    returns[2] = x % 10;
                    if (returns[1] < returns[2]) {
                        check = true;
                    }
                    break;
                case 3:
                    returns[1] = x / 100;
                    returns[2] = (x / 10) % 10;
                    returns[3] = x % 10;
                    if (returns[1] < returns[2] && returns[2] < returns[3]) {
                        check = true;
                    }
                    break;
                case 4:
                    returns[1] = x / 1000;
                    returns[2] = (x / 100) % 10;
                    returns[3] = (x / 10) % 10;
                    returns[4] = x % 10;
                    if (returns[1] < returns[2] && returns[2] < returns[3] && returns[3] < returns[4]) {
                        check = true;
                    }
                    break;
            } // Isolates the Numbers to search through into their own variables
            // and further skips unnecessary combinations to search through

            for (int y = 1; y <= setSize; y++) {
                if (howMany[returns[y]] == 1 || howMany[returns[y]] > setSize) {
                    check = false;
                }
            } // Verifies the Cell is not solved already and that the Number
            // has the right amount of possible locations in the group

            if (check) {
                returns[0] = setSize;
                returns[9] = cap + cell[returns[1]] + cell[returns[2]] + cell[returns[3]] + cell[returns[4]];
                comp = "" + returns[9];
                comp = comp.replace("0", "");
                if (comp.length() - 1 == setSize) {
                    return returns;
                }
            } // If all the above criteria is meet then check if the Numbers form a subset
        }
        for (int x = 0; x <= 9; x++) {
            returns[x] = 0;
        } // Returns 0 for everything if a subset is not found
        return returns;
    }
    private boolean evaluateSingleSubset(int[] subset, boolean[][] cells, int rcbNum) {
        boolean changes = false;

        String sReplace = "123456789";
        sReplace = sReplace.replace("" + subset[1], "");
        sReplace = sReplace.replace("" + subset[2], "");
        sReplace = sReplace.replace("" + subset[3], "");
        sReplace = sReplace.replace("" + subset[4], "");
        sReplace = sReplace.replace("" + subset[5], "");
        // Creates a string of the Numbers not in the subset

        String cellNums = "";
        for (int x = 0; x <= 8; x++) {
            if (cells[(int) subset[1]][x] || cells[(int) subset[2]][x] || cells[(int) subset[3]][x] ||
                    cells[(int) subset[4]][x] || cells[(int) subset[5]][x]) {
                // If this is a Cell in the subset

                for (int y = 0; y < sReplace.length(); y++) {
                    if (getBG(rcbNum, x).contains(sReplace.substring(y, y + 1))) {
                        // If the Cell contains a Number not in the subset

                        if (cellNums.equalsIgnoreCase("")) {
                            sysOutPrintBoard();
                            String locations = "" + subset[9];
                            for (int z = 1; z <= 9; z++) {
                                if (!locations.substring(z, z + 1).equalsIgnoreCase("0")) {
                                    cellNums += groups[rcbNum][z - 1] + "|";
                                } // Mark the locations of the Cells in the subset
                            }
                        }
                        setBG(rcbNum, x, getBG(rcbNum, x).replace(sReplace.substring(y, y + 1), ""));
                        // Remove the Number
                        changes = true;
                    }
                }
            }
        }
        if (!cellNums.equalsIgnoreCase("")) {
            String subNums = subset[1] + "|" + subset[2] + "|";
            if (subset[3] != 0) {
                subNums += subset[3] + "|";
            }
            if (subset[4] != 0) {
                subNums += subset[4] + "|";
            }
            if (subset[5] != 0) {
                subNums += subset[5] + "|";
            }
            System.out.println("In " + convertRCBToText(rcbNum) + ", the numbers " + addCommasAndAnd(subNums) +
                    " form a subset, so clear the rest of cell" + addCommasAndAnd(cellNums) + ".");
            cellNums = "";
        }
        return changes;
    }


    // Solve Method Schemes
    private void solveBoard() {
        // Solves the puzzle, if solvable
        if (clearedCount != 81) {
            groupsDecs();
            inputBoard();
            assignDefaultValues();
            clearRelations();
            if (clearedCount != 81) {
                sysOutPrintBoard();
            }
        }


        boolean test1 = false;
        boolean test2 = false;
        do {

            onlyInGroup();
            clearRelations();
            test1 = rcbInteraction();
            test2 = singleGroupSubset();


        } while (test1 || test2);


        clearedCount = 0;
        sysOutPrintBoard();
        outputBoard();

    }
    private void clearRelations() {
        // Check if every solved Cell has had their relations cleared
        int iChanges;
        do {
            iChanges = 0;
            for (int x = 11; x <= 99; x++) {
                if (x % 10 != 0) {
                    iChanges += clearRelations(x);
                }
            } // Clear the relations for every cell
        } while (iChanges > 0);
    }
    private void onlyInGroup() {
        // If a Number can only be in one Cell in a Row/Column/Box then assign that Number to that Cell
        boolean changes;
        clearRelations();
        do {
            changes = false;
            for (int number = 1; number <= 9; number++) {
                for (int rcbNum = 0; rcbNum <= 26; rcbNum++) {
                    boolean[] cells = findNumberInRCB(number, rcbNum);
                    // Finds all the Cells that contain the Number in the Row/Column/Box

                    int[] instances = new int[2];
                    for (int location = 0; location <= 8; location++) {
                        if (cells[location]) {
                            instances[0] = location;
                            instances[1] += 1;
                        }
                    } // Checks how many Cells can contain the Number in the Row/Column/Box

                    if (instances[1] == 1 && getBG(rcbNum, instances[0]).length() > 1) {
                        setBG(rcbNum, instances[0], "" + number);
                        System.out.println("Cell " + getCellInt(rcbNum, instances[0]) + " is the only cell in " +
                                convertRCBToText(rcbNum) + " that can be " + number + ".  So cell " +
                                getCellInt(rcbNum, instances[0]) + " is " + number + ".");
                        clearRelations(getCellInt(rcbNum, instances[0]));
                        changes = true;
                    } // If the Number can only be in one Cell and the Cell is not solved already then assign it
                }
            }
        } while (changes);
    }
    private boolean rcbInteraction() {
        // If there are 2/3 Cells, in a Row/Column within the same Box, that a Number must be in because of the
        // Row/Column or the Box then remove that Number as a possibility from either the Box or Row/Column
        clearRelations();
        boolean changes = false;
        for (int number = 1; number <= 9; number++) {
            for (int boxNum = 18; boxNum <= 26; boxNum++) {

                boolean[] boxCells = findNumberInRCB(number, boxNum);
                // Find all the Cells within the Box that contain the Number

                Integer[] rcNum = boxSubgroups(boxCells, boxNum);
                // Find all the instances in the Box of at least 2 Cells
                // in the same Row/Column that contain the Number

                boolean subChanges = significantOverlap(rcNum, number, boxNum, boxCells);
                // If the subgroup of Cells must contain the Number then remove the Number
                // from the possibilities of either the rest of the Row/Column or the Box

                if (subChanges) {
                    changes = true;
                }
            }
        }
        return changes;
    }
    private boolean singleGroupSubset() {
        // If, in a Row/Column/Box, a group of possible Numbers form a subset then
        // remove the possible Numbers in those Cells that aren't in the subset
        clearRelations();
        boolean changes = false;
        boolean[][] cells = new boolean[10][];
        for (int rcbNum = 0; rcbNum <= 26; rcbNum++) {
            for (int number = 0; number <= 9; number++) {
                cells[number] = findNumberInRCB(number, rcbNum);
            } // Find all the Cells within the Row/Column/Box that contain each Number

            int[] subset = findSingleSubset(cells);
            // Finds a subset in the Row/Column/Box if there is one

            if (subset[0] != 0) {
                changes = evaluateSingleSubset(subset, cells, rcbNum);
            }
            // If the Cells in the subset contain Numbers not in the subset then remove those Numbers
        }
        return changes;
    }


    public void newSudoku(int difficulty) {
        groupsDecs();
        inputBoard();
        assignDefaultValues();
        clearedCount = 0;


        // Put nine 1s.
        // Loop
        // Put nine 2s.
        // Put nine 3s.
        // .
        // .
        // .
        // If at any point it won't work delete all the numbers you were currently on and try again
        // If


        // try 2
// do {
//            int randomNum = ThreadLocalRandom.current().nextInt(11, 100);
//            if (randomNum % 10 != 0 && board[randomNum].length() != 1) {
//                if (board[randomNum].length() > 1) {
//                    int randomSubNum = ThreadLocalRandom.current().nextInt(1, board[randomNum].length() + 1);
//                    board[randomNum] = "" + board[randomNum].substring(randomSubNum - 1, randomSubNum);
//                }
//
//
//                boolean test2 = false;
//                do  {
//                    clearRelations();
//                    onlyInGroup();
//                    test2 = brcInteraction();
//                } while (test2);
//                sysOutPrintBoard();
//
//
//
//                int test1;
//                newSudoku = true;
//                test1 = clearRelations(randomNum);
//                if (test1 > 0 && test1 % 10 == 0) {
//
//                    sysOutPrintBoard();
//
//                    for (int x = 11; x <= 99; x ++) {
//                        if (x % 10 == 0) { x++; }
//                        if (board[x].length() == 1 && Integer.parseInt(board[x]) == test1 / 10) {
//                            board[x] = "123456789";
//                        } else if (board[x].length() != 1) {
//                            board[x] = "123456789";
//                        }
//                    }
//
//                    clearedCount = 0;
//                    sysOutPrintBoard();
//                    clearRelations();
//
//                }
//
//                test2 = false;
//                do  {
//                    clearRelations();
//                    onlyInGroup();
//                    test2 = brcInteraction();
//                } while (test2);
//                sysOutPrintBoard();
//            }
//        } while (clearedCount != 81);


        //try 1
//
//        for (int x = 11; x <= 99; x++) {
//            if (x % 10 != 0) {
//
//
//
//                System.out.println("------------------------------" + randomNum + "-" + x + "----------------------------------------");
//                for (int b = 1; b <= 9; b++) {
//                    System.out.println("| " + board[b * 10 + 1] + " | " + board[b * 10 + 2] + " | " + board[b * 10 + 3] + " | " + board[b * 10 + 4] + " | " +
//                            board[b * 10 + 5] + " | " + board[b * 10 + 6] + " | " + board[b * 10 + 7] + " | " + board[b * 10 + 8] + " | " + board[b * 10 + 9] + " |");
//                }
//                System.out.println("--------------------------------------------------------------------------");
//
//
//            }
//        }


        outputBoard();
    }


    public void step3() {
        //output board into string
        //        ---------------------------------------------------------------------------------
        //  Easy  .......7.....5.8.1..641..356.7...52....2.9....41...6.997..214..1.5.3.....8.......
        //  Intr  1.7.5....3...9..12...3.....4....6.....6....9...2.1.7562.1.......6...9.......843..
        //  IntF  13.4..2.........6.7...621....3......59...1..8...6...94.....7..3.1..95....2...6...
        //  Hard  .......28..7.8.5..9...7.4..4...68......1.....5.32....4......2...25...18....5.6.9.


        //add up/down/left/right key press to move you around the board functionality
        //add functionality that moves you to the next cell after one char is typed
        //show candidate numbers in cell



        inputBoard();
        sysOutPrintBoard();
        String boardString = "";
        for (int x = 11; x <= 99; x++) {
            if (x % 10 != 0) {
                if (board[x].length() > 0) {
                    boardString += board[x];
                } else {
                    boardString += ".";
                }
            }
        }
        System.out.println(boardString);

    }

}
