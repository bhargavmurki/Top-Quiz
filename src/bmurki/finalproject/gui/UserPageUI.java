package bmurki.finalproject.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Class that creates the UserPageUI
public class UserPageUI extends JFrame{
    // Declare instance variables
    private JPanel Home;
    private JLabel Username;
    private JTextField usernameText;
    private JButton SubmitButton;
    private JLabel ageUser;
    private JSpinner spinner1;
    private Container contentPane;

    // Method to initialize the values of the instance variables
    private void initValues()
    {
        usernameText=null;
        Home=null;
        Username=null;
        SubmitButton=null;
        contentPane=null;
    }

    // Constructor for UserPageUI
    public UserPageUI() {
        // Call the superclass constructor and set the title of the frame
        super("TopQuiz");
        // Call the initValues method
        initValues();

        // Get the content pane and set its background color
        contentPane=getContentPane();
        contentPane.setBackground(new Color(255, 255, 255));

        // FRAME properties
        // set default size and minimum size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setMinimumSize(new Dimension(300,300));
        // center the frame on screen
        setLocationRelativeTo(null);

        // Set the default close operation and make the frame visible
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Create the label for the username
        Username = new JLabel("Username");
        // Set font and alignment for the label
        Username.setFont(new Font("Times New Roman", Font.BOLD, 35));
        Username.setAlignmentX(CENTER_ALIGNMENT);

        // Create the textfield for the username
        usernameText = new JTextField(20);
        // Set font and alignment for the textfield
        usernameText.setFont(new Font("Times New Roman", Font.BOLD, 35));
        usernameText.setAlignmentX(CENTER_ALIGNMENT);
        // Add input verification to the textfield
        usernameText.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JTextField feild = (JTextField) input;
                // Check if the textfield is empty
                if(feild.getText().trim().length() == 0){
                    // Show an error message if the textfield is empty
                    JOptionPane.showMessageDialog(null, "Enter a name");
                    return false;
                }else {
                    return true;
                }
            }
        });

        // Create the label for the age
        ageUser = new JLabel("Age");

        // Create the spinner for the age
        SpinnerNumberModel ageSpinnerModel = new SpinnerNumberModel(5, 5, 10, 1);
        spinner1 = new JSpinner(ageSpinnerModel);
        spinner1.setValue(6);
        JFormattedTextField ageTextField = ((JSpinner.DefaultEditor) spinner1.getEditor()).getTextField();
        ageTextField.setEditable(false);

        SubmitButton = new JButton("Submit");
        SubmitButton.setFont(new Font("Times New Roman", Font.BOLD, 35));
        SubmitButton.setAlignmentX(CENTER_ALIGNMENT);

        Home = new JPanel();
//        Home.setLayout(new GridLayout(3,2));
        Home.add(Username);
        Home.add(usernameText);
        Home.add(ageUser);
        Home.add(spinner1);


        Home.add(SubmitButton);
        setContentPane(Home);


    SubmitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = usernameText.getText();
            String age = spinner1.getValue().toString();
            System.out.println(name + " " + age);

            FileOutputStream out = null;
            FileInputStream in = null;
            try {
                out = new FileOutputStream("./currentuser.txt");
                ObjectOutputStream obj = new ObjectOutputStream(out);
                obj.writeObject(name);
                obj.flush();
                obj.close();
                out.close();

                in = new FileInputStream("./currentuser.txt");
                ObjectInputStream obj2 = new ObjectInputStream(in);
                String name2 = (String) obj2.readObject();
            } catch (Exception exp) {
                exp.printStackTrace();
            }

            dispose();
            new MainFrameUI();
        }
    });
}
}