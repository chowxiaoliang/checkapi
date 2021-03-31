package collections.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * 冒泡排序
 */
public class BubblingSort {

    public static void main(String[] args) {
        int[] arrays = {1,9,5,2,7,4,3,100,0};
        arrays = BubblingSort.sort(arrays);
        System.out.println(JSONObject.toJSONString(arrays));
    }

    /**
     * 从小到大排列
     * 相邻的两位进行比较
     * @param arrays
     * @return
     */
    private static int[] sort(int[] arrays){
        int length = arrays.length;
        if(length == 0){
            return null;
        }
        for(int i = 0;i<length-1;i++){
            for(int j=0;j<length-1-i;j++){
                if(arrays[j] > arrays[j+1]){
                    int temp = arrays[j];
                    arrays[j] = arrays[j+1];
                    arrays[j+1] = temp;
                }
            }
        }
        return arrays;
    }
}
