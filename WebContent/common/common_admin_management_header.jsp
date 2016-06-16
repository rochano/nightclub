  <%@taglib uri="/struts-tags" prefix="s" %>
  <%@taglib uri="/struts-jquery-tags" prefix="sj" %>
  
  <s:head />
  <sj:head/>
  
  <%@include file="/common/common_include_header.jsp" %>
  <link rel="stylesheet" type="text/css" href="<s:url value="/Semantic-UI-master/dist/semantic.min.css"/>">
  
  <style>
   body {
    padding: 1em;
    background-color: #373534;
  }
  body.pushable>.pusher {
	background: #373534;
  }
  .ui.menu:not(.vertical):not(.dataTables_paginate) > .item {
  	width: 20%;
  }
  .ui.menu:not(.vertical) > .item > span {
  	display: block;
	margin: auto;
  }
  .ui.menu:not(.vertical) > .item:last-child::before {
  	width: 0;
  }
  .ui.inverted.form .inline.field>label {
  	 color: rgba(255,255,255,.9);
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
  .ui.items>.item {
  	margin: 0;
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
      $('.ui.accordion')
      .accordion()
    ;
      dataTable = $("#searchList").dataTable({
  		"ordering":  false,
  		"dom": '<"ui grid"<"column right floated right aligned"l>>t<"ui grid"<"eight wide column"i><"right floated eight wide column"p>>',
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