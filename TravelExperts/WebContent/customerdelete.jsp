<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Customer</title>
<script src="jquery-3.2.1.min.js"></script>
</head>
<body>
   <p>Select a customer to delete them</p>
   
   <select id="customerselect">
      <option>Select an Customer</option>
   </select>
  <script>
      $(document).ready(function(){
    	  $.get("/TravelExperts/rs/travelexperts/getallcustomers",
    		 		 function(data){
    		 	         for (i=0; i<data.length; i++)
    		 	         {
    			    	 	     $("#customerselect").append("<option value='" 
    			    	 	    		 + data[i].customerId
    			    	 	    		 + "'>"
    			    	 	    		 + data[i].custFirstName
    			    	 	    		 + "</option>");
    		 	         }
    		          },
    		 		 "json");
      });
      
      $("#customerselect").change(function(){
    	  if (confirm("Press OK to confirm the deletion"))
    	  {
    		  data = '{ "customerId":"' + $("#customerselect").val() + '" }';
	    	  $.ajax({
	    			  url:"/TravelExperts/rs/travelexperts/deletecustomer",
	    			  type:"DELETE",
	    			  data:data,
	    			  contentType:"application/json",
	    			  cache:false,
	    			  dataType:"html",
	    			  success:function(){ alert("Customer Deleted"); }
	    	  });
		  }
      });
  </script>

</body>
</html>