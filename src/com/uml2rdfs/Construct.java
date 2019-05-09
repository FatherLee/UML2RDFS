package com.uml2rdfs;

import java.util.ArrayList;
import java.util.List;

/*
 * ����ģ��
 */
public class Construct {
	
	private String str="";//gou������������ַ���
	
	public  void constrClass(List<Clas> clas,List<Generalization> gen){
		//g�������rdf���
		
		boolean isgen=false;//shi�Ƿ���ĳһ������ϵ������
		for(int i=0;i<clas.size();i++) {
			//System.out.println(i+":"+clas.size());
			System.out.println(clas.get(i).getId()+":"+clas.get(i).getName());
			for(int n=0;n<gen.size();n++) {
				if(clas.get(i).getId().equals(gen.get(n).getCid())) {
					//ru��������Ƿ��������࣬���Ȳ�������������䣬�ڷ����й���
					isgen=true;
					break;
				}
				else
					isgen=false;
			}
			if(!isgen) {
				str=str+"<rdfs:Class rdf:ID=��"+clas.get(i).getName()+"��/>\r\n";
				if(clas.get(i).getAttr()!=null) {
					for(int j=0;j<clas.get(i).getAttr().size();j++) {
						//System.out.println(i+"-"+j);
						str=str+"<rdf:Property rdf:ID=��"+clas.get(i).getAttr().get(j).getName()+"��>\r\n";
						str=str+"\t";
						str=str+"<rdfs:domain rdf:resource=��#"+clas.get(i).getName()+"��/>\r\n";
						str=str+"\t";
						str=str+"<rdfs:range rdf:resource=��&xsd:"+clas.get(i).getAttr().get(j).getDataType()+"��/>\r\n";
						str=str+"</rdf:Property>"+"\r\n";
					}
				}
			}
				
		}
		
	}
	
	
	public  void ConstrAsso(List<Association> asso){
			//���������rdf���
		for(int i=0;i<asso.size();i++) {
			str=str+"<rdfs:Class rdf:ID=��"+asso.get(i).getName()+"��/>\r\n";
			if(asso.get(i).getRoleAid()!=null) {
				str=str+"<rdf:Property rdf:ID=��"+asso.get(i).getRoleAname()+"��>\r\n";
				str=str+"\t";
				str=str+"<rdfs:domain rdf:resource=��#"+asso.get(i).getName()+"��/>\r\n";
				str=str+"\t";
				str=str+"<rdfs:range rdf:resource=��#"+asso.get(i).getRoleA()+"��/>\r\n";
				str=str+"</rdf:Property>"+"\r\n";
			}
			if(asso.get(i).getRoleBid()!=null) {
				str=str+"<rdf:Property rdf:ID=��"+asso.get(i).getRoleBname()+"��>\r\n";
				str=str+"\t";
				str=str+"<rdfs:domain rdf:resource=��#"+asso.get(i).getName()+"��/>\r\n";
				str=str+"\t";
				str=str+"<rdfs:range rdf:resource=��#"+asso.get(i).getRoleB()+"��/>\r\n";
				str=str+"</rdf:Property>"+"\r\n";
			}
		}
	}
	
	
	public  void ConstrAggre(List<Aggregation> aggre){
		//����ۺϵ�rdf���
		for(int i=0;i<aggre.size();i++) {
			
			str=str+"<rdf:Property rdf:ID=��"+aggre.get(i).getName()+"��>\r\n";
			str=str+"\t";
			str=str+"<rdfs:domain rdf:resource=��#"+aggre.get(i).getObj2AggreName()+"��/>\r\n";
			str=str+"\t";
			str=str+"<rdfs:range rdf:resource=��#"+aggre.get(i).getObj1AggreByName()+"��/>\r\n";
			str=str+"</rdf:Property>"+"\r\n";
		}
		
	}
	
	
	public  void ConstrGen(List<Generalization> gen,List<Clas> clas){
		//fan��������rdf���
		for(int i=0;i<gen.size();i++) {
			str=str+"<rdfs:Class rdf:ID=��"+gen.get(i).getCname()+"��/>\r\n";
			str=str+"\t";
			str=str+"<rdfs:subClassOf rdf:resource=��#"+gen.get(i).getFname()+"��/>\r\n";
			str=str+"</rdfs:Class>\r\n";
			for(int j=0;j<clas.size();j++) {
				if(gen.get(i).getCid().equals(clas.get(j).getId())) {
					//�ҵ����ڹ���ķ�����ϵ�����࣬�������������
					if(clas.get(j).getAttr()!=null) {
						for(int n=0;n<clas.get(j).getAttr().size();n++) {
							str=str+"<rdf:Property rdf:ID=��"+clas.get(j).getAttr().get(n).getName()+"��>\r\n";
							str=str+"\t";
							str=str+"<rdfs:domain rdf:resource=��#"+clas.get(j).getName()+"��/>\r\n";
							str=str+"\t";
							str=str+"<rdfs:range rdf:resource=��&xsd:"+clas.get(j).getAttr().get(n).getDataType()+"��/>\r\n";
							str=str+"</rdf:Property>"+"\r\n";
						}
					}
				}
			}
		}
		
	}
	
	public  void ConstrDep(List<Dependency> dep,List<Clas> clas){
		//����������rdf���
		for(int i=0;i<dep.size();i++) {
			str=str+"<rdf:Property rdf:ID=��"+dep.get(i).getName()+"��>\r\n";
			for(int j=0;j<clas.size();j++) {	
				if(dep.get(i).getDepid().equals(clas.get(j).getId())){
				
					str=str+"\t";
					str=str+"<rdfs:domain rdf:resource=��#"+clas.get(j).getName()+"��/>\r\n";
				}
				if(dep.get(i).getDepbyid().equals(clas.get(j).getId())) {
					str=str+"\t";
					str=str+"<rdfs:range rdf:resource=��#"+clas.get(j).getName()+"��/>\r\n";
					
				}
			}
			str=str+"</rdf:Property>"+"\r\n";
		}
	
	
	}

	public String getStr() {
		return str;
	}

	
	


}
