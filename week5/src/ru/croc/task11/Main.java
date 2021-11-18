package ru.croc.task11;

import java.util.Date;

public class Main {
    static class Lot {
        private volatile int price;
        private volatile String buyer;
        private Date finish;

        public Lot(Date finish) {
            this.finish = finish;
        }

        public synchronized boolean update(int price, String buyer) {
            if ((price > this.price) && (new Date().before(this.finish))) {
                this.price = price;
                this.buyer = buyer;
                return true;
            } else return false;
        }

        public String getWinner() {
            return new Date().after(this.finish) ? this.buyer : null;
        }
    }

    public static void main(String[] args) {
	    Lot lot = new Lot(new Date(System.currentTimeMillis() + 1_000_000));
        lot.update(100, "Croc");
        System.out.println(lot.getWinner());
    }
}
