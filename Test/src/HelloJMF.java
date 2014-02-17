/* Program HelloJMF.java
Plays an audio file that is present in the same directory wherein the
HelloJMF.class file resides.
*/
import javax.swing.*;
import java.awt.*;
import javax.media.*;
import java.awt.event.*;
import java.net.*;

public class HelloJMF {
	JFrame frame = new JFrame(" Hello JMF Player");
	static Player helloJMFPlayer = null;
	public HelloJMF(){
		try{
			// method using URL
			URL url=new URL("file",null,"Kya.mp3");
			helloJMFPlayer = Manager.createRealizedPlayer( url);
		} catch( Exception e) {
			System.out.println(" Unable to create the audioPlayer :" + e );
		}
		Component control = helloJMFPlayer.getControlPanelComponent();
		frame.getContentPane().add( control, BorderLayout.CENTER);
		frame.addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				HelloJMF.stop();System.exit(0);
			}
		});
		frame.pack();
		frame.setSize( new Dimension(200,50) );
		frame.setVisible(true);
		helloJMFPlayer.start();
	}
	public static void stop(){
		helloJMFPlayer.stop();
		helloJMFPlayer.close();
	}
	public static void main( String args[]){
		HelloJMF helloJMF = new HelloJMF();
	}
}