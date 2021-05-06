package hard;

/*
   Runtime: 2ms
   Memory used: 40Mb
 */

public class MedianOfTwoSortedArray {
    public static void main(String[] args) {
        final int[] nums1 = {1,3};
        final int[] nums2 = {2};
        System.out.println(new MedianOfTwoSortedArray().findMedianSortedArrays(nums1, nums2));
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return traverseOne(nums2);
        }
        if (nums2.length == 0) {
            return traverseOne(nums1);
        }
        if (nums1[0] <= nums2[0]) {
            return traverseMergedList(nums1, nums2);
        } else {
            return traverseMergedList(nums2, nums1);
        }
    }

    public static double traverseOne(int[] a) {
        if (a.length == 1) {
            return (double) a[0];
        }
        if (a.length == 2) {
            return ((double) a[0] + (double) a[1])/2;
        }
        if (a.length%2==0) {
            return ((double) a[a.length/2] + (double) a[a.length/2-1])/2;
        } else {
            return (double) a[a.length/2];
        }
    }

    public static double traverseMergedList(int[] a, int[] b) {
        int i = 0;
        int k = 0;
        int l = 0;
        int temp = a[k];
        int lastTemp = 0;
        int mergedListLength = a.length+b.length;
        while (i < mergedListLength/2) {
            lastTemp = temp;
            if (k == a.length-1) {
                if (temp < b[l]) {
                    temp = b[l];
                }
                l++;
                i++;
                continue;
            }
            if (l == b.length) {
                k++;
                i++;
                if (temp < a[k]) {
                    temp = a[k];
                }
                continue;
            }
            if (a[k+1] <= b[l]) {
                i++;
                k++;
                temp = a[k];
            } else {
                temp = b[l];
                l++;
                i++;
            }
        }
        if (mergedListLength%2!=0) {
            return (double) temp;
        } else {
            return ((double) lastTemp + (double) temp)/2;
        }
    }
}
