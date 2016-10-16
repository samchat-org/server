package com.samchat.dao.redis.interfaces;

import java.util.List;

public interface IBaseRedisDao<K, V> {
	 
		public void hset(String key, String field, String value) ;                                                    
		public void hsetJsonObj(String key, String field, Object value) throws Exception ;                            
		public void set(String key, String value) ;                                                                   
		public void set(String key, String value, int expire) ;                                                       
		public boolean setNX(String key, String value) ;                                                              
		public boolean setNX(String key, String value, int expire) ;                                                  
		public void setJsonObj(String key, Object valueObj) throws Exception ;                                        
		public void setJsonObj(String key, Object valueObj, int expire) throws Exception ;                            
		public boolean setJsonObjNX(String key, Object valueObj) throws Exception ;                                   
		public boolean setJsonObjNX(String key, Object valueObj, int expire) throws Exception ;                       
		public String getFromMaster(String key) ;                                                                     
		public String get(String key) ;                                                                               
		public <T> T getJsonObj(String key) throws Exception ;                                                        
		public String hgetFromMaster(String key,  String field) ;                                                
		public String hgetFromSlave(String key,  String field) ;                                                 
		public String hget(String key,  String field) ;                                                          
		public <T> T hgetJsonObj(String key, String field) throws Exception ;                                         
		public void delete(String key) ;                                                                              
		public void delete(List<String> keyls) ;                                                                      
}
