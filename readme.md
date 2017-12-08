# This is ideal not dream  

__A project of daily study,in which takes notes that I learned.Such as algorithm,new technique,some view of myself and so on.__ 

All I can do is try my best,and never give up!

### idea multiple module test
parent.pom 进行模块之间的管理。父pom里边集成子module的moduleName。然后各个子模块只需要引入自己需要的包。如果多个模块有公用的依赖，可以从父模块引入，然后子模块继承父模块。

    <modules>
        <module>demo</module>
        <module>learn</module>
    </modules>
     
    //子module继承父module
    <parent>
        <artifactId>study</artifactId>
        <groupId>com.study</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    
