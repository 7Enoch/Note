package Mise;

import java.util.ArrayList;
import java.util.List;

public class CharSearch {
    public static void main(String[] args) {
        String all = "你好，我是你爹，你可真不错";
        String[] discoloredText = new String[] { "你好", "你爹", "真不错" };
        SearchChar(all, discoloredText);
    }

    // 全部字符 寻找字符
    // 将全部的部分字符的角标n-m
    public static void SearchChar(String allText, String[] discoloredText) {
        List<charCount> list = new ArrayList<charCount>();
        for (int i = 0; i < discoloredText.length; i++) {
            int start = allText.indexOf(discoloredText[i]);
            int end = start + discoloredText[i].length() - 1;
            list.add(new charCount(start, end));
        }
        for (charCount list2 : list) {
            System.out.println(list2.start + " " + list2.end);
        }

    }

}

class charCount {
    int start;
    int end;

    public charCount(int start, int end) {
        this.start = start;
        this.end = end;
    }

}