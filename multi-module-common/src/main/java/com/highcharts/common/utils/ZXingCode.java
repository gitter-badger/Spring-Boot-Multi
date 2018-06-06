package com.highcharts.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @program: multi-module
 * @description: (二维码)
 * @author: Brucezheng
 * @create: 2018-04-09 08:27
 **/
public class ZXingCode {
    private static final int QRCOLOR = 0xFF000000;   //默认是黑色
    private static final int BGWHITE = 0xFFFFFFFF;   //背景颜色
    private static final Logger logger = LoggerFactory.getLogger(ZXingCode.class);

    public static void main(String[] args) throws WriterException {
        try {
            // getLogoQRCode("https://www.baidu.com/", "http://img.hexun.com/2011-06-21/130726386.jpg", "跳转到百度的二维码");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成带logo的二维码图片
     *
     * @param qrUrl
     * @param netUrl
     * @param netUrl 网络图片路经 如果本地图片需要修改addLogo_QRCode（）方法参数
     */
    public static String getLogoQRCode(String qrUrl, String netUrl, String productName, HttpServletResponse response) {
//      String filePath = (javax.servlet.http.HttpServletRequest)request.getSession().getServletContext().getRealPath("/") + "resources/images/logoImages/llhlogo.png";
        //filePath是二维码logo的路径，但是实际中我们是放在项目的某个路径下面的，所以路径用上面的，把下面的注释就好
        //String filePath = "C:\\Users\\Administrator.SKY-20170531RMO\\Desktop\\marker\\电话.jpg";  //TODO
        //new一个URL对象
        URL url = null;
        try {
            url = new URL(netUrl);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }
        String content = qrUrl;
        try {
            ZXingCode zp = new ZXingCode();
            BufferedImage bim = zp.getQR_CODEBufferedImage(content, BarcodeFormat.QR_CODE, 400, 400, zp.getDecodeHintType());
            return zp.addLogo_QRCode(bim, url, new LogoConfig(), productName, response);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 生成带logo的二维码图片 zip
     *
     * @param qrUrl  二维码链接
     * @param netUrl 中加logo地址
     * @param netUrl 网络图片路经
     */
    public static byte[] getLogoQRCodeForZip(String qrUrl, String netUrl, String productName) {
        URL url = null;
        try {
            url = new URL(netUrl);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }
        String content = qrUrl;
        try {
            ZXingCode zp = new ZXingCode();
            BufferedImage bim = zp.getQR_CODEBufferedImage(content, BarcodeFormat.QR_CODE, 400, 400, zp.getDecodeHintType());
            return zp.addLogo_QRCode(bim, url, productName);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 给二维码图片添加Logo
     *
     * @param bim
     * @param logoPic
     * @param logoConfig
     * @param productName
     * @return
     */
    public String addLogo_QRCode(BufferedImage bim, URL logoPic, LogoConfig logoConfig, String productName, HttpServletResponse response) {
        try {
            /**
             * 读取二维码图片，并构建绘图对象
             */
            BufferedImage image = bim;
            Graphics2D g = image.createGraphics();

            /**
             * 读取Logo图片
             */
            BufferedImage logo = ImageIO.read(logoPic);
            // ImageIO.re
            /**
             * 设置logo的大小,本人设置为二维码图片的20%,因为过大会盖掉二维码
             */
            int widthLogo = logo.getWidth(null) > image.getWidth() * 3 / 10 ? (image.getWidth() * 3 / 10) : logo.getWidth(null),
                    heightLogo = logo.getHeight(null) > image.getHeight() * 3 / 10 ? (image.getHeight() * 3 / 10) : logo.getWidth(null);

            /**
             * logo放在中心
             */
            int x = (image.getWidth() - widthLogo) / 2;
            int y = (image.getHeight() - heightLogo) / 2;
            /**
             * logo放在右下角
             *  int x = (image.getWidth() - widthLogo);
             *  int y = (image.getHeight() - heightLogo);
             */

            //开始绘制图片
            g.drawImage(logo, x, y, widthLogo, heightLogo, null);
//            g.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
//            g.setStroke(new BasicStroke(logoConfig.getBorder()));
//            g.setColor(logoConfig.getBorderColor());
//            g.drawRect(x, y, widthLogo, heightLogo);
            g.dispose();

            //把商品名称添加上去，商品名称不要太长哦，这里最多支持两行。太长就会自动截取啦
            if (productName != null && !productName.equals("")) {
                //新的图片，把带logo的二维码下面加上文字
                BufferedImage outImage = new BufferedImage(400, 445, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D outg = outImage.createGraphics();
                //画二维码到新的面板
                outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                //画文字到新的面板
                outg.setColor(Color.BLACK);
                outg.setFont(new Font("宋体", Font.BOLD, 30)); //字体、字型、字号
                int strWidth = outg.getFontMetrics().stringWidth(productName);
                if (strWidth > 399) {
//                  //长度过长就截取前面部分
//                  outg.drawString(productName, 0, image.getHeight() + (outImage.getHeight() - image.getHeight())/2 + 5 ); //画文字
                    //长度过长就换行
                    String productName1 = productName.substring(0, productName.length() / 2);
                    String productName2 = productName.substring(productName.length() / 2, productName.length());
                    int strWidth1 = outg.getFontMetrics().stringWidth(productName1);
                    int strWidth2 = outg.getFontMetrics().stringWidth(productName2);
                    outg.drawString(productName1, 200 - strWidth1 / 2, image.getHeight() + (outImage.getHeight() - image.getHeight()) / 2 + 12);
                    BufferedImage outImage2 = new BufferedImage(400, 485, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D outg2 = outImage2.createGraphics();
                    outg2.drawImage(outImage, 0, 0, outImage.getWidth(), outImage.getHeight(), null);
                    outg2.setColor(Color.BLACK);
                    outg2.setFont(new Font("宋体", Font.BOLD, 30)); //字体、字型、字号
                    outg2.drawString(productName2, 200 - strWidth2 / 2, outImage.getHeight() + (outImage2.getHeight() - outImage.getHeight()) / 2 + 5);
                    outg2.dispose();
                    outImage2.flush();
                    outImage = outImage2;
                } else {
                    outg.drawString(productName, 200 - strWidth / 2, image.getHeight() + (outImage.getHeight() - image.getHeight()) / 2 + 12); //画文字
                }
                outg.dispose();
                outImage.flush();
                image = outImage;
            }
            logo.flush();
            image.flush();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.flush();
            ImageIO.write(image, "png", baos);
/**
 * 改造成将BufferedImage转换成byte[],接着输出
 */
            response.setHeader("Content-Type", "image/png");
            // 上面的读取和输出的代码写完了并不管作用，那么样只是单纯的读取和输出，是不能实现文件下载的，想要实现文件下载，还要先向浏览器输出一个响应头
            SecureRandom secureRandom = new SecureRandom();
            byte[] states = new byte[16];
            secureRandom.nextBytes(states);
            String s = Hex.encodeHexString(states);
            response.setHeader("content-disposition", "attachment;filename=wx_" + s + ".png");
           // response.setHeader("Content-Type", "image/png");
          //charset=UTF-8
            response.setContentType("charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            ServletOutputStream outputStream = response.getOutputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(image, "png", out);
            byte[] b = out.toByteArray();
            outputStream.write(b);
            outputStream.flush();
            outputStream.close();


            //二维码生成的路径，但是实际项目中，我们是把这生成的二维码显示到界面上的，因此下面的折行代码可以注释掉
            //可以看到这个方法最终返回的是这个二维码的imageBase64字符串
            //前端用 <img src="data:image/png;base64,${imageBase64QRCode}"/>  其中${imageBase64QRCode}对应二维码的imageBase64字符串
            //ImageIO.write(image, "png", new File("C:\\Users\\Administrator.SKY-20170531RMO\\Desktop\\marker\\" + new Date().getTime() + "test.png")); //TODO

            String imageBase64QRCode = Base64.encodeBase64URLSafeString(baos.toByteArray());

            baos.close();
            return imageBase64QRCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成二维码的byte[]数组
     *
     * @param logoPic
     */
    public static byte[] addLogo_QRCode(BufferedImage bim, URL logoPic, String productName) {
        try {

            BufferedImage image = bim;
            Graphics2D g = image.createGraphics();
            BufferedImage logo = ImageIO.read(logoPic);
            int widthLogo = logo.getWidth(null) > image.getWidth() * 3 / 10 ? (image.getWidth() * 3 / 10) : logo.getWidth(null),
                    heightLogo = logo.getHeight(null) > image.getHeight() * 3 / 10 ? (image.getHeight() * 3 / 10) : logo.getWidth(null);
            int x = (image.getWidth() - widthLogo) / 2;
            int y = (image.getHeight() - heightLogo) / 2;

            //开始绘制图片
            g.drawImage(logo, x, y, widthLogo, heightLogo, null);
            g.dispose();

            //把商品名称添加上去，商品名称不要太长哦，这里最多支持两行。太长就会自动截取啦
            if (productName != null && !productName.equals("")) {
                //新的图片，把带logo的二维码下面加上文字
                BufferedImage outImage = new BufferedImage(400, 445, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D outg = outImage.createGraphics();
                //画二维码到新的面板
                outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                //画文字到新的面板
                outg.setColor(Color.BLACK);
                outg.setFont(new Font("宋体", Font.BOLD, 30)); //字体、字型、字号
                int strWidth = outg.getFontMetrics().stringWidth(productName);
                if (strWidth > 399) {
                    String productName1 = productName.substring(0, productName.length() / 2);
                    String productName2 = productName.substring(productName.length() / 2, productName.length());
                    int strWidth1 = outg.getFontMetrics().stringWidth(productName1);
                    int strWidth2 = outg.getFontMetrics().stringWidth(productName2);
                    outg.drawString(productName1, 200 - strWidth1 / 2, image.getHeight() + (outImage.getHeight() - image.getHeight()) / 2 + 12);
                    BufferedImage outImage2 = new BufferedImage(400, 485, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D outg2 = outImage2.createGraphics();
                    outg2.drawImage(outImage, 0, 0, outImage.getWidth(), outImage.getHeight(), null);
                    outg2.setColor(Color.BLACK);
                    outg2.setFont(new Font("宋体", Font.BOLD, 30)); //字体、字型、字号
                    outg2.drawString(productName2, 200 - strWidth2 / 2, outImage.getHeight() + (outImage2.getHeight() - outImage.getHeight()) / 2 + 5);
                    outg2.dispose();
                    outImage2.flush();
                    outImage = outImage2;
                } else {
                    outg.drawString(productName, 200 - strWidth / 2, image.getHeight() + (outImage.getHeight() - image.getHeight()) / 2 + 12); //画文字
                }
                outg.dispose();
                outImage.flush();
                image = outImage;
            }
            logo.flush();
            image.flush();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.flush();
            // ImageIO.write(image, "png", baos);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(image, "png", out);
            byte[] b = out.toByteArray();
            // String imageBase64QRCode = Base64.encodeBase64URLSafeString(baos.toByteArray());

            baos.close();
            //   return imageBase64QRCode;
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 输出zip
     *
     * @param request
     * @param response
     * @param picBuff
     */
    public static void exponseZip(HttpServletRequest request, HttpServletResponse response, List<byte[]> picBuff, String[] sname) {
        try {
            String downloadFilename = "商家二维码.zip";//文件的名称
            downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");//转换中文否则可能会产生乱码
            response.setContentType("application/octet-stream;charset=UTF-8");// 指明response的返回对象是文件流
            //response.setContentType("charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);// 设置在下载框默认显示的文件名
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            //String[] files = new String[]{"http://xxxx/xx.jpg", "http://xxx/xx.jpg"};
            /*for (int i = 0; i < picBuff.size(); i++) {
                zos.putNextEntry(new ZipEntry(i + ".jpg"));

                File file = new File("");
                OutputStream output = new FileOutputStream(file);
                BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
                bufferedOutput.write(picBuff.get(i));

                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int r = 0;
                while ((r = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, r);
                }
                fis.close();
            }
            zos.flush();
            zos.close();*/
            for (int i = 0; i < picBuff.size(); i++) {
                try {
                    zos.putNextEntry(new ZipEntry(sname[i] + ".jpeg"));
                    zos.write(picBuff.get(i), 0, picBuff.get(i).length);
                } catch (IOException e) {
                    logger.error(e.getLocalizedMessage());
                }
                zos.flush();
            }
            zos.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 构建初始化二维码
     *
     * @param bm
     * @return
     */
    public BufferedImage fileToBufferedImage(BitMatrix bm) {
        BufferedImage image = null;
        try {
            int w = bm.getWidth(), h = bm.getHeight();
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? 0xFF000000 : 0xFFCCDDEE);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 生成二维码bufferedImage图片
     *
     * @param content       编码内容
     * @param barcodeFormat 编码类型
     * @param width         图片宽度
     * @param height        图片高度
     * @param hints         设置参数
     * @return
     */
    public BufferedImage getQR_CODEBufferedImage(String content, BarcodeFormat barcodeFormat, int width, int height, Map<EncodeHintType, ?> hints) {
        MultiFormatWriter multiFormatWriter = null;
        BitMatrix bm = null;
        BufferedImage image = null;
        try {
            multiFormatWriter = new MultiFormatWriter();
            // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            bm = multiFormatWriter.encode(content, barcodeFormat, width, height, hints);
            int w = bm.getWidth();
            int h = bm.getHeight();
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 设置二维码的格式参数
     *
     * @return
     */
    public Map<EncodeHintType, Object> getDecodeHintType() {
        // 用于设置QR二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 0);
        hints.put(EncodeHintType.MAX_SIZE, 350);
        hints.put(EncodeHintType.MIN_SIZE, 100);

        return hints;
    }
}

class LogoConfig {
    // logo默认边框颜色
    public static final Color DEFAULT_BORDERCOLOR = Color.WHITE;
    // logo默认边框宽度
    public static final int DEFAULT_BORDER = 2;
    // logo大小默认为照片的1/5
    public static final int DEFAULT_LOGOPART = 5;

    private final int border = DEFAULT_BORDER;
    private final Color borderColor;
    private final int logoPart;


    public LogoConfig() {
        this(DEFAULT_BORDERCOLOR, DEFAULT_LOGOPART);
    }

    public LogoConfig(Color borderColor, int logoPart) {
        this.borderColor = borderColor;
        this.logoPart = logoPart;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public int getBorder() {
        return border;
    }

    public int getLogoPart() {
        return logoPart;
    }
}