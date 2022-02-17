package com.company;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MusicLogic {

    private Statement myStmt = null;
    private Connection myConn = null;
    private ResultSet myRs = null;

    private String filePath;

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

    public MusicLogic() {

    }

    public Connection connectToDB() throws SQLException {
        //DB STUFF
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicdb",
                "student", "student");
        return myConn;
    }

    /**
     * Display current date at top of screen.
     */
    public void displayCurrentDate(JLabel label) {
        String p = "EEEEE, MMMMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(p);
        String date = simpleDateFormat.format(new Date());
        label.setText(date);
    }

    public String getDBRS() throws SQLException {
            Connection myConn = connectToDB();
            //Create query and execute for hip hop radio.
            String query3 = "SELECT * FROM song_info ORDER BY RAND() LIMIT 1";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(query3);


        //See if RS returns anything and get info based on it.
        while (myRs.next()) {

            name = myRs.getString(2);

            album = myRs.getString(4);

            title = myRs.getString(5);
            title2 = title.replaceAll("\\s", "");

            year = myRs.getString(8);

            //the filepath string for a specific song
            filePath = "C:\\Users\\Dalton Price\\Downloads\\RapSongOfTheDayApp\\" + title2 + ".wav";
            //songNameLabel.setText(name + " - " + title);
            //songSubLabel.setText(album + " - " + year);
        }
        return filePath;
    }

    public void createAndPlayMusicPlayer(String file) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        //create AudioInputStream object
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());

        //create clip reference
        clip = AudioSystem.getClip();

        //open audioInputStream to the clip
        clip.open(audioInputStream);
    }

    public void setLabelText(JLabel label) {
        label.setText("hi");

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
