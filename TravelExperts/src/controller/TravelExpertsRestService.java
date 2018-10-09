package controller;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Agent;
import model.Booking;
import model.Bookingdetail;
import model.Customer;
import model.Package;


@Path("/travelexperts")
public class TravelExpertsRestService {

	private static final Logger logger = Logger.getLogger(TravelExpertsRestService.class);

// http://localhost:8080/TravelExperts/rs/customers/getallcustomers
	@GET
	@Path("/getallcustomers")
    @Produces(MediaType.TEXT_PLAIN)
	public String getAllCustomers(@QueryParam("request") String request ,
			 @DefaultValue("1") @QueryParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");
	                
	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts");
	                EntityManager em = factory.createEntityManager();
	                
	                Query query = em.createQuery("select c from Customer c");
	                List<Customer> customer = (List<Customer>) query.getResultList();
	                Gson gson = new Gson();
	                Type type = new TypeToken<List<Customer>>(){}.getType();
	                String json = gson.toJson(customer, type);
	                response = json;
	                em.close();
	                factory.close();
	                
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End getAllCustomers");
        }
        return response;	
	}
	
	// http://localhost:8080/TravelExperts/rs/travelexperts/getcustomer/104
	@GET
	@Path("/getcustomer/{customerid}")
    @Produces(MediaType.TEXT_PLAIN)
	public String getCustomer(@PathParam("customerid") int customerid, @QueryParam("request") String request ,
			 @DefaultValue("1") @QueryParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");
	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts");
	                EntityManager em = factory.createEntityManager();
	                
	                Query query = em.createQuery("select c from Customer c where c.customerId=" + customerid);
	                Customer customer = (Customer) query.getSingleResult();
	                Gson gson = new Gson();
	                Type type = new TypeToken<Customer>(){}.getType();
	                String json = gson.toJson(customer, type);
	                response = json;
	                em.close();
	                factory.close();
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End getCustomer");
        }
        return response;	
	}	
	
// http://localhost:8080/TravelExperts/rs/travelexperts/postcustomer
	@POST
	@Path("/postcustomer")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
	public String postAgent(String jsonString, @FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start postCustomer");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                JSONObject json = new JSONObject(jsonString);
	                System.out.println(json);

	                EntityManagerFactory factory =
	                		Persistence.createEntityManagerFactory("TravelExperts");
	                EntityManager em = factory.createEntityManager();
	                
	                Customer customer = em.find(Customer.class, json.getInt("customerId"));
	                customer.setCustomerId(json.getInt("customerId"));
	                customer.setCustFirstName(json.getString("custFirstName"));
	                customer.setCustLastName(json.getString("custLastName"));
	                customer.setCustAddress(json.getString("custAddress"));
	                customer.setCustCity(json.getString("custCity"));
	                customer.setCustProv(json.getString("custProv"));
	                customer.setCustPostal(json.getString("custPostal"));
	                customer.setCustHomePhone(json.getString("custHomePhone"));
	                customer.setCustBusPhone(json.getString("custBusPhone"));
	                customer.setCustEmail(json.getString("custEmail"));
	                customer.setAgentId(json.getInt("agentId"));
	                em.getTransaction().begin();
	                em.persist(customer);
	                
	                em.getTransaction().commit();
                    response = "Customer Data was Successfully Updated";
	                
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End postCustomer");
        }
        return response;	
	}
	
// http://localhost:8080/TravelExperts/rs/travelexperts/putcustomer
	@POST
	@Path("/putcustomer")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
	public String postInsertCustomer(String jsonString, @FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start postInsertCustomer");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                JSONObject json = new JSONObject(jsonString);
	                System.out.println(json);

	                EntityManagerFactory factory =
	                		Persistence.createEntityManagerFactory("TravelExperts");
	                EntityManager em = factory.createEntityManager();
	                
	                Customer customer = new Customer();
	                customer.setCustomerId(json.getInt("customerId"));
	                customer.setCustFirstName(json.getString("custFirstName"));
	                customer.setCustLastName(json.getString("custLastName"));
	                customer.setCustAddress(json.getString("custAddress"));
	                customer.setCustCity(json.getString("custCity"));
	                customer.setCustProv(json.getString("custProv"));
	                customer.setCustPostal(json.getString("custPostal"));
	                customer.setCustHomePhone(json.getString("custHomePhone"));
	                customer.setCustBusPhone(json.getString("custBusPhone"));
	                customer.setCustEmail(json.getString("custEmail"));
	                customer.setAgentId(json.getInt("agentId"));
	                em.getTransaction().begin();
	                em.persist(customer);
	                
	                em.getTransaction().commit();
                    response = "Customer was Successfully Added";
	                
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End postInsertCustomer");
        }
        return response;	
	}

	// http://localhost:8080/TravelExperts/rs/travelexperts/deletecustomer
	@DELETE
	@Path("/deletecustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteAgent(String customerId, @FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start deleteCustomer");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}


        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                JSONObject json = new JSONObject(customerId);
	                EntityManagerFactory factory =
	                		Persistence.createEntityManagerFactory("TravelExperts");
	                EntityManager em = factory.createEntityManager();
	                
	                Customer customer = em.find(Customer.class, Integer.parseInt(json.getString("customerId")));
	                em.getTransaction().begin();
	                em.remove(customer);
	                em.getTransaction().commit();
	                em.close();
	                factory.close();

	                break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("End deleteCustomer");
        }
	}
	
// http://localhost:8080/TravelExperts/rs/travelexperts/getpackage/1
	@GET
	@Path("/getpackage/{packageid}")
    @Produces(MediaType.TEXT_PLAIN)
	public String getPackage(@PathParam("packageid") int packageid, @QueryParam("request") String request ,
			 @DefaultValue("1") @QueryParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getPackage");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");
	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts");
	                EntityManager em = factory.createEntityManager();
	                
	                Query query = em.createQuery("select p from Package p where p.packageId=" + packageid);
	                Package travelpackage = (Package) query.getSingleResult();
	                Gson gson = new Gson();
	                Type type = new TypeToken<Package>(){}.getType();
	                String json = gson.toJson(travelpackage, type);
	                response = json;
	                em.close();
	                factory.close();
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End getPackage");
        }
        return response;	
	}
	
	
// http://localhost:8080/TravelExperts/rs/travelexperts/getallpackages
	@GET
	@Path("/getallpackages")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAllPackages(@QueryParam("request") String request ,
			 @DefaultValue("1") @QueryParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllPackages");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts");
	                EntityManager em = factory.createEntityManager();
	                
	                Query query = em.createQuery("select p from Package p");
	                List<Package> travelpackage = (List<Package>) query.getResultList();
	                Gson gson = new Gson();
	                Type type = new TypeToken<List<Package>>(){}.getType();
	                String json = gson.toJson(travelpackage, type);
	                response = json;
	                em.close();
	                factory.close();
	                
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End getAllPackages");
        }
        return response;	
	}

// http://localhost:8080/TravelExperts/rs/travelexperts/getbooking/11
	@GET
	@Path("/getbooking/{bookingid}")
    @Produces(MediaType.TEXT_PLAIN)
	public String getBooking(@PathParam("bookingid") int bookingid, @QueryParam("request") String request ,
			 @DefaultValue("1") @QueryParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getBooking");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");
	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts");
	                EntityManager em = factory.createEntityManager();
	                
	                Query query = em.createQuery("select b from Booking b where b.bookingId=" + bookingid);
	                Booking booking = (Booking) query.getSingleResult();
	                Gson gson = new Gson();
	                Type type = new TypeToken<Booking>(){}.getType();
	                String json = gson.toJson(booking, type);
	                response = json;
	                em.close();
	                factory.close();
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End getBooking");
        }
        return response;	
	}
	
// http://localhost:8080/TravelExperts/rs/travelexperts/getallbookings
	@GET
	@Path("/getallbookings")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAllBookings(@QueryParam("request") String request ,
			 @DefaultValue("1") @QueryParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllBookings");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts");
	                EntityManager em = factory.createEntityManager();
	                
	                Query query = em.createQuery("select b from Booking b");
	                List<Booking> booking = (List<Booking>) query.getResultList();
	                Gson gson = new Gson();
	                Type type = new TypeToken<List<Booking>>(){}.getType();
	                String json = gson.toJson(booking, type);
	                response = json;
	                em.close();
	                factory.close();
	                
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End getAllBookings");
        }
        return response;	
	}

// http://localhost:8080/TravelExperts/rs/travelexperts/getbookingdetail/1
	@GET
	@Path("/getbookingdetail/{bookingid}")
    @Produces(MediaType.TEXT_PLAIN)
	public String getBookingDetail(@PathParam("bookingid") int bookingid, @QueryParam("request") String request ,
			 @DefaultValue("1") @QueryParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getBookingDetail");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");
	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts");
	                EntityManager em = factory.createEntityManager();
	                
	                Query query = em.createQuery("select b from Bookingdetail b where b.bookingId=" + bookingid);
	                Bookingdetail bookingdetail = (Bookingdetail) query.getSingleResult();
	                Gson gson = new Gson();
	                Type type = new TypeToken<Bookingdetail>(){}.getType();
	                String json = gson.toJson(bookingdetail, type);
	                response = json;
	                em.close();
	                factory.close();
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End getBookingdetail");
        }
        return response;	
	}
	
	
// http://localhost:8080/TravelExperts/rs/travelexperts/getallbookingdetails
	@GET
	@Path("/getallbookingdetails")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAllBookingdetails(@QueryParam("request") String request ,
			 @DefaultValue("1") @QueryParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllBookingdetails");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts");
	                EntityManager em = factory.createEntityManager();
	                
	                Query query = em.createQuery("select b from Bookingdetail b");
	                List<Bookingdetail> bookingdetail = (List<Bookingdetail>) query.getResultList();
	                Gson gson = new Gson();
	                Type type = new TypeToken<List<Bookingdetail>>(){}.getType();
	                String json = gson.toJson(bookingdetail, type);
	                response = json;
	                em.close();
	                factory.close();
	                
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End getAllBookingdetails");
        }
        return response;	
	}

	@POST
	@Path("/<add method name here>")
    @Produces(MediaType.TEXT_PLAIN)
	public String postSomething(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start postSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                response = "Response from RESTEasy Restful Webservice : " + request;
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End postSomething");
        }
        return response;	
	}

	@PUT
	@Path("/<add method name here>")
    @Produces(MediaType.TEXT_PLAIN)
	public String putSomething(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start putSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                response = "Response from RESTEasy Restful Webservice : " + request;
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End putSomething");
        }
        return response;	
	}

	@DELETE
	@Path("/<add method name here>")
	public void deleteSomething(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start deleteSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}


        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("End deleteSomething");
        }
	}
}
