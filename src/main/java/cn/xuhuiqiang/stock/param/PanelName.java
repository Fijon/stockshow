package cn.xuhuiqiang.stock.param;

public enum PanelName
{
    INCREASE_BTN("增持"),REDUCTION_BTN("减持"),REPUCHRCHASE_BTN("回购"),GRADE_BTN("评级"),SEARCH_BTN("搜索");
    private final String name;
    private PanelName(String value){
       this.name = value;
    }

    public String toString(){
    	return name;
    }
}
