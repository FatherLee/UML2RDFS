package com.uml2rdfs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.AncestorListener;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

/*
 * 程序主窗体及控件监听、触发
 * 
 */
public class MainFrame {
	
	//String filepath=new String();
	
	public MainFrame() {
		MainFra mf = new MainFra(); 
	}

	
	public static void main(String[] args) {
		new MainFrame();
	}
	
	//主窗体
	public class MainFra extends JFrame implements ActionListener{
		
		String filepath=new String();//记录打开的文件路径方便其他响应事件调用
		//String rowString=new String();
		JdomPrase temp=new JdomPrase();//赋予解析结果，给构造模块用
		
		
		
		JButton jbInput = new JButton("Input");
		JButton jbPrase = new JButton("Prase");
		JButton jbConstruct = new JButton("Construct");
		JButton jbSaveAs =new JButton("SaveAs");
		
		JLabel jlUml = new JLabel("UML:");
		JLabel jlClass = new JLabel("Classes:");
		JLabel jlRdfs = new JLabel("RDF(S):");
		
		JLabel jlAC = new JLabel("Associate classes:");
		JLabel jlAttr = new JLabel("Attributes:");
		JLabel jlRoles = new JLabel("Roles:");
		JLabel jlRS = new JLabel("Relationships:");
		JLabel jlRSC = new JLabel("Relationships classes:");
		JLabel jlConstraints= new JLabel(""	+ "Constraints:");
		
		JTextArea jtaUml;
		JTextArea jtaClass;
		JTextArea jtaAC;
		JTextArea jtaAttr;
		JTextArea jtaRoles;
		JTextArea jtaRS;
		JTextArea jtaRSC;
		JTextArea jtaConstraints;
		JTextArea jtaRDFS;
		//protected String rowStr;
		protected int line;//文本域光标所在行
		protected String storeC;//文本域光标所在行内容
		protected String storeAC;
		protected String storeRS;
		
		public MainFra() {
			this.setTitle("UML2RDFS");
			//主窗体大小固定
			this.setSize(1000, 720);
			this.setResizable(false);
			//绝对布局
			this.setLayout(null);
			//简单居中
			this.setLocationRelativeTo(null);
			//关闭方法
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//容器
			Container con = this.getContentPane();
			
			//三个按钮设置大小位置及添加监听
			
			jbInput.setBounds(100,20, 100, 30);
			jbPrase.setBounds(450, 20, 100, 30);
			jbConstruct.setBounds(750, 20, 100, 30);
			jbSaveAs.setBounds(850, 20, 100, 30);
			
			jbInput.addActionListener(this);
			jbPrase.addActionListener(this);
			jbConstruct.addActionListener(this);
			jbSaveAs.addActionListener(this);
			
			con.add(jbInput);
			con.add(jbPrase);
			con.add(jbConstruct);
			con.add(jbSaveAs);
			
			//label
			
			
			jlUml.setBounds(10, 60, 100, 10);
			jlClass.setBounds(360, 60, 100, 10);
			jlRdfs.setBounds(710, 60, 100, 10);
			
			
			jlAC.setBounds(360, 150, 110, 10);			
			jlAttr.setBounds(360, 240, 100, 10);			
			jlRoles.setBounds(360, 330, 100, 10);			
			jlRS.setBounds(360, 420, 100, 10);			
			jlRSC.setBounds(360, 510, 130, 10);			
			jlConstraints.setBounds(360, 600, 100, 10);
			
			con.add(jlUml);
			con.add(jlClass);
			con.add(jlRdfs);
			con.add(jlAC);
			con.add(jlAttr);
			con.add(jlRoles);
			con.add(jlRS);
			con.add(jlRSC);
			con.add(jlConstraints);
			
			
			//带滚动条的文本域
			jtaUml = new JTextArea();
			JScrollPane jsp1 = new JScrollPane(jtaUml);
			jspAlways(jsp1);
			jsp1.setBounds(10, 70, 280, 600);
			jtaUml.setEditable(false);
			con.add(jsp1);
			
			jtaClass = new JTextArea();
			JScrollPane jsp2 = new JScrollPane(jtaClass);
			jspAlways(jsp2);
			jsp2.setBounds(360, 70, 280, 70);
			//jtaClass.setEditable(false);
			jtaClass.setName("Classes");
			
			con.add(jsp2);
			
			jtaAC = new JTextArea();
			JScrollPane jsp3 = new JScrollPane(jtaAC);
			jspAlways(jsp3);
			jsp3.setBounds(360, 160, 280, 70);
			//jtaAC.setEditable(false);
			jtaAC.setName("Associate classes");
			con.add(jsp3);
			
			jtaAttr = new JTextArea();
			JScrollPane jsp4 = new JScrollPane(jtaAttr);
			jspAlways(jsp4);
			jsp4.setBounds(360, 250, 280, 70);
			jtaAttr.setEditable(false);
			jtaAttr.setName("Attributes");
			con.add(jsp4);
			
			jtaRoles = new JTextArea();
			JScrollPane jsp5 = new JScrollPane(jtaRoles);
			jspAlways(jsp5);
			jsp5.setBounds(360, 340, 280, 70);
			jtaRoles.setEditable(false);
			jtaRoles.setName("Roles");
			con.add(jsp5);
			
			jtaRS = new JTextArea();
			JScrollPane jsp6 = new JScrollPane(jtaRS);
			jspAlways(jsp6);
			jsp6.setBounds(360, 430, 280, 70);
			//jtaRS.setEditable(false);
			jtaRS.setName("Relationships");
			con.add(jsp6);
			
			jtaRSC = new JTextArea();
			JScrollPane jsp7 = new JScrollPane(jtaRSC);
			jspAlways(jsp7);
			jsp7.setBounds(360, 520, 280, 70);
			jtaRSC.setEditable(false);
			jtaRSC.setName("Relationship Classes");
			con.add(jsp7);
			
			jtaConstraints = new JTextArea();
			JScrollPane jsp8 = new JScrollPane(jtaConstraints);
			jspAlways(jsp8);
			jsp8.setBounds(360, 610, 280, 70);
			jtaConstraints.setEditable(false);
			jtaConstraints.setName("Constraints");
			con.add(jsp8);
			
			jtaRDFS = new JTextArea();
			JScrollPane jsp9 = new JScrollPane(jtaRDFS);
			jspAlways(jsp9);
			jsp9.setBounds(710, 70, 280, 600);
			jtaRDFS.setEditable(false);
			con.add(jsp9);
			
			
			//文本域监听  传回光标所在行的字符串
			jtaClass.addCaretListener(new CaretListener() {
				public void caretUpdate(CaretEvent e) {
					if (jtaClass.getText().trim().length() == 0)
						return;
 
					int offset = e.getDot();
					// 计算光标所在行
					try {
                                                //得到光标所在的行数
						line = jtaClass.getLineOfOffset(offset);
						//System.out.println(line);
 
					} catch (BadLocationException e3) {
						e3.printStackTrace();
					}
				}
			});
			jtaClass.addMouseListener(new MouseAdapter() {
				 
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() != 0) {// 获取该行
						//DefaultHighlighter h = (DefaultHighlighter) jtaClass.getHighlighter();
						//MyHighlightPainter p = new MyHighlightPainter(new Color(226, 239, 255));
						String[] lines = jtaClass.getText().split("\r\n");
						storeC = lines[line];
					}
					
				}
			});
			
			//文本域监听  传回光标所在行的字符串
			jtaAC.addCaretListener(new CaretListener() {
				public void caretUpdate(CaretEvent e) {
					if (jtaAC.getText().trim().length() == 0)
						return;
 
					int offset = e.getDot();
					// 计算光标所在行
					try {
                                                //得到光标所在的行数
						line = jtaAC.getLineOfOffset(offset);
						//System.out.println(line);
 
					} catch (BadLocationException e3) {
						e3.printStackTrace();
					}
				}
			});
			jtaAC.addMouseListener(new MouseAdapter() {
				 
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() != 0) {// 获取该行
					//	DefaultHighlighter h = (DefaultHighlighter) jtaClass.getHighlighter();
					//	MyHighlightPainter p = new MyHighlightPainter(new Color(226, 239, 255));
						String[] lines = jtaAC.getText().split("\r\n");
						storeAC = lines[line];
					}
					
				}
			});
		
		
			//文本域监听  传回光标所在行的字符串
			jtaRS.addCaretListener(new CaretListener() {
				public void caretUpdate(CaretEvent e) {
					if (jtaRS.getText().trim().length() == 0)
						return;
 
					int offset = e.getDot();
					// 计算光标所在行
					try {
                                                //得到光标所在的行数
						line = jtaRS.getLineOfOffset(offset);
						//System.out.println(line);
 
					} catch (BadLocationException e3) {
						e3.printStackTrace();
					}
				}
			});
			jtaRS.addMouseListener(new MouseAdapter() {
				 
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() != 0) {// 获取该行
						//DefaultHighlighter h = (DefaultHighlighter) jtaClass.getHighlighter();
						//MyHighlightPainter p = new MyHighlightPainter(new Color(226, 239, 255));
						String[] lines = jtaRS.getText().split("\r\n");
						storeRS = lines[line];
					}
					
				}
			});
		
			
					
			//主窗体可见
			this.setVisible(true);
			
		}

		@Override
		//按钮触发事件
		public void actionPerformed(ActionEvent e) {
			
			 
			
			if(e.getSource()==jbInput) {
				FileOpenDialog fod=new FileOpenDialog();
				try {
					 filepath=fod.getFilePath();
					 jtaUml.setText(fod.getFile(filepath));
					System.out.println(filepath);
				}catch(IOException e1) {
					e1.printStackTrace();
				}
				
			}
			else if(e.getSource()==jbPrase) {
			
				JdomPrase jp=new JdomPrase(filepath);
				//jp.show(jtaClass,"Classes");
				jp.show(jtaClass);
				jp.show(jtaAC);
				jp.show(jtaRS);
				
			System.out.println("解析成功");
			System.out.println(storeC);System.out.println(storeAC);System.out.println(storeRS);
				jp.show(jtaAttr, storeC);
				jp.show(jtaRoles, storeAC);
				jp.show(jtaConstraints,storeAC);
				jp.show(jtaRSC, storeRS);		
				
				System.out.println(jp.eClass.size());
				temp.copy(jp);
				System.out.println(temp.eClass.size());
			}
			else if(e.getSource()==jbConstruct) {
				
				Construct con=new Construct();
				con.constrClass(temp.eClass,temp.eGeneralization);
				
				con.ConstrGen(temp.eGeneralization,temp.eClass);
				
				con.ConstrAsso(temp.eAssociation);
				
				con.ConstrAggre(temp.eAggregation);
				
				con.ConstrDep(temp.eDependency,temp.eClass);
				
				jtaRDFS.setText(con.getStr());
			}
			else if(e.getSource()==jbSaveAs) {
				FileSaveDialog fsd=new FileSaveDialog();
				try {
					fsd.fileSavaAs(jtaRDFS, fsd.getFileSavePath());
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		}
			
			
		
		//滚动条总是出现
		public void jspAlways(JScrollPane jsp){
			//分别设置水平和垂直滚动条总是出现
			  jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			  jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
		}
		
	}
	/*class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
		//gao一行高亮
		public MyHighlightPainter(Color color) {
			super(color);
		}
	}*/

}
