import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;


public class kullanici_islemleri {

    private Connection con = null;
    private Statement statement = null;
    public PreparedStatement preparedStatement = null;
    
     public ArrayList<filmler> filmleriGetir() throws SQLException{
         
         ArrayList<filmler> cikti = new ArrayList<filmler>();
         
         
        try {
             
            
            String url = "jdbc:sqlite:folder1/Netflix.db";
		Connection con = this.con;

        try{
            con = (Connection)DriverManager.getConnection(url);
			System.out.println("Veritabanına bağlandınız!");
			
        }catch (Exception e){
            System.out.println("aa bağlantı başarısız");
        }
            String sorgu = "select film.id,film.isim,tur.ad from film,film_tur,tur where film.id=film_tur.film_id and tur.id=film_tur.tur_id  ";
         
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sorgu);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String ad = rs.getString("isim");
                String tür = rs.getString("ad");
                
                
                cikti.add(new filmler(id,ad,tür));
            }
            
             con.close();
              return cikti;
             
      
          
          
      
        } catch (SQLException ex) {
            Logger.getLogger(kullanici_islemleri.class.getName()).log(Level.SEVERE, null, ex);
       return null;
        }
    
}
     
     public ArrayList<filmpuan> filmpuansırala(String tur) throws SQLException{
         
         ArrayList<filmpuan> cikti = new ArrayList<filmpuan>();
         
         
        try {
             
            
            String url = "jdbc:sqlite:folder1/Netflix.db";
		Connection con = this.con;

        try{
            con = (Connection)DriverManager.getConnection(url);
			System.out.println("Veritabanına bağlandınız!");
			
        }catch (Exception e){
            System.out.println("aa bağlantı başarısız");
        }
            String sorgu = "select film.id,film.isim,tur.ad,film.puan from film,film_tur,tur where film.id=film_tur.film_id and tur.id=film_tur.tur_id and tur.ad= ? LIMIT 2  ";
         
             preparedStatement = con.prepareStatement(sorgu);
           
            
            preparedStatement.setString(1, tur);
           
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                
                String isim = rs.getString("isim");
                String tür = rs.getString("ad");
                int puan = rs.getInt("puan");
                
                cikti.add(new filmpuan(isim,tür,puan));
            }
            
             con.close();
              return cikti;
             
      
          
          
      
        } catch (SQLException ex) {
            Logger.getLogger(kullanici_islemleri.class.getName()).log(Level.SEVERE, null, ex);
       return null;
        }
    
}
     
     
     
     
     
     public void kullaniciekle(String ad , String parola, String email, String dgtarih) throws SQLException{
         
         String url = "jdbc:sqlite:folder1/Netflix.db";
		Connection con = this.con;

        try{
            con = (Connection)DriverManager.getConnection(url);
			System.out.println("Veritabanına bağlandınız!");
			
        }catch (Exception e){
            System.out.println("aa bağlantı başarısız");
        }
         
         
         String sorgu = "insert into kullanici (email,isim,sifre,dogum_tarihi) VALUES (?,?,?,?)";
         
        try {
            preparedStatement = con.prepareStatement(sorgu);
            
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, ad);
            preparedStatement.setString(3, parola);
            preparedStatement.setString(4, dgtarih);
            
            preparedStatement.executeUpdate();
            
            
       
            
            con.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(kullanici_islemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
         
     }
     
   
     public void filmkullaniciekle( String film , String tarih , int sure, int puan )throws SQLException{
         Girisekrani g = new Girisekrani();
         String k_email = g.getKullanici_adi();
         String url = "jdbc:sqlite:folder1/Netflix.db";
		
        try{
            con = (Connection)DriverManager.getConnection(url);
			System.out.println("Veritabanına bağlandınız!");
			
        }catch (Exception e){
            System.out.println("aa bağlantı başarısız");
        }
        
        String sorgu = "INSERT INTO kullanici_has_film (kullanici_email, film_isim, izleme_tarihi,izleme_suresi,puan) VALUES (?,?,?,?,?)";
        System.out.println(k_email+"    "+ film+"    "+tarih );
       try{
        preparedStatement = con.prepareStatement(sorgu);
        
        preparedStatement.setString(1, k_email);
        preparedStatement.setString(2, film);
        preparedStatement.setString(3, tarih);
        preparedStatement.setInt(4, sure);
        preparedStatement.setInt(5, puan);
        
        
        preparedStatement.executeUpdate();
          } catch (SQLException ex) {
            Logger.getLogger(kullanici_islemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
         preparedStatement.close();
         con.close();
         
         
     }
     
     public void filmkullaniciguncelle( String film , String tarih , int sure )throws SQLException{
         Girisekrani g = new Girisekrani();
         String k_email = g.getKullanici_adi();
        
      
             String url = "jdbc:sqlite:folder1/Netflix.db";
		Connection con = this.con;

        try{
            con = (Connection)DriverManager.getConnection(url);
			System.out.println("Veritabanına bağlandınız!");
			
        }catch (Exception e){
            System.out.println("aa bağlantı başarısız");
        }
         
        String sorgu = "UPDATE kullanici_has_film SET izleme_tarihi = ? , izleme_suresi = ?  WHERE kullanici_email = ? and film_isim = ?";

         try {
              System.out.println(k_email+"    "+ film+"    "+tarih+"    "+sure);
                 preparedStatement = con.prepareStatement(sorgu);
                 
                 preparedStatement.setString(1, tarih);
                 preparedStatement.setInt(2, sure);
                 preparedStatement.setString(3, k_email);
                preparedStatement.setString(4, film);
                 
                 
                 preparedStatement.executeUpdate();
               con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(kullanici_islemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
          
         
         
         
     }
     
     
   
         
     public int suregetir(String film){
        
         Girisekrani g = new Girisekrani();
         String k_email = g.getKullanici_adi();
         int minute=0;
         int i=0;
         int sure;
         String mail,filmisim,izlemetarihi;
         
         try {
            
             
            
            String url = "jdbc:sqlite:folder1/Netflix.db";
		Connection con = this.con;

        try{
            con = (Connection)DriverManager.getConnection(url);
			System.out.println("Veritabanına bağlandınız!");
			
        }catch (Exception e){
            System.out.println("aa bağlantı başarısız");
        }
        
        
             statement = con.createStatement();
              String sorgu = "Select* from kullanici_has_film ";
            ResultSet rs = statement.executeQuery(sorgu);
            
            while(rs.next()){
                
                 sure = rs.getInt("izleme_suresi");
                 mail = rs.getString("kullanici_email");
                 filmisim = rs.getString("film_isim");
                 izlemetarihi = rs.getString("izleme_tarihi");
               
                 if(mail.equals(k_email) && filmisim.equals(film)){
                    
                    
                    
                    minute=sure;
                    
                }
             
            
            }
                con.close();
            return minute; 
                        
        } catch (SQLException ex) {
            Logger.getLogger(kullanici_islemleri.class.getName()).log(Level.SEVERE, null, ex);
         return 534;
        }
         
         
  
     }
     public String tarihgetir(String film){
        
         Girisekrani g = new Girisekrani();
         String k_email = g.getKullanici_adi();
         int minute=0;
         int i=0;
         int sure;
         String mail,filmisim,izlemetarihi;
         String tarih="";
         
          String url = "jdbc:sqlite:folder1/Netflix.db";
		Connection con = this.con;

        try{
            con = (Connection)DriverManager.getConnection(url);
			System.out.println("Veritabanına bağlandınız!");
			
        }catch (Exception e){
            System.out.println("aa bağlantı başarısız");
        }
       
        
         
         try {
             statement = con.createStatement();
              String sorgu = "Select* from kullanici_has_film ";
            ResultSet rs = statement.executeQuery(sorgu);
            
            while(rs.next()){
                
                 
                 mail = rs.getString("kullanici_email");
                 filmisim = rs.getString("film_isim");
                 izlemetarihi = rs.getString("izleme_tarihi");
               
                 if(mail.equals(k_email) && filmisim.equals(film)){
                    
                    
                    tarih=izlemetarihi;
                    
                    
                }
             
            }
                con.close();
            return tarih; 
                        
        } catch (SQLException ex) {
            Logger.getLogger(kullanici_islemleri.class.getName()).log(Level.SEVERE, null, ex);
         return null;
        }
        
         
         
  
     }
  
    
    public boolean girisYap(String kullanici_adi, String parola) throws SQLException{
        
        
         String sorgu = "Select * from kullanici where email = ? and sifre = ?  ";//asd@gmail.com

        
        try {
             String url = "jdbc:sqlite:folder1/Netflix.db";
		Connection con = this.con;

        try{
            con = (Connection)DriverManager.getConnection(url);
			System.out.println("Veritabanına bağlandınız!");
			
        }catch (Exception e){
            System.out.println("aa bağlantı başarısız");
        }
            preparedStatement = con.prepareStatement(sorgu);
           
            
            preparedStatement.setString(1, kullanici_adi);
            preparedStatement.setString(2, parola);
            
            
            
           
            
            ResultSet rs = preparedStatement.executeQuery();
            
           
            con.close();
            return rs.next();
                
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(kullanici_islemleri.class.getName()).log(Level.SEVERE, null, ex);
         return false;
        }
            
      
        
      
    }
    
    
    public kullanici_islemleri(){
     
  }      
      
}
