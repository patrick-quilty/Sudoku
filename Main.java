package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

public class Main extends Application {
    public TextField tf11 = new TextField();
    public TextField tf12 = new TextField();
    public TextField tf13 = new TextField();
    public TextField tf14 = new TextField();
    public TextField tf15 = new TextField();
    public TextField tf16 = new TextField();
    public TextField tf17 = new TextField();
    public TextField tf18 = new TextField();
    public TextField tf19 = new TextField();
    public TextField tf21 = new TextField();
    public TextField tf22 = new TextField();
    public TextField tf23 = new TextField();
    public TextField tf24 = new TextField();
    public TextField tf25 = new TextField();
    public TextField tf26 = new TextField();
    public TextField tf27 = new TextField();
    public TextField tf28 = new TextField();
    public TextField tf29 = new TextField();
    public TextField tf31 = new TextField();
    public TextField tf32 = new TextField();
    public TextField tf33 = new TextField();
    public TextField tf34 = new TextField();
    public TextField tf35 = new TextField();
    public TextField tf36 = new TextField();
    public TextField tf37 = new TextField();
    public TextField tf38 = new TextField();
    public TextField tf39 = new TextField();
    public TextField tf41 = new TextField();
    public TextField tf42 = new TextField();
    public TextField tf43 = new TextField();
    public TextField tf44 = new TextField();
    public TextField tf45 = new TextField();
    public TextField tf46 = new TextField();
    public TextField tf47 = new TextField();
    public TextField tf48 = new TextField();
    public TextField tf49 = new TextField();
    public TextField tf51 = new TextField();
    public TextField tf52 = new TextField();
    public TextField tf53 = new TextField();
    public TextField tf54 = new TextField();
    public TextField tf55 = new TextField();
    public TextField tf56 = new TextField();
    public TextField tf57 = new TextField();
    public TextField tf58 = new TextField();
    public TextField tf59 = new TextField();
    public TextField tf61 = new TextField();
    public TextField tf62 = new TextField();
    public TextField tf63 = new TextField();
    public TextField tf64 = new TextField();
    public TextField tf65 = new TextField();
    public TextField tf66 = new TextField();
    public TextField tf67 = new TextField();
    public TextField tf68 = new TextField();
    public TextField tf69 = new TextField();
    public TextField tf71 = new TextField();
    public TextField tf72 = new TextField();
    public TextField tf73 = new TextField();
    public TextField tf74 = new TextField();
    public TextField tf75 = new TextField();
    public TextField tf76 = new TextField();
    public TextField tf77 = new TextField();
    public TextField tf78 = new TextField();
    public TextField tf79 = new TextField();
    public TextField tf81 = new TextField();
    public TextField tf82 = new TextField();
    public TextField tf83 = new TextField();
    public TextField tf84 = new TextField();
    public TextField tf85 = new TextField();
    public TextField tf86 = new TextField();
    public TextField tf87 = new TextField();
    public TextField tf88 = new TextField();
    public TextField tf89 = new TextField();
    public TextField tf91 = new TextField();
    public TextField tf92 = new TextField();
    public TextField tf93 = new TextField();
    public TextField tf94 = new TextField();
    public TextField tf95 = new TextField();
    public TextField tf96 = new TextField();
    public TextField tf97 = new TextField();
    public TextField tf98 = new TextField();
    public TextField tf99 = new TextField();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("grid.fxml"));
        primaryStage.setTitle("Sudoku Guru");
        primaryStage.setScene(new Scene(root, 212, 500));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    //Declarations
    @FXML
    private Button inputBoard;
    @FXML
    private Button step2Button;
    @FXML
    private Button step3Button;

    //Buttons
    public void onButtonClicked(ActionEvent buttonClicked) {
        if (buttonClicked.getSource().equals(inputBoard)) { inputBoard(); }
        if (buttonClicked.getSource().equals(step2Button)) { step3(); }
        if (buttonClicked.getSource().equals(step3Button)) { solveBoard(); }
    }

    public String[] board = new String[100];
    public String[][] groups = new String[27][];
    public String[][] relations = new String[100][];

    public void groupsDecs() {
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


    //add up/down/left/right key press to move you around the board functionality
    //show candidate numbers in cell


    public void inputBoard() {
        // 123456789123456789123456789123456789123456789123456789123456789123456789123456789
        if (tf11.getLength() == 81) {
            for (int x = 99; x >= 11; x--) {
                if (x % 10 != 0) {
                    board[x] = tf11.getText().substring(tf11.getLength() - 1);
                    tf11.setText(tf11.getText(0, tf11.getLength() - 1));
                }
            }
        } else {
            board[99] = tf99.getText();
            board[98] = tf98.getText();
            board[97] = tf97.getText();
            board[96] = tf96.getText();
            board[95] = tf95.getText();
            board[94] = tf94.getText();
            board[93] = tf93.getText();
            board[92] = tf92.getText();
            board[91] = tf91.getText();
            board[89] = tf89.getText();
            board[88] = tf88.getText();
            board[87] = tf87.getText();
            board[86] = tf86.getText();
            board[85] = tf85.getText();
            board[84] = tf84.getText();
            board[83] = tf83.getText();
            board[82] = tf82.getText();
            board[81] = tf81.getText();
            board[79] = tf79.getText();
            board[78] = tf78.getText();
            board[77] = tf77.getText();
            board[76] = tf76.getText();
            board[75] = tf75.getText();
            board[74] = tf74.getText();
            board[73] = tf73.getText();
            board[72] = tf72.getText();
            board[71] = tf71.getText();
            board[69] = tf69.getText();
            board[68] = tf68.getText();
            board[67] = tf67.getText();
            board[66] = tf66.getText();
            board[65] = tf65.getText();
            board[64] = tf64.getText();
            board[63] = tf63.getText();
            board[62] = tf62.getText();
            board[61] = tf61.getText();
            board[59] = tf59.getText();
            board[58] = tf58.getText();
            board[57] = tf57.getText();
            board[56] = tf56.getText();
            board[55] = tf55.getText();
            board[54] = tf54.getText();
            board[53] = tf53.getText();
            board[52] = tf52.getText();
            board[51] = tf51.getText();
            board[49] = tf49.getText();
            board[48] = tf48.getText();
            board[47] = tf47.getText();
            board[46] = tf46.getText();
            board[45] = tf45.getText();
            board[44] = tf44.getText();
            board[43] = tf43.getText();
            board[42] = tf42.getText();
            board[41] = tf41.getText();
            board[39] = tf39.getText();
            board[38] = tf38.getText();
            board[37] = tf37.getText();
            board[36] = tf36.getText();
            board[35] = tf35.getText();
            board[34] = tf34.getText();
            board[33] = tf33.getText();
            board[32] = tf32.getText();
            board[31] = tf31.getText();
            board[29] = tf29.getText();
            board[28] = tf28.getText();
            board[27] = tf27.getText();
            board[26] = tf26.getText();
            board[25] = tf25.getText();
            board[24] = tf24.getText();
            board[23] = tf23.getText();
            board[22] = tf22.getText();
            board[21] = tf21.getText();
            board[19] = tf19.getText();
            board[18] = tf18.getText();
            board[17] = tf17.getText();
            board[16] = tf16.getText();
            board[15] = tf15.getText();
            board[14] = tf14.getText();
            board[13] = tf13.getText();
            board[12] = tf12.getText();
            board[11] = tf11.getText();
        }
        outputBoard();
    }
    public void outputBoard() {
        for (int x = 99; x >= 11; x--) {
            if (x % 10 != 0) {
                if (board[x].equals("1") || board[x].equals("2") || board[x].equals("3") ||
                        board[x].equals("4") || board[x].equals("5") || board[x].equals("6") ||
                        board[x].equals("7") || board[x].equals("8") || board[x].equals("9")) {
                } else {
                    board[x] = "";
                }
            }
        }
        tf99.setText(board[99]);
        tf98.setText(board[98]);
        tf97.setText(board[97]);
        tf96.setText(board[96]);
        tf95.setText(board[95]);
        tf94.setText(board[94]);
        tf93.setText(board[93]);
        tf92.setText(board[92]);
        tf91.setText(board[91]);
        tf89.setText(board[89]);
        tf88.setText(board[88]);
        tf87.setText(board[87]);
        tf86.setText(board[86]);
        tf85.setText(board[85]);
        tf84.setText(board[84]);
        tf83.setText(board[83]);
        tf82.setText(board[82]);
        tf81.setText(board[81]);
        tf79.setText(board[79]);
        tf78.setText(board[78]);
        tf77.setText(board[77]);
        tf76.setText(board[76]);
        tf75.setText(board[75]);
        tf74.setText(board[74]);
        tf73.setText(board[73]);
        tf72.setText(board[72]);
        tf71.setText(board[71]);
        tf69.setText(board[69]);
        tf68.setText(board[68]);
        tf67.setText(board[67]);
        tf66.setText(board[66]);
        tf65.setText(board[65]);
        tf64.setText(board[64]);
        tf63.setText(board[63]);
        tf62.setText(board[62]);
        tf61.setText(board[61]);
        tf59.setText(board[59]);
        tf58.setText(board[58]);
        tf57.setText(board[57]);
        tf56.setText(board[56]);
        tf55.setText(board[55]);
        tf54.setText(board[54]);
        tf53.setText(board[53]);
        tf52.setText(board[52]);
        tf51.setText(board[51]);
        tf49.setText(board[49]);
        tf48.setText(board[48]);
        tf47.setText(board[47]);
        tf46.setText(board[46]);
        tf45.setText(board[45]);
        tf44.setText(board[44]);
        tf43.setText(board[43]);
        tf42.setText(board[42]);
        tf41.setText(board[41]);
        tf39.setText(board[39]);
        tf38.setText(board[38]);
        tf37.setText(board[37]);
        tf36.setText(board[36]);
        tf35.setText(board[35]);
        tf34.setText(board[34]);
        tf33.setText(board[33]);
        tf32.setText(board[32]);
        tf31.setText(board[31]);
        tf29.setText(board[29]);
        tf28.setText(board[28]);
        tf27.setText(board[27]);
        tf26.setText(board[26]);
        tf25.setText(board[25]);
        tf24.setText(board[24]);
        tf23.setText(board[23]);
        tf22.setText(board[22]);
        tf21.setText(board[21]);
        tf19.setText(board[19]);
        tf18.setText(board[18]);
        tf17.setText(board[17]);
        tf16.setText(board[16]);
        tf15.setText(board[15]);
        tf14.setText(board[14]);
        tf13.setText(board[13]);
        tf12.setText(board[12]);
        tf11.setText(board[11]);
    }
    public void assignDefaultValues() {
        for (int x = 11; x <= 99; x++) {
            if (x % 10 != 0) {
                if (board[x].length() != 1) {
                    board[x] = "123456789";
                }
            }
        }
    }
    public void clearBoard() {
        tf11.clear();
        tf12.clear();
        tf13.clear();
        tf14.clear();
        tf15.clear();
        tf16.clear();
        tf17.clear();
        tf18.clear();
        tf19.clear();
        tf21.clear();
        tf22.clear();
        tf23.clear();
        tf24.clear();
        tf25.clear();
        tf26.clear();
        tf27.clear();
        tf28.clear();
        tf29.clear();
        tf31.clear();
        tf32.clear();
        tf33.clear();
        tf34.clear();
        tf35.clear();
        tf36.clear();
        tf37.clear();
        tf38.clear();
        tf39.clear();
        tf41.clear();
        tf42.clear();
        tf43.clear();
        tf44.clear();
        tf45.clear();
        tf46.clear();
        tf47.clear();
        tf48.clear();
        tf49.clear();
        tf51.clear();
        tf52.clear();
        tf53.clear();
        tf54.clear();
        tf55.clear();
        tf56.clear();
        tf57.clear();
        tf58.clear();
        tf59.clear();
        tf61.clear();
        tf62.clear();
        tf63.clear();
        tf64.clear();
        tf65.clear();
        tf66.clear();
        tf67.clear();
        tf68.clear();
        tf69.clear();
        tf71.clear();
        tf72.clear();
        tf73.clear();
        tf74.clear();
        tf75.clear();
        tf76.clear();
        tf77.clear();
        tf78.clear();
        tf79.clear();
        tf81.clear();
        tf82.clear();
        tf83.clear();
        tf84.clear();
        tf85.clear();
        tf86.clear();
        tf87.clear();
        tf88.clear();
        tf89.clear();
        tf91.clear();
        tf92.clear();
        tf93.clear();
        tf94.clear();
        tf95.clear();
        tf96.clear();
        tf97.clear();
        tf98.clear();
        tf99.clear();
    }
    public String convertRCBToText(int groupNum) {
        if (groupNum < 0 || groupNum > 26) {
            return "$%&^$%#$^&*()error*&*&%^%^&*&^%";
        }
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
            case 18: return "The Top Left Box";
            case 19: return "The Top Middle Box";
            case 20: return "The Top Right Box";
            case 21: return "The Middle Left Box";
            case 22: return "The Center Box";
            case 23: return "The Middle Right Box";
            case 24: return "The Bottom Left Box";
            case 25: return "The Bottom Middle Box";
            case 26: return "The Bottom Right Box";
            default:
            return "Column " + (groupNum - 8);
        }
    }
    public void sysOutPrintBoard() {
        //Prints out Board
        int highest = 1;
        for( int i = 11; i <= 99; i++ ) {
            if (i % 10 == 0) { i++; }
            if (board[i].length() > highest) {
                highest = board[i].length();
                if (highest == 9) { i = 99; }
            }
        }
        String borderGrid = "";
        for (int x = 1; x <= 32 + highest * 9; x++) {
            borderGrid += "-";
        }
        System.out.println(borderGrid);
        for (int b = 1; b <= 9; b++) {
            String rowString = "|| ";
            for (int c = 1; c <=9; c++) {
                switch (highest - board[b * 10 + c].length()) {
                    case 0:
                        rowString += board[b * 10 + c]; break;
                    case 1:
                        rowString += board[b * 10 + c] + " "; break;
                    case 2:
                        rowString += " " + board[b * 10 + c] + " "; break;
                    case 3:
                        rowString += " " + board[b * 10 + c] + "  "; break;
                    case 4:
                        rowString += "  " + board[b * 10 + c] + "  "; break;
                    case 5:
                        rowString += "  " + board[b * 10 + c] + "   "; break;
                    case 6:
                        rowString += "   " + board[b * 10 + c] + "   "; break;
                    case 7:
                        rowString += "   " + board[b * 10 + c] + "    "; break;
                    case 8:
                        rowString += "    " + board[b * 10 + c] + "    "; break;
                }
                if (c % 3 == 0 && c != 9) { rowString += " || "; }
                if (c ==9) { rowString += " ||"; }
                if (c % 3 != 0) { rowString += " | "; }
            }
            System.out.println(rowString);
            if (b % 3 == 0) {
                System.out.println(borderGrid);
            }
        }
    }

    public void newSudoku(int difficulty) {
        groupsDecs();


        for (int x = 11; x <= 99; x++) {
            if (x % 10 != 0) {
                int randomNum = ThreadLocalRandom.current().nextInt(1, board[x].length() + 1);
                if (board[x].length() > 1) {
                    board[x] = "" + board[x].substring(randomNum - 1, randomNum);
                }
                for (int p = 0; p <= 19; p++) {


                    board[Integer.parseInt(relations[x][p])] = board[Integer.parseInt(relations[x][p])].replace("" + board[x], "");


                }

                System.out.println("------------------------------" + randomNum + "-" + x + "----------------------------------------");
                for (int b = 1; b <= 9; b++) {
                    System.out.println("| " + board[b * 10 + 1] + " | " + board[b * 10 + 2] + " | " + board[b * 10 + 3] + " | " + board[b * 10 + 4] + " | " +
                            board[b * 10 + 5] + " | " + board[b * 10 + 6] + " | " + board[b * 10 + 7] + " | " + board[b * 10 + 8] + " | " + board[b * 10 + 9] + " |");
                }
                System.out.println("--------------------------------------------------------------------------");


            }
        }


        outputBoard();

        //System.out.println(board[Integer.parseInt(groups[5][8])]);
    }

    public int clearRelations(int cellNumber) {
        // If a cell only has one possibility then mark that as the solution to that cell
        // and remove that number as a possibility from all related cells (same R/C/B)
        if ((board[cellNumber].length() == 1) && (relations[cellNumber][20] != "Cleared")) {
            for (int p = 0; p <= 19; p++) {
                board[Integer.parseInt(relations[cellNumber][p])] = board[Integer.parseInt(relations[cellNumber][p])].replace("" + board[cellNumber], "");
            }
            relations[cellNumber][20] = "Cleared";
            return 1;
        }
        return 0;
    }
    public boolean clearRelations() {
        // Check if every cell has had their immediate possibilities narrowed
        boolean bChanges = false;
        int iChanges;
        do {
            iChanges = 0;
            for (int x = 11; x <= 99; x++) {
                if (x % 10 != 0) {
                    iChanges += clearRelations(x);
                }
            }
            if (iChanges > 0) {
                bChanges = true;
            }
        } while (iChanges > 0);
        return bChanges;
    }
    public void singleGroup() {
        // If a number can only be in one cell in a R/C/B then assign that number to that cell
        boolean changes;
        do {
            changes = false;
            for (int p = 0; p <= 26; p++) {
                for (int q = 0; q <= 8; q++) {
                    for (int r = 1; r <= 9; r++) {
                        if (board[Integer.parseInt(groups[p][q])].contains("" + r) && board[Integer.parseInt(groups[p][q])].length() != 1) {
                            boolean onlyInstance = true;
                            for (int x = 0; x <= 8; x++) {
                                if (x != q) {
                                    if (board[Integer.parseInt(groups[p][x])].contains("" + r)) {
                                        onlyInstance = false;
                                        x = 8;
                                    }
                                }
                            }
                            if (onlyInstance) {
                                board[Integer.parseInt(groups[p][q])] = "" + r;
                                clearRelations(Integer.parseInt(groups[p][q]));
                                changes = true;
                            }
                        }
                    }
                }
            }
        } while (changes);
    }
    public boolean brcInteraction() {
        // If a number can only exist in a certain row or column, inside a box, then
        // remove that number as a possibility from the rest of the row or column
        boolean changes = false;
        for (int r = 1; r <= 9; r++) {
            for (int c = 11; c <= 99; c++) {
                if (c % 10 != 0) {
                    if (board[c].contains("" + r) && board[c].length() > 1) {
                        for (int p = 0; p <= 17; p++) {
                            for (int q = 0; q <= 8; q++) {
                                if (groups[p][q].contains("" + c)) {
                                    for (int s = 18; s <= 26; s++) {
                                        for (int t = 0; t <= 8; t++) {
                                            if (groups[s][t] == groups[p][q]) {
                                                boolean rcOnly = true;
                                                for (int u = 0; u <= 8; u++) {
                                                    if (board[Integer.parseInt(groups[s][u])].contains("" + r)) {
                                                        if (p < 9 && t / 3 != u / 3) {
                                                            rcOnly = false;
                                                            u = 8;
                                                        } else if (p > 8 && t % 3 != u % 3) {
                                                            rcOnly = false;
                                                            u = 8;
                                                        }
                                                    }
                                                }
                                                if (rcOnly) {
                                                    String callEm = "";
                                                    for (int w = 0; w <= 8; w++) {
                                                        if ((q / 3 != w / 3) && board[Integer.parseInt(groups[p][w])].contains("" + r)) {
                                                            callEm += groups[p][w] + ", ";
                                                            board[Integer.parseInt(groups[p][w])] = board[Integer.parseInt(groups[p][w])].replace("" + r, "");
                                                            changes = true;
                                                        }
                                                    }
                                                    if (callEm.length() > 4) {
                                                        callEm = callEm.substring(0, callEm.length() - 2);
                                                        System.out.println("In " + convertRCBToText(s) + " the number " + r + " can only be in " + convertRCBToText(p) + ", so remove " + r + " as a possibility from cells " + callEm);
                                                    } else if (callEm.length() > 1){
                                                        callEm = callEm.substring(0, callEm.length() - 2);
                                                        System.out.println("In " + convertRCBToText(s) + " the number " + r + " can only be in " + convertRCBToText(p) + ", so remove " + r + " as a possibility from cell " + callEm);
                                                    }
                                                }
                                                s = 26;
                                                t = 8;
                                            }
                                        }
                                    }
                                    if (p <= 8) { p = 8; } else { p = 17; }
                                    q = 8;
                                }
                            }
                        }
                    }
                }
            }
        }
        return changes;
    }


    public void solveBoard() {
        groupsDecs();
        inputBoard();
        assignDefaultValues();





        boolean test1 = false;
        boolean test2 = false;
        do {
            sysOutPrintBoard();



            test1 = clearRelations();
            singleGroup();
            test2 = brcInteraction();




        } while (test1 || test2);










        sysOutPrintBoard();



        outputBoard();
    }



    public void step3() {
        //output board into string
        //        ---------------------------------------------------------------------------------
        //  Easy  .......7.....5.8.1..641..356.7...52....2.9....41...6.997..214..1.5.3.....8.......
        //  Intr  1.7.5....3...9..12...3.....4....6.....6....9...2.1.7562.1.......6...9.......843..
        //  IntF  13.4..2.........6.7...621....3......59...1..8...6...94.....7..3.1..95....2...6...
        //  Hard  .......28..7.8.5..9...7.4..4...68......1.....5.32....4......2...25...18....5.6.9.

        inputBoard();
        sysOutPrintBoard();
        String boardString = "";
        for (int x = 11; x <= 99; x++) {
            if (x % 10 != 0) {
                if (board[x].length() > 0) { boardString += board[x]; } else { boardString += "."; }
            }
        }
        System.out.println(boardString);

    }


}
