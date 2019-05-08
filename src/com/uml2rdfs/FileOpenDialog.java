package com.uml2rdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * 文件读取
 */
public class FileOpenDialog extends JFrame{
	
	private String filePath;
	
	public FileOpenDialog(){
		JFileChooser fod = new JFileChooser();
		//默认为当前文件夹
		fod.setCurrentDirectory(new File("."));
		//只选文件
		fod.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		//不能多选
		fod.setMultiSelectionEnabled(false);
		
		//文件过滤，power designer生成的uml类图，文件类型：Object-Oriented Model（xml）(*.oom);
		fod.setFileFilter(new FileNameExtensionFilter("Object-Oriented Model（xml）(*.oom)","oom"));
		fod.addChoosableFileFilter(new FileNameExtensionFilter ("Text(*.txt)","txt"));

		//获取文件路径
		
		int result = fod.showOpenDialog(this);
		
		if(result == JFileChooser.APPROVE_OPTION){
			File file = fod.getSelectedFile();//获取打开的文件
			this.setFilePath(file.getAbsolutePath());//获取文件路径
			//System.out.println(file.getAbsolutePath());
		}	

	}
	
	public void setFilePath(String filePath) {
		this.filePath=filePath;
	}
	
	public String getFilePath() {
		return filePath;
		
	}
	
	public String getFile(String filePath) throws IOException{
		//获取文件内容
		filePath=this.getFilePath();
		File file=new File(filePath);
		FileInputStream fis=new FileInputStream(file);
		InputStreamReader reader=new InputStreamReader(fis,"UTF-8");
		
		StringBuffer sb=new StringBuffer();
		while(reader.ready()) {
			sb.append((char)reader.read());
		}
		
		reader.close();
		fis.close();
		
		return sb.toString();
		
	}
	
	
}
