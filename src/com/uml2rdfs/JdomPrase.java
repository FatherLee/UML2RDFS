package com.uml2rdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;



public class JdomPrase {

//	final static int SIZE=  50; 
	//解析结果装入类

	List<Clas> eClass=new ArrayList<Clas>();
	List<Generalization> eGeneralization=new ArrayList<Generalization>();
	List<Dependency> eDependency=new ArrayList<Dependency>();
    List<Association> eAssociation=new ArrayList<Association>();
    List<Aggregation> eAggregation=new ArrayList<Aggregation>();

	//解析用的
    Element eClasses ;
    Element eAssociations ;
    Element eGeneralizations ;
    Element eDependencies ;
   // Element eAssociationClassLinks;
    
    public JdomPrase() {
		// TODO Auto-generated constructor stub
	}
    
	public JdomPrase(String fileName) {
		//解析   参数：文件名  
	
		
		try { 
			SAXBuilder builder=new SAXBuilder();
			FileInputStream fis=new FileInputStream(new File(fileName));
			InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
			Document document=builder.build(isr);
			Element root=document.getRootElement(); 
			//去除我们生成的类图文件的最外层的节点，因为这不是核心内容,而且根元素和其子元素和孙子元素只出现了了一次
		    List<Element> rootObject=root.getChildren(); 
		    List<Element> children =rootObject.get(0).getChildren();
		    //Element model =children.get(0);
		    List<Element> model=children.get(0).getChildren(); 
		    Element m =model.get(0);
		   // System.out.println(m.getAttributeValue("Id"));
		    //要处理的元素
		     eClasses =m.getChild("Classes", Namespace.getNamespace("c", "collection"));
		     eAssociations =m.getChild("Associations",Namespace.getNamespace("c", "collection"));
		     eGeneralizations =m.getChild("Generalizations",Namespace.getNamespace("c", "collection"));
		     eDependencies =m.getChild("Dependencies",Namespace.getNamespace("c", "collection"));
		   //  eAssociationClassLinks =m.getChild("AssociationClassLinks",Namespace.getNamespace("c", "collection"));
		    	//List<Element> eAssociationClassLinks =el.getChildren("c：AssociationClassLinks");
		   
		   /* if(eClasses != null) {
		    	System.out.println("lj");
		    }
		    */
		    //List<Element> aaa=eClasses.get(1).getChildren();
		    //System.out.println(aaa.get(0).getAttributeValue("Id"));
		    
		   
		    
		}catch(JDOMException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		//System.out.println("try prase");
	}
	
	

	public void show(JTextArea jta) {
		//直接显示的部分Classes  ，Associate classes ，Relationships
	//we文本域 
		List<Element> li;
		String stri="";//xian要显示的文本
		
		switch (jta.getName()) {
		case "Classes":
			li=eClasses.getChildren();
			for(int i=0;i<li.size();i++) {
				stri=stri+li.get(i).getChildText("Name", Namespace.getNamespace("a", "attribute"))+"\r\n";
				
				}
			jta.setText(stri);
			
			break;
		case "Associate classes":
			li=eAssociations.getChildren();
			for(int i=0;i<li.size();i++) {
				if(li.get(i).getChildText("RoleAIndicator", Namespace.getNamespace("a", "attribute"))!=null) {
					
					continue;
					}
				else{
					//System.out.println(li.get(i).getChildText("RoleAIndicator", Namespace.getNamespace("a", "attribute")));
					stri=stri+li.get(i).getChildText("Name", Namespace.getNamespace("a", "attribute"))+"\r\n";
					}
				}
			jta.setText(stri);
			
			/*
			 * li=eClasses.getChildren();
		   	
			*/
			break;
		case"Relationships":
			stri="aggregation"+"\r\n"+"generalization"+"\r\n"+"dependency";
			jta.setText(stri);
			
			break;
			
		default :break;
		
		}
	    	
		/*li=eGeneralizations.getChildren();
		for(int i=0;i<li.size();i++) {
			eGeneralization[i]=new OGeneralization(li.get(i));
			}
		li=eDependencies.getChildren();
		for(int i=0;i<li.size();i++) {
	    	eDependency[i]=new ODependency(li.get(i));
	    	}
		   
		li=eAssociationClassLinks.getChildren();
	  	for(int i=0;i<li.size();i++) {
	  		eAssociationClassLink[i]=new OAssociationClassLink(li.get(i));
    		}
	    	
	   	*/
	}
	
	public void show(JTextArea jta,String sstr) {
		//点解其他文本域获取内容后显示的部分Attributes,  Roles,  Relationship Classes,  Constraints
		//把解析结果存入相应类
		//canshu:写入的文本域 ，从其他文本域获取的字符(即包含显示的类的类)
		
		
		
		List<Element> li;//临时使用存储各种元素
		String stri="";//要显示的文本
		
		switch (jta.getName()) {
		case "Attributes"://来源是类或者关联类
			
			
			//存储类
			li=eClasses.getChildren();
			
			for(int i=0;i<li.size();i++) {
				
				List<Attribt> eAttr=new ArrayList<Attribt>();
				Element attr=li.get(i).getChild("Attributes", Namespace.getNamespace("c", "collection"));
				Clas temp = new Clas();
				Attribt tep=new Attribt();
				List<Element> attrList=new ArrayList<Element>();
				
				if(attr!=null) {
					attrList=attr.getChildren();
					for(int n=0;n<attrList.size();n++) {
						
						tep.setId(attrList.get(n).getAttributeValue("Id"));
						tep.setName(attrList.get(n).getChildText("Name", Namespace.getNamespace("a", "attribute")));
						tep.setDataType(attrList.get(n).getChildText("DataType", Namespace.getNamespace("a", "attribute")));
						eAttr.add(tep);
					}
				}
				else {
					attrList.clear();
					
				}
				
				temp.setId(li.get(i).getAttributeValue("Id"));
				temp.setName(li.get(i).getChildText("Name", Namespace.getNamespace("a", "attribute")));
				temp.setAttr(eAttr);
				eClass.add(temp);
				
				if(li.get(i).getChildText("Name", Namespace.getNamespace("a", "attribute")).equals(sstr)) {
					//如果是选择的类，就获取其属性列表显示
					
					for(int j=0;j<attrList.size();j++) {
						stri=stri+attrList.get(j).getChildText("Name", Namespace.getNamespace("a", "attribute"))
								+":"+attrList.get(j).getChildText("DataType", Namespace.getNamespace("a", "attribute"))+"\r\n";
						}
					
					}
				}
			
			changeAttrName(eClass);
			
			jta.setText(stri);
			
			break;
			
		case "Roles"://来源是关联类w文本域
			
			//存储关联类
			li=eAssociations.getChildren();
			Association tempA=new Association();
			for(int i=0;i<li.size();i++) {
				
				Element obj1=li.get(i).getChild("Object1", Namespace.getNamespace("c", "collection"));
				Element obj2=li.get(i).getChild("Object2", Namespace.getNamespace("c", "collection"));
				Element objc1=obj1.getChild("Class", Namespace.getNamespace("o", "object"));
				Element objc2=obj2.getChild("Class", Namespace.getNamespace("o", "object"));
				if(li.get(i).getChildText("RoleAIndicator", Namespace.getNamespace("a", "attribute"))!=null) {
					continue;
				}
				else {
					tempA.setId(li.get(i).getAttributeValue("Id"));
					tempA.setName(li.get(i).getChildText("Name", Namespace.getNamespace("a", "attribute")));
					
					if(li.get(i).getChildText("RoleAName", Namespace.getNamespace("a", "attribute"))!=null) {
						tempA.setRoleAname(li.get(i).getChildText("RoleAName", Namespace.getNamespace("a", "attribute")));
						//eAssociation.get(i).setRoleBname(li.get(i).getChildText("RoleBName", Namespace.getNamespace("a", "attribute")));
					}
					else {
						tempA.setRoleAname(null);
					}
					if(li.get(i).getChildText("RoleBName", Namespace.getNamespace("a", "attribute"))!=null) {
						//eAssociation.get(i).setRoleAname(li.get(i).getChildText("RoleAName", Namespace.getNamespace("a", "attribute")));
						tempA.setRoleBname(li.get(i).getChildText("RoleBName", Namespace.getNamespace("a", "attribute")));
					}
					else {
						tempA.setRoleBname(null);
					}
					tempA.setRoleAid(objc2.getAttributeValue("Ref"));
					tempA.setRoleBid(objc1.getAttributeValue("Ref"));
					tempA.setRoleA(findClass(tempA.getRoleAid()));
					tempA.setRoleB(findClass(tempA.getRoleBid()));
					
					eAssociation.add(tempA);
				}
				//System.out.println(eAssociation.size());
				//System.out.println("rolezifu:"+sstr);
				if(li.get(i).getChildText("Name", Namespace.getNamespace("a", "attribute")).equals(sstr)) {
					//如果是选择的关联类，就获取其Roles显示
					
					stri=stri+li.get(i).getChildText("RoleAName", Namespace.getNamespace("a", "attribute"))+":"+findClass(objc2.getAttributeValue("Ref"))+"\r\n";
					stri=stri+li.get(i).getChildText("RoleBName", Namespace.getNamespace("a", "attribute"))+":"+findClass(objc1.getAttributeValue("Ref"))+"\r\n";
					
					}
				}
			jta.setText(stri);
			
			break;
			
		case "Relationship Classes"://来源是关系wenbenyu
			
			//存储部分
			//juhe聚合类
			li=eAssociations.getChildren();
			//System.out .println(li.size());
			Aggregation tempAg=new Aggregation();
			for(int n=0;n<li.size();n++) {
				if(li.get(n).getChildText("RoleAIndicator", Namespace.getNamespace("a", "attribute"))!=null) {
					//ju聚合
					Element obj1=li.get(n).getChild("Object1", Namespace.getNamespace("c", "collection"));
					Element obj2=li.get(n).getChild("Object2", Namespace.getNamespace("c", "collection"));
					Element objc1=obj1.getChild("Class", Namespace.getNamespace("o", "object"));
					Element objc2=obj2.getChild("Class", Namespace.getNamespace("o", "object"));
					
					tempAg.setId(li.get(n).getAttributeValue("Id"));
					tempAg.setName(li.get(n).getChildText("Name", Namespace.getNamespace("a", "attribute")));
					tempAg.setObj1AggreById(obj1.getAttributeValue(objc1.getAttributeValue("Ref")));
					tempAg.setObj2AggreId(obj2.getAttributeValue(objc2.getAttributeValue("Ref")));
					
					//stri=stri+li.get(i).getChildText("Name", Namespace.getNamespace("a", "attribute"))+":"+findClass(objc1.getAttributeValue("Ref"))+"——<>"+findClass(objc2.getAttributeValue("Ref"))+"\r\n";
					eAggregation.add(tempAg);
					}
				}
		//	li.clear();
		
			//泛化类
			li=eGeneralizations.getChildren();
			//System.out .println(li.size());
			Generalization tempGe=new Generalization();
			for(int n=0;n<li.size();n++) {
				
				
					Element obj1=li.get(n).getChild("Object1", Namespace.getNamespace("c", "collection"));
					Element obj2=li.get(n).getChild("Object2", Namespace.getNamespace("c", "collection"));
					Element objc1=obj1.getChild("Class", Namespace.getNamespace("o", "object"));
					Element objc2=obj2.getChild("Class", Namespace.getNamespace("o", "object"));
					
					tempGe.setId(li.get(n).getAttributeValue("Id"));
					tempGe.setName(li.get(n).getChildText("Name", Namespace.getNamespace("a", "attribute")));
					tempGe.setFid(objc1.getAttributeValue("Ref"));
					tempGe.setCid(objc2.getAttributeValue("Ref"));
					tempGe.setCname(findClass(objc2.getAttributeValue("Ref")));
					tempGe.setFname(findClass(objc1.getAttributeValue("Ref")));
					//stri=stri+li.get(i).getChildText("Name", Namespace.getNamespace("a", "attribute"))+":"+findClass(objc1.getAttributeValue("Ref"))+"<|——"+findClass(objc2.getAttributeValue("Ref"))+"\r\n";
					eGeneralization.add(tempGe);
				
				}
		//	li.clear();
			
			//yi依赖类
			li=eDependencies.getChildren();
			//System.out .println(li.size());
			Dependency tempDe=new Dependency();
			for(int n=0;n<li.size();n++) {
				
				
					Element obj1=li.get(n).getChild("Object1", Namespace.getNamespace("c", "collection"));
					Element obj2=li.get(n).getChild("Object2", Namespace.getNamespace("c", "collection"));
					Element objc1=obj1.getChild("Class", Namespace.getNamespace("o", "object"));
					Element objc2=obj2.getChild("Class", Namespace.getNamespace("o", "object"));
					
					tempDe.setId(li.get(n).getAttributeValue("Id"));
					tempDe.setName(findClass(objc2.getAttributeValue("Ref"))+"_dep_"+findClass(objc1.getAttributeValue("Ref")));
					tempDe.setDepbyid(objc1.getAttributeValue("Ref"));
					tempDe.setDepid(objc2.getAttributeValue("Ref"));
					//stri=stri+li.get(i).getChildText("Name", Namespace.getNamespace("a", "attribute"))+":"+findClass(objc1.getAttributeValue("Ref"))+"<----"+findClass(objc2.getAttributeValue("Ref"))+"\r\n";
					
					eDependency.add(tempDe);
				}
		//	li.clear();
			
			
			
			//显示部分
			
			String agg="aggregation";
			String gen="generalization";
			String dep="dependency";
			if(agg.equals(sstr)) {
				//juhe聚合
				
				li=eAssociations.getChildren();
			//	System.out .println(li.size());
				for(int i=0;i<li.size();i++) {
					
					//System.out.println("juhezifu:"+sstr);
					if(li.get(i).getChildText("RoleAIndicator", Namespace.getNamespace("a", "attribute"))!=null) {
						//ju聚合
						Element obj1=li.get(i).getChild("Object1", Namespace.getNamespace("c", "collection"));
						Element obj2=li.get(i).getChild("Object2", Namespace.getNamespace("c", "collection"));
						Element objc1=obj1.getChild("Class", Namespace.getNamespace("o", "object"));
						Element objc2=obj2.getChild("Class", Namespace.getNamespace("o", "object"));
						
						stri=stri+li.get(i).getChildText("Name", Namespace.getNamespace("a", "attribute"))+":"+findClass(objc1.getAttributeValue("Ref"))+"——<>"+findClass(objc2.getAttributeValue("Ref"))+"\r\n";
						
						}
					}
				
			}
			else if(gen.equals(sstr)) {
				//fanhu泛化
				li=eGeneralizations.getChildren();
				for(int i=0;i<li.size();i++) {
					
					
						Element obj1=li.get(i).getChild("Object1", Namespace.getNamespace("c", "collection"));
						Element obj2=li.get(i).getChild("Object2", Namespace.getNamespace("c", "collection"));
						Element objc1=obj1.getChild("Class", Namespace.getNamespace("o", "object"));
						Element objc2=obj2.getChild("Class", Namespace.getNamespace("o", "object"));
						
						stri=stri+li.get(i).getChildText("Name", Namespace.getNamespace("a", "attribute"))+":"+findClass(objc1.getAttributeValue("Ref"))+"<|——"+findClass(objc2.getAttributeValue("Ref"))+"\r\n";
						
					
					}
				
			}
			else if(dep.equals(sstr)) {
				//依赖
				li=eDependencies.getChildren();
				for(int i=0;i<li.size();i++) {
					
					
						Element obj1=li.get(i).getChild("Object1", Namespace.getNamespace("c", "collection"));
						Element obj2=li.get(i).getChild("Object2", Namespace.getNamespace("c", "collection"));
						Element objc1=obj1.getChild("Class", Namespace.getNamespace("o", "object"));
						Element objc2=obj2.getChild("Class", Namespace.getNamespace("o", "object"));
						
						stri=stri+li.get(i).getChildText("Name", Namespace.getNamespace("a", "attribute"))+":"+findClass(objc1.getAttributeValue("Ref"))+"<----"+findClass(objc2.getAttributeValue("Ref"))+"\r\n";
						
					
					}
				
			}
			jta.setText(stri);
			
			break;
		case "Constraints"://来源是关联类文本域
			li=eAssociations.getChildren();
			for(int i=0;i<li.size();i++) {
				
				if(li.get(i).getChildText("Name", Namespace.getNamespace("a", "attribute")).equals(sstr)) {
					//如果是选择的关联类，就获取其Roles
					//Element obj1=li.get(i).getChild("Object1", Namespace.getNamespace("c", "collection"));
					//Element obj2=li.get(i).getChild("Object2", Namespace.getNamespace("c", "collection"));
					//Element objc1=obj1.getChild("Class", Namespace.getNamespace("o", "object"));
					//Element objc2=obj2.getChild("Class", Namespace.getNamespace("o", "object"));
					
					stri=stri+li.get(i).getChildText("RoleAName", Namespace.getNamespace("a", "attribute"))+"("+li.get(i).getChildText("RoleAMultiplicity", Namespace.getNamespace("a", "attribute"))+")---";
					stri=stri+li.get(i).getChildText("RoleBName", Namespace.getNamespace("a", "attribute"))+"("+li.get(i).getChildText("RoleBMultiplicity", Namespace.getNamespace("a", "attribute"))+")\r\n";
					
					}
				}
			jta.setText(stri);
			
			break;
			
			
		default :break;
			
		}
	}
	
	public String findClass(String id) {
		//根据id找类
		String name="未找到";
		List<Element> cid=eClasses.getChildren();
		for(Element e:cid) {
			if(e.getAttributeValue("Id").equals(id)) {
				name= (String)e.getChildText("Name", Namespace.getNamespace("a", "attribute"));
				break;
		}
			
		}
		return name;
	}
	
	public void  changeAttrName(List<Clas> eClass){
		//查找类的属性，如果有名字相同的就更改名字 ：类名_属性名
		//System.out .println("变："+eClass.size());
		List<List<Attribt>> tempAttr=new ArrayList<List<Attribt>>() ;
		List<Clas> tempClass=new ArrayList<Clas>();
		String tempattr;
		for(int i=0;i<eClass.size();i++) {
			
			tempClass.add(i, eClass.get(i));
			if(eClass.get(i).getAttr()!=null) {
				tempAttr.add(eClass.get(i).getAttr());
			}
			else {
				tempAttr.add(null);
			}
		}
		for(int i=0;i<tempClass.size();i++) {
			for(int j=0;j<tempAttr.get(i).size();j++) {
				if(tempAttr.get(i).get(j)!=null) {
					tempattr=tempAttr.get(i).get(j).getName();
				}
				else {
					continue;
				}
				for(int m=i+1;m<tempClass.size();m++) {
					for(int n=0;n<tempAttr.get(m).size();n++) {
						if(tempAttr.get(m).get(n)==null) {
							continue;
						}
						else if(tempattr.equals(tempAttr.get(m).get(n).getName())) {
							tempAttr.get(i).get(j).setName(tempClass.get(i).getName()+"_"+tempattr);
							tempAttr.get(m).get(n).setName(tempClass.get(m).getName()+"_"+tempattr);
						}
					}
				}
			}
		}
		for(int i=0;i<eClass.size();i++) {
			eClass.get(i).setAttr(tempAttr.get(i));
			//tempAttr.set(i, eClass.get(i).getAttr());
		}
		
	}

	public void copy(JdomPrase jp) {
		this.eClass=jp.eClass;
		this.eAggregation=jp.eAggregation;
		this.eAssociation=jp.eAssociation;
		this.eDependency=jp.eDependency;
		this.eGeneralization=jp.eGeneralization;
		
	}
}
	
