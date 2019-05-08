package com.uml2rdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * �ļ���ȡ
 */
public class FileOpenDialog extends JFrame{
	
	private String filePath;
	
	public FileOpenDialog(){
		JFileChooser fod = new JFileChooser();
		//Ĭ��Ϊ��ǰ�ļ���
		fod.setCurrentDirectory(new File("."));
		//ֻѡ�ļ�
		fod.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		//���ܶ�ѡ
		fod.setMultiSelectionEnabled(false);
		
		//�ļ����ˣ�power designer���ɵ�uml��ͼ���ļ����ͣ�Object-Oriented Model��xml��(*.oom);
		fod.setFileFilter(new FileNameExtensionFilter("Object-Oriented Model��xml��(*.oom)","oom"));
		fod.addChoosableFileFilter(new FileNameExtensionFilter ("Text(*.txt)","txt"));

		//��ȡ�ļ�·��
		
		int result = fod.showOpenDialog(this);
		
		if(result == JFileChooser.APPROVE_OPTION){
			File file = fod.getSelectedFile();//��ȡ�򿪵��ļ�
			this.setFilePath(file.getAbsolutePath());//��ȡ�ļ�·��
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
		//��ȡ�ļ�����
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
