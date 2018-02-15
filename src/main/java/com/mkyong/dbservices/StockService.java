package com.mkyong.dbservices;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.mkyong.entity.SearchCriteria;
import com.mkyong.entity.Stock;
import com.mkyong.persistence.HibernateUtil;

@Service
public class StockService {
	
	private Session session;
	
	private Session getSession() {
		if(session == null)
			session = HibernateUtil.getSessionFactory().openSession();
		return session; 
	}
	
	public void saveStock(Stock stock) {			
		if(stock.getStockId() != null) {
			Stock temp = stock;
			stock = getStockById(stock.getStockId());
			stock.setStockCode(temp.getStockCode());
			stock.setStockName(temp.getStockName());
		}
		
		getSession().beginTransaction();    
		getSession().saveOrUpdate(stock);
		getSession().getTransaction().commit();
	}

	public void deleteStock(Stock stock) {
		getSession().beginTransaction();  
		getSession().delete(stock);
	    getSession().getTransaction().commit();
	}
	
	
	public List<Stock> searchStock(SearchCriteria search) {
		Criteria criteria = getSession().createCriteria(Stock.class);
		if(search != null) {
			criteria.add(Restrictions.ilike("stockName", "%"+search.getStockname()+"%"));
		}
		List<Stock> result = criteria.list();
		return result;
	}
	
	public Stock getStockById(int stockId) {
		Criteria criteria = getSession().createCriteria(Stock.class);
		criteria.add(Restrictions.eq("stockId", stockId));
		Stock stock =  (Stock) criteria.uniqueResult();
		return stock;
	}
	
	public int saveStockHql(Stock stock) {		
		int result = 0;
		if(stock.getStockId() != null) {
			Query query = getSession().createQuery(
					"update Stock set stockName = :stockName" +
			        " ,stockCode = :stockCode" +
					" where stockId = :stockId");
			query.setParameter("stockId", stock.getStockId());
			query.setParameter("stockName", stock.getStockName());
			query.setParameter("stockCode", stock.getStockCode());
			result = query.executeUpdate();
		}
		else {
			getSession().beginTransaction();    
			getSession().saveOrUpdate(stock);
			getSession().getTransaction().commit();
			result = 1;
		}
		return result;
	}
	
	public List<Stock> searchStockhql(SearchCriteria search) {
		Query query = getSession().createQuery("from Stock where stockName like :name ");
		query.setParameter("name", "%" + search.getStockname() + "%");
		return query.list();
	}
	
	
	
	/*public static List<Stock> get(Stock filter) {
		Criteria criteria = getSession().createCriteria(Stock.class);
		if(filter != null) {
			if(filter.getStockId() != null)
				criteria.add(Restrictions.eq("stockId", filter.getStockId()));
			if(filter.getStockCode() != null)
				criteria.add(Restrictions.eq("stockCode", filter.getStockCode()));
			if(filter.getStockName() != null)
				criteria.add(Restrictions.eq("stockName", filter.getStockName()));
		}
		List<Stock> result = criteria.list();
		return result;
	}*/
}
