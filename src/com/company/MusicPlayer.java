package com.company;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    // to store current position
    Long currentFrame;
    Clip clip;

    // current status of clip
    String status;

    AudioInputStream audioInputStream;
    static String filePath;





    /**
     * Constructor for Music Player
     * @throws IOException
     */
    public MusicPlayer() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        JFrame frame = new JFrame("Discover a new song daily");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 450);
        frame.setResizable(false);

        filePath = "C:\\Users\\Dalton Price\\Downloads\\RapSongOfTheDayApp\\Kodak_Versatile3.wav";

        // create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        //clip.loop(Clip.LOOP_CONTINUOUSLY);

        //Set images for buttons
        Image myImage = ImageIO.read(getClass().getResource("downloadplay.png"));
        myImage = myImage.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        ImageIcon myImageIcon = new ImageIcon(myImage);
        playButtonLabel.setIcon(myImageIcon);

        Image pauseImage = ImageIO.read(getClass().getResource("images.png"));
        pauseImage = pauseImage.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        ImageIcon pauseImageIcon = new ImageIcon(pauseImage);
        pauseLabel.setIcon(pauseImageIcon);

        Image loopImage = ImageIO.read(getClass().getResource("loop.png"));
        loopImage = loopImage.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        ImageIcon loopImageIcon = new ImageIcon(loopImage);
        loopLabel.setIcon(loopImageIcon);

        //Display current date
        displayCurrentDate();
        rightSideTopLabel.setText("<html><center>S O N G  <br><br> O F <br><br> T H E <br><br> D A Y</html>");
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


        loopLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

        pauseLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pause();
            }
        });
    }

    //END CONSTUCTOR START METHODS

    //Display the current date for the date label
    public void displayCurrentDate() {

        String p = "EEEEE, MMMMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(p);

        String date = simpleDateFormat.format(new Date());
        dateLabel.setText(date);
    }

    // Method to play the audio
    public void play() {
        //start the clip
        clip.start();
        status = "play";
    }

    // Method to pause the audio
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
