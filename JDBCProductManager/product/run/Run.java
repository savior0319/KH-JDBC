package product.run;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import product.view.ProductMangerView;

public class Run {

	public static void main(String[] args) {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}
		new ProductMangerView().view();
	}
}
