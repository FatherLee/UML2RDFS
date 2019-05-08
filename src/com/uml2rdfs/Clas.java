package com.uml2rdfs;


import java.util.List;

/*
 * �������ݴ�����
 */
public class Clas extends Attrib{

	//��ͼ��������
	
	private List<Attribt> attr;

	public List<Attribt> getAttr() {
		return attr;
	}

	public void setAttr(List<Attribt> attr) {
		this.attr = attr;
	}
	

	
}

class Attrib{
	// ����Ϊ����
	private String id;
	private String name ;
	//private String dataType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
class Attribt extends Attrib{
	//shuxi����
	private String dataType;

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}

class Association extends Attrib{
	//����
	private String roleAname;
	private String roleBname;
	private String roleAid;
	private String roleBid;
	private String roleA;
	private String roleB;
	
	public String getRoleAname() {
		return roleAname;
	}
	public void setRoleAname(String roleAname) {
		this.roleAname = roleAname;
	}
	public String getRoleBname() {
		return roleBname;
	}
	public void setRoleBname(String roleBname) {
		this.roleBname = roleBname;
	}
	public String getRoleAid() {
		return roleAid;
	}
	public void setRoleAid(String roleAid) {
		this.roleAid = roleAid;
	}
	public String getRoleBid() {
		return roleBid;
	}
	public void setRoleBid(String roleBid) {
		this.roleBid = roleBid;
	}
	public String getRoleA() {
		return roleA;
	}
	public void setRoleA(String roleA) {
		this.roleA = roleA;
	}
	public String getRoleB() {
		return roleB;
	}
	public void setRoleB(String roleB) {
		this.roleB = roleB;
	}
}

class Aggregation extends Attrib{
	//ju�ۺ�
	private String obj1AggreById;//�ۺϵķ���id
	private String obj2AggreId;//�ۺϵ�����id
	public String getObj1AggreById() {
		return obj1AggreById;
	}
	public void setObj1AggreById(String obj1AggreById) {
		this.obj1AggreById = obj1AggreById;
	}
	public String getObj2AggreId() {
		return obj2AggreId;
	}
	public void setObj2AggreId(String obj2AggreId) {
		this.obj2AggreId = obj2AggreId;
	}
	
	
}
class Generalization extends Attrib{
	//fanhua����
	private String fid;//����id
	private String cid;//����id
	private String fname;//fulei������
	private String cname;//������
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}

class Dependency extends Attrib{
	
	//���������ָ�ʽ�� �������������_dep_���������������
	private String depbyid;//������id
	private String depid;//����id
	public String getDepbyid() {
		return depbyid;
	}
	public void setDepbyid(String depbyid) {
		this.depbyid = depbyid;
	}
	public String getDepid() {
		return depid;
	}
	public void setDepid(String depid) {
		this.depid = depid;
	}
	
}
