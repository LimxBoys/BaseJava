package com.base.test;
import java.util.ArrayList;  
import java.util.List;  
  
public class Tree {  
    private StringBuffer html = new StringBuffer();  
    private List<Node> nodes;  
      
    public Tree(List<Node> nodes){  
        this.nodes = nodes;  
    }  
      
    public String buildTree(){  
        html.append("<ul>");  
        for (Node node : nodes) {  
            Integer id = node.getId();  
            if (node.getParentId() == null) {  
                html.append("\r\n<li id='" + id + "'>" + node.getName()+ "</li>");  
                build(node);  
            }  
        }  
        html.append("\r\n</ul>");  
        return html.toString();  
    }  
      
    private void build(Node node){  
        List<Node> children = getChildren(node);  
        if (!children.isEmpty()) {  
            html.append("\r\n<ul>");  
            for (Node child : children) {  
                Integer id = child.getId();  
                html.append("\r\n<li id='" + id + "'>" + child.getName()+ "</li>");  
                build(child);  
            }  
            html.append("\r\n</ul>");  
        }   
    }  
      
    private List<Node> getChildren(Node node){  
        List<Node> children = new ArrayList<Node>();  
        Integer id = node.getId();  
        for (Node child : nodes) {  
            if (id.equals(child.getParentId())) {  
                children.add(child);  
            }  
        }  
        return children;  
    }  
}  