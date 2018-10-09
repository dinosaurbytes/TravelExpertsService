<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Get Customer</title>
<script src="jquery-3.2.1.min.js"></script>
    <script>
       function getCustomer(customerid)
       {
           $.get("/TravelExperts/rs/travelexperts/getcustomer/" + customerid,
          		 function(data){
				     var mystring = "<table border='1'><tr>";
				     mystring += "<td>" + data.customerId + "</td>";
				     mystring += "<td>" + data.custFirstName + "</td>";
				     mystring += "<td>" + data.custLastName + "</td>";
				     mystring += "<td>" + data.custAddress + "</td>";
				     mystring += "<td>" + data.custCity + "</td>";
				     mystring += "<td>" + data.custProv + "</td>";
				     mystring += "<td>" + data.custPostal + "</td>";
				     mystring += "<td>" + data.custCountry + "</td>";
				     mystring += "<td>" + data.custHomePhone + "</td>";
				     mystring += "<td>" + data.custBusPhone + "</td>";
				     mystring += "<td>" + data.custEmail + "</td>";
				     mystring += "<td>" + data.agentId + "</td>";
				     mystring += "</tr></table>";
				     $("#customerdetail").html(mystring);
                 },
                 "json"
		   );
       }
</script>
</head>
<body>
<p>Select a customer name to view customer details</p>

<select id="customerselect" onchange="getCustomer(this.value)">
   <option>Select a Customer</option>
</select>
<div id="customerdetail"></div>
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
	    	 	    		 + " "
	    	 	    		 + data[i].custLastName
	    	 	    		 + "</option>");
 	         }
          },
 		 "json");
   });
</script>


</body>
</html>