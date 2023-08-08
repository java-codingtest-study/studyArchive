import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

class Solution {
    public static final String TIME_SEPARATOR = ":";
    public static LinkedList<Subject> subjects = new LinkedList<>();
    public static Stack<Subject> remainSubjects = new Stack<>();


    public static String[] solution(String[][] plans) {
        LinkedList<String> answer = new LinkedList<>();
        inputSubjects(plans);

        for (int i = 0; i < subjects.size(); i++) {
            // 마지막 과제일땐 시간관계없이 수행
            if (i == subjects.size() - 1) {
                answer.add(subjects.get(i).name);
                break;
            }
            // 마지막 과제가 아닌 경우
            Subject subject = subjects.get(i);
            Subject nextSubject = subjects.get(i + 1);

            // 현재 subject 와 다음 Subject의 시간차가 playtime보다 크면 사용가능한 시간만 사용 후remainSubject 에 등록
            if (Math.abs(subject.compareTo(nextSubject)) < subject.playTime) {
                subject.useTime(Math.abs(subject.compareTo(nextSubject)));
                remainSubjects.push(subject);

                // playTime 을 수행한 현재 subject 의 시간이 nextSubject 과 같으면 answer 에 등록
            } else if (Math.abs(subject.compareTo(nextSubject)) >= subject.playTime) {
                subject.usePlayTime();
                answer.add(subject.name);

                // playTime 을 수행한 후, 다음 과제로 가기 전 남는 시간이 있다면 사용
                int remainTime = Math.abs(subject.compareTo(nextSubject));

                while (remainTime > 0 && !remainSubjects.isEmpty()) {
                    Subject remainSubject = remainSubjects.pop();
                    int afterUseRemainTime = remainSubject.useRemainTime(remainTime);
                    remainTime = afterUseRemainTime;

                    if (remainSubject.playTime <= 0) {
                        answer.add(remainSubject.name);
                    } else if (remainSubject.playTime > 0) {
                        remainSubjects.push(remainSubject);
                    }
                }
            }
        }

        inputRemainSubjects(answer);

        return answer.stream().toArray(String[]::new);
    }

    private static void inputRemainSubjects(LinkedList<String> answer) {
        while (!remainSubjects.isEmpty()) {
            answer.add(remainSubjects.pop().name);
        }
    }

    private static void inputSubjects(String[][] plans) {
        for (String[] plan : plans) {
            subjects.add(new Subject(plan[0], plan[1], plan[2]));
        }
        Collections.sort(subjects);
    }

    private static class Subject implements Comparable<Subject> {

        private String name;
        private int startHour;
        private int startMinute;
        private int playTime;

        public static final int MINUTE_OF_HOUR = 60;

        public Subject(String name, String start, String playTime) {
            String[] time = start.split(TIME_SEPARATOR);
            this.name = name;
            this.startHour = Integer.parseInt(time[0]);
            this.startMinute = Integer.parseInt(time[1]);
            this.playTime = Integer.parseInt(playTime);
        }

        public String getName() {
            return name;
        }

        public int useRemainTime(int remainTime) {
            playTime -= remainTime;
            if (playTime >= 0) {
                return 0;
            }
            return -playTime;
        }

        public void usePlayTime() {
            startMinute += playTime;
            startHour += startMinute / MINUTE_OF_HOUR;
            startMinute %= MINUTE_OF_HOUR;
        }

        @Override
        public int compareTo(Subject s) {
            return ((this.startHour - s.startHour) * 60) + (this.startMinute - s.startMinute);
        }

        public void useTime(int time) {
            playTime -= time;
        }
    }
}
