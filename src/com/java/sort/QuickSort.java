package com.java.sort;

import java.util.Arrays;

public class QuickSort {
	//快速排序
	public int getMiddle(int[] a,int low,int high){
		int temp = a[low];
		while(low<high){
			while(low<high && a[high]>=temp){
				high--;
			}
			a[low] = a[high];
			while(low<high && a[low]<=temp){
				low++;
			}
			a[high] = a[low];
		}
		a[low] = temp;
		return low;
	}
	
	public void sort(int[] a,int low,int high){
		if(low<high){
			int middle = getMiddle(a, low, high);
			sort(a,low,middle-1);
			sort(a,middle+1,high);
		}
	}
	
	public void quickSort(int[] a){
		sort(a, 0, a.length-1);
	}
	
	public static void main(String[] args) {
		int[] array = {15,8,20,13,15,7,9,30,23};
		QuickSort q = new QuickSort();
		q.quickSort(array);
		System.out.println(Arrays.toString(array));
		String a = "abc";
		String b = new String("abc");
		System.out.println(a==b);
		System.out.println(a.equals(b));
		int h = a.hashCode();
		System.out.println(h);
		System.out.println(h>>>16);
		int h1 = h^(h>>>16);
		System.out.println(h1);
		System.out.println(10 & h1);
	}
}
