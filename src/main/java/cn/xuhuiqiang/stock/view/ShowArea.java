package cn.xuhuiqiang.stock.view;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ShowArea extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea text;
	private JTextField urlText;
	private JButton copyBtn;
	
	public ShowArea(){
		initView();
	}
	
	private void initView(){
		setVisible(true);
		setSize(60, 80);
		
		urlText = new JTextField();
		urlText.setEditable(false);
		copyBtn = new JButton();
		text = new JTextArea();
		//text.setText("show detail contents");
		copyBtn.addActionListener(new ActionListener() {
			
		
			public void actionPerformed(ActionEvent e) {
				String url = urlText.getText();
				if(null == url || url.isEmpty()){
					return;
				}
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable content = new StringSelection(url);  
				clipboard.setContents(content, null);
			}
		});
		add(urlText);
		add(text);
	}
	
	public JTextArea getTextArea(){
		return text;
	}
	
	public void updateContent(String value){
		text.setText(value);
	}
	
}
