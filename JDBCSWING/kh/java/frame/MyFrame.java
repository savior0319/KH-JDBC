package kh.java.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MyFrame extends JFrame implements MouseListener {

	private String[] columnNames = { "이름", "나이", "주소" };

	private Object[][] data = { { "홍길동", "20", "경기도 부천" }, { "김말똥", "30", "서울시 관악" }, { "고길동", "40", "제주도 서귀포" } };
	private DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
	private JTable jtb = new JTable(dtm);
	private JScrollPane jsp = new JScrollPane(jtb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private Container ct = getContentPane();
	private JLabel jlbName = new JLabel("이름", JLabel.CENTER);
	private JLabel jlbAge = new JLabel("나이", JLabel.CENTER);
	private JLabel jlbAddress = new JLabel("주소", JLabel.CENTER);
	private JTextField jtfName = new JTextField(10);
	private JTextField jtfAge = new JTextField(10);
	private JTextField jtfAddress = new JTextField(10);
	private JPanel mainPanel = new JPanel();
	private JPanel secondMainPanel = new JPanel();
	private JPanel secondWestPanel = new JPanel();
	private JPanel secondEastPanel = new JPanel();
	private JButton jbtAdd = new JButton("추가");
	private JButton jbtDelete = new JButton("삭제");
	private JButton jbtUpdate = new JButton("수정");

	public MyFrame() {
		setTitle("TEST");
		setVisible(true);
		setSize(485, 550);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		ct.setLayout(new BorderLayout());

		mainPanel.add(jsp);

		secondMainPanel.setLayout(new BorderLayout());
		secondMainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 50));

		secondWestPanel.setLayout(new GridLayout(3, 2));
		secondWestPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		jlbName.setFont(new Font("맑은고딕", Font.PLAIN, 13));
		jlbAge.setFont(new Font("맑은고딕", Font.PLAIN, 13));
		jlbAddress.setFont(new Font("맑은고딕", Font.PLAIN, 13));
		secondWestPanel.add(jlbName);
		secondWestPanel.add(jtfName);
		secondWestPanel.add(jlbAge);
		secondWestPanel.add(jtfAge);
		secondWestPanel.add(jlbAddress);
		secondWestPanel.add(jtfAddress);

		secondEastPanel.setLayout(new GridLayout(1, 3, 3, 3));
		jbtAdd.setFont(new Font("맑은고딕", Font.PLAIN, 12));
		jbtDelete.setFont(new Font("맑은고딕", Font.PLAIN, 12));
		jbtUpdate.setFont(new Font("맑은고딕", Font.PLAIN, 12));
		secondEastPanel.add(jbtAdd);
		secondEastPanel.add(jbtDelete);
		secondEastPanel.add(jbtUpdate);

		secondMainPanel.add(secondWestPanel, BorderLayout.WEST);
		secondMainPanel.add(secondEastPanel, BorderLayout.CENTER);
		ct.add(mainPanel, BorderLayout.CENTER);
		ct.add(secondMainPanel, BorderLayout.SOUTH);

		jbtAdd.addActionListener(e -> {
			String name, address, age;
			name = jtfName.getText();
			address = jtfAddress.getText();
			age = jtfAge.getText();
			if (!(name.equals("") || address.equals("") || age.equals(""))) {
				dtm.addRow(new Object[] { name, age, address }); 
			}
			jtfName.setText("");
			jtfAddress.setText("");
			jtfAge.setText("");
		});

		jbtDelete.addActionListener(e -> {
			int row = jtb.getSelectedRow();
			dtm.removeRow(row);
			jtfName.setText("");
			jtfAge.setText("");
			jtfAddress.setText("");
		});

		jbtUpdate.addActionListener(e -> {
			int row = jtb.getSelectedRow();
			if (!(jtfName.getText().equals("") || jtfAge.getText().equals("") || jtfAddress.getText().equals(""))) {
				jtb.setValueAt(jtfName.getText(), row, 0);
				jtb.setValueAt(jtfAge.getText(), row, 1);
				jtb.setValueAt(jtfAddress.getText(), row, 2);
			}
			jtb.clearSelection();
			jtfName.setText("");
			jtfAge.setText("");
			jtfAddress.setText("");
		});
		jtb.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == jtb) {
			int row = jtb.getSelectedRow();
			String name = (String) jtb.getValueAt(row, 0);
			String age = (String) jtb.getValueAt(row, 1);
			String address = (String) jtb.getValueAt(row, 2);
			jtfName.setText(name);
			jtfAge.setText(age);
			jtfAddress.setText(address);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
