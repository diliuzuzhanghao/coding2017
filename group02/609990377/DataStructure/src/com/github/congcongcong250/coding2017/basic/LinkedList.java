package com.github.congcongcong250.coding2017.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	public LinkedList(){
		head.next = head;
		head.previous = head;
		size = 0;
	}
	
	public void add(Object o){
		addLast(o);
	}
	
	public void add(int index , Object o){
		//Check bound
		if(index >= size){
			throw new IndexOutOfBoundsException("Index:"+index+" Size:"+size);
		}
		
		Node nx = this.find(index);
		Node pr = nx.previous;
		Node in = new Node(o,pr,nx);
		nx.previous = in;
		pr.next = in;
		size++;
	}
	
	public Object get(int index){
		//Check bound
		if(index >= size){
			throw new IndexOutOfBoundsException("Index:"+index+" Size:"+size);
		}
		return this.find(index);
	}
	
	public Object remove(int index){
		//Check bound
		if(index >= size){
			throw new IndexOutOfBoundsException("Index:"+index+" Size:"+size);
		}
		Node rem = this.find(index);
		
		Node pr = rem.previous;
		Node nx = rem.next;
		pr.next = nx;
		nx.previous = pr;
		
		Object ret = rem.data;
		rem.previous = null;
		rem.next = null;
		rem.data = null;
		size--;
		return ret;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node nx = head.next;
		Node in = new Node(o,head, nx);
		head.next = in;
		nx.previous = in;
		size++;
	}
	
	public void addLast(Object o){
		Node last = head.previous;
		Node in = new Node(o,last,head);
		last.next = in;
		head.previous = in;
		
		size++;
	}
	public Object removeFirst(){
		return remove(0);
	}
	public Object removeLast(){
		return remove(size-1);
	}
	public Iterator iterator(){
		return new ListItr();
	}
	
	private Node find(int index){
		Node tra = head;
		
		//If index < size/2
		if( index < (size >> 1)){
			for(int i = 0; i <= index; i++){
				tra = tra.next;
			}
		}else{
			for(int i = size; i >= index; i--){
				tra = tra.previous;
			}
		}
		return tra;
	}
	
	private static class Node{
		Object data;
		Node next;
		Node previous;
		
		public Node(Object obj,Node pre, Node nx){
			data = obj;
			next = nx;
			previous = pre;
		}
		
		
	}
	
	private class ListItr implements Iterator{
		//Point to next node
		Node cursor;
		int nextIndex;

		public ListItr(){
			cursor = head.next;
			nextIndex = 0;
		}
		
		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		@Override
		public Object next() {
			Node re = cursor;
			cursor = cursor.next;
			nextIndex++;
			return re;
		}
		
		public Object previous() {
			Node re = cursor.previous.previous;
			cursor = cursor.previous;
			nextIndex--;
			return re;
		}

		@Override
		public void remove() {
			//Check bound
			if(nextIndex > size){
				throw new NoSuchElementException("Iterates to the end");
			}
			LinkedList.this.remove(--nextIndex);
			
		}
		
	}
}
