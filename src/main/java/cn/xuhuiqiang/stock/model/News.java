package cn.xuhuiqiang.stock.model;

import java.util.UUID;

public class News {
	private String parent_id;
	private String code;
	private String title;
	private String content;
	private String post_time;
	private String url;

	public String grapInsertSql() {
		StringBuffer sb = new StringBuffer();
		if (parent_id == null) {
			parent_id = "0";
		}
		UUID uuid = UUID.randomUUID();
		
		sb.append("INSERT INTO News VALUES('").append(uuid).append("','")
				.append(code).append("','").append(title).append("','")
				.append(content).append("','").append(url).append("','")
				.append(post_time).append("','").append(parent_id)
				.append("');");
		System.out.println(sb.toString());
		return sb.toString();
	}

	public static String tabelSql() {
		String sql = "CREATE TABLE News " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " CODE           TEXT    NOT NULL, " + 
                " TITLE           TEXT    NOT NULL, " + 
                " CONTENT        TEXT, " + 
                " URL        TEXT, " + 
                " POST_TIME        CHAR(50), " +
                " PARENTID    INT)"; 
		return sql;
	}
	

	public String getParent_id() {
		return parent_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPost_time() {
		return post_time;
	}

	public void setPost_time(String post_time) {
		this.post_time = post_time;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("parent_id: ").append(parent_id).append("; Title: ")
				.append(title).append("; content: ").append(content)
				.append("; url").append(url).append("; postTime: ")
				.append(post_time);
		return sb.toString();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
