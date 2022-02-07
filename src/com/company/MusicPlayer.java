package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    /**
     * Constructor for Music Player
     * @throws IOException
     */
    public MusicPlayer() throws IOException {
        JFrame frame = new JFrame("Discover a new song daily");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 550);

        //Set images for buttons
        Image myImage = ImageIO.read(getClass().getResource("downloadplay.png"));
        myImage = myImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        ImageIcon myImageIcon = new ImageIcon(myImage);
        playButtonLabel.setIcon(myImageIcon);

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
                System.out.println("Hello");
                int clicked = 0;
                clicked++;
                //play the musuci and switch sign

                Image pauseImage = null;
                try {
                    pauseImage = ImageIO.read(getClass().getResource("images.png"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                pauseImage = pauseImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                ImageIcon pauseImageIcon = new ImageIcon(pauseImage);
                playButtonLabel.setIcon(pauseImageIcon);

                if (clicked % 2 == 0) {
                    playButtonLabel.setIcon(myImageIcon);
                }

            }
        });
    }



    //Display the current date for the date label
    public void displayCurrentDate() {
        //dateLabel.setText(String.valueOf(LocalDate.now()));

        String pattern = "dd MM yyyy";
        String p = "EEEEE, MMMMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(p);

        String date = simpleDateFormat.format(new Date());
        dateLabel.setText(date);

    }


}
