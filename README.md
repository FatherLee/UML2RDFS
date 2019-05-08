＃UML2RDFS
输入：power designer生成的uml类图文件
输出：一个TXT文件，保存RDFS格式的类图信息
功能上的不足：	基数约束不能构造语句；
		    泛化关系的不相交属性没有相关构造；
		    类的属性的基本类型与rdf属性类型之间没有映射表，而是直接用类的属性类型构造的。
   
开发IDE为eclipse oxygen 3,
	开发软件库版本 jdk1.8.0
  
  UML2RDFS文件夹是工程文件，其中包含了一个powerdesigner
	生成的测试用例，名字为UML Class Diagram_university.oom
