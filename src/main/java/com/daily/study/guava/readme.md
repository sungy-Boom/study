# Guava

在pom文件中引入Guava 
 
    <dependency>  
        <groupId>com.google.guava</groupId>  
        <artifactId>guava</artifactId>  
        <version>21.0</version>  
    </dependency>  

## Optional  
在Java中null既不是对象也不是实例，只是一个关键字。Optional就是对null的处理。  
### Optional.of  
    //Optional.of ---> 会检测null 如果是null抛出异常。在of方法中调用了Preconditions.checkNotNull方法进行空值判断 
    Optional<Integer> num1 = Optional.of(value1);  
### Optional.fromNullable     
    //Optional.fromNullable --> 允许输入为空。但是在使用的时候要进行判断  
    Optional<Integer> num2 = Optional.fromNullable(value1);    
    //如果num2不为空。pres == true 否则 pres == false  
    boolean pres = num2.isPresent();   
### Optional.orNull 
    Optional<Integer> num3 = Optional.absent();   
    //Optional.orNull --> 如果是null，就把null作为值，否则返回num3的值    
    System.out.println(num3.orNull());   
### Optional.or
    //Optional.or --> 如果存在，返回值，如果不存在，返回传入的值  
    //想用一个特定值代替null的时候，可以使用这个方法  
    Integer num = Optional.fromNullable(value1).or(0);  
### get
    //通过get方法获取Optional对象的值
    num1.get();
    