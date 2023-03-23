package bmurki.finalproject.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class UserPage extends JFrame{
    private JPanel Home;
    private JLabel Username;
    private JTextField usernameText;
    private JButton SubmitButton;
    private JLabel ageUser;
    private JSpinner spinner1;
    private Container contentPane;
    private void initValues()
    {
        usernameText=null;
        Home=null;
        Username=null;
        SubmitButton=null;
        contentPane=null;
    }

    public UserPage() {
        super("TopQuiz");
        initValues();

        contentPane=getContentPane();
        contentPane.setBackground(new Color(255, 255, 255));

        //FRAME properties
        //set default size and minimum size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setMinimumSize(new Dimension(300,300));
        //center the frame on screen
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Username = new JLabel("Username");
        Username.setFont(new Font("Times New Roman", Font.BOLD, 35));
        Username.setAlignmentX(CENTER_ALIGNMENT);

        usernameText = new JTextField(20);
        usernameText.setFont(new Font("Times New Roman", Font.BOLD, 35));
        usernameText.setAlignmentX(CENTER_ALIGNMENT);
        usernameText.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JTextField feild = (JTextField) input;
                if(feild.getText().trim().length() == 0){
                    JOptionPane.showMessageDialog(null, "Please enter a name");
                    return false;
                }else {
                    return true;
                }
            }
        });

        ageUser = new JLabel("Age");

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

//                System.out.println(name2);

            } catch (Exception exp) {
                exp.printStackTrace();
            }

            dispose();
            new MainFrameUI();
        }
    });
}
}