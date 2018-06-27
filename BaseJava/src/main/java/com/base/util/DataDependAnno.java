package com.base.util;


import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataDependAnno {
	
		 /**
	     * DependCode：实体依赖标识
	     * @author	limingxing
	     * @date	2018-6-5 上午10:51:17
	     * @version	1.0.0
	     */		
		 @Target({ElementType.FIELD,ElementType.TYPE})  
		 @Retention(RetentionPolicy.RUNTIME)  
		 @Documented 
	     public @interface Depend {
	    	 boolean value() default true;
	     }
	     /**
	     * DependCode：被依赖字段  value="被依赖表名" 
	     * @author	limingxing
	     * @date	2018-6-5 上午10:51:17
	     * @version	1.0.0
	     */		
	 	 @Target({ ElementType.FIELD })
	     @Retention(RetentionPolicy.RUNTIME)
	     @Documented
	     public @interface DependedTable {
	    		
	    	String value() default "DependedTable";
	     }
   
	     /**
	     * DependCode：被依赖字段  value="被依赖表字段" 
	     * @author	limingxing
	     * @date	2018-6-5 上午10:51:17
	     * @version	1.0.0
	     */		
		 @Target({ ElementType.FIELD })
		 @Retention(RetentionPolicy.RUNTIME)
		 @Documented
	     public @interface DependedCode {
	    	String value() default "DependedCode";
	     }
    
	     /**
	     * DependCode：依赖数据标识字段
	     * @author	limingxing
	     * @date	2018-6-5 上午10:51:17
	     * @version	1.0.0
	     */		
	     @Target({ ElementType.FIELD })
	     @Retention(RetentionPolicy.RUNTIME)
	     @Documented
	     public @interface DependColoum {
	
	     }
	     /**
	     * DependCode：依赖数据标识字段描述
	     * @author	limingxing
	     * @date	2018-6-5 上午10:51:17
	     * @version	1.0.0
	     */		
	     @Target({ ElementType.FIELD })
		 @Retention(RetentionPolicy.RUNTIME)
		 @Documented
	     public @interface DependText {
	    	String value() default "DependText";
	     }
      
	     /**
	     * DependCode：依赖功能描述
	     * @author	limingxing
	     * @date	2018-6-5 上午10:51:17
	     * @version	1.0.0
	     */		
	     @Target({ElementType.FIELD,ElementType.TYPE})  
		 @Retention(RetentionPolicy.RUNTIME)  
		 @Documented 
	     public @interface DependDec {
	     	String value() default "DependDec";
	     }
	     /**
		 * DependCode：被依赖功能描述
		 * @author	limingxing
		 * @date	2018-6-5 上午10:51:17
		 * @version	1.0.0
		 */		
		 @Target({ElementType.FIELD,ElementType.TYPE})  
	     @Retention(RetentionPolicy.RUNTIME)  
		 @Documented 
		 public @interface DependedDec {
		     String value() default "DependedDec";
		 }
	       
	     /**
		 * DependCode： 是否允许编辑
		 * @author	limingxing
		 * @date	2018-6-5 上午10:51:17
		 * @version	1.0.0
		 */		
		 @Target({ ElementType.FIELD })
		 @Retention(RetentionPolicy.RUNTIME)
		 @Documented
		 public @interface isEdit {
		     String value() default "Y";
		 }
		       
		 /**
		 * DependCode：被依赖数据标识字段
		 * @author	limingxing
		 * @date	2018-6-5 上午10:51:17
		 * @version	1.0.0
		 */		
		 @Target({ ElementType.FIELD })
		 @Retention(RetentionPolicy.RUNTIME)
		 @Documented
		 public @interface DependedColoum {
			
		 }		  
	
     
    
}