package com.tianhe.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tianhe.resultmodel.ResultModel;

public class DiffRunner {
	public List<ResultModel> diffRun(String path1, String path2) throws IOException {
		FileLoader fl1 = new FileLoader(), fl2 = new FileLoader();
		List<String> lst1 = fl1.getLines(path1);
		List<String> lst2 = fl2.getLines(path2);

		int[][] record = new int[lst1.size()][lst2.size()];
		int[][] num = new int[lst1.size() + 1][lst2.size() + 1];

		for (int i = 0; i < lst1.size() + 1; i++)
			num[i][lst2.size()] = 0;
		for (int i = 0; i < lst2.size() + 1; i++)
			num[lst1.size()][i] = 0;

		for (int i = lst1.size() - 1; i >= 0; i--) {
			for (int j = lst2.size() - 1; j >= 0; j--) {
				if (lst1.get(i).equals(lst2.get(j))) {
					record[i][j] = 0;
					num[i][j] = 1 + num[i + 1][j + 1];
				} else {
					int x = num[i + 1][j], y = num[i][j + 1];
					if (x >= y) {
						num[i][j] = x;
						record[i][j] = 1;
					} else {
						num[i][j] = y;
						record[i][j] = 2;
					}
				}

			}
		}

		List<Integer> rec1 = new ArrayList<Integer>();
		List<Integer> rec2 = new ArrayList<Integer>();

		int p1 = 0, p2 = 0;
		while (p1 < lst1.size() && p2 < lst2.size()) {
			if (record[p1][p2] == 1) {
				p1++;
			} else if (record[p1][p2] == 2) {
				p2++;
			} else {
				rec1.add(p1);
				rec2.add(p2);
				p1++;
				p2++;
			}
		}
		// System.out.println(rec1);
		// System.out.println(rec2);

		p1 = 0;
		p2 = 0;

		List<ResultModel> resultList = new ArrayList<ResultModel>();

		for (int i = 0; i < rec1.size(); i++) {
			int r1 = rec1.get(i);
			int r2 = rec2.get(i);
			for (int s = p1; s < r1; s++)
				resultList.add(new ResultModel(ResultModel.DELETE, lst1.get(s)));
			for (int s = p2; s < r2; s++)
				resultList.add(new ResultModel(ResultModel.ADD, lst2.get(s)));
			resultList.add(new ResultModel(ResultModel.NORMAL, lst1.get(r1)));
			p1 = r1 + 1;
			p2 = r2 + 1;
		}

		for (int s = p1; s < lst1.size(); s++)
			resultList.add(new ResultModel(ResultModel.DELETE, lst1.get(s)));

		for (int s = p2; s < lst2.size(); s++)
			resultList.add(new ResultModel(ResultModel.ADD, lst2.get(s)));

		return resultList;
	}

	public static void main(String[] args) throws IOException {
		List<ResultModel> lst = new DiffRunner().diffRun("D:\\hashTable.cpp", "D:\\hashTable (2).cpp");
		new HtmlOutputer().output("Test", new FileOutputStream("D:\\k.html"), lst);
	}
}
