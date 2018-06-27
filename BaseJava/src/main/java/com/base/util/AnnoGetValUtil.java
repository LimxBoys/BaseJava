package com.base.util;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Table;

import com.base.util.DataDependAnno.Depend;
import com.base.util.DataDependAnno.DependColoum;
import com.base.util.DataDependAnno.DependDec;
import com.base.util.DataDependAnno.DependText;
import com.base.util.DataDependAnno.DependedCode;
import com.base.util.DataDependAnno.DependedColoum;
import com.base.util.DataDependAnno.DependedDec;
import com.base.util.DataDependAnno.DependedTable;
import com.base.util.DataDependAnno.isEdit;


/**
* AnnoGetValUtil：通过注解获取值、属性（应用于数据依赖）
* @author	limingxing
* @date	2018-6-5 下午1:40:17
* @version	1.0.0
*/		
public class AnnoGetValUtil {

	
	/**
	* getAnnoInfo：获取指定变量上的注解信息或者注解field
	* @author limingxing
	* @param entity 参数实体
	* @param atClass  注解类
	* @return	String  注解信息或者field
	*/
	public static String getAnnoInfo(Object entity,Class<? extends Annotation> atClass) {
		String value=null;
		Field[]  list = entity.getClass().getDeclaredFields();
	    for(Field f : list){
	            Field field = f;
	            if(field.isAnnotationPresent(atClass)){//是否使用数据依赖注解
	                for (Annotation anno : field.getDeclaredAnnotations()) {//获得所有的注解
	                    if(anno.annotationType().equals(atClass) ){//找到自己的注解
	                    	if(atClass.equals(DependedCode.class)){  //被依赖表字段
	                    		DependedCode annotation = field.getAnnotation(DependedCode.class);  
	                    		if(value!=null){
	                    			value=value+","+annotation.value();
	                    		}else{
	                    			value=annotation.value();
	                    		}
	                    		
	                    	}
	                    	if(atClass.equals(DependText.class)){ //依赖数据标识字段描述
	                    		DependText annotation = field.getAnnotation(DependText.class);  
	                    		
	                    			value=annotation.value();
	                    		
	                    	}
	                    	if(atClass.equals(DependDec.class)){ // 依赖功能描述
	                    		DependDec annotation = field.getAnnotation(DependDec.class);  
	                    		
	                    			value=annotation.value();
	                    	}
	                    	if(atClass.equals(DependedDec.class)){ // 被依赖功能描述
	                    		DependedDec annotation = field.getAnnotation(DependedDec.class);  
	                    		if(value!=null){
	                    			value=value+","+annotation.value();
	                    		}else{
	                    			value=annotation.value();
	                    		}
	                    	}
	                    	if(atClass.equals(DependedTable.class)){ //被依赖表名
	                    		DependedTable annotation = field.getAnnotation(DependedTable.class);  
	                    		if(value!=null){
	                    			value=value+","+annotation.value();
	                    		}else{
	                    			value=annotation.value();
	                    		}
	                    	}
	                    	if(atClass.equals(isEdit.class)){ //被依赖表名
	                    		isEdit annotation = field.getAnnotation(isEdit.class);  
	                    		if(value!=null){
	                    			value=value+","+annotation.value();
	                    		}else{
	                    			value=annotation.value();
	                    		}
	                    	}
	                    	if(atClass.equals(DependedColoum.class)){ //被依赖数据标识
	                    		if(value!=null){
	                    			value=value+","+field.getName();
	                    		}else{
	                    			value=field.getName();
	                    		}
	                    	}
	                    	if(atClass.equals(DependColoum.class)){ // 依赖数据标识
	                    		value=field.getName();
	                    	}
	                    }
	                }
	            }

	        }
		return value;
	}
	
	
	/**
	* getClassAnno：获取实体类上的注解信息（用于判断是否做依赖保存）
	* @author limingxing
	* @param entity  参数实体
	* @param atClass 注解类
	* @return	Boolean  
	*/
	public static Boolean getClassAnno(Object entity,Class<? extends Annotation> atClass) {
		Boolean flag=false;
		if(atClass.equals(Depend.class)){
			Depend annotation = entity.getClass().getAnnotation(Depend.class);
			if(annotation==null){
				flag=false;
			}else{
				flag=annotation.value();
			}
			
		}
		  
		return flag;
		
	}
	
	
	/**
	* getClassAnno：获取实体类上的注解信息
	* @author limingxing
	* @param entity  参数实体
	* @param atClass 注解类
	* @return	Boolean  
	*/
	public static String getClassAnnos(Object entity,Class<? extends Annotation> atClass) {
		String value=null;
		if(atClass.equals(Table.class)){
			Table annotation = entity.getClass().getAnnotation(Table.class);
			if(annotation==null){
				value="";
			}else{
				value=annotation.name();
			}
			
		}
		if(atClass.equals(DependDec.class)){
			DependDec annotation = entity.getClass().getAnnotation(DependDec.class);
			if(annotation==null){
				value="";
			}else{
				value=annotation.value();
			}
			
		}
		  
		return value;
		
	}
	
	/**驼峰转下划线*/  
    public static String humpToLine(String str){  
    	Pattern humpPattern = Pattern.compile("[A-Z]");  
        Matcher matcher = humpPattern.matcher(str);  
        StringBuffer sb = new StringBuffer();  
        while(matcher.find()){  
            matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());  
        }  
        matcher.appendTail(sb);  
        return sb.toString();  
    }  
}
