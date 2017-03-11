package cn.xuhuiqiang.stock;

import java.awt.Container;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import cn.xuhuiqiang.stock.dao.BaseDao;
import cn.xuhuiqiang.stock.model.News;
import cn.xuhuiqiang.stock.param.SettingParam;
import cn.xuhuiqiang.stock.view.ButtonPanel;
import cn.xuhuiqiang.stock.view.ListPanel;
import cn.xuhuiqiang.stock.view.ShowArea;
 

public class JframeMain extends JFrame {

	private static Logger log = Logger.getLogger(JframeMain.class);
	private static final long serialVersionUID = -1250587505384102977L;
	private ListPanel listPanel;

	/**
     * 
     */

	private Container container;
	private SwingWorker<List<News>, Void> worker;

	public JframeMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(SettingParam.WIDTH, SettingParam.HEIGH);
		setVisible(true);
		container = getContentPane();
		initView();
		initData();

	}
	private void initView() {
		ShowArea content = new ShowArea();
		addPanel("Center", content);
		List<News> values = new LinkedList<News>();
		News newsTmp = new News();
		newsTmp.setTitle("waiting...");
		values.add(newsTmp);
		listPanel = new ListPanel(values);
		addPanel("West", listPanel);
		listPanel.setContentPanel(content);
		ButtonPanel btn = new ButtonPanel(content, listPanel);
		addPanel("North", btn);
	}
	
	private void initData(){
		worker = new SwingWorker<List<News>, Void>(){
			@Override
			protected List<News> doInBackground() throws Exception {
				return BaseDao.checkNews();
			}

			@Override
			protected void done() {
				List<News> datas = null;
				try {
					datas = get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				
				if(null == datas){
					System.out.println("error...");
					log.error("BaseDao.checkNews return is null");
					return;
				}
				if(datas.size() <= 0){
					System.out.println("**************** size is 0");
					log.warn("BaseDao.checkNews return is null");
				}
				log.info("size of datas: " + datas.size());
				listPanel.updateList(datas);			
			}
		};
		worker.execute();
	}

	public void addPanel(String position, JPanel panel) {
		position = position == null ? "Center" : position;
		container.add(position, panel);
	}
}