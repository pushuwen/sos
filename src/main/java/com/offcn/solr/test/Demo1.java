package com.offcn.solr.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import com.offcn.pojo.TbItem;
public class Demo1 {

	static ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:solr.xml");
	static SolrTemplate template = (SolrTemplate) app.getBean("solrTemplate");
	
	public static void main(String[] args) {
		
//		save();
		
//		getData();
		
//		deleteData();
		
//		saveAll();
		System.out.print("111");
		deleteAllData();
		
//		fenyefind();
		
		
//		tiaojiansearch();
		
		

	}
	
	
	// 娣诲姞鏁版嵁
	public static void save(){
		TbItem item = new TbItem();
		item.setId(1l);
		item.setPrice(new BigDecimal(2000));
		item.setTitle("鎵嬫満");
		// save
		template.saveBean(item);
		template.commit();
		
		System.out.println("ok");
	}
	
	public static void saveAll(){
		List<TbItem> list = new ArrayList<TbItem>();
		for(long i = 0; i < 100; i++){
			TbItem item = new TbItem();
			item.setId(i);
			item.setTitle("鎵嬫満"+i);
			item.setPrice(new BigDecimal(2000+i));
			list.add(item);
		}
		// 娣诲姞澶氭潯鏁版嵁
		template.saveBeans(list);
		template.commit();
	}
	// 鏍规嵁id鏌ヨ
	public static void getData(){
		TbItem tbItem = template.getById(1l, TbItem.class);
		System.out.println(tbItem.getTitle());
	}
	
	// 鍒犻櫎
	public static void deleteData(){
		template.deleteById("1");
		template.commit();
		System.out.println("ok");
	}
	
	
	// 鍒嗛〉鏌ヨ
	
	public static void fenyefind(){
		Query query = new SimpleQuery("*:*");
		// 璁剧疆寮�濮嬬储寮曞拰姣忛〉鐨勬暟鎹�
		query.setOffset(0);
		query.setRows(20);
		
		// 寮�濮嬪垎椤垫煡璇�
		ScoredPage<TbItem> page = template.queryForPage(query, TbItem.class);
		System.out.println(page.getTotalElements()+"鎬绘潯鐩暟");
		
		for(TbItem item : page){
			System.out.println(item.getTitle());
		}
		
	}
	
	
	// 鍒犻櫎solr涓殑鎵�鏈夌殑鏁版嵁
	public static void deleteAllData(){
		Query query = new SimpleQuery("*:*");
		template.delete(query);
		template.commit();
		System.out.println("鍒犻櫎鎵�鏈夋暟鎹�");
	}
	
	public static void tiaojiansearch(){
		Query query = new SimpleQuery("sellerId:1");
//		Criteria criteria = new Criteria("title").contains("鎵嬫満");
//		criteria.and("title").
//		query.addCriteria(criteria);
		
		// 璁剧疆鍒嗛〉
//		query.setOffset(0);
//		query.setRows(100);
		
		ScoredPage<TbItem> list = template.queryForPage(query, TbItem.class);
		
		for(TbItem item : list){
			System.out.println(item.getTitle());
		}
		
	}
}






























