package org.example;

import javax.swing.*;
import java.io.File;

/**
 * 文件选择框
 */
public class Main {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(null);
        File selectedFile = fileChooser.getSelectedFile();
        System.out.println(selectedFile);
        System.out.println();
        if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("用户取消了选择");
        }
    }
}