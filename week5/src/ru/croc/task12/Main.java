package ru.croc.task12;

public class Main {
    // количество вариантов паролей длиной в 7 символов и только из строчных букв латинского алфавита
    static final long VARIANT_COUNT = (long)Math.pow(26, 7);

    static class Password {
        private int[] chars = new int[7];
        public Password(long number) {
            for (long i = 0; i < number; i++) {
                this.inc();
            }
        }
        public String asString() {
            char[] arr = new char[this.chars.length];
            for (int i = 0; i < this.chars.length; i++) {
                arr[i] = (char)(this.chars[i] + 97);
            }
            return String.valueOf(arr);
        }
        public void inc() {
            this.inc(chars.length - 1);
        }
        private void inc(int index) {
            if (chars[index] + 1 > 25) {
                chars[index] = 0;
                if (index == 0) {
                   this.inc();
                } else this.inc(index - 1);
            } else chars[index]++;
        }
    }

    static class MyThread extends Thread {
        private int step;
        private long startIndex, endIndex;
        private String hash;

        public MyThread(long startIndex, long endIndex, int step, String hash) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.step = step;
            this.hash = hash;
        }
        @Override
        public void run() {
            long index = startIndex;
            Password password = new Password(startIndex);
            while (index <= endIndex) {
                String variant = password.asString();
                if (this.hash.equalsIgnoreCase(MD5.hashPassword(variant))) {
                    System.out.printf("Пароль: %s\n", variant);
                    System.exit(0);
                } else {
                    index += step;
                    for (int i = 0; i < this.step; i++) password.inc();
                }
            }
        }
    }

    public static void main (String[] args) throws InterruptedException {
        MyThread[] threads = new MyThread[Integer.parseInt(args[0])];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(i, VARIANT_COUNT - 1, threads.length, args[1]);
            threads[i].start();
        }

        System.out.printf("Выполняется поиск пароля...\n");
        for (MyThread thread : threads) thread.join();
        System.out.printf("К сожалению, не удалось найти пароль.\n");
    }
}
