package com.gth.trendmicro.model;

public class User {
	private String url;
	private String time;
	private String id;
	private Integer experience;
	private Integer treasure;
	private String accept;
	private Integer question_num;
	private Integer answer_num;
	private Integer excellent;
	private Integer goods;
	private String attention_cates;
	private String attention_words;
	private String activities;
	private String teams;

	/* important: a default constructor is needed */
	public User() {
	}

	public User(String url, String time, String id, Integer experience, Integer treasure, String accept,
			Integer question_num, Integer answer_num, Integer excellent, Integer goods, String attention_cates,
			String attention_words, String activities, String teams) {
		this.url = url;
		this.time = time;
		this.id = id;
		this.experience = experience;
		this.treasure = treasure;
		this.accept = accept;
		this.question_num = question_num;
		this.answer_num = answer_num;
		this.excellent = excellent;
		this.goods = goods;
		this.attention_cates = attention_cates;
		this.attention_words = attention_words;
		this.activities = activities;
		this.teams = teams;
	}

	@Override
	public String toString() {
		return "User [url=" + url + ", time=" + time + ", id=" + id + ", experience=" + experience + ", treasure="
				+ treasure + ", accept=" + accept + ", question_num=" + question_num + ", answer_num=" + answer_num
				+ ", excellent=" + excellent + ", goods=" + goods + ", attention_cates=" + attention_cates
				+ ", attention_words=" + attention_words + ", activities=" + activities + ", teams=" + teams + "]";
	}

	/* important: the hash code & equals function only have associations with id */
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* important: setters and getters are needed */
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Integer getTreasure() {
		return treasure;
	}

	public void setTreasure(Integer treasure) {
		this.treasure = treasure;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public Integer getQuestion_num() {
		return question_num;
	}

	public void setQuestion_num(Integer question_num) {
		this.question_num = question_num;
	}

	public Integer getAnswer_num() {
		return answer_num;
	}

	public void setAnswer_num(Integer answer_num) {
		this.answer_num = answer_num;
	}

	public Integer getExcellent() {
		return excellent;
	}

	public void setExcellent(Integer excellent) {
		this.excellent = excellent;
	}

	public Integer getGoods() {
		return goods;
	}

	public void setGoods(Integer goods) {
		this.goods = goods;
	}

	public String getAttention_cates() {
		return attention_cates;
	}

	public void setAttention_cates(String attention_cates) {
		this.attention_cates = attention_cates;
	}

	public String getAttention_words() {
		return attention_words;
	}

	public void setAttention_words(String attention_words) {
		this.attention_words = attention_words;
	}

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

	public String getTeams() {
		return teams;
	}

	public void setTeams(String teams) {
		this.teams = teams;
	}

}
