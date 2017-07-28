package com.java.sort;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
		int[] array = {15,8,20,13,15,7,9,30,23};
		int n = array.length;
		for(int i=0;i<=n-1;i++){
			for(int j=0;j<n-i-1;j++){
				if(array[j]>array[j+1]){
					int temp = array[j+1];
					array[j+1] = array[j];
					array[j] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(array));
	}
}
