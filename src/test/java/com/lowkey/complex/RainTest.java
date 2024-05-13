package com.lowkey.complex;

public class RainTest {
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(rain(height));
    }

    public static long rain(int[] height) {
        //定义左侧最大值,初始值为0
        int leftMaxValue = 0;
        //初始就把最左边当作中间值计算
        int midIndex = 0;
        //定义最右侧下标
        int rightIndex = height.length - 1;
        //计算雨水总量
        int sum = 0;
        //定义最右侧最大值
        int rightMaxValue = 0;//定义最大值为该数组的第一个数
        //定义中间值
        int midValue;//定义最大值为该数组的第一个数
        //定义最左侧最大值及最右侧最大值中小的那个值
        int leftRightMaxValue;
        //遍历循环数组，找出最右侧最大值
        for (int j : height) {
            if (rightMaxValue < j) {
                rightMaxValue = j;
            }
        }

        //循环遍历数组找出中间那个可以装雨水的值并累加
        while (midIndex < rightIndex) {
            midValue = height[midIndex];
            leftRightMaxValue = Math.min(rightMaxValue, leftMaxValue);
            int result = leftRightMaxValue - midValue;
            if (result > 0) {
                sum += result;
            }

            //当中间值比左侧最大值大，则将左侧最大值置为当前中间值
            if (midValue > leftMaxValue) {
                leftMaxValue = midValue;
            }

            //当中间值比右侧最大值大，则将右侧最大值置为0，重新计算右侧最大值
            if (midValue == rightMaxValue) {
                rightMaxValue = 0;
                for (int i = midIndex + 1; i < height.length; i++) {
                    if (rightMaxValue < height[i]) {
                        rightMaxValue = height[i];
                    }
                }
            }
            //中间值加1继续循环
            midIndex++;
        }
        return sum;
    }
}
