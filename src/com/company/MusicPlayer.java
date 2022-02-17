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
    private Statement myStmt = null;
    private Connection myConn = null;
    private ResultSet myRs = null;
    private String url;
    private String name;
    private String title;
    private String year;
    private String album;
    private String title2;

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

        //DB STUFF
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicdb",
                "student", "student");

        //Create query and execute for hip hop radio.
        String query3 = "SELECT * FROM song_info ORDER BY RAND() LIMIT 1";
        myStmt = myConn.createStatement();
        myRs = myStmt.executeQuery(query3);

        //See if RS returns anything and get info based on it.
        while (myRs.next()) {

            System.out.println(myRs.getString(2));
            name = myRs.getString(2);

            System.out.println(myRs.getString(4));
            album = myRs.getString(4);

            System.out.println(myRs.getString(5));
            title = myRs.getString(5);
            title2 = title.replaceAll("\\s", "");

            System.out.println(myRs.getString(8));
            year = myRs.getString(8);


            //the filepath string for a specific song
            filePath = "C:\\Users\\Dalton Price\\Downloads\\RapSongOfTheDayApp\\" + title2 + ".wav";
            songNameLabel.setText(name + " - " + title);
            songSubLabel.setText(album + " - " + year);
        }

        //create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        //create clip reference
        clip = AudioSystem.getClip();

        //open audioInputStream to the clip
        clip.open(audioInputStream);

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
        displayCurrentDate();
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
                play();
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
                pause();
            }
        });
    }

    /**
     * Display current date at top of screen
     */
    public void displayCurrentDate() {
        String p = "EEEEE, MMMMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(p);
        String date = simpleDateFormat.format(new Date());
        dateLabel.setText(date);
    }

    /**
     * Play the selected music
     */
    public void play() {
        //start the clip
        clip.start();
        status = "play";
    }

    /**
     * Pause the song
     */
    public void pause() {
        if (status.equals("paused")) {
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

}
