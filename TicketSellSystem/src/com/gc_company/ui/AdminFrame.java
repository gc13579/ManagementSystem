package com.gc_company.ui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import com.gc_company.Constant.Constant;
import com.gc_company.enity.Ticket;
import com.gc_company.enity.User;
import com.gc_company.exception.InsufficientMoneyException;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.proxy.TicketServiceProxy;
import com.gc_company.util.DateVaileUtil;
import com.gc_company.util.Pager;

public class AdminFrame {
	JFrame mainFrame;
	JLabel startPlaceLabel;
	JTextField startPlaceField;
	JLabel endPlacrLabel;
	JTextField endPlaceField;
	JLabel startTimeLabel;
	JTextField startTimeField;
	JButton searchButton;
	JButton userManagementButton;
	JButton handleUserApplyButton;
	JTable resultTable;
	JButton firstPageButton;
	JButton lastPageButton;
	JButton nextPageButton;
	JButton endPageButton;
	JButton deleteButton;
	JButton updateButton;
	JButton addButton;
	TicketServiceProxy ticketServiceProxy;
	JScrollPane jScrollPane;
	Pager<Ticket> page;
	String lastStartPlace;
	String lastEndPlace;
	String lastStartTime;
	User user;

	public void init() {
		mainFrame = new JFrame("购票系统-管理员主页面");
		startPlaceLabel = new JLabel("起点:");
		startPlaceField = new JTextField();
		endPlacrLabel = new JLabel("终点:");
		endPlaceField = new JTextField();
		startTimeLabel = new JLabel("出发时间:");
		startTimeField = new JTextField("请按照以下格式输入,例:2000-05-03");
		searchButton = new JButton("查询");
		userManagementButton = new JButton("用户管理");
		handleUserApplyButton = new JButton("处理申请");
		resultTable = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		firstPageButton = new JButton("首页");
		lastPageButton = new JButton("上一页");
		nextPageButton = new JButton("下一页");
		endPageButton = new JButton("末页");
		deleteButton = new JButton("删除");
		updateButton = new JButton("修改");
		addButton = new JButton("添加车票");
		ticketServiceProxy = (TicketServiceProxy) ObjectFactory
				.getObject("ticketServiceProxy");
		jScrollPane = new JScrollPane(resultTable);
		lastStartPlace = "";
		lastEndPlace = "";
		lastStartTime = "";
	}

	public void build() {
		mainFrame.setBounds(100, 100, 820, 460);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);

		startPlaceLabel.setBounds(25, 25, 40, 25);
		mainFrame.add(startPlaceLabel);

		startPlaceField.setBounds(65, 25, 80, 25);
		mainFrame.add(startPlaceField);

		endPlacrLabel.setBounds(170, 25, 40, 25);
		mainFrame.add(endPlacrLabel);

		endPlaceField.setBounds(210, 25, 80, 25);
		mainFrame.add(endPlaceField);

		startTimeLabel.setBounds(315, 25, 60, 25);
		mainFrame.add(startTimeLabel);

		startTimeField.setBounds(385, 25, 200, 25);
		mainFrame.add(startTimeField);

		searchButton.setBounds(605, 25, 60, 25);
		mainFrame.add(searchButton);

		userManagementButton.setBounds(690, 10, 100, 25);
		mainFrame.add(userManagementButton);

		handleUserApplyButton.setBounds(690, 40, 100, 25);
		mainFrame.add(handleUserApplyButton);

		jScrollPane.setBounds(35, 70, 750, 300);
		mainFrame.add(jScrollPane);

		firstPageButton.setBounds(30, 385, 60, 25);
		mainFrame.add(firstPageButton);

		lastPageButton.setBounds(130, 385, 80, 25);
		mainFrame.add(lastPageButton);

		nextPageButton.setBounds(250, 385, 80, 25);
		mainFrame.add(nextPageButton);

		endPageButton.setBounds(370, 385, 60, 25);
		mainFrame.add(endPageButton);

		deleteButton.setBounds(470, 385, 60, 25);
		mainFrame.add(deleteButton);

		updateButton.setBounds(570, 385, 60, 25);
		mainFrame.add(updateButton);

		addButton.setBounds(670, 385, 95, 25);
		mainFrame.add(addButton);

		mainFrame.setVisible(true);
	}

	public void addAction() {
		startTimeFieldSetFocusListener();
		searchButtonSetActionListener();
		lastPageButtonSetActionListener();
		nextPageButtonSetActionListener();
		firstPageButtonSetActionListener();
		endPageButtonSetActionListener();
		deleteButtonSetActionListener();
		userManagementButtonSetActionListener();
		addButtonSetActionListener();
		updateButtonSetActionListener();
		handleUserApplyButtonSetActionListener();
	}

	public void startTimeFieldSetFocusListener() {
		startTimeField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// 出发时间的框失去焦点，且框内容为空时，把默认显示的字符串显示
				if ("".equals(startTimeField.getText())) {
					startTimeField.setText("请按照以下格式输入,例:2000-05-03");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// 出发时间的框获得焦点，把内容清空，等待用户输入时间
				if ("请按照以下格式输入,例:2000-05-03".equals(startTimeField.getText())) {
					startTimeField.setText("");
				}
			}
		});
	}

	// 首页按钮
	public void firstPageButtonSetActionListener() {
		firstPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (page != null) {
					selectAndShowOnePage(1);
				}
			}
		});
	}

	// 上一页按钮
	public void lastPageButtonSetActionListener() {
		lastPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (page != null) {
					selectAndShowOnePage(page.getLastPageNum());
				}
			}
		});
	}

	// 下一页按钮
	public void nextPageButtonSetActionListener() {
		nextPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (page != null) {
					selectAndShowOnePage(page.getNextPageNum());
				}
			}
		});
	}

	// 末页按钮
	public void endPageButtonSetActionListener() {
		endPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (page != null) {
					selectAndShowOnePage(page.getTotalPage());
				}
			}
		});
	}

	// 查询按钮
	public void searchButtonSetActionListener() {
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectAndShowOnePage(1);
			}
		});

	}

	// 用户管理
	public void userManagementButtonSetActionListener() {
		userManagementButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserManagementFrame().start();
				mainFrame.setVisible(false);
			}
		});
	}

	// 处理申请
	public void handleUserApplyButtonSetActionListener() {
		handleUserApplyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new HandleUserApplyFrame().start();
				mainFrame.setVisible(false);
			}
		});
	}

	// 修改车票
	public void updateButtonSetActionListener() {
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StringBuffer errorMessages = new StringBuffer();
				int row = resultTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(mainFrame, "您还没有选择车次",
							"修改失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String trainNum = String.valueOf(resultTable.getValueAt(row, 0));
				String startPlace = String.valueOf(resultTable.getValueAt(row,
						1));
				String endPlace = String.valueOf(resultTable.getValueAt(row, 2));
				String startTime = String.valueOf(resultTable
						.getValueAt(row, 3));
				String ticketCount = String.valueOf(resultTable.getValueAt(row,
						4));
				String ticketPrice = String.valueOf(resultTable.getValueAt(row,
						5));
				String state = String.valueOf(resultTable.getValueAt(row, 6));
				// 表单验证
				// 车次不符合命名规则 一个大写字母+(2~4)个数字
				if (!trainNum.matches("[K,D,T,G,Z]{1}\\d{2,4}")) {
					errorMessages.append("车次名称不规范，请重新输入").append("\n");
				}
				// 起点终点相同
				if (startPlace.equals(endPlace)) {
					errorMessages.append("起点终点不能相同，请重新输入").append("\n");
				}
				// 起点终点不能为空
				if ("".equals(startPlace.trim()) || "".equals(endPlace.trim())) {
					errorMessages.append("起点或终点不能为空，请重新输入").append("\n");
				}
				// 时间格式不正确
				try {
					if (!DateVaileUtil.isValidDate(startTime)) {
						errorMessages.append("时间格式不规范，请重新输入").append("\n");
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 票数格式不正确
				if (!ticketCount.matches("[0-9]*")) {
					errorMessages.append("票数格式不规范，请重新输入").append("\n");
				}
				// 票价格式不正确
				if (!ticketPrice.matches("[1-9]{1}(\\d*(.)?\\d+)?")) {
					errorMessages.append("票价不规范，请重新输入").append("\n");
				}
				// 显示所有表单验证的错误
				if (errorMessages.length() != 0) {
					JOptionPane.showMessageDialog(mainFrame, errorMessages,
							"添加失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Ticket ticket = new Ticket();
				ticket.setTrainnum(trainNum);
				ticket.setStartPlace(startPlace);
				ticket.setEndPlace(endPlace);
				ticket.setStartTime(startTime);
				ticket.setCount(Integer.valueOf(ticketCount));
				ticket.setPrice(Double.valueOf(ticketPrice));
				ticket.setState(state);
				mainFrame.setVisible(false);
				new UpdateTicketFrame(ticket).start();
			}
		});
	}

	// 添加车票
	public void addButtonSetActionListener() {
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddTicketFrame().start();
				mainFrame.setVisible(false);
			}
		});
	}

	// 删除车次按钮
	public void deleteButtonSetActionListener() {
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StringBuffer errorMessages = new StringBuffer();
				int row = resultTable.getSelectedRow();
				int column = 0;
				int columnOfState = 6;
				// 表单验证
				// 没有选择车次
				if (row == -1) {
					JOptionPane.showMessageDialog(mainFrame, "您还没有选择车次",
							"删除失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String trainnum = null;
				try {
					trainnum = String.valueOf(resultTable.getValueAt(row,
							column));
					// 直接硬删除车次，当报错时，表明此车次被购买过，在异常中处理为软删除
					ticketServiceProxy.deleteOneTicket(trainnum);
					// 买票后，自动刷新当前页面
					selectAndShowOnePage(page.getPageNum());
				} catch (InsufficientMoneyException e) {
					errorMessages.append(e.getMessage()).append("\n");
				} catch (SQLException e) {
					try {
						// 软删除
						ticketServiceProxy.updateOneTicketStateByTrainnum(
								trainnum, "已删除");
						selectAndShowOnePage(page.getPageNum());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					return;
				} catch (Exception e) {
					errorMessages.append(e.getMessage()).append("\n");
				}
				if (errorMessages.length() != 0) {
					JOptionPane.showMessageDialog(mainFrame, errorMessages,
							"删除失败", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(mainFrame, "删除成功", "删除成功",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	// 所有换页按钮的"模版"方法
	public void selectAndShowOnePage(Integer pageNum) {
		String startPlace = startPlaceField.getText();
		String endPlace = endPlaceField.getText();
		String startTime = startTimeField.getText();
		// 表单验证
		if ("请按照以下格式输入,例:2000-05-03".equals(startTime)) {
			startTime = "";
		}
		// 起点终点相同且不为空
		if (startPlace.equals(endPlace) && !"".equals(startPlace)) {
			JOptionPane.showMessageDialog(mainFrame, "起点和终点不能相同", "查询失败",
					JOptionPane.ERROR_MESSAGE);
		}
		// 时间格式不匹配
		try {
			if (!DateVaileUtil.isValidDate(startTime)
					&& (!"".equals(startTime))) {
				JOptionPane.showMessageDialog(mainFrame, "时间格式不匹配，请重新输入",
						"查询失败", JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			// 如果三个查询的条件的任意个数，与上次查询结果不同
			// 则本次作为一次新查询，显示新条件的第一页
			if ((!startPlace.equals(lastStartPlace))
					|| (!endPlace.equals(lastEndPlace))
					|| (!startTime.equals(lastStartTime))) {
				page = ticketServiceProxy.getTicketByPageAndCond(startPlace,
						endPlace, startTime, 1);
			} else {
				// 三个查询条件与上次相同，则为正常的换页操作
				page = ticketServiceProxy.getTicketByPageAndCond(startPlace,
						endPlace, startTime, pageNum);
			}
			String[] columns = new String[] { "车次", "起点", "终点", "出发时间", "剩余票数",
					"价格", "状态" };
			Object[][] data = new Object[page.getData().size()][columns.length];
			for (int i = 0; i < page.getData().size(); i++) {
				Ticket ticket = page.getData().get(i);
				data[i][0] = ticket.getTrainnum();
				data[i][1] = ticket.getStartPlace();
				data[i][2] = ticket.getEndPlace();
				data[i][3] = ticket.getStartTime();
				data[i][4] = ticket.getCount();
				data[i][5] = ticket.getPrice();
				data[i][6] = ticket.getState();
			}
			DefaultTableModel defaultTableModel = new DefaultTableModel(data,
					columns);
			resultTable.setModel(defaultTableModel);
			// 把本次查询结果，作为"上次查询"的结果
			lastStartPlace = startPlace;
			lastEndPlace = endPlace;
			lastStartTime = startTime;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		init();
		addAction();
		build();
	}
}
