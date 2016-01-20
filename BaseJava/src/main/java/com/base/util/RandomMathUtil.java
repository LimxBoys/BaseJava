package com.base.util;

import java.util.Random;
/**
 * 随机数
 * @author limingxing
 * @Date:2016-1-20下午6:35:58
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
 */
public class RandomMathUtil {
	/**
     * 随机红包
     * @total 红包总数 
     * @number 红包个数 
     * @min 红包最小值 
     * @min 红包最大值 
     * @accuracy 红包精确到多少位
     */
    public static void createRomNumber(double total, int number, double min, double max, int accuracy) {
        if (total < 0) {
            System.out.println("红包总金额不能为负数！");
            return;
        }
        if (number < 0) {
            System.out.println("红包数不能为负数！");
            return;
        }
        if (min < 0 || min * number > total) {
            System.out.println("红包最小值为负数或者超出平均红包金额！");
            return;
        }
        if (max < min) {
            System.out.println("红包最大值必须大于最小值！");
            return;
        }
        /** 默认精度为两位 */
        if (accuracy < 0) {
            accuracy = 2;
        }
 
        Random rd = new Random();
        /** 总随机余额 */
        double totalRdNumber = total - number * min;
        /** 当前最大和最小金额总偏差 */
        double totalOffset = max - min;
        /** 当前最大发的随机部分的金额 */
        double currentMaxSend = 0;
        /** 统计红包 */
        double countNumber = 0;
        /** 当前最小发的随机部分的金额 */
        double currentMinSend = 0;
        /** 当前红包 */
        double money = 0;
        System.out.println("总金额：" + "-----------" + total);
        System.out.println("总红包数：" + "-----------" + number);
        System.out.println("总随机余额：" + "-----------" + totalRdNumber);
        System.out.println("--------------------------------------");
        for (int i = 0; i < number; i++) {
            if (i == number - 1) {
                /** 最后一个红包 */
                money = total - countNumber;
            } else {
                /** 当前剩余可随机的余额 */
                double rdNumber = totalRdNumber - countNumber + i * min;
                if (rdNumber < 0) {
                    currentMinSend = 0;
                    currentMaxSend = 0;
                } else if (rdNumber < totalOffset) {
                    currentMinSend = 0;
                    currentMaxSend = rdNumber;
                } else if (rdNumber - (number - i - 1) * totalOffset < 0) {
                    currentMinSend = 0;
                    currentMaxSend = totalOffset;
                } else if (rdNumber - (number - i - 1) * totalOffset < totalOffset) {
                    currentMinSend = rdNumber - (number - i - 1) * totalOffset;
                    currentMaxSend = totalOffset;
                } else {
                    currentMinSend = totalOffset;
                    currentMaxSend = totalOffset;
                }
                money = min + (currentMinSend * Math.pow(10, accuracy) + rd.nextInt((int) (currentMaxSend * Math.pow(10, accuracy)))) / Math.pow(10, accuracy);
            }
            money = Math.round(Math.pow(10, accuracy) * money) / Math.pow(10, accuracy);
 
            countNumber += money;
            System.out.println("第" + (i + 1) + "个红包：" + "-----------" + money);
        }
 
    }
}
