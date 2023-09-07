package interfaces;


public interface IMapper<Tentity, Tkey>{
	
	   Tkey create(Tentity e) throws Exception;
	   
	   Tentity read(Tkey k) throws Exception;
	    
	   void update(Tentity e) throws Exception;
	    
	   void delete(Tentity e) throws Exception;  
}
