import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class Main implements WindowListener,KeyListener {
    static JFrame frame = new JFrame();

    static TextArea InstanceTextArea = new TextArea();
    static JPanel panel = new JPanel();
    static TextArea TextWindow;
    static String FileName = "file12.txt";
    static String PrevText;
    static JFrame saveframe = new JFrame();
    static Main Handle = new Main();
        static String SelectedFont;
        static int SelectedSize,SizeFont=20;
        static String SelectedStyle;
            // Default Fonts
            static Font[] AppFonts = new Font[3];
        static JMenuBar Menubar  = new JMenuBar();
            static JMenuItem NewWindow  = new JMenuItem("New Window");

            static JMenuItem Save  = new JMenuItem("Save");
            static JMenuItem Exit = new JMenuItem("Exit");


            static JMenuItem AboutTextWin = new JMenuItem("About TextWin");
            static JMenuItem AboutUs = new JMenuItem("About Us");



    void InitializeFrame(JFrame Winframe, TextArea TextWin)
    {
        TextWindow  =new TextArea("",0,0,TextArea.SCROLLBARS_BOTH);
        panel = new JPanel();
                // INITIALIZING FONTS ATTRIBUTES
                AppFonts[0] = new Font(SelectedFont,Font.PLAIN,SelectedSize);
                AppFonts[1] = new Font(SelectedFont,Font.BOLD,SelectedSize);
                AppFonts[2] = new Font(SelectedFont,Font.ITALIC,SelectedSize);
        Winframe.setLayout(null);
        panel.setLayout(null);
        Winframe.setBounds(0,0,1024,720);
        panel.setBounds(0,0,1024,720);
        Winframe.add(panel);
        Winframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TextWindow.setBounds(0,0,1000,655);
        TextWindow.setFont(new Font("Arial",Font.PLAIN,SizeFont));
        TextWindow.setText("");

         panel.add(TextWindow);
        Winframe.setTitle("TextWin");
        Winframe.setVisible(true);
        TextWindow.addKeyListener(this);
    }
    static void AddMenu()
    {

        JMenu File = new JMenu("File");
                Save.addActionListener(e -> {
                    try {

                        Handle.SaveDoc();
                        Save.setEnabled(false);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                Exit.addActionListener(e->{System.exit(0);});

                File.add(NewWindow);
                File.add(Save);
                File.add(Exit);
        JMenu About = new JMenu("About");
                About.add(AboutTextWin);
                About.add(AboutUs);
        Menubar.add(File);
        Menubar.add(About);
        Menubar.setBounds(0,0,1024,30);
        frame.setJMenuBar(Menubar);

    }
    void SaveDoc()throws IOException
    {

            saveframe.setTitle("Save File");
            saveframe.setVisible(true);
            saveframe.setLayout(null);
            saveframe.setBounds(0,0,250,100);
            saveframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            saveframe.addWindowListener(this);

        JTextField SaveFileName = new JTextField();
            SaveFileName.setBounds(15,5,200,20);
            saveframe.add(SaveFileName);
            saveframe.setResizable(false);
            saveframe.setAlwaysOnTop(true);


        JButton Save = new JButton("Save");
            Save.setFocusable(false);
            Save.setBorder(BorderFactory.createEtchedBorder(Color.black,Color.WHITE));
            Save.setBounds(10,30,70,20);
            saveframe.add(Save);


       File OutFile = new File(FileName);
        FileWriter Write = new FileWriter(OutFile);

            Save.addActionListener(e -> {
                try {
                    PrevText = TextWindow.getText();
                    Write.append(TextWindow.getText());
                    System.out.println("Text is "+TextWindow.getText());
                    Write.close();
                    TextWindow.setText(PrevText);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
    }

    public static void main(String[] args)
    {
        AddMenu();
        Handle.InitializeFrame(frame,TextWindow);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        if(e.getSource()==saveframe)
        {
            Save.setEnabled(true);
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()=='+'&&e.isControlDown())
        {
            SizeFont+=5;
            TextWindow.setFont(new Font("Arial",Font.PLAIN,SizeFont));

        }
        else if(e.getKeyChar()=='-'&&e.isControlDown())
        {
            SizeFont-=5;
            TextWindow.setFont(new Font("Arial",Font.PLAIN,SizeFont));

        }
        else if(e.getKeyChar()=='2'&&e.isControlDown())
        {
            TextWindow.setFont(new Font("Arial",Font.BOLD,SizeFont));

        }
        else if(e.getKeyChar()=='3'&&e.isControlDown())
        {
            TextWindow.setFont(new Font("Arial",Font.ITALIC,SizeFont));

        }
        else if(e.getKeyChar()=='1'&&e.isControlDown())
        {
            TextWindow.setFont(new Font("Arial",Font.PLAIN,SizeFont));
            System.out.println("Pressed");
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
