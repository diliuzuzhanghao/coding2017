package com.coding.basic.tree;


public class BinarySearchTree<T extends Comparable<T>> {
	
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
		T min = root.getData();
		while(cur.getLeft() != null){
			cur = cur.getLeft();
			if(cur.getData().compareTo(min) < 0){
				min = cur.getData();
			}
		}
		return min;
	}
	
	public T findMax(){
		if(root == null){
			throw new RuntimeException("BinarySearchTree is empty");
		}
		
		BinaryTreeNode<T> cur = root;
		T max = root.getData();
		while(cur.getRight() != null){
			cur = cur.getRight();
			if(cur.getData().compareTo(max) > 0){
				max = cur.getData();
			}
		}
		return max;
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
		
		//删除e
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
	
}

