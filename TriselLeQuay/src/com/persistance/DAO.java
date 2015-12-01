package com.persistance;

import java.sql.Connection;
import java.util.*;
//classe DAO generique indiquée par DAO<T>
	public abstract class DAO<T> {
	   protected   Connection con=AccesBd.getInstance();
	   
	   public DAO(){
	      this.con = AccesBd.getInstance();
	   }
	   
	   /**
	    * Méthode de création
	    * @param obj
	    * @return boolean 
	    */
	   public  abstract boolean create(T obj);
	   /**
	    * Méthode pour effacer
	    * @param obj
	    * @return boolean 
	    */
	   public  abstract boolean delete(T obj);
	   /**
	    * Méthode de mise à jour
	    * @param obj
	    * @return boolean
	    */
	   public  abstract boolean update(T obj);
	   /**
	    * Méthode de recherche des informations
	    * @param id
	    * @return T
	    */
	   public  abstract T find(int id);
	   /**
	    * Méthode de recherche des informations
	    * @return ArrayList<T>
	    */
	   public  abstract T find(String id);
	   /**
	    * Méthode de recherche des informations
	    * @return ArrayList<T>
	    */
	   public abstract ArrayList<T> retrieve();
	}

