<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Package Post</title>
<script src="jquery-3.2.1.min.js"></script>
    <script>
       function getPackage(packageid)
       {
           $.get("/TravelExpertsProject/rs/packages/getpackage/" + packageid,
          		 function(data){
				     $("#PackageId").val(data.packageId);
				     $("#PkgName").val(data.pkgName);
				     $("#PkgStartDate").val(data.pkgStartDate);
				     $("#PkgEndDate").val(data.pkgEndDate);
				     $("#PkgDesc").val(data.pkgDesc);
				     $("#PkgBasePrice").val(data.pkgBasePrice);
				     $("#PkgAgencyCommission").val(data.pkgAgencyCommission);
				  },
                 "json"
		   );
       }
    </script>
</head>
<body>
<p>Select a package name to view details</p>
   
   <select id="packageselect" onchange="getPackage(this.value)">
      <option>Select an Package</option>
   </select>
   <div id="agentdetail">
      Package ID: <input type="number" id="PackageId" disabled="disabled" /><br />
      Package Name: <input type="text" id="PkgName" /><br />
      Start Date: <input type="text" id="PkgStartDate" /><br />
      End Date: <input type="text" id="PkgEndDate" /><br />
      Description: <input type="text" id="PkgDesc" /><br />
      Base Price: <input type="text" id="PkgBasePrice" /><br />
      Agency Commission: <input type="text" id="PkgAgencyCommission" /><br />
   </div>
   <button id="update">Update Package</button>
  <script>
   $(document).ready(function(){
       $.get("/TravelExpertsProject/rs/packages/getallpackages",
 		 function(data){
 	         for (i=0; i<data.length; i++)
 	         {
	    	 	     $("#packageselect").append("<option value='" 
	    	 	    		 + data[i].packageId
	    	 	    		 + "'>"
	    	 	    		 + data[i].pkgName
	    	 	    		 + "</option>");
 	         }
          },
 		 "json");
   });

      
      $("#update").click(function(){
    	  data = '{ "packageId":"' + $("#PackageId").val() 
    		  + '", "pkgName":"' + $("#PkgName").val()
    		  + '", "pkgStartDate":"' + $("#PkgStartDate").val()
    		  + '", "pkgEndDate":"' + $("#PkgEndDate").val()
    		  + '", "pkgDesc":"' + $("#PkgDesc").val()
    		  + '", "pkgBasePrice":"' + $("#PkgBasePrice").val()
    		  + '", "pkgAgencyCommission":"' + $("#PkgAgencyCommission").val()
    		  + '" }';
    	  $.ajax({
    			  url:"/TravelExpertsProject/rs/packages/postpackage",
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