[TOC]
<h1 align="center">Guava</h1>

## 1. 在pom文件中引入Guava
 
    <dependency>  
        <groupId>com.google.guava</groupId>  
        <artifactId>guava</artifactId>  
        <version>21.0</version>  
    </dependency>  

## 2. 基本类
### 2.1 Optional 
在Java中null既不是对象也不是实例，只是一个关键字。Optional就是对null的处理。  
#### <T> T Optional.of(T reference)  
    //Optional.of ---> 会检测null 如果是null抛出异常。在of方法中调用了Preconditions.checkNotNull方法进行空值判断 
    Optional<Integer> num1 = Optional.of(value1);  
#### <T> Optional<T> Optional.fromNullable(T nullableReference)     
    //Optional.fromNullable --> 允许输入为空。但是在使用的时候要进行判断  
    Optional<Integer> num2 = Optional.fromNullable(value1);    
    //如果num2不为空。pres == true 否则 pres == false  
    boolean pres = num2.isPresent();   
#### T orNull() 
    Optional<Integer> num3 = Optional.absent();   
    //Optional.orNull --> 如果是null，就把null作为值，否则返回num3的值    
    System.out.println(num3.orNull());   
#### T or(Optional<? extends T> newValue)
    //Optional.or --> 如果存在，返回值，如果不存在，返回传入的值  
    //想用一个特定值代替null的时候，可以使用这个方法  
    Integer num = Optional.fromNullable(value1).or(0);  
#### T get()  
    //通过get方法获取Optional对象的值
    num1.get();
    
## 2.2 Preconditions
前置条件检查，可以对表达式、布尔值进行检查，检查中可以输出自定义异常信息，输出被检查的值  
在Preconditions这个类中，checkArgument/checkState比较相似，checkElementIndex /checkPositionIndex比较相似  

#### checkArgument/checkState方法
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

#### checkElementIndex/checkPositionIndex方法

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
    
#### checkNotNull 方法

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
    
### 2.3 Ordering  
Ordering是Guava类库提供的一个强大的比较器工具，它非常容易扩展，可以轻松构造复杂的comparator，然后用在容器的比较、排序等操作中。Ordering使用链式表达式，配合java8的lambda~~很不错  

#### 返回一个排序器

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

#### 常用方法  

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

#### 例子

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
    System.out.println(Test + ordering.isOrdered(list_2));//true
    System.out.println(Test + ordering.isStrictlyOrdered(list_2));
  }

  /**
   * reverse
   */
  private void reverseTest(Ordering ordering) {
    System.out.println(Test + ordering.reverse().sortedCopy(list_2));
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
    System.out.println(Test + Ordering.from(comparator).sortedCopy(comparator_test));
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

## 3.集合类

### 3.1 Multiset
允许重复，但是不保证顺序。

    Multiset<String> multiset_1 = LinkedHashMultiset.create();
    Multiset<String> multiset_2 = TreeMultiset.create();
    Multisets multisets;
    Multiset<String> multiset = HashMultiset.create();
        
#### 常用方法

    //向其中添加单个元素
    int add(E element)  
    //向其中添加指定个数的元素 返回0 element是新增加的对象，返回1的时候，说明原来的element已经存在
    int add(E element,int occurrences) 
     
    //返回给定参数元素的个数
    int count(Object element) 
    //判断集合中是否包含指定元素
    boolean contains(Object element)
    //判断当前集合是不是指定集合的子集
    boolean containsAll(Collection<?> elements)
     
    //移除一个元素，其count值 会响应减少
    boolean remove(E element) 
    //如果element存在，返回数目，如果不存在返回0
    int remove(Object element, int occurrences) 
    //保留出现在给定集合参数的所有的元素
    boolean retainAll(Collection c) 
    //去除出现给给定集合参数的所有的元素
    boolean removeAll(Collection c) 
     
    //将不同的元素放入一个Set中。保有set的不重性质，只输出元素，不计个数
    Set<E> elementSet() 
    //返回所有的数据，例如有 qw x 3 那么qw就会输出三次
    Iterator<E> iterator() 
    //类似与Map.entrySet 返回Set<Multiset.Entry>。包含的Entry支持使用getElement()和getCount()
    Set<Multiset.Entry<E>> entrySet()
     
    //设定某一个元素的重复次数.如果这个元素之前不存在，返回0，如果存在，返回之前出现的次数
    int setCount(E element ,int count)
    //将符合原有重复个数的元素修改为新的重复次数
    boolean setCount(E element,int oldCount,int newCount)
    
#### 代码示例

```java
public class MultisetTest {

  public static void main(String[] args) {

    Set<String> set = new HashSet<>();

    Multiset<String> multiset = HashMultiset.create();
    MultisetTest test = new MultisetTest();
    test.addSetElement(multiset);
    test.countAndJudge(multiset);
    test.removeAndRetain(multiset);
    test.goThroughSet(multiset);
    test.setCountTest(multiset);
  }

  /**
   * 集合中增加元素
   */
  private void addSetElement(Multiset<String> multiset) {
    multiset.add("raw");
    multiset.add("raw");
    multiset.add("raw");
    multiset.add("qw");

    //加入null值
    multiset.add(null);

    //指定添加元素的个数,这个个数最大为Integer.MAX_VALUE
//    System.out.println(multiset.add("count", Integer.MAX_VALUE));
//    System.out.println(multiset.add("count", Integer.MAX_VALUE+1));
    System.out.println(multiset.add("qw", 3));
    System.out.println(multiset.add("e", 0));
    System.out.println("向集合中添加元素：" + multiset);
  }

  /**
   * multiset集合统计与判断
   */
  private void countAndJudge(Multiset<String> multiset) {
    System.out.println("计算集合中指定元素的个数：" + multiset.count("raw"));
    System.out.println("集合中是否包含指定的元素：" + multiset.contains("raw"));
    Multiset<String> set = HashMultiset.create();
    set.add("raw");
    List<String> list = new ArrayList<>(Arrays.asList("raw", "qw"));
    System.out.println("集合中是否包含指定的集合：" + multiset.containsAll(set));
    System.out.println("集合中是否包含指定的集合：" + multiset.containsAll(list));
  }

  /**
   * remove / retain
   */
  private void removeAndRetain(Multiset<String> multiset) {
    System.out.println("remove test：" + multiset.remove("raw"));
    System.out.println("after remove ：" + multiset);

    System.out.println("remove specify num：" + multiset.remove("qpoi", 1));
    System.out.println("after remove specify num ：" + multiset);

    Multiset<String> set = HashMultiset.create();
    set.add("raw");
    set.add("qqqq");
    set.add("qw");
    System.out.println("retain :" + multiset.retainAll(set));
    System.out.println("after retain element：" + multiset);

    System.out.println("remove all in set：" + multiset.removeAll(set));
    System.out.println("after remove all element in set：" + multiset);

    addSetElement(multiset);
  }

  /**
   * 集合遍历
   */
  private void goThroughSet(Multiset<String> multiset) {
    Set<String> set = multiset.elementSet();

    System.out.print("use elementSet go through set：");
    for (String item : set) {
      System.out.print(item + " ");
    }

    Iterator<String> iter = multiset.iterator();
    System.out.println("iterator go through set ：");
    while (iter.hasNext()) {
      System.out.print(iter.next() + " ");
    }
    System.out.println();
    System.out.println("entrySet go through set：");
    for (Multiset.Entry<String> item : multiset.entrySet()) {
      System.out.println(
          item.getCount() + " " + item.getElement() + " " + (item.getElement() == null ? 0
              : item.getElement().length()));
    }
  }

  /**
   * setCount test
   */
  private void setCountTest(Multiset<String> multiset) {

    System.out.println(multiset.setCount("test", 9));
    System.out.println(multiset);
    System.out.println(multiset.setCount("qw", 6));
    System.out.println(multiset);

    System.out.println(multiset.setCount("qw", 8, 9));
    System.out.println(multiset.setCount("qw", 6, 9));
  }
}
```
### 3.2 BiMap
> 继承 java.util.Map

BiMap实现键值对的双向映射，并保持它们间的同步。键和值都不可以有重复
#### 常用方法

    //如果key存在，返回之前的value，如果不存在，返回null
    V put(K key, V value) 
    //如果key存在，返回之前的value，如果不存在，返回null
    V forcePut(K key, V value)
    //把另一个集合添加到bimap中
    void putAll(Map<? extends K,? extends V> map)
    //返回一个set
    Set<V> values()
    //将map的键值反转
    BiMap<K, V> inverse()

#### 例子

```java
public class BiMapTest {

  public static void main(String[] args) {
    BiMapTest test = new BiMapTest();
    BiMap<String, Integer> biMap = HashBiMap.create(16);
    test.addElement(biMap);
    test.keyToValue(biMap);
  }

  /**
   * biMap中添加元素 value already present 对于不同的键，不可以有相同的值
   */
  private void addElement(BiMap<String, Integer> biMap) {
    System.out.println(Test + biMap.put("test1", 9));
    System.out.println(Test + biMap.put("test1", 0));
    biMap.put("test2", 3);
    System.out.println(biMap);

    Map<String, Integer> map = new HashMap<>();
    map.put("map", 1);
    map.put("map1", 2);
    map.put("map2", 4);
    biMap.putAll(map);
    System.out.println("put map into biMap: " + biMap);
    System.out.println(biMap.forcePut("test3", 3));
    System.out.println(biMap.forcePut("test3", 3));
    System.out.println("force put into biMap:" + biMap);
  }
  /**
   * search value by key
   */
  private void keyToValue(BiMap<String, Integer> biMap) {
    System.out.println("get value by key: " + biMap.get("map"));
    System.out.println("get key by value: " + biMap.inverse().get(1));
    System.out.println("get values: " + biMap.values());
    System.out.println("get keys: " + biMap.inverse().values());
  }
}
```