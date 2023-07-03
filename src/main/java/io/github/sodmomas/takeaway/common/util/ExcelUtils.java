package io.github.sodmomas.takeaway.common.util;

import com.alibaba.excel.EasyExcel;
import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import io.github.sodmomas.takeaway.listener.easyexcel.MyAnalysisEventListener;

import java.io.InputStream;

/**
 * Excel 工具类
 *
 * @author haoxr
 * @since 2023/03/01
 */
public class ExcelUtils {

    public static <T> String importExcel(InputStream is, Class clazz, MyAnalysisEventListener<T> listener) {
        EasyExcel.read(is, clazz, listener).sheet().doRead();
        return listener.getMsg();
    }

    public static void main(String[] args) {
        //中文验证码
        Captcha captcha = new ChineseCaptcha();
        //获取本次生成的验证码
        String text = captcha.text();
        System.out.println(text);
        /**
         * 验证码内容长度
         */
        int length = 4;
        /**
         * 验证码宽度
         */
        int width = 120;
        /**
         * 验证码高度
         */
        int height = 36;
        //算术验证码
        Captcha captcha1 = new ArithmeticCaptcha();
        //获取本次生成的验证码
//        String text1 = captcha1.text();

        Captcha captcha2 = new ArithmeticCaptcha(width, height);
        //固定设置为两位，图片为算数运算表达式
        captcha2.setLen(2);
//        System.out.printfln(captcha2.text());
        Captcha captcha3 = new ChineseCaptcha(width, height);
        captcha3.setLen(length);
        System.out.println("ChineseCaptcha"+captcha3.text());
        Captcha captcha4 = new ChineseGifCaptcha(width, height);
        captcha4.setLen(length);
        System.out.println("ChineseGifCaptcha"+captcha4.text());
        Captcha captcha5  = new GifCaptcha(width, height);//最后一位是位数
        captcha5.setLen(length);
        System.out.println("GifCaptcha"+captcha5.text());
        Captcha captcha6 = new SpecCaptcha(width, height);
        captcha6.setLen(length);
        System.out.println("SpecCaptcha"+captcha6);
    }
}
