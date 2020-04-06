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

import com.gc_company.enity.Ticket;
import com.gc_company.enity.User;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.proxy.UserServiceProxy;

public class UserManagementFrame {

	JFrame mainFrame;
	JTable resultTable;
	JScrollPane jScrollPane;
	JButton forbidButton;
	JButton removeForbidButton;
	JButton backButton;
	UserServiceProxy userServiceProxy;

	public void init() {
		mainFrame = new JFrame("购票系统-用户管理页面");
		resultTable = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jScrollPane = new JScrollPane(resultTable);
		forbidButton = new JButton("封号");
		removeForbidButton = new JButton("解封");
		backButton = new JButton("返回主界面");
		userServiceProxy = (UserServiceProxy) ObjectFactory.getObject("userServiceProxy");
	}

	public void build() {
		mainFrame.setBounds(300, 200, 300, 400);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);

		jScrollPane.setBounds(20, 20, 260, 280);
		mainFrame.add(jScrollPane);

		forbidButton.setBounds(20, 330, 60, 25);
		mainFrame.add(forbidButton);

		removeForbidButton.setBounds(100, 330, 60, 25);
		mainFrame.add(removeForbidButton);

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

	// 封号
	public void forbidButtonSetActionListener() {
		forbidButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = resultTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(mainFrame, "您还没有选择封号对象", "删除失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String userName = String.valueOf(resultTable.getValueAt(row, 0));
				String state = String.valueOf(resultTable.getValueAt(row, 2));
				if ("管理员".equals(state) || "被封用户".equals(state)) {
					return;
				} else {
					try {
						userServiceProxy.updateUserState(userName, "被封用户");
						//刷新界面
						showAllUsers();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "封号失败", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	// 解封
	public void removeForbidButtonSetActionListener() {
		removeForbidButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = resultTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(mainFrame, "您还没有选择解封对象", "解封失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String userName = String.valueOf(resultTable.getValueAt(row, 0));
				String state = String.valueOf(resultTable.getValueAt(row, 2));
				if ("管理员".equals(state) || "正常用户".equals(state)) {
					return;
				} else {
					try {
						userServiceProxy.updateUserState(userName, "正常用户");
						//刷新界面
						showAllUsers();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "解封失败", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	// 显示所有用户
	public void showAllUsers() {
		try {
			List<User> list = userServiceProxy.showAllUsers();
			String[] columns = new String[] { "用户帐号", "余额", "状态" };
			Object[][] data = new Object[list.size()][columns.length];
			for (int i = 0; i < list.size(); i++) {
				User user = list.get(i);
				data[i][0] = user.getUsername();
				data[i][1] = user.getMoney();
				data[i][2] = user.getState();
			}
			DefaultTableModel defaultTableModel = new DefaultTableModel(data, columns);
			resultTable.setModel(defaultTableModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addAction() {
		backButtonSetActionListener();
		forbidButtonSetActionListener();
		removeForbidButtonSetActionListener();
	}

	public void start() {
		init();
		addAction();
		build();
		showAllUsers();
	}

}
