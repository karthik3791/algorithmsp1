package com.karthik.java.greedy;

import java.util.*;

public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        int[] freqMap = new int[26];
        for (int i = 0; i < tasks.length; i++)
            freqMap[tasks[i] - 'A'] += 1;
        PriorityQueue<Character> pq = new PriorityQueue<>((c1, c2) -> freqMap[c2 - 'a'] == freqMap[c1 - 'a'] ? c1 - c2 : freqMap[c2 - 'a'] - freqMap[c1 - 'a']);
        for (int i = 0; i < 26; i++)
            if (freqMap[i] > 0)
                pq.offer((char) (i + 'A'));

        int count = 0;
        while (!pq.isEmpty()) {
            int k = n + 1;
            List<Character> tempList = new ArrayList<>();
            while (k > 0 && !pq.isEmpty()) {
                Character c = pq.poll();
                freqMap[c - 'A'] -= 1;
                if (freqMap[c - 'A'] > 0)
                    tempList.add(c);
                count++;
                k--;
            }
            pq.addAll(tempList);
            if (pq.isEmpty())
                break;
            count += k;
        }
        return count;
    }


    public int leastInterval2(char[] tasks, int n) {
        Map<Character, Integer> taskFreqMap = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            taskFreqMap.put(tasks[i], taskFreqMap.getOrDefault(tasks[i], 0) + 1); // map key is TaskName, and value is number of times to be executed.
        }
        PriorityQueue<Map.Entry<Character, Integer>> q = new PriorityQueue<>( //frequency sort
                (a, b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey());
        q.addAll(taskFreqMap.entrySet());
        int count = 0;
        while (!q.isEmpty()) {
            int k = n + 1;
            List<Map.Entry> tempList = new ArrayList<>();
            while (k > 0 && !q.isEmpty()) {
                Map.Entry<Character, Integer> top = q.poll(); // most frequency task
                top.setValue(top.getValue() - 1); // decrease frequency, meaning it got executed
                tempList.add(top); // collect task to add back to queue
                k--;
                count++; //successfully executed task
            }

            for (Map.Entry<Character, Integer> e : tempList) {
                if (e.getValue() > 0) q.add(e); // add valid tasks
            }
            //When all tasks are completed, we must not add the idle time over to the count.
            if (q.isEmpty()) break;
            count = count + k;
        }
        return count;
    }

    public int leastIntervalAfterLotsOfStruggle(char[] tasks, int n) {
        Map<Character, Integer> taskFreqMap = new HashMap<>();
        Map<Character, Integer> taskScheduleMap = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            taskFreqMap.put(tasks[i], taskFreqMap.getOrDefault(tasks[i], 0) + 1); // map key is TaskName, and value is number of times to be executed.
            taskScheduleMap.put(tasks[i], 1);
        }
        PriorityQueue<Character> q = new PriorityQueue<>( //frequency sort
                (a, b) -> taskFreqMap.get(b) == taskFreqMap.get(a) ? a - b : taskFreqMap.get(b) - taskFreqMap.get(a));

        q.addAll(taskFreqMap.keySet());

        int time = 0;
        List<Character> popped = new ArrayList<>();
        while (!q.isEmpty()) {
            boolean scheduled = false;
            time++;
            while (!q.isEmpty() && !scheduled) {
                Character task = q.poll();
                int when = taskScheduleMap.get(task);
                if (time >= when) {
                    taskScheduleMap.put(task, time + n + 1);
                    taskFreqMap.put(task, taskFreqMap.get(task) - 1);
                    if (taskFreqMap.get(task) == 0) {
                        taskFreqMap.remove(task);
                        taskScheduleMap.remove(task);
                    }
                    scheduled = true;
                }
                if (taskFreqMap.get(task) != null)
                    popped.add(task);
            }
            if (!popped.isEmpty()) {
                q.addAll(popped);
                popped = new ArrayList<>();
            }
        }
        return time;
    }


    /*
      This does not work because it does not always pick the most frequent task to run for a particular time
     */
    public int leastIntervalOld(char[] tasks, int n) {
        Map<Character, Integer> taskFreqMap = new HashMap<>();
        Map<Character, Integer> taskScheduleMap = new HashMap<>();
        for (char c : tasks) {
            taskFreqMap.put(c, taskFreqMap.getOrDefault(c, 0) + 1);
            taskScheduleMap.put(c, 1);
        }
        int time = 0;
        //Only when all tasks are completed, this will be empty
        while (!taskFreqMap.isEmpty()) {
            time++; // time always keeps moving on even if we did not do any task
            for (Character task : taskFreqMap.keySet()) {
                int when = taskScheduleMap.get(task);
                if (time >= when) {
                    taskScheduleMap.put(task, time + n + 1);
                    taskFreqMap.put(task, taskFreqMap.get(task) - 1);
                    if (taskFreqMap.get(task) == 0) {
                        taskFreqMap.remove(task);
                        taskScheduleMap.remove(task);
                    }
                    break; // Can only do one task at a time
                }
            }

        }
        return time;
    }

    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E'};
        TaskScheduler t = new TaskScheduler();
        //System.out.println(t.leastInterval2(tasks, 2));

        char[] tasks2 = {'A', 'A', 'A', 'B', 'B', 'B'};
        System.out.println(t.leastInterval(tasks2, 2));
    }
}
