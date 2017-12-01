# Guava

在pom文件中引入Guava 
 
    <dependency>  
        <groupId>com.google.guava</groupId>  
        <artifactId>guava</artifactId>  
        <version>21.0</version>  
    </dependency>  

## Optional  
在Java中null既不是对象也不是实例，只是一个关键字。Optional就是对null的处理。  
### <T> T Optional.of(T reference)  
    //Optional.of ---> 会检测null 如果是null抛出异常。在of方法中调用了Preconditions.checkNotNull方法进行空值判断 
    Optional<Integer> num1 = Optional.of(value1);  
### <T> Optional<T> Optional.fromNullable(T nullableReference)     
    //Optional.fromNullable --> 允许输入为空。但是在使用的时候要进行判断  
    Optional<Integer> num2 = Optional.fromNullable(value1);    
    //如果num2不为空。pres == true 否则 pres == false  
    boolean pres = num2.isPresent();   
### T orNull() 
    Optional<Integer> num3 = Optional.absent();   
    //Optional.orNull --> 如果是null，就把null作为值，否则返回num3的值    
    System.out.println(num3.orNull());   
### T or(Optional<? extends T> newValue)
    //Optional.or --> 如果存在，返回值，如果不存在，返回传入的值  
    //想用一个特定值代替null的时候，可以使用这个方法  
    Integer num = Optional.fromNullable(value1).or(0);  
### T get()  
    //通过get方法获取Optional对象的值
    num1.get();
    
## Preconditions
前置条件检查，可以对表达式、布尔值进行检查，检查中可以输出自定义异常信息，输出被检查的值  
在Preconditions这个类中，checkArgument/checkState比较相似，checkElementIndex /checkPositionIndex比较相似  
### void Preconditions.checkArgument(boolean expression)/checkState
### void Preconditions.checkArgument(boolean expression，Object errorMessage)/checkState
### void Preconditions.checkArgument(boolean expression, String errorMessageTemplate, Object.. errorMessageObjects)/checkState
### void Preconditions.checkArgument(boolean expression, String errorMessageTemplate, Object ob1, Object ob2, Object ob3, Object ob4)/checkState

    //当name == null的时候，抛出异常，异常信息为空 
    Preconditions.checkArgument(name != null);  
    //当name == test的时候，抛出异常，异常信息为"this is a test" 
    Preconditions.checkArgument(name != "test", "this is a test");  
    //当age <= 0 || name == null 时，抛出异常，并在异常信息中输出age， name对应的值  
    Preconditions.checkArgument(age > 0 && name != null, "%s's age is %s is valid", name, age);  
    //当age <= 0 || name == null 时，抛出异常，并在异常信息中输出age， name对应的值。其他的，不能够通过errorMessageTemplate格式化的参数，以数组形式，统一输出 
    Preconditions.checkArgument(age > 0 && name != null, "%s's age is %s is valid", name, age, 1,2,3,4,5,4,3,4,32);  

### int Preconditions.checkElementIndex(int index, int size)/checkPositionIndex  
### int Preconditions.checkElementIndex(int index, int size, String desc)/checkPositionIndexes    
### void Preconditions.checkPositionIndexes(int start, int end, int size) 

    // 前两种方法时checkPositionIndexes、 checkElementIndex都有的方法，参数也是相同的
    // index 要操作的索引，size 数组的大小 index>0 && index < size 返回index，否则抛出异常
    int res = Preconditions.checkElementIndex(index, size);
    // index 要操作的索引，size 数组的大小 index>0 && index < size 返回index，否则抛出异常,desc为自定义异常信息
    int res =  Preconditions.checkElementIndex(index, size, desc);
     
    checkPositionIndex 只比 checkElementIndex 多了一个方法  
    //start 开始index end 结束index size 集合大小  
    // 如果start < 0  || end < start || end > size 抛出异常  
    void Preconditions.checkPositionIndexes(int start, int end, int size)  
    
### <T> T Preconditions.checkNotNull(T reference)  
### <T> T Preconditions.checkNotNull(T reference, @Nullable Object errorMessage)  
### <T> T Preconditions.checkNotNull(T reference, String errorMessageTemplate, Object... objects)  
### <T> T Preconditions.checkNotNull(T reference, String errorMessageTemplate, Object ob1, Object ob2, Object ob3, Object ob4)  

    //当name == null的时候，抛出异常，异常信息为空 
    Preconditions.checkArgument(name);  
    //当name == null的时候，抛出异常，异常信息为"this is a test" 
    Preconditions.checkArgument(name, "this is a test");  
    //name == null 时，抛出异常，并在异常信息中输出age， name对应的值  
    Preconditions.checkArgument(name, "name is null, age is %s", age);  
    //name == null 时，抛出异常，并在异常信息中输出age对应的值。其他的，不能够通过errorMessageTemplate格式化的参数，以数组形式，统一输出 
    Preconditions.checkArgument(name, "name is null, age is %s", age, 1,2,3,4,5,4,3,4,32); 