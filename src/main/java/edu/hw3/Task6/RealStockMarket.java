package edu.hw3.Task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class RealStockMarket implements StockMarket {
    private final PriorityQueue<Stock> queue = new PriorityQueue<>(Comparator.comparing(Stock::getPrice).reversed());

    public PriorityQueue<Stock> getQueue() {
        return queue;
    }

    @Override
    public void add(Stock stock) {
        if (stock == null) {
            return;
        }
        queue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        if (stock == null) {
            return;
        }
        queue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return queue.peek();
    }
}
