  <%@taglib uri="/struts-tags" prefix="s" %>
  
  <%
		String ua = request.getHeader( "User-Agent" );
		boolean isFirefox = ( ua != null && ua.indexOf( "Firefox/" ) != -1 );
		boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
		boolean isChrome = ( ua != null && ua.indexOf( "Chrome/" ) != -1 && ua.indexOf( "Safari/" ) != -1);
		boolean isSafari = ( ua != null && ua.indexOf( "Chrome/" ) == -1 && ua.indexOf( "Safari/" ) != -1);
		boolean isEdge = ( ua != null && ua.indexOf( "Edge/" ) == -1 && ua.indexOf( "Safari/" ) != -1);
		boolean isMobile = ( ua != null && ua.indexOf( "Mobile/" ) != -1);
	%>
  
  <link rel="stylesheet" type="text/css" href="<s:url value="/Semantic-UI-master/dist/semantic.css"/>">
  <%@include file="/common/common_include_header.jsp" %>

  
  <link rel="stylesheet" type="text/css" href="<s:url value="/slick/slick.css"/>"/> 
  <link rel="stylesheet" type="text/css" href="<s:url value="/slick/slick-theme.css"/>"/>
  <script type="text/javascript" src="<s:url value="/slick/slick.min.js"/>"></script>
  <style>
  .ui.menu:not(.vertical) > .item {
  	width: 33.33%;
	justify-content: center;
	border-width: 1px ;
	border-style: solid;
	border-color: #555555;
	border-width: 1px 1px 1px 0;
	background: linear-gradient(#6E6E6E 50%, #2e2e2e 50%);
  	font-size: 14px;
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
	.ui.menu:not(.vertical) > .item {
		font-size: 1rem;
	}
	.ui.fixed.menu + .ui.grid {
		padding-top: 0;
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
  .slide-image {
  	height: 500px;
  	overflow: hidden;
  }
  .slide-image img.ui.fluid.image {
  	height: 500px;
  	width:auto;
  }
  .inform {
	display: block;
	text-align: left;
  }
  .inform p {
    line-height: 1.5;
  }
  .ui.table tr td:first-child > p,
  .ui.table tr td:nth-child(2) > p {
  	color: #686868;
  }
  #adv1, #adv2 {
	display: none;
  }
    .ui.leaderboard.ad {
  	padding: 0;
  	width: 100%;
  	background: url('<s:url value="/assets/images/black_mamba.png" />');
  }
  .ui.leaderboard.ad img {
  	width: 100%;
  	max-height: 90px;
  	max-width: 660px;
  }
  .ui.leaderboard.ad.active {
  	background: inherit;
  }
  .ui.skyscraper.ad {
  	background: url('<s:url value="/assets/images/black_mamba.png" />')
  }
  .ui.skyscraper.ad img {
  	height: 100%;
  	max-height: 600px;
  	max-width: 160px;
  }
  .ui.skyscraper.ad.active {
  	background: inherit;
  }
  @media only screen and (max-width: 767px) {
  	.ui.menu:not(.vertical):not(.fixed)/*,
  	.ui.grid.menu-slide-image*/ {
  		display: none;
  	}
  	.ui.menu.toc:not(.vertical):not(.fixed) {
  		display: block;
  	}
  	.inform {
  		margin-top: 50px;
  	}
  	/* .ui.inverted.menu .active.item {overflow: auto ;}
	.ui.inverted.menu .active.item:hover {width: 100%;} */
	.ui[class*="wide skyscraper"].ad {
		display: none;
	}
	#wrapper > .ui.centered.grid > .row > .three.wide.column,
	#wrapper > .ui.centered.grid > .three.wide.column {
		display: none;
	}
	#adv1, #adv2 {
		display: block;
	}
  }
  @media only screen and (max-width: 992px) {
  	.ui[class*="wide skyscraper"].ad {
		width: 100% !important;
	}
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
	    width: auto;
	    margin: 1em 0;
	}
	  .ui.header.segment.header {
	  	margin-bottom: 0;
	  }
	  .ui.segment:first-child {
	  	margin-top: 1em;
	  }
	  .centered {
	  	text-align: center;
	  }
	  .ui.accordion .accordion {
	  	margin: 0;
	  }
	  .slick-list {
	  	background: inherit;
	  }
	  .ui.modal, .ui.modal>.actions {
	  	text-align: center;
	  }
	  .ui.breadcrumb.segment.attached {
	  	margin-top: 1rem;
	  }
	  .ui.fixed.menu {
	  	z-index:999;
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
      $('.ui.sidebar.menu')
      .sidebar('setting', {
         dimPage          : true,
         transition       : "overlay",
         mobileTransition : "overlay"
       })
      .sidebar('attach events', '.toc.item')
    	;
      $('.container > .ui.accordion')
      .accordion()
    ;
      $('.ui.sticky')
	    .sticky({
	      offset: 70,
	      context: '#wrapper'
	    });
      var key = 'thainightnavi_cookies', value = 'accept', age_days = 90;
      if(!document.cookie.includes(key + '=' + value)) {
				$('.ui.cookie.sidebar').sidebar('setting', {
			         dimPage          : true,
			         transition       : "overlay",
			         mobileTransition : "overlay",
			         closable         : false
			       }).sidebar('show');
				$('.ui.cookie.sidebar .ui.accept.button').on('click', function() {
					document.cookie = key + '=' + value + '; Path=/; SameSite=Strict; Max-Age=' + (60 * 60 * 24 * age_days);
					$('.ui.cookie.sidebar').sidebar('hide');
					/* $('.ui.basic.modal')
					.modal('setting', {
			         closable         : false
			       }).modal('show')
					; */
				});
			};

			$(".loadMore").click(function() {
				var this_ = $(this);
				loadMore(this_);
	    	})
	    if ($(".inform").length > 0) {
			if ($(".inform").text().trim() == "") {
				$(".inform").empty();
			}
		}

		extendsOnReady();
	});

	function loadMore(btn) {
		btn.empty()
		btn.text('...');
  		btn.attr("disabled","disabled");
	  	var userType = '<s:property value="userType" />';
		var feedOffset = $('.ui.card', '.ui.cards').length;
		$.getJSON("<s:url value="/ajax/loadMoreGirlInfos/" />" + feedOffset,
			$("#searchForm").serialize(),
			function(jsonResponse) {
				$.each(jsonResponse.girlInfos, function(i, obj) {
					var infoHtml = '';
					infoHtml += '<div class="ui red card">';
					infoHtml += '<div class="ui left red corner label girl-tag toggleGirlTag"';
					infoHtml += '	data-variation="tiny">';
					$.each(obj.girlTags, function(j, objGirlTag) {
						infoHtml += '		<div class="ui ' + objGirlTag.primaryKey.girlTagInfo.color + ' circular label">';
						infoHtml += '			' + objGirlTag.primaryKey.girlTagInfo.girlTagNameEn;
						infoHtml += '			<br />';
						infoHtml += '		</div>';
					});
					infoHtml += '</div>';
					infoHtml += '<div class="image ui centered corner labeled pic" >';
					infoHtml += '		<a href="' + getUrl('girl/' + obj.girlInfoId) + '">';
					infoHtml += getPic(obj);
					infoHtml += '		</a>';
					if (obj.allSame == 'true') {
						infoHtml += '<div class="ui right corner label green verified">';
						infoHtml += '<i class="icon"><s:text name="global.all_same" /></i>';
						infoHtml += '</div>';
					}
					infoHtml += '	</div>';
					infoHtml += '	<div class="content left aligned label pink circular ui">';
					infoHtml += '		<span class="right floated">';
					infoHtml += getPrice(obj);
					infoHtml += '		</span>';
					infoHtml += '	</div>';
					infoHtml += '	<div class="content left aligned">';
					infoHtml += '		<a class="ui header " href="' + getUrl('girl/' + obj.girlInfoId) + '">' + obj.nickName + '</a>';
					infoHtml += '		<div class="description">';
					infoHtml += getCustomDescription(obj);
					/* infoHtml += '			<i class="marker icon"></i>';
					if (obj.countryInfo) {
						infoHtml += '		' + obj.countryInfo.countryNameEn;
					}
					$.each(obj.girlProvinces, function(j, objProvince) {
						infoHtml += '					<div class="ui medium label">';
						infoHtml += objProvince.primaryKey.provinceInfo.provinceNameEn;
						infoHtml += '					</div>';
					}); */
					infoHtml += '			<i class="address card outline icon"></i>';
					if (obj.nationalityInfo) {
						infoHtml += '		' + obj.nationalityInfo.nationalityNameEn;
					} else {
						infoHtml += '		-';
					}
					infoHtml += '			<div>';
					infoHtml += '			<i class="globe icon"></i>';
					if (obj.countryInfo) {
						infoHtml += '		' + obj.countryInfo.countryNameEn;
					}
					infoHtml += '			</div>';
					infoHtml += '			<div>';
					if (obj.girlProvinces) {
						$.each(obj.girlProvinces, function(j, objGirlProvince) {
							infoHtml += '						' + objGirlProvince.primaryKey.provinceInfo.provinceNameEn;
							
						});
					}
					infoHtml += '			</div>';
					if (obj.girlLocations) {
						infoHtml += '					<div>';
						$.each(obj.girlLocations, function(k, objGirlLocations) {
							
							
							infoHtml += '								<span class="ui tiny label">';
							infoHtml += '									' + objGirlLocations.zoneInfo.zoneNameEn;
							infoHtml += '								</span>';
							if (obj.girlLocations.length > 3 && k == 2) {
								infoHtml += '								<span class="ui tiny label">';
								infoHtml += '									...';
								infoHtml += '								</span>';
								
								return false;
							}
						});
						infoHtml += '					</div>';
					}
					infoHtml += '		</div>';
					
					infoHtml += '	</div>';
					infoHtml += '</div>';
					$('.ui.cards').append(infoHtml);
				});
				btn.empty()
				btn.text('LOAD MORE');
				btn.removeAttr("disabled");
				$('.ui.sticky')
				  .sticky('refresh')
				;
		});
	}

	function extendsOnReady() {}

  	function getUrl(uri) {
  	  	var host = '<s:url value="//"/>';
		return host.substring(0, host.length-1) + uri;
	}

	function checkIsNotNullAndEmpty(value) {
		if (value != null && value != "") {
			return true;
		}
		return false;
	}

	function getImg(pic) {
		var picW160 = pic.replace('upload/', 'upload/w_160,c_scale/');
		return '<img class="image ui centered" src="' + picW160 + '">'
	}

	function getPic(obj) {
		if (checkIsNotNullAndEmpty(obj.pic1)) {
			return getImg(obj.pic1)
		}
		if (checkIsNotNullAndEmpty(obj.pic2)) {
			return getImg(obj.pic2)
		}
		if (checkIsNotNullAndEmpty(obj.pic3)) {
			return getImg(obj.pic3)
		}
		if (checkIsNotNullAndEmpty(obj.pic4)) {
			return getImg(obj.pic4)
		}
		if (checkIsNotNullAndEmpty(obj.pic5)) {
			return getImg(obj.pic5)
		}
		return getImg('<s:url value="/assets/images/wireframe/square-image.png" />');
	}

	function getPrice(obj) {
		var conditions = [
			{chk: obj.chk40Mins, price: obj.priceIncall40Mins, crcy: obj.crcy40Mins},
			{chk: obj.chk40Mins, price: obj.priceOutcall40Mins, crcy: obj.crcy40Mins},
			{chk: obj.chk60Mins, price: obj.priceIncall60Mins, crcy: obj.crcy60Mins},
			{chk: obj.chk60Mins, price: obj.priceOutcall60Mins, crcy: obj.crcy60Mins},
			{chk: obj.chk90Mins, price: obj.priceIncall90Mins, crcy: obj.crcy90Mins},
			{chk: obj.chk90Mins, price: obj.priceOutcall90Mins, crcy: obj.crcy90Mins},
			{chk: obj.chk120Mins, price: obj.priceIncall120Mins, crcy: obj.crcy120Mins},
			{chk: obj.chk120Mins, price: obj.priceOutcall120Mins, crcy: obj.crcy120Mins},
			{chk: obj.chk6Hrs, price: obj.priceIncall6Hrs, crcy: obj.crcy6Hrs},
			{chk: obj.chk6Hrs, price: obj.priceOutcall6Hrs, crcy: obj.crcy6Hrs},
		];

		for(var i in conditions) {
			var cond = conditions[i];
			if (cond.chk == 'true' && cond.price) {
				return cond.price.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " " + cond.crcy;
			}
		}
		return "Nego";
	}

	function getCustomDescription() {
		var infoHtml = "";
		return infoHtml;
	}
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