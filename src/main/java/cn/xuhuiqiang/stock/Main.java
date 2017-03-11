package cn.xuhuiqiang.stock;

import javax.swing.SwingUtilities;



public class Main {
	static JframeMain frame;
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame = new JframeMain();
			}
		});
	}
}
