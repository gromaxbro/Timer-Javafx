package application;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;

public class controller {
    @FXML
    private Label txt;
    @FXML
    ProgressBar pgBar;
    // The label injected from FXML
    Timeline mjTimeline;
    Timeline timerrTimeline;
    MediaPlayer mediaPlayer;
    
    final int[] tm = {10};
    int max = tm[0];

    private void clock_run() {
    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
         LocalTime time = LocalTime.now();


         // Format the current time and set it to the label
         txt.setText(time.format(formatter));
         
         // Timeline that updates every second
         mjTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTime(formatter)));
         mjTimeline.setCycleCount(Animation.INDEFINITE);
         mjTimeline.play();// Start the animation
	}
    public void initialize() {
        // Set up the formatter for the time display
    	pgBar.setProgress(1);
    	clock_run();
    	URL mediaResource = getClass().getResource("ardin.wav");
        Media media = new Media(mediaResource.toString());
        mediaPlayer = new MediaPlayer(media);
        
        mediaPlayer.setCycleCount(Animation.INDEFINITE);
        
    }

    // Method to update the time on the label
    private void updateTime(DateTimeFormatter formatter) {
        // Get the current time
        LocalTime time = LocalTime.now();
        
        // Format the current time and set it to the label
        txt.setText(time.format(formatter));
    }
    public static String formatSeconds(int timeInSeconds)
    {
        int secondsLeft = timeInSeconds % 3600 % 60;
        int minutes = (int) Math.floor(timeInSeconds % 3600 / 60);
        int hours = (int) Math.floor(timeInSeconds / 3600);

        String HH = ((hours       < 10) ? "0" : "") + hours;
        String MM = ((minutes     < 10) ? "0" : "") + minutes;
        String SS = ((secondsLeft < 10) ? "0" : "") + secondsLeft;

        return HH + ":" + MM + ":" + SS;
    }   
    public void change(int max,int[] tm) {
    	
    	double pr = (double) tm[0]/max;
//    	System.out.println(tm[0]);

    	
    	if(pr == 0.0) {
    		System.out.println("gg");
    		txt.setText(formatSeconds(tm[0]));
    		pgBar.setProgress(pr);
    		timerrTimeline.pause();
    		tm[0] = 0;
            // Load and play media
    		mediaPlayer.stop();

    		mediaPlayer.play();


    	}
    	else {
    		txt.setText(formatSeconds(tm[0]));
        	pgBar.setProgress(pr);
        	tm[0]--;
        	
    	}

    	
	}
    
    public void timer_run() {
    	

        // Timeline that updates every second
    	
    	txt.setText(formatSeconds(tm[0]));
    	timerrTimeline  = new Timeline(new KeyFrame(Duration.seconds(1), e -> change(max,tm)));
    	timerrTimeline.setCycleCount(Animation.INDEFINITE);
//    	timerrTimeline.play();// Start the animation
    }
   
    public void Timer() {
    	mjTimeline.stop();
        timer_run();
        
        
    }

    // Clock button action
    public void Clock() {
    
    	timerrTimeline.stop();
        clock_run();
    }
    public void Start() {
    	if(tm[0] != 0 && timerrTimeline != null) {
    		timerrTimeline.play();
    	}
    	
	}
    public void Stop() {
    	if (timerrTimeline != null) {
    		timerrTimeline.pause();
    		mediaPlayer.pause();
    	}
    	
  	}
    public void add_1() {
    	tm[0]+= 60;
    	max = tm[0];
    	txt.setText(formatSeconds(tm[0]));
    	
  	}
    public void add_10() {
    	tm[0]+= 60*10;
    	max = tm[0];
    	txt.setText(formatSeconds(tm[0]));
  	}
    public void add_5() {
    	tm[0]+= 60*5;
    	max = tm[0];
    	txt.setText(formatSeconds(tm[0]));
  	}
    public void reset() {
    	timerrTimeline.stop();
    	mediaPlayer.pause();
    	tm[0]= 0;
    	max = tm[0];
    	pgBar.setProgress(1);
    	txt.setText(formatSeconds(tm[0]));
    	
	}
}
