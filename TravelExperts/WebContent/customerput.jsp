<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Customer</title>
<script src="jquery-3.2.1.min.js"></script>
</head>
<body>
   <p>Create a New Customer</p>
   
   <div id="customerdetail">
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
   <button id="insert">Insert Agent</button>
  <script>
	  $("#insert").click(function(){
		  data = '{ "customerId":"' + '0' 
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
			  url:"/TravelExperts/rs/travelexperts/putcustomer",
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