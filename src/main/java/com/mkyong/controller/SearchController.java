package com.mkyong.controller;

import com.mkyong.dbservices.StockService;
import com.mkyong.entity.AjaxResponseBody;
import com.mkyong.entity.SearchCriteria;
import com.mkyong.entity.Stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchController {

    StockService stockService;

    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);

        }

        List<Stock> stocks = stockService.searchStock(search);
        if (stocks.isEmpty()) {
            result.setMsg("no stocks found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(stocks);

        return ResponseEntity.ok(result);

    }
    
    @PostMapping("/api/manage")
    public ResponseEntity<?> stockManage(@RequestBody Stock stock) {
    	
    	stockService.saveStock(stock);

        return ResponseEntity.ok("success");

    }
    
    @PostMapping("/api/manageHql")
    public ResponseEntity<?> stockManageHql(@RequestBody Stock stock) {
    	
    	int r = stockService.saveStockHql(stock);

        return ResponseEntity.ok(r + " rows updated");

    }
    
    @PostMapping("/api/searchHql")
    public ResponseEntity<?> getSearchHql( @RequestBody SearchCriteria search) {

    	AjaxResponseBody result = new AjaxResponseBody();
    	
        List<Stock> stocks = stockService.searchStockhql(search);
        if (stocks.isEmpty()) {
            result.setMsg("no stocks found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(stocks);

        return ResponseEntity.ok(result);

    }
    

    
}
