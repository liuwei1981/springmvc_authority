package com.lw.upload;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public abstract class UploadService {
	/**
	 * 文字水印参数
	 */
	protected static final String MARK_TEXT = "慕课网";
	protected static final String FONT_NAME = "微软雅黑";
	protected static final int FONT_STYLE = Font.BOLD;
	protected static final int FONT_SIZE = 120;
	protected static final Color FONT_COLOR = Color.BLACK;

	/**
	 * 水印透明度
	 */
	protected static final float ALPHA = 0.3F;

	/**
	 * 水印图片参数
	 */
	protected static final String LOGO = "/logo/logo.png";

	/**
	 * 水印坐标
	 */
	protected static int X = 100;// 横坐标
	protected static int Y = 100;// 纵坐标

	/**
	 * 批量上传图片，并完成添加水印处理
	 * 
	 * @param image
	 * @param imageFileName
	 * @param uploadPath
	 * @return
	 */
	public List<PicInfo> uploadAndMark(CommonsMultipartFile[] image,
			String uploadPath, HttpSession session) {

		List<PicInfo> ret = new ArrayList<PicInfo>();

		if (image != null && image.length > 0) {
			String realUploadPath = getRealUploadPath(uploadPath, session);// 获取图片上传绝对路径

			for (int i = 0; i < image.length; i++) {
				if (image[i] == null || image[i].getSize() <= 0) {
					continue;
				}

				ret.add(uploadAndMark(image[i], image[i].getOriginalFilename(),
						uploadPath, realUploadPath));// 完成单张图片上传与添加水印操作
			}
		}

		try {

		} catch (Exception e) {
			System.out.println("文件水印操作失败");
			e.printStackTrace();
		}

		return ret;

	}

	/**
	 * 上传图片，并完成图片添加水印操作
	 * 
	 * @param image
	 * @param imageFileName
	 * @param uploadPath
	 * @param realUploadPath
	 * @return
	 */
	private PicInfo uploadAndMark(CommonsMultipartFile image,
			String imageFileName, String uploadPath, String realUploadPath) {
		PicInfo ret = new PicInfo();

		InputStream fis = null;// 上传图片文件输入流
		OutputStream fos = null;// 图片文件输出流
		OutputStream wfos = null;// 水印图片文件输出流

		try {
			String logoFileName = "logo_" + imageFileName;

			fos = new FileOutputStream(realUploadPath + "/" + imageFileName);// 原图文件输出流
			wfos = new FileOutputStream(realUploadPath + "/" + logoFileName);// 水印图片输入流，原文件名之前加前缀

			// 保存原图文件
			fis = image.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {// 完成原图文件上传保存
				fos.write(buffer, 0, len);
			}

			fis = image.getInputStream();

			if (watermark(fis, wfos)) {
				ret.setMarkImgURL(uploadPath + "/" + logoFileName);
			}

			ret.setImgURL(uploadPath + "/" + imageFileName);// 设置原图web访问路径

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public abstract boolean watermark(InputStream fis, OutputStream fos);

	/**
	 * 将文件相对路径转化为绝对路径
	 * 
	 * @param uploadPath
	 * @return
	 */
	protected String getRealUploadPath(String uploadPath, HttpSession session) {
		return session.getServletContext().getRealPath(uploadPath);
	}

	/**
	 * 根据文本获得文本宽度
	 * 
	 * @param text
	 * @return
	 */
	protected int getTextLength(String text) {
		int textLength = text.length();
		int length = textLength;

		for (int i = 0; i < textLength; i++) {// 对中文文本的处理，一个中文字符两个字节
			if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
				length++;
			}
		}
		return (length % 2 == 0) ? length / 2 : length / 2 + 1;
	}

}
