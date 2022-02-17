package com.company;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MusicPlauer class to control the music player and GUI components.
 */
public class MusicPlayer {
    private JPanel mainPanel;
    private JLabel rightSideTopLabel;
    private JPanel datePanel;
    private JLabel dateLabel;
    private JLabel onThisDayLabel;
    private JLabel onThisDayInfoLabel;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel sotdInfoPanel;
    private JLabel sotdLabel;
    private JLabel songNameLabel;
    private JPanel musicPlayerPanel;
    private JLabel playButtonLabel;
    private JPanel playPanel;
    private JLabel pauseLabel;
    private JPanel pausePanel;
    private JPanel rightPanel;
    private JSlider songSlider;
    private JPanel loopPanel;
    private JLabel loopLabel;
    private JPanel sliderPanel;
    private JLabel songSubLabel;
    private JLabel rightHeader;
    private String url;

    MusicLogic m = new MusicLogic();

    //store current position
    Long currentFrame;
    Clip clip;

    //current status of clip
    String status;

    //initialize stream obj and filepath
    AudioInputStream audioInputStream;
    static String filePath;

    /**
     * Constructor for MusicPlayer
     * @throws IOException
     */
    public MusicPlayer() throws IOException, UnsupportedAudioFileException, LineUnavailableException, SQLException {
        JFrame frame = new JFrame("Discover a new song daily");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(925, 650);
        frame.setResizable(false);

        m.connectToDB();
        m.getDBRS();
        String me = m.getDBRS();
        m.createAndPlayMusicPlayer(me);


        //Set images for labels
        Image myImage = ImageIO.read(getClass().getResource("downloadplay.png"));
        myImage = myImage.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        ImageIcon myImageIcon = new ImageIcon(myImage);
        playButtonLabel.setIcon(myImageIcon);

        Image pauseImage = ImageIO.read(getClass().getResource("images.png"));
        pauseImage = pauseImage.getScaledInstance(38, 38, java.awt.Image.SCALE_SMOOTH);
        ImageIcon pauseImageIcon = new ImageIcon(pauseImage);
        pauseLabel.setIcon(pauseImageIcon);

        Image loopImage = ImageIO.read(getClass().getResource("loop.png"));
        loopImage = loopImage.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        ImageIcon loopImageIcon = new ImageIcon(loopImage);
        loopLabel.setIcon(loopImageIcon);

        rightHeader.setText("<HTML><U>C O R E     M U S I C</U></HTML>");

        onThisDayInfoLabel.setText("<html><center>Kodak was sliding going crazy." +
                "<br> This was a good album and elevated kodak alot with his music.</html>");

        //Display current date
        m.displayCurrentDate(dateLabel);
        //rightSideTopLabel.setText("<html><center>S O N G  <br><br> O F <br><br> T H E <br><br> D A Y</html>");
        rightSideTopLabel.setText("New music, everyday.");
        frame.setVisible(true);
        frame.add(mainPanel);

        /**
         * Action listener for play button label
         */
        playButtonLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                m.play();
            }
        });

        /**
         * Action listener for loop button label
         */
        loopLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        });

        /**
         * Action listener for pause button label
         */
        pauseLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                m.pause();
            }
        });
    }

}
