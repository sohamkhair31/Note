import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main implements WindowListener {
    static JFrame frame = new JFrame();
    static JPanel panel = new JPanel();
    static TextArea TextWindow;
    static String FileName = "file12.txt";
    static String PrevText;
    static JFrame saveframe = new JFrame();
    static Main Handle = new Main();
        static String SelectedFont;
        static int SelectedSize;
        static String SelectedStyle;
            // Default Fonts
            static Font[] AppFonts = new Font[3];
        static JMenuBar Menubar  = new JMenuBar();
            static JMenuItem NewWindow  = new JMenuItem("New Window");
            static JMenuItem Save  = new JMenuItem("Save");
            static JMenuItem Exit = new JMenuItem("Exit");

            static  JMenuItem TextSetting = new JMenuItem("Text Settings");

            static JMenuItem AboutTextWin = new JMenuItem("About TextWin");
            static JMenuItem AboutUs = new JMenuItem("About Us");



    static void InitializeFrame()
    {
                // INITIALIZING FONTS ATTRIBUTES
                AppFonts[0] = new Font(SelectedFont,Font.PLAIN,SelectedSize);
                AppFonts[1] = new Font(SelectedFont,Font.BOLD,SelectedSize);
                AppFonts[2] = new Font(SelectedFont,Font.ITALIC,SelectedSize);
        TextWindow  =new TextArea("",0,0,TextArea.SCROLLBARS_BOTH);
        frame.setLayout(null);
        panel.setLayout(null);
        frame.setBounds(0,0,1024,720);
        panel.setBounds(0,0,1024,720);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TextWindow.setBounds(0,0,1000,655);
        TextWindow.setText("");

         panel.add(TextWindow);
         frame.setTitle("NoteWin");
        frame.setVisible(true);
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
        JMenu Text = new JMenu("Text");
                Text.add(TextSetting);
                TextSetting.addActionListener(e->{Handle.ApplyTextSettings();});
        JMenu About = new JMenu("About");
                About.add(AboutTextWin);
                About.add(AboutUs);
        Menubar.add(File);
        Menubar.add(Text);
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

                    Write.write(TextWindow.getText());
                    System.out.println("Text is "+TextWindow.getText());
                    Write.close();
                    TextWindow.setText(PrevText);
                } catch (IOException ex) {
                    System.out.println("Stream is Closed");
                }
            });
    }

    void ApplyTextSettings()
    {
        JLabel TextSize = new JLabel("Text Size");
        JLabel TextFont = new JLabel("Text Font");
        JLabel TextStyle = new JLabel("Text Style");
        JComboBox ComboSize = new JComboBox();
            ComboSize.addItem(3);ComboSize.addItem(4);
            ComboSize.addItem(15);ComboSize.addItem(22);
            ComboSize.setSelectedItem(4);
        JComboBox ComboFont = new JComboBox();
            ComboFont.addItem("Arial");ComboFont.addItem("Calibri");
            ComboFont.addItem("Serif");ComboFont.addItem("Times New Roman");
            ComboFont.setSelectedItem(0);
        JComboBox ComboStyle = new JComboBox();
            ComboStyle.addItem("Simple");ComboStyle.addItem("Italic");
            ComboStyle.addItem("Bold");
            ComboStyle.setSelectedItem(0);
        JButton Apply = new JButton("Apply");

        JFrame SettingWindow = new JFrame();
        SettingWindow.setBounds(0,0,300,150);
        SettingWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        SettingWindow.setLayout(null);
        SettingWindow.setTitle("Text Settings");
            TextSize.setBounds(20,10,100,20);
            ComboSize.setBounds(80,10,100,20);
                SettingWindow.add(ComboSize);
                SettingWindow.add(TextSize);
            TextFont.setBounds(20,40,100,20);
            ComboFont.setBounds(80,40,100,20);
                SettingWindow.add(ComboFont);
                SettingWindow.add(TextFont);
            TextStyle.setBounds(20,70,100,20);
            ComboStyle.setBounds(80,70,100,20);
                SettingWindow.add(TextStyle);
                SettingWindow.add(ComboStyle);
            Apply.setBounds(200,50,50,20);
            Apply.setBackground(Color.WHITE);
            Apply.setForeground(Color.BLACK);
            Apply.setBorder(BorderFactory.createEtchedBorder(Color.black,Color.WHITE));
        SettingWindow.add(Apply);
        SettingWindow.setVisible(true);
        SettingWindow.setResizable(false);
        SettingWindow.setAlwaysOnTop(true);
        SelectedSize = (int)ComboSize.getSelectedItem();
            ComboSize.addActionListener(e->{
                TextWindow.setFont(new Font("Arial",Font.BOLD,SelectedSize));
            });
            ComboStyle.addActionListener(e->{SelectedStyle=(String)ComboStyle.getSelectedItem();});
            ComboFont.addActionListener(e->{SelectedFont=(String)ComboFont.getSelectedItem();});
            Apply.addActionListener(e->{
                switch (ComboStyle.getSelectedIndex())
                {
                    case 0:
                        TextWindow.setFont(AppFonts[0]);
                        System.out.println(TextFont.getFont());
                        break;
                    case 1:
                        TextWindow.setFont(AppFonts[1]);
                        System.out.println(TextWindow.getFont());
                        break;
                    case 2:
                        frame.setFont(AppFonts[2]);
                        break;
                    default:
                        break;
                }
            });
    }
    public static void main(String[] args)
    {


        AddMenu();
        InitializeFrame();
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
}
