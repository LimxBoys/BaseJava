package com.base.util.tree;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

/**
 * asm反射工具类
 * @author limingxing
 *
 */
public class ReflectAsmUtil {
   
   public static Object newObject(Object entity){
	   ConstructorAccess  access = ConstructorAccess.get(entity.getClass());
	   entity = access.newInstance();
	   return entity;
   }
   
  
   public static void setValue(Object entity,String fieldName,Object value){
	   MethodAccess methodAccess = MethodAccess.get(entity.getClass());
	   try {
		   if(fieldName!=null){
			   fieldName = String.valueOf(fieldName.charAt(0)).toUpperCase()+fieldName.substring(1);
			   value =  methodAccess.invoke(entity, "set"+fieldName,value);
		   }
	   } catch (Exception e) {
		   LogUtil.error(e);
	   }  
   
   }
   
   private static Map<String, Field> isHasSuperFields(Map<String, Field> fieldMap, Class entityClazz){
	   try{
		   if(!"Object".equalsIgnoreCase(entityClazz.getSuperclass().getSimpleName())){
			   Field [] fields = entityClazz.getSuperclass().getDeclaredFields();
			   for (Field field : fields) {
				   if(Modifier.isStatic(field.getModifiers())){
					   continue;
				   }
				   fieldMap.put(field.getName(), field);
			   }
			   isHasSuperFields(fieldMap, entityClazz.getSuperclass());
		   }
	   } catch (Exception e) {
		   LogUtil.error(e);
	   } 
	   return fieldMap;
   }
   
   private static Map<String, Field> getEntityFields(Map<String, Field> fieldMaps, Object entity){
	   Field [] entityFields = entity.getClass().getDeclaredFields();
	   for (Field field : entityFields) {
		   if(Modifier.isStatic(field.getModifiers())){
			   continue;
		   }
		   	   fieldMaps.put(field.getName(), field);
	   }
   	   return isHasSuperFields(fieldMaps, entity.getClass()); 
   }
   
   private static Field getField(Object entity,String fieldName) throws Exception{
	   
	   Map<String, Field> maps = getEntityFields(new HashMap<String, Field>(), entity);
	   return maps.get(fieldName);
   }
   @SuppressWarnings("unchecked")
  public static void setValue(Object entity,Class annotationClass,Object value){
	   MethodAccess methodAccess = MethodAccess.get(entity.getClass());
	   try {
		   Map<String, Field> maps = getEntityFields(new HashMap<String, Field>(), entity);
		   Set<String> set = maps.keySet();
		   
		   for (String field : set) {
			   Field fs = getField(entity,field);
			   if(fs!=null&&fs.isAnnotationPresent(annotationClass)){
				   field = String.valueOf(field.charAt(0)).toUpperCase()+field.substring(1);
				   methodAccess.invoke(entity, "set"+field, value);
				   break;
			   }
		   }
	   } catch (Exception e) {
		   LogUtil.error(e);
	   }  
   }
   public static Object getValue(Object entity,String fieldName){
	   MethodAccess methodAccess = MethodAccess.get(entity.getClass());
	   Object value = null;
	   try {
		   if(fieldName!=null){
			   fieldName = String.valueOf(fieldName.charAt(0)).toUpperCase()+fieldName.substring(1);
			   int nameIndex = methodAccess.getIndex("get"+fieldName);
			   value =  methodAccess.invoke(entity, nameIndex);
		   }
	   } catch (Exception e) {
		   LogUtil.error(e);
	   }  
	   return value;
   }
   
   @SuppressWarnings("unchecked")
  public static Object getValue(Object entity,Class annotationClass){
	   MethodAccess methodAccess = MethodAccess.get(entity.getClass());
	   Object value = null;
	   Map<String, Field> maps = getEntityFields(new HashMap<String, Field>(), entity);
	   Set<String> set = maps.keySet();
	   
	   Field fs;
	   for (String field : set) {
			try {
				fs = getField(entity,field);
				if(fs!=null&&fs.isAnnotationPresent(annotationClass)){
					   field = String.valueOf(field.charAt(0)).toUpperCase()+field.substring(1);
					   int nameIndex = methodAccess.getIndex("get"+field);
					   value =  methodAccess.invoke(entity, nameIndex);
					   break;
				}
			} catch (Exception e) {
				LogUtil.error(e);
			}
	   }
	   return value;
   }
   
   /**
	 * @description 获得类的所有属性
	 * @param clazz
	 */
	public static List<Field> getAllField(Class<?> clazz) {
		List<Field> list = new ArrayList<Field>();
		for (Field field : clazz.getDeclaredFields()) {
			if(Modifier.isStatic(field.getModifiers())){
				continue;
			}
			list.add(field);
		}
		Class<?> _clazz = clazz.getSuperclass();
		while(_clazz != null) {
			list.addAll(getAllField(_clazz));
			_clazz = _clazz.getSuperclass();
		}
		return list;
	}   
	/**
	 * 获得类的所有标注了指定的atClass类型注解的属性
	 *
	 * @param clazz
	 * @param atClass
	 * @return
	 */
	public static List<Field> getFieldsByAnnotation(Class<?> clazz, Class<? extends Annotation> atClass) {
		List<Field> list = getAllField(clazz);
		List<Field> ret = new ArrayList<Field>();
		for (Field field : list) {
			if(field.isAnnotationPresent(atClass)){
				ret.add(field);
			}
		}
		return ret;
	}
	/**
	 * 获取一个Class对象的所有字段名
	 * @return
	 * @Description:
	 * @return List<String>
	 */
	public static List<String> getAllFieldNames(Class<?> clazz){
		List<String> list = new ArrayList<String>();
		List<Field> fields = getAllField(clazz);
		for (Field field : fields) {
			list.add(field.getName());
		}
		return list;
	}
	public static List<Object> getValues(Object entity,Class<? extends Annotation> annotationClass){
		   MethodAccess methodAccess = MethodAccess.get(entity.getClass());
		   Object value = null;
		   List<Field> fields = getFieldsByAnnotation(entity.getClass(),annotationClass);
		   List<Object> valuesList = new ArrayList<Object>();
		   for (Field field : fields) {
			   String fieldName = String.valueOf(field.getName().charAt(0)).toUpperCase()+field.getName().substring(1);
			   int nameIndex = methodAccess.getIndex("get"+fieldName);
			   value =  methodAccess.invoke(entity, nameIndex);
			   if(value!=null){
				   valuesList.add(value);
			   }
		   }
		   return valuesList;
	   }
}