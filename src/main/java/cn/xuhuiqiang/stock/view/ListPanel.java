package cn.xuhuiqiang.stock.view;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger; 

import cn.xuhuiqiang.stock.model.News;

public class ListPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<News> contents;
    private JList<String> listPanel;
    private ShowArea contentPanel;
    private static Logger log = Logger.getLogger(ListPanel.class);
    
    public ListPanel(List<News> content){
        super();
        this.contents = content;
    	initView();
    }
    
    public void initView(){ 
    	setVisible(true);
        listPanel=new JList<String>();
        listPanel.setVisibleRowCount(30);
        String[] tmp = new String[contents.size()];
        for(int i = 0; i < contents.size();i++){
        	tmp[i] = i + ". " + contents.get(i).getTitle();
        }
        ListModel<String> showData = new DefaultComboBoxModel<String>(
                tmp);
        listPanel.setModel(showData);
        
        JScrollPane scrollList = new JScrollPane(listPanel);
        listPanel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = listPanel.getSelectedIndex();
				if(index < 0){
					log.warn("index is less than 0");
					return ;
				}
				News selectNews = contents.get(index);
				System.out.println(selectNews.toString());
				//String show = GetNews.getBuyContent(selectNews.getUrl());
				String show = "";
				JTextArea text = contentPanel.getTextArea();
				text.setText(show);
			}
		});
        scrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //listPanel.setPreferredSize(new Dimension(400, 600));
        listPanel.setFixedCellWidth(400);
        add(scrollList);
    }
    
    public void updateList(List<News> data){
    	remove(listPanel);
    	contents = data;
    	String[] tmp = new String[contents.size()];
        for(int i = 0; i < contents.size();i++){
        	tmp[i] = i + ". " + contents.get(i).getTitle();
        }
        ListModel<String> showData = new DefaultComboBoxModel<String>(
                tmp);
        listPanel.setModel(showData);
        log.info("all data of list: " + listPanel.getComponentCount());
    }
    
    public void updateListByPage(List<News> data, int page){
    	remove(listPanel);
    	contents = data.subList(50 * page -1, 50 * page);
    	int index = 50 * page;
    	String[] tmp = new String[index];
    	
        for(int i = 0; i < contents.size();i++){
        	tmp[i] = contents.get(i).getTitle();
        }
        ListModel<String> showData = new DefaultComboBoxModel<String>(
                tmp);
        listPanel.setModel(showData);
        log.info("all data of list: " + listPanel.getComponentCount());
        
    }

	public void setContentPanel(ShowArea contentPanel) {
		this.contentPanel = contentPanel;
	}
    
}
