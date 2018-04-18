package kh.java.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class MyFrame2 extends JFrame {

	private SpinnerModel spm = new SpinnerNumberModel(0, -100, 100, 1);
	private JSpinner jsp = new JSpinner(spm);
	private Container ct = getContentPane();
	private JButton jbtAdd = new JButton("출력");
	private JLabel jLabel = new JLabel("0", JLabel.CENTER);

	public MyFrame2() {
		setSize(400, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jLabel.setPreferredSize(new Dimension(200, 300));
		jLabel.setFont(new Font("맑은고딕", Font.BOLD, 30));
		ct.setLayout(new BorderLayout());
		ct.add(jLabel, BorderLayout.NORTH);
		ct.add(jbtAdd, BorderLayout.SOUTH);
		ct.add(jsp, BorderLayout.CENTER);

		jbtAdd.addActionListener(e -> {
			jLabel.setText(String.valueOf(jsp.getValue()));
		});
	}

}