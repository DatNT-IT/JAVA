package giongnoi;

import javazoom.jl.player.Player;
import marytts.util.data.audio.AudioPlayer;


import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Music {
    public static void main(String[] args) {
        try {


            FileInputStream fileInputStream = new FileInputStream("music.mp3");
            Player player = new Player(fileInputStream);
            player.play();
            System.out.println("đã chạy");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
