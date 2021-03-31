package collections.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * 简单选择排序
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arrays = {1,9,5,2,7,4,3};
        arrays = SelectSort.sort(arrays);
        System.out.println(JSONObject.toJSONString(arrays));
    }

    /**
     * 选择排序，从小到大排列
     * 每一位依次和其后面所有的位进行比较
     * @param arrays
     * @return
     */
    private static int[] sort(int[] arrays){
        int length = arrays.length;
        if (length == 0){
            return null;
        }
        // 最后一个元素不需要排序
        for(int i = 0;i < length-1; i ++){
            for(int j = i+1;j<length;j++){
                if(arrays[i] > arrays[j]){
                    int temp = arrays[i];
                    arrays[i] = arrays[j];
                    arrays[j] = temp;
                }
            }
        }
        return arrays;
    }
}
