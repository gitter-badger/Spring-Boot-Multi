package com.highcharts.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public final class ImageUtils {
	public ImageUtils() {

	}

	/**
	 * 缩放裁切图片
	 * 
	 * @param path
	 *            --原始图片地址
	 * @param savePath
	 *            --图片保存地址
	 * @param x
	 *            --裁切顶点x坐标
	 * @param y
	 *            --裁切顶点y坐标
	 * @param w
	 *            --裁切区域宽度
	 * @param h
	 *            --裁切区域高度
	 * @param imgW
	 *            --图片缩放后的宽度
	 * @param imgH
	 *            --图片缩放后的高度
	 */
	public static void ImageCrop(String path, String savePath, int x, int y,
                                 int w, int h, int imgW) throws IOException {
		BufferedImage source = null;
		BufferedImage target = null;
		File saveFile = new File(savePath);
		File fromFile = new File(path);
		String imgType = "JPEG";
		source = ImageIO.read(fromFile);
		int type = source.getType();
		int imgH = imgW * source.getHeight() / source.getWidth();
		target = new BufferedImage(imgW, imgH, type);
		Graphics2D g = (Graphics2D) target.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.drawImage(source.getScaledInstance(source.getWidth(),
				source.getHeight(), 0), 0, 0, imgW, imgH, null);
		target = target.getSubimage(x, y, w, h);
		ImageIO.write(target, imgType, saveFile);
	}

	public static void genImage(String openfile, String savefile, String text,
                                String text2) {
		genImage(openfile,savefile,text,text2,0);
	}
	/**
	 * 生成车标
	 * @param openfile
	 * @param savefile
	 * @param text
	 * @param text2
	 */
	public static void genImage(String openfile, String savefile, String text,
                                String text2, int jd) {
		int width = 150;
		int height = 70;

		try {
			Image image = ImageIO.read(new File(openfile));

			BufferedImage bi = new BufferedImage(40, 40,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2d= bi.createGraphics();

			bi = graphics2d.getDeviceConfiguration().createCompatibleImage(40, 40, Transparency.TRANSLUCENT);
			graphics2d.dispose();
			graphics2d=bi.createGraphics();
	        graphics2d.rotate(Math.toRadians(jd), 40 / 2, 40 / 2);
	        graphics2d.drawImage(image, 0, 0, null);
	        graphics2d.dispose();
	        //////////////////////////////
			BufferedImage buff = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			
			Graphics2D g = buff.createGraphics();

			// ---------- 增加下面的代码使得背景透明 -----------------

			buff = g.getDeviceConfiguration().createCompatibleImage(width,
					height, Transparency.TRANSLUCENT);

			g.dispose();

			g = buff.createGraphics();

			g.setColor(Color.red);
			g.drawString(text, 0, 28);
			if (text2 != null) {
				g.drawString(text2, 0, 14);
			}
			g.drawImage(bi, 0, 30, null);
			g.dispose();//
			ImageIO.write(buff, "PNG", new FileOutputStream(savefile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}