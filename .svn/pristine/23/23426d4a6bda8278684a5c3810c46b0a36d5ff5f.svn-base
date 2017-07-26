package webcat.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.awt.SunHints;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by dengfan on 2017/3/4.
 */
public class PictureUtil {

    //图片宽度
    private static final int imageWidth = 900;

    //图片高度
    private static final int imageHeight = 500;

    public static File createFile(){

        try {
            // 创建图片
            BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_BGR);
            Graphics graphics = image.getGraphics();
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, imageWidth, imageHeight);
            graphics.setColor(Color.gray);
            graphics.setFont(new Font("宋体", Font.PLAIN, 35));// 设置画笔字体

            int num = 150;
            int left = 0;
            int top = 50;
            graphics.drawString("马沧湖路单位宿舍两房78万出售", left, num);
            num += top;
            graphics.drawString("房源地址：钟家村 - 汉阳钟家村", left, num);
            num += top;
            graphics.drawString("房源户型：2室1厅1卫", left, num);
            num += top;
            graphics.drawString("房源面积：69平", left, num);
            num += top;
            graphics.drawString("房源价格：69万元", left, num);
            num += top;
            graphics.drawString("房源类型：住宅", left, num);

            ImageIO.write(image, "png", new File("D:\\pri_test\\b.png"));// 输出png图片


        } catch (IOException e) {

        }

        return null;
    }

    public static void main(String[] args) {
        PictureUtil.createFile();
    }
}
