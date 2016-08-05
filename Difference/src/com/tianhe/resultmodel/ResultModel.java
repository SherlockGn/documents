package com.tianhe.resultmodel;

public class ResultModel {
	private int type;
	private String sentence;

	public final static int ADD = 0;
	public final static int DELETE = 1;
	public final static int NORMAL = 2;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public ResultModel(int type, String sentence) {
		this.type = type;
		this.sentence = sentence;
	}

	public ResultModel() {
	}

	public ResultModel(String sentence) {
		this.type = ResultModel.NORMAL;
		this.sentence = sentence;
	}

}
