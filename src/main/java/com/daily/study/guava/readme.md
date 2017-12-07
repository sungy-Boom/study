[TOC]
<h1 align="center">Guava</h1>

## 在pom文件中引入Guava
 
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

### checkArgument/checkState方法
__void Preconditions.checkArgument(boolean expression)/checkState方法
void Preconditions.checkArgument(boolean expression，Object errorMessage)/checkState
void Preconditions.checkArgument(boolean expression, String errorMessageTemplate, Object.. errorMessageObjects)/checkState
void Preconditions.checkArgument(boolean expression, String errorMessageTemplate, Object ob1, Object ob2, Object ob3, Object ob4)/checkState__

    //当name == null的时候，抛出异常，异常信息为空 
    Preconditions.checkArgument(name != null);  
    //当name == test的时候，抛出异常，异常信息为"this is a test" 
    Preconditions.checkArgument(name != "test", "this is a test");  
    //当age <= 0 || name == null 时，抛出异常，并在异常信息中输出age， name对应的值  
    Preconditions.checkArgument(age > 0 && name != null, "%s's age is %s is valid", name, age);  
    //当age <= 0 || name == null 时，抛出异常，并在异常信息中输出age， name对应的值。其他的，不能够通过errorMessageTemplate格式化的参数，以数组形式，统一输出 
    Preconditions.checkArgument(age > 0 && name != null, "%s's age is %s is valid", name, age, 1,2,3,4,5,4,3,4,32);  

### checkElementIndex/checkPositionIndex方法

__int Preconditions.checkElementIndex(int index, int size)/checkPositionIndex  
int Preconditions.checkElementIndex(int index, int size, String desc)/checkPositionIndexes    
void Preconditions.checkPositionIndexes(int start, int end, int size)__ 

    // 前两种方法时checkPositionIndexes、 checkElementIndex都有的方法，参数也是相同的
    // index 要操作的索引，size 数组的大小 index>0 && index < size 返回index，否则抛出异常
    int res = Preconditions.checkElementIndex(index, size);
    // index 要操作的索引，size 数组的大小 index>0 && index < size 返回index，否则抛出异常,desc为自定义异常信息
    int res =  Preconditions.checkElementIndex(index, size, desc);
     
    checkPositionIndex 只比 checkElementIndex 多了一个方法  
    //start 开始index end 结束index size 集合大小  
    // 如果start < 0  || end < start || end > size 抛出异常  
    void Preconditions.checkPositionIndexes(int start, int end, int size)  
    
### checkNotNull 方法

__<T> T Preconditions.checkNotNull(T reference)  
<T> T Preconditions.checkNotNull(T reference, @Nullable Object errorMessage)  
<T> T Preconditions.checkNotNull(T reference, String errorMessageTemplate, Object... objects)  
<T> T Preconditions.checkNotNull(T reference, String errorMessageTemplate, Object ob1, Object ob2, Object ob3, Object ob4)__  

    //当name == null的时候，抛出异常，异常信息为空 
    Preconditions.checkArgument(name);  
    //当name == null的时候，抛出异常，异常信息为"this is a test" 
    Preconditions.checkArgument(name, "this is a test");  
    //name == null 时，抛出异常，并在异常信息中输出age， name对应的值  
    Preconditions.checkArgument(name, "name is null, age is %s", age);  
    //name == null 时，抛出异常，并在异常信息中输出age对应的值。其他的，不能够通过errorMessageTemplate格式化的参数，以数组形式，统一输出 
    Preconditions.checkArgument(name, "name is null, age is %s", age, 1,2,3,4,5,4,3,4,32); 
    
## Ordering  
Ordering是Guava类库提供的一个强大的比较器工具，它非常容易扩展，可以轻松构造复杂的comparator，然后用在容器的比较、排序等操作中。Ordering使用链式表达式，配合java8的lambda~~很不错  

### 返回一个排序器

    // 生成一个对数据随意排列的排序器
    static Ordering<Object> arbitrary(); 
    // 生成一个数据按照自然序排列的排序器
    static <C extends Comparable> Ordering<C> natural()  
    // 所有数据地位相等，表明这个返回的是一个无排序的排序器
    static Ordering<Object> allEqual()  
    // 使用toString()返回的字符串按字典顺序进行排序
    static Ordering<Object> usingToString()  
    // 生成一个按照指定方式排序的排序器
    static <T> Ordering<T> from(Comparator<T> comparator)  

### 常用方法  

    // 返回一个将数据反转的排序器。
    <S extends T> Ordering<S> reverse()  
    // 对数据进行二分查找，如果数据存在，返回索引，如果不存在，返回 -(low + 1)
    int binarySearch(List<? extends T> sortedList, T key) 
    // left<right return -1; left == right return 0; left>right return 1;
    abstract int compare(T left, T right) 
    //传入集合由大到小排序，返回最大的k个元素
    <E extends T> List<E> greatestOf(Iterable<E> iterable, int k) 
    //传入集合由小到大排序，返回最小的k个元素
    <E extends T> List<E> leastOf(Iterable<E> iterable, int k)  
    //按照当前的Ordering进行排序
    <E extends T> List<E> sortedCopy(Iterable<E> iterable) 
    // 判断当前集合是否已经进行了排序
    boolean isOrdered(Iterable<? extends T> iterable) 
    // strictly ordered 严格排序，即排序集合中没有相等的数据时才会返回true
    boolean isStrictlyOrdered(Iterable<? extends T> iterable) 
    // 返回两个数中的最大值
    <E extends T> E max(E a, E b)  
    //返回多个数中的最大值
    <E extends T> E max(E a, E b, E c, E... rest)  
    //返回集合中的最大值，若有多个，返回第一个
    <E extends T> E max(Iterable<E> iterable) 
    // 返回两个数中的最小值
    <E extends T> E min(E a, E b) 
    //返回多个数中的最小值
    <E extends T> E min(E a, E b, E c, E... rest) 
    //返回集合中的最小值，若有多个，返回第一个
    <E extends T> E min(Iterable<E> iterable) 
    // 把空值放在首位
    <S extends T> Ordering<S> nullsFirst() 
    // 把空值放在末位
    <S extends T> Ordering<S> nullsLast()  

### 例子

```java
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import java.util.*;

/**
 * Guava Ordering
 *
 * @author Soul
 */
public class OrderingTest {

  private List<Integer> list_1 = new ArrayList<>(Arrays.asList(1, 0, 3, 4, 2, 1));
  private List<Integer> list_2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
  private List<String> abc = ImmutableList.of("a", "b", "c");//创建一个不可变集合
  private List<Integer> comparator_test = new ArrayList<>(Arrays.asList(-2, 1, 4, 3, 5, -9, -1));

  public static void main(String[] args) {
    OrderingTest test = new OrderingTest();
    Ordering ordering = Ordering.natural();
    test.binarySearchTest(ordering);
    test.compareTest(ordering);
    test.isSortedTest(ordering);
    test.reverseTest(ordering);
    test.maxMinTest(ordering);
    test.otherMethodTest(ordering);
    test.useComparatorTest(ordering);
    test.sortedSet();
  }

  /**
   * ordering.binarySearch(sortedList, key); attention to sortedList
   *
   * list_1 = ordering.sortedCopy(list_1);
   */
  private void binarySearchTest(Ordering ordering) {
    list_1 = ordering.sortedCopy(list_1);
    System.out.println("sorted copy ：" + list_1);
    System.out.println("is strictly sorted list_1： " + ordering.isStrictlyOrdered(list_1));//false
    int res = ordering.binarySearch(list_1, 5);
    System.out.println("binary search ：" + res);
  }

  private void compareTest(Ordering ordering) {
   int res = ordering.compare(1, 2);
    System.out.println("compare two parameters：" + res);

    res = ordering.compare(2, 2);
    System.out.println("compare two parameters：" + res);

    res = ordering.compare(3, 2);
    System.out.println("compare two parameters：" + res);
  }

  /**
   * 判断集合是否已经排序
   */
  private void isSortedTest(Ordering ordering) {
    System.out.println("sorted test ：" + ordering.isOrdered(list_2));//true
    System.out.println("strictly sorted test ：" + ordering.isStrictlyOrdered(list_2));
  }

  /**
   * reverse
   */
  private void reverseTest(Ordering ordering) {
    System.out.println("test list_2 reverse :" + ordering.reverse().sortedCopy(list_2));
    System.out.println("isOrdered reverse :" + ordering.reverse().isOrdered(abc));
  }

  /**
   * max min
   */
  private void maxMinTest(Ordering ordering) {
    System.out.println("max :" + ordering.max(abc));
    System.out.println("min :" + ordering.min(abc));
  }

  /**
   * ImmutableSortedSet
   */
  private void sortedSet() {
    Set<String> imSortList = ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
    System.out.println("imSortSet：" + imSortList);
  }

  private void otherMethodTest(Ordering ordering) {

    System.out.println("leastOf:" + ordering.leastOf(list_2, 2));
    System.out.println("greatestOf:" + ordering.greatestOf(list_2, 3));
    System.out.println("reverse list_2 :" + ordering.reverse().sortedCopy(list_2));
    list_2.add(null);
    System.out.println("add null list_2:" + list_2);
    System.out.println("nullsFirst list_2 :" + ordering.nullsFirst().sortedCopy(list_2));
    System.out.println("nullsLast list_2 :" + ordering.nullsLast().sortedCopy(list_2));
  }

  /**
   * 对ordering使用自定义排序器
   */
  private void useComparatorTest(Ordering ordering) {
    Comparator<Integer> comparator = (Integer o1,Integer o2)->(o1.compareTo(o2));
    System.out.println("comparator test: " + Ordering.from(comparator).sortedCopy(comparator_test));
  }
}
```

    sorted copy ：[0, 1, 1, 2, 3, 4]
    is strictly sorted list_1： false
    binary search ：-7
    compare two parameters：-1
    compare two parameters：0
    compare two parameters：1
    sorted test ：true
    strictly sorted test ：true
    test list_2 reverse :[9, 8, 7, 6, 5, 4, 3, 2, 1]
    isOrdered reverse :false
    max :c
    min :a
    leastOf:[1, 2]
    greatestOf:[9, 8, 7]
    reverse list_2 :[9, 8, 7, 6, 5, 4, 3, 2, 1]
    add null list_2:[1, 2, 3, 4, 5, 6, 7, 8, 9, null]
    nullsFirst list_2 :[null, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    nullsLast list_2 :[1, 2, 3, 4, 5, 6, 7, 8, 9, null]
    comparator test: [-9, -2, -1, 1, 3, 4, 5]
    imSortSet：[a, b, c, d]

对binarySearch进行说明：  __binary search ：-7__ 

    [0, 1, 1, 2, 3, 4]为排序结果，我们要查找的是5.  
    第一次low = 0，high = 5. mid = 2, 查到1<5.low = mid+1 = 3
    第二次low = 3, high = 5, mid = 4, 查到3<5.low = mid+1 = 5  
    第三次low = 5, high = 5, mid = 5, 查到4<5.low = mid+1 = 6
    第四次low > high,结束循环，返回 -(low+1) = -7
