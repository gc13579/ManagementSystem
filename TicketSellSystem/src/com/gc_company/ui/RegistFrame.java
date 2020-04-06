package com.gc_company.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import com.gc_company.Constant.Constant;
import com.gc_company.enity.User;
import com.gc_company.exception.SomeFieldsAreSameException;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.impl.UserServiceImpl;
import com.gc_company.service.proxy.UserServiceProxy;

public class RegistFrame {
	JFrame mainFrame = null;
	JLabel welcomeWord = null;
	JLabel usernameLabel = null;
	JTextField usernameField = null;
	JLabel passwordLabel = null;
	JPasswordField passwordField = null;
	JLabel sureYourPasswordLabel = null;
	JPasswordField sureYourPasswordField = null;
	JLabel phoneLabel = null;
	JTextField phoneField = null;
	JLabel idCardLabel = null;
	JTextField idCardField = null;
	JButton registButton = null;
	JButton cancelButton = null;
	UserServiceProxy userServiceProxy = null;

	public void init() {
		mainFrame = new JFrame("购票系统注册页面");
		welcomeWord = new JLabel("欢  迎  加  入  我  们");
		usernameLabel = new JLabel("用户名:");
		usernameField = new JTextField();
		passwordLabel = new JLabel("密 码:");
		passwordField = new JPasswordField();
		sureYourPasswordLabel = new JLabel("确认密码:");
		sureYourPasswordField = new JPasswordField();
		phoneLabel = new JLabel("手机号:");
		phoneField = new JTextField();
		idCardLabel = new JLabel("身份证:");
		idCardField = new JTextField();
		registButton = new JButton("注册");
		cancelButton = new JButton("取消");
		userServiceProxy = (UserServiceProxy) ObjectFactory
				.getObject("userServiceProxy");
	}

	public void build() {
		mainFrame.setSize(300, 430);
		mainFrame.setLocation(500, 100);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);

		welcomeWord.setBounds(75, 10, 150, 30);
		mainFrame.add(welcomeWord);

		usernameLabel.setBounds(50, 50, 60, 25);
		mainFrame.add(usernameLabel);

		usernameField.setBounds(120, 50, 135, 25);
		mainFrame.add(usernameField);

		passwordLabel.setBounds(50, 100, 60, 25);
		mainFrame.add(passwordLabel);

		passwordField.setBounds(120, 100, 135, 25);
		mainFrame.add(passwordField);

		sureYourPasswordLabel.setBounds(50, 150, 60, 25);
		mainFrame.add(sureYourPasswordLabel);

		sureYourPasswordField.setBounds(120, 150, 135, 25);
		mainFrame.add(sureYourPasswordField);

		phoneLabel.setBounds(50, 200, 60, 25);
		mainFrame.add(phoneLabel);

		phoneField.setBounds(120, 200, 135, 25);
		mainFrame.add(phoneField);

		idCardLabel.setBounds(50, 250, 60, 25);
		mainFrame.add(idCardLabel);

		idCardField.setBounds(120, 250, 135, 25);
		mainFrame.add(idCardField);

		registButton.setBounds(80, 300, 60, 25);
		mainFrame.add(registButton);

		cancelButton.setBounds(160, 300, 60, 25);
		mainFrame.add(cancelButton);

		mainFrame.setVisible(true);
	}

	public void addAction() {
		registButtonSetAcctionListener();
		cancelButtonSetAcctionListener();
	}

	public void registButtonSetAcctionListener() {
		registButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StringBuffer errorMessages = new StringBuffer();
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				String sureYourPassword = new String(sureYourPasswordField
						.getPassword());
				String phone = phoneField.getText();
				String idCard = idCardField.getText();
				// 做一系列表单验证
				// 帐号长度不为0
				if (username.length() == 0 || password.length() == 0) {
					errorMessages.append("帐号或密码长度不能为0").append("\n");
				}
				// 帐号密码长度超过20
				if (username.length() > Constant.USERNAME_AND_PASSWORD_LENGTH
						|| password.length() > Constant.USERNAME_AND_PASSWORD_LENGTH) {
					errorMessages.append("帐号或密码长度不能超过20").append("\n");
				}
				// 两次输入的密码不匹配
				if (!password.equals(sureYourPassword)) {
					errorMessages.append("两次输入的密码不相同,请重新输入").append("\n");
				}
				// 身份证是否合法验证
				if (!idCard.matches("\\d{15}(\\d{2}[0-9xX]{1})?")) {
					errorMessages.append("身份证格式不正确！").append("\n");
				}
				// 电话号码是否合法验证
				if (!phone.matches("\\d{11}")) {
					errorMessages.append("电话号码格式不正确！").append("\n");
				}

				StringBuffer stringBuffer = new StringBuffer();
				// 显示表单错误，暂不显示逻辑错误
				if (errorMessages.length() != 0) {
					JOptionPane.showMessageDialog(mainFrame, errorMessages,
							"注册失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// 在regist方法中抛出自定义异常，不让程序在JDBCTemplate
				// 的update方法执行sql时，报出长度超长的运行时异常
				try {
					userServiceProxy.regist(username, password, phone, idCard);
					JOptionPane.showMessageDialog(mainFrame, "注册成功", "注册成功",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (SomeFieldsAreSameException e) {
					errorMessages.append(e.getMessage()).append("\n");
				} catch (Exception e) {
					// 捕获意外的异常(事务操作)
					errorMessages.append(e.getMessage()).append("\n");
				}
				// 显示逻辑错误
				if (errorMessages.length() != 0) {
					JOptionPane.showMessageDialog(mainFrame, errorMessages,
							"注册失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
	}

	public void cancelButtonSetAcctionListener() {
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.setVisible(false);
				new LoginFrame().start();
			}
		});
	}

	public void start() {
		init();
		addAction();
		build();
	}

}
