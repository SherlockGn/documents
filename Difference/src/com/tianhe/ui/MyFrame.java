package com.tianhe.ui;

import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JOptionPane;

import com.tianhe.resultmodel.ResultModel;
import com.tianhe.util.DiffRunner;
import com.tianhe.util.HtmlOutputer;

public class MyFrame {

	private Frame fr;
	private Button btn1, btn2, btn;
	private Label lb1, lb2, lb3;
	private TextField tf1, tf2, tf3;

	public MyFrame() {
		init();
		setBounds();
		addComps();
		addListeners();
	}

	public static void main(String[] args) {
		new MyFrame();
	}

	void init() {
		fr = new Frame();
		fr.setVisible(true);
		fr.setLayout(null);
		fr.setResizable(false);

		lb1 = new Label("file1");
		lb2 = new Label("file2");
		lb3 = new Label("output");

		tf1 = new TextField();
		tf2 = new TextField();
		tf3 = new TextField("D:\\output.html");

		btn1 = new Button();
		btn2 = new Button();
		btn = new Button("run");

	}

	void setBounds() {
		fr.setBounds(300, 300, 200, 200);
		lb1.setBounds(20, 50, 50, 20);
		lb2.setBounds(20, 80, 50, 20);
		lb3.setBounds(20, 110, 50, 20);
		tf1.setBounds(70, 50, 70, 20);
		tf2.setBounds(70, 80, 70, 20);
		tf3.setBounds(70, 110, 70, 20);
		btn1.setBounds(160, 50, 20, 20);
		btn2.setBounds(160, 80, 20, 20);
		btn.setBounds(80, 150, 50, 20);
	}

	void addComps() {
		fr.add(btn1);
		fr.add(btn2);
		fr.add(lb1);
		fr.add(lb2);
		fr.add(lb3);
		fr.add(tf1);
		fr.add(tf2);
		fr.add(tf3);
		fr.add(btn);
	}

	void addListeners() {
		fr.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(fr, "choose a file");
				fd.setMode(FileDialog.LOAD);
				fd.setVisible(true);
				String path = fd.getDirectory() + fd.getFile();
				tf1.setText(path);
			}
		});

		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(fr, "choose a file");
				fd.setMode(FileDialog.LOAD);
				fd.setVisible(true);
				String path = fd.getDirectory() + fd.getFile();
				tf2.setText(path);
			}
		});

		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<ResultModel> lst = new DiffRunner().diffRun(tf1.getText(), tf2.getText());
					File f1 = new File(tf1.getText());
					File f2 = new File(tf2.getText());
					String title = f1.getName() + " -> " + f2.getName();
					new HtmlOutputer().output(title, new FileOutputStream(tf3.getText()), lst);
					Runtime.getRuntime().exec("cmd /c start " + tf3.getText());
				} catch (Exception ex) {
					alert("error occored: " + ex.getMessage(), ex.getClass().getName());
				}
			}
		});
	}

	public void alert(String msg, String title) {
		JOptionPane.showMessageDialog(fr, msg, title, JOptionPane.INFORMATION_MESSAGE);
	}
}
