package com.daily.study.guava;

import com.google.common.base.Preconditions;

/**
 * Preconditions test
 *
 * @author Soul
 */
public class PreconditionTest {

    public static void main(String[] args) {
        PreconditionTest test = new PreconditionTest();
       /* try {
            test.nameArgumentCondition(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        try {
            test.nameArgumentCondition("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            test.nameArgumentCondition("这是一个测试");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void nameArgumentCondition(String name) throws Exception {
        //checkArgument(expression) 只判断条件是否符合
//        Preconditions.checkArgument(name == null,"sdf");
        //checkoutArgument(expression, message)  判断条件，并返回message
        Preconditions.checkArgument("".equals(name), "name is \"\"");

        Preconditions.checkArgument(name == null, "name is %s", name);
        System.out.println("测试中输出" + name);
    }
}
