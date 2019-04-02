package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    // Declarations
    private Button[] button = new Button[7]; // Form control
    private Button[] bigButtons = new Button[100]; // Form control
    private TextField[] textFields = new TextField[100]; // Form control
    private TextArea[] bigTextAreas = new TextArea[100]; // Form control
    private TextArea textArea1 = new TextArea(); // Form control, used to output solution as Barcode beneath the board
    private TextArea textArea2 = new TextArea(); // Form control, used to output creation as Barcode beneath the board
    private String[] board = new String[100]; // Contains the possible Numbers for each Cell
    private String[][] groups = new String[27][]; // The numbered Rows, Columns, and Boxes of the board
    private String[][] relations = new String[100][]; // Contains all the Related Cells for each Cell
    private int clearedCount = 0; // Number of Cells that are solved and have had their Relations cleared
    private int[] combo = new int[246]; // Every 2-4 digit combination of the numbers 1-9
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
            if (x % 10 == 0) { x++; }
            textFields[x] = new TextField();
            int addRow = ((x - 10) / 30) * 2;
            int addCol = (((x - 1) % 10) / 3) * 2;
            root.add(textFields[x], (x % 10) + addCol, (x / 10) + addRow);
            textFields[x].setMinWidth(21.5);
            textFields[x].setMaxWidth(21.5);
            textFields[x].setMinHeight(25);
            textFields[x].setMaxHeight(25);
            textFields[x].setText(board[x]);
        } // Initialize and place textFields

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
        button[4] = new Button("Brute");
        button[5] = new Button("Play");
        // Initialize buttons

        for (int x = 0; x <= 5; x++) {
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
    private void newPuzzleForm() {
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

        button[6] = new Button("Create");
        button[6].setMinWidth(68);
        button[6].setOnAction(e -> {
            newStage.close();
            newSudoku(diffComboBox.getValue().toString(), symYes.isSelected());
        });
        root.add(button[6], 2 , 3);
        // Initialize and place buttons
    }
    private void playPuzzleForm() {
        textFields[11].setText(".5736.2846.4825...28.7.465..924.6...3619.7.42.45132.964.62...75.2.57.46.57864.32.");
        inputBoard();
        Stage newStage = new Stage();
        newStage.setTitle("SuDoKu");
        GridPane root = new GridPane();
        Scene myScene = new Scene(root, 415, 626);
        root.setHgap(2);
        root.setVgap(2);
        root.setAlignment(Pos.TOP_LEFT);
        root.setStyle("-fx-background-color: white;");
        // Basic setup for the Form

        for (int x = 11; x <= 99; x++) {
            if (x % 10 == 0) { x++; }
            bigButtons[x] = new Button();
            bigButtons[x].setMinHeight(50);
            bigButtons[x].setMaxHeight(50);
            bigButtons[x].setMinWidth(7);
            bigButtons[x].setMaxWidth(7);
            bigButtons[x].setOnAction(this::onBigButtonClicked);
            if (board[x].length() == 1) { bigButtons[x].setVisible(false); }
        } // Initialize and handle actions for candidate toggle buttons

        for (int x = 11; x <= 99; x++) {
            if (x % 10 == 0) { x++; }
            bigTextAreas[x] = new TextArea();
            int addRow = ((x - 10) / 30) * 2;
            int addCol = (((x - 1) % 10) / 3) * 2;
            root.add(bigTextAreas[x], (x % 10) + addCol, (x / 10) + addRow);
            root.add(bigButtons[x], (x % 10) + addCol, (x / 10) + addRow); // Place buttons on top of textAreas
            bigTextAreas[x].setMinWidth(43);
            bigTextAreas[x].setMaxWidth(43);
            bigTextAreas[x].setMinHeight(50);
            bigTextAreas[x].setMaxHeight(50);
            bigTextAreas[x].setText(board[x]);
            bigTextAreas[x].setMouseTransparent(true);
            Font font = new Font("Arial Black", 9.3);
            bigTextAreas[x].setFont(font);
            if (board[x].length() == 1) {
                bigTextAreas[x].setStyle("-fx-font-size: 22; -fx-control-inner-background:#EEEEEE;");
                bigTextAreas[x].cancelEdit();
            } else {
                bigTextAreas[x].setText(" 1 2 3\n 4 5 6\n 7 8 9");
                bigButtons[x].fire();
            }
        } // Initialize and place textFields

        CheckBox candidates = new CheckBox("Show Candidates");
        root.add(candidates, 7, 50, 5, 1);
        candidates.requestFocus();
        candidates.setOnAction(actionEvent -> {
            for (int x = 11; x <= 99; x++) {
                if (x % 10 == 0) { x++; }
                if (board[x].length() != 1 && bigTextAreas[x].isMouseTransparent() && !candidates.isSelected()) {
                    bigButtons[x].fire();
                }
                if (board[x].length() != 1 && !bigTextAreas[x].isMouseTransparent() && candidates.isSelected()) {
                    bigButtons[x].fire();
                }
            }
        }); // Initialize and place candidates checkbox



        myScene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            double xPos = e.getSceneX();
            double yPos = e.getSceneY();
            System.out.println("X:" + xPos + ", Y:" + yPos);
            if(xPos <17 & xPos > 11 & yPos < 16 & yPos > 9) {
                if (bigTextAreas[11].getText().contains("1")) {
                    //bigBoard[11] = bigBoard[11].replace("1", "");
                    bigTextAreas[11].setText(bigTextAreas[11].getText().replace("1", "  "));
                    System.out.println(bigTextAreas[11].getText());
                } else {
                    bigTextAreas[11].setText(" 1" + bigTextAreas[11].getText().substring(3, bigTextAreas[11].getLength()));
                    System.out.println(bigTextAreas[11].getText());
                }

                //Call Write BigBoard Method
            }
            if(xPos <23 & xPos > 17 & yPos < 16 & yPos > 9) {
                if (bigTextAreas[11].getText().contains("2")) {
                    //bigBoard[11] = bigBoard[11].replace("1", "");
                    bigTextAreas[11].setText(bigTextAreas[11].getText().replace("2", "  "));
                    System.out.println(bigTextAreas[12].getText());
                } else {
                    if (bigTextAreas[11].getText().contains("1")) {
                        bigTextAreas[11].setText(" 1 2" + bigTextAreas[11].getText().substring(5, bigTextAreas[11].getLength()));
                        System.out.println(bigTextAreas[12].getText());
                    } else {
                        bigTextAreas[11].setText("    2" + bigTextAreas[11].getText().substring(5, bigTextAreas[11].getLength()));
                        System.out.println(bigTextAreas[12].getText());
                    }
                }

                //Call Write BigBoard Method
            }


        });




//
//
//        Label difficultyLabel = new Label();
//        difficultyLabel.setText("Difficulty:");
//        GridPane.setHalignment(difficultyLabel, HPos.RIGHT);
//        root.add(difficultyLabel, 1, 1);
//        Label symmetryLabel = new Label();
//        symmetryLabel.setText("Symmetry:");
//        GridPane.setHalignment(symmetryLabel, HPos.RIGHT);
//        root.add(symmetryLabel, 1, 2);
//        // Initialize and place labels
//
//        ObservableList<String> options =
//                FXCollections.observableArrayList(
//                        "Easy",
//                        "Intermediate",
//                        "Hard",
//                        "Expert",
//                        "Trial and Error");
//        ComboBox diffComboBox = new ComboBox(options);
//
//
//        diffComboBox.setMinWidth(129);
//        root.add(diffComboBox, 2, 1, 2, 1);
//        // Initialize and place combo box
//
//        ToggleGroup group = new ToggleGroup();
//        RadioButton symYes = new RadioButton();
//        symYes.setText("Yes");
//        GridPane.setHalignment(symYes, HPos.CENTER);
//        root.add(symYes, 2, 2);
//        symYes.setToggleGroup(group);
//        RadioButton symNo = new RadioButton();
//        symNo.setText("No");
//        GridPane.setHalignment(symNo, HPos.CENTER);
//        root.add(symNo, 3, 2);
//        symNo.setToggleGroup(group);
//        // Initialize and place radio buttons



//        button[6] = new Button("Create");
//        button[6].setMinWidth(68);
//        button[6].setOnAction(e -> {
//            newStage.close();
//            newSudoku(diffComboBox.getValue().toString(), symYes.isSelected());
//        });
//        root.add(button[6], 2 , 3);
//        // Initialize and place buttons






        newStage.setScene(myScene);
        newStage.show();
        // Display Form
    }


    // Buttons
    private void onButtonClicked(ActionEvent buttonClicked) {
        if (buttonClicked.getSource() == button[0]) { inputBoard(); }
        if (buttonClicked.getSource() == button[1]) { clearBoard(); }
        if (buttonClicked.getSource() == button[2]) { solveBoard(); }
        if (buttonClicked.getSource() == button[3]) { newPuzzleForm(); }
        if (buttonClicked.getSource() == button[4]) { bruteForce(false); }
        if (buttonClicked.getSource() == button[5]) { playPuzzleForm(); }
    }
    private void onBigButtonClicked(ActionEvent buttonClicked) {
        for (int x = 11; x <= 99; x++) {
            if (x % 10 == 0) { x++; }
            if (buttonClicked.getSource() == bigButtons[x]) {
                if (bigTextAreas[x].getText().length() > 1) {
                    board[x] = bigTextAreas[x].getText();
                    bigTextAreas[x].setText("");
                    bigTextAreas[x].setStyle("-fx-font-size: 22;");
                    bigTextAreas[x].setMouseTransparent(false);
                } else {
                    bigTextAreas[x].setText(board[x]);
                    bigTextAreas[x].setStyle("-fx-font-size: 9.3;");
                    bigTextAreas[x].setMouseTransparent(true);
                }
                x = 99;
            }
        }
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
        if (createNew) {
            System.out.println("Copy and paste Game Code into the top left cell and hit Input to reload the board.\n" +
                    "Game Code:\n" + "\n" + boardString + "\n");
            textArea1.appendText("\nCopy and paste Game Code into the top left cell and hit Input to reload the board.\n" +
                    "Game Code:\n" + "\n" + boardString + "\n");
        } else {
            System.out.println("Copy and paste Game Code into the top left cell and hit Input to reload the board.\n" +
                    "Game Code:\n" + "\n" + boardString + "\n" + "\nSolution:");
            textArea1.setText("Copy and paste Game Code into the top left cell and hit Input to reload the board.\n" +
                    "Game Code:\n" + "\n" + boardString + "\n" + "\nSolution:");
        }
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
    private int getCellInt(int rcbNum, int location) {
        // Simplifies having to type:
        return Integer.parseInt(groups[rcbNum][location]);
    }
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
                    setRelations(cellNumber, location, "error");
                        infoBox("Error in entered puzzle.  \nRemoved the last possible Number from Cell " +
                                relations[cellNumber][location] + ".", "Unsolvable puzzle");
                    System.out.println("Error in entered puzzle.  Unsolvable puzzle.");
                    textArea1.appendText("\nError in entered puzzle.  Unsolvable puzzle.");
                    clearedCount = 82;
                    return 1000;
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
        // Returns array[combination of items, 1st item, 2nd item, 3rd item, 4th item, number of items]
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

                            if (!createNew) {
                                clearRelations();
                                onlyInGroup();
                            } // Checks if the removed Numbers allowed the puzzle to be solved by an easier method
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

            if (!createNew) {
                clearRelations();
                onlyInGroup();
                rcbInteraction();
            }
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

                if (!createNew) {
                    clearRelations();
                    onlyInGroup();
                    rcbInteraction();
                    singleGroupMultipleNumbersSubset();
                }
                // Checks if the removed Numbers allowed the puzzle to be solved by an easier method
            }
        }
        return results;
    }

    // Solve Method Schemes
    private void solveBoard() {
        // Solves the puzzle, if solvable, and details the steps taken
        long startTime = System.nanoTime();
        groupsDecs();
        inputBoard();
        createBoardString();
        assignDefaultValues();
        clearRelations();
        sysOutPrintBoard();
        // First time board setup


        boolean[] test;
        do {
            test = new boolean[6];
            if (clearedCount < 81) { test[0] = clearRelations(); }
            if (clearedCount < 81) { test[1] = onlyInGroup(); }
            if (clearedCount < 81) { test[2] = rcbInteraction(); }
            if (clearedCount < 81) { test[3] = singleGroupMultipleNumbersSubset(); }
            if (clearedCount < 81) { test[4] = multipleGroupsSingleNumberChain(); }
            if (clearedCount < 81) { test[5] = multipleGroupsMultipleNumbersBoard(); }
        } while (test[0] || test[1] || test[2] || test[3] || test[4] || test[5]);
        // Logic based solve methods


        if (clearedCount < 81) { bruteForce(true); }
        // Brute force solve method


        if (clearedCount == 81) {
            sysOutPrintBoard();
            outputBoard();
            clearedCount = 0;
            long totalTime = System.nanoTime() - startTime;
            double time = (double) (totalTime / 1_000_000);
            time /= 1_000;
            System.out.println("Puzzle solved in " + time + " seconds.");
            textArea1.appendText("\nPuzzle solved in " + time + " seconds.");
        } // When finished print everything out
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
                if (iChanges > 999) { return false; }
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

        int[] unsolved = new int[81 - clearedCount];
        int count = -1;
        for (int x = 11; x <= 99; x++) {
            if (x % 10 == 0) { x++; }
            if (!relations[x][20].equalsIgnoreCase("Cleared")) { count++; unsolved[count] = x;}
        } // Gather unsolved Cells into an array

        String[][] rowsByNumbers = new String[10][10];
        String[][] columnsByNumbers = new String[10][10];
        for (int rowsOrColumns = 0; rowsOrColumns <= 1; rowsOrColumns++) {
            for (int x = 0; x <= count; x++) {
                for (int y = 0; y <= board[unsolved[x]].length() - 1; y++) {
                    int number = Integer.parseInt(board[unsolved[x]].substring(y, y + 1));
                    if (rowsOrColumns == 0) {
                        if (rowsByNumbers[unsolved[x] / 10][number] == null) {
                            rowsByNumbers[unsolved[x] / 10][number] = "" + unsolved[x] % 10;
                        } else {
                            rowsByNumbers[unsolved[x] / 10][number] += "" + unsolved[x] % 10;
                        }
                    } else {
                        if (columnsByNumbers[unsolved[x] % 10][number] == null) {
                            columnsByNumbers[unsolved[x] % 10][number] = "" + unsolved[x] / 10;
                        } else {
                            columnsByNumbers[unsolved[x] % 10][number] += "" + unsolved[x] / 10;
                        }
                    }
                }
            }
        } // Mark the Columns/Row that the Numbers are in for the Row/Columns
          // rowsByNumbers[Row Number][1-9 Number] = "Column Numbers" ex. rowsByNumbers[Row 3][# 4] = Columns "467"

        sysOutPrintBoard();



        for (int rowsOrColumns = 0; rowsOrColumns <= 1; rowsOrColumns++) {
            for (int rcNum = 1; rcNum <= 9; rcNum++) {
                for (int number = 1; number <= 9; number++) {
                    if (rowsOrColumns == 0) {
                        if (rowsByNumbers[rcNum][number] != null) {
                            System.out.println("In Row " + rcNum + " the Number " + number + " appears in Columns " + rowsByNumbers[rcNum][number]);
                        }

                    } else {
                        if (columnsByNumbers[rcNum][number] != null) {
                            System.out.println("In Column " + rcNum + " the Number " + number + " appears in Rows " + columnsByNumbers[rcNum][number]);
                        }
                    }
                }
            }
        }




        return changes;
    }






    // Comment out the TooLong feature
    // Run testy to check the amount of passes that successful brutes are running vs the obscenly long ones and find the right number


    // Needed: a way to ensure the difficulty is as high as it needs to be and roughly the right amount of numbers left
    //   /\ right now you can request an expert and receive a lower difficulty puzzle












    private double bruteForce(boolean showRoute) {
        // Hard Brute ..............3.85..1.2.......5.7.....4...1...9.......5......73..2.1........4...9
        long startBrute = System.nanoTime();
        double timeBrute;
        System.out.println("Begin Brute Force:");
        textArea1.appendText("\nBegin Brute Force:");
        int[][] neighbors = new int[100][20];
        for (int a = 11; a <= 99; a++) {
            if (a % 10 == 0) { a++; }
            int counter = -1;
            for (int b = 11; b <= 99; b++) {
                if (b % 10 == 0) { b++; }
                if (a != b) {
                    if (a / 10 == b / 10 || a % 10 == b % 10 || // < If in same column/row or \/ in same box
                            ((a / 10 - 1 ) / 3  == (b / 10 - 1 ) / 3 && (a % 10 - 1 ) / 3  == (b % 10 - 1 ) / 3)) {
                        counter ++;
                        neighbors[a][counter] = b;
                    }
                }
            }
        } // Define relations manually because math is fun

        if (createNew) { // How to run if creating new puzzle

            System.out.println("Working...");
            textArea1.appendText("\nWorking...");
            int counter = -1;
            int tooLong = 90_000; // Somewhere??? could be below
            for (int cell = 11; cell <= 99; cell++) {
                if (cell % 10 == 0) { cell++; }

                if (counter > tooLong) {
                    System.out.println("Brute force taking too long, starting new puzzle.");
                    textArea1.appendText("\nBrute force taking too long, starting new puzzle.");
                    return 0;
                } // If the program came across a very difficult to brute force puzzle then pick another one

                if (board[cell].isBlank()) { // Find the lowest blank Cell number
                    int current = 1; // Set current Number to 1
                    boolean test;
                    do { // Repeat this until a Number is found for the Cell that does not conflict with neighbors
                        counter++; // Counts how many passes
                        if (current < 10) { // If every Number has not been tried yet
                            board[cell] = "" + current; // Set the Cell to the lowest number not disproven
                            test = true;
                            for (int neighbor = 0; neighbor <= 19; neighbor++) {
                                if (board[neighbors[cell][neighbor]] != null &&
                                        board[neighbors[cell][neighbor]] != "" &&
                                        board[neighbors[cell][neighbor]].equalsIgnoreCase(board[cell])) {
                                    test = false;
                                    neighbor = 19;
                                }
                            } // Tests whether the number conflicts with any neighboring Cells
                        } else { test = false; } // If already at the highest Number
                        if (!test) { // If the Number conflicts
                            counter++; // Counts how many passes
                            current++; // Go to the next Number
                            if (current > 9) { // If already at the highest Number
                                counter++; // Counts how many passes
                                board[cell] = ""; // Clear the Cell
                                cell--; // Move to the previous Cell
                                if (cell % 10 == 0) { cell--; } // Corrects how the program chooses to number the Cells
                                if (board[cell] != null && board[cell] != "") { // If the new Cell is not empty or null
                                    current = Integer.parseInt(board[cell]); // Set the current Number to the Cell value
                                } else { current = 0; } // If it is empty or null then set the current Number to 0
                                current++; // Go to the next Number
                            }
                        }
                    } while (!test);
                }
            } // Solves by Brute Force
            long totalBrute = System.nanoTime() - startBrute;
            timeBrute = (double) (totalBrute / 1_000_000);
            timeBrute /= 1_000;
            System.out.println("Brute solved in " + timeBrute + " seconds.");
            textArea1.appendText("\nBrute solved in " + timeBrute + " seconds.");
            return timeBrute;

        } else { // How to run if solving a puzzle (Either Solve button or Brute button pressed)

            inputBoard();
            createBoardString();
            if (showRoute) { // How to run if Solve button pressed

                for (int cell = 11; cell <= 99; cell++) {
                    if (cell % 10 == 0) { cell++; }

                    if (board[cell].isBlank()) {
                        int current = 1;
                        boolean test;
                        do {
                            if (current < 10) {
                                board[cell] = "" + current;
                                System.out.println("Trying Cell " + cell + " as " + current);
                                textArea1.appendText("\nTrying Cell " + cell + " as " + current);
                                test = true;
                                for (int neighbor = 0; neighbor <= 19; neighbor++) {
                                    if (board[neighbors[cell][neighbor]] != null &&
                                            board[neighbors[cell][neighbor]] != "" &&
                                            board[neighbors[cell][neighbor]].equalsIgnoreCase(board[cell])) {
                                        test = false;
                                        neighbor = 19;
                                    }
                                }
                            } else { test = false; }
                            if (!test) {
                                current++;
                                if (current > 9) {
                                    board[cell] = "";
                                    cell--;
                                    if (cell % 10 == 0) { cell--; }
                                    if (board[cell] != null && board[cell] != "") {
                                        current = Integer.parseInt(board[cell]);
                                    } else { current = 0; }
                                    current++;
                                }
                            }
                        } while (!test);
                    }
                }

            } else { // How to run if Brute button pressed

                for (int cell = 11; cell <= 99; cell++) {
                    if (cell % 10 == 0) { cell++; }

                    if (board[cell].isBlank()) {
                        int current = 1;
                        boolean test;
                        do {
                            if (current < 10) {
                                board[cell] = "" + current;
                                test = true;
                                for (int neighbor = 0; neighbor <= 19; neighbor++) {
                                    if (board[neighbors[cell][neighbor]] != null &&
                                            board[neighbors[cell][neighbor]] != "" &&
                                            board[neighbors[cell][neighbor]].equalsIgnoreCase(board[cell])) {
                                        test = false;
                                        neighbor = 19;
                                    }
                                }
                            } else { test = false; }
                            if (!test) {
                                current++;
                                if (current > 9) {
                                    board[cell] = "";
                                    cell--;
                                    if (cell % 10 == 0) { cell--; }
                                    if (board[cell] != null && board[cell] != "") {
                                        current = Integer.parseInt(board[cell]);
                                    } else { current = 0; }
                                    current++;
                                }
                            }
                        } while (!test);
                    }
                }
            }
        }

        outputBoard();
        long totalBrute = System.nanoTime() - startBrute;
        timeBrute = (double) (totalBrute / 1_000_000);
        timeBrute /= 1_000;
        System.out.println("Brute solved in " + timeBrute + " seconds.");
        textArea1.appendText("\nBrute solved in " + timeBrute + " seconds.");
        // Print time to solve by brute force
        return timeBrute;
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
    private void otherTesty() {
        double[] time = new double[250];
        for (int x = 0; x <= 249; x++) {
            time[x] = newSudoku("Easy", true);
            time[x] %= 100;
        }
        double total = 0;
        for (int x = 0; x <= 99; x++) {
            System.out.println(time[x]);
            total += time[x];
        }
        System.out.println("Time: " + total + ", avg Time: " + total / 100);
    }


    // Puzzle Creation
    private double newSudoku(String difficulty, boolean symmetry) {
        long startTime = System.nanoTime();
        boolean fastEnough;
        do {
            clearedCount = 0;
            createNew = true;
            clearBoard();
            groupsDecs();
            inputBoard();
            assignDefaultValues();
            // Initial assignments and methods

            System.out.println("Randomly generated puzzle of difficulty level: " + difficulty + "\nBegin:");
            textArea1.setText("Randomly generated puzzle of difficulty level: " + difficulty + "\nBegin:");
            sysOutPrintBoard();
            fastEnough = createBoard();
        } while (!fastEnough); // Generates a random new board

        String textSymmetry = "";
        int[] partners = new int[100];
        if (symmetry) { partners = partners(); }
        // If the new board is to be symmetrical then assign the Cells that will become partners in solved status

        removeCells(difficulty, symmetry, partners);
        // Removes enough Cells so that the board can be solved on the designated difficulty

        outputBoard();
        sysOutPrintBoard();
        createBoardString();
        long totalTime = System.nanoTime() - startTime;
        // Stops timer
        double time = (double) (totalTime / 1_000_000);
        time /= 1_000;
        System.out.println("Puzzle created in " + time + " seconds.\n");
        textArea1.appendText("\nPuzzle created in " + time + " seconds.\n");
        textArea2.setText(textArea1.getText());
        textArea1.setText("");
        // Final print and time



        int tempAdd = 0;
        for (int x = 11; x <= 99; x++) {
            if (x % 10 == 0) { x++; }
            if (board[x].length() == 1) { tempAdd += 1; }
        }
        System.out.println("Final Cell Count: " + tempAdd);
        tempAdd *= 100;
        time += tempAdd;



        clearedCount = 0;
        createNew = false;
        return time;
    }
    private boolean createBoard() {
        // Creates a random new board
        for (int x = 0; x <= 26; x++) {
            int cellNumber = getCellInt(18 + 4 * (x / 9), x % 9);
            int randomSubNum = ThreadLocalRandom.current().nextInt(0, board[cellNumber].length());
            board[cellNumber] = board[cellNumber].substring(randomSubNum, randomSubNum + 1);
            // Picks and places a random Number in the designated Cell

            for (int location = 0; location <= 19; location++) {
                setRelations(cellNumber, location,
                        getRelations(cellNumber, location).replace("" + board[cellNumber], ""));
            } // Remove the Number as a possibility from the related Row, Column, and Box

            System.out.println("Insert random: Cell " + cellNumber + " is now " + board[cellNumber] + ", so remove "
                    + board[cellNumber] + " as a possibility from all related Rows, Columns, and Boxes.");
            textArea1.appendText("\nInsert random: Cell " + cellNumber + " is now " + board[cellNumber] + ", so remove "
                    + board[cellNumber] + " as a possibility from all related Rows, Columns, and Boxes.");
            // Prints the change
        } // Places the first 27 cells diagonally because it is faster this way

        sysOutPrintBoard();

        for (int x = 11; x <= 99; x++) {
            if (x % 10 == 0) { x++; }
            if (board[x].length() > 1) { board[x] = ""; }
        }

        if (bruteForce(false) == 0) { return false; }
        // Places the rest of the cells

        sysOutPrintBoard();

        return true;
    }
    private int[] partners() {
        // Determines which Cells will have the same solved status based on the line of symmetry
        int[] partners = new int[100];
        int lineOfSymmetry = ThreadLocalRandom.current().nextInt(0, 4);
        switch (lineOfSymmetry) {
            case 0: // Symmetry line from left center to right center
                for (int x = 11; x <= 99; x++) {
                    if (x % 10 == 0) { x++; }
                    partners[x] = x;
                    if (x < 50) { partners[x] = x + 10 * (10 - 2 * (x / 10)); }
                    if (x > 60) { partners[x] = x - 10 * (2 * (x / 10) - 10); }
                }
                break;
            case 1: // Symmetry line from top left to bottom right
                for (int x = 11; x <= 99; x++) {
                    if (x % 10 == 0) { x++; }
                    partners[x] = 10 * (x % 10) + x / 10;
                }
                break;
            case 2: // Symmetry line from top center to bottom center
                for (int x = 11; x <= 99; x++) {
                    if (x % 10 == 0) { x++; }
                    partners[x] = x;
                    if (x % 10 < 5) {partners[x] = x + 2 * (5 - x % 10);}
                    if (x % 10 > 5) {partners[x] = x - 2 * (x % 10 - 5);}
                }
                break;
            case 3: // Symmetry line from top right to bottom left
                for (int x = 11; x <= 99; x++) {
                    if (x % 10 == 0) { x++; }
                    partners[x] = 10 * (10 - x % 10) + 10 - x / 10;
                }
                break;
        }
        partners[7] = lineOfSymmetry;
        return partners;
    }
    private void removeCells(String difficulty, boolean symmetry, int[] partners) {
        // Erases numbers from the board until it looks like a sudoku
        String[] finalBoard = new String[100]; // The solved board, before removal
        for (int x = 11; x <= 99; x++) {
            if (x % 10 == 0) { x++; }
            finalBoard[x] = board[x];
            relations[x][20] = "";
        } // Transfers current Board to finalBoard for storage and clears clearedCount

        int intDiff = 0;
        int removal = 0;
        switch (difficulty) {
            case "Easy":            intDiff = 1; removal = 40; break;
            case "Intermediate":    intDiff = 2; removal = 45; break;
            case "Hard":            intDiff = 3; removal = 50; break;
            case "Expert":          intDiff = 4; removal = 55; break;
            case "Trial and Error": intDiff = 5; removal = 60; break;
        } // Assigns an int value to the difficulty string and determines how many numbers to remove

        if (symmetry) {
            String textSymmetry = "";
            switch (partners[7]) {
                case 0: textSymmetry = "the left center to the right center"; break;
                case 1: textSymmetry = "the top left to the bottom right"; break;
                case 2: textSymmetry = "the top center to the bottom center"; break;
                case 3: textSymmetry = "the top right to the bottom left"; break;
            } // Defines the english for the line of symmetry

            System.out.println("\nNow remove " + removal +
                    " numbers with respect to the line of symmetry from " + textSymmetry + "." + "\n");
            textArea1.appendText("\n\nNow remove " + removal +
                    " numbers with respect to the line of symmetry from " + textSymmetry + "." + "\n");
        } else {
            System.out.println("\nNow remove " + removal + " numbers.\n");
            textArea1.appendText("\n\nNow remove " + removal + " numbers.\n");
        } // Prints the removal plan

        String possibleCells = "";
        for (int x = 11; x <= 99; x++) {
            if (x % 10 == 0) { x++; }
            possibleCells += x + ", ";
        } // Puts possible Cells to be removed into a string

        int randomCell;
        do {
            randomCell = ThreadLocalRandom.current().nextInt(0, possibleCells.length() / 4);
            randomCell = Integer.parseInt(possibleCells.substring(4 * randomCell, 4 * randomCell + 2));
            board[randomCell] = "";
            possibleCells = possibleCells.replace(randomCell + ", ", "");
            if (symmetry) {
                board[partners[randomCell]] = "";
                possibleCells = possibleCells.replace(partners[randomCell] + ", ", "");
            }
        } while (possibleCells.length() > (81 - removal) * 4);
        sysOutPrintBoard();
        // Removes the necessary amount of cells to get it close to where it is needed

        String[] tempBoard = new String[100]; // The board position after the removal, before testing difficulty
        if (intDiff < 5) {
            for (int x = 11; x <= 99; x++) {
                if (x % 10 == 0) { x++; }
                tempBoard[x] = board[x];
                if (board[x].equalsIgnoreCase("")) { board[x] = "123456789"; }
            } // Transfers current Board to tempBoard for storage while testing difficulty

            clearedCount = 0;
            boolean[] test = new boolean[5];
            do {
                if (clearedCount < 81)                 { test[0] = clearRelations(); }
                if (clearedCount < 81)                 { test[1] = onlyInGroup(); }
                if (clearedCount < 81 && intDiff >= 2) { test[2] = rcbInteraction(); }
                if (clearedCount < 81 && intDiff >= 3) { test[3] = singleGroupMultipleNumbersSubset(); }
                if (clearedCount < 81 && intDiff >= 4) { test[4] = multipleGroupsSingleNumberChain(); }
                if (clearedCount == 81) { test = new boolean[5]; }
            } while (test[0] || test[1] || test[2] || test[3] || test[4]);
            // Solves the board only as far as the difficulty level permits

            if (clearedCount < 81) {
                do {
                    sysOutPrintBoard();

                    String cellsLeft = "";
                    for (int x = 11; x <= 99; x++) {
                        if (x % 10 == 0) { x++; }
                        if (board[x].length() > 1) { cellsLeft += x + ", "; }
                    } // Puts the remaining Cells into a string

                    randomCell = ThreadLocalRandom.current().nextInt(0, cellsLeft.length() / 4);
                    randomCell = Integer.parseInt(cellsLeft.substring(4 * randomCell, 4 * randomCell + 2));
                    board[randomCell] = finalBoard[randomCell];
                    tempBoard[randomCell] = finalBoard[randomCell];
                    System.out.println("\nAdd Cell " + randomCell +
                            " to the final board to clear the chain and lower the difficulty.");
                    textArea1.appendText("\n\nAdd Cell " + randomCell +
                            " to the final board to clear the chain and lower the difficulty.");
                    if (symmetry && randomCell != partners[randomCell]) {
                        System.out.println("Add Cell " + partners[randomCell] +
                                " to the final board too because of symmetry.\n");
                        textArea1.appendText("\nAdd Cell " + partners[randomCell] +
                                " to the final board too because of symmetry.\n");
                        board[partners[randomCell]] = finalBoard[partners[randomCell]];
                        tempBoard[partners[randomCell]] = finalBoard[partners[randomCell]];
                    } // Randomly solves 1 or 2 Cells to help the process along

                    test = new boolean[5];
                    do {
                        if (clearedCount < 81)                 { test[0] = clearRelations(); }
                        if (clearedCount < 81)                 { test[1] = onlyInGroup(); }
                        if (clearedCount < 81 && intDiff >= 2) { test[2] = rcbInteraction(); }
                        if (clearedCount < 81 && intDiff >= 3) { test[3] = singleGroupMultipleNumbersSubset(); }
                        if (clearedCount < 81 && intDiff >= 4) { test[4] = multipleGroupsSingleNumberChain(); }
                        if (clearedCount == 81) { test = new boolean[5]; }
                    } while (test[0] || test[1] || test[2] || test[3] || test[4]); // Tries to solve the board
                } while (clearedCount < 81);
                // Attempts to solve the board with the new Cells added
            } // Adds Cells back onto the board until it can be solved at the current difficulty

            sysOutPrintBoard();
            System.out.println("\nThe board can be solved on the current difficulty.\n");
            textArea1.appendText("\n\nThe board can be solved on the current difficulty.\n");

            for (int x = 11; x <= 99; x++) {
                if (x % 10 == 0) { x++; }
                board[x] = tempBoard[x];
            } // Transfers tempBoard back to current Board plus the additional Cells if needed
        } // Gets the board to where it can be solved by the current difficulty
    }



    private int[] checkForMultiple() {
        // Determines which Cells to not remove to ensure only one solution is possible
        System.out.println("Checking for Cells that could cause multiple solutions to be possible if removed.");
        textArea1.appendText("\nChecking for Cells that could cause multiple solutions to be possible if removed.");

        int[] blocks = new int[200];
        int blocksCounter = -1;
        int[][] chains = new int[200][];
        int chainCounter = -1;

        for (int comboRows = 0; comboRows <= 245; comboRows++) {
            for (int comboColumns = 0; comboColumns <= 245; comboColumns++) {
                boolean skip = false;
                if (comboRows < 36 && comboColumns > 35) { skip = true; comboColumns = 245; }
                if (comboRows > 35 && comboColumns < 36) { comboColumns = 36; }
                if (comboRows < 120 && comboColumns > 119) { skip = true; comboColumns = 245; }
                if (comboRows > 119 && comboColumns < 120) { comboColumns = 120; }
                // Ensures that the same number of Rows and Columns are being compared

                if (!skip) {
                    int[] rCombo = searchCombinations(comboRows);
                    int[] cCombo = searchCombinations(comboColumns);
                    // Each returns array[combination of items, 1st item, 2nd item, 3rd item, 4th item, number of items]
                    // Picks the Rows and Columns to compare

                    String cellValues;
                    int[] cells = new int[rCombo[5] * cCombo[5]];
                    cells[0] = rCombo[1] * 10 + cCombo[1];
                    cells[1] = rCombo[1] * 10 + cCombo[2];
                    cells[2] = rCombo[2] * 10 + cCombo[1];
                    cells[3] = rCombo[2] * 10 + cCombo[2];
                    cellValues = board[cells[0]] + board[cells[1]] + board[cells[2]] + board[cells[3]];
                    if (rCombo[5] > 2) {
                        cells[4] = rCombo[1] * 10 + cCombo[3];
                        cells[5] = rCombo[2] * 10 + cCombo[3];
                        cells[6] = rCombo[3] * 10 + cCombo[1];
                        cells[7] = rCombo[3] * 10 + cCombo[2];
                        cells[8] = rCombo[3] * 10 + cCombo[3];
                        cellValues += board[cells[4]] + board[cells[5]] + board[cells[6]] + board[cells[7]]
                                + board[cells[8]];
                    }
                    if (rCombo[5] > 3) {
                        cells[9]  = rCombo[1] * 10 + cCombo[4];
                        cells[10] = rCombo[2] * 10 + cCombo[4];
                        cells[11] = rCombo[3] * 10 + cCombo[4];
                        cells[12] = rCombo[4] * 10 + cCombo[1];
                        cells[13] = rCombo[4] * 10 + cCombo[2];
                        cells[14] = rCombo[4] * 10 + cCombo[3];
                        cells[15] = rCombo[4] * 10 + cCombo[4];
                        cellValues += board[cells[9]] + board[cells[10]] + board[cells[11]] + board[cells[12]]
                                + board[cells[13]] + board[cells[14]] + board[cells[15]];
                    }
                    // Determines which Cells to check for a Chain in and combines all the Cell values to compare easier

                    int checker = 0;
                    switch (rCombo[5]) {
                        case 2: // Checking 2 Rows and Columns
                            for (int x = 1; x <= 9; x++) {
                                if (4 - cellValues.replace("" + x, "").length() == 2) { checker += 1; }
                            }
                            if (checker == 2) { // If 2 Numbers appear in the same 2 Rows and Columns
                                chainCounter++;
                                chains[chainCounter] = new int[4];
                                System.arraycopy(cells,0,chains[chainCounter],0,4);
                                Arrays.sort(chains[chainCounter]);
                            }
                            break;
                        case 3: // Checking 3 Rows and Columns
                            int[] numbers = new int[3];
                            int subCounter = -1;
                            for (int x = 1; x <= 9; x++) {
                                if (9 - cellValues.replace("" + x, "").length() == 3) {
                                    checker += 1;
                                    subCounter++;
                                    numbers[subCounter] = x;
                                }
                            }
                            if (checker == 2) { // If 2 Numbers appear in the same 3 Rows and Columns
                                chainCounter++;
                                chains[chainCounter] = new int[6];
                                subCounter = -1;
                                for (int x = 0; x <= 8; x++) {
                                    if (board[cells[x]].equalsIgnoreCase("" + numbers[0]) ||
                                            board[cells[x]].equalsIgnoreCase("" + numbers[1])) {
                                        subCounter++;
                                        chains[chainCounter][subCounter] = cells[x];
                                    }
                                }
                                Arrays.sort(chains[chainCounter]);
                            }
                            if (checker == 3) { // If 3 Numbers appear in the same 3 Rows and Columns
                                chainCounter++;
                                chains[chainCounter] = new int[9];
                                System.arraycopy(cells,0,chains[chainCounter],0,9);
                                Arrays.sort(chains[chainCounter]);
                                chainCounter++; // Additional Cell needed to ensure single solution
                                chains[chainCounter] = new int[10];
                            }
                            break;
                        case 4: // Checking 4 Rows and Columns
                            numbers = new int[4];
                            subCounter = -1;
                            for (int x = 1; x <= 9; x++) {
                                if (16 - cellValues.replace("" + x, "").length() == 4) {
                                    checker += 1;
                                    subCounter++;
                                    numbers[subCounter] = x;
                                }
                            }
                            if (checker == 2) { // If 2 Numbers appear in the same 4 Rows and Columns
                                chainCounter++;
                                chains[chainCounter] = new int[8];
                                subCounter = -1;
                                for (int x = 0; x <= 15; x++) {
                                    if (board[cells[x]].equalsIgnoreCase("" + numbers[0]) ||
                                            board[cells[x]].equalsIgnoreCase("" + numbers[1])) {
                                        subCounter++;
                                        chains[chainCounter][subCounter] = cells[x];
                                    }
                                }
                                Arrays.sort(chains[chainCounter]);
                            }
                            if (checker == 3) { // If 3 Numbers appear in the same 4 Rows and Columns
                                chainCounter++;
                                chains[chainCounter] = new int[12];
                                subCounter = -1;
                                for (int x = 0; x <= 15; x++) {
                                    if (board[cells[x]].equalsIgnoreCase("" + numbers[0]) ||
                                            board[cells[x]].equalsIgnoreCase("" + numbers[1]) ||
                                            board[cells[x]].equalsIgnoreCase("" + numbers[2])) {
                                        subCounter++;
                                        chains[chainCounter][subCounter] = cells[x];
                                    }
                                }
                                Arrays.sort(chains[chainCounter]);
                                chainCounter++; // Additional Cell needed to ensure single solution
                                chains[chainCounter] = new int[13];
                                chainCounter++; // Additional Cell needed to ensure single solution
                                chains[chainCounter] = new int[13];
                            }
                            if (checker == 4) { // If 4 Numbers appear in the same 4 Rows and Columns
                                chainCounter++;
                                chains[chainCounter] = new int[16];
                                System.arraycopy(cells,0,chains[chainCounter],0,16);
                                Arrays.sort(chains[chainCounter]);
                                chainCounter++; // Additional Cell needed to ensure single solution
                                chains[chainCounter] = new int[17];
                                chainCounter++; // Additional Cell needed to ensure single solution
                                chains[chainCounter] = new int[17];
                                chainCounter++; // Additional Cell needed to ensure single solution
                                chains[chainCounter] = new int[17];
                                chainCounter++; // Additional Cell needed to ensure single solution
                                chains[chainCounter] = new int[17];
                            }
                            break;
                    }
                }
            }
        } // Finds all the Chains

        for (int x = 0; x <= chainCounter; x++) {
            String orderedString = "";
            for (int y = 0; y <= chains[x].length - 1; y++) {
                orderedString += chains[x][y] + "|";
            }
            System.out.println("Found a dependent chain in Cell" + addCommasAndAnd(orderedString));
            textArea1.appendText("\nFound a dependent chain in Cell" + addCommasAndAnd(orderedString));
        } // Prints the Chains

        int[] overlap = new int[100];
        for (int x = 0; x <= chainCounter - 1; x++) {
            for (int y = 0; y <= chains[x].length - 1; y++) {
                if (chains[x][y] != 0) {
                    overlap[chains[x][y]] += 1;
                } // ex. compare[34] = 3: Cell 34 appears in 3 chains
            }
        } // Finds overlap between the Chains

        for (int x = 0; x <= chainCounter; x++) {
            // In certain types of Chains it is necessary to maintain multiple Cells in order to
            // ensure the erased Chain does not create a puzzle with multiple solutions possible
            // In Chains of 9:  Pick 2 numbers from 2 different Rows and 2 different Columns of 2 different values
            // In Chains of 12: Pick 3 numbers from 3 different Rows and 3 different Columns of 3 different values
            // In Chains of 16: Pick 5 numbers from 4 different Rows and 4 different Columns of 4 different values
            if (chains[x].length > 8) {
                int[] overlapScore = new int[30];
                int mostOverlap = 0;
                int a = 0;
                if (chains[x].length == 9) {
                    int[][] candidates = new int[30][2];
                    for (int y = 0; y <= 29; y ++) {
                        String possible = "012345678";
                        for (int b = 0; b <= 8; b++) {
                            if (chains[x][a] / 10 == chains[x][b] / 10 ||
                                    chains[x][a] % 10 == chains[x][b] % 10 ||
                                    board[chains[x][a]].equalsIgnoreCase(board[chains[x][b]])) {
                                possible = possible.replace(b + "", "");
                            }
                        } // With "a" as the first Cell determine the other Cells that would meet the criteria

                        if (possible.length() != 2) {
                            System.out.println("You should never be here.");
                        }

                        candidates[y][0] = chains[x][a];
                        candidates[y][1] = chains[x][Integer.parseInt(possible.substring(0, 1))];
                        overlapScore[y] = overlap[candidates[y][0]] + overlap[candidates[y][1]];
                        y++;
                        candidates[y][0] = chains[x][a];
                        candidates[y][1] = chains[x][Integer.parseInt(possible.substring(1, 2))];
                        overlapScore[y] = overlap[candidates[y][0]] + overlap[candidates[y][1]];
                        // Mark and score potential groups of Cells that meet the criteria
                        a++;
                        if (a == 8) { y = 28; }
                    } // Find up to 30 possibilities for groups of Cells that meet the criteria

                    for (int y = 1; y <= 29; y++) {
                        if (overlapScore[y] > overlapScore[mostOverlap]) { mostOverlap = y; }
                        if (overlapScore[y] == 0) { y = 29;}
                    } // Determine the group with the highest overlap

                    blocksCounter++;
                    blocks[blocksCounter] = candidates[mostOverlap][0];
                    blocksCounter++;
                    blocks[blocksCounter] = candidates[mostOverlap][1];
                    // Sets the Cells for this Chain to those with the highest overlap
                }
                if (chains[x].length == 12) {
                    int[][] candidates = new int[30][3];
                    for (int y = 0; y <= 29; y ++) {
                        String possible = "0123456789ab";
                        String forReference = "0123456789abcdef";
                        for (int b = 0; b <= 11; b++) {
                            if (chains[x][a] / 10 == chains[x][b] / 10 ||
                                    chains[x][a] % 10 == chains[x][b] % 10 ||
                                    board[chains[x][a]].equalsIgnoreCase(board[chains[x][b]])) {
                                possible = possible.replace(forReference.substring(b, b + 1), "");
                            }
                        } // With "a" as the first Cell determine the other Cells that would meet the criteria

                        if (possible.length() != 4) {
                            System.out.println("You should never be here.");
                        }

                        y--;
                        int[] lastTwo = new int[] {1, 2, 3, 12, 13, 23};
                        int[] location = new int[2];
                        for (int b = 0; b <= 5; b++) {
                            for (int c = 0; c <= 1; c++) {
                                int part = 0;
                                switch (c) {
                                    case 0: part = lastTwo[b] / 10; break;
                                    case 1: part = lastTwo[b] % 10; break;
                                }
                                for (int d = 0; d <= 11; d++) {
                                    if (possible.substring(part, part + 1).equalsIgnoreCase(
                                            forReference.substring(d, d + 1))) {
                                        location[c] = d;
                                    }
                                }
                            }

                            if (chains[x][location[0]] / 10 != chains[x][location[1]] / 10 &&
                                    chains[x][location[0]] % 10 != chains[x][location[1]] % 10 &&
                                    !board[chains[x][location[0]]].equalsIgnoreCase(board[chains[x][location[1]]])) {
                                y++;
                                candidates[y][0] = chains[x][a];
                                candidates[y][1] = chains[x][location[0]];
                                candidates[y][2] = chains[x][location[1]];
                                overlapScore[y] = overlap[candidates[y][0]] + overlap[candidates[y][1]] +
                                        overlap[candidates[y][2]];
                            }
                        } // Mark and score potential groups of Cells that meet the criteria
                        a++;
                        if (a == 11) { y = 29; }
                    } // Find up to 30 possibilities for groups of Cells that meet the criteria

                    for (int y = 1; y <= 29; y++) {
                        if (overlapScore[y] > overlapScore[mostOverlap]) { mostOverlap = y; }
                        if (overlapScore[y] == 0) { y = 29;}
                    } // Determine the group with the highest overlap

                    blocksCounter++;
                    blocks[blocksCounter] = candidates[mostOverlap][0];
                    blocksCounter++;
                    blocks[blocksCounter] = candidates[mostOverlap][1];
                    blocksCounter++;
                    blocks[blocksCounter] = candidates[mostOverlap][2];
                    // Sets the Cells for this Chain to those with the highest overlap
                }
                if (chains[x].length == 16) {
                    int[][] candidates = new int[30][5];
                    for (int y = 0; y <= 29; y ++) {
                        String possible = "0123456789abcdef";
                        String forReference = "0123456789abcdef";
                        for (int b = 0; b <= 15; b++) {
                            if (chains[x][a] / 10 == chains[x][b] / 10 ||
                                    chains[x][a] % 10 == chains[x][b] % 10 ||
                                    board[chains[x][a]].equalsIgnoreCase(board[chains[x][b]])) {
                                possible = possible.replace(forReference.substring(b, b + 1), "");
                            }
                        } // With "a" as the first Cell determine the other Cells that would meet the criteria

                        if (possible.length() != 6) {
                            System.out.println("You should never be here.");
                        }

                        y--;
                        int[] lastFour = new int[] {123, 124, 125, 134, 135, 145,
                                234, 235, 245, 345, 1234, 1235, 1245, 1345, 2345};
                        int[] location = new int[4];
                        for (int b = 0; b <= 14; b++) {
                            for (int c = 0; c <= 3; c++) {
                                int part = 0;
                                switch (c) {
                                    case 0: part = lastFour[b] / 1000; break;
                                    case 1: part = (lastFour[b] / 100) % 10; break;
                                    case 2: part = (lastFour[b] / 10) % 10; break;
                                    case 3: part = lastFour[b] % 10; break;
                                }
                                for (int d = 0; d <= 15; d++) {
                                    if (possible.substring(part, part + 1).equalsIgnoreCase(
                                            forReference.substring(d, d + 1))) {
                                        location[c] = d;
                                    }
                                }
                            }

                            String threeRows = "";
                            String threeColumns = "";
                            String threeValues = "";
                            for (int c = 0; c <= 3; c++) {
                                if (!threeRows.contains("" + chains[x][location[c]] / 10)) {
                                    threeRows += chains[x][location[c]] / 10;
                                }
                                if (!threeColumns.contains("" + chains[x][location[c]] % 10)) {
                                    threeColumns += chains[x][location[c]] % 10;
                                }
                                if (!threeValues.contains("" + board[chains[x][location[c]]])) {
                                    threeValues += board[chains[x][location[c]]];
                                }
                            } // Mark the different number of Rows, Columns, and Values of the remaining potential Cells

                            if (threeRows.length() == 3 && threeColumns.length() == 3 && threeValues.length() == 3) {
                                y++;
                                candidates[y][0] = chains[x][a];
                                candidates[y][1] = chains[x][location[0]];
                                candidates[y][2] = chains[x][location[1]];
                                candidates[y][3] = chains[x][location[2]];
                                candidates[y][4] = chains[x][location[3]];
                                overlapScore[y] = overlap[candidates[y][0]] + overlap[candidates[y][1]] +
                                        overlap[candidates[y][2]] + overlap[candidates[y][3]] +
                                        overlap[candidates[y][4]];
                            }
                        } // Mark and score potential groups of Cells that meet the criteria

                        a++;
                        if (a == 15) { y = 29; }
                    } // Find up to 30 possibilities for groups of Cells that meet the criteria

                    for (int y = 1; y <= 29; y++) {
                        if (overlapScore[y] > overlapScore[mostOverlap]) { mostOverlap = y; }
                        if (overlapScore[y] == 0) { y = 29;}
                    } // Determine the group with the highest overlap

                    blocksCounter++;
                    blocks[blocksCounter] = candidates[mostOverlap][0];
                    blocksCounter++;
                    blocks[blocksCounter] = candidates[mostOverlap][1];
                    blocksCounter++;
                    blocks[blocksCounter] = candidates[mostOverlap][2];
                    blocksCounter++;
                    blocks[blocksCounter] = candidates[mostOverlap][3];
                    blocksCounter++;
                    blocks[blocksCounter] = candidates[mostOverlap][4];
                    // Sets the Cells for this Chain to those with the highest overlap
                }
            }
        } // Finds the Cells to return for the Chains that require more than one Cell to be marked

        for (int x = 0; x <= chainCounter; x++) {
            if (chains[x].length < 9) {
                boolean found = false;
                for (int y = 0; y <= chains[x].length - 1; y++) {
                    for (int z = 0; z <= blocksCounter; z++) {
                        if (chains[x][y] == blocks[z]) {
                            found = true;
                            z = blocksCounter;
                            y = chains[x].length - 1;
                        }
                    }
                } // If there is a Cell in the Chain already being returned then mark it found

                if (!found) {
                    int mostOverlap = 0;
                    for (int y = 1; y <= chains[x].length - 1; y++) {
                        if (overlap[chains[x][y]] > overlap[chains[x][mostOverlap]]) {
                            mostOverlap = y;
                        }
                    } // Finds the Cell in the Chain with the most overlap
                    blocksCounter++;
                    blocks[blocksCounter] = chains[x][mostOverlap];
                } // If there is not a Cell in the Chain already being returned then pick the one with the most overlap
            }
        } // Finds the Cells to return for the Chains that only require one Cell to be marked

        Arrays.sort(blocks);
        int[] returnBlocks = new int[blocksCounter + 1];
        System.arraycopy(blocks, blocks.length - (blocksCounter + 1),
                returnBlocks, 0, blocksCounter + 1);
        // Erases blanks in the return array if there are any

        if (blocksCounter > 0) {
            String theBlocks = "";
            for (int x = 0; x <= blocksCounter; x++) {
                theBlocks += returnBlocks[x] + "|";
            }
            theBlocks = addCommasAndAnd(theBlocks);
            theBlocks = theBlocks.replace("and", "or");
            System.out.println("To prevent multiple solutions do not clear cell" + theBlocks + ".");
            textArea1.appendText("\nTo prevent multiple solutions do not clear cell" + theBlocks + ".");
        }
        if (blocksCounter == 0) {
            System.out.println("To prevent multiple solutions do not clear cell " + returnBlocks[0] + ".");
            textArea1.appendText("\nTo prevent multiple solutions do not clear cell " + returnBlocks[0] + ".");
        }
        if (blocksCounter == -1) {
            System.out.println("Multiple solutions not possible with this board.  That's rare.");
            textArea1.appendText("\nMultiple solutions not possible with this board.  That's rare.");
        }
        // Prints the Cells to not clear

        return returnBlocks;
    }

}


// https://www.youtube.com/watch?v=_KHCHiH2RZ0
// to export program to an exe, there's an error though