package com.company;
import java.io.IOException;
import java.sql.SQLException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Main method to call instance of MusicPlayer
 */
public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, SQLException {
        new MusicPlayer();
    }




}

