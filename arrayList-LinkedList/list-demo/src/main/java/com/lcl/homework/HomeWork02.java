package com.lcl.homework;

import java.util.*;

public class HomeWork02 {

    /**
     * 子域名访问计数
     *
     * @param cpdomains
     * @return
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String cpdomain : cpdomains) {
            String[] cpdoainArr = cpdomain.split(" ");
            int count = Integer.parseInt(cpdoainArr[0]);
            String[] tempArr = cpdoainArr[1].split("\\.");
            String key = "";
            for (int i = tempArr.length - 1; i >= 0; i--) {
                key = i == tempArr.length - 1 ? tempArr[i] : tempArr[i] + "." + key;
                if (!map.containsKey(key)) {
                    map.put(key, count);
                } else {
                    map.put(key, map.get(key) + count);
                }
            }
        }
        List<String> ans = new ArrayList<String>();
        for (String key : map.keySet()) {
            ans.add(map.get(key) + " " + key);
        }
        return ans;
    }


    /**
     * 数组的度
     *
     * @param nums
     * @return
     */
    public int findShortestSubArray(int[] nums) {
        // key为数值，int[]数组长度为3，分别是数字个数、当前数值的第一个值的位置、最后一个值的位置
        Map<Integer, Integer[]> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Integer[] arr = map.get(nums[i]);
                arr[0] = arr[0] + 1;
                arr[2] = i;
                map.put(nums[i], arr);
            } else {
                Integer[] arr = new Integer[]{1, i, i};
                map.put(nums[i], arr);
            }
        }
        int ansCount = 0;
        int minLen = 0;
        for (Integer num : map.keySet()) {
            Integer[] arr = map.get(num);
            if (arr[0] > ansCount) {
                ansCount = arr[0];
                minLen = arr[2] - arr[1] + 1;
            } else if (arr[0] == ansCount && arr[2] - arr[1] < minLen) {
                minLen = arr[2] - arr[1] + 1;
            }
        }
        return minLen;

    }


    public static void main(String[] args) {
        HomeWork02 homeWork02 = new HomeWork02();
        homeWork02.permute(new int[]{1, 2, 3});
    }

    public List<List<Integer>> permute(int[] nums) {
        List<Boolean> flagList = new ArrayList();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            flagList.add(false);
        }
        List<List<Integer>> ans = new ArrayList();
        List<Integer> list = new ArrayList();
        permuteNew(nums, 0, len, list, flagList, ans);
        return ans;
    }

    private void permuteNew(int[] nums, int i, int length, List<Integer> list, List<Boolean> flagList, List<List<Integer>> ans) {
        if (i == length || list.size() == length) {
            List<Integer> temp = new ArrayList(list);
            ans.add(temp);
            return;
        }
        for (int index = 0; index < length; index++) {
            if (!flagList.get(index)) {
                list.add(nums[index]);
                flagList.set(index, true);
                permuteNew(nums, i + 1, length, list, flagList, ans);
                list.remove(list.size() - 1);
                flagList.set(index, false);
            }
        }
    }


    /**
     * 合并K个升序链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }

    public ListNode mergeKLists(ListNode[] lists, int start, int end) {
        if (end - start == 0) {
            return lists[start];
        }
        if (end - start == 1) {
            return mergeKLists(lists[start], lists[start + 1]);
        }
        int mid = start + (end - start) / 2;
        ListNode startNode = mergeKLists(lists, start, mid);
        ListNode endNode = mergeKLists(lists, mid + 1, end);
        return mergeKLists(startNode, endNode);
    }

    public ListNode mergeKLists(ListNode aNode, ListNode bNode) {
        ListNode protect = new ListNode();
        ListNode head = protect;
        while (aNode != null || bNode != null) {
            ListNode nextNode = null;
            if (aNode == null || (bNode != null && bNode.val < aNode.val)) {
                nextNode = new ListNode(bNode.val);
                bNode = bNode.next;
            } else {
                nextNode = new ListNode(aNode.val);
                aNode = aNode.next;
            }
            head.next = nextNode;
            head = nextNode;
        }
        return protect.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    /**
     * 全排列2
     */
    class Solution {
        int len;
        boolean[] flagArr;

        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> ans = new ArrayList();
            List<Integer> list = new ArrayList();
            len = nums.length;
            flagArr = new boolean[len];
            Arrays.sort(nums);
            permuteUnique(ans, list, nums, 0);
            return ans;
        }

        public void permuteUnique(List<List<Integer>> ans, List<Integer> list, int[] nums, int index) {
            if (list.size() == len) {
                List<Integer> subList = new ArrayList(list);
                ans.add(subList);
                return;
            }
            for (int i = 0; i < len; i++) {
                if (flagArr[i] || (i > 0 && nums[i] == nums[i - 1]) && flagArr[i - 1]) {
                    continue;
                }
                list.add(nums[i]);
                flagArr[i] = true;
                permuteUnique(ans, list, nums, index + 1);
                list.remove(list.size() - 1);
                flagArr[i] = false;
            }
        }
    }

}
