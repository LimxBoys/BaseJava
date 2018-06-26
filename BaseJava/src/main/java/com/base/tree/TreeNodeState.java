package com.base.tree;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.FIELD,ElementType.TYPE})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
public @interface TreeNodeState {

}
