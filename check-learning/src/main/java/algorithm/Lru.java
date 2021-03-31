package algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 最近最少使用LRU
 * 原理：可以使用栈或链表
 * 获取数据，如果不存在则返回-1，如果存在则将该key移动到链表或栈的最开始处
 */
public class Lru {

    private int size;

    private Stack<String> stack;

    private Map<String, Integer> map;

    public Lru(int size){
        this.size = size;
        this.stack = new Stack<>();
        this.map = new HashMap<>(size);
    }

    public Integer getValue(String key){
        if (! stack.contains(key)){
            return -1;
        }else {
            // 先删除
            stack.remove(key);
            // 再入栈
            stack.push(key);
        }
        return map.get(key);
    }

    public void putValue(String key, Integer value){
        stack.remove(key);
        if (stack.size() == size){
            stack.remove(0);
        }
        stack.push(key);
        map.put(key,value);
    }
}
