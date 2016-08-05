package com.tianhe.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
	public List<String> getLines(String path) throws IOException {
		File f = new File(path);
		if (!f.exists() || f.isDirectory())
			return null;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
		BufferedReader br = new BufferedReader(isr);
		String line;
		List<String> resultList = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			resultList.add(line);
		}
		br.close();
		return resultList;
	}
}
