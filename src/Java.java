import com.sun.source.tree.Tree;

import javax.sound.sampled.EnumControl;
import java.lang.reflect.Array;
import java.util.*;

public class Java {
    public static void main(String[] args) {
        System.out.println();
    }
    public class Solution {
        //1. Two Sum
        public int[] twoSum(int[] nums, int target) {
            HashMap <Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }
            for (int i = 0; i < nums.length; i++) {
                if (map.get(target-nums[i]) != null && i != map.get(target-nums[i])) {
                    return new int[]{i, map.get(target-nums[i])};
                }
            }
            return null;
        }
        //2. Add Two Numbers
        public class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1.next == null || l2.next == null) {
                if (l1.val + l2.val >= 10) {
                    if (l1.next == null && l2.next == null) {
                        return new ListNode(l1.val + l2.val - 10, new ListNode(1));
                    }
                    if (l1.next == null) {
                        l2.next.val++;
                        return new ListNode(l1.val + l2.val - 10, addTwoNumbers(new ListNode(), l2.next));
                    }
                    l1.next.val++;
                    return new ListNode(l1.val + l2.val - 10, addTwoNumbers(l1.next, new ListNode()));
                }
                if (l1.next == null && l2.next == null) {
                    return new ListNode(l1.val + l2.val);
                }
                if (l1.next == null) {
                    return new ListNode(l1.val + l2.val, addTwoNumbers(new ListNode(), l2.next));
                }
                return new ListNode(l1.val + l2.val, addTwoNumbers(l1.next, new ListNode()));
            }
            if (l1.val + l2.val >= 10) {
                l1.next.val++;
                return new ListNode(l1.val + l2.val - 10, addTwoNumbers(l1.next, l2.next));
            }
            return new ListNode(l1.val + l2.val, addTwoNumbers(l1.next, l2.next));
        }
    }
    //11. Container With Most Water
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length-1;
        while (left < right) {
            max = Math.max((right-left) * Math.min(height[left], height[right]), max);
            if (height[left] < height [right]) {
                left++;
            } else right--;
        }
        return max;
    }
    //15. 3Sum
    public List<List<Integer>> threeSum(int[] nums) {
        HashSet <Integer> targets = new HashSet<>();
        HashSet <Integer> seen = new HashSet<>();
        HashSet <String> added = new HashSet<>();
        List<List<Integer>> ans = new ArrayList<>();
        int target;

        for (int i = 0; i < nums.length; i++) {
            seen.clear();
            if (targets.contains(nums[i])) continue;
            targets.add(nums[i]);
            target = nums[i];
            for (int j = 0; j < nums.length; j++) {
                if (i == j) continue;
                if (seen.contains(- target - nums[j])) {
                    ArrayList <Integer> temp = new ArrayList<>();
                    int [] sort = new int [3];
                    sort[0] = target;
                    sort[1] = nums[j];
                    sort[2] = - target - nums[j];
                    Arrays.sort(sort);
                    String s = String.format("%d %d %d", sort[0], sort[1], sort[2]);

                    if (!added.contains(s)) {
                        temp.add(target);
                        temp.add(nums[j]);
                        temp.add(- target - nums[j]);
                        ans.add(temp);
                    }

                    added.add(s);
                }
                seen.add(nums[j]);
            }
        }
        return ans;
    }
    //151. Reverse Words in a String
    public String reverseWords(String s) {
        String [] words = s.split(" ");
        Collections.reverse(Arrays.asList(words));
        StringBuilder sBuilder = new StringBuilder();
        for (String word: words) {
            if (word.isEmpty()) continue;
            sBuilder.append(word.replace(" ", "")).append(" ");
        }
        return sBuilder.toString().trim();
    }
    //238. Product of Array Except Self
    public int[] productExceptSelf(int[] nums) {
        int [] output = new int[nums.length];
        int suffix = 1;

        output[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            output[i] = output[i-1] * nums[i-1];
        }

        for (int i = nums.length - 1; i >=0; i--) {
            output[i] *= suffix;
            suffix *= nums[i];
        }
        return output;
    }
    //283. Move Zeroes
    public static void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }
    //334. Increasing Triplet Subsequence
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int i : nums) {
            if (i <= first) {
                first = i;
            } else if (i <= second) {
                second = i;
            } else return true;
        }
        return false;
    }
    //345. Reverse Vowels of a String
    public String reverseVowels(String s) {
        int left = 0;
        int right = s.length()-1;
        char [] chars = s.toCharArray();

        while (left < right) {
            boolean leftIsVowel = isVowel(Character.toLowerCase(chars[left]));
            boolean rightIsVowel = isVowel(Character.toLowerCase(chars[right]));
            if (leftIsVowel && rightIsVowel) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            } else if (leftIsVowel) {
                right--;
            } else if (rightIsVowel) {
                left++;
            } else {
                left++; right--;
            }
        }
        return new String(chars);
    }
    //392. Is Subsequence
    public boolean isSubsequence(String s, String t) {
        int j = 0;
        if (s.isEmpty()) {
            return true;
        }
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(j)) {
                j++;
            }
        }
        return j == s.length();
    }
    //443. String Compression
    public int compress(char[] chars) {
        int index = 0;
        int i = 0;
        while (i < chars.length) {
            char current = chars[i];
            int count = 0;

            while (i < chars.length && chars[i] == current) {
                i++;
                count++;
            }

            chars[index++] = current;

            if (count > 1) {
                int start = index;

                while (count > 0) {
                    chars[index++] = (char) ('0' + count % 10);
                    count /= 10;
                }

                int end = index - 1;
                while (start < end) {
                    char temp = chars[start];
                    chars[start] = chars[end];
                    chars[end] = temp;
                    start++;
                    end--;
                }
            }
        }
        return index;
    }
    //485. Max Consecutive Ones
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) count++;
            else {
                max = Math.max(max, count);
                count = 0;
            }
        }
        return Math.max(max, count);
    }
    //605. Can Place Flowers
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) return true;
        for (int i = 0; i < flowerbed.length; i++) {
            try {
                if (flowerbed[i-1] == 0 && flowerbed[i+1] == 0 && flowerbed[i] != 1) {
                    flowerbed[i] = 1;
                    n--;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if (flowerbed.length == 1) return flowerbed[0] == 0;
                else if (i == 0 && flowerbed[i+1] == 0 && flowerbed[i] != 1) {
                    flowerbed[i] = 1;
                    n--;
                }
                else if (i == flowerbed.length - 1 && flowerbed[i-1] == 0 && flowerbed[i] != 1) {
                    flowerbed[i] = 1;
                    n--;
                }
            }
        }
        return n <= 0;
    }
    //643. Maximum Average Subarray I
    public double findMaxAverage(int[] nums, int k) {
        int acc = 0;
        int max;
        for (int i = 0; i < k; i++) {
            acc += nums[i];
        }
        max = acc;

        for (int i = 1; i < nums.length-k+1; i++) {
            acc -= nums[i-1];
            acc += nums[i+k-1];
            max = Math.max(acc, max);
        }
        return (double) max/k;
    }
    //724. Find Pivot Index
    public int pivotIndex(int[] nums) {
        int total = 0;
        int leftSum = 0;
        for (int i: nums) {
            total += i;
        }
        for (int i = 0; i < nums.length; i++) {
            if (total - leftSum - nums[i] == leftSum) return i;
            leftSum += nums[i];
        }
        return -1;
    }
    //735. Asteroid Collision
    public int[] asteroidCollision(int[] asteroids) {
        Stack <Integer> ans = new Stack<>();
        int [] ansArray;
        for (int i = 0; i < asteroids.length; i++) {
            if (ans.isEmpty() || ans.peek() < 0) {
                ans.add(asteroids[i]);
            } else {
                if (asteroids[i] > 0) {
                    ans.add(asteroids[i]);
                } else {
                    while (!ans.isEmpty() && ans.peek() > 0 && Math.abs(asteroids[i]) > ans.peek()) {
                        ans.pop();
                    }
                    if (!ans.isEmpty() && Math.abs(asteroids[i]) == ans.peek()) ans.pop();
                    else if (ans.isEmpty() || ans.peek() < 0) ans.add(asteroids[i]);
                }
            }
        }
        ansArray = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ansArray[i] = ans.get(i);
        }
        return ansArray;
    }
    //933. Number of Recent Calls
    class RecentCounter {
        Queue <Integer> pingQueue;
        int ans;
        public RecentCounter() {
            pingQueue = new LinkedList<>();
            ans = 0;
        }

        public int ping(int t) {
            pingQueue.add(t);
            while (pingQueue.peek() < t-3000) {
                pingQueue.poll();
            }
            return pingQueue.size();
        }
    }
    //1004. Max Consecutive Ones III
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int ans = 0;
        int zeroes = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeroes++;

            while (zeroes > k) {
                if (nums[left] == 0) zeroes--;
                left++;
            }

            ans = Math.max(ans, right-left + 1);
        }
        return ans;
    }

    //1071. Greatest Common Divisor of Strings
    public String gcdOfStrings(String str1, String str2) {
        String longest = (str1.length() > str2.length()) ? str1 : str2;
        String shortest = (str1.length() > str2.length()) ? str2 : str1;
        for (int i = shortest.length(); i > 0; i--) {
            if (longest.length() % i == 0 && shortest.length() % i == 0) {
                String sub = shortest.substring(0, i);
                StringBuilder builder = new StringBuilder();
                builder.append(sub.repeat(shortest.length() / i));
                if (!builder.toString().equals(shortest)) continue;
                builder.append(sub.repeat(longest.length() / i - shortest.length() / i));
                if (builder.toString().equals(longest)) return sub;
            }
        }
        return "";
    }
    //1207. Unique Number of Occurrences
    public boolean uniqueOccurrences(int[] arr) {
        HashMap <Integer, Integer> occurrences = new HashMap<>();
        HashSet <Integer> checkContains = new HashSet<>();

        for (int i: arr) {
            if (!occurrences.containsKey(i)) occurrences.put(i, 0);
            else occurrences.replace(i, occurrences.get(i) + 1);
        }

        for (int i: occurrences.values()) {
            if (!checkContains.contains(i)) checkContains.add(i);
            else return false;
        }
        return true;
    }
    //1431. Kids With the Greatest Number of Candies
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        LinkedList <Boolean> result = new LinkedList<>();
        int max = -1;
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] > max) max = candies[i];
        }
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] + extraCandies >= max) result.add(true);
            else result.add(false);
        }
        return result;
    }
    //1456. Maximum Number of Vowels in a Substring of Given Length
    public int maxVowels(String s, int k) {
        Queue <Character> substring = new LinkedList<>();
        int vowelCount = 0;
        int highest = 0;
        for (int i = 0; i < k; i++) {
            char curr = s.charAt(i);
            if (isVowel(curr)) {
                vowelCount++;
            }
            substring.add(curr);
        }
        highest = vowelCount;
        for (int i = k; i < s.length(); i++) {
            if (isVowel(substring.poll())) vowelCount--;
            char curr = s.charAt(i);
            if (isVowel(curr)) {
                vowelCount++;
            }
            substring.add(curr);
            highest = Math.max(highest, vowelCount);
        }
        return highest;
    }
    public boolean isVowel(char curr) {
        return curr == 'a' || curr == 'e' || curr == 'i' || curr == 'o' || curr == 'u';
    }
    //1470. Shuffle the Array
    public int[] shuffle(int[] nums, int n) {
        int [] ans = new int [nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                ans[i] = nums[i/2];
            } else ans[i] = nums[i/2 + n];
        }
        return ans;
    }
    //1493. Longest Subarray of 1's After Deleting One Element
    public int longestSubarray(int[] nums) {
        int left = 0;
        int zeroes = 0;
        int largest = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeroes++;
            while (zeroes > 1) {
                if (nums[left] == 0) {
                    zeroes--;
                }
                left++;
            }
            largest = Math.max(largest, right - left);
        }
        return largest;
    }
    //1657. Determine if Two Strings Are Close
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        int [] letters1 = new int[26];
        int [] letters2 = new int[26];


        for (char a: word1.toCharArray()) {
            letters1[a - 'a']++;
        }
        for (char a: word2.toCharArray()) {
            letters2[a - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if ((letters1[i] == 0) != (letters2[i] == 0)) return false;
        }
        Arrays.sort(letters1);
        Arrays.sort(letters2);


        return Arrays.equals(letters1, letters2);
    }
    //1679. Max Number of K-Sum Pairs
    public int maxOperations(int[] nums, int k) {
        HashMap <Integer, Integer> valueToCount = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            int num2 = k - num;

            if (valueToCount.getOrDefault(num2, 0) > 0) {
                ans++;
                valueToCount.put(num2, valueToCount.get(num2) -1);
            } else {
                valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
            }
        }
        return ans;
    }
    //1732. Find the Highest Altitude
    public int largestAltitude(int[] gain) {
        int largest = 0;
        int altitude = 0;
        for (int i = 0; i < gain.length; i++) {
            altitude += gain[i];
            largest = Math.max(altitude, largest);
        }
        return largest;
    }
    //1768. Merge Strings Alternately
    public String mergeAlternately(String word1, String word2) {
        StringBuilder str = new StringBuilder();
        int length = Math.min(word1.length(), word2.length());
        for (int i = 0; i < length; i++) {
            str.append(word1.charAt(i));
            str.append(word2.charAt(i));
        }
        if (word1.length() > word2.length()) {
            str.append(word1.substring(length));
        } else if (word1.length() < word2.length()) {
            str.append(word2.substring(length));
        }
        return str.toString();
    }
    //1929. Concatenation of Array
    public int[] getConcatenation(int[] nums) {
        int [] ans = new int [nums.length * 2];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = nums[i % nums.length];
        }
        return ans;
    }
    //2215. Find the Difference of Two Arrays
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashSet <Integer> set1 = new HashSet<>();
        HashSet <Integer> set2 = new HashSet<>();
        List<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> ans1 = new ArrayList<>();
        ArrayList<Integer> ans2 = new ArrayList<>();
        ans.add(ans1);
        ans.add(ans2);

        for (int i: nums1) set1.add(i);
        for (int i: nums2) set2.add(i);

        for (int i: nums1) {
            if (!set2.contains(i)) {
                ans1.add(i);
                set2.add(i);
            }
        }
        for (int i: nums2) {
            if (!set1.contains(i)) {
                ans2.add(i);
                set1.add(i);
            }
        }

        return ans;
    }
    //2265. Count Nodes Equal to Average of Subtree
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }
    public int averageOfSubtree(TreeNode root) {
        if (root == null) return 0;
        if (sumOfSubtree(root)/nodesOfSubtree(root) == root.val) return 1 + averageOfSubtree(root.left) + averageOfSubtree(root.right);
        return averageOfSubtree(root.left) + averageOfSubtree(root.right);
    }
    public int sumOfSubtree(TreeNode root) {
        if (root == null) return 0;
        return root.val + sumOfSubtree(root.left) + sumOfSubtree(root.right);
    }
    public int nodesOfSubtree(TreeNode root) {
        if (root == null) return 0;
        return 1 + nodesOfSubtree(root.left) + nodesOfSubtree(root.right);
    }
    //2352. Equal Row and Column Pairs
    public int equalPairs(int[][] grid) {
        HashMap <String, Integer> columns = new HashMap<>();
        StringBuilder temp;
        int ans = 0;
        for (int[] value : grid) {
            temp = new StringBuilder();
            for (int i : value) {
                temp.append(i).append(" ");
            }
            columns.put(temp.toString(), columns.getOrDefault(temp.toString(), 0) + 1);
        }
        for (int c = 0; c < grid[0].length; c++) {
            temp = new StringBuilder();
            for (int[] ints : grid) {
                temp.append(ints[c]).append(" ");
            }
            if (columns.containsKey(temp.toString())) ans += columns.get(temp.toString());
        }
        return ans;
    }
    //2390. Removing Stars From a String
    public String removeStars(String s) {
        Stack <Character> letters = new Stack<>();
        StringBuilder ans = new StringBuilder();
        int starCount = 0;

        for (char c: s.toCharArray()) {
            letters.add(c);
        }
        while (!letters.isEmpty()) {
            char curr = letters.pop();
            if (curr == '*') starCount++;
            else if (starCount > 0) starCount--;
            else ans.append(curr);
        }
        ans.reverse();
        return ans.toString();
    }
    //3871. Count Commas in Range II
    public long countCommas(long n) {
        long count = 0;
        String num = "" + n;
        for (long i = 1000; i <= n; i *= 1000) {
            count += n - i + 1;
        }
        return count;
    }
    //3903. Smallest Stable Index I
    //3904. Smallest Stable Index II
    public int firstStableIndex(int[] nums, int k) {
        int max = -1;
        int [] minValues = new int [nums.length];
        minValues[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >=0; i--) {
            minValues[i] = Math.min(nums[i], minValues[i + 1]);
        }
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            if (max - minValues[i] <= k) return i;
        }
        return -1;
    }

}