import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

@Path("/delectable")
public class REST_Controller {

	OrderManager om = new OrderManager();
	MenuManager mm = new MenuManager();
	CustomerManager cm = new CustomerManager();
	
	@Path("/menu")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayMenu(){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if(mm.DisplayMenu() != null)
		{
			String s = gson.toJson(mm.DisplayMenu());
			return Response.status(Response.Status.OK).entity(s).build();
		}
		
        return Response.status(Response.Status.NOT_FOUND).entity("Menu Not Created yet").build();        
	}
	
	
	@Path("/menu/{mid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayMenu(@PathParam("mid") int mid){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if(mm.DisplayMenu(mid) != null)
		{
			String s = gson.toJson(mm.DisplayMenu(mid));
			return Response.status(Response.Status.OK).entity(s).build();
		}
		
        return Response.status(Response.Status.NOT_FOUND).entity("Menu ID " + mid + " Not Found").build();        
	}
	
	@Path("/admin/menu")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response CreateMenu(String json, @Context UriInfo uriInfo){
		Gson gson = new Gson();
		MenuManager mm1 = gson.fromJson(json, MenuManager.class);
		int id = mm.CreateMenu(mm1);
		JsonObject jo = new JsonObject();
		if(id == -1)
			return Response.status(Response.Status.NOT_FOUND).entity("Something wrong with Count or MenuID").build();
		
		jo.addProperty("id", id);
				
		String s = gson.toJson(jo);
		
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(id));
		return Response.created(builder.build()).entity(s).build();
		        
	}
	
	
	@Path("/admin/menu/{mid}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response ModifyMenu(String json, @PathParam("mid") int mid){
		Gson gson = new Gson();
		MenuManager mm1 = gson.fromJson(json, MenuManager.class);
		int id = mm.ModifyMenuPrice(mm1, mid);
		JsonObject jo = new JsonObject();
		if(id == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Something wrong with MenuID/ MenuID not found").build();
		
		jo.addProperty("id", id);
				
		String s = gson.toJson(jo);
		
		return Response.status(Response.Status.NO_CONTENT).entity(s).build();
		        
	}
	
	@Path("/admin/surcharge")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplaySurcharge(){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		double surcharge_ = om.GetSurcharge();
		JsonObject jo = new JsonObject();		
		jo.addProperty("surcharge", surcharge_);
		
		String s = gson.toJson(jo);
		return Response.status(Response.Status.OK).entity(s).build();	        
	}
	
	
	@Path("/admin/surcharge")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response PostSurcharge(String json){
		Gson gson = new Gson();		
		OrderManager om1 = gson.fromJson(json, OrderManager.class);
		om.SetSurcharge(om1);
		
		return Response.status(Response.Status.OK).entity("Surcharge Modified").build();	        
	}
	
	
	@Path("/order")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayOrder(@DefaultValue("") @QueryParam("date") String date){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		if(date.equals(""))
		{
			if(om.DisplayOrder() != null)
			{
				String s = gson.toJson(om.DisplayOrder());	        
		        return Response.status(Response.Status.OK).entity(s).build();
			}
			return Response.status(Response.Status.NOT_FOUND).entity("No Orders Created Yet").build();
		}
		else
		{
			if(om.DisplayOrder(date) != null)
			{			
				String s = gson.toJson(om.DisplayOrder(date));	        
		        return Response.status(Response.Status.OK).entity(s).build();			
			}
			return Response.status(Response.Status.NOT_FOUND).entity("No Orders Created Yet").build();
		}
		
	}
	
	@Path("/order/{oid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayOrderByOid(@PathParam("oid") int oid){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if(om.DisplayOrder(oid) != null)
		{
			String s = gson.toJson(om.DisplayOrder(oid));
			return Response.status(Response.Status.OK).entity(s).build();
		}
		
        return Response.status(Response.Status.NOT_FOUND).entity("Order ID " + oid + " Not Found").build();
        
	}
	
	
	@Path("/order")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response CreateOrder(String json, @Context UriInfo uriInfo){
		Gson gson = new Gson();
		OrderManager om1 = gson.fromJson(json, OrderManager.class);
		int id = om.CreateOrder(om1);
		JsonObject jo = new JsonObject();
		if(id == -1)
			return Response.status(Response.Status.NOT_FOUND).entity("Something wrong with Count or MenuID").build();
		
		jo.addProperty("id", id);
				
		String s = gson.toJson(jo);
		
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(id));
		return Response.created(builder.build()).entity(s).build();
		        
	}
	
	
	@Path("/order/cancel/{oid}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response CancelOrder(@PathParam("oid") int oid){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if(om.CancelOrder(oid) != -1)
		{
			String s = gson.toJson(om.CancelOrder(oid));
			return Response.status(Response.Status.OK).entity(s).build();
		}
		
        return Response.status(Response.Status.NOT_FOUND).entity("Order ID " + oid + " Not Found").build();        
	}
	
	
	@Path("/customer")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response Displaycustomers(@DefaultValue("") @QueryParam("key") String nameStr){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		if(nameStr.equals(""))
		{
			if(cm.DisplayCustomers() != null)
			{
				String s = gson.toJson(cm.DisplayCustomers());
				return Response.status(Response.Status.OK).entity(s).build();
			}			
	        return Response.status(Response.Status.NOT_FOUND).entity("No Customers have ordered yet").build();
		}
		else
		{
			if(cm.DisplayCustomers(nameStr) != null)
			{			
				String s = gson.toJson(cm.DisplayCustomers(nameStr));	        
		        return Response.status(Response.Status.OK).entity(s).build();			
			}
			return Response.status(Response.Status.NOT_FOUND).entity("No Customers with name having: " + nameStr).build();
		}		
	}
	
	
	@Path("/customer/{cid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplaycustomersByCid(@PathParam("cid") int cid){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if(cm.DisplayCustomers(cid) != null)
		{
			String s = gson.toJson(cm.DisplayCustomers(cid));
			return Response.status(Response.Status.OK).entity(s).build();
		}
		
        return Response.status(Response.Status.NOT_FOUND).entity("No Customers with cId: " + cid).build();        
	}
	
	
	@Path("/report")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayReportsName(){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Reports r = new Reports();
		String s = gson.toJson(r);
        
        return Response.status(Response.Status.OK).entity(s).build();
	}
	
	@Path("/report/{rid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayReportsNameByRId(@PathParam("rid") int rid, @DefaultValue("") @QueryParam("start_date") String start_date, @DefaultValue("") @QueryParam("end_date") String end_date){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if(start_date.equals(""))
		{
			Reports r = new Reports(rid);
			List<Reports> rList = r.Get_Report(rid);				
			String s = gson.toJson(rList);
	        
	        return Response.status(Response.Status.OK).entity(s).build();
		}
		else
		{
			Reports r = new Reports(rid);
			List<Reports> rList = r.Get_Report(804, start_date);				
			String s = gson.toJson(rList);
	        
	        return Response.status(Response.Status.OK).entity(s).build();
		}
	}

}
