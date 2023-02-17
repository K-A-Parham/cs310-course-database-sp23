package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import com.github.cliftonlabs.json_simple.*;

public class SectionDAO {
    
    // INSERT YOUR CODE HERE
    
    private final DAOFactory daoFactory;
    
    SectionDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public String find(int termid, String subjectid, String num) {
        
        JsonArray JA = new JsonArray();
        
        String query = "SELECT * FROM jsu_sp23_v1.section WHERE termid=? AND subjectid=? AND num=?";
        query = ".... WHERE termid=" + termid;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(query);
                ps.setInt(1, termid);
                ps.setString(2, subjectid);
                ps.setString(3, num);
                
                ps.execute();
                
                rs = ps.getResultSet();
                rsmd = rs.getMetaData();
                
                while (rs.next()) {
                    
                    JsonObject JO = new JsonObject();
                    
                    for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
                        
                        String key = rsmd.getColumnLabel(i);
                        
                        JO.put(key, rs.getString(key));
                        
                    }
                    
                    JA.add(JO);
                    
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return JA.toString();
        
    }
    
}