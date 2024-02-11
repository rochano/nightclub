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
  	.article {
    	margin-bottom: 50px;
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
 .label {
    font-style: normal;
}
.ui.grid > .column:not(.row).overflow-x {
	overflow-x: auto;
	overflow-y: hidden;
	padding-top: 0;
	padding-bottom: 0;
	margin-top: 1em;
	margin-bottom: 1em;
}
  </style>
  <script>
  var dataSet = [];
  var columnDefs = [];
  var pageLength = 10;
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
  		"dom": '<"ui grid"<"column right floated right aligned"l>><"ui grid"<" one column overflow-x"t>><"ui grid"<"left aligned eight wide column"i><"right floated eight wide column"p>>',
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
  	        select.dropdown('set selected', pageLength);
  	    },
  	 	"lengthMenu": [[10, 25, 50, 100, 150], [10, 25, 50, 100, 150]],
  	 	"pageLength": pageLength,
  	  });
    })
  ;
	  function addEventDateFormat(selector, form) {
		selector.blur(function(){
	  		  var input = $(this);
	  		  var value = $(this).val();
	  		  if (value.length == 8) {
	  				try {
	  					dd = value.substring(0,2);
	  					mm = value.substring(2,4);
	  					yyyy = value.substring(4);
	  					compare = new Date(yyyy + "-" + mm + "-" + dd);
	  					if (isNaN(compare)) {
							throw new Exception();
	  					}
	  					input.val(dd + "/" + mm + "/" + yyyy);
	  				} catch(e) {
	  					input.val("");
	  				}
	            } else if (value.length == 10) {
	          	  try {
	          		  	a = value.split("/");
	  					dd = a[0];
	  					mm = a[1];
	  					yyyy = a[2];
	  					compare = new Date(yyyy + "-" + mm + "-" + dd);
	  					if (isNaN(compare)) {
							throw new Exception();
	  					}
	  					input.val(dd + "/" + mm + "/" + yyyy);
	  				} catch(e) {
	  					input.val("");
	  				}
	            } else {
	          	  input.val("");
	            }
	  	  }).focus(function() {
	  		  var input = $(this);
	  		  var newValue = input.val().replaceAll("/", "");
	  		  input.val(newValue);
	  	  });
	  }
  </script>