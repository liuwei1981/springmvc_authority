package com.lw.upload;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Service
public class UploadTextService extends UploadService {

	@Override
	public boolean watermark(InputStream fis, OutputStream fos) {

		boolean ret = false;

		try {
			Image image = ImageIO.read(fis);// 原图图片信息处理
			int width = image.getWidth(null);
			int height = image.getHeight(null);

			BufferedImage bufferedImage = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufferedImage.createGraphics();// 创建绘图工具类
			g.drawImage(image, 0, 0, width, height, null);// 绘制原图

			// 设置绘制文字信息
			g.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
			g.setColor(FONT_COLOR);

			int width1 = FONT_SIZE * getTextLength(MARK_TEXT);// 获得水印文字宽度
			int height1 = FONT_SIZE;// 获得水印文字高度
			int widthDiff = width - width1;// 图片宽度与水印文字宽度之差
			int heightDiff = height - height1;// 图片高度与水印文字高度之差

			int x = X;
			int y = Y;
			if (x > widthDiff) {// 如果水印横轴坐标超过水印文字与原图宽度差，调整水印横轴，默认为图片横轴最右侧
				x = widthDiff;
			}

			if (y > heightDiff) {// 如果水印纵轴坐标超过水印文字与原图高度差，调整水印纵轴，默认为图片纵轴最下侧
				y = heightDiff;
			}

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					ALPHA));// 设置水印透明度
			g.drawString(MARK_TEXT, x, y + FONT_SIZE);// 绘制水印文字
			g.dispose();

			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(fos);// 生成水印图片
			en.encode(bufferedImage);

			ret = true;
		} catch (Exception e) {
			System.out.println("添加文字水印失败");
			e.printStackTrace();
		}

		return ret;
	}

}
