  <%@taglib uri="/struts-tags" prefix="s" %>
  
   <link rel="stylesheet" type="text/css" href="<s:url value="/Semantic-UI-master/dist/semantic.min.css"/>">
   <%@include file="/common/common_include_header.jsp" %>
 
  <style>
   body {
   	padding: 1em;
    /*background-color: #1B1C1D;*/
  }
  body, body.pushable>.pusher {
  	/*background-color: #1B1C1D;*/
  	background-color: #373534;
  }
  .ui.inverted.menu .item:before {
  	background: none;
  }
  .ui.menu:not(.vertical):not(.dataTables_paginate) > .item {
  	/*width: 120px;*/
  	width: 14%;
	justify-content: center;
	background: linear-gradient(#6E6E6E 50%, #2e2e2e 50%);
  }
  .ui.menu:not(.vertical):not(.dataTables_paginate) > .item:nth-child(6) {
  	width: 15%;
  }
  .ui.menu:not(.vertical):not(.dataTables_paginate) > .item:last-child::before {
  	width: 0;
  }
  span {
    word-wrap: break-word;
  }
  .ui.items>.item .meta {
    color: rgba(255,255,255,.9)
  }
   @media only screen and (max-width: 767px) {
	.ui.table:not(.unstackable) thead {
	    display: none;
	}
	.ui.table:not(.unstackable) tr {
		box-shadow: 0 -1px 0 0 rgba(0,0,0,1) inset!important;
	}
  }
  .ui.menu:not(.vertical):not(.dataTables_paginate) > .item.toc {
	display: none;
  }
  @media only screen and (max-width: 767px) {
  	.ui.menu:not(.vertical):not(.dataTables_paginate) > .item {
  		display: none;
  	}
  	.ui.menu:not(.vertical):not(.dataTables_paginate) > .item.toc {
  		display: block;
  	}
  }
  .ui.menu .dropdown.item .menu {
    background: rgba(27,27,27,1);
  }
  .ui.items>.item {
  	margin: 0;
  }
  .ui.sortable.table thead th {
  	/*color: rgba(255,255,255,.9);*/
  }
  /* .ui.inverted.menu {
  	background: linear-gradient(#6E6E6E 50%, #2e2e2e 50%);
  } */
  .ui.vertical.inverted.sidebar.menu {
  	background: #1B1C1D;
  }
  .ui.table tr th, .ui.structured.celled.table tr th,
  .ui.sortable.table thead th {
    background: #333333;
  }
  .ui.segment {
    background: rgba(27, 26, 26, 0.42);
	color: #CCCCCC;
  }
  .ui.header:first-child {
    margin-top: calc(2rem -  0.14285em );
  }
  </style>
  <script>
  $(document)
    .ready(function() {
      $('.ui.menu .ui.dropdown').dropdown({
        on: 'hover'
      });
      
      $('.ui.sidebar')
      .sidebar('attach events', '.toc.item')
    	;

      $("#searchList").dataTable({
    		"ordering":  false,
    		"dom": '<"ui grid"<"column right floated right aligned"l>><"ui grid"<" one column overflow"t>><"ui grid"<"left aligned eight wide column"i><"right floated eight wide column"p>>',
    		"language":{
    		    "lengthMenu": "Number of items to show _MENU_",
    		    "zeroRecords": "No matching items found",
    		    "infoEmpty": "Showing 0 to 0 of 0 items",
    		    "info": "Showing _START_ to _END_ of _TOTAL_ items",
    		    "infoFiltered": "(filtered from _MAX_ total items)"
    		},
    		"pagingType": "numbers",
    		"drawCallback": function() {
    		    var tableA_paginate = $("#searchList_paginate");
    		    var page_numbers = tableA_paginate.find("span").children().clone(true);
    		    tableA_paginate.empty().append(page_numbers);
    		    tableA_paginate.addClass("right floated ui pagination menu");
    		    tableA_paginate.children().each(function(k, v){
    		    	$(v).addClass("item");
    		        if($(v).hasClass("current")){
    		        	$(v).addClass("active");
    		        }
    		        if($(v).hasClass("ellipsis")){
    		        	$(v).addClass("disabled");
    		        }
    		    });
    		},
    		"initComplete": function(settings, json) {
    	        // Tidy up length select
    	        var tableA_length = $("#searchList_length");
    	        var select = tableA_length.find("select").clone(true);
    	        tableA_length.find("select").remove();
    	        select.attr("id", select.attr("name") + "_select").addClass("ui dropdown");
    	        tableA_length.find("label").attr("for", select.attr("name") + "_select");
    	        tableA_length.append(select);
    	        tableA_length.addClass("field");
    	        select.dropdown();
    	    }
    	  });
    })
  ;
  </script>