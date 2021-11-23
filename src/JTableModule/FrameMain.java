package JTableModule;

import Utils.ArrayUtils;
import Utils.JTableUtils;
import Utils.SwingUtils;
import com.Logic;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTable tableInput;
    private JButton buttonLoadInputFromFile;
    private JButton buttonRandomInput;
    private JButton buttonSaveInputIntoFile;
    private JButton buttonSaveOutputIntoFile;
    private JButton buttonGetResult;
    private JTextField textFieldOutput;

    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;

    public FrameMain() {
        this.setTitle("Task8_22");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelMain);
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 40, true, true, true, true);
        JTableUtils.resizeJTable(tableInput, 0,0, 30, 30);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        buttonLoadInputFromFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] arr = ArrayUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                    Logic.checkIfArrayIsNull(arr);
                    Logic.checkIfArrayIsEmpty(arr);
                    Logic.checkIfArrayIsRectangle(arr);
                    JTableUtils.writeArrayToJTable(tableInput, arr);
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        buttonRandomInput.addActionListener(actionEvent -> {
            try {
                int[][] matrix = ArrayUtils.createRandomIntMatrix(
                        tableInput.getRowCount(), tableInput.getColumnCount(), -1, 2);
                JTableUtils.writeArrayToJTable(tableInput, matrix);
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        buttonSaveInputIntoFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableInput);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    ArrayUtils.writeArrayToFile(file, matrix);
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });


        buttonSaveOutputIntoFile.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int res = Integer.parseInt(textFieldOutput.getText());
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    Logic.saveOutputIntoFile(file, res);
                }
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox(ex);
            }
        });

        buttonGetResult.addActionListener(e -> {
            try{
                int[][] arr = JTableUtils.readIntMatrixFromJTable(tableInput);
                JTableUtils.writeArrayToJTable(tableInput, arr);
                Logic.checkIfArrayIsEmpty(arr);
                Logic.checkIfArrayIsRectangle(arr);
                int result = Logic.resultOfTheMatch(arr);
                textFieldOutput.setText(Integer.toString(result));
            } catch(Exception ex){
                SwingUtils.showErrorMessageBox(ex);
            }
        });
    }
}
