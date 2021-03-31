package collections.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arrays = {1,9,5,2,7,4,3};
        arrays = InsertSort.sort(arrays);
        System.out.println(JSONObject.toJSONString(arrays));
    }

    /**
     * 从小到大排序
     * @param arrays
     * @return
     */
    private static int[] sort(int[] arrays){
        int length = arrays.length;
        if(length == 0){
            return null;
        }
        for(int i = 1; i < length; i++){
            for(int j = i; arrays[j] < arrays[j - 1]; j --){
                int temp = arrays[j-1];
                arrays[j-1] = arrays[j];
                arrays[j] = temp;
            }
        }
        return arrays;
    }
}
