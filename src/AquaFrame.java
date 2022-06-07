import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class AquaFrame extends JFrame {

    AquaPanel aquarium;

    public AquaFrame()
    {
        aquarium = new AquaPanel();
        var aquariumThread = new Thread(aquarium);
        JFrame frame = new JFrame("Menu");
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(1200, 700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menu = new JMenuBar();
        frame.setJMenuBar(menu);

        JMenu file = new JMenu("File");
        menu.add(file);
        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);

        JMenuItem image = new JMenuItem("Image");
        JMenuItem blue = new JMenuItem("Blue");
        JMenuItem none = new JMenuItem("None");
        JMenu background = new JMenu("Background");
        menu.add(background);

        background.add(image);
        background.add(blue);
        background.add(none);

        JMenu help = new JMenu("Help");
        menu.add(help);

        JMenuItem helpItem = new JMenuItem("Help");
        help.add(helpItem);

        helpItem.addActionListener(e -> JOptionPane.showMessageDialog(null, " Home Work 3 \n GUI @ Threads"));

        BorderLayout border = new BorderLayout();
        frame.setLayout(border);

        frame.add(aquarium, BorderLayout.SOUTH);
        frame.pack();

        exit.addActionListener(e -> System.exit(0));
        // blue background
        blue.addActionListener(e -> aquarium.changeBackground("blue"));

        // Returning the background to white by pressing "None" item
        none.addActionListener(e -> aquarium.changeBackground("white"));

        image.addActionListener(e -> aquarium.changeBackground("image"));

        aquariumThread.start();

    }


    public static void main(String[] args)
    {
        new AquaFrame();
    }

}
