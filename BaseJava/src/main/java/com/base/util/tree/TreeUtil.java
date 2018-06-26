package com.base.util.tree;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;



import com.base.tree.TreeNodeExtAttribute;
import com.base.tree.TreeNodeIcon;
import com.base.tree.TreeNodeId;
import com.base.tree.TreeNodeJoinAttribute;
import com.base.tree.TreeNodeParentId;
import com.base.tree.TreeNodeQueryByParent;
import com.base.tree.TreeNodeRoot;
import com.base.tree.TreeNodeState;
import com.base.tree.TreeNodeText;

public class TreeUtil {

	/**
	 * 构建树
	 * @param dataList数据实体list
	 * @param entity数据实体
	 * @return
	 */
	public static String buildTree(List dataList, Object entity) {
		String rootId = TreeUtil.getEntityTreeNodeRootId(entity);
		String rootName = TreeUtil.getEntityTreeNodeRootName(entity);
		JSONArray childArray = new JSONArray();
		Map<String, Object> treeNodeMap = new LinkedHashMap<String, Object>();
		Map<String, List<Object>> mapForTree =  new LinkedHashMap<String, List<Object>>();
		List<Object> rootNodeList = new ArrayList<Object>();
		
		//将树节点list集合转化成map集合
		for(Object treeNode : dataList){
			String id = TreeUtil.getEntityTreeId(treeNode);
			if(treeNode!=null&&!treeNodeMap.containsKey(id)){
				treeNodeMap.put(id, treeNode);
			}		
		}
		 
		//将树节点map集合组合成key为父节点的map集合
		for (String key : treeNodeMap.keySet()) {
			Object treeNode = treeNodeMap.get(key);
			if(treeNode == null){
				continue;
			}
			//判断是否已存在以该权限的父节点为key的集合
			String parentId = TreeUtil.getEntityTreeParentId(treeNode);
			if(parentId!=null&&mapForTree.get(parentId) != null&&mapForTree.get(parentId).toString().length()>0){
				List<Object> list = mapForTree.get(parentId);
				list.add(treeNode);
			} else{
				List<Object> objList = new ArrayList<Object>();
				objList.add(treeNode);
				mapForTree.put(parentId, objList);
			}
		}
//		将父节点不在集合中的节点抽取出来，成为一级节点
		for (String key : mapForTree.keySet()) {
			if(treeNodeMap.get(key) == null){
				List<Object> os = mapForTree.get(key);
				for(Object o : os){
					rootNodeList.add(o);
				}
			}
		}
		//构造一级节点，并组装他们的孩子节点集合
		if(rootNodeList!=null){
			for(Object treeObj : rootNodeList){
 				String id = TreeUtil.getEntityTreeId(treeObj);
				//String parentId = TreeUtil.getEntityTreeParentId(treeObj);
				String newNodeText = TreeUtil.getEntityTreeNodeText(treeObj);
				String newNodeIcon = TreeUtil.getEntityTreeNodeIcon(treeObj);
				Map<String,Object> map = TreeUtil.getEntityTreeExtAttribute(treeObj);
				Map<String,Object> queryMap =TreeUtil.getEntityTreeNodeQueryParam(treeObj);
				JSONObject jsonObj = buildChildNode(mapForTree,treeObj);
				addJsonObject(jsonObj, id, newNodeText,newNodeIcon,"",map,queryMap);
				childArray.add(jsonObj);
	    	}
		}
		
		String rootFieldName = TreeUtil.getEntityFieldNameNodeText(entity);
		Map<String,Object> map = TreeUtil.getEntityTreeExtAttribute(entity);
 		Map<String,Object> queryMap =TreeUtil.getEntityTreeNodeQueryParam(entity);
		if (StringUtils.isBlank(rootName)) {
			return JsonUtil.toJSONStringForJSONArray(childArray);
		}
		JSONArray rootArray = getRootTreeNode(childArray, rootId, rootName,
				rootFieldName,map,queryMap);
		return JsonUtil.toJSONStringForJSONArray(rootArray);
	}
	
	/**
	 * @discription:  递归方法，为树节点组装子节点集合
	 */
    private static JSONObject buildChildNode(Map<String, List<Object>> mapForTree, Object childNode){
    	JSONObject currentObj = JsonUtil.toJSONObject(childNode);
    	JSONArray childArray = new JSONArray();
    	String id = TreeUtil.getEntityTreeId(childNode);
    	addTreeNodeJoinAttribute(childNode,currentObj,childArray,id);
    	List<Object> childNodeList = mapForTree.get(id);
    	if(childNodeList != null && childNodeList.size() > 0){
    		for(Object newNode : childNodeList){
    			String newNodeId = TreeUtil.getEntityTreeId(newNode);
    			String newNodeText = TreeUtil.getEntityTreeNodeText(newNode);
    			String newNodeIcon = TreeUtil.getEntityTreeNodeIcon(newNode);
    			Map<String,Object> map = TreeUtil.getEntityTreeExtAttribute(newNode);
    			Map<String,Object> queryMap =TreeUtil.getEntityTreeNodeQueryParam(childNode);
				JSONObject childObj = buildChildNode(mapForTree,newNode);
				addJsonObject(childObj, newNodeId, newNodeText,newNodeIcon,"",map,queryMap);
				childArray.add(childObj);
    		}
    	} 
    	
    	if (!childArray.isEmpty()) {
			String newNodeId = TreeUtil.getEntityTreeId(childNode);
			String newNodeText = TreeUtil.getEntityTreeNodeText(childNode);
			String newNodeIcon = TreeUtil.getEntityTreeNodeIcon(childNode);
			String newNodeState = TreeUtil.getEntityTreeNodeState(childNode);
			addJSONArray(currentObj, childArray);
			Map<String,Object> map = TreeUtil.getEntityTreeExtAttribute(childNode);
			Map<String,Object> queryMap =TreeUtil.getEntityTreeNodeQueryParam(childNode);
			addJsonObject(currentObj, newNodeId, newNodeText,newNodeIcon,newNodeState,map,queryMap);
		} 
		return currentObj;
    }
	

	private static void addJsonObject(JSONObject jsonObj, String id, String text,String icon,String state,Map<String,Object> map,Map<String,Object> queryMap) {
		jsonObj.put("id", id);
		jsonObj.put("text", text);
		jsonObj.put("iconCls", icon);
		if(state!=null&&state.length()>0){
			jsonObj.put("state", state);
		}
		if(queryMap!=null&&queryMap.size()>0){
			map.putAll(queryMap);
		}
		if(map!=null&&map.size()>0){
		    jsonObj.put("attributes", map);	
		} 
		
	}

	private static void addJSONArray(JSONObject jsonObj, JSONArray jSONArray) {
		jsonObj.put("children", jSONArray);
	}

	private static JSONArray getRootTreeNode(JSONArray childArray,
			String rootCode, String rootName, String rootFieldName,Map<String,Object> map,Map<String,Object> queryMap) {
		JSONObject rootNode = new JSONObject();
		JSONArray rootArray = new JSONArray();

		if (StringUtils.isNotBlank(rootName)) {
			addJsonObject(rootNode, rootCode, rootName,"icon-monitor-menu","",map,queryMap);
			rootNode.put(rootFieldName, rootName);
		}
		addJSONArray(rootNode, childArray);
		rootArray.add(rootNode);
		return rootArray;
	}

	private static void addTreeNodeJoinAttribute(Object currentNode,JSONObject currentObj,JSONArray childArray,String parentId){
		List resObj = TreeUtil.getEntityTreeNodeJoinAttribute(currentNode);
		if(resObj!=null&&resObj.size()>0){
			for (int i = 0; i < resObj.size(); i++) {
				Object obj = resObj.get(i);
				Map<String,Object> map = TreeUtil.getEntityTreeExtAttribute(obj);
				Map<String,Object> queryMap =TreeUtil.getEntityTreeNodeQueryParam(obj);
				JSONObject childObj_ = JsonUtil.toJSONObject(currentNode);
				String newNodeId = TreeUtil.getEntityTreeId(obj);
				String newNodeText = TreeUtil.getEntityTreeNodeText(obj);
				String newNodeState = TreeUtil.getEntityTreeNodeState(obj);
				childObj_.put("parentId",parentId);
				childObj_.put("text",newNodeText);
				childObj_.put("id",newNodeId);
				childObj_.put("state",newNodeState);
				if(queryMap!=null&&queryMap.size()>0){
					map.putAll(queryMap);
				}
				if(map!=null&&map.size()>0){
					childObj_.put("attributes", map);	
				}
				childArray.add(childObj_);
				currentObj.put("children", childArray);
			}
		}
		
	}
	
	public static String getEntityTreeNodeRootName(Object entity){
		Class<?> clazz = entity.getClass();
		if(clazz.isAnnotationPresent(TreeNodeRoot.class)){
			TreeNodeRoot treeNodeRoot =clazz.getAnnotation(TreeNodeRoot.class);
			if(treeNodeRoot!=null&&StringUtils.isNotBlank(treeNodeRoot.name())){
				return treeNodeRoot.name();
			}
		}
		return "";
	}
	
	public static String getEntityTreeNodeRootId(Object entity){
		Class<?> clazz = entity.getClass();
		if(clazz.isAnnotationPresent(TreeNodeRoot.class)){
			TreeNodeRoot treeNodeRoot =clazz.getAnnotation(TreeNodeRoot.class);
			if(treeNodeRoot!=null&&StringUtils.isNotBlank(treeNodeRoot.id())){
				return treeNodeRoot.id();
			} 
		}
		return "";
	}
	
	public static Map<String,Object> getEntityTreeExtAttribute(Object entity){
		Map<String,Object> map = new HashMap<String, Object>();
		Class<?> clazz = entity.getClass();
		Field fields[] = clazz.getDeclaredFields();
		for (Field field : fields) {
			if(field.isAnnotationPresent(TreeNodeExtAttribute.class)){
				 Object value =  getField(entity, field.getName());
				 TreeNodeExtAttribute attrValue = field.getAnnotation(TreeNodeExtAttribute.class);
				 if(attrValue != null &&!"".equals(attrValue.value())){
					 value = attrValue.value();
				 }
				 if(value == null){
					 value = "";
				 }
				 map.put(field.getName(), value);
			}
		}
		return map;
		
	}
	public static Map<String,Object> getEntityTreeNodeQueryParam(Object entity){
		Map<String,Object> map = new HashMap<String, Object>();
		Class<?> clazz = entity.getClass();
		Field fields[] = clazz.getDeclaredFields();
		for (Field field : fields) {
			if(field.isAnnotationPresent(TreeNodeQueryByParent.class)){
				 Object value =  getField(entity, field.getName());
				 TreeNodeQueryByParent attrValue = field.getAnnotation(TreeNodeQueryByParent.class);
				 if(attrValue != null &&!"".equals(attrValue.value())){
					 value = attrValue.value();
				 }
				 if(value == null){
					 value = "";
				 }
				 map.put(field.getName()+"_ByParent", value);
			}
		}
		return map;
		
	}
	/**
	 * 返回值一般都是集合，用于混合树查询
	 * @param entity
	 * @return
	 */
	public static List getEntityTreeNodeJoinAttribute(Object entity){
		Class<?> clazz = entity.getClass();
		Field fields[] = clazz.getDeclaredFields();
		for (Field field : fields) {
			if(field.isAnnotationPresent(TreeNodeJoinAttribute.class)){
				if("List".equalsIgnoreCase(field.getType().getSimpleName())){
					List value =  (List)getField(entity, field.getName());
					return value;
				}
				
			}
		}
		return null;
	}
	
	public static String getEntityTreeNodeText(Object entity){
		StringBuffer sb = new StringBuffer();
		List<Object> values = ReflectAsmUtil.getValues(entity, TreeNodeText.class);
		for (Object value : values) {
			if(value != null){
				 sb.append(value).append(" ");
			}
		}
		return sb.toString().trim();
	}
	public static String getEntityTreeNodeIcon(Object entity){
		Object value = ReflectAsmUtil.getValue(entity, TreeNodeIcon.class);
		 if(value == null){
			 value = "";
		 }
		return value.toString();
	}
	public static String getEntityTreeNodeState(Object entity){
		Object value = ReflectAsmUtil.getValue(entity, TreeNodeState.class);
		if(value == null || "0".equals(value)){
			 return "closed";
		}
		return "";
	}
	/**
	 * 获取具有TreeNodeText注解的字段属性名
	 * @param entity
	 * @return
	 */
	public static String getEntityFieldNameNodeText(Object entity){
		Class<?> clazz = entity.getClass();
		Field fields[] = clazz.getDeclaredFields();
		for (Field field : fields) {
			if(field.isAnnotationPresent(TreeNodeText.class)){
				 return field.getName();
			}
		}
		return "";
	}
	
	/**获取实体类的parentId
	 * 
	 * @param entity
	 * @return
	 */
	public static String getEntityTreeParentId(Object entity){
		Object value = ReflectAsmUtil.getValue(entity, TreeNodeParentId.class);
		if(value == null){
			 value = "";
		}
		return String.valueOf(value);
	}
	
	public static String getEntityTreeId(Object entity){
		Object value = ReflectAsmUtil.getValue(entity, TreeNodeId.class);
		if(value == null){
			 value = "";
		}
		return String.valueOf(value);
	}
	
	/**
	 * 通过反射直接取得对象的值
	 * */
	public static Object getField(Object entity, String fieldName) {
		Object value = ReflectAsmUtil.getValue(entity, fieldName);
		return value;
	}
	
	
}