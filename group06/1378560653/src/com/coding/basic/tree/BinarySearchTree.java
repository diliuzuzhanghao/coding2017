package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.coding.basic.queue.Queue;

public class BinarySearchTree<T extends Comparable<T>> {
	/*
	 * V2.0 
	 */
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	
	public T findMin(){
		if(root == null) {
			return null;
		}
		
		return findMin(root).getData();
	}
	
	public T findMax(){
		if(root == null) {
			return null;
		}
	
		return findMax(root).getData();
	}
	
	public int height() {
	    return height(root);
	}
	
	public int size() {
		return size(root);
	}
	
	public void remove(T e){
		remove(e,root);
	}
	
	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
		if(node == null) {
			return null;
		} else if (node.left == null) {
			return node;
		} else {
			return findMin(node.left);
		}
	}
	
	private BinaryTreeNode<T> findMax(BinaryTreeNode<T> node) {
		if(node == null) {
			return null;
		} else if (node.right == null) {
			return node;
		} else {
			return findMax(node.right);
		}
	}
	
	private int height(BinaryTreeNode<T> node) {
		if (node == null){
			return 0;
		} else {
			int leftTreeHeight = height(node.left);
			int rightTreeHeight = height(node.right);
			if(leftTreeHeight > rightTreeHeight) {
				return leftTreeHeight + 1;
			} else {
				return rightTreeHeight + 1;
			}
		}
	}
	
	private int size(BinaryTreeNode<T> node) {
		if(node == null){
			return 0;
		} 
		return size(node.left) + size(node.right) + 1;
	}
	
	private BinaryTreeNode<T> remove(T x, BinaryTreeNode<T> t) {
		if(t == null) {
			return t;
		}
		
		int compareResult = x.compareTo(t.data);
		
		if(compareResult < 0) {
			t.left = remove(x, t.left);
		} else if (compareResult > 0) {
			t.right = remove(x, t.right);
		} else {
			if(t.left != null && t.right != null){
				t.data = findMin(t.right).data;
				t.right = remove(t.data, t.right);
			} else {
				t = (t.left != null) ? t.left : t.right;
			}
		}
		
		return t;
	}

	/*
	 * 二叉树按层遍历
	 */
	public List<T> levelVisit(){
		if(root == null) {
			return new ArrayList<>();
		}
		
		List<T> result = new ArrayList<>();
		Queue<BinaryTreeNode<T>> queue = new Queue<>();
		
		queue.enQueue(root);
		while(!queue.isEmpty()){
			BinaryTreeNode<T> cur = queue.deQueue();
			result.add(cur.data);
			if(cur.getLeft()!=null){
				queue.enQueue(cur.getLeft());
			}
			if(cur.getRight()!=null){
				queue.enQueue(cur.getRight());
			}
		}
		
		return result;
	}
	/*
	 * 二叉树按层遍历，按层打印
	 */
	public int[][] levelTravel() {
		LinkedList<BinaryTreeNode<T>> queue = new LinkedList<>(); //创建一个队列：使用链表来实现队列
		List<ArrayList<BinaryTreeNode<T>>> results = new ArrayList<>(); //每一层放在一个arraylist中，然后将每一层放在results中
		ArrayList<BinaryTreeNode<T>> levelList = new ArrayList<>(); //存放一层的节点
		
		BinaryTreeNode<T> last = root;
		BinaryTreeNode<T> nlast = null;
		BinaryTreeNode<T> cur = root;
		
		queue.add(root);
		
		while(!queue.isEmpty()){
			//弹出队列
			cur = queue.poll(); 
			levelList.add(cur);
			
			//将弹出节点的左右孩子入队
			if(cur.left != null){
				queue.add(cur.left);
				nlast = cur.left;
			}
			
			if(cur.right != null){
				queue.add(cur.right);
				nlast = cur.right;
			}
			
			//判断是否到此层最右节点，如果是，换行更新
			if(cur == last){
				results.add(levelList);
				levelList = new ArrayList<>();
				last = nlast;
			}
		}
		
		int[][] result = new int[results.size()][];
		for(int i = 0; i < results.size(); i++){
			result[i] = new int[results.get(i).size()];
			for(int j = 0; j < result[i].length; j++){
				result[i][j] = (int) results.get(i).get(j).getData();
			}
		}
		return result;
		
	}
	/*
	 * 判断二叉树是否为搜索二叉树：中序遍历的应用
	 */
	public boolean isValid(){
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
		
		BinaryTreeNode<T> cur = root;
		BinaryTreeNode<T> last = null;
		
		stack.push(root);
		
		while(!stack.isEmpty() || cur != null){
			while(cur != null){
				stack.push(cur);
				cur = cur.getLeft();
			}
			
			if(!stack.isEmpty()){
				cur = stack.pop();
				
				//当栈为空时，说明中序遍历结束，不必再比较，跳出循环。
				if(stack.isEmpty()){
					break;
				} 
				
				if(last != null && cur.data.compareTo(last.data) < 0){
					return false;
				}
				last = cur;
				cur = cur.getRight();	
			}
		}
		return true;
	}
	/*
	 * 找到2个节点的最小祖先：利用先序遍历，找到一个节点，这个节点的特点是：必然是这2个节点中至少一个节点的父节点
	 */
	public T getLowestCommonAncestor(T n1, T n2){
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
		
		BinaryTreeNode<T> cur = root;
		stack.push(root);
		
		while(!stack.isEmpty()){
			cur = stack.pop();
			if(cur.getLeft().data == n1 || cur.getRight().data == n2) {
				return cur.data;
			}
			
			if(cur.getRight() != null){
				stack.push(cur.getRight());
			}
			
			if(cur.getLeft() != null){
				stack.push(cur.getLeft());
			}
		}
		return null;  
	}
	/**
	 * 返回所有满足下列条件的节点的值：  n1 <= n <= n2 , 
	 * n 为该二叉查找树中的某一节点
	 * 中序遍历的应用
	 * @param n1
	 * @param n2
	 * @return
	 */
	public List<T> getNodesBetween(T n1, T n2){
		List<T> result = new ArrayList<>();
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
		
		BinaryTreeNode<T> cur = root;
		stack.push(root);
		
		while(!stack.isEmpty() || cur!=null){
			while(cur != null){
				stack.push(cur);
				cur = cur.left;
			}
			
			if(!stack.isEmpty()){
				cur = stack.pop();
				if(cur.getData().compareTo(n1) >=0 && cur.getData().compareTo(n2) <= 0){
					result.add(cur.getData());
				}
				cur = cur.getRight();
			}
		}
		
		return result;
	}
	
	public static void main(String[] args){
		
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);
		root.left = new BinaryTreeNode<Integer>(2);
		root.right = new BinaryTreeNode<Integer>(8);
		root.left.left = new BinaryTreeNode<Integer>(1);
		root.left.right = new BinaryTreeNode<Integer>(4);
		root.left.right.left = new BinaryTreeNode<Integer>(3);
		root.left.right.right = new BinaryTreeNode<Integer>(5);
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(root);
		
		int[][] result= tree.levelTravel();
		for(int i = 0 ; i < result.length; i++){
			System.out.print(i+": ");
			for(int j = 0; j < result[i].length; j++){
				System.out.print(result[i][j]+" ");
			}
			System.out.println();
		}
		
		
	}

	
	
	/*V1.0-----------------------------------------------------------------------
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	public T findMin(){
		if(root == null){
			throw new RuntimeException("BinarySearchTree is empty");
		}
		
		BinaryTreeNode<T> cur = root;
				BinaryTreeNode<T> cur = root;
		while(cur.getLeft() != null) {
			cur = cur.getLeft();
		}
		
		return cur.getData();
	}
	
	public T findMax(){
		if(root == null){
			throw new RuntimeException("BinarySearchTree is empty");
		}
		
		BinaryTreeNode<T> cur = root;
		while(cur.getRight() != null) {
			cur = cur.getRight();
		}
		return cur.getData();
	}
	
	public int height(BinaryTreeNode<T> root) {
		if(root == null){
			return 0;
		} else {
			int lheight = height(root.getLeft());
			int rheight = height(root.getRight());
			return 1 + Math.max(lheight, rheight);
		}
	}
	
	public int size(BinaryTreeNode<T> root) {
		if(root == null){
			return 0;
		} else {
			int lsize = size(root.getLeft());
			int rsize = size(root.getRight());
			return lsize + rsize + 1;
		}
	}
	
	public void remove(T e){
		BinaryTreeNode<T> parent = null;
		BinaryTreeNode<T> node = root;
		//查找e
		while(node != null){
			if(node.getData().compareTo(e) == 0){
				break;
			} else if(node.getData().compareTo(e) > 0) {
				parent = node;
				node = node.left;
			} else {
				parent = node;
				node = node.right;
			}
		}
		
		if(node == null){
			throw new RuntimeException(e+" doesn't exist");
		}
		
		//删除e:这部分有问题。
		if(node.left == null){
			if(parent != null){
				if(node.getData().compareTo(parent.getData())<0){
					parent.setLeft(node.right);
				} else {
					parent.setRight(node.right);
				}
			} else { 
				root = root.right; //parent == null
			}
		} else {
			BinaryTreeNode<T> newparent = node;
			BinaryTreeNode<T> newnode = node.left;
			while(newnode.getRight() != null){
				newparent = newnode;
				newnode = newnode.getRight();
			}
			
			if(newnode.getData().compareTo(newparent.getData()) < 0){
				newparent.setLeft(newnode.getLeft());
			} else {
				newparent.setRight(newnode.getLeft());
			}
			
			node.setData(newnode.getData());
		}
	}
	----------------------------------------------------------------------------
	*/
	
	
	
}

