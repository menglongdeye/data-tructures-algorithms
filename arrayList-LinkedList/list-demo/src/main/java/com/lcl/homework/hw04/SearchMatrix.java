package com.lcl.homework.hw04;

/**
 * 搜索二维矩阵
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowcount = matrix.length;
        int colcount = matrix[0].length;
        int end = rowcount*colcount - 1;
        int start = 0;
        while(start <= end){
            int mid = (start + end)/2;
            int row = mid/colcount;
            int col = mid%colcount;
            if(matrix[row][col] > target){
                end = mid -1;
            }else if(matrix[row][col] < target){
                start = mid+1;
            }else{
                return true;
            }
        }
        return false;
    }
}
