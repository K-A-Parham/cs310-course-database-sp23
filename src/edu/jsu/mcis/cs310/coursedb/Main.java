package edu.jsu.mcis.cs310.coursedb;

import edu.jsu.mcis.cs310.coursedb.dao.*;

public class Main {

    public static void main(String[] args) {
        
        DAOFactory daoFactory = new DAOFactory("coursedb");
        SectionDAO sectionDAO = daoFactory.getSectionDAO();
        
        
        if ( !daoFactory.isClosed() ) {
            System.out.println("Connected Successfully!");
        }
        
        System.out.println(sectionDAO.find(1, "CS", "201"));
        
    }
    
}