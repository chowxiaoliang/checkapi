package collections.sort;

/**
 * 堆排序 时间复杂度平均为O(nlogn)
 */
public class PileSort {

    public static void sort(int []arr) {
        //1.构建大顶堆
        for(int i=arr.length/2-1;i>=0;i--) {
            //从最后一个一个非叶子结点i从下至上，从右至左调整结构
            adjustHeap(arr,i,arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            //将堆顶元素与末尾元素进行交换
            swap(arr,0,j);
            //重新对堆进行调整
            adjustHeap(arr,0,j);
        }
    }

    /**
     * 调整大顶堆
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int []arr,int i,int length) {
        //i是父节点
        while(2*i+1<length) {
            // k是子节点
            int k=i*2+1;
            //从i结点的左子结点开始，也就是2i+1处开始，使得k指向i的左右子节点中较大的一个
            // 如果不存在右子节点，就不用比较了
            if(k+1<length && arr[k]<arr[k+1]) {
                //如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            // 比较该较大节点与父节点
            if(arr[k] <arr[i]) {
                break;
            }
            //如果子节点大于父节点，将子节点和父节点交换
            swap(arr,i,k);
            // 更新父节点，调整下面的子树
            i = k;
        }
    }

    /**
     * 交换元素
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int []arr,int a ,int b) {
        int temp=arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
