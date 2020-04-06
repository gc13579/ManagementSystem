package com.gc_company.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.management.StringValueExp;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.gc_company.Constant.Constant;
import com.gc_company.enity.BuyHistory;
import com.gc_company.enity.PurchaseRecord;
import com.gc_company.enity.Ticket;
import com.gc_company.enity.User;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.proxy.BuyHistoryServiceProxy;
import com.gc_company.service.proxy.PurchaseRecordServiceProxy;
import com.gc_company.service.proxy.RechargeApplyServiceProxy;
import com.gc_company.service.proxy.TicketServiceProxy;
import com.gc_company.service.proxy.UserServiceProxy;

public class OwnerCenterFrame {
	JFrame mainFrame;
	JTable resultTable;
	JButton rechargeButton;
	JButton returnTicketButton;
	JButton backButton;
	JScrollPane jScrollPane;
	User user;
	PurchaseRecordServiceProxy purchaseRecordServiceProxy;
	UserServiceProxy userServiceProxy;
	RechargeApplyServiceProxy rechargeApplyServiceProxy;
	TicketServiceProxy ticketServiceProxy;
	BuyHistoryServiceProxy buyHistoryServiceProxy;

	public OwnerCenterFrame(User user) {
		this.user = user;
	}

	public void init() {
		mainFrame = new JFrame("购票系统-用户中心");
		resultTable = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jScrollPane = new JScrollPane(resultTable);
		rechargeButton = new JButton("充值");
		returnTicketButton = new JButton("退票");
		backButton = new JButton("返回主界面");
		purchaseRecordServiceProxy = (PurchaseRecordServiceProxy) ObjectFactory
				.getObject("purchaseRecordProxy");
		userServiceProxy = (UserServiceProxy) ObjectFactory
				.getObject("userServiceProxy");
		rechargeApplyServiceProxy = (RechargeApplyServiceProxy) ObjectFactory
				.getObject("rechargeApplyServiceProxy");
		ticketServiceProxy = (TicketServiceProxy) ObjectFactory
				.getObject("ticketServiceProxy");
		buyHistoryServiceProxy = (BuyHistoryServiceProxy) ObjectFactory
				.getObject("buyHistoryServiceProxy");
	}

	public void build() {
		mainFrame.setBounds(400, 200, 600, 450);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);

		jScrollPane.setBounds(30, 30, 550, 330);
		mainFrame.add(jScrollPane);

		rechargeButton.setBounds(30, 370, 60, 25);
		mainFrame.add(rechargeButton);

		returnTicketButton.setBounds(160, 370, 60, 25);
		mainFrame.add(returnTicketButton);

		backButton.setBounds(290, 370, 110, 25);
		mainFrame.add(backButton);

		mainFrame.setVisible(true);
	}

	// 充值
	public void rechargeButtonSetActionListener() {
		rechargeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String string = JOptionPane.showInputDialog(mainFrame,
						"请选择充值金额", "欢迎充值", JOptionPane.QUESTION_MESSAGE);
				StringBuffer errorMessages = new StringBuffer();
				try {
					if (string != null) {
						Double money = Double.valueOf(string);
						if (money <= 0) {
							errorMessages.append("充值的金额必须大于0").append("\n");
						}
					}
					// rechargeApplyServiceProxy.addOneRecord(user.getId(),
					// Double.valueOf(string), new
					// SimpleDateFormat("yyyy-MM-dd").format(new
					// java.util.Date()), "正在申请");
					// JOptionPane.showMessageDialog(mainFrame,
					// "已提交申请，请等待管理员审核", "提交申请成功",
					// JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException e2) {
					errorMessages.append("充值金额格式不正确").append("\n");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (errorMessages.length() != 0) {
					JOptionPane.showMessageDialog(mainFrame,
							errorMessages.toString(), "充值错误",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(mainFrame, "已提交申请，请等待管理员审核",
							"提交申请成功", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	// 退票
	public void returnTicketButtonSetActionListener() {
		returnTicketButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int row = resultTable.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(mainFrame, "您没有选择要退的票",
								"退票失败", JOptionPane.ERROR_MESSAGE);
						return;
					}
					String trainNum = String.valueOf(resultTable.getValueAt(
							row, 0));
					String state = String.valueOf(resultTable
							.getValueAt(row, 6));
					Double price = Double.valueOf(String.valueOf(resultTable
							.getValueAt(row, 4)));
					if ("已退票".equals(state)) {
						JOptionPane.showMessageDialog(mainFrame, "您已退票，不能重复退票",
								"退票失败", JOptionPane.ERROR_MESSAGE);
						return;
					}
					purchaseRecordServiceProxy.returnOneTicket(trainNum, user);
					// Ticket ticket =
					// ticketServiceProxy.selectOneTicketByTrainnum(trainNum);
					// purchaseRecordServiceProxy.returnOneTicket(ticket.getId());
					// userServiceProxy.updateUserMonney(user.getUsername(),
					// user.getMoney() + (0.8 * price));
					user.setMoney(user.getMoney() + (0.8 * price));
					JOptionPane.showMessageDialog(mainFrame, "退票成功，扣除您20%手续费",
							"退票成功", JOptionPane.INFORMATION_MESSAGE);
					// 退票后刷新页面
					showAllBuyRecord();
					// ticket.setCount(ticket.getCount() + 1);
					// ticketServiceProxy.updateOneTicket(ticket);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void backButtonSetActionListener() {
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User user2 = null;
				try {
					user2 = userServiceProxy.selectUserByUsername(user
							.getUsername());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				new UserMainFrame(user2).start();
				mainFrame.setVisible(false);
			}
		});
	}

	public void showAllBuyRecord() {
		try {
			List<BuyHistory> list = buyHistoryServiceProxy
					.showAllBuyHistory(user.getId());
			String[] columns = new String[] { "购买车次", "起点", "终点", "出发时间", "票价",
					"购买时间", "购买状态" };
			Object[][] data = new Object[list.size()][columns.length];
			for (int i = 0; i < list.size(); i++) {
				BuyHistory buyHistory = list.get(i);
				data[i][0] = buyHistory.getTrainNum();
				data[i][1] = buyHistory.getStartPlace();
				data[i][2] = buyHistory.getEndPlace();
				data[i][3] = buyHistory.getStartTime();
				data[i][4] = buyHistory.getTarinPrice();
				data[i][5] = buyHistory.getBuyTime();
				data[i][6] = buyHistory.getBuyState();
			}
			DefaultTableModel defaultTableModel = new DefaultTableModel(data,
					columns);
			resultTable.setModel(defaultTableModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addAction() {
		rechargeButtonSetActionListener();
		returnTicketButtonSetActionListener();
		backButtonSetActionListener();
	}

	public void start() {
		init();
		addAction();
		build();
		showAllBuyRecord();
	}
}
