package com.wdc.learnning.awt;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by wangdachong on 2017/5/15.
 */
public class ChartGraphics {
    private static String[] colors = {"79cb7f", "f87e4d", "3399ff", "af65df", "f7bf28", "ff99cc", "ff6666", "69dee8"};

    public static void main(String[] args) {
        ChartGraphics cg = new ChartGraphics();
        try {
            cg.getHeadIcon("Rachel", "test@test.ai","E:\\tmp\\2.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getHeadIcon(String userName, String userId, String imgurl) throws IOException {
        int radius = 30;
        int width = 100;
        int height = 100;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        //设置背景透明
//        image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
//        g2d.dispose();
//        g2d = image.createGraphics();

        Color curColor = new Color(Integer.parseInt(colors[getDefaultAvatarBgColor(userId)], 16));
        g2d.setColor(curColor);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(0, 0, width, height, radius, radius);

        g2d.setColor(Color.WHITE);
        //字体需系统支持
        Font font = new Font("黑体", Font.BOLD, 60);
        g2d.setFont(font);
        g2d.setComposite(AlphaComposite.SrcOver);

        FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(font);
        String mString = getMString(userName);

        g2d.drawString(mString, (width - fm.stringWidth(mString))/2, 70);
        g2d.dispose();

        ImageIO.write(image, "png", new File(imgurl));
    }

    public String getMString(String userName){
        String mString = "";
        if (null != userName && userName.trim().length() > 0) {
            String filterName=getStringFilter(userName).toUpperCase();
            char[] names;
            if (filterName.length()>0) {
                String throwNumberName = filterName.replaceAll("\\d+", "");
                if (throwNumberName.length() > 0) {
                    if (containsChineseChar(throwNumberName)){
                        throwNumberName=throwNumberName.replaceAll("[a-zA-Z]","" );
                        names = throwNumberName.toCharArray();
                    }else {
                        names = throwNumberName.toCharArray();
                    }

                } else {
                    names = userName.toCharArray();
                }
            }else{
                names = userName.toCharArray();
            }
            mString = String.valueOf(names[names.length - 1]);
        } else {
            mString = "佚";
        }
        return mString;
    }

    public int getDefaultAvatarBgColor(String userId) {
        if (null != userId && userId.trim().length() > 0) {
            String[] names = userId.split("@");
            if (names.length > 0) {
                char[] key = names[0].toCharArray();
                if (key.length > 0) {
                    int colorIndex = (key[0] + key[key.length - 1] + key.length) % colors.length;
                    return colorIndex;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        }else{
            return 0;
        }

    }

    /** 过滤特殊字符 */
    public String getStringFilter(String str) throws PatternSyntaxException {

        String regEx = "[Ω`~!@#$%^&*()+=|{}':;'，,\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’-………《×✘^O^☞★☜$‖か囍℡*^O^*⊙▽⊙⊙﹏⊙*♞→_→←_←》^¥€﹉–╭(╯ε╰)╮＠_＠π_π–-。وˊΩˋ٩Ωω]";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(str.replace(" ", ""));

        return m.replaceAll("");
    }

    public boolean containsChineseChar(String str) {
        boolean temp = false;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            temp = true;
        }
        return temp;
    }

}