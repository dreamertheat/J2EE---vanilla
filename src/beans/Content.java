package beans;

public class Content {
	
	public Content() {
		// TODO Auto-generated constructor stub
	}
	
	int _id, master_id, previous_level, previous_level_group_id, next_level, next_level_group_id;
	String title, definition, content, content_type, date;
	
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int getMaster_id() {
		return master_id;
	}
	public void setMaster_id(int master_id) {
		this.master_id = master_id;
	}
	public int getPrevious_level() {
		return previous_level;
	}
	public void setPrevious_level(int previous_level) {
		this.previous_level = previous_level;
	}
	public int getPrevious_level_group_id() {
		return previous_level_group_id;
	}
	public void setPrevious_level_group_id(int previous_level_group_id) {
		this.previous_level_group_id = previous_level_group_id;
	}
	public int getNext_level() {
		return next_level;
	}
	public void setNext_level(int next_level) {
		this.next_level = next_level;
	}
	public int getNext_level_group_id() {
		return next_level_group_id;
	}
	public void setNext_level_group_id(int next_level_group_id) {
		this.next_level_group_id = next_level_group_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
}
