package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeUtil {
	/**
	 * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
	 * 中-左-右
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		
		if(root != null){
			result.add(root.getData());
		}
		
		if(root.getLeft() != null){
			result.addAll(preOrderVisit(root.getLeft()));
		}
		
		if(root.getRight() != null){
			result.addAll(preOrderVisit(root.getRight()));
		}
		
		return result;
	}

	/**
	 * 用递归的方式实现对二叉树的中序遍历
	 * 左-中-右
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		
		if(root.getLeft() != null){
			result.addAll(inOrderVisit(root.getLeft()));
		}
		
		if(root != null){
			result.add(root.getData());
		}
		
		if(root.getRight() != null){
			result.addAll(inOrderVisit(root.getRight()));
		}
		
		return result;
	}

	/**
	 * 用递归的方式实现对二叉树的后序遍历
	 * 左-右-中
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		
		if(root.getLeft() != null){
			result.addAll(postOrderVisit(root.getLeft()));
		}
		
		if(root.getRight() != null){
			result.addAll(postOrderVisit(root.getRight()));
		}
		
		if(root != null){
			result.add(root.getData());
		}
		
		return result;
	}
	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();		
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
		
		stack.push(root);
		
		while(!stack.isEmpty()){
			BinaryTreeNode<T> cur = stack.pop();
			result.add(cur.getData());
			
			if(cur.getRight() != null){
				stack.push(cur.getRight());
			}
			
			if(cur.getLeft() != null){
				stack.push(cur.getLeft());
			}
		}
		
		return result;
	}
	/**
	 * 用非递归的方式实现对二叉树的中序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
		
		BinaryTreeNode<T> cur = root;
			
		while(!stack.isEmpty() || cur != null){
			while(cur != null){
				stack.push(cur);
				cur = cur.getLeft();
			}
			
			if(!stack.isEmpty()){
				cur = stack.pop();
				result.add(cur.getData());
				cur = cur.getRight();
			}
			
		}
		
		return result;
	}
	
	/**
	 * 用非递归的方式实现对二叉树的后序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> s1 = new Stack<>();
		Stack<BinaryTreeNode<T>> s2 = new Stack<>();
		
		s1.push(root);
		
		while(!s1.isEmpty()){
			BinaryTreeNode<T> cur = s1.pop();
			s2.push(cur);
			if(cur.getLeft()!=null){
				s1.push(cur.getLeft());
			}
			if(cur.getRight()!=null){
				s1.push(cur.getRight());
			}
		}
		
		while(!s2.isEmpty()){
			result.add(s2.pop().getData());
		}
		return result;
	}
}
