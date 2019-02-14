package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class Main extends Application {
    private Runnable newPuzzleForm;
    private Runnable newPuzzleForm1;

    public static void main(String[] args) {
        launch(args);
    }


    // Declarations
    private Button[] button = new Button[5]; // Form control
    private TextField[] textFields = new TextField[100]; // Form control
    private TextArea textArea1 = new TextArea(); // Form control, used to output solution as Barcode beneath the board
    private TextArea textArea2 = new TextArea(); // Form control, used to output creation as Barcode beneath the board
    private String[] board = new String[100]; // Contains the possible Numbers for each Cell
    private String[][] groups = new String[27][]; // The numbered Rows, Columns, and Boxes of the board
    private String[][] relations = new String[100][]; // Contains all the Related Cells for each Cell
    private String[] tempBoard = new String[100]; // Used when making new puzzles
    private int clearedCount = 0; // Number of Cells that are solved and have had their Relations cleared
    private int[] combo = new int[246]; // Every 2-4 digit combination of the numbers 1-9
    private boolean guess = false; // Used to determine if the program is using guess and check
    private boolean complete; // Used to determine if the puzzle is finished
    private boolean createNew; // Used when making new puzzles
    private void groupsDecs() {
        // Rows
        groups[0] = new String[]{"11", "12", "13", "14", "15", "16", "17", "18", "19"};
        groups[1] = new String[]{"21", "22", "23", "24", "25", "26", "27", "28", "29"};
        groups[2] = new String[]{"31", "32", "33", "34", "35", "36", "37", "38", "39"};
        groups[3] = new String[]{"41", "42", "43", "44", "45", "46", "47", "48", "49"};
        groups[4] = new String[]{"51", "52", "53", "54", "55", "56", "57", "58", "59"};
        groups[5] = new String[]{"61", "62", "63", "64", "65", "66", "67", "68", "69"};
        groups[6] = new String[]{"71", "72", "73", "74", "75", "76", "77", "78", "79"};
        groups[7] = new String[]{"81", "82", "83", "84", "85", "86", "87", "88", "89"};
        groups[8] = new String[]{"91", "92", "93", "94", "95", "96", "97", "98", "99"};

        // Columns
        groups[9]  = new String[]{"11", "21", "31", "41", "51", "61", "71", "81", "91"};
        groups[10] = new String[]{"12", "22", "32", "42", "52", "62", "72", "82", "92"};
        groups[11] = new String[]{"13", "23", "33", "43", "53", "63", "73", "83", "93"};
        groups[12] = new String[]{"14", "24", "34", "44", "54", "64", "74", "84", "94"};
        groups[13] = new String[]{"15", "25", "35", "45", "55", "65", "75", "85", "95"};
        groups[14] = new String[]{"16", "26", "36", "46", "56", "66", "76", "86", "96"};
        groups[15] = new String[]{"17", "27", "37", "47", "57", "67", "77", "87", "97"};
        groups[16] = new String[]{"18", "28", "38", "48", "58", "68", "78", "88", "98"};
        groups[17] = new String[]{"19", "29", "39", "49", "59", "69", "79", "89", "99"};

        // Boxes
        groups[18] = new String[]{"11", "12", "13", "21", "22", "23", "31", "32", "33"};
        groups[19] = new String[]{"14", "15", "16", "24", "25", "26", "34", "35", "36"};
        groups[20] = new String[]{"17", "18", "19", "27", "28", "29", "37", "38", "39"};
        groups[21] = new String[]{"41", "42", "43", "51", "52", "53", "61", "62", "63"};
        groups[22] = new String[]{"44", "45", "46", "54", "55", "56", "64", "65", "66"};
        groups[23] = new String[]{"47", "48", "49", "57", "58", "59", "67", "68", "69"};
        groups[24] = new String[]{"71", "72", "73", "81", "82", "83", "91", "92", "93"};
        groups[25] = new String[]{"74", "75", "76", "84", "85", "86", "94", "95", "96"};
        groups[26] = new String[]{"77", "78", "79", "87", "88", "89", "97", "98", "99"};

        // Cells and every Cell they are in the same Groups with, plus "100" to contain whether their relations have been cleared or not
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

        // Accepts numbers 0-245 because 246 is the number of unique 2-4 digit number(1-9) combinations\
        // Used to concisely search through combinations of numbers or groups
        // Every 4 digit combination has an opposite 5 digit combination so searching through 5 is unnecessary
        combo = new int[]{12, 13, 14, 15, 16, 17, 18, 19, 23, 24, 25, 26, 27, 28, 29, 34, 35,
                36, 37, 38, 39, 45, 46, 47, 48, 49, 56, 57, 58, 59, 67, 68, 69, 78, 79, 89,
                123, 124, 125, 126, 127, 128, 129, 134, 135, 136, 137, 138, 139, 145, 146, 147, 148, 149, 156, 157, 158,
                159, 167, 168, 169, 178, 179, 189, 234, 235, 236, 237, 238, 239, 245, 246, 247, 248, 249, 256, 257, 258,
                259, 267, 268, 269, 278, 279, 289, 345, 346, 347, 348, 349, 356, 357, 358, 359, 367, 368, 369, 378, 379,
                389, 456, 457, 458, 459, 467, 468, 469, 478, 479, 489, 567, 568, 569, 578, 579, 589, 678, 679, 689, 789,
                1234, 1235, 1236, 1237, 1238, 1239, 1245, 1246, 1247, 1248, 1249, 1256, 1257, 1258, 1259, 1267,
                1268, 1269, 1278, 1279, 1289, 1345, 1346, 1347, 1348, 1349, 1356, 1357, 1358, 1359, 1367, 1368,
                1369, 1378, 1379, 1389, 1456, 1457, 1458, 1459, 1467, 1468, 1469, 1478, 1479, 1489, 1567, 1568,
                1569, 1578, 1579, 1589, 1678, 1679, 1689, 1789, 2345, 2346, 2347, 2348, 2349, 2356, 2357, 2358,
                2359, 2367, 2368, 2369, 2378, 2379, 2389, 2456, 2457, 2458, 2459, 2467, 2468, 2469, 2478, 2479,
                2489, 2567, 2568, 2569, 2578, 2579, 2589, 2678, 2679, 2689, 2789, 3456, 3457, 3458, 3459, 3467,
                3468, 3469, 3478, 3479, 3489, 3567, 3568, 3569, 3578, 3579, 3589, 3678, 3679, 3689, 3789, 4567,
                4568, 4569, 4578, 4579, 4589, 4678, 4679, 4689, 4789, 5678, 5679, 5689, 5789, 6789};

        for (int x = 11; x <= 99; x++) {
            if (x % 10 == 0) { x++; }
            tempBoard[x] = "";
        }
    }


    // Forms
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sudoku Guru");
        GridPane root = new GridPane();
        Scene myScene = new Scene(root, 212, 423);//357
        root.setHgap(1);
        root.setVgap(1);
        root.setAlignment(Pos.TOP_LEFT);
        // Basic setup for the Form

        for (int x = 11; x <= 99; x++) {
            if (x % 10 != 0) {
                textFields[x] = new TextField();
                int addRow = ((x - 10) / 30) * 2;
                int addCol = (((x - 1) % 10) / 3) * 2;
                root.add(textFields[x], (x % 10) + addCol, (x / 10) + addRow);
                textFields[x].setMinWidth(21.5);
                textFields[x].setMaxWidth(21.5);
                textFields[x].setMinHeight(25);
                textFields[x].setMaxHeight(25);
                textFields[x].setText(board[x]);

            }
        } // Initialize and place textFields




//        textFields[12].setStyle("-fx-font-size: 6;");
//        textFields[13].setStyle("-fx-font-size: 6;");
//        textFields[14].setStyle("-fx-font-size: 6;");
//        textFields[15].setStyle("-fx-font-size: 6;");
//        textFields[16].setStyle("-fx-font-size: 6;");



// https://www.google.com/search?ei=hgQ5XO2aLIi0tQXPg5moAQ&q=javafx+transforms+rotate+explain+pivot&oq=javafx+transforms+rotate+explain+pivot&gs_l=psy-ab.3..33i160l3.408567.411456..411694...0.0..0.634.1861.0j9j5-1......0....1..gws-wiz.......33i22i29i30j33i299.qR5FnZ6mFgE
// https://gist.github.com/jewelsea/1475424

        Label solutionLabel = new Label();
        solutionLabel.setText("Solution:");
        root.add(solutionLabel, 1, 16, 3, 1);
        textArea1 = new TextArea();
        root.add(textArea1, 1, 64, 15, 1);
        Rotate rotate = new Rotate(-90, textArea1.getBoundsInLocal().getWidth() / 2, textArea1.getBoundsInLocal().getHeight() / 2);
        textArea1.getTransforms().addAll(rotate);
        textArea1.setMinWidth(50);
        textArea1.setMaxWidth(50);
        textArea1.setMinHeight(210);
        textArea1.setMaxHeight(210);
        textArea1.setStyle("-fx-font-size: .75;");
        textArea1.setEditable(false);
        // Initialize and place Solution/BarCode textArea

        Label creationLabel = new Label();
        creationLabel.setText("Creation:");
        root.add(creationLabel, 1, 65, 3, 1);
        creationLabel.setTranslateY(-210);
        textArea2 = new TextArea();
        root.add(textArea2, 1, 66, 15, 1);
        textArea2.setTranslateY(-163);
        Rotate rotate2 = new Rotate(-90, textArea2.getBoundsInLocal().getWidth() / 2, textArea2.getBoundsInLocal().getHeight() / 2);
        textArea2.getTransforms().addAll(rotate2);
        textArea2.setMinWidth(50);
        textArea2.setMaxWidth(50);
        textArea2.setMinHeight(210);
        textArea2.setMaxHeight(210);
        textArea2.setStyle("-fx-font-size: .75;");
        textArea2.setEditable(false);
        // Initialize and place Creation/BarCode textArea

        button[0] = new Button("Input");
        button[1] = new Button("Clear");
        button[2] = new Button("Solve");
        button[3] = new Button("New");
        // Initialize buttons

        for (int x = 0; x < 4; x++) {
            button[x].setMinWidth(68);
            button[x].setOnAction(this::onButtonClicked);
            root.add(button[x], 1 + ((x % 3) * 5), 14 + (x / 3), 3, 1);
        } // Place buttons

        myScene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            switch (e.getCode()) {
                case UP:
                    for (int x = 11; x <= 99; x++) {
                        if (x % 10 != 0) {
                            if (textFields[x].focusedProperty().get()) {
                                if (x / 10 == 1) {
                                    if (x % 10 == 1) {
                                        textFields[99].requestFocus();
                                    } else {
                                        textFields[x + 79].requestFocus();
                                    }
                                } else {
                                    textFields[x - 10].requestFocus();
                                }
                                x = 99;
                            }
                        }
                    }
                    break;
                case DOWN:
                    for (int x = 11; x <= 99; x++) {
                        if (x % 10 != 0) {
                            if (textFields[x].focusedProperty().get()) {
                                if (x / 10 == 9) {
                                    if (x % 10 == 9) {
                                        textFields[11].requestFocus();
                                    } else {
                                        textFields[x - 79].requestFocus();
                                    }
                                } else {
                                    textFields[x + 10].requestFocus();
                                }
                                x = 99;
                            }
                        }
                    }
                    break;
                case LEFT:
                    for (int x = 11; x <= 99; x++) {
                        if (x % 10 != 0) {
                            if (textFields[x].focusedProperty().get()) {
                                if (x % 10 == 1) {
                                    if (x / 10 == 1) {
                                        textFields[99].requestFocus();
                                    } else {
                                        textFields[x - 2].requestFocus();
                                    }
                                } else {
                                    textFields[x - 1].requestFocus();
                                }
                                x = 99;
                            }
                        }
                    }
                    break;
                case RIGHT:
                    for (int x = 11; x <= 99; x++) {
                        if (x % 10 != 0) {
                            if (textFields[x].focusedProperty().get()) {
                                if (x % 10 == 9) {
                                    if (x / 10 == 9) {
                                        textFields[11].requestFocus();
                                    } else {
                                        textFields[x + 2].requestFocus();
                                    }
                                } else {
                                    textFields[x + 1].requestFocus();
                                }
                                x = 99;
                            }
                        }
                    }
                    break;
            }
        });
        // Handles arrowKey navigation

        primaryStage.setScene(myScene);
        primaryStage.show();
        // Display Form
    }
    public void newPuzzleForm() {
        Stage newStage = new Stage();
        newStage.setTitle("New Puzzle");
        GridPane root = new GridPane();
        Scene myScene = new Scene(root, 188, 71);
        root.setHgap(1);
        root.setVgap(1);
        root.setAlignment(Pos.TOP_LEFT);
        // Basic setup for the Form

        Label difficultyLabel = new Label();
        difficultyLabel.setText("Difficulty:");
        GridPane.setHalignment(difficultyLabel, HPos.RIGHT);
        root.add(difficultyLabel, 1, 1);
        Label symmetryLabel = new Label();
        symmetryLabel.setText("Symmetry:");
        GridPane.setHalignment(symmetryLabel, HPos.RIGHT);
        root.add(symmetryLabel, 1, 2);
        // Initialize and place labels

        ObservableList<String> options =
                FXCollections.observableArrayList(
                    "Easy",
                    "Intermediate",
                    "Hard",
                    "Expert",
                    "Trial and Error");
        ComboBox diffComboBox = new ComboBox(options);


        diffComboBox.setMinWidth(129);
        root.add(diffComboBox, 2, 1, 2, 1);
        // Initialize and place combo box

        ToggleGroup group = new ToggleGroup();
        RadioButton symYes = new RadioButton();
        symYes.setText("Yes");
        GridPane.setHalignment(symYes, HPos.CENTER);
        root.add(symYes, 2, 2);
        symYes.setToggleGroup(group);
        RadioButton symNo = new RadioButton();
        symNo.setText("No");
        GridPane.setHalignment(symNo, HPos.CENTER);
        root.add(symNo, 3, 2);
        symNo.setToggleGroup(group);
        // Initialize and place radio buttons

        newStage.setScene(myScene);
        newStage.show();
        // Display Form

        button[4] = new Button("Create");
        button[4].setMinWidth(68);
        button[4].setOnAction(e -> {
            newStage.close();
            newSudoku(diffComboBox.getValue().toString(), symYes.isSelected());
        });
        root.add(button[4], 2 , 3);
        // Initialize and place button
    }


    // Buttons
    private void onButtonClicked(ActionEvent buttonClicked) {
        if (buttonClicked.getSource() == button[0]) { inputBoard(); }
        if (buttonClicked.getSource() == button[1]) { testy(); }
        if (buttonClicked.getSource() == button[2]) { solveBoard(); }
        if (buttonClicked.getSource() == button[3]) { newPuzzleForm(); }
    }


    // Board Handling
    private void createBoardString() {
        // Outputs board into string
        //        ---------------------------------------------------------------------------------
        //  Easy  .......7.....5.8.1..641..356.7...52....2.9....41...6.997..214..1.5.3.....8.......
        //  Intr  1.7.5....3...9..12...3.....4....6.....6....9...2.1.7562.1.......6...9.......843..
        //  IntF  13.4..2.........6.7...621....3......59...1..8...6...94.....7..3.1..95....2...6...
        //  Hard  .......28..7.8.5..9...7.4..4...68......1.....5.32....4......2...25...18....5.6.9.
        //  SwdF  .5736.2846.4825...28.7.465..924.6...3619.7.42.45132.964.62...75.2.57.46.57864.32.
        //  Expr  54....18...19..254..2.....3..825.....5..7..2.....987..6.....8..417..65...85....46
        //  Exp2  ..6.....4...86.73..4.35...217.4..6...9.....8...8..6.172...81.4..67.43...8.....3..

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
        System.out.println("Copy and paste Game Code into the top left cell and hit Input to reload the board.\n" +
                    "Game Code:\n" + "\n" + boardString + "\n" + "\nSolution:");
        textArea1.setText("Copy and paste Game Code into the top left cell and hit Input to reload the board.\n" +
                    "Game Code:\n" + "\n" + boardString + "\n" + "\nSolution:");

    }
    private void inputBoard() {
        // If importing a game string then break up the string and assign it to the Board array
        // If not then just assign to the Board array
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
                if (board[x] == null) { board[x] = ""; }
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
        // Clears Board
        clearedCount = 0;
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
            if (x % 10 == 0) { x ++; }
            if (board[x].length() != 1) { board[x] = "123456789"; }
        }
    }
    private void sysOutPrintBoard() {
        // Prints out Board in text/picture format
        int longest = 1;
        for (int i = 11; i <= 99; i++) {
            if (i % 10 == 0) { i++; }
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
        if (longest % 2 != 0) { colNums = "    "; }
        for (int x = 1; x <= 9; x++) {
            for (int y = 1; y <= longest - 1; y++) {
                if (x == 1) {
                    if (y != longest - 1) { colNums += " "; }
                    y++;
                } else { colNums += " "; }
            }
            colNums += "x" + x + "  ";
            if (x % 3 == 0) { colNums += " "; }
        } // Builds the Column number labels at the top of the printed board

        System.out.println(colNums); textArea1.appendText("\n" + colNums);
        System.out.println(borderGrid); textArea1.appendText("\n" + borderGrid);

        for (int b = 1; b <= 9; b++) {
            String rowString = b + "x|| ";
            for (int c = 1; c <= 9; c++) {
                switch (longest - board[b * 10 + c].length()) {
                    case 0: rowString += board[b * 10 + c]; break;
                    case 1: rowString += board[b * 10 + c] + " "; break;
                    case 2: rowString += " " + board[b * 10 + c] + " "; break;
                    case 3: rowString += " " + board[b * 10 + c] + "  "; break;
                    case 4: rowString += "  " + board[b * 10 + c] + "  "; break;
                    case 5: rowString += "  " + board[b * 10 + c] + "   "; break;
                    case 6: rowString += "   " + board[b * 10 + c] + "   "; break;
                    case 7: rowString += "   " + board[b * 10 + c] + "    "; break;
                    case 8: rowString += "    " + board[b * 10 + c] + "    "; break;
                }
                if (c % 3 == 0 && c != 9) { rowString += " || "; }
                if (c == 9) { rowString += " ||"; }
                if (c % 3 != 0) { rowString += " | "; }
            }// Prints the possibilities for each row, one at a time
            System.out.println(rowString); textArea1.appendText("\n" + rowString);
            if (b % 3 == 0) {
                System.out.println(borderGrid); textArea1.appendText("\n" + borderGrid);
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
                case 8:  return "Row " + (groupNum + 1);
                case 18: return "the Top Left Box";
                case 19: return "the Top Middle Box";
                case 20: return "the Top Right Box";
                case 21: return "the Middle Left Box";
                case 22: return "the Center Box";
                case 23: return "the Middle Right Box";
                case 24: return "the Bottom Left Box";
                case 25: return "the Bottom Middle Box";
                case 26: return "the Bottom Right Box";
                default: return "Column " + (groupNum - 8);
            }
        }
        return "missing data";
    }
    private String addCommasAndAnd(String cellNums) {
        // ex. in> "45|46|"           out> "45 and 46"
        // ex. in> "32|35|36|38|39|"  out> "32, 35, 36, 38, and 39"
        // ex. in> "1|2|7"            out> "1, 2, and 7"
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
        // Simplifies having to type:
        // board[Integer.parseInt(groups[rcbNum][location])]
        return board[getCellInt(rcbNum, location)];
    }
    private void setBG(int rcbNum, int location, String value) {
        // Simplifies having to type:
        board[Integer.parseInt(groups[rcbNum][location])] = value;
    }
    private String getRelations(int cellNumber, int location) {
        // Simplifies having to type:
        return board[Integer.parseInt(relations[cellNumber][location])];
    }
    private void setRelations(int cellNumber, int location, String value) {
        // Simplifies having to type:
        board[Integer.parseInt(relations[cellNumber][location])] = value;
    }
    private int getCellInt(int rcbNum, int location) {
        // Simplifies having to type:
        return Integer.parseInt(groups[rcbNum][location]);
    }
    private int whichBox(int rcNum, int location) {
        // Input a Row/Column number and the Location in the Row/Column
        // Output the Box number
        // Math:
        if (rcNum < 9) { return location / 3 + 18 + (rcNum / 3) * 3; }
        if (rcNum >= 9) { return 3 * (location / 3) + 18 + (rcNum / 3) % 3; }
        return -1;
    }
    private int whichRC(int cell1, int cell2) {
        // Input 2 cell numbers
        // Output the Row or Column they are in, else -1
        // Math:
        if (cell1 / 10 == cell2 / 10) { return cell1 / 10 - 1; }
        if (cell1 % 10 == cell2 % 10) { return cell1 % 10 - 1 + 9; }
        return -1;
    }
    private int whichRC(int boxNum, int location1, int location2) {
        // Input the Box and 2 Locations (0 thur 8) in the Box
        // Output the Row or Column they are in
        // Simplifies having to type nested if then statements
        return whichRC(getCellInt(boxNum, location1), getCellInt(boxNum, location2));
    }


    // Solve Method Tools
    private int clearRelations(int cellNumber) {
        // If a Cell only has one possibility then mark that as the solution to that Cell
        // and remove that Number as a possibility from the related Row, Column, and Box
        if ((clearedCount < 81 && board[cellNumber].length() == 1) &&
                !(relations[cellNumber][20].equalsIgnoreCase("Cleared"))) {
            for (int location = 0; location <= 19; location++) {
                setRelations(cellNumber, location,
                        getRelations(cellNumber, location).replace("" + board[cellNumber], ""));
                // Removes the solved Number as a possibility from the related Cell

                if (getRelations(cellNumber, location).length() == 0) {
                    if (!guess && !createNew) {
                        setRelations(cellNumber, location, "error");
                        infoBox("Error in entered puzzle.  \nRemoved the last possible Number from Cell " +
                                relations[cellNumber][location] + ".", "Unsolvable puzzle");
                    }
                    System.out.println("Error in entered puzzle.  Unsolvable puzzle.");
                    textArea1.appendText("\nError in entered puzzle.  Unsolvable puzzle.");
                    clearedCount = 81;
                    location = 19;
                } // Catches if the entered puzzle has an error that makes it unsolvable
            } // Remove the Number as a possibility from the related Row, Column, and Box

            relations[cellNumber][20] = "Cleared";
            clearedCount += 1;

            if (clearedCount == 81) {
                System.out.println("Cell " + cellNumber + " is " + board[cellNumber] +
                        ", and that completes the puzzle.");
                textArea1.appendText("\nCell " + cellNumber + " is " + board[cellNumber] +
                        ", and that completes the puzzle.");
            } else if(clearedCount != 82){
                System.out.println("Cell " + cellNumber + " is " + board[cellNumber] + ", so remove " +
                        board[cellNumber] + " as a possibility from all related Rows, Columns, and Boxes.");
                textArea1.appendText("\nCell " + cellNumber + " is " + board[cellNumber] + ", so remove " +
                        board[cellNumber] + " as a possibility from all related Rows, Columns, and Boxes.");
            }// If the puzzle isn't solved then print the removal plan, if it is than print the final number
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
    private int stringNumberInRCB(int number, int groupNumber){
        // Converts the locations of a number in a group to an int: 1_010,001,100
        int returns;
        boolean[] test = findNumberInRCB(number, groupNumber);
        returns = 1;
        for (int location = 0; location <= 8; location++) {
            returns *= 10;
            if (test[location]) { returns += 1; }
        }
        return returns;
    }
    private int howManyOfANumberAreSolved(int number) {
        // Returns how many of a Number are solved in the whole Board
        int solved = 0;
        for (int x = 11; x <= 99; x++) {
            if (x % 10 == 0) { x++; }
            if (board[x].equalsIgnoreCase("" + number)) {
                solved++;
            }
        }
        return solved;
    }
    private int[] searchCombinations(int comboNumber) {
        // Accepts numbers 0-245 because 246 is the number of unique 2-4 digit number(1-9) combinations
        // Returns array[combination of items, 1st item, 2nd item, 3rd item, 4th, item, number of items]
        int[] returns = new int[6];
        returns[0] = combo[comboNumber];
        returns[5] = 4;
        if (returns[0] < 790) { returns[5] = 3; }
        if (returns[0] < 90) { returns[5] = 2; }
        // Determines returns[5] (combination size)

        switch (returns[5]) {
            case 2:
                returns[1] = returns[0] / 10;
                returns[2] = returns[0] % 10;
                break;
            case 3:
                returns[1] = returns[0] / 100;
                returns[2] = (returns[0] / 10) % 10;
                returns[3] = returns[0] % 10;
                break;
            case 4:
                returns[1] = returns[0] / 1000;
                returns[2] = (returns[0] / 100) % 10;
                returns[3] = (returns[0] / 10) % 10;
                returns[4] = returns[0] % 10;
                break;
        } // Isolates the Numbers to search through into their own variables
    return returns;
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
    private Integer[] findSubgroupOverlap(boolean cells[], int boxNum) {
        // Find all the instances in a Box of at least 2 Cells
        // in the same Row/Column that contain the Number
        Integer[] rcNum = new Integer[6];
        for (int subGroup = 0; subGroup <= 5; subGroup++) {
            int[] subCell = new int[3];
            switch (subGroup / 3) {
                case 0: // Checks the 3 Rows in the Box
                    for (int y = 0; y <= 2; y++) {
                        if (cells[3 * subGroup + y]) { subCell[y] = 1; }
                    }
                    if (subCell[0] + subCell[1] + subCell[2] >= 2) {
                        rcNum[subGroup] = whichRC(boxNum, 3 * subGroup, 3 * subGroup + 1);
                    }
                    break;
                case 1: // Checks the 3 Columns in the Box
                    for (int y = 0; y <= 2; y++) {
                        if (cells[subGroup % 3 + 3 * y]) { subCell[y] = 1; }
                    }
                    if (subCell[0] + subCell[1] + subCell[2] >= 2) {
                        rcNum[subGroup] = whichRC(boxNum, subGroup % 3, subGroup % 3 + 3);
                    }
                    break;
            }
        }
        return rcNum;
    }
    private boolean evaluateSubgroupOverlap(Integer[] rcNum, int number, int boxNum, boolean[] boxCells) {
        // If a subgroup of Cells must contain a Number then remove the Number
        // from the possibilities of the rest of the Row/Column or Box
        boolean changes = false;
        Integer[] trials = new Integer[2]; // The Row/Column number or the Box number to search through
        Boolean[] tests  = new Boolean[2]; // The boolean Number locations in the Row/Column or the Box
        boolean[] rcCells;
        for (int x = 0; x <= 5; x++) {
            if (rcNum[x] != null) {

                rcCells = findNumberInRCB(number, rcNum[x]);
                // Find all the Cells within the Row/Column that contain the Number

                Integer[] overlappingCells = rcbOverlap(rcNum[x], boxNum);
                // Find the Cells that overlap between the Row/Column and the Box

                for (int z = 0; z <= 1; z++) { // Checks the Row/Column the first time through then the Box the next
                    boolean onlyInstance = true;

                    for (int y = 0; y <= 8; y++) {
                        if (z == 0) { trials[z] = rcNum[x]; } else { trials[z] = boxNum; }
                        if (z == 0) { tests[z] = rcCells[y]; } else { tests[z] = boxCells[y]; }
                        // Sets the appropriate Group to check (z=0:Row/Column, z=1:Box)

                        if (tests[z] && getCellInt(trials[z], y) != overlappingCells[0] &&
                                getCellInt(trials[z], y) != overlappingCells[1] &&
                                getCellInt(trials[z], y) != overlappingCells[2]) {
                            onlyInstance = false;
                            y = 8;
                        }
                    }// Checks if the overlapping Cells are the only instance
                     // of that number in either the Row/Column or in the Box

                    if (onlyInstance) {
                        z = Math.abs(z - 1);
                        // Upon finding that the Row/Column(z=0) or Box(z=1) is the only instance of possible cells,
                        // the z(0/1) is flipped z(1/0) to check the Box(z=1) or Row/Column(z=0) for a number to remove

                        String cellNums = "";
                        for (int y = 0; y <= 8; y++) {
                            if (z == 0) { trials[z] = rcNum[x]; } else { trials[z] = boxNum; }
                            if (z == 0) { tests[z] = rcCells[y]; } else { tests[z] = boxCells[y]; }
                            // Sets the appropriate Group to check (z=0:Row/Column, z=1:Box)

                            if (tests[z] && getCellInt(trials[z], y) != overlappingCells[0] &&
                                    getCellInt(trials[z], y) != overlappingCells[1] &&
                                    getCellInt(trials[z], y) != overlappingCells[2]) {
                                if (cellNums.equalsIgnoreCase("")) { sysOutPrintBoard(); }
                                cellNums += groups[trials[z]][y] + "|";
                                setBG(trials[z], y, getBG(trials[z], y).replace("" + number, ""));
                                // Remove the Number if it is a possibility
                            }
                        }
                        z = Math.abs(z - 1);
                        // Flips z(1/0) back so we can reference the initial group correctly,
                        // or search again if necessary

                        if (!cellNums.equalsIgnoreCase("")) {
                            changes = true;
                            System.out.println("In " + convertRCBToText(trials[z]) + ", the Number " +
                                    number + " must be in " + convertRCBToText(trials[Math.abs(z - 1)]) +
                                    ", so remove " + number + " as a possibility from Cell" +
                                    addCommasAndAnd(cellNums) + ".");
                            textArea1.appendText("\nIn " + convertRCBToText(trials[z]) + ", the Number " +
                                    number + " must be in " + convertRCBToText(trials[Math.abs(z - 1)]) +
                                    ", so remove " + number + " as a possibility from Cell" +
                                    addCommasAndAnd(cellNums) + ".");

                            // Prints the removed Numbers

                            clearRelations();
                            onlyInGroup();
                            // Checks if the removed Numbers allowed the puzzle to be solved by an easier method
                        }
                    }// If the overlapping Cells are the only instances then remove that Number
                    // from the possibilities from the rest of either the Box or Row/Column
                }
            }
        }
        return changes;
    }
    private int[] findSubset(boolean[][] cells) {
        // Finds a subset in the Row/Column/Box if there is one
        int [] returns = new int[6];
        int cap = 1_000_000_000;

        int[] cell = new int[10];
        int[] howMany = new int[10];
        for (int number = 1; number <= 9; number++) {
            cell[number] = 1;
            howMany[number] = 0;
            for (int location = 0; location <= 8; location++) {
                cell[number] *= 10;
                if (cells[number][location]) { cell[number] += 1; howMany[number] += 1; }
            }
            cell[number] -= cap;
        } // Converts boolean cells[number][location] to int cell[number] = 010,101,011
          // to add later easier and also counts how many possibilities for each number


        for (int x = 0; x <= 245; x++) {

            returns = searchCombinations(x);
            int setSize = returns[5];
            // Isolates the Numbers to search through into their own variables

            boolean check = true;
            for (int y = 1; y <= setSize; y++) {
                if (howMany[returns[y]] == 1 || howMany[returns[y]] > setSize) { check = false; }
            } // Verifies the Cell is not solved already and that the Number
              // has the right amount of possible locations in the group

            if (check) { // If all the above criteria is met then check if the Numbers form a subset
                returns[0] = setSize;
                returns[5] = cap + cell[returns[1]] + cell[returns[2]] + cell[returns[3]] + cell[returns[4]];
                String comp = "" + returns[5];
                comp = comp.replace("0", "");
                // Determines how many different locations exist within the Row/Column for the Numbers provided
                if (comp.length() - 1 == setSize) { return returns; }
            }
        }
        for (int x = 0; x <= 5; x++) {
            returns[x] = 0;
        } // Returns 0 for everything if a subset is not found
        return returns;
    }
    private boolean evaluateSubset(int[] subset, boolean[][] cells, int rcbNum) {
        // If the Cells in the subset contain Numbers not in the subset then remove those Numbers
        boolean changes = false;

        String sReplace = "123456789";
        sReplace = sReplace.replace("" + subset[1], "");
        sReplace = sReplace.replace("" + subset[2], "");
        sReplace = sReplace.replace("" + subset[3], "");
        sReplace = sReplace.replace("" + subset[4], "");
        // Creates a string of the Numbers not in the subset

        String cellNums = "";
        for (int x = 0; x <= 8; x++) {
            if (cells[subset[1]][x] || cells[subset[2]][x] ||
                    cells[subset[3]][x] || cells[subset[4]][x]) {
                // If x is a location of a Cell in the subset

                for (int y = 0; y < sReplace.length(); y++) {
                    if (getBG(rcbNum, x).contains(sReplace.substring(y, y + 1))) {
                        // If the Cell contains a Number not in the subset

                        if (cellNums.equalsIgnoreCase("")) {
                            sysOutPrintBoard();
                            String locations = "" + subset[5];
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
        } // Clears the Cells in the subset of Numbers not in the subset

        if (!cellNums.equalsIgnoreCase("")) {
            String subNums = subset[1] + "|" + subset[2] + "|";
            if (subset[3] != 0) { subNums += subset[3] + "|"; }
            if (subset[4] != 0) { subNums += subset[4] + "|"; }
            System.out.println("In " + convertRCBToText(rcbNum) + ", the Numbers " + addCommasAndAnd(subNums) +
                    " form a Subset, so clear the rest of Cell" + addCommasAndAnd(cellNums) + ".");
            textArea1.appendText("\nIn " + convertRCBToText(rcbNum) + ", the numbers " + addCommasAndAnd(subNums) +
                    " form a Subset, so clear the rest of Cell" + addCommasAndAnd(cellNums) + ".");
            // Prints the removed Numbers

            clearRelations();
            onlyInGroup();
            rcbInteraction();
            // Checks if the removed Numbers allowed the puzzle to be solved by an easier method
        }
        return changes;
    }
    private boolean findAndEvaluateChain(int[] sCombo, int rowsOrColumns, int number) {
        // Compares the Rows or Columns and if there is a chain then clear the chain
        boolean results = false;

        int chainSize = sCombo[5];
        boolean[][] groupsChecks = new boolean[19][];
        int cap = 1_000_000_000;
        String compare;
        boolean check = true;
        int[] rcNumLocations = new int[chainSize + 1];

        for (int y = 1; y <= chainSize; y++) {

            rcNumLocations[y] = (sCombo[y] - 1) + (9 * rowsOrColumns);
            // Converts the 1-9(combo number) to the Row or Column number to check

            groupsChecks[rcNumLocations[y]] = findNumberInRCB(number, rcNumLocations[y]);
            // Gets the boolean locations of the number in the Row or Column

            rcNumLocations[0] = stringNumberInRCB(number,rcNumLocations[y]);
            // Converts the boolean locations to int 1_010,001,100 format for easier comparison

            rcNumLocations[y] = rcNumLocations[0] - cap;
            compare = "" + rcNumLocations[y];
            compare = compare.replace("0", "");
            if (compare.length() == 1) { check = false; }
            // Checks if the Number is already solved in any of the Rows or Columns
            // If so then skip this combination

        }// Designates the different ways to handle the data in the method

        rcNumLocations[0] = 0;
        for (int y = 1; y <= chainSize; y++) {
            rcNumLocations[0] += rcNumLocations[y];
        }
        compare = "" + rcNumLocations[0];
        compare = compare.replace("0", "");
        // Determines how many different Columns/Rows the Number is in of the Row/Columns being checked

        if (compare.length() == chainSize && check && chainSize != 9 - howManyOfANumberAreSolved(number)) {
            // If the Number of possible locations matches the chain size and
            // if the Number is not solved in any of the Rows/Columns and
            // if the chain is not all of the Cells left that contain the Number

            rcNumLocations[0] += cap;
            compare = "" + rcNumLocations[0];
            int[] switchRC = new int[chainSize + 1];
            switchRC[0] = -1;
            for (int y = 1; y <= chainSize; y++) {
                for (int z = switchRC[y - 1] % 9 + 2; z <= compare.length(); z++) {
                    if (!compare.substring(z, z + 1).equalsIgnoreCase("0")) {
                        switchRC[y] = rowsOrColumns == 0 ? z + 8 : z - 1;
                        groupsChecks[switchRC[y]] = findNumberInRCB(number, switchRC[y]);
                        z = compare.length();
                    }
                }
            } // Figures out the Columns or Rows that the Rows or Columns form the chain in

            String resultCells = "";
            for (int y = 1; y <= chainSize; y++) {
                for (int location = 0; location <= 8; location++) {
                    if (groupsChecks[switchRC[y]][location]) {
                        // Finds all the locations of the Number in the Columns or Rows

                        check = true;
                        for (int z = 1; z <= chainSize; z++) {
                            if (location + 1 == sCombo[z]) {
                                check = false;
                                z = chainSize;
                            }
                        } // Determines if the location is in the Rows or Columns in the chain

                        if (check) {
                            if (resultCells.equalsIgnoreCase("")) { sysOutPrintBoard(); }
                            resultCells += getCellInt(switchRC[y], location) + "|";
                            setBG(switchRC[y], location, getBG(switchRC[y], location).replace("" + number, "" ));
                        } // If the location is not in the chain then it removes it from that Cell
                    }
                }
            } // Removes the Number as a possibility from the Columns or Rows not in the chain

            if (!resultCells.equalsIgnoreCase("")) {
                String rcTag = "";
                String switchTag = "";
                for (int y = 1; y <= chainSize; y++) {
                    rcTag += sCombo[y] + "|";
                    switchTag += switchRC[y] + (rowsOrColumns == 1 ? +1 : - 8) + "|";
                }
                rcTag = addCommasAndAnd(rcTag);
                switchTag = addCommasAndAnd(switchTag);
                System.out.println("In " + (rowsOrColumns == 0 ? "Rows " : "Columns ") + rcTag +
                        ", the Number " + number + " forms a Chain in " +
                        (rowsOrColumns == 1 ? "Rows " : "Columns ") + switchTag + ", so remove " +
                        number + " as a possibility from Cell" + addCommasAndAnd(resultCells) + ".");
                textArea1.appendText("\nIn " + (rowsOrColumns == 0 ? "Rows " : "Columns ") + rcTag +
                        ", the Number " + number + " forms a Chain in " +
                        (rowsOrColumns == 1 ? "Rows " : "Columns ") + switchTag + ", so remove " +
                        number + " as a possibility from Cell" + addCommasAndAnd(resultCells) + ".");
                // Prints the removed Numbers

                results = true;

                clearRelations();
                onlyInGroup();
                rcbInteraction();
                singleGroupMultipleNumbersSubset();
                // Checks if the removed Numbers allowed the puzzle to be solved by an easier method
            }
        }
        return results;
    }


    // Solve Method Schemes
    private void solveBoard() {
        long startTime = System.nanoTime();
        // Solves the puzzle, if solvable
        if (clearedCount == 0) {
            guess = false;
            complete = false;
            groupsDecs();
            inputBoard();
            createBoardString();
            assignDefaultValues();
            clearRelations();
            sysOutPrintBoard();
        } // First time board setup


        boolean[] test = new boolean[6];
        do {

            test[0] = false;
            test[1] = false;
            test[2] = false;
            test[3] = false;
            test[4] = false;
            test[5] = false;
            if (clearedCount < 81) { test[0] = clearRelations(); }
            if (clearedCount < 81) { test[1] = onlyInGroup(); }
            if (clearedCount < 81) { test[2] = rcbInteraction(); }
            if (clearedCount < 81) { test[3] = singleGroupMultipleNumbersSubset(); }
            if (clearedCount < 81) { test[4] = multipleGroupsSingleNumberChain(); }
            if (clearedCount < 81) { test[5] = multipleGroupsMultipleNumbersBoard(); }

        } while (test[0] || test[1] || test[2] || test[3] || test[4] || test[5]);
        // Logic based solve methods


        if (!guess && clearedCount != 81) { guess(); }
        // Brute force solve method


        if (clearedCount == 81 && !complete && !createNew) {
            sysOutPrintBoard();
            outputBoard();
            complete = true;
            clearedCount = 0;
            long totalTime = System.nanoTime() - startTime;
            double time = (double) (totalTime / 1_000_000);
            time /= 1_000;
            System.out.println("Puzzle solved in " + time + " seconds.");
            textArea1.appendText("\nPuzzle solved in " + time + " seconds.");
        } // When finished print everything out

        if (!guess && clearedCount == 82) { clearedCount = 0; }
        // If error in entered puzzle then reset to let them try again
    }
    private boolean clearRelations() {
        // Check if every solved Cell has had their relations cleared
        boolean returns = false;
        int iChanges;
        do {
            iChanges = 0;
            for (int x = 11; x <= 99; x++) {
                if (x % 10 == 0) { x++; }
                iChanges += clearRelations(x);
            } // Clear the relations for every cell
            if (iChanges > 0) { returns = true; }
        } while (iChanges > 0);
        return returns;
    }
    private boolean onlyInGroup() {
        // If a Number can only be in one Cell in a Row/Column/Box then assign that Number to that Cell
        boolean returns = false;
        boolean changes;
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
                        System.out.println("Cell " + getCellInt(rcbNum, instances[0]) + " is the only Cell in " +
                                convertRCBToText(rcbNum) + " that can be " + number + ".  So Cell " +
                                getCellInt(rcbNum, instances[0]) + " is " + number + ".");
                        textArea1.appendText("\nCell " + getCellInt(rcbNum, instances[0]) + " is the only Cell in " +
                                convertRCBToText(rcbNum) + " that can be " + number + ".  So Cell " +
                                getCellInt(rcbNum, instances[0]) + " is " + number + ".");
                        // Prints the removed Numbers

                        changes = true;

                        clearRelations();
                        // Checks if the removed Numbers allowed the puzzle to be solved by an easier method
                    } // If the Number can only be in one Cell and the Cell is not solved already then assign it
                }
            }
            if (changes) { returns = true; }
        } while (changes);
        return returns;
    }
    private boolean rcbInteraction() {
        // If there are 2/3 Cells, in a Row/Column within the same Box, that a Number must be in because of the
        // Row/Column or the Box then remove that Number as a possibility from either the Box or Row/Column
        boolean changes = false;
        for (int number = 1; number <= 9; number++) {
            for (int boxNum = 18; boxNum <= 26; boxNum++) {
                boolean[] boxCells = findNumberInRCB(number, boxNum);
                // Find all the Cells within the Box that contain the Number

                Integer[] rcNum = findSubgroupOverlap(boxCells, boxNum);
                // Find all the instances in the Box of at least 2 Cells
                // in the same Row/Column that contain the Number

                boolean subChanges = evaluateSubgroupOverlap(rcNum, number, boxNum, boxCells);
                // If the subgroup of Cells must contain the Number then remove the Number
                // from the possibilities of either the rest of the Row/Column or the Box

                if (subChanges) { changes = true; }
            }
        }
        return changes;
    }
    private boolean singleGroupMultipleNumbersSubset() {
        // If, in a Row/Column/Box, a group of possible Numbers form a subset then
        // remove the possible Numbers in those Cells that aren't in the subset
        boolean changes = false;
        boolean[][] cells = new boolean[10][];
        for (int rcbNum = 0; rcbNum <= 26; rcbNum++) {
            for (int number = 0; number <= 9; number++) {
                cells[number] = findNumberInRCB(number, rcbNum);
            } // Find all the Cells within the Row/Column/Box that contain each Number

            int[] subset = findSubset(cells);
            // Finds a subset in the Row/Column/Box if there is one

            if (subset[0] != 0) { changes = evaluateSubset(subset, cells, rcbNum); }
            // If the Cells in the subset contain Numbers not in the subset then remove those Numbers
        }
        return changes;
    }
    private boolean multipleGroupsSingleNumberChain() {
        // Also known as X-Wing / Swordfish
        // If, in multiple Rows/Columns, a Number is found the same number of times as Columns/Rows that it is in,
        // then those Cells can be said to form a chain.  If the Number can be found in the Columns/Rows of the chain
        // but in Cells not in the chain, then remove the Number as a possibility from those Cells.
        boolean changes = false;
        for (int rowsOrColumns = 0; rowsOrColumns <= 1; rowsOrColumns++) {
            for (int number = 1; number <=9; number++) {
                for (int comboNumber = 0; comboNumber <= 245; comboNumber++) {

                    int [] sCombo = searchCombinations(comboNumber);
                    // Picks the Rows or Columns to compare

                    changes = findAndEvaluateChain(sCombo, rowsOrColumns, number);
                    // Compares the Rows or Columns and if there is a chain then clear the chain
                }
            }
        }
        return changes;
    }
    private boolean multipleGroupsMultipleNumbersBoard() {
        boolean changes = false;


        return changes;
    }
    private void guess() {
        if (!guess && !createNew) {
            System.out.println("\n\nBegin Trial and Error.\n\n\n");
            textArea1.appendText("\n\n\nBegin Trial and Error.\n\n");
        }
        guess = true;
        String[] guessBoard = new String[100];
        String[] guessRelations = new String[100];

        do {
            int guessClearedCount = clearedCount;

            for (int x = 11; x <= 99; x++) {
                if (x % 10 == 0) { x++; }
                guessBoard[x] = board[x];
                guessRelations[x] = relations[x][20];
            } // Transfer current Board and Relations to guessBoard and guessRelations for storage

            for (int x = 11; x <= 99; x++) {
                if (x % 10 == 0) { x++; }
                if (board[x].length() == 2 && clearedCount != 81) {
                    sysOutPrintBoard();
                    board[x] = board[x].substring(0, 1);
                    System.out.println("Let's make Cell " + x + " the Number " + board[x] + ".");
                    textArea1.appendText("\nLet's make Cell " + x + " the Number " + board[x] + ".");
                    solveBoard();
                    // Pick a 2 possibility Cell and select the first option

                    if (clearedCount == 82) {
                        // If that causes an unsolvable puzzle then the second option is definitely right
                        for (int y = 11; y <= 99; y++) {
                            if (y % 10 == 0) {
                                y++;
                            }
                            board[y] = guessBoard[y];
                            relations[y][20] = guessRelations[y];
                        } // Transfer guessBoard and guessRelations back to Board and Relations from storage
                        clearedCount = guessClearedCount;

                        sysOutPrintBoard();
                        System.out.println("Since Cell " + x + " as the Number " + board[x].substring(0, 1) +
                                " causes the puzzle to become unsolvable, Cell "
                                + x + " must be " + board[x].substring(1, 2) + " instead.");
                        textArea1.appendText("\nSince Cell " + x + " as the Number " + board[x].substring(0, 1) +
                                " causes the puzzle to become unsolvable, Cell "
                                + x + " must be " + board[x].substring(1, 2) + " instead.");
                        board[x] = board[x].substring(1, 2);
                        solveBoard();
                    }
                }
            } // If the puzzle can be solved by guessing at Cells with 2 options then solve it

            for (int x = 11; x <= 99; x++) {
                if (x % 10 == 0) { x++; }
                if (board[x].length() == 3 && clearedCount != 81) {
                    sysOutPrintBoard();
                    board[x] = board[x].substring(0, 1);
                    System.out.println("Let's make Cell " + x + " the Number " + board[x] + ".");
                    textArea1.appendText("\nLet's make Cell " + x + " the Number " + board[x] + ".");
                    solveBoard();
                    // Pick a 3 possibility Cell and select the first option

                    if (clearedCount == 82) {
                        // If that causes an unsolvable puzzle then try the second option
                        for (int y = 11; y <= 99; y++) {
                            if (y % 10 == 0) { y++; }
                            board[y] = guessBoard[y];
                            relations[y][20] = guessRelations[y];
                        } // Transfer guessBoard and guessRelations back to Board and Relations from storage
                        clearedCount = guessClearedCount;

                        sysOutPrintBoard();
                        board[x] = board[x].substring(1, 2);
                        System.out.println("Let's make Cell " + x + " the Number " + board[x] + ".");
                        textArea1.appendText("\nLet's make Cell " + x + " the Number " + board[x] + ".");
                        solveBoard();
                        // Pick a 3 possibility Cell and select the first option

                        if (clearedCount == 82) {
                            // If that causes an unsolvable puzzle then the third option is definitely right
                            for (int y = 11; y <= 99; y++) {
                                if (y % 10 == 0) {
                                    y++;
                                }
                                board[y] = guessBoard[y];
                                relations[y][20] = guessRelations[y];
                            } // Transfer guessBoard and guessRelations back to Board and Relations from storage
                            clearedCount = guessClearedCount;


                            sysOutPrintBoard();
                            board[x] = board[x].substring(2, 3);
                            System.out.println("Since Cell " + x + " as the Number " + board[x].substring(0, 1) +
                                    " or " + board[x].substring(1, 2) + " causes the puzzle to become unsolvable, Cell "
                                    + x + " must be " + board[x].substring(2, 3) + " instead.");
                            textArea1.appendText("\nSince Cell " + x + " as the Number " + board[x].substring(0, 1) +
                                    " or " + board[x].substring(1, 2) + " causes the puzzle to become unsolvable, Cell "
                                    + x + " must be " + board[x].substring(2, 3) + " instead.");
                            board[x] = board[x].substring(2, 3);
                            solveBoard();
                        }
                    }
                }
            } // If the puzzle can be solved by guessing at Cells with 3 options then solve it
        } while (!complete && clearedCount != 81);
    }

    private void testy() {
        long startTime = System.nanoTime();
        int x = 0;
        do {
            newSudoku("Easy", true);
            solveBoard();
            x++;
        } while (x <= 25);
        long totalTime = System.nanoTime() - startTime;

        double time = (double) (totalTime / 1_000_000);
        time /= 1_000;
        System.out.println("25 puzzles created and solved in " + time + " seconds.");
        textArea1.appendText("\n25 puzzles created and solved in " + time + " seconds.");
    }

    public void newSudoku(String difficulty, boolean symmetry) {
        long startTime = System.nanoTime();
        do {
            guess = false;
            clearedCount = 0;
            createNew = true;
            clearBoard();
            groupsDecs();
            inputBoard();
            assignDefaultValues();
            // Initial methods and assignments

            System.out.println("Randomly generated puzzle of difficulty level: " + difficulty + "\nBegin:");
            textArea1.setText("Randomly generated puzzle of difficulty level: " + difficulty + "\nBegin:");
            sysOutPrintBoard();

            for (int x = 0; x <= 26; x++) {
                int randomSubNum = ThreadLocalRandom.current().nextInt
                        (1, getBG(18 + 4 * (x / 9), x % 9).length() + 1);
                setBG(18 + 4 * (x / 9), x % 9,
                        getBG(18 + 4 * (x / 9), x % 9).substring(randomSubNum - 1, randomSubNum));
                // Picks and places a random Number in the designated Cell

                System.out.println("Insert random: Cell " + getCellInt(18 + 4 * (x / 9),x % 9) +
                        " is now " + randomSubNum + ".");
                textArea1.appendText("\nInsert random: Cell " + getCellInt(18 + 4 * (x / 9),x % 9) +
                        " is now " + randomSubNum + ".");
                // Prints the change

                clearRelations(getCellInt(18 + 4 * (x / 9),x % 9));
                // Clears the related Cells
            } // Places the first 27 diagonally because it is faster this way

            sysOutPrintBoard();

            for (int x = 0; x <= 81; x++) {
                int randomCell = ThreadLocalRandom.current().nextInt(11, 100);
                if (randomCell % 10 != 0 && board[randomCell].length() > 1) {
                    int randomSubNum =
                            ThreadLocalRandom.current().nextInt(1, board[randomCell].length() + 1);
                    board[randomCell] = "" + board[randomCell].substring(randomSubNum - 1, randomSubNum);
                    // Picks and places a random Number a random Cell

                    System.out.println("Insert random: Cell " + randomCell +
                            " is now " + board[randomCell] + ".");
                    textArea1.appendText("\nInsert random: Cell " + randomCell +
                            " is now " + board[randomCell] + ".");
                    // Prints the change

                    clearRelations();
                    if (clearedCount < 81) { onlyInGroup(); }
                    if (clearedCount < 81) { rcbInteraction(); }
                    if (clearedCount < 81) { singleGroupMultipleNumbersSubset(); }
                    if (clearedCount < 81) { multipleGroupsSingleNumberChain(); }
                    if (clearedCount < 81) { guess(); }
                    // Tries to solve the board
                }
                x = clearedCount;
            } // Generates the rest of the board
        } while (clearedCount != 81);
        sysOutPrintBoard();
        outputBoard();
        // Creates a new board

        int[] blocks = checkForMultiple();
        int blocksCount = blocks[82];
        blocks[82] = 0;
        Arrays.sort(blocks);
        for (int x = 1; x <= blocks.length - 1; x++) {
            if (blocks[x] == blocks[x - 1]) {
                blocks[x] = 0; }
        }
        for (int x = 0; x <= blocksCount; x++) {
            blocks[x] = blocks[blocks.length - (blocksCount - x) - 1];
            if (x != 0) { blocks[x-1] = blocks[x]; }
        }
        for (int x = 0; x <= blocksCount - 1; x++) {
            if (blocks[x] == 0) {
                for (int y = x; y <= blocksCount - 1; y++) {
                    blocks[y] = blocks[y + 1];
                }
                blocksCount--;
            }
        }

        if (blocksCount > 1) {
            String theBlocks = "";
            for (int x = 0; x <= blocksCount - 1; x++) {
                theBlocks += blocks[x] + "|";
            }
            theBlocks = addCommasAndAnd(theBlocks);
            theBlocks = theBlocks.replace("and", "or");
            System.out.println("To prevent multiple solutions do not clear cell" + theBlocks + ".");
            textArea1.appendText("\nTo prevent multiple solutions do not clear cell" + theBlocks + ".");
        }
        if (blocksCount == 1) {
            System.out.println("To prevent multiple solutions do not clear cell " + blocks[0] + ".");
            textArea1.appendText("\nTo prevent multiple solutions do not clear cell " + blocks[0] + ".");
        }
        // Marks which Cells would cause multiple solutions to be possible

        int[] partners = new int[100];
        String textSymmetry = "";
        if (symmetry) {
            int lineOfSymmetry = ThreadLocalRandom.current().nextInt(0, 4);
            for (int x = 11; x <= 99; x++) {
                if (x % 10 == 0) { x++; }
                partners[x] = x;
                switch (lineOfSymmetry) {
                    case 0: // Symmetry line from left center to right center
                        if (x < 50) { partners[x] = x + 10 * (10 - 2 * (x / 10)); }
                        if (x > 60) { partners[x] = x - 10 * (2 * (x / 10) - 10); }
                        break;
                    case 1: // Symmetry line from top left to bottom right
                        partners[x] = 10 * (x % 10) + x / 10;
                        break;
                    case 2: // Symmetry line from top center to bottom center
                        if (x % 10 < 5) {partners[x] = x + 2 * (5 - x % 10);}
                        if (x % 10 > 5) {partners[x] = x - 2 * (x % 10 - 5);}
                        break;
                    case 3: // Symmetry line from top right to bottom left
                        partners[x] = 10 * (10 - x % 10) + 10 - x / 10;
                        break;
                }
            }
            switch (lineOfSymmetry) {
                case 0: textSymmetry = "the left center to the right center"; break;
                case 1: textSymmetry = "the top left to the bottom right"; break;
                case 2: textSymmetry = "the top center to the bottom center"; break;
                case 3: textSymmetry = "the top right to the bottom left"; break;
            }
        } // Determines which Cells will have the same solved status based on the line of symmetry


        String[] finalBoard = new String[100];
        for (int x = 11; x <= 99; x++) {
            if (x % 10 == 0) { x++; }
            finalBoard[x] = board[x];
        } // Transfer current Board solvedBoard for storage

        int intDiff = 0;
        if (difficulty.equalsIgnoreCase("Easy"))            { intDiff = 1; }
        if (difficulty.equalsIgnoreCase("Intermediate"))    { intDiff = 2; }
        if (difficulty.equalsIgnoreCase("Hard"))            { intDiff = 3; }
        if (difficulty.equalsIgnoreCase("Expert"))          { intDiff = 4; }
        if (difficulty.equalsIgnoreCase("Trial and Error")) { intDiff = 5; }
        boolean correctDifficulty;
        int lastCell;
        int cellsLeft = 81;


        if (symmetry) {System.out.println("\nNow begin removing numbers with respect to the line of symmetry from " +
                textSymmetry + "." + "\n");
                textArea1.appendText("\n\nNow begin removing numbers with respect to the line of symmetry from " +
                        textSymmetry + "." + "\n");
        } else {
            System.out.println("\nNow begin removing numbers.\n");
            textArea1.appendText("\n\nNow begin removing numbers.\n");
        }

        String possibleCells = "";
        for (int x = 11; x <= 99; x++) {
            if (x % 10 == 0) { x++; }
            possibleCells += x + ", ";
        }
        for (int x = 1; x <= blocks.length - 1; x++) {
            possibleCells = possibleCells.replace(blocks[x] + ", ", "");
            if (symmetry) {  possibleCells = possibleCells.replace(partners[blocks[x]] + ", ", ""); }
        }

        do {
            lastCell = ThreadLocalRandom.current().nextInt(0, possibleCells.length() / 4);
            if (possibleCells.length() > 3) {
                lastCell = Integer.parseInt(possibleCells.substring(4 * lastCell, 4 * lastCell + 2));
            } else {
                lastCell = 0;
            }
            possibleCells = possibleCells.replace(lastCell + ", ", "");
            if (symmetry) { possibleCells = possibleCells.replace(partners[lastCell] + ", ", ""); }

            correctDifficulty = true;
            if (possibleCells.length() > 3 && !board[lastCell].equalsIgnoreCase("")) {
                tempBoard[lastCell] += board[lastCell];
                board[lastCell] = "";
                cellsLeft--;
                if (symmetry) {
                    tempBoard[partners[lastCell]] += board[partners[lastCell]];
                    board[partners[lastCell]] = "";
                    cellsLeft--;
                } // Removes a random Cell and it's partner if it has one


                assignTempValues(lastCell, symmetry, partners);


                String tag = lastCell + "|" +
                        ((symmetry && partners[lastCell] != lastCell) ? partners[lastCell] + "|" : "");
                System.out.println("Remove Cell" + addCommasAndAnd(tag) + ".");
                textArea1.appendText("\nRemove Cell" + addCommasAndAnd(tag) + ".");


                board[lastCell] = "X";
                if (symmetry) { board[partners[lastCell]] = "X"; }
                sysOutPrintBoard();
                board[lastCell] = "";
                if (symmetry) { board[partners[lastCell]] = ""; }
                System.out.println("Candidate number board:");
                textArea1.appendText("\nCandidate number board:");
                sysOutPrintTempBoard();


                correctDifficulty = testDifficultyLevel(intDiff, finalBoard, lastCell, symmetry, partners);
                // Checks to see if the puzzle is still able to be solved on the current difficulty

            } // Removes Cells if it is possible to solve on the current difficulty

            if (!correctDifficulty) {
                board[lastCell] = finalBoard[lastCell];
                if(tempBoard[lastCell].length() > 1) {
                    tempBoard[lastCell] = tempBoard[lastCell].substring(0, tempBoard[lastCell].length() - 1);
                }
                cellsLeft++;
                if (symmetry) {
                    board[partners[lastCell]] = finalBoard[partners[lastCell]];
                    if(tempBoard[partners[lastCell]].length() > 1) {
                        tempBoard[partners[lastCell]] =
                                tempBoard[partners[lastCell]].substring(0, tempBoard[partners[lastCell]].length() - 1);
                    }
                    cellsLeft++;
                }
            } // Puts the last Cell and it's partner back on the board if cannot be solved on the current difficulty
        } while (cellsLeft > 47 - 4 * intDiff && possibleCells.length() > 3);
        // Removes Cells until enough have been removed

        if (clearedCount == 81) {
            sysOutPrintBoard();
            outputBoard();
            clearedCount = 0;
            createNew = false;
            guess = false;
            complete = false;
            groupsDecs();
            inputBoard();
            long totalTime = System.nanoTime() - startTime;

            double time = (double) (totalTime / 1_000_000);
            time /= 1_000;
            System.out.println("Puzzle created in " + time + " seconds.");
            textArea1.appendText("\nPuzzle created in " + time + " seconds.");
            textArea2.setText(textArea1.getText());
            textArea1.setText("");
        } // Final print and time
    }

    private int[] checkForMultiple() {



//        textFields[11].setText("123456789456789123789123456312645978645978312978312645231564897564897231897231564");
//        inputBoard();
//        sysOutPrintBoard();


        System.out.println("Begin checking for Cells that could cause multiple solutions to be possible if removed.");
        textArea1.appendText("\nBegin checking for Cells that could cause multiple solutions to be possible if removed.");




        String[] threePieceR = new String[27];
        String[] threePieceC = new String[27];
        for (int x = 0; x <= 26; x++) {
            threePieceR[x] = board[11 + (3 * x) + (x / 3)] + board[12 + (3 * x) + (x / 3)] +
                    board[13 + (3 * x) + (x / 3)];
            threePieceC[x] = board[11 + 30 * (x % 3) + (x / 3)] + board[21 + 30 * (x % 3) + (x / 3)] +
                    board[31 + 30 * (x % 3) + (x / 3)];
        } // Breaks up the board into 3 piece chunks by Row and Column

        int[] rcSearch = new int[]{ // Combinations of acceptable Rows/Columns to compare
                147, 148, 149, 157, 158, 159, 167, 168, 169,
                247, 248, 249, 257, 258, 259, 267, 268, 269,
                347, 348, 349, 357, 358, 359, 367, 368, 369
        };

        int[][] blocksAll = new int[200][];
        int chainCount = -1;
        int[] blocksReturn = new int[200];
        for (int rowsOrColumns = 0; rowsOrColumns <= 1; rowsOrColumns++) {
            for (int search = 0; search <= 2; search++) {
                for (int x = 0; x <= 26; x++) {
                    int[] intRC = new int[3]; // The Row or Column chunks to search through
                    String combine = ""; // The combined chunks for easier referencing
                    intRC[0] = (rcSearch[x] / 100 - 1) * 3 + search;
                    intRC[1] = ((rcSearch[x] / 10) % 10 - 1) * 3 + search;
                    intRC[2] = (rcSearch[x] % 10 - 1) * 3 + search;
                    if (rowsOrColumns == 0) {
                        combine = threePieceR[intRC[0]] + threePieceR[intRC[1]] + threePieceR[intRC[2]];
                    }
                    if (rowsOrColumns == 1) {
                        combine = threePieceC[intRC[0]] + threePieceC[intRC[1]] + threePieceC[intRC[2]];
                    }
                    int[] howMany = new int[10];
                    for (int y = 1; y <= 9; y++) {
                        howMany[y] = 9 - combine.replace("" + y, "").length();
                    }
                    int[] twos = new int[4];
                    int[] threes = new int[3];
                    int twoLocation = 0;
                    int threeLocation = 0;
                    for (int y = 1; y <= 9; y++) {
                        if (howMany[y] == 2) {
                            twos[twoLocation] = y;
                            twoLocation++;
                        }
                        if (howMany[y] == 3) {
                            threes[threeLocation] = y;
                            threeLocation++;
                        }
                    }

                    if (threes[2] > 0) { // If 3 numbers appear 3 times
                        chainCount++;
                        blocksAll[chainCount] = new int[9];
                        for (int y = 0; y <= 8; y += 3) {
                            blocksAll[chainCount][y] = getCellInt((rcSearch[x] / 100 - 1 +
                                    (9 * rowsOrColumns)), combine.indexOf("" + threes[y / 3]) + 3 * search);
                            blocksAll[chainCount][y + 1] = getCellInt(((rcSearch[x] / 10) % 10 - 1 +
                                    (9 * rowsOrColumns)), combine.indexOf("" + threes[y / 3], 3) + 3 * search - 3);
                            blocksAll[chainCount][y + 2] = getCellInt((rcSearch[x] % 10 - 1 +
                                    (9 * rowsOrColumns)), combine.indexOf("" + threes[y / 3], 6) + 3 * search - 6);
                        }
                        int[] orderedArray = blocksAll[chainCount];
                        if (rowsOrColumns == 1) {
                            for (int b = 0; b <= 8; b++) {
                                orderedArray[b] = 10 * (orderedArray[b] % 10) + orderedArray[b] / 10;
                            }
                            Arrays.sort(orderedArray);
                            for (int b = 0; b <= 8; b++) {
                                orderedArray[b] = 10 * (orderedArray[b] % 10) + orderedArray[b] / 10;
                            }
                        } else {
                            Arrays.sort(orderedArray);
                        }
                        String orderedString = orderedArray[0] + "|" + orderedArray[1] + "|" + orderedArray[2] + "|" +
                                orderedArray[3] + "|" + orderedArray[4] + "|" + orderedArray[5] + "|" + orderedArray[6]
                                + "|" + orderedArray[7] + "|" + orderedArray[8] + "|";
                        System.out.println("Found a dependent chain in Cell" + addCommasAndAnd(orderedString));
                        textArea1.appendText("\nFound a dependent chain in Cell" + addCommasAndAnd(orderedString));
                        chainCount++; // 2 spots because you need to mark 2 of these Cells to be sure of no multiples
                        blocksAll[chainCount] = new int[10];
                    }
                    if (threes[2] == 0 && threes[1] > 0) { // If 2 numbers appear 3 times
                        chainCount++;
                        blocksAll[chainCount] = new int[6];
                        for (int y = 0; y <= 5; y += 3) {
                            blocksAll[chainCount][y] = getCellInt((rcSearch[x] / 100 - 1 +
                                    (9 * rowsOrColumns)), combine.indexOf("" + threes[y / 3]) + 3 * search);
                            blocksAll[chainCount][y + 1] = getCellInt(((rcSearch[x] / 10) % 10 - 1 +
                                    (9 * rowsOrColumns)), combine.indexOf("" + threes[y / 3], 3) + 3 * search - 3);
                            blocksAll[chainCount][y + 2] = getCellInt((rcSearch[x] % 10 - 1 +
                                    (9 * rowsOrColumns)), combine.indexOf("" + threes[y / 3], 6) + 3 * search - 6);
                        }
                        int[] orderedArray = blocksAll[chainCount];
                        if (rowsOrColumns == 1) {
                            for (int b = 0; b <= 5; b++) {
                                orderedArray[b] = 10 * (orderedArray[b] % 10) + orderedArray[b] / 10;
                            }
                            Arrays.sort(orderedArray);
                            for (int b = 0; b <= 5; b++) {
                                orderedArray[b] = 10 * (orderedArray[b] % 10) + orderedArray[b] / 10;
                            }
                        } else {
                            Arrays.sort(orderedArray);
                        }
                        String orderedString = orderedArray[0] + "|" + orderedArray[1] + "|" + orderedArray[2] + "|" +
                                orderedArray[3] + "|" + orderedArray[4] + "|" + orderedArray[5] + "|";
                        System.out.println("Found a dependent chain in Cell" + addCommasAndAnd(orderedString));
                        textArea1.appendText("\nFound a dependent chain in Cell" + addCommasAndAnd(orderedString));
                    }
                    if (twos[1] > 0) { // If 2 numbers appear in the same 2 spots in 2 chunks
                        int[] correctOverlaps = new int[]{1245, 1278, 1346, 1379, 2356, 2389, 4578, 4679};
                        String tempCombine;
                        int locations;
                        int howManyTwos = 2;
                        if (twos[2] > 0) howManyTwos = 3;
                        if (twos[3] > 0) howManyTwos = 4;
                        for (int y = 0; y <= howManyTwos - 2; y++) {
                            for (int z = y + 1; z <= howManyTwos - 1; z++) {
                                tempCombine = combine.replace("" + twos[y], "X");
                                tempCombine = tempCombine.replace("" + twos[z], "X");
                                locations = 0;
                                for (int a = 0; a <= 3; a++) {
                                    locations += tempCombine.indexOf("X", (locations / 10) % 10) + 1;
                                    if (a != 3) {
                                        locations *= 10;
                                    }
                                }
                                for (int a = 0; a <= 7; a++) {
                                    if (locations == correctOverlaps[a]) {
                                        chainCount++;
                                        blocksAll[chainCount] = new int[4];
                                        blocksAll[chainCount][0] = getCellInt(intRC[(locations / 1000 - 1) / 3] / 3 +
                                                (9 * rowsOrColumns), (locations / 1000 - 1) % 3 + (search * 3));
                                        blocksAll[chainCount][1] = getCellInt(intRC[((locations / 100) % 10 - 1) / 3] / 3 +
                                                (9 * rowsOrColumns), ((locations / 100) % 10 - 1) % 3 + (search * 3));
                                        blocksAll[chainCount][2] = getCellInt(intRC[((locations / 10) % 10 - 1) / 3] / 3 +
                                                (9 * rowsOrColumns), ((locations / 10) % 10 - 1) % 3 + (search * 3));
                                        blocksAll[chainCount][3] = getCellInt(intRC[(locations % 10 - 1) / 3] / 3 +
                                                (9 * rowsOrColumns), (locations % 10 - 1) % 3 + (search * 3));
                                        int[] orderedArray = blocksAll[chainCount];
                                        if (rowsOrColumns == 1) {
                                            for (int b = 0; b <= 3; b++) {
                                                orderedArray[b] = 10 * (orderedArray[b] % 10) + orderedArray[b] / 10;
                                            }
                                            Arrays.sort(orderedArray);
                                            for (int b = 0; b <= 3; b++) {
                                                orderedArray[b] = 10 * (orderedArray[b] % 10) + orderedArray[b] / 10;
                                            }
                                        } else {
                                            Arrays.sort(orderedArray);
                                        }
                                        String orderedString = orderedArray[0] + "|" + orderedArray[1] + "|" +
                                                orderedArray[2] + "|" + orderedArray[3] + "|";
                                        System.out.println("Found a dependent chain in Cell" +
                                                addCommasAndAnd(orderedString));
                                        textArea1.appendText("\nFound a dependent chain in Cell" +
                                                addCommasAndAnd(orderedString));
                                        a = 7;
                                        z = howManyTwos - 1;
                                        y = howManyTwos - 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        int[] compare = new int[100];
        for (int x = 0; x <= chainCount - 1; x++) {
            for (int y = 0; y <= blocksAll[x].length - 1; y++) {
                if (blocksAll[x][y] != 0) {
                    compare[blocksAll[x][y]] += 1;
                }
            }
        } // Compile all the Cells that could lead to multiple solutions and find if there are overlapping Cells
        int counter = 0;
        for (int x = 11; x <= 99; x++) {
            if (compare[x] > 1) {
                counter++;
            }
        } // Find how many overlapping Cells there are
        int[] overlap = new int[100];
        counter = 0;
        for (int x = 11; x <= 99; x++) {
            if (compare[x] > 1) {
                overlap[counter] = x;
                counter++;
            }
        } // Put the overlapping Cells in an array
        counter = -1;
        for (int x = 11; x <= 99; x++) {
            if (compare[x] > 2) {
                counter++;
                blocksReturn[counter] = overlap[x];
            }
        } // Marks the ones with Cells that appear in 3 chains to use those first
        boolean[] found = new boolean[100];
        int tripleNum = 0;
        for (int x = 0; x <= chainCount; x++) {
            found[x] = false;
            if (blocksAll[x].length < 10) {
                for (int y = 0; y <= blocksAll[x].length - 1; y++) {
                    for (int z = 0; z <= counter; z++) {
                        if (blocksAll[x][y] == blocksReturn[z]) {
                            found[x] = true;
                            if (x < chainCount) {
                                if (blocksAll[x + 1].length == 10) { tripleNum = blocksReturn[z]; }
                            }
                            z = counter;
                            y = blocksAll[x].length - 1;
                        }
                    }
                }
            } // If there is a Cell in the chain already being returned then mark it found

            if (!found[x] && blocksAll[x].length < 10) {
                for (int y = 0; y <= blocksAll[x].length - 1; y++) {
                    for (int z = 11; z <= 99; z++) {
                        if (blocksAll[x][y] == overlap[z]) {
                            found[x] = true;
                            counter++;
                            blocksReturn[counter] = blocksAll[x][y];
                            if (x < chainCount) {
                                if (blocksAll[x + 1].length == 10) { tripleNum = blocksReturn[counter]; }
                            }
                            z = 99;
                            y = blocksAll[x].length;
                        }
                    }
                } // If there is a Cell in the chain that overlaps with another chain then return that Cell

                if (!found[x]) {
                    found[x] = true;
                    counter++;
                    blocksReturn[counter] = blocksAll[x][ThreadLocalRandom.current().nextInt(0, blocksAll[x].length)];
                    if (x < chainCount) {
                        if (blocksAll[x + 1].length == 10) { tripleNum = blocksReturn[counter]; }
                    }
                } // If not then return a random Cell in the chain

            } // If there is not a Cell in the chain already being returned then pick one

            if (blocksAll[x].length == 10) {
                String test = "";
                int testCounter = -1;
                for (int y = 0; y <= 8; y++) {
                    for (int z = 0; z <= counter; z++) {
                        if (blocksAll[x - 1][y] == blocksReturn[z]) {
                            testCounter ++;
                            test += "" + y;
                        }
                    }
                } // Counts how many Cells in the chain are already being returned

                if (testCounter == 0) {
                    String chunkString = "012345678";
                    for (int y = 0; y <= 8; y++) {
                        if (blocksAll[x - 1][y] / 10 == tripleNum / 10 ||
                                blocksAll[x - 1][y] % 10 == tripleNum % 10 ||
                                board[blocksAll[x - 1][y]].equalsIgnoreCase(board[tripleNum])) {
                            chunkString = chunkString.replace(y + "", "");
                        }
                    } // Removes the potential Cells that do not meet the criteria

                    int differentChunkCell = ThreadLocalRandom.current().nextInt(0, 2);
                    differentChunkCell =
                            Integer.parseInt(chunkString.substring(differentChunkCell, differentChunkCell + 1));
                    counter++;
                    blocksReturn[counter] = blocksAll[x - 1][differentChunkCell];
                    found[x] = true;

                } // If only one Cell is being returned then add the other

                if (!found[x]) {
                    for (int y = 0; y <= testCounter - 1; y++) {
                        int yIndex = Integer.parseInt(test.substring(y, y + 1));
                        for (int z = y + 1; z <= testCounter; z++) {
                            int zIndex = Integer.parseInt(test.substring(z, z + 1));
                            if (yIndex / 3 != zIndex / 3 && yIndex % 3 != zIndex % 3 &&
                                    !board[blocksAll[x - 1][yIndex]].equalsIgnoreCase(
                                            board[blocksAll[x - 1][zIndex]])) {
                                counter++;
                                blocksReturn[counter] = blocksAll[x - 1][yIndex];
                                counter++;
                                blocksReturn[counter] = blocksAll[x - 1][zIndex];
                                found[x] = true;
                                z = testCounter;
                                y = testCounter - 1;
                            }
                        }
                    } // Checks if any two Cells meet the criteria and if so then returns the two Cells

                    if (!found[x]) {
                        String chunkString = "012345678";
                        for (int y = 0; y <= 8; y++) {
                            if (blocksAll[x - 1][y] / 10 == tripleNum / 10 ||
                                    blocksAll[x - 1][y] % 10 == tripleNum % 10 ||
                                    board[blocksAll[x - 1][y]].equalsIgnoreCase(board[tripleNum])) {
                                chunkString = chunkString.replace(y + "", "");
                            }
                        } // Removes the potential Cells that do not meet the criteria

                        int differentChunkCell = ThreadLocalRandom.current().nextInt(0, 2);
                        differentChunkCell =
                                Integer.parseInt(chunkString.substring(differentChunkCell, differentChunkCell + 1));
                        counter++;
                        blocksReturn[counter] = blocksAll[x - 1][differentChunkCell];
                        found[x] = true;
                    } // If no groups of two Cells meet the criteria then pick another one that does

                } // If there are multiple Cells being returned already then make sure at least two fit the criteria

            } // Handles the second number needed for chains of 3 numbers in 3 chunks

        } // Selects the Cells to not erase

        int[] tempBlocks = new int[200];
        counter = -1;
        for (int x = 0; x <= blocksReturn.length - 1; x++) {
            if (blocksReturn[x] != 0) {
                counter++;
                tempBlocks[counter] = blocksReturn[x];
            }
        }
        blocksReturn = new int[83];
        for (int x = 0; x <= counter; x++) {
            blocksReturn[x] = tempBlocks[x];
        }
        // Erases blanks in the return array if there are any

        blocksReturn[82] = counter + 1;
        return blocksReturn;
    }
    private boolean testDifficultyLevel(int difficulty, String[] finalBoard, int lastCell, boolean symmetry, int[] partners) {
        System.out.println("Testing Difficulty");
        textArea1.appendText("\nTesting Difficulty");
        guess = false;
        complete = false;
        clearedCount = 0;

        for (int x = 11; x <= 99; x++) {
            if (x % 10 == 0) { x++; }
            if (tempBoard[x].length() > 1) {
                board[x] = tempBoard[x];
            } else {
                if (board[x].equalsIgnoreCase("")) {
                    board[x] = "123456789";
                }
            }
        }
        sysOutPrintBoard();
        // Now that you have the board output and candidate board output



        clearRelations(lastCell);
        if (symmetry) { clearRelations(partners[lastCell]); }
        for (int x = 1; x <= difficulty; x++) {
            boolean[] test = new boolean[6];
            do {
                test[0] = false;
                test[1] = false;
                test[2] = false;
                test[3] = false;
                test[4] = false;
                test[5] = false;


                if (clearedCount < 81)                    { test[1] = onlyInGroup(finalBoard, lastCell, symmetry, partners); }
                if (clearedCount < 81)                    { test[0] = clearRelations(finalBoard, lastCell, symmetry, partners); }
                if (clearedCount < 81)                    { test[1] = onlyInGroup(finalBoard, lastCell, symmetry, partners); }
                if (clearedCount < 81 && difficulty >= 2) { test[2] = rcbInteraction(); }
                if (clearedCount < 81 && difficulty >= 3) { test[3] = singleGroupMultipleNumbersSubset(); }
                if (clearedCount < 81 && difficulty >= 4) { test[4] = multipleGroupsSingleNumberChain(); }
                if (clearedCount < 81 && difficulty >= 5) { test[5] = multipleGroupsMultipleNumbersBoard(); }
            } while (test[0] || test[1] || test[2] || test[3] || test[4] || test[5]);
        } // Solves the board only as far as the difficulty level permits

        boolean results = true;
        if (!board[lastCell].equalsIgnoreCase(finalBoard[lastCell])) { results = false; }
        if (symmetry && !board[partners[lastCell]].equals(finalBoard[partners[lastCell]])) { results = false; }

        String tag = lastCell + "|" + ((symmetry && partners[lastCell] != lastCell) ? partners[lastCell] + "|" : "");
        if (results) {
            System.out.println("\nCell" + addCommasAndAnd(tag) + ((tag.length() > 3) ? " are": " is") +
                    " solved.  The board can be solved on the current difficulty.\n");
            textArea1.appendText("\n\nCell" + addCommasAndAnd(tag) + ((tag.length() > 3) ? " are": " is") +
                    " solved.  The board can be solved on the current difficulty.\n");
            finalBoard[lastCell] = "";
            if (symmetry) { finalBoard[partners[lastCell]] = ""; }
        } else {
            System.out.println("\nThe board cannot be solved on the current difficulty.\nReplace cell" +
                    addCommasAndAnd(tag) + ".\n");
            textArea1.appendText("\n\nThe board cannot be solved on the current difficulty.\nReplace cell" +
                    addCommasAndAnd(tag) + ".\n");
        }

        for (int x = 11; x <= 99; x++) {
            if (x % 10 == 0) { x++; }
            board[x] = finalBoard[x];
        }
        return results;
    }
    private boolean clearRelations(String[] finalBoard, int lastCell, boolean symmetry, int[] partners) {
        // Check if every solved Cell has had their relations cleared
        boolean returns = false;
        int iChanges;
        do {
            iChanges = 0;
            for (int x = 11; x <= 99; x++) {
                if (x % 10 == 0) { x++; }
                iChanges += clearRelations(x);
                if (symmetry) {
                    if (board[partners[lastCell]].equals(finalBoard[partners[lastCell]]) &&
                            board[lastCell].equals(finalBoard[lastCell])) {
                        clearedCount = 81;
                        return false;
                        // If the board has been solved up to lastCell and it's partner then end the search
                    }
                } else {
                    if (board[lastCell].equals(finalBoard[lastCell])) {
                        clearedCount = 81;
                        return false;
                        // If the board has been solved up to lastCell then end the search
                    }
                }
            } // Clear the relations for every cell
            if (iChanges > 0) { returns = true; }
        } while (iChanges > 0);
        return returns;
    }
    private boolean onlyInGroup(String[] finalBoard, int lastCell, boolean symmetry, int[] partners) {
        // If a Number can only be in one Cell in a Row/Column/Box then assign that Number to that Cell
        boolean returns = false;
        boolean changes;
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
                        textArea1.appendText("\nCell " + getCellInt(rcbNum, instances[0]) + " is the only cell in " +
                                convertRCBToText(rcbNum) + " that can be " + number + ".  So cell " +
                                getCellInt(rcbNum, instances[0]) + " is " + number + ".");
                        // Prints the removed Numbers

                        if (symmetry) {
                            if (board[partners[lastCell]].equals(finalBoard[partners[lastCell]]) &&
                                    board[lastCell].equals(finalBoard[lastCell])) {
                                clearedCount = 81;
                                rcbNum = 26;
                                number = 9;
                                return false;
                                // If the board has been solved up to lastCell and it's partner then end the search
                            }
                        } else {
                            if (board[lastCell].equals(finalBoard[lastCell])) {
                                clearedCount = 81;
                                rcbNum = 26;
                                number = 9;
                                return false;
                                // If the board has been solved up to lastCell then end the search
                            }
                        }

                        changes = true;

                        clearRelations(getCellInt(rcbNum, instances[0]));
                        // Checks if the removed Numbers allowed the puzzle to be solved by an easier method

                    } // If the Number can only be in one Cell and the Cell is not solved already then assign it
                }
            }
            if (changes) { returns = true; }
        } while (changes);
        return returns;
    }
    private void assignTempValues(int lastCell, boolean symmetry, int[] partners) {
        for (int x = 0; x <= 19; x++) {
            if (getRelations(lastCell, x).equalsIgnoreCase("") &&
                    !tempBoard[Integer.parseInt(relations[lastCell][x])].contains(
                            tempBoard[lastCell].substring(0, 1))) {
                tempBoard[Integer.parseInt(relations[lastCell][x])] +=
                        tempBoard[lastCell].substring(0, 1);
            }
            if (symmetry && board[Integer.parseInt(relations[partners[lastCell]][x])].equalsIgnoreCase("") &&
                    !tempBoard[Integer.parseInt(relations[partners[lastCell]][x])].contains(
                            tempBoard[partners[lastCell]].substring(0, 1))) {
                tempBoard[Integer.parseInt(relations[partners[lastCell]][x])] +=
                        tempBoard[partners[lastCell]].substring(0, 1);
            }
        }
    }
    private void sysOutPrintTempBoard() {
        // Prints out Board in text/picture format
        int longest = 1;
        for (int x = 11; x <= 99; x++) {
            if (x % 10 == 0) { x++; }
            if (tempBoard[x].length() > longest) {
                longest = tempBoard[x].length();
                if (longest == 9) {
                    x = 99;
                }
            }

        } // Determines the longest string length of the possibilities of each cell

        String borderGrid = "  ";
        for (int x = 1; x <= 32 + longest * 9; x++) {
            borderGrid += "-";
        } // Assigns the top border, as well as the border after each third row, to the String borderGrid

        String colNums = "     ";
        if (longest % 2 != 0) { colNums = "    "; }
        for (int x = 1; x <= 9; x++) {
            for (int y = 1; y <= longest - 1; y++) {
                if (x == 1) {
                    if (y != longest - 1) { colNums += " "; }
                    y++;
                } else { colNums += " "; }
            }
            colNums += "x" + x + "  ";
            if (x % 3 == 0) { colNums += " "; }
        } // Builds the Column number labels at the top of the printed board

        System.out.println(colNums); textArea1.appendText("\n" + colNums);
        System.out.println(borderGrid); textArea1.appendText("\n" + borderGrid);

        for (int b = 1; b <= 9; b++) {
            String rowString = b + "x|| ";
            for (int c = 1; c <= 9; c++) {
                switch (longest - tempBoard[b * 10 + c].length()) {
                    case 0: rowString += tempBoard[b * 10 + c]; break;
                    case 1: rowString += tempBoard[b * 10 + c] + " "; break;
                    case 2: rowString += " " + tempBoard[b * 10 + c] + " "; break;
                    case 3: rowString += " " + tempBoard[b * 10 + c] + "  "; break;
                    case 4: rowString += "  " + tempBoard[b * 10 + c] + "  "; break;
                    case 5: rowString += "  " + tempBoard[b * 10 + c] + "   "; break;
                    case 6: rowString += "   " + tempBoard[b * 10 + c] + "   "; break;
                    case 7: rowString += "   " + tempBoard[b * 10 + c] + "    "; break;
                    case 8: rowString += "    " + tempBoard[b * 10 + c] + "    "; break;
                    case 9: rowString += "         "; break;
                }
                if (c % 3 == 0 && c != 9) { rowString += " || "; }
                if (c == 9) { rowString += " ||"; }
                if (c % 3 != 0) { rowString += " | "; }
            }// Prints the possibilities for each row, one at a time
            System.out.println(rowString); textArea1.appendText("\n" + rowString);
            if (b % 3 == 0) {
                System.out.println(borderGrid); textArea1.appendText("\n" + borderGrid);
            }// Prints the borders
        }
    }
}


// https://www.youtube.com/watch?v=_KHCHiH2RZ0
// to export program to an exe, there's an error though