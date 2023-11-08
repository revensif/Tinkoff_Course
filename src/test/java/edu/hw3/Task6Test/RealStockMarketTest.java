package edu.hw3.Task6Test;

import edu.hw3.Task6.RealStockMarket;
import edu.hw3.Task6.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RealStockMarketTest {
    private static RealStockMarket market;

    @Test
    @DisplayName("Task6Test: add() Test")
    void shouldDoAddStock() {
        market = new RealStockMarket();
        Stock stock1 = new Stock(-1);
        Stock stock2 = new Stock(15);
        Stock stock3 = new Stock(10);
        market.add(stock1);
        market.add(stock2);
        assertThat(market.getQueue().size()).isEqualTo(2);
        assertThat(market.getQueue()).containsExactlyInAnyOrder(stock1, stock2);
        market.add(stock3);
        assertThat(market.getQueue().size()).isEqualTo(3);
        assertThat(market.getQueue()).containsExactlyInAnyOrder(stock1, stock2, stock3);
        market.add(null);
        assertThat(market.getQueue().size()).isEqualTo(3);
        assertThat(market.getQueue()).containsExactlyInAnyOrder(stock1, stock2, stock3);
    }

    @Test
    @DisplayName("Task6Test: remove() Test")
    void shouldDoRemoveStock() {
        market = new RealStockMarket();
        Stock stock1 = new Stock(10);
        Stock stock2 = new Stock(3);
        Stock stock3 = new Stock(2);
        market.add(stock1);
        market.add(stock2);
        market.add(stock3);
        assertThat(market.getQueue().size()).isEqualTo(3);
        assertThat(market.getQueue()).containsExactlyInAnyOrder(stock1, stock2, stock3);
        market.remove(stock2);
        assertThat(market.getQueue().size()).isEqualTo(2);
        assertThat(market.getQueue()).containsExactlyInAnyOrder(stock1, stock3);
        market.remove(null);
        assertThat(market.getQueue().size()).isEqualTo(2);
        assertThat(market.getQueue()).containsExactlyInAnyOrder(stock1, stock3);
        Stock stock4 = new Stock(5);
        market.remove(stock4);
        assertThat(market.getQueue().size()).isEqualTo(2);
        assertThat(market.getQueue()).containsExactlyInAnyOrder(stock1, stock3);
    }

    @Test
    @DisplayName("Task6Test: mostValuableStock() Test")
    void shouldDoMostValuableStock() {
        market = new RealStockMarket();
        assertThat(market.mostValuableStock()).isNull();
        Stock stock1 = new Stock(-1);
        Stock stock2 = new Stock(87);
        Stock stock3 = new Stock(30);
        market.add(stock1);
        assertThat(market.mostValuableStock()).isEqualTo(stock1);
        market.add(stock2);
        market.add(stock3);
        assertThat(market.mostValuableStock()).isEqualTo(stock2);
        market.remove(stock2);
        assertThat(market.mostValuableStock()).isEqualTo(stock3);
    }
}
