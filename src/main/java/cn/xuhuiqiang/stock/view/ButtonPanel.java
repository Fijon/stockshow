package cn.xuhuiqiang.stock.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import cn.xuhuiqiang.stock.dao.BaseDao;
import cn.xuhuiqiang.stock.model.News;
import cn.xuhuiqiang.stock.param.PanelName;



public class ButtonPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton searchBtn;

	private JTextField searchCode;

	private ShowArea panel;
	private ListPanel listPanel;

	
	private static Logger log = Logger.getLogger(ButtonPanel.class);
	public ButtonPanel() {
		super();
		initView();
	}

	public ButtonPanel(ShowArea panel, ListPanel listPanel) {
		super();
		this.panel = panel;
		this.listPanel = listPanel;
		initView();

	}

	private void initView() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setVisible(true);
		searchCode = new JTextField("股票代码");
		searchBtn = new JButton(PanelName.SEARCH_BTN.toString());

		setSize(30, 600);
		add(searchCode);
		add(searchBtn);

		if (panel == null) {
			System.out.println("error");
		}

		searchBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String tmpStr = searchCode.getText();
				tmpStr = null == tmpStr || tmpStr.isEmpty() ? "0" : tmpStr;
				System.out.println(tmpStr);
				String code = null;
				try {
					code = String.format("%06d", Integer.valueOf(tmpStr));
				} catch (Exception exception) {
					code = "0";
					log.warn("input string is not a stock code");
				}
				final ReadDataThread task = new ReadDataThread(listPanel, code);
				SwingUtilities.invokeLater(task);

			}
		});
	}

	public ShowArea getPanel() {
		return panel;
	}

	public void setPanel(ShowArea panel) {
		this.panel = panel;
	}

	class ReadDataThread implements Runnable {

		private ListPanel listPanel;
		private String code;

		public ReadDataThread(ListPanel listPanel, String code) {
			this.listPanel = listPanel;
			this.code = code;
		}

		public void run() {
			List<News> result = BaseDao.queryByCode(code);
			if (null == result) {
				result = new LinkedList<News>();
			}
			listPanel.updateList(result);
		}
	}

}
