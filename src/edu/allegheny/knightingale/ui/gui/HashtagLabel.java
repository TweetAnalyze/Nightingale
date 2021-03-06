//     __ __     _      __   __  _                __   
//    / //_/__  (_)__ _/ /  / /_(_)__  ___ ____ _/ /__ 
//   / ,< / _ \/ / _ `/ _ \/ __/ / _ \/ _ `/ _ `/ / -_)
//  /_/|_/_//_/_/\_, /_//_/\__/_/_//_/\_, /\_,_/_/\__/ 
//              /___/                /___/          
//  Open-source Twitter analytics...with style!

package edu.allegheny.knightingale.ui.gui;

import java.net.URI;
import java.net.URISyntaxException;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import java.util.logging.Logger;
import java.util.logging.Level;

import org.mcavallo.opencloud.Tag;


/**
 * Wrapper element for hashtags JLabels in frequency clouds to allow 
 * interaction behaviour.
 *
 * Please note that, as a GUI element, this class is not a target 
 * for test automation, as it will be tested manually during the 
 * user testing and acceptance testing phases.
 *
 * @author 	Hawk Weisman
 * @version 1.0
 * @since 	December 7th, 2013
 */
public class HashtagLabel extends JPanel implements MouseListener {

	protected Desktop desktop;
	protected JLabel label;
	protected Tag tag;

	public static Logger logger = Logger.getLogger(HashtagLabel.class.getName());
	
	/**
	 * @param tag the OpenCloud tag element containing the text and weight for this label
	 */
	public HashtagLabel(Tag tag) {
		super();
	    label = new JLabel(tag.getName());
	    this.tag = tag;
	    label.addMouseListener(this);
	    add(label);

	    if (Desktop.isDesktopSupported()) {
			desktop = Desktop.getDesktop();
	    } else {
	    	logger.log(Level.WARNING, "java.awt.Desktop is not supported on this platform, get a real computer if you want to use browser linking.");
	    }

		label.setOpaque(false);
	    label.setFont(label.getFont().deriveFont((float) tag.getWeight() * 10));
	}

	@Override
	public void mouseEntered(MouseEvent e){
		label.setForeground(Color.BLUE);
	}

	@Override
	public void mouseExited(MouseEvent e){
		label.setForeground(Color.BLACK);
	}

    @Override
	public void mouseClicked(MouseEvent e) {    
	   	try {       
	        desktop.browse(new URI("https://twitter.com/search?q=%23"+label.getText().substring(1)+"&src=hash"));
	    } catch (Exception ex) {
	    	ex.printStackTrace(); // log this later
	    }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
    }   

	@Override
   	public void mousePressed(MouseEvent e) {
   	}  
}
