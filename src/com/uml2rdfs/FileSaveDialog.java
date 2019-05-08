package com.uml2rdfs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * 文件保存
 */
public class FileSaveDialog extends JFrame{

	private String fileSavePath;
	JFileChooser fsd = new JFileChooser();
	
	public void setFileSavePath(String filePath) {
		this.fileSavePath=filePath;
	}
	
	public String getFileSavePath() {
		return this.fileSavePath;
		
	}
	
	public FileSaveDialog() {
		
		fsd.setCurrentDirectory(new File("."));
		fsd.setSelectedFile(new File("UML Class Diagram_rdfs.txt"));
		fsd.setFileFilter(new FileNameExtensionFilter("RdfsText(*.txt)","txt"));
		//fsd.addChoosableFileFilter(new FileNameExtensionFilter ("Object-Oriented Model（xml）(*.oom)","oom"));

		int result = fsd.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            // 如果点击了"保存", 则获取选择的保存路径
        	
        	
            File file = fsd.getSelectedFile();
            String fp=file.getAbsolutePath();
            if(accept(file)) {
            	
            }
            else {
            	fp=fp+".txt";
            	//file=new File(file.getAbsolutePath()+"."+fsd.getFileFilter().getDescription());
            }
            
            //判断如果么有后缀自动添加
            //String fname = fsd.getName(file);
            //MyFileFilter mff=MyFileFilter(fsd);
            /*
            String ext = ((MyFileFilter)fsd.getFileFilter()).getExtension();
            String fileAbsolutePath=file.getAbsolutePath();
            if (fileAbsolutePath.toUpperCase().endsWith(ext.toUpperCase())) {
            	
            }
            else {
            	file.renameTo(new File(fileAbsolutePath+"."+ext));
            	file = new File(fileAbsolutePath+"."+ext);

            }
            */
            this.setFileSavePath(fp);
        }

	}
	
	public void fileSavaAs(JTextArea jta,String fileSavepath) throws IOException{
		//文本域的内容存储进文件
		File file=new File(this.getFileSavePath());
		
		if(file.exists()){//已存在文件

		     JOptionPane.showConfirmDialog(this, file + "文件已经存在,是否覆盖!", "文件存在", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);//显示一个对话框来实现是否覆盖源文件

		}
		FileOutputStream fos=new FileOutputStream(file);
		OutputStreamWriter  writer=new OutputStreamWriter(fos,"UTF-8");
		
		String[] text=jta.getText().split("\n");;
		for(String str:text) {
			writer.write(str);
		    writer.write("\r\n");
		}
		writer.close();
		fos.close();
		
	}
	
	
	
	public boolean accept(File f) 
	{
		//后缀是否时txt
		if (f != null) 
		{
			
			String extension = getExtension(f);
			if (extension != null && extension.equals("txt")) 
			{
				return true;
			}
			else 
				return false;
		}
		return false;
	}



	

	public String getExtension(File f)
	{
		//自己输入的文件名字，有后缀就返回，没有就返回null
		if (f != null) 
		{
			String filename = f.getName();
			int i = filename.lastIndexOf('.');
			if (i > 0 && i < filename.length() - 1) 
			{
				return filename.substring(i + 1).toLowerCase();
			}
		}
		return null;
	}
	

}
