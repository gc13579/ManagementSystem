package com.ssm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.ssm.exception.fileuploadexception.UploadTypeException;

public class FileUtil {
	// 上传文件
	public static void uploadFile(InputStream is, String filePath) {

		// 根据webRoot/img/文件名.后缀 获取上传的文件路径
		File f = new File(filePath);
		// 如果文件不存在新建文件
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		OutputStream os = null;
		try {
			os = new FileOutputStream(f);
			byte[] bs = new byte[is.available()];
			is.read(bs);
			os.write(bs);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
