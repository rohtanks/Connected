<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>분실물을 찾아보아요</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<body>
	<!-- 모바일 메인 -->
<!-- 	<div -->
<!-- 		style="background-image: url(resources/img/bg_main_pyeongchang1.jpg); background-repeat: no-repeat;"> -->
<!-- 		<form action="pc_gour_home.do" method="get"> -->
<!-- 			<h2>평창</h2> -->
<!-- 			<section> -->
<%-- 				<input type="hidden" name="test0" value="${test0 }" /> --%>
<!-- 				<input type="submit" value="맛집" /> -->
<%-- 				<button name="test1" value="${test1 }">숙박</button> --%>
<!-- 			</section> -->
<!-- 		</form> -->
<!-- 	</div> -->
	<!-- 	<div style="background-image: url(resources/img/bg_main_jeongseon.jpg); background-repeat: no-repeat;"> -->
	<!-- 		<section> -->
	<!-- 			<h1>정선</h1>Jeongseon-gun -->
	<!-- 			<button type="button" onclick="location.href='js_gour_home.do'">맛집</button> -->
	<!-- 			<button type="button">숙박</button> -->
	<!-- 		</section> -->
	<!-- 	</div> -->
	<!-- 	<div> -->
	<!-- 		올림픽 관련 소식 -->
	<!-- 		<table> -->
	<!-- 			<tr> -->
	<!-- 				<td>·</td> -->
	<!-- 				<td><a href="">관련기사1</a></td> -->
	<!-- 			</tr> -->
	<!-- 			<tr> -->
	<!-- 				<td>·</td> -->
	<!-- 				<td><a href="">관련기사2</a></td> -->
	<!-- 			</tr> -->
	<!-- 		</table> -->
	<!-- 	</div> -->
	<fieldset class="main-search ng-scope" ng-controller="mp20_main_search_suggest_controller">
		<legend>전체 검색</legend>

		<label for="main-search" class="search-word">
		<span class="icon">검색 항목 선택</span>
			<form action="search.do" method="get">
				<div class id="fkbx">
					<tr>
						<td>
							<select name="searchkey">
								<option value="sGet_name">습득물품명</option>
								<option value="sTake_place">수령가능장소</option>
								<option value="sGet_position">습득위치</option>
								<option value="searchTest">종합검색</option>
							</select>
							<div id="fkbx-text">검색창</div>
							<input type="text" name="searchbx" id="searchbx" placeholder="습득물품, 수령가능장소 또는 습득위치" />
							<input type="submit" value="검색" /></td>
					</tr>
				</div>
		</label>
	</fieldset>

</body>
</html>