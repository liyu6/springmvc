package demo;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class IsEmptyDemo {
    /**
     * list!=null跟!list.isEmpty()有什么区别?
     * 这就相当与，你要喝水，
     * 前面就是判断是不是连水杯都没有，
     * 后面就是判断水杯里面没有水，
     * 连盛水的东西都没有，
     * 这个水从何而来？
     * 所以一般的判断是
     * if(list!=null && !list.isEmpty()){
     * 这个里面取list中的值
     * }else{
     * 做其他处理
     * }
     * ---------------------
     */
    public static void main(String[] args) {
        List<String> list=new ArrayList<String>();
//        list=null;
        if (list.isEmpty()){
            System.out.println("[isEmpty]--list集合为空！");
        }
        if (list!=null&&list.size()==0){
            System.out.println("list集合为空！");
        }
    }
}

