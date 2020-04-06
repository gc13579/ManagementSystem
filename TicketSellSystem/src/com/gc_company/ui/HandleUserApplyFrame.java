package com.gc_company.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.gc_company.enity.InvestApplyHistory;
import com.gc_company.enity.RechargeApply;
import com.gc_company.enity.User;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.proxy.InvestApplyServiceProxy;
import com.gc_company.service.proxy.RechargeApplyServiceProxy;
import com.gc_company.service.proxy.UserServiceProxy;

public class HandleUserApplyFrame {
	JFrame mainFrame;
	JTable resultTable;
	JScrollPane jScrollPane;
	JButton agreeApplyButton;
	JButton backButton;
	UserServiceProxy userServiceProxy;
	RechargeApplyServiceProxy rechargeApplyServiceProxy;
	InvestApplyServiceProxy investApplyServiceProxy;

	public void init() {
		mainFrame = new JFrame("购票系统-处理用户申请页面");
		resultTable = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jScrollPane = new JScrollPane(resultTable);
		agreeApplyButton = new JButton("同意");
		backButton = new JButton("返回主界面");
		userServiceProxy = (UserServiceProxy) ObjectFactory.getObject("userServiceProxy");
		rechargeApplyServiceProxy = (RechargeApplyServiceProxy) ObjectFactory.getObject("rechargeApplyServiceProxy");
		investApplyServiceProxy = (InvestApplyServiceProxy) ObjectFactory.getObject("investApplyServiceProxy");
	}

	public void build() {
		mainFrame.setBounds(300, 200, 330, 400);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);

		jScrollPane.setBounds(20, 20, 280, 280);
		mainFrame.add(jScrollPane);

		agreeApplyButton.setBounds(20, 330, 60, 25);
		mainFrame.add(agreeApplyButton);

		backButton.setBounds(180, 330, 100, 25);
		mainFrame.add(backButton);

		mainFrame.setVisible(true);
	}

	// 返回主界面
	public void backButtonSetActionListener() {
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AdminFrame().start();
				mainFrame.setVisible(false);
			}
		});
	}

	// 同意申请
	public void agreeApplyButtonSetActionListener() {
		agreeApplyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = resultTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(mainFrame, "您还没有选择要处理的申请", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				RechargeApply rechargeApply = new RechargeApply();
				rechargeApply.setApplyMoney(Double.valueOf(String.valueOf(resultTable.getValueAt(row, 1))));
				rechargeApply.setApplyTime(String.valueOf(resultTable.getValueAt(row, 2)));
				rechargeApply.setApplyState(String.valueOf(resultTable.getValueAt(row, 3)));
				if ("正在申请".equals(rechargeApply.getApplyState())) {
					try {
						String username = String.valueOf(resultTable.getValueAt(row, 0));
						Double money = Double.valueOf(String.valueOf(resultTable.getValueAt(row, 1)));
						//跟新数据，内容包括：
						//更新申请的状态，用户加钱
						rechargeApplyServiceProxy.updateOneRecord(rechargeApply,username,money);
//						try {
//							// 找人
//							User user = userServiceProxy.selectUserByUsername(username);
//							// 设置钱
//							userServiceProxy.updateUserMonney(username, user.getMoney()+money);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(mainFrame, "不能重复批准", "", JOptionPane.ERROR_MESSAGE);
				}
				showAllApplies();
//				String username = String.valueOf(resultTable.getValueAt(row, 0));
//				Double money = Double.valueOf(String.valueOf(resultTable.getValueAt(row, 1)));
//				try {
//					// 找人
//					User user = userServiceProxy.selectUserByUsername(username);
//					// 设置钱
//					userServiceProxy.updateUserMonney(username, user.getMoney()+money);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}
		});
	}

	// 显示所有用户申请
	public void showAllApplies() {
		try {
			List<InvestApplyHistory> list = investApplyServiceProxy.showAllRecords();
			String[] columns = new String[] { "用 户 帐 号", "申 请 金 额", "申 请 时 间", "申 请 状 态" };
			Object[][] data = new Object[list.size()][columns.length];
			for (int i = 0; i < list.size(); i++) {
				InvestApplyHistory investApplyHistory = list.get(i);
				data[i][0] = investApplyHistory.getUserName();
				data[i][1] = investApplyHistory.getApplyMoney();
				data[i][2] = investApplyHistory.getApplyTime();
				data[i][3] = investApplyHistory.getApplyState();
			}
			DefaultTableModel defaultTableModel = new DefaultTableModel(data, columns);
			resultTable.setModel(defaultTableModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addAction() {
		backButtonSetActionListener();
		agreeApplyButtonSetActionListener();
	}

	public void start() {
		init();
		addAction();
		build();
		showAllApplies();
	}

}
