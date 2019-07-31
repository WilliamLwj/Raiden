
// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;

public class Game implements Runnable {
	private final JPanel control_panel = new MyPanel(400, 40, "files/control_panel.png");
	private GameCourt court;
	private final JPanel record_panel = new RecordPanel(); 
	private final JPanel start_panel = new MyPanel(400, 600, "files/start.png"); 
	private final JPanel help_panel = new MyPanel(400, 600, "files/help.png"); 
	private final JFrame frame = new JFrame("Raiden");
	
	
    public void run() {
    	court = new GameCourt("Johnson");
    	court.reset(false);
        frame.setLocation(400, 1);
        frame.add(start_panel, BorderLayout.CENTER);

        frame.add(control_panel, BorderLayout.NORTH);
        
        changePanels(User_Interfaces.USER_NAME);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    
    
    public void changePanels(User_Interfaces UI) {
    	 control_panel.removeAll();
    	 control_panel.revalidate();
    	 final JButton start = new MyButton("files/buttons/start_button.png");
         start.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 
                 start_panel.removeAll();
                 start_panel.add(court);
                 start_panel.revalidate();
                 start_panel.repaint();
                 court.reset(true);
                 changePanels(User_Interfaces.GAME);
             }
         });
    	final JButton main = new MyButton ("files/buttons/main_button.png");
		main.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                start_panel.removeAll();
	                start_panel.revalidate();
	                start_panel.repaint();
	                court.reset(false);
	                changePanels(User_Interfaces.START);
	            }
	        });
		
		final JButton reset = new MyButton("files/buttons/restart_button.png");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	start_panel.removeAll();
            	start_panel.add(court);
            	start_panel.revalidate();
                start_panel.repaint();
                court.reset(true);
                changePanels(User_Interfaces.GAME);
            }
        });
        
        final JButton ranking = new MyButton("files/buttons/ranking_button.png");
        ranking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	start_panel.removeAll();
            	start_panel.add(record_panel);
                start_panel.revalidate();
                start_panel.repaint();
                court.reset(false);
                changePanels(User_Interfaces.RANKING);
            }
        });
        
        
        final JButton help = new MyButton("files/buttons/help_button.png");
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                start_panel.removeAll();
                start_panel.add(help_panel);
                start_panel.revalidate();
                start_panel.repaint();
                court.reset(false);
                changePanels(User_Interfaces.HELP);
            }
        });
        final JButton enter = new MyButton("files/buttons/start_button.png");
        enter.setActionCommand("ENTER");
        JTextArea input = new JTextArea("Type in your name, no spaces");
        input.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
            	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            		enter.doClick();
            	}	
            }
        });
        input.setPreferredSize(new Dimension (200, 20));
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String username = input.getText();
            	if(username.indexOf(' ') >= 0) {
            		 input.setText("No Spaces!");
            	}
            	else {
            		court.setName(username);
            		changePanels(User_Interfaces.START);
            	}
            }
        });
        
        
    	
        switch(UI) {
    		case USER_NAME:{
    			control_panel.add(input);
    			control_panel.add(enter);
    			control_panel.repaint();
    			break;
    		}
    		
    		case START:{
    			start_panel.removeAll();
    			
    			control_panel.add(start);
    			control_panel.add(ranking);
    			control_panel.add(help);
    			control_panel.repaint();
    	        break;
    		}
    		case GAME:{
    	        control_panel.add(reset);
    	        control_panel.add(ranking);
    	        control_panel.add(help);
    	        control_panel.repaint();
    			break;
    		}
    		
    		case RANKING:{
    			
    	        control_panel.add(main);
    	        control_panel.add(reset);
    	        control_panel.repaint();
    			break;
    		}
    		
    		case HELP:{
    	        control_panel.add(main);
    	        control_panel.add(reset);
    	        control_panel.repaint();
    			break;
    		}
    		default:{
    			break;
    		}
    	}
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}