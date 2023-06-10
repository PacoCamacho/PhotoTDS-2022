package photo.tds.helpers;

import javax.swing.JPanel;

public class PanelItem {
	  private JPanel panel;

	    public PanelItem(JPanel panel) {
	        this.panel = panel;
	    }

	    public JPanel getPanel() {
	        return panel;
	    }

	    @Override
	    public String toString() {
	        return "PanelItem";
	    }
}
