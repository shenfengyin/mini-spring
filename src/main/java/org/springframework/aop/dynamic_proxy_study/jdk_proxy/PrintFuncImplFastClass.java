package org.springframework.aop.dynamic_proxy_study.jdk_proxy;


import net.sf.cglib.core.Signature;

/**
 * @Author:sfy
 * @Date: 2024/3/16 - 0:58
 * Description:
 */
public class PrintFuncImplFastClass {
    static Signature s0 = new Signature("printFunc", "()V");
    static Signature s1 = new Signature("secondFunc", "(I)I");


    /*
       printFunc(void) 0
       secondFunc(int) 1
     signature包括方法名字、参数返回值
     */
    public int getIndex(Signature signature) {
        if (s0.equals(signature)) {
            return 0;
        } else if (s1.equals(signature)) {
            return 1;
        }
        return -1;
    }

    // 更具方法编号，正常调用目标对象方法
    public Object invoke(int index, Object target, Object[] args) {
        if (index == 0) {
            ((PrintFuncImpl) target).printFunc();
            return null;
        } else if (index == 1) {
            ((PrintFuncImpl) target).secondFunc((int) args[0]);
        } else {
            throw new IllegalArgumentException("no such method whose index is " + index);
        }
        return null;
    }

    public static void main(String[] args) {
        PrintFuncImplFastClass fastClass = new PrintFuncImplFastClass();
        int index = fastClass.getIndex(new Signature("printFunc", "()V"));
        System.out.println(index);
        fastClass.invoke(index, new PrintFuncImpl(), new Object[0]);
    }
}
