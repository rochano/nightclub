  <%@taglib uri="/struts-tags" prefix="s" %>

  <%@include file="/common/common_include_header.jsp" %>
  <link rel="stylesheet" type="text/css" href="<s:url value="/Semantic-UI-master/dist/semantic.min.css"/>">
  
  
  <style>
   body {
    /*padding: 1em;*/
    background-color: #373534;
  }
  body.pushable>.pusher {
	background: #373534;
  }
  .ui.menu:not(.vertical):not(.dataTables_paginate) > .item {
  	width: 14%;
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
  .ui.menu.toc {
	display: none;
  }
  @media only screen and (max-width: 767px) {
  	.toc .ui.menu:not(.dataTables_paginate) {
  		display: none;
  	}
  	.ui.menu.toc:not(.dataTables_paginate) { 
  		display: block;
  	}
  }
  .ui.items>.item {
  	margin: 0;
  }
    .full.height > .toc {
    position: relative;
    z-index: 1;
    background-color: #1b1c1d;
    -webkit-box-flex: 0;
    -webkit-flex: 0 0 auto;
    -ms-flex: 0 0 auto;
    flex: 0 0 auto;
  }
  .article {
    -webkit-box-flex: 1;
    -webkit-flex: 1 1 auto;
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    min-width: 0px;
}
.pusher > .full.height {
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    -webkit-flex-direction: row;
    -ms-flex-direction: row;
    flex-direction: row;
}
.ui.menu {
	margin: 0;
}
@media only screen and (max-width: 767px) {
  	.ui.form .inline.field>input, .ui.form .inline.field>select, .ui.form .inline.fields .field>input, .ui.form .inline.fields .field>select,
  	.ui.small.image, .ui.small.images .image, .ui.small.images img, .ui.small.images svg,
  	.ui[class*="five column"].grid>.column:not(.row), .ui[class*="five column"].grid>.row>.column,
  	.ui.form .inline.fields .field {
  		width: 100%;
  	}
  	.ui.container {
  		margin-left: 0!important;
  		margin-right: 0!important;
  	}
 }
.ui.image video {
	display: block;
	max-width: 100%;
	height: auto;
}
  </style>
  <script>
  var dataSet = [];
  var columnDefs = [];
  $(document)
    .ready(function() {
      $('.ui.menu .ui.dropdown').dropdown({
        on: 'hover'
      });
      $('.ui.sidebar')
      .sidebar('attach events', '.toc.item')
    	;
      $('.container > .ui.accordion')
      .accordion()
    ;
      dataTable = $("#searchList").dataTable({
		"data" : dataSet,
		"columnDefs" : columnDefs,
  		"ordering":  false,
  		"dom": '<"ui grid"<"column right floated right aligned"l>><"ui grid"<" one column overflow"t>><"ui grid"<"left aligned eight wide column"i><"right floated eight wide column"p>>',
  		"language":{
  		    "lengthMenu": "<s:i18n name="global_th"><s:text name="global.length_menu" /></s:i18n>",
  		    "zeroRecords": "<s:i18n name="global_th"><s:text name="global.zero_records" /></s:i18n>",
  		    "infoEmpty": "<s:i18n name="global_th"><s:text name="global.info_empty" /></s:i18n>",
  		    "info": "<s:i18n name="global_th"><s:text name="global.info" /></s:i18n>",
  		    "infoFiltered": "<s:i18n name="global_th"><s:text name="global.info_filtered" /></s:i18n>"
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