<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="jquery-3.2.1.min.js"></script>
    <script>
       function getCustomer(customerid)
       {
           $.get("/TravelExperts/rs/travelexperts/getcustomer/" + customerid,
          		 function(data){
				     $("#CustomerId").val(data.customerId);
				     $("#CustFirstName").val(data.custFirstName);
				     $("#CustLastName").val(data.custLastName);
				     $("#CustAddress").val(data.custAddress);
				     $("#CustCity").val(data.custCity);
				     $("#CustProv").val(data.custProv);
				     $("#CustPostal").val(data.custPostal);
				     $("#CustCountry").val(data.custCountry);
				     $("#CustHomePhone").val(data.custHomePhone);
				     $("#CustBusPhone").val(data.custBusPhone);
				     $("#CustEmail").val(data.custEmail);
				     $("#AgentId").val(data.agentId);
                 },
                 "json"
		   );
       }
    </script>
</head>
<body>
   <p>Select an Customer name to view Customer details</p>
   
   <select id="customerselect" onchange="getCustomer(this.value)">
      <option>Select an Customer</option>
   </select>
   <div id="customerdetail">
      Customer ID: <input type="number" id="CustomerId" disabled="disabled" /><br />
      First Name: <input type="text" id="CustFirstName" /><br />
      Last Name: <input type="text" id="CustLastName" /><br />
      Address: <input type="text" id="CustAddress" /><br />
      City: <input type="text" id="CustCity" /><br />
      Province: <input type="text" id="CustProv" /><br />
      Postal: <input type="text" id="CustPostal" /><br />
      Country: <input type="text" id="CustCountry" /><br />
      Home Phone: <input type="text" id="CustHomePhone" /><br />
      Business Phone: <input type="text" id="CustBusPhone" /><br />
      Email: <input type="text" id="CustEmail" /><br />
      Agent ID: <input type="number" id="AgentId" /><br />
   </div>
   <button id="update">Update Customer</button>
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
      
      $("#update").click(function(){
    	  data = '{ "customerId":"' + $("#CustomerId").val() 
    	  + '", "custFirstName":"' + $("#CustFirstName").val()
		  + '", "custLastName":"' + $("#CustLastName").val()
		  + '", "custAddress":"' + $("#CustAddress").val()
		  + '", "custCity":"' + $("#CustCity").val()
		  + '", "custProv":"' + $("#CustProv").val()
		  + '", "custPostal":"' + $("#CustPostal").val()
		  + '", "custCountry":"' + $("#CustCountry").val()
		  + '", "custHomePhone":"' + $("#CustHomePhone").val()
		  + '", "custBusPhone":"' + $("#CustBusPhone").val()
		  + '", "custEmail":"' + $("#CustEmail").val()
		  + '", "agentId":"' + $("#AgentId").val()
    		  + '" }';
    	  $.ajax({
    			  url:"/TravelExperts/rs/travelexperts/postcustomer",
    			  type:"POST",
    			  data:data,
    			  contentType:"application/json",
    			  cache:false,
    			  dataType:"html",
    			  success:function(data){ alert(data); }
    	  });
      });
  </script>

</body>
</html>