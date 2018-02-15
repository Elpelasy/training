package com.mkyong.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.mkyong.controller.SearchController;
import com.mkyong.dbservices.StockService;

@SpringBootApplication
@ComponentScan(basePackageClasses = SearchController.class)
@ComponentScan(basePackageClasses = StockService.class)

//Start the application
// conflict
// Another comment 
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
        
        /*System.out.println("Maven + Hibernate + MySQL");
        
        for(int i=0; i< 10; i++){
	        Stock stock = new Stock(); 
	        stock.setStockCode("s" + i);
	        stock.setStockName("Stock" + i);
	        StockService.saveStock(stock);
        }
        
        Stock q1 = null;
        Stock q2 = new Stock();
        q2.setStockCode("s3");
     
        List<Stock> r1 = StockService.get(q1);
        System.out.println(r1.size());
        
        
        List<Stock> r2 = StockService.get(q2);
        System.out.println(r2.size());
        
        if(!r2.isEmpty())
        	StockService.deleteStock(r2.get(0));
        	*/
    }
}
