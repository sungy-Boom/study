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

#### 输出结果  

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
     
    //移除一个元素，其count值 会相应减少
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

#### 输出结果

    1
    0
    向集合中添加元素：[null, qw x 4, raw x 3]
    计算集合中指定元素的个数：3
    集合中是否包含指定的元素：true
    集合中是否包含指定的集合：true
    集合中是否包含指定的集合：true
    remove test：true
    after remove ：[null, qw x 4, raw x 2]
    remove specify num：0
    after remove specify num ：[null, qw x 4, raw x 2]
    retain :true
    after retain element：[qw x 4, raw x 2]
    remove all in set：true
    after remove all element in set：[]
    1
    0
    向集合中添加元素：[null, qw x 4, raw x 3]
    use elementSet go through set：null qw raw iterator go through set ：
    null qw qw qw qw raw raw raw 
    entrySet go through set：
    1 null 0
    4 qw 2
    3 raw 3
    0
    [null, test x 9, qw x 4, raw x 3]
    4
    [null, test x 9, qw x 6, raw x 3]
    false
    true

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

#### 输出结果

    test return value: null
    test return value: 9
    {test1=0, test2=3}
    put map into biMap: {test1=0, test2=3, map2=4, map1=2, map=1}
    null
    3
    force put into biMap:{test1=0, map2=4, map1=2, map=1, test3=3}
    get value by key: 1
    get key by value: map
    get values: [0, 4, 2, 1, 3]
    get keys: [test1, map2, map1, map, test3]
    [1, 2]
    first

### 3.3 Multimap
多重映射接口扩展映射，使得其键一次可被映射到多个值。
#### 常用方法

    //Multimap 中加入元素，如果key在之前不存在，返回true，如果存在，返回false。 对于同一个key，如果put不同的value，会将value形成一个Collection
    boolean put(K, V)
    boolean putAll(K key, Iterable<? extends V> values)
    //清除map中的所有内容
    void clear()
    //判断是否包含 key-value 对
    boolean containsEntry(Object key, Object value)
    boolean containsKey(Object * key)
    boolean containsValue(Object value)
    // 返回一个存放在Collection中的map
    Collection<Map.Entry<K,V>> entries()
    // 根据key返回value
    Collection<V> get(K key)
    //返回所有的key，不重复
    Multiset<K> keys()
    Set<K> keySet()
    //返回一个map，value放到数组中，返回一个Map<key, Collection<value>>
    Map<K,Collection<V>> asMap()
    // remove掉一个键值对，如果没有这键值对，返回false，否则返回true
    boolean remove(Object key, Object value)
    //remove删除指定key所对应的value，返回Collection<value>
    Collection<V> removeAll(Object key)
    // 把指定key原来的value替换为指定的value。返回原value
    Collection<V> replaceValues(K key, Iterable<? extends V> values)
    
#### 代码示例

```java
public class MultimapTest {

    public static void main(String[] args) {
        Multimap<String, Integer> multiMap = HashMultimap.create();
        Multimap<String, List<Integer>> map = HashMultimap.create();
        MultimapTest test = new MultimapTest();

        test.addTest(multiMap, map);
        System.out.println();
        test.judgeContain(multiMap, map);
        System.out.println();
        test.throughTest(multiMap, map);
        System.out.println();
        test.replceTest(multiMap, map);
        System.out.println();
        test.removeTest(multiMap, map);
        System.out.println();

        test.clearTest(multiMap, map);
    }

    /**
     * put key-value to map
     */
    private void addTest(Multimap<String, Integer> multiMap, Multimap<String, List<Integer>> map) {
        multiMap.put("test", 1);
        System.out.println("is the value put success : " + multiMap.put("test", 2));
        System.out.println("after addTest map : " + multiMap);
        System.out.println("map get key : " + multiMap.get("test"));

        System.out.println("is the value put success : " + multiMap.put("test", 2));
        System.out.println("after addTest map : " + multiMap);
        System.out.println("map get key : " + multiMap.get("test"));

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        map.put("Test", list);

        List<Integer> list_2 = new ArrayList<>();
        list_2.add(1);
        list_2.add(1);
        System.out.println("is the value list put success : " + map.put("Test", list_2));
        System.out.println("map put list : " + map);
        list_2.add(2);
        System.out.println("is the value list put success : " + map.put("Test", list_2));
        System.out.println("map put list : " + map);
        System.out.println("is the value list put success : " + map.put("Test1", list_2));
        System.out.println("map put list : " + map);
    }

    /**
     * judge
     */
    private void judgeContain(Multimap<String, Integer> multiMap, Multimap<String, List<Integer>> map) {
        System.out.println("is multiMap contain the key-value pair : " +
                multiMap.containsEntry("test", 1));
        System.out.println("is multiMap contain the specify key : " +
                multiMap.containsKey("test"));
        System.out.println("is multiMap contain the specify value : " +
                multiMap.containsValue(1));

        System.out.println("is map contain the key-value pair : " +
                map.containsEntry("test", Arrays.asList(1, 1)));
        System.out.println("is map contain the key-value pair : " +
                map.containsEntry("Test", Arrays.asList(1, 1)));
        System.out.println("is map contain the specify key : " +
                multiMap.containsKey("test"));
        System.out.println("is map contain the specify value : " +
                multiMap.containsValue(Arrays.asList(1, 1)));

        //Multimap如果value是list，那么list中的每一个数都有一个对应的key
        System.out.println(multiMap);
        System.out.println("is map contain the specify value : " +
                multiMap.containsValue(1));
    }

    /**
     * go through map
     */
    private void throughTest(Multimap<String, Integer> multiMap, Multimap<String, List<Integer>> map) {
        // map.entries()
        Collection<Map.Entry<String, List<Integer>>> collectionMap = map.entries();
        Iterator<Map.Entry<String, List<Integer>>> iter = collectionMap.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, List<Integer>> entry = iter.next();
            System.out.println("key-value " + entry);
            System.out.println("key " + entry.getKey());
            System.out.println("value " + entry.getValue());
        }

        //map.get(K key)
        Collection<List<Integer>> list = map.get("Test");
        System.out.println("getKey : " + list);

        //map.keys()
        Multiset<String> set = map.keys();
        System.out.println("keys: " + set);

        //map.keySet()
        Set<String> keySet = map.keySet();
        System.out.println("keySet: " + keySet);

        //map.asMap()
        Map<String, Collection<Integer>> asmap = multiMap.asMap();
        System.out.println("asMap: " + asmap);

        //map.values()
        Collection<Integer> multi = multiMap.values();
        System.out.println("values: " + multi);
    }

    //replace
    private void replceTest(Multimap<String, Integer> multiMap, Multimap<String, List<Integer>> map) {
        System.out.println("replace test: " + multiMap.replaceValues("test", ImmutableList.of(1, 23, 4)));
        System.out.println(multiMap);

        System.out.println("replace test: " + multiMap.replaceValues("error_test", ImmutableList.of(1, 23, 4)));
        System.out.println(multiMap);
    }

    // remove
    private void removeTest(Multimap<String, Integer> multiMap, Multimap<String, List<Integer>> map) {
        System.out.println("multimap remove false: " + multiMap.remove("test", ImmutableList.of(1, 2)));
        System.out.println("multimap remove true: " + multiMap.remove("test", 1));
        System.out.println("multimap after remove : " + multiMap);

        System.out.println("map remove false: " + map.remove("Test", ImmutableList.of(1, 1)));
        System.out.println("map remove true: " + map.remove("Test", 1));
        System.out.println("map after remove : " + map);

        // map.removeAll()
        System.out.println("removeAll test: " + multiMap.removeAll("test"));
        System.out.println("removeAll test: " + multiMap.removeAll("error_test"));
    }


    /**
     * clear map
     */
    private void clearTest(Multimap<String, Integer> multiMap, Multimap<String, List<Integer>> map) {
        multiMap.clear();
        map.clear();
        System.out.println("multiMap after clear : " + multiMap);
        System.out.println("map after clear : " + map);
    }

}
```

#### 输出结果

    is the value put success : true
    after addTest map : {test=[1, 2]}
    map get key : [1, 2]
    is the value put success : false
    after addTest map : {test=[1, 2]}
    map get key : [1, 2]
    is the value list put success : false
    map put list : {Test=[[1, 1]]}
    is the value list put success : true
    map put list : {Test=[[1, 1], [1, 1, 2]]}
    is the value list put success : true
    map put list : {Test1=[[1, 1, 2]], Test=[[1, 1], [1, 1, 2]]}
     
    is multiMap contain the key-value pair : true
    is multiMap contain the specify key : true
    is multiMap contain the specify value : true
    is map contain the key-value pair : false
    is map contain the key-value pair : true
    is map contain the specify key : true
    is map contain the specify value : false
    {test=[1, 2]}
    is map contain the specify value : true
     
    key-value Test1=[1, 1, 2]
    key Test1
    value [1, 1, 2]
    key-value Test=[1, 1]
    key Test
    value [1, 1]
    key-value Test=[1, 1, 2]
    key Test
    value [1, 1, 2]
    getKey : [[1, 1], [1, 1, 2]]
    keys: [Test1, Test x 2]
    keySet: [Test1, Test]
    asMap: {test=[1, 2]}
    values: [1, 2]
     
    replace test: [1, 2]
    {test=[4, 1, 23]}
    replace test: []
    {test=[4, 1, 23], error_test=[4, 1, 23]}
     
    multimap remove false: false
    multimap remove true: true
    multimap after remove : {test=[4, 23], error_test=[4, 1, 23]}
    map remove false: true
    map remove true: false
    map after remove : {Test1=[[1, 1, 2]], Test=[[1, 1, 2]]}
    removeAll test: [4, 23]
    removeAll test: [4, 1, 23]
     
    multiMap after clear : {}
    map after clear : {}
    
### 3.4 Table 
有两个键一个值的数据结构。

#### 常用API

    //获取指定列的值，返回行和值形成的键值对
    Map<R,V> column(C columnKey)
    // 获取指定行的值，返回列和值形成的键值对
    Map<C,V> row(R rowKey)
    // 获取列元素
    Set<C> columnKeySet()
    // 获取行元素
    Set<R> rowKeySet()
    // 返回以row为键，列和值为值得键值对
    Map<R,Map<C,V>> rowMap()
    Map<C,Map<R,V>> columnMap()
    // 返回所有值
    Collection<V> values()
    //根据行和列确定值
    V get(Object rowKey, Object columnKey)
    //根据行和列移除元素，如果存在返回值，不存在返回null
    V remove(Object rowKey, Object columnKey)
    //判断元素是否存在
    boolean contains(Object rowKey, Object columnKey)
    boolean containsColumn(Object columnKey)
    boolean containsRow(Object rowKey)
    boolean containsValue(Object value)

#### 代码示例

```java
public class TableTest {

    public static void main(String[] args) {
        TableTest test = new TableTest();
        Table<String, String, Integer> table = HashBasedTable.create();

        test.putTest(table);
        System.out.println();
        test.throughTableTest(table);
        System.out.println();
        test.containTest(table);
    }

    //添加元素
    private void putTest(Table<String, String, Integer> table) {
        table.put("语文", "张三", 110);
        table.put("语文", "李四", 120);
        table.put("语文", "王五", 130);

        table.put("数学", "张三", 110);
        table.put("数学", "李四", 120);
        table.put("数学", "王五", 130);
        System.out.println("table :" + table);
    }

    // 遍历
    private void throughTableTest(Table<String, String, Integer> table) {
        System.out.println(table);
        Map<String, Integer> columnMap = table.column("张三");
        System.out.println("column values: " + columnMap);

        Map<String, Integer> rowMap = table.row("语文");
        System.out.println("row values: " + rowMap);

        Set<String> columnSet = table.columnKeySet();
        System.out.println("column key set: " + columnSet);

        Set<String> rowSet = table.rowKeySet();
        System.out.println("row key set: " + rowSet);

        Map<String, Map<String, Integer>> rowMapMap = table.rowMap();
        System.out.println("rowMap test: " + rowMapMap);

        Map<String, Map<String, Integer>> columnMapMap = table.columnMap();
        System.out.println("columnMap test: " + columnMapMap);

        Collection<Integer> collectionTest = table.values();
        System.out.println("collection : " + collectionTest);

        Integer value = table.get("语文", "张三");
        System.out.println("get value by row and column :" + value);

        System.out.println("根据行和列移除元素1： " + table.remove("张三", "语文"));
        System.out.println("根据行和列移除元素2： " + table.remove("张三", "张三"));
        System.out.println("根据行和列移除元素3： " + table.remove("语文", "张三"));
    }

    private void containTest(Table<String, String, Integer> table) {
        System.out.println("contains key and value1 : " + table.contains("张三", "语文"));
        System.out.println("contains key and value2 : " + table.contains("语文", "李四"));
        System.out.println("contain row: " + table.containsRow("语文"));
        System.out.println("contain column: " + table.containsColumn("张三"));
        System.out.println("contain value: " + table.containsValue(110));
    }
}
```

#### 结果输出

    table :{语文={张三=110, 李四=120, 王五=130}, 数学={张三=110, 李四=120, 王五=130}}
    
    {语文={张三=110, 李四=120, 王五=130}, 数学={张三=110, 李四=120, 王五=130}}
    column values: {语文=110, 数学=110}
    row values: {张三=110, 李四=120, 王五=130}
    column key set: [张三, 李四, 王五]
    row key set: [语文, 数学]
    rowMap test: {语文={张三=110, 李四=120, 王五=130}, 数学={张三=110, 李四=120, 王五=130}}
    columnMap test: {张三={语文=110, 数学=110}, 李四={语文=120, 数学=120}, 王五={语文=130, 数学=130}}
    collection : [110, 120, 130, 110, 120, 130]
    get value by row and column :110
    根据行和列移除元素1： null
    根据行和列移除元素2： null
    根据行和列移除元素3： 110
     
    contains key and value1 : false
    contains key and value2 : true
    contain row: true
    contain column: true
    contain value: true
    
## 4.字符串拼接

### 4.1 Joiner

#### 常用方法

    // 通过使用指定参数，对字符串进行拼接
    static Joiner on(String separator)
    static Joiner on(char separator)
    // join里边是要拼接的字符串
    String join(Iterable<?> parts)
    String join(Iterator<?> parts)
    String join(Object[] parts)
    String join(Object first, Object second, Object... rest)
    // 对null进行处理
    Joiner skipNulls()
    Joiner useForNull(String nullText)
    Joiner.MapJoiner withKeyValueSeparator(String keyValueSeparator)
    
#### 代码示例

```java
public class JoinerTest {

    public static void main(String[] args) {
        JoinerTest test = new JoinerTest();

        test.joinerTest1();
    }

    private void joinerTest1() {
        List<String> list1 = ImmutableList.of("data1", "data2", "1", "2");
        System.out.println("on(char separator) test : " + Joiner.on(',').join(list1));
        System.out.println("on(String separator) test : " + Joiner.on("--").join(list1));
//        list1.add("null");

        System.out.println();
        List<String> list2 = new ArrayList<>(Arrays.asList("1", "test", "4"));
        list2.add("null");
        list2.add("");
        System.out.println("test : " + Joiner.on("--").join(list2));
        System.out.println("skipNulls() test : " + Joiner.on("--").skipNulls().join(list2, null,1));
        System.out.println("useForNull() test : " + Joiner.on("--").useForNull("this is a null").join(list2, null,2,1));

        System.out.println();
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        Joiner.MapJoiner mapJoiner = Joiner.on(",").withKeyValueSeparator("=");
        System.out.println("withKeyValueSeparator() test : " + mapJoiner.join(map));
    }
}
```

#### 输出结果
    
    on(char separator) test : data1,data2,1,2
    on(String separator) test : data1--data2--1--2
     
    test : 1--test--4--null--
    skipNulls() test : [1, test, 4, null, ]--1
    useForNull() test : [1, test, 4, null, ]--this is a null--2--1
     
    withKeyValueSeparator() test : key1=value1,key2=value2,key3=value3
    
### 4.2 Splitter

#### 常用方法 

    // 设置分割字符
    static Splitter on(char separator)
    static Splitter on(String separator)
    static Splitter on(CharMatcher separatorMatcher)
    static Splitter on(Pattern separatorPattern)
    static Splitter onPattern(String separatorPattern)
    // 返回的结果是Iterable接口类型的数据
    Iterable<String> split(CharSequence sequence)
    List<String> splitToList(CharSequence sequence)
    // 去掉子串中的空格
    Splitter trimResults()
    Splitter trimResults(CharMatcher trimmer)
    // 直接返回Map的键值对
    Splitter.MapSplitter withKeyValueSeparator(char separator)
    Splitter.MapSplitter withKeyValueSeparator(String separator)
    Splitter.MapSplitter withKeyValueSeparator(Splitter keyValueSplitter)
    // 将给定的字符串分割为长度为length的子字符串
    static Splitter fixedLength(int length)
    // 分割的子字符串达到了limit个时
    Splitter limit(int limit)
    // 去掉空的子字符串
    Splitter omitEmptyStrings()
    
#### 代码示例 

```java
public class SplitterTest {

    public static void main(String[] args) {
        SplitterTest test = new SplitterTest();
        test.spiltterTest();

    }

    private final Pattern testPattern = Pattern.compile("@");

    private void spiltterTest() {
        String str = "this is a   test.@99999@email";
        System.out.println("on(String) test: " + Splitter.on(" ").split(str));
        System.out.println("on(Pattern) test: " + Splitter.on(testPattern).split(str));
        System.out.println("onPattern(String) test: " + Splitter.onPattern("@").split(str));

        System.out.println();
        System.out.println("fixedLength(length) test : " + Splitter.fixedLength(2).split(str));
        System.out.println("fixedLength(length) and trimResults test : " + Splitter.fixedLength(2).trimResults().split(str));
        System.out.println("fixedLength(length) and trimResults test : " + Splitter.fixedLength(2).trimResults().split(str));

        System.out.println();
        System.out.println("on(String) test: " + Splitter.on(" ").split(str));
        System.out.println("fixedLength(length) and omitEmptyStrings test : " +
                Splitter.on(" ").omitEmptyStrings().split(str));

        System.out.println();
        Map<String,String> split = Splitter.on(";").trimResults().withKeyValueSeparator("=").split("a=2;b=3");
        System.out.println("withKeyValueSeparator test: " + split.get("a"));
    }
}
```

#### 输出结果 

    on(String) test: [this, is, a, , , test.@99999@email]
    on(Pattern) test: [this is a   test., 99999, email]
    onPattern(String) test: [this is a   test., 99999, email]
     
    fixedLength(length) test : [th, is,  i, s , a ,   , te, st, .@, 99, 99, 9@, em, ai, l]
    fixedLength(length) and trimResults test : [th, is, i, s, a, , te, st, .@, 99, 99, 9@, em, ai, l]
    fixedLength(length) and trimResults test : [th, is, i, s, a, , te, st, .@, 99, 99, 9@, em, ai, l]
     
    on(String) test: [this, is, a, , , test.@99999@email]
    fixedLength(length) and omitEmptyStrings test : [this, is, a, test.@99999@email]
     
    withKeyValueSeparator test: 2
    
### 4.3 CharMatcher 
找到匹配的字符，处理匹配的字符。
实现了大量公用内部类, 用来方便用户对字符串做匹配；
实现了大量处理字符串的方法, 使用特定的CharMatcher可以对匹配到的字符串做出多种处理

#### 常量

    ANY: 匹配任何字符
    ASCII: 匹配是否是ASCII字符
    BREAKING_WHITESPACE: 匹配所有可换行的空白字符(不包括非换行空白字符,例如"\u00a0")
    DIGIT: 匹配ASCII数字
    INVISIBLE: 匹配所有看不见的字符
    JAVA_DIGIT: 匹配UNICODE数字, 使用 Character.isDigit() 实现
    JAVA_ISO_CONTROL: 匹配ISO控制字符, 使用 Charater.isISOControl() 实现
    JAVA_LETTER: 匹配字母, 使用 Charater.isLetter() 实现
    JAVA_LETTER_OR_DIGET: 匹配数字或字母
    JAVA_LOWER_CASE: 匹配小写
    JAVA_UPPER_CASE: 匹配大写
    NONE: 不匹配所有字符
    SINGLE_WIDTH: 匹配单字宽字符, 如中文字就是双字宽
    WHITESPACE: 匹配所有空白字符

#### 常用方法 

    //返回匹配指定字符的Matcher
    CharMatcher is(char match):
    //返回不匹配指定字符的Matcher
    CharMatcher isNot(char match):
     
    //返回匹配sequence中任意字符的Matcher
    CharMatcher anyOf(CharSequence sequence):
    //返回不匹配sequence中任何一个字符的Matcher
    CharMatcher noneOf(CharSequence sequence):
     
    //删除sequence中匹配到到的字符并返回
    String removeFrom(CharSequence sequence):
    //保留sequence中匹配到的字符并返回
    String retainFrom(CharSequence sequence):
    //返回以当前Matcher判断规则相反的Matcher
    CharMatcher negate():
     
    //返回与other匹配条件组合做与来判断的Matcher
    CharMatcher and(CharMatcher other):
    //返回与other匹配条件组合做或来判断的Matcher
    CharMatcher or(CharMatcher other):
    
    //返回匹配范围内任意字符的Matcher
    CharMatcher inRange(char startInclusive, char endIncludesive):
    //返回使用predicate的apply()判断匹配的Matcher
    CharMatcher forPredicate(Predicate<? super Charater> predicate):
    //只要sequence中有任意字符能匹配Matcher,返回true
    boolean matchesAnyOf(CharSequence sequence):
    //sequence中所有字符都能匹配Matcher,返回true
    boolean matchesAllOf(CharSequence sequence):
    // sequence中所有字符都不能匹配Matcher,返回true
    boolean matchesNoneOf(CharSequence sequence):
    //返回sequence中匹配到的第一个字符的坐标
    int indexIn(CharSequence sequence):
    //返回从start开始,在sequence中匹配到的第一个字符的坐标
    int indexIn(CharSequence sequence, int start):
    //返回sequence中最后一次匹配到的字符的坐标
    int lastIndexIn(CharSequence sequence):
    //返回sequence中匹配到的字符计数
    int countIn(CharSequence sequence):
     
    //替换sequence中匹配到的字符并返回
    String replaceFrom(CharSequence sequence, char replacement):
    //删除首尾匹配到的字符并返回
    String trimFrom(CharSequence sequence):
    //删除首部匹配到的字符
    String trimLeadingFrom(CharSequence sequence):
    //删除尾部匹配到的字符
    String trimTrailingFrom(CharSequence sequence):
    //将匹配到的组(连续匹配的字符)替换成replacement
    String collapseFrom(CharSequence sequence, char replacement):
    //先trim再将分好的组用replacement连接
    String trimAndCollapseFrom(CharSequence sequence, char replacement):
    
#### 代码示例

```java
public class CharMatcherTest {
    public static void main(String[] args) {
        CharMatcherTest test = new CharMatcherTest();
        test.charMatcherTest();
    }

    public void charMatcherTest() {
        // removerFrom
        String removeFromResult = CharMatcher.isNot('a').removeFrom("abacd");
        System.out.println("removeForm:" + removeFromResult);
        // retainFrom method
        String retainFormResult = CharMatcher.is('a').retainFrom("abacd");
        System.out.println("retainForm:" + retainFormResult);
        // replaceFrom method
        String replaceFormResult1 = CharMatcher.WHITESPACE.replaceFrom("a bcd",
                'f');
        System.out.println("replaceFrom_1:" + replaceFormResult1);
        String replaceFormResult2 = CharMatcher.DIGIT.replaceFrom("a3bcd",
                "Three");
        System.out.println("replaceFrom_2:" + replaceFormResult2);
        // trimFrom
        String trimFromResult = CharMatcher.anyOf("ab").trimFrom("abacatabb");
        System.out.println("trimFrom:" + trimFromResult);
        // trimLeadingFrom
        String trimLeadingFromResult = CharMatcher.anyOf("ab").trimLeadingFrom(
                "abacatabb");
        System.out.println("trimLeadingFrom:" + trimLeadingFromResult);
        // trimTrailingFrom
        String trimTrailingFromResult = CharMatcher.anyOf("ab")
                .trimTrailingFrom("abacatabb");
        System.out.println("trimTrailingFrom:" + trimTrailingFromResult);
        // collapseFrom
        String collapseFromResult = CharMatcher.anyOf("bre").collapseFrom(
                "bookkeeper", '-');
        System.out.println("collapseFrom:" + collapseFromResult);
        // trimAndCollapseFrom
        String trimAndCollapseFromResult = CharMatcher.anyOf("bre")
                .trimAndCollapseFrom("bookkeeperkko", '-');
        System.out.println("trimAndCollapseFrom:" + trimAndCollapseFromResult);
        // matchesAllOf
        boolean matchesAllOfResult = CharMatcher.JAVA_UPPER_CASE
                .matchesAnyOf("hcd");
        System.out.println("matchesAnyOf:" + matchesAllOfResult);
        // or and negate
        String orResult = CharMatcher.JAVA_DIGIT
                .or(CharMatcher.JAVA_UPPER_CASE).retainFrom("dd59cF");
        System.out.println("or:" + orResult);
        // negate
        String negateResult = CharMatcher.JAVA_DIGIT.negate().retainFrom(
                "dd59cF");
        System.out.println("negate:" + negateResult);

        // and
        String andResult = CharMatcher.DIGIT
                .and(CharMatcher.WHITESPACE).retainFrom(
                "1gg4 7d d5R9cFa4");
        System.out.println("and:" + andResult);
    }
}
```

#### 输出结果

    removeForm:aa
    retainForm:aa
    replaceFrom_1:afbcd
    replaceFrom_2:aThreebcd
    trimFrom:cat
    trimLeadingFrom:catabb
    trimTrailingFrom:abacat
    collapseFrom:-ookk-p-
    trimAndCollapseFrom:ookk-p-kko
    matchesAnyOf:false
    or:59F
    negate:ddcF
    
### 4.4 CaseFormat

#### 常用方法

    //testData
    LOWER_CAMEL
    // test-data
    LOWER_HYPHEN
    test_data
    LOWER_UNDERSCORE
    //TestData
    UPPER_CAMEL
    //TEST_DATA
    UPPER_UNDERSCORE
     
    //从指定格式转换到另一种格式
    String to(CaseFormat format, String str)
    
#### 代码示例 

```java
public class CaseFormatTest {

    public static void main(String[] args){
        CaseFormatTest test = new CaseFormatTest();;

        test.caseFormatTest();
    }

    private void caseFormatTest(){
        String data = "test_data";
        System.out.println("LOWER_HYPHEN to UPPER_CAMEL: " +
                CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, "test-data"));
        System.out.println("LOWER_UNDERSCORE to LOWER_CAMEL: " +
                CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println("UPPER_UNDERSCORE to UPPER_CAMEL: " +
                CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "Test_Data"));
        System.out.println("LOWER_CAMEL to LOWER_UNDERSCORE: " +
                CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testData"));
        System.out.println("LOWER_CAMEL to LOWER_HYPHEN: " +
                CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "testData"));
        System.out.println("LOWER_CAMEL to UPPER_UNDERSCORE: " +
                CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "testData"));
    }
}
```

#### 输出结果 

    LOWER_HYPHEN to UPPER_CAMEL: TestData
    LOWER_UNDERSCORE to LOWER_CAMEL: testData
    UPPER_UNDERSCORE to UPPER_CAMEL: TestData
    LOWER_CAMEL to LOWER_UNDERSCORE: test_data
    LOWER_CAMEL to LOWER_HYPHEN: test-data
    LOWER_CAMEL to UPPER_UNDERSCORE: TEST_DATA
    
### 5.Guava cache

