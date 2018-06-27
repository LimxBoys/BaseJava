package com.base.util;


import java.math.BigDecimal;

/**
 * 这个工具类提供精
 * 确的浮点数运算，包括加减乘除和四舍五入
 * @author limingxing
 *
 */
public class ArithUtil{

    //默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
    private static final int DEF_SCALE = 6;
    private static final int _SCALE = 6;
    static final BigDecimal one = new BigDecimal(1);
    static final Double zero = new Double(0.000000);
    public  static final BigDecimal big_zero = new BigDecimal(0.000000);
    //这个类不能实例化
    private ArithUtil(){
    }
 
    /**
     * 提供精确的加法运算
     * @param v1 被加
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));        
        return round(b1.add(b2).doubleValue(),DEF_SCALE);
    }
    
    public static BigDecimal add(String v1,String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);        
        return round(b1.add(b2),_SCALE);
    }
    
    public static BigDecimal add(BigDecimal v1,BigDecimal v2){
        return round(v1.add(v2),_SCALE);
    }
    /**
     * 自定义精度加法运算
     * @param v1 被加
     * @param v2 加数
     * @param scale 精度
     * @return 两个参数的和
     */
    public static double addByAccuracy(double v1,double v2,int scale){
    	 if(scale<0){
             throw new IllegalArgumentException(
                 "The scale must be a positive integer or zero");
         }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));        
        return round(b1.add(b2).doubleValue(),scale);
    }
    
    public static BigDecimal addByAccuracy(String v1,String v2,int scale){
    	 if(scale<0){
             throw new IllegalArgumentException(
                 "The scale must be a positive integer or zero");
         }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);        
        return round(b1.add(b2),scale);
    }
    
    public static BigDecimal addByAccuracy(BigDecimal v1,BigDecimal v2,int scale){
    	 if(scale<0){
             throw new IllegalArgumentException(
                 "The scale must be a positive integer or zero");
         }
        return round(v1.add(v2),scale);
    }
    /**
     * 提供精确的减法运算
     * @param v1 被减
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return round(b1.subtract(b2).doubleValue(),DEF_SCALE);
    } 
    /**
     * @author limingxing
     * 提供精确的减法运算
     * @param v1 被减
     * @param v2 减数
     * @param scale 精度
     * @return 两个参数的差
     * 
     * */
    public static double subByAccuracy(double v1,double v2,int scale){
    	 if(scale<0){
             throw new IllegalArgumentException(
                 "The scale must be a positive integer or zero");
         }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return round(b1.subtract(b2).doubleValue(),scale);
    } 
    
    /**
     * 提供精确的减法运算
     * @param b1
     * @param b2
     * @return
     */
    public static BigDecimal sub(BigDecimal b1,BigDecimal b2){
        return round(b1.subtract(b2),_SCALE);
    } 
    /**
     * 提供精确的减法运算--自定义精度
     * @param b1 被减
     * @param b2 减数
     * @param scale 精度
     * @return 两个参数的差
     * 
     * */
    public static BigDecimal subByAccuracy(BigDecimal b1,BigDecimal b2,int scale){
    	 if(scale<0){
             throw new IllegalArgumentException(
                 "The scale must be a positive integer or zero");
         }
        return round(b1.subtract(b2),scale);
    } 
    /**
     * 提供精确的乘法运算
     * @param v1 被乘
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1,double v2){
    	if("".equals(v1)){
    		v1 = 0.0;
    	}
    	if("".equals(v2)){
    		v2 = 0.0;
    	}
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return round(b1.multiply(b2).doubleValue(),DEF_SCALE);
    }
    public static double mul(String v1,double v2){
    	if(null == v1 || "".equals(v1) || Double.isNaN(v2) || v2==0){
    		return 0;
    	}
        BigDecimal b1 = new BigDecimal(Double.valueOf(v1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(v2));
        return round(b1.multiply(b2).doubleValue(),DEF_SCALE);
    }
    
    /**
     * 提供精确的乘法运算--自定义精度
     * @param v1 被乘
     * @param v2 乘数
     * @param scale 精度
     * @return 两个参数的积
     * **/
    public static double mulByAccuracy(double v1,double v2,int scale){
    	 if(scale<0){
             throw new IllegalArgumentException(
                 "The scale must be a positive integer or zero");
         }
    	if("".equals(v1)){
    		v1 = 0.0;
    	}
    	if("".equals(v2)){
    		v2 = 0.0;
    	}
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return round(b1.multiply(b2).doubleValue(),scale);
    }
    
    public static double mulByAccuracy(String v1,double v2,int scale){
    	 if(scale<0){
             throw new IllegalArgumentException(
                 "The scale must be a positive integer or zero");
         }
    	if(null == v1 || "".equals(v1) || Double.isNaN(v2) || v2==0){
    		return 0;
    	}
        BigDecimal b1 = new BigDecimal(Double.valueOf(v1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(v2));
        return round(b1.multiply(b2).doubleValue(),scale);
    }
    
    /**提供精确的乘法运算
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal mul(BigDecimal v1,BigDecimal v2){
        return round(v1.multiply(v2),_SCALE);
    }
    /**
     * 提供精确的乘法运算---自定义精度
     * @param v1
     * @param v2
     * @param scale 精度
     * @return 两个参数的乘积
     * 
     * */
    public static BigDecimal mulByAccuracy(BigDecimal v1,BigDecimal v2,int scale){
    	 if(scale<0){
             throw new IllegalArgumentException(
                 "The scale must be a positive integer or zero");
         }
        return round(v1.multiply(v2),scale);
    }
    
    /**提供精确的乘法运算
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal mulOne(BigDecimal v1){
        return round(v1.multiply(one),_SCALE);
    }
    
    public static BigDecimal mulOne(Double v1){
        return round(new BigDecimal(v1).multiply(one),_SCALE);
    }
    public static BigDecimal mulOne_null(Double v1){
    	if(v1 == null){
    		return null;
    	}
        return round(new BigDecimal(v1).multiply(one),_SCALE);
    }
    /**提供精确的乘法运算 ---自定义精度
     * @param v1
     * @param scale 精度
     * @return
     */
    
    public static BigDecimal mulOneByAccuracy(BigDecimal v1,int scale){
    	 if(scale<0){
             throw new IllegalArgumentException(
                 "The scale must be a positive integer or zero");
         }
        return round(v1.multiply(one),scale);
    }
    
    public static BigDecimal mulOneByAccuracy(Double v1,int scale){
    	 if(scale<0){
             throw new IllegalArgumentException(
                 "The scale must be a positive integer or zero");
         }
        return round(new BigDecimal(v1).multiply(one),scale);
    }
    public static BigDecimal mulOne_null_def(Double v1,int scale){
    	 if(scale<0){
             throw new IllegalArgumentException(
                 "The scale must be a positive integer or zero");
         }
    	if(v1 == null){
    		return null;
    	}
        return round(new BigDecimal(v1).multiply(one),scale);
    }
 
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以0位，以后的数字四舍五入
     * @param v1 被除
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1,double v2){
    	if(v2==0){
    		return v1;
    	}
        return div(v1,v2,DEF_DIV_SCALE);
    }
    
    
    public static double div(String v1,double v2){
    	if(v2==0){
    		return new Double(v1);
    	}
        return div(new Double(v1),v2,DEF_DIV_SCALE);
    }
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以0位，以后的数字四舍五入---自定义精度
     * @param v1 被除
     * @param v2 除数
     * @param scale 精度
     * @return 两个参数的商
     */
    public static double divByAccuracy(double v1,double v2,int scale){
    	 if(scale<0){
             throw new IllegalArgumentException(
                 "The scale must be a positive integer or zero");
         }
    	if(v2==0){
    		return v1;
    	}
        return div(v1,v2,scale);
    }
    
    
    public static double divByAccuracy(String v1,double v2,int scale){
    	 if(scale<0){
             throw new IllegalArgumentException(
                 "The scale must be a positive integer or zero");
         }
    	if(v2==0){
    		return new Double(v1);
    	}
        return div(new Double(v1),v2,scale);
    }
    
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal div(BigDecimal v1,BigDecimal v2){
    	if(v2.doubleValue()==zero){
    		return null;
    	}
        return div(v1,v2,_SCALE);
    }
    /** 自定义精度
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * @param v1
     * @param v2
     * @param scale 精度
     * @return
     */
    public static BigDecimal divByAccuracy(BigDecimal v1,BigDecimal v2,int scale){
    	 if(scale<0){
             throw new IllegalArgumentException(
                 "The scale must be a positive integer or zero");
         }
    	if(v2.doubleValue()==zero){
    		return null;
    	}
        return div(v1,v2,scale);
    }
 
    /**
     * 提供（相对）精确的除法运算当发生除不尽的情况时，由scale参数
     * 定精度，以后的数字四舍五入
     * @param v1 被除
     * @param v2 除数
     * @param scale 表示表示精确到小数点以后几位
     * @return 两个参数的商
     */
    public static double div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
     public static BigDecimal div(BigDecimal v1,BigDecimal v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
       
        return v1.divide(v2,scale,scale);
    }
 
    /**
     * 提供精确的小数位四舍五入处理
     * @param v 四舍五入的数
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
        String vv = Double.toString(v);
        BigDecimal b = null;
        if(vv.contains("E")){
        	vv = vv.substring(0,vv.indexOf("E"));
        	b = new BigDecimal(vv);
        }else{
        	b = new BigDecimal(Double.toString(v));
        }
     
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public static BigDecimal round(BigDecimal v,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
     
        BigDecimal one = new BigDecimal("1");
        return v.divide(one,scale,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 返回余数
	 * @param str1
	 * @param str2
	 * @return
	 */
    public static Double remainder(String str1,Double doub){	  
		double d1 = ArithUtil.div(str1, doub);
		double d2 = (int)d1;
		return d1-d2;
   }

}
