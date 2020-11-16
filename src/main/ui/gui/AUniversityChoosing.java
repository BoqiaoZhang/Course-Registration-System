package ui.gui;

import model.University;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AUniversityChoosing extends JFrame implements ActionListener {
    private University uni;
    private JLabel questionAboutUniversity;
    private JTextField txtUniversity;
    private JLabel lblUniversity;
    private JButton btnOK;
    private JButton btnExit;

    public AUniversityChoosing() {
        init();
    }

    public void init() {
        questionAboutUniversity = new JLabel("Which university are you from?");
        questionAboutUniversity.setBounds(20,20,300,20);
        lblUniversity = new JLabel("University:");
        lblUniversity.setBounds(20,40,100,20);

        setLayout(null);

        txtUniversity = new JTextField(15);
        txtUniversity.setBounds(100,40,100,20);

        initializeEnterAndExistButtons();

        addComponents();

        setBounds(1,1,500,600);
        setTitle("GUIdesign");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addComponents() {
        add(questionAboutUniversity);
        add(lblUniversity);
        add(txtUniversity);
        add(btnOK);
        add(btnExit);
    }

    public static void main(String[] args) {
        new AUniversityChoosing();
    }

    //EFFECTS: Default method with nothing to do
    @Override
    public void actionPerformed(ActionEvent e) {
        //do nothing
    }

    public void initializeEnterAndExistButtons() {
        btnOK = new JButton("Enter");
        btnExit = new JButton("Exit");
        btnOK.setBounds(60,100,60,20);
        btnExit.setBounds(125,100,60,20);

        btnOK.addActionListener(new ActionListener() {
            //EFFECTS: go to the statusChoosing window and close this window
            @Override
            public void actionPerformed(ActionEvent e) {
                uni = new University(txtUniversity.getText());
                new BStatusChoosing(uni);
                setVisible(false);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            //EFFECTS:Stop the whole system when clicking "exist"
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login out successfully");
                System.exit(0);
            }
        });
    }
}