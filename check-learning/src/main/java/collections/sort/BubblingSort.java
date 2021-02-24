package collections.sort;

import com.alibaba.fastjson.JSONObject;

public class BubblingSort {

    public static void main(String[] args) {
        int[] arrays = {1,9,5,2,7,4,3};
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
            for(int j=i+1;j<length;j++){
                if(arrays[j] < arrays[i]){
                    int temp = arrays[i];
                    arrays[i] = arrays[j];
                    arrays[j] = temp;
                }
            }
        }
        return arrays;
    }
}
