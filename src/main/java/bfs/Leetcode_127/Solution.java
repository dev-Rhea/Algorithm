package bfs.Leetcode_127;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);

        if (!set.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        set.remove(beginWord);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String now = queue.poll();

                if (now.equals(endWord)) {
                    return level;
                }

                char[] chars = now.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char origin = chars[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == origin) {
                            continue;
                        }

                        chars[j] = c;
                        String next = new String(chars);

                        if (set.contains(next)) {
                            set.remove(next);
                            queue.add(next);
                        }
                    }

                    chars[j] = origin;
                }
            }
            level++;
        }
        return 0;
    }
}
