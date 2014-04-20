
package org.dict;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.dict.db.Word;
import org.dict.db.HibernateUtil;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/definition/{varWord}")
public class MyResource {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces({"text/plain"})
    public String getIt(@PathParam("varWord") String word) {
        
        Session sess = null;
        String result = "Exception";
        try{
               sess = HibernateUtil.getSession();
               Word w = (Word) sess.get(Word.class, 1);
               if(w != null) {
            	   result = "Found " + w.getWord();
               }
               
               result = word + " not found";
        }
        catch(HibernateException e){
               e.printStackTrace();//Later remove this by appropriate logger statement or throw custom exception
        }
        return result;
    }
}
