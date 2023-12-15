package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
public class SwingControlDemo {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JProgressBar progressBar;
    private JTextArea textArea1;

    public SwingControlDemo(){
        prepareGUI();
    }
    public static void main(String[] args){
        SwingControlDemo swingControlDemo = new SwingControlDemo();
        swingControlDemo.showEventDemo();
    }
    private void prepareGUI(){
        mainFrame = new JFrame("Java SWING Example");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.setBackground(Color.green);

        headerLabel = new JLabel("",JLabel.CENTER );
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }
    private void showEventDemo(){
        headerLabel.setText("Control in action: Button");

        JButton okButton = new JButton("OK");
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        JButton chooseButton = new JButton("Choose Background");
        JCheckBox checkBox = new JCheckBox("Wine");
        JRadioButton radioButton = new JRadioButton("Steak");
        DefaultListModel fruits = new DefaultListModel();
        fruits.addElement("Apple");
        fruits.addElement("Grapes");
        fruits.addElement("Mango");
        JList fruitsList = new JList(fruits);
        fruitsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fruitsList.setSelectedIndex(0);
        fruitsList.setVisibleRowCount(3);
        JScrollPane fruitsListScrollPane = new JScrollPane(fruitsList);
        DefaultComboBoxModel fruitsName = new DefaultComboBoxModel();
        fruitsName.addElement("Apples");
        fruitsName.addElement("Grapes");
        fruitsName.addElement("Mango");
        fruitsName.addElement("Peer");
        JComboBox fruitCombo = new JComboBox(fruitsName);
        fruitCombo.setSelectedIndex(0);
        JScrollPane fruitsComboScrollPane = new JScrollPane(fruitCombo);
        JTextField userText = new JTextField(6);
        JPasswordField passwordField = new JPasswordField(6);
        JTextArea textArea = new JTextArea(5,10);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JButton optionButton = new JButton("Option");
        final JFileChooser fileChooser = new JFileChooser();
        JButton showFile = new JButton("Open File");
        progressBar = new JProgressBar(0,100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        JButton startButton = new JButton("Start");
        textArea1 = new JTextArea("",5,20);
        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        JSlider slider = new JSlider(JSlider.HORIZONTAL,0,100,1);
        SpinnerModel spinnerModel = new SpinnerNumberModel(10, 0, 100, 1);
        JSpinner spinner = new JSpinner(spinnerModel);

        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                statusLabel.setText("Value: " + ((JSpinner)e.getSource()).getValue());
            }
        });

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                statusLabel.setText("Value: " + ((JSlider)e.getSource()).getValue());
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task task = new Task();
                task.start();
            }
        });


        showFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(mainFrame);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    java.io.File file = fileChooser.getSelectedFile();
                    statusLabel.setText("File selected: " + file.getName());
                }
                else statusLabel.setText("Command cancelled by user");
            }
        });

        optionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int output = JOptionPane.showConfirmDialog(mainFrame,"Click any button","Option Pop-Up",JOptionPane.YES_NO_CANCEL_OPTION);
                if(output == JOptionPane.YES_OPTION){
                    statusLabel.setText("Pressed Yes");
                } else if (output == JOptionPane.NO_OPTION) {
                    statusLabel.setText("Pressed No");
                }
                else if(output == JOptionPane.CANCEL_OPTION) statusLabel.setText("Pressed Cancel");
            }
        });

        chooseButton.addActionListener(e -> {
            Color backgroundColor = JColorChooser.showDialog(mainFrame,"Choose background color", Color.white);
            if(backgroundColor != null){
                controlPanel.setBackground(backgroundColor);
                mainFrame.getContentPane().setBackground(backgroundColor);
            }
        });



        controlPanel.add(checkBox);
        controlPanel.add(radioButton);
        controlPanel.add(fruitsListScrollPane);
        controlPanel.add(fruitsComboScrollPane);
        controlPanel.add(chooseButton);
        controlPanel.add(userText);
        controlPanel.add(scrollPane);
        controlPanel.add(passwordField);
        controlPanel.add(optionButton);
        controlPanel.add(showFile);
        controlPanel.add(startButton);
        controlPanel.add(progressBar);
        controlPanel.add(scrollPane1);
        controlPanel.add(slider);
        controlPanel.add(spinner);
        mainFrame.setVisible(true);
    }
    private class Task extends Thread{
        public Task(){}
        public void run(){
            for(int i = 0; i<= 100; i+=10){
                final int progress = i;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setValue(progress);
                        textArea1.setText(textArea1.getText() + String.format("Completed %d%% of task. \n",progress));
                    }
                });
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){}
            }
        }
    }
}