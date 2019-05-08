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
 * �ļ�����
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
		//fsd.addChoosableFileFilter(new FileNameExtensionFilter ("Object-Oriented Model��xml��(*.oom)","oom"));

		int result = fsd.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            // ��������"����", ���ȡѡ��ı���·��
        	
        	
            File file = fsd.getSelectedFile();
            String fp=file.getAbsolutePath();
            if(accept(file)) {
            	
            }
            else {
            	fp=fp+".txt";
            	//file=new File(file.getAbsolutePath()+"."+fsd.getFileFilter().getDescription());
            }
            
            //�ж����ô�к�׺�Զ����
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
		//�ı�������ݴ洢���ļ�
		File file=new File(this.getFileSavePath());
		
		if(file.exists()){//�Ѵ����ļ�

		     JOptionPane.showConfirmDialog(this, file + "�ļ��Ѿ�����,�Ƿ񸲸�!", "�ļ�����", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);//��ʾһ���Ի�����ʵ���Ƿ񸲸�Դ�ļ�

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
		//��׺�Ƿ�ʱtxt
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
		//�Լ�������ļ����֣��к�׺�ͷ��أ�û�оͷ���null
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
