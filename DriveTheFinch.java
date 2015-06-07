package MyPrograms;

import RSSReaders.WeatherReader;
import edu.cmu.ri.createlab.TeRK.userinterface.ImagePanel;
import finch.Finch;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author Ajit Sutreja
 */
public class DriveTheFinch extends JFrame {
    //Menu GUI

    private JMenuBar menuBar;
    private JMenu fileMenu, weatherMenu;
    private JMenuItem closeProgram, colorChoice, city1, city2, city3, city4,
            city5;
    //Label and Slider GUI
    private JPanel mainPanel, panel1, panel2, panel3, panel4, panel5;
    private JSlider lwvSlider, rwvSlider, rSlider, gSlider, bSlider;
    private JLabel label1, label2, label3, label4, label5;
    //WeatherReader
    private WeatherReader wr;
    //Picture
    ImagePanel panel = new ImagePanel(new ImageIcon("tree.png").getImage());
    Finch saphira = new Finch();

    public DriveTheFinch() {
        super("Drive the Finch");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MenuPanels();
        BuildPanels();

        add(mainPanel);

        pack();
        setVisible(true);
    }

    private void MenuPanels() {
        // Create the menu bar.
        menuBar = new JMenuBar();

        // Create the file menu.
        buildFileMenu();
        buildWeatherMenu();

        // Add the file menu to the menu bar.
        menuBar.add(fileMenu);
        menuBar.add(weatherMenu);

        // Set the window's menu bar.
        setJMenuBar(menuBar);
    }

    private void buildFileMenu() {
        colorChoice = new JMenuItem("Color Choice");
        colorChoice.addActionListener(new FileMenuListener());

        // Create an Exit menu item.
        closeProgram = new JMenuItem("Close Program");
        closeProgram.addActionListener(new FileMenuListener());

        // Create a JMenu object for the File menu.
        fileMenu = new JMenu("File");

        // Add the Exit menu item to the File menu.
        fileMenu.add(colorChoice);
        fileMenu.add(closeProgram);
    }

    private void buildWeatherMenu() {
        // Create an Exit menu item.
        city1 = new JMenuItem("Pittsburgh, PA");
        city1.addActionListener(new CityListener());

        city2 = new JMenuItem("Nashville, TN");
        city2.addActionListener(new CityListener());

        city3 = new JMenuItem("Austin, TX");
        city3.addActionListener(new CityListener());

        city4 = new JMenuItem("Sacramento, CA");
        city4.addActionListener(new CityListener());

        city5 = new JMenuItem("Helena, MT");
        city5.addActionListener(new CityListener());

        // Create a JMenu object for the File menu.
        weatherMenu = new JMenu("Weather Report");

        // Add the Exit menu item to the File menu.
        weatherMenu.add(city1);
        weatherMenu.add(city2);
        weatherMenu.add(city3);
        weatherMenu.add(city4);
        weatherMenu.add(city5);
    }

    private void BuildPanels() {
        panel1 = new JPanel(new GridLayout(2, 1));
        panel1.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
        label1 = new JLabel("Left Wheel Velocity", JLabel.CENTER);
        lwvSlider = new JSlider(JSlider.HORIZONTAL, -35, 35, 0);
        lwvSlider.setMajorTickSpacing(10);
        lwvSlider.setMinorTickSpacing(0);
        lwvSlider.setPaintTicks(true);
        lwvSlider.setPaintLabels(true);
        lwvSlider.addChangeListener(new SliderListener());

        panel2 = new JPanel(new GridLayout(2, 1));
        panel2.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
        label2 = new JLabel("Right Wheel Velocity", JLabel.CENTER);
        rwvSlider = new JSlider(JSlider.HORIZONTAL, -35, 35, 0);
        rwvSlider.setMajorTickSpacing(10);
        rwvSlider.setMinorTickSpacing(0);
        rwvSlider.setPaintTicks(true);
        rwvSlider.setPaintLabels(true);
        rwvSlider.addChangeListener(new SliderListener());

        panel3 = new JPanel(new GridLayout(2, 1));
        panel3.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
        label3 = new JLabel("Red", JLabel.CENTER);
        label3.setForeground(Color.red);
        rSlider = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);
        rSlider.setMajorTickSpacing(50);
        rSlider.setMinorTickSpacing(10);
        rSlider.setPaintTicks(true);
        rSlider.setPaintLabels(true);
        rSlider.addChangeListener(new SliderListener());

        panel4 = new JPanel(new GridLayout(2, 1));
        panel4.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
        label4 = new JLabel("Green", JLabel.CENTER);
        label4.setForeground(Color.green);
        gSlider = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);
        gSlider.setMajorTickSpacing(50);
        gSlider.setMinorTickSpacing(10);
        gSlider.setPaintTicks(true);
        gSlider.setPaintLabels(true);
        gSlider.addChangeListener(new SliderListener());

        panel5 = new JPanel(new GridLayout(2, 1));
        panel5.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
        label5 = new JLabel("Blue", JLabel.CENTER);
        label5.setForeground(Color.blue);
        bSlider = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);
        bSlider.setMajorTickSpacing(50);
        bSlider.setMinorTickSpacing(10);
        bSlider.setPaintTicks(true);
        bSlider.setPaintLabels(true);
        bSlider.addChangeListener(new SliderListener());



        panel1.add(label1);
        panel1.add(lwvSlider);

        panel2.add(label2);
        panel2.add(rwvSlider);

        panel3.add(label3);
        panel3.add(rSlider);

        panel4.add(label4);
        panel4.add(gSlider);

        panel5.add(label5);
        panel5.add(bSlider);

        mainPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(5, 1));
        //mainPanel.add(panel);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);
        mainPanel.add(panel5);
    }

    private class SliderListener implements ChangeListener {

        public void stateChanged(ChangeEvent e) {
            int left, right, red, green, blue;

            left = lwvSlider.getValue();
            right = rwvSlider.getValue();
            red = rSlider.getValue();
            green = gSlider.getValue();
            blue = bSlider.getValue();

            saphira.setWheelVelocities(left, right);
            saphira.setLED(red, green, blue);
        }
    }

    private class FileMenuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == colorChoice) {
                Color selectedColor;
                selectedColor = JColorChooser.showDialog(null, "Select a Color", Color.BLUE);
                saphira.setLED(selectedColor);
            }
            if (e.getSource() == closeProgram) {
                int repeat;

                repeat = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to quit?", "Please Confirm", JOptionPane.YES_NO_OPTION);
                if (repeat == JOptionPane.YES_OPTION) {
                    saphira.quit();
                    System.exit(0);
                }
            }

        }
    }

    private class CityListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == city1) {
                wr = new WeatherReader("Pittsburgh, PA");
                saphira.saySomething("The current temperature in Pittsburgh, PA is"
                        + wr.getTemperature() + "degrees");
            }
            if (e.getSource() == city2) {
                wr = new WeatherReader("Nashville, TN");
                saphira.saySomething("The current temperature in Nashville, TN is"
                        + wr.getTemperature() + "degrees");
            }
            if (e.getSource() == city3) {
                wr = new WeatherReader("Austin, TX");
                saphira.saySomething("The current temperature in Austin, TX is"
                        + wr.getTemperature() + "degrees");
            }
            if (e.getSource() == city4) {
                wr = new WeatherReader("Sacramento, CA");
                saphira.saySomething("The current temperature in Sacramento, CA is"
                        + wr.getTemperature() + "degrees");
            }
            if (e.getSource() == city5) {
                wr = new WeatherReader("Helena, MT");
                saphira.saySomething("The current temperature in Helena, MT is"
                        + wr.getTemperature() + "degrees");
            }
        }
    }

    public static void main(String[] args) {
        new DriveTheFinch();
    }
}
