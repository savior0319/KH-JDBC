package product.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import oracle.net.aso.e;
import product.Controller.ProductController;
import product.model.VO.ProductVO;

@SuppressWarnings("all")

public class ProductMangerView extends JFrame {

	private ProductController pc = new ProductController();

	private Container ct = getContentPane();
	private JRadioButton jrbPID = new JRadioButton("Product ID");
	private JRadioButton jrbPName = new JRadioButton("Product Name");
	private ButtonGroup bg = new ButtonGroup();

	private JButton jbtList = new JButton("목록보기");
	private JButton jbtSearch = new JButton("검색");
	private JButton jbtAdd = new JButton("추가");
	private JButton jbtUpdate = new JButton("수정");
	private JButton jbtDelete = new JButton("삭제");

	private JLabel jlbDetail = new JLabel("──── 상세 보기 ────");
	private JLabel jlbId = new JLabel("상품 ID : ");
	private JLabel jlbName = new JLabel("상 품 명 :");
	private JLabel jlbPrice = new JLabel("가   격");
	private JLabel jlbDesc = new JLabel("상품설명 : ");

	private JTextField jtfSearchContent = new JTextField();
	private JTextField jtfId = new JTextField();
	private JTextField jtfName = new JTextField();
	private JTextField jtfDesc = new JTextField();

	private JPanel jpEast = new JPanel();
	private JPanel jpEastUp = new JPanel();
	private JPanel jpEastUpNorth = new JPanel();
	private JPanel jpEastUpSouth = new JPanel();

	private JPanel jpEastDown = new JPanel();
	private JPanel jpEastDownNorth = new JPanel();
	private JPanel jpEastDownCenter = new JPanel();
	private JPanel jpEastDownSouth = new JPanel();

	private JPanel jpCenter = new JPanel();

	private String[] columnNames = { "상품 ID", "상품 이름", "가격", "상품설명" };

	private Object[][] data = { { "TESTID", "TESTNAME", "1000000", "TESTDESC" } };
	private DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
	private JTable jtb = new JTable(dtm);
	private JScrollPane jsp = new JScrollPane(jtb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	private SpinnerModel spm = new SpinnerNumberModel(0, 0, 100000000, 50000);
	private JSpinner jspn = new JSpinner(spm);

	public ProductMangerView() {
	}

	public void view() {
		setTitle("상품 관리 프로그램");
		setVisible(true);
		setSize(850, 470);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jbtList.setFont(new Font("맑은고딕", Font.PLAIN, 12));
		jrbPID.setFont(new Font("맑은고딕", Font.PLAIN, 12));
		jrbPName.setFont(new Font("맑은고딕", Font.PLAIN, 12));
		jbtSearch.setFont(new Font("맑은고딕", Font.PLAIN, 12));
		jbtAdd.setFont(new Font("맑은고딕", Font.PLAIN, 12));
		jbtUpdate.setFont(new Font("맑은고딕", Font.PLAIN, 12));
		jbtDelete.setFont(new Font("맑은고딕", Font.PLAIN, 12));
		jlbDetail.setFont(new Font("맑은고딕", Font.PLAIN, 12));
		jlbId.setFont(new Font("맑은고딕", Font.PLAIN, 12));
		jlbName.setFont(new Font("맑은고딕", Font.PLAIN, 12));
		jlbPrice.setFont(new Font("맑은고딕", Font.PLAIN, 12));
		jlbDesc.setFont(new Font("맑은고딕", Font.PLAIN, 12));

		bg.add(jrbPID);
		bg.add(jrbPName);

		ct.setLayout(new BorderLayout());

		jpEast.setLayout(new BorderLayout());
		jpEastUp.setLayout(new BorderLayout());

		jpEastUpNorth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jpEastUpNorth.setLayout(new GridLayout(1, 3));

		jpEastUpNorth.add(jrbPID);
		jpEastUpNorth.add(jrbPName);
		jpEastUpNorth.add(jbtList);

		jpEastUpSouth.setLayout(new BorderLayout());
		jpEastUpSouth.add(jtfSearchContent, BorderLayout.CENTER);
		jpEastUpSouth.add(jbtSearch, BorderLayout.EAST);

		jpEastUp.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		jpEastUp.add(jpEastUpNorth, BorderLayout.NORTH);
		jpEastUp.add(jpEastUpSouth, BorderLayout.SOUTH);

		jpEastDown.setLayout(new BorderLayout());

		jpEastDownNorth.add(jlbDetail);
		jpEastDownNorth.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));
		jpEastDown.add(jpEastDownNorth, BorderLayout.NORTH);

		jpEastDownCenter.setLayout(new GridLayout(4, 2, 25, 25));
		jpEastDownCenter.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		jpEastDownCenter.add(jlbId);
		jpEastDownCenter.add(jtfId);
		jpEastDownCenter.add(jlbName);
		jpEastDownCenter.add(jtfName);
		jpEastDownCenter.add(jlbPrice);
		jpEastDownCenter.add(jspn);
		jpEastDownCenter.add(jlbDesc);
		jpEastDownCenter.add(jtfDesc);

		jpEastDownSouth.setLayout(new GridLayout(1, 3, 10, 10));
		jpEastDownSouth.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
		jpEastDownSouth.add(jbtAdd);
		jpEastDownSouth.add(jbtUpdate);
		jpEastDownSouth.add(jbtDelete);

		jpEastDown.add(jpEastDownCenter, BorderLayout.CENTER);
		jpEastDown.add(jpEastDownSouth, BorderLayout.SOUTH);

		jpEast.add(jpEastUp, BorderLayout.NORTH);
		jpEast.add(jpEastDown, BorderLayout.CENTER);

		jpCenter.add(jsp);

		ct.add(jpCenter, BorderLayout.CENTER);
		ct.add(jpEast, BorderLayout.EAST);

		jbtList.addActionListener(e -> {
			getProductAll();
		});
	}

	public void getProductAll() {
		ArrayList<ProductVO> aList = pc.getProductAll();
		if (aList == null) {
			System.out.println("상품정보 x");
		} else {
			Iterator<ProductVO> it = aList.iterator();
			while (it.hasNext()) {
				System.out.println(it.next().toString());
			}
		}
	}
}
