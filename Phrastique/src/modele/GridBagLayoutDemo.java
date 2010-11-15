package modele;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;

public class GridBagLayoutDemo {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public static void addComponentsToPane(Container pane) {
    	Recuperation donnees = new Recuperation("exemple1.xml");
    	
        JTextPane t;
	pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	t = new JTextPane();
	t.setText(donnees.getTrans().getPhrases().get("P1"));
	t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
			BorderFactory.createLoweredBevelBorder()),BorderFactory.createEmptyBorder(2, 2, 2, 2)));
	c.fill = GridBagConstraints.HORIZONTAL;
	c.anchor = GridBagConstraints.LINE_START; //bottom of space
	c.gridx = 0;       //aligned with button 2
	c.gridy = GridBagConstraints.RELATIVE;       //third row
	pane.add(t, c);

	t = new JTextPane();
	t.setText(donnees.getTrans().getPhrases().get("P2"));
	t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
			BorderFactory.createLoweredBevelBorder()),BorderFactory.createEmptyBorder(2, 2, 2, 2)));
	pane.add(t, c);

	t = new JTextPane();
	t.setText(donnees.getTrans().getPhrases().get("P3"));
	t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
			BorderFactory.createLoweredBevelBorder()),BorderFactory.createEmptyBorder(2, 2, 2, 2)));
	
	pane.add(t, c);

	t = new JTextPane();
	t.setText(donnees.getTrans().getPhrases().get("P4"));
	t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
			BorderFactory.createLoweredBevelBorder()),BorderFactory.createEmptyBorder(2, 2, 2, 2)));
	
	pane.add(t, c);

	t = new JTextPane();
	t.setText(donnees.getTrans().getPhrases().get("P5"));
	t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
			BorderFactory.createLoweredBevelBorder()),BorderFactory.createEmptyBorder(2, 2, 2, 2)));
	
	pane.add(t, c);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.setVisible(true);
        frame.setSize(800, 600);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
