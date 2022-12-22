package awtpw;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class awtpw {
	public static void main(String[] args) {
		// 创建窗口
		JFrame frame = new JFrame("加密解密");
		frame.setSize(400, 140);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 创建标签和文本框
		JLabel keyLabel = new JLabel("密钥:");
		JTextField keyField = new JTextField(10);
		JLabel fileLabel = new JLabel("文件名:");
		JTextField fileField = new JTextField(10);
		JLabel modeLabel = new JLabel("模式(encrypt or decrypt):");
		JTextField modeField = new JTextField(10);

		// 创建按钮
		JButton submitButton = new JButton("Submit");

		// 为按钮添加事件监听器
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 读取用户输入的内容
				String keyString = keyField.getText();
				String fileName = fileField.getText();
				String mode = modeField.getText();

				// 将密钥转换为字节
				byte key = (byte) Integer.parseInt(keyString, 16);

				// 根据用户输入的模式选择加密或解密
				if ("encrypt".equalsIgnoreCase(mode)) {
					// 加密
					encryptFile(fileName, key);
				} else if ("decrypt".equalsIgnoreCase(mode)) {
					// 解密
					decryptFile(fileName, key);
				}
			}
		});

		// 将标签、文本框和按钮添加到窗口中
		frame.add(keyLabel);
		frame.add(keyField);
		frame.add(fileLabel);
		frame.add(fileField);
		frame.add(modeLabel);
		frame.add(modeField);
		frame.add(submitButton);
		
		// 设置窗口布局
		frame.setLayout(new FlowLayout());

		// 显示窗口
		frame.setVisible(true);
	}

	private static void encryptFile(String fileName, byte key) {
		// 读取文件内容
		byte[] fileContent = readFile(fileName);

		// 加密
		byte[] encryptedContent = xor(fileContent, key);

		System.out.println(encryptedContent);
		
		// 写入加密后的文件
		// writeFile("encrypted.txt", encryptedContent);
	}

	private static void decryptFile(String fileName, byte key) {
		// 读取文件内容
		byte[] fileContent = readFile(fileName);

		// 解密
		byte[] decryptedContent = xor(fileContent, key);
		
		System.out.println(decryptedContent);

		// 写入解密后的文件
		// writeFile("decrypted.txt", decryptedContent);
	}

	private static byte[] readFile(String fileName) {
		try (FileInputStream inputStream = new FileInputStream(fileName)) {
			// 读取文件
			byte[] fileContent = new byte[inputStream.available()];
			inputStream.read(fileContent);
			return fileContent;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void writeFile(String fileName, byte[] content) {
		try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
			// 写入文件内容
			outputStream.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static byte[] xor(byte[] content, byte key) {
		// 异或运算
		byte[] result = new byte[content.length];
		for (int i = 0; i < content.length; i++) {
			result[i] = (byte) (content[i] ^ key);
		}
		return result;
	}
}
