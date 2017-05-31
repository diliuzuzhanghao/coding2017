package com.coding.basic.tree;

import java.io.File;

public class FileList {
	public static void list(File f) {
		if(!f.exists()){
			throw new IllegalArgumentException("目录："+ f + "不存在");
		}
		
		File[] files = f.listFiles();
		for(int i = 0; i<files.length; i++){
			if(files[i].isDirectory()){
				list(files[i]);
			}else{
				System.out.println(files[i]);
			}
		}
	}

	public static void main(String[] args){
		File file = new File("C:/Python36");
		list(file);
	}
	
}
