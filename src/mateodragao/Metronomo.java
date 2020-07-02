package mateodragao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Metronomo extends ActionSubject implements ActionListener {
   private Timer metro;
    
   public Metronomo(int intervalo) {
      metro = new Timer(intervalo, this);
   }
   
   public void start() {
      metro.start();
   }
    
   public void stop() {
      metro.stop();
   }
    
   public void actionPerformed(ActionEvent event) {
      notify(event);
   }
}