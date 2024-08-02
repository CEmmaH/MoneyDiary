mybatis-3.5.jar 和 mysqul-connector-j-9.jar 两个jar包放在web-inf下
用户登录
1. 数据库创建，及table： MoneyDiary, user(mysql)
2. 前台页面： login.jsp
3. 后台实现：
	登录功能： 接收页面传过来的参数；
			 非空校验，即使前台做过一次非空判断，后台安全起见最好再做一次，因为有可能前台和后端是不同group编写；
			 	如果参数为空，通过消息模型对象返回数据结果（设置成功状态-是否成功，提示消息，
			 	回显数据 - 将前台输入的信息返回页面），将消息对象设置到request作用域，请求转发跳转回登录页面；
			 通过用户姓名查询用户对象，判断是否为空，如非空，比较密码；
			 登录成功，将用户信息设置到session作用域中；重定向跳转到首页；
			 
后端思路
	controller层
		1.接收客户端请求（调用service层，返回结果）
		2.调用service层的方法，返回消息模型对象；
		3.判断消息模型状态码
			如果为失败
				将消息模型对象设置到request作用域，请求转发跳转会登录页面login.jsp
			如果成功
				将消息模型对象中的用户信息设置到session作用域，重定向跳转到首页index.jsp
		4.请求转发跳转到登录页面
	service层（业务逻辑判断）
		1. 参数的非空判断
			如果参数为空： 将状态码，提示信息，回显数据设置到消息模型对象中，返回消息模型对象；
		2. 调用Mapper层的查询方法，通过用户名查询用户对象
		3. 判断用户对象是否为空
			如果参数为空： 将状态码，提示信息，回显数据设置到消息模型对象中，返回消息模型对象；
		4. 判断密码是否一致
			如果不等，将状态码，提示信息，回显数据设置到消息模型对象中，返回消息模型对象；
		5. 将成功状态，提示信息，用户对象设置消息模型对象，并return
			
	mapper 层（也称为Dao层）
		定义对应接口类
		mapper.xml mybatis域数据库相关操作
	entity-po, model 一般存放与数据库一一对应的javaBean
		JavaBean实体
	util
		工具类-通用方法/类
	test
		测试类/方法
	
JDBC顺序
1. entity中建立对于表的类，并把每个column转为field，创建setter 和 getter method；
1. 编写 *mapper.xml, *mapper.java，里面信息需要对应，一般放在 mapper package下面
2. mysql.properties文件:主要数据库名称，及登录数据库的用户名密码要修改正确
	url=jdbc:mysql://127.0.0.1:3306/DB Name?serverTimezone=UTC&useSSl=false&characterEncoding=UTF-8
3. mybatis-config.xml： 
4. util package下创建GetSqlSession.java