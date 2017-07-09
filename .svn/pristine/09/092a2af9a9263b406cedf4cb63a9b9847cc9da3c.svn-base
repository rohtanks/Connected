<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>정선 맛집</title>
</head>
<body>
	<fieldset class="main-search ng-scope" ng-controller="mp20_main_search_suggest_controller">
		<legend>임시 검색 결과</legend>

<!-- 		<label for="main-search" class="search-word"> -->
				<div class id="resultbx">
					<table border="0" bgcolor="#000000" cellspacing="1">
						<tr bgcolor="ffffff">
							<th>분실물 ID</th>
							<th>습득물품명</th>
							<th>습득일자</th>
							<th>수령가능장소</th>
							<th>연락처</th>
							<th>습득품분류</th>
							<th>습득장소</th>
							<th>상세정보</th>
							<th>상태</th>
							<th>분류코드</th>
						</tr>
						<c:forEach var="vo" varStatus="status" items="${list }">
						<tr bgcolor="ffffff">
							<td>${vo.id }</td>
							<td>${vo.get_name }</td>
<%-- 							<td>${vo.url }</td> --%>
<%-- 							<td>${vo.title }</td> --%>
							<td>${vo.get_date }</td>
							<td>${vo.take_place }</td>
							<td>${vo.contact }</td>
							<td>${vo.cate }</td>
							<td>${vo.get_position }</td>
<%-- 							<td>${vo.get_place }</td> --%>
							<td>${vo.get_thing }</td>
							<td>${vo.status }</td>
							<td>${vo.code }</td>
<%-- 							<td>${vo.image_url }</td> --%>
						</tr>
						</c:forEach>
					</table>
				</div>
<!-- 		</label> -->
	</fieldset>
	
	<div>
		
	</div>

</body>
</html>