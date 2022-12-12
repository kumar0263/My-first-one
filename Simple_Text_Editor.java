import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class Simple_Text_Editor implements ActionListener {
    JFrame frame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu File;
    JMenu Edit;
    JMenu Close;
    JMenuItem NewFile;
    JMenuItem OpenFile;
    JMenuItem SaveFile;
    JMenuItem PrintFile;
    JMenuItem cut;
    JMenuItem copy;
    JMenuItem paste;
    JMenuItem closeEditor;
    Simple_Text_Editor(){
        //creating the frame where everything gets displayed
        frame = new JFrame("Notepad pro");
        //setting up to which extent it should be visible
        frame.setBounds(0,0,800,1000);
        //Area where actual operation like writing text is done
        jTextArea = new JTextArea("Welcome to the editor");
        //creating menu bar where every overview of specific task there
        jMenuBar = new JMenuBar();
        File = new JMenu("File");
        Edit = new JMenu("Edit");
        Close = new JMenu("Close");

        jMenuBar.add(File);
        jMenuBar.add(Edit);
        jMenuBar.add(Close);


        OpenFile = new JMenuItem("Open");
        OpenFile.addActionListener(this);

        SaveFile = new JMenuItem("Save");
        SaveFile.addActionListener(this);

        NewFile = new JMenuItem("New");
        NewFile.addActionListener(this);

        PrintFile = new JMenuItem("Print");
        PrintFile.addActionListener(this);

        //adding menu items to File menu
        File.add(NewFile);
        File.add(OpenFile);
        File.add(SaveFile);
        File.add(PrintFile);


        copy = new JMenuItem("Copy");
        copy.addActionListener(this);

        cut = new JMenuItem("Cut");
        cut.addActionListener(this);

        paste = new JMenuItem("Paste");
        paste.addActionListener(this);

        //adding menu items to Edit menu
        Edit.add(copy);
        Edit.add(cut);
        Edit.add(paste);


        closeEditor = new JMenuItem("Close");
        closeEditor.addActionListener(this);

        Close.add(closeEditor);


        frame.setJMenuBar(jMenuBar);
        frame.add(jTextArea);
        //program will stop after closing the editor
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Simple_Text_Editor editor = new Simple_Text_Editor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("Copy")){
            jTextArea.copy();
        }else if(s.equals("Cut")){
            jTextArea.cut();
        }else if(s.equals("Paste")){
            jTextArea.paste();
        }else if(s.equals("Print")){
            try {
                jTextArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }else if(s.equals("New")){
            jTextArea.setText("");
        }else if(s.equals("Close")){
            frame.setVisible(false);
        }else if(s.equals("Open")){
            JFileChooser jFileChooser = new JFileChooser("C:");

            int ans = jFileChooser.showOpenDialog(null);
            if(ans == JFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1="",s2 = "";
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while((s1= bufferedReader.readLine())!=null){
                        s2 += s1+"\n";
                    }
                    jTextArea.setText(s2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if(s.equals("Save")){
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file,false));
                    writer.write((jTextArea.getText()));
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
