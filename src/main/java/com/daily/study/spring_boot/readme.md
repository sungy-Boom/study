## 项目中使用springboot的jpa
首先要建一个springboot的项目，在http://start.spring.io/网站上边可以方便的建一个新的springboot工程。  
![springboot initializr](/springboot.png)  
在项目中进行配置（配置application.properties文件）  
 
    spring.datasource.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8
    spring.datasource.username=root
    spring.datasource.password=root
    
    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    spring.datasource.initial-size=3
    spring.datasource.min-idle=5
    spring.datasource.max-active=50
    spring.datasource.maximum-pool-size=120
    spring.datasource.max-wait=60000
    
    #update 表示当实体类的属性发生变化时，表结构跟着更新
    #create 表示启动的时候删除上一次生成的表，并根据实体类重新生成表，这个时候之前表中的数据就会被清空
    #create-drop 这个表示启动时根据实体类生成表，但是当sessionFactory关闭的时候表会被删除
    #validate 表示启动时验证实体类和数据表是否一致
    #none 表示啥都不做
    spring.jpa.hibernate.ddl-auto=update
    
    #表示hibernate在操作的时候在控制台打印真实的sql语句
    spring.jpa.show-sql=true
    
    #表示格式化输出的json字符串
    spring.jackson.serialization.indent_output=true

