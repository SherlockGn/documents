package com.tianhe.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import com.tianhe.resultmodel.ResultModel;

public class HtmlOutputer {

	private static String strReverse(String str) {
		return str.replaceAll(" ", "&nbsp;").replaceAll(">", "&gt;").replaceAll("<", "&lt;");
	}

	private static String genSpace(int num) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++)
			sb.append("&nbsp;");
		return sb.toString();
	}

	public void output(String title, OutputStream os, List<ResultModel> lst) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
		bw.write("<!DOCTYPE html>\n");
		bw.write("<html>\n");
		bw.write("<head> \n");
		bw.write("<title>" + title + "</title>\n");
		bw.write("<link href=\"http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
		bw.write("<style>\n");
		bw.write(".table .sen td {\n");
		bw.write("margin-left:3000pt;\n");
		bw.write("}\n");
		bw.write("</style>\n");
		bw.write("</head>\n");
		bw.write("<body>\n");
		bw.write("<div class=\"container\">\n");
		bw.write("<div class=\"col-lg-offset-2 col-lg-8\">\n");
		bw.write("<table class=\"table table-hover table-condensed\">\n");
		bw.write("<thead>\n");
		bw.write("<tr>\n");
		bw.write("<th style=\"width:800pt;\">state</th>\n");
		bw.write("<th>content</th>\n");
		bw.write("</tr>\n");
		bw.write("</thead>\n");
		bw.write("<tbody>\n");

		for (ResultModel rm : lst) {
			if (rm.getType() == ResultModel.NORMAL)
				bw.write("<tr class=\"active\"><td>normal</td>\n");
			else if (rm.getType() == ResultModel.ADD)
				bw.write("<tr class=\"success\"><td>add</td>\n");
			else if (rm.getType() == ResultModel.DELETE)
				bw.write("<tr class=\"danger\"><td>delete</td>\n");
			bw.write("<td>" + genSpace(1) + strReverse(rm.getSentence()) + "</td></tr>\n");
		}

		bw.write("</tbody>\n");
		bw.write("</table>\n");
		bw.write("</div>\n");
		bw.write("</div>\n");
		bw.write("<script src=\"http://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>\n");
		bw.write("<script src=\"http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js\"></script>\n");
		bw.write("</body>\n");
		bw.write("</html>\n");

		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {

	}
}