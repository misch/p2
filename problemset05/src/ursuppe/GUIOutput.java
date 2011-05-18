package ursuppe;

import javax.swing.JTextArea;

public class GUIOutput extends Output {
	
	private JTextArea area;
	
	public GUIOutput(JTextArea area){
		this.area = area;
	}

	@Override
	public void print(String s){
		
		area.append(s);
	}

	
}
