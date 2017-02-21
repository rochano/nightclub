  <%@taglib uri="/struts-tags" prefix="s" %>
  
  <link rel="stylesheet" type="text/css" href="<s:url value="/Semantic-UI-master/dist/semantic.min.css"/>">
  <%@include file="/common/common_include_header.jsp" %>

  
  <link rel="stylesheet" type="text/css" href="<s:url value="/slick/slick.css"/>"/> 
  <link rel="stylesheet" type="text/css" href="<s:url value="/slick/slick-theme.css"/>"/>
  <script type="text/javascript" src="<s:url value="/slick/slick.min.js"/>"></script>
  <style>
  .ui.menu:not(.vertical) > .item {
  	width: 20%;
	justify-content: center;
	border-width: 1px ;
	border-style: solid;
	border-color: #555555;
	border-width: 1px 1px 1px 0;
	background: linear-gradient(#6E6E6E 50%, #2e2e2e 50%);
  }
  .ui.menu:not(.vertical) > .item:first-child {
  	border-left-width: 1px;
  }
   @media only screen and (max-width: 767px) {
	.ui.table:not(.unstackable) thead {
	    display: none;
	}
	.ui.table:not(.unstackable) tr {
		box-shadow: 0 -1px 0 0 rgba(0,0,0,0.5) inset!important;
	}
  }
  .ui.inverted.menu .item:before {
  	background: none;
  }
  .ui.menu.toc:not(.vertical) {
	display: none;
  }
  .ui.menu.toc:not(.vertical) > .item {
	justify-content: flex-start;
  }
  @media only screen and (max-width: 767px) {
  	.ui.menu:not(.vertical) {
  		display: none;
  	}
  	.ui.menu.toc:not(.vertical) {
  		display: block;
  	}
  	.ui.inverted.menu .active.item {overflow: auto ;}
	.ui.inverted.menu .active.item:hover {width: 100%;}
  }
  .ui.menu .dropdown.item .menu {
    background: rgba(27,27,27,1);
  }
  .ui.menu .ui.dropdown .menu>.item {
    color: #FFF !important;
  }
  .ui.menu .ui.dropdown .menu>.item:hover {
  	color: #FFF !important;
  }
  pre {
  	word-wrap: break-word;
  }
  .ui.table tr th, .ui.structured.celled.table tr th,
	.ui.sortable.table thead th {
		background: #333333;
	}
	.ui.segment {
		background: rgba(27, 26, 26, 0.42);
		color: #CCCCCC;
	}
	.ui.leaderboard.ad {
	    max-width: 728px;
	    width: auto;
	}
  </style>
  <script>
  $(document)
    .ready(function() {
      $('.ui.menu .ui.dropdown').dropdown({
        on: 'hover'
      });
      $('.single-item').slick({
    	  autoplay: true,
    	  autoplaySpeed: 3000,
    	  arrows: true
      });
      $('.ui.sidebar')
      .sidebar('attach events', '.toc.item')
    	;
      $('.ui.accordion')
      .accordion()
    })
  ;
  </script>