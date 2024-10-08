package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LeetCodeTests {


    @ParameterizedTest
    @MethodSource("romanDigitProvider")
    public void romanToIntTest(String s) {
        System.out.println(romanToInt(s));

    }

    static Stream<Arguments> romanDigitProvider() {
        return Stream.of(
//                Arguments.of("MMMCDXXVII"),
                Arguments.of("MMMCMLXXVII", "3977"),
                Arguments.of("MMXXV", "2025"),
                Arguments.of("DCXXI", "621"),
                Arguments.of("MDCCCLXXXIV", "1884"),
                Arguments.of("MMMCDXXIX", "3429"));
    }

    @ParameterizedTest
    @MethodSource("diffArraysProvider")
    public void diffArraysTest(int[] nums1, int[] nums2) {
        System.out.println(findDifference(nums1, nums2));

    }

    static Stream<Arguments> diffArraysProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, new int[]{2, 4, 6}, List.of(1, 3), List.of(4, 6)));
    }





    public String reverseVowels(String s) {
        char bf;
        char[] ca = s.toCharArray();
        int firstVowelIndex;
        for (int left = 0, right = ca.length - 1; left < right; left++, right--) {
            while (!isVowel(ca[left]) && left < right)
                left++;
            firstVowelIndex = left;
            System.out.println(firstVowelIndex);
            while (!isVowel(ca[right]) && left < right)
                right--;
            if (ca[left] != ca[right] && left != firstVowelIndex) {
                bf = ca[left];
                ca[left] = ca[right];
                ca[right] = bf;
            }

        }
        return new String(ca);
    }


    boolean isVowel(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'Y');
    }



    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        var set = new HashSet<Integer>(Arrays.stream(nums1).boxed().toList());
        var list = new ArrayList<List<Integer>>();
        list.add(new ArrayList<>());
        list.add(new ArrayList<>());
        for (int j : nums2) {
            if (set.add(j))
                list.get(1).add(j);
        }
        set.clear();
        set.addAll(Arrays.stream(nums2).boxed().toList());
        for (int j : nums1) {
            if (set.add(j))
                list.get(0).add(j);
        }
        list.get(1).addAll(set);
        return list;
    }

    public int romanToInt(String s) {
        // MMM CM L XX V II   3977
        int num = 0;
        int i = 0;
        if (s.charAt(i) == 'M') {
            num += 1000;
            if (i < s.length() - 1)
                i++;
            else
                return num;
            while (s.charAt(i) == 'M') {
                num += 1000;
                if (i < s.length() - 1)
                    i++;
                else
                    return num;
            }
        }
        if (s.startsWith("CM", i)) {
            num += 900;
            if (i < s.length() - 2)
                i += 2;
            else
                return num;
        }
        if (s.startsWith("CD", i)) {
            num += 400;
            if (i < s.length() - 2)
                i += 2;
            else
                return num;
        }
        if (s.charAt(i) == 'D') {
            num += 500;
            if (i < s.length() - 1)
                i++;
            else
                return num;
        }
        if (s.charAt(i) == 'C') {
            num += 100;
            if (i < s.length() - 1)
                i++;
            else
                return num;
            while (s.charAt(i) == 'C') {
                num += 100;
                if (i < s.length() - 1)
                    i++;
                else
                    return num;
            }
        }

        if (s.startsWith("XC", i)) {
            num += 90;
            if (i < s.length() - 2)
                i += 2;
            else
                return num;
        }

        if (s.startsWith("XL", i)) {
            num += 40;
            if (i < s.length() - 2)
                i += 2;
            else
                return num;
        }
        if (s.charAt(i) == 'L') {
            num += 50;
            if (i < s.length() - 1)
                i++;
            else
                return num;
        }
        if (s.charAt(i) == 'X') {
            num += 10;
            if (i < s.length() - 1)
                i++;
            else
                return num;
            while (s.charAt(i) == 'X') {
                num += 10;
                if (i < s.length() - 1)
                    i++;
                else
                    return num;
            }
        }
        if (s.startsWith("IX", i)) {
            num += 9;
            return num;
        }
        if (s.startsWith("IV", i)) {
            num += 4;
            return num;
        }
        if (s.startsWith("V", i)) {
            num += 5;
            if (i < s.length() - 1)
                i++;
            else
                return num;
        }
        if (s.charAt(i) == 'I') {
            num += 1;
            while (i < s.length() - 1) {
                num += 1;
                i++;
            }
        }
        return num;
    }

    @ParameterizedTest
    @MethodSource("mergeArrayProvider")
    public void mergeArrayTest(int[] nums1, int m, int[] nums2, int n) {
        mergeArray(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    static Stream<Arguments> mergeArrayProvider() {
        return Stream.of(Arguments.of(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3));
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 0};
        mergeArray(nums1, 1, new int[]{1}, 1);
        System.out.println(Arrays.toString(nums1));
    }

    public static void mergeArray(int[] nums1, int m, int[] nums2, int n) {
        int j = 0;
        int k, rem;
        for (int i = 0; i < nums1.length && j < nums2.length; i++) {
            if (nums1[i] > nums2[j]) {

                // [1,2,3,0,0,0]
                // [2,5,6]
                // [1,2,2,3,5,6]
                k = i + 1;
                rem = nums1[i]; // rem 3
                nums1[i] = nums2[j]; // insert 2 instead 3
                j++;
                while (k < nums1.length) {
                    nums1[k] = rem;
                    if (k < nums1.length - 1) // insert 3
                        rem = nums1[k + 1]; // rem next

                    k++;
                }
            } else if (nums1[i] == 0) {
                nums1[i] = nums2[j];
                j++;
            }
        }
    }


    // дан массив с любыми числами
    // k - размер подмассима
    // найти массив с k элементов, среди которых разница менжду максимальным
    // и минимальным элементом будет минимальная, вывести разницу
}
