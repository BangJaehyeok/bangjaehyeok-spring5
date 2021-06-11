<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/header.jsp" %>

<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">회원 리스트</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">관리자 관리</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- 콘텐츠 내용 -->
        <div class="card">
          <div class="card-header">
            <h3 class="card-title">목록</h3>
            <div class="card-tools ">
        <!-- 내용검색 폼 -->
        <div class="input-group input-group-md">
          <form name="form_search" action="/admin/member/member_list" method="GET" class="form-horizental">
            <select name="search_type" class="form-control float-left"
            style="width: inherit;">
              <option value="all">전체</option>
              <option value="user_id">아이디</option>
              <option value="user_name">이름</option>
            </select>
          <input type="text" name="search_keyword" class="form-control float-left" placeholder="Search" style="width: inherit;">
          <div class="input-group-append float-left" style="width: inherit;">
            <button type="submit" class="btn btn-default">
            <i class="fas fa-search"></i>
            </button>
        </div>
        </div>
      </form>
      <!-- //내용검색 폼 -->
            </div>
          </div>
          <!-- /.card-header -->
          <div class="card-body table-responsive p-0">
            <table class="table table-hover"><!-- text-nowrap 문자줄바꿈안하기 -->
              <thead>
                <tr>
                  <th class="text-center">사용자ID</th>
                  <th class="text-center">사용자이름</th>
                  <th class="text-center">이메일</th>
                  <th class="text-center">레벨</th>
                  <th class="text-center">가입일자</th>
                </tr>
              </thead>
              <tbody>
              <!-- listMember객체 검색시 빈값일때  -->
              <c:if test="${empty listMember}">
              <tr>
              	<td colspan="5" class="text-center">조회된 값이 없습니다.</td>
              </tr>
              </c:if>
               <!-- jstl 반복문으로 listMember 객체 바인딩 -->
               <c:forEach var="memberVO" items="${listMember}">
               <tr style="cursor: pointer;" onclick="location.replace('/admin/member/member_view?user_id=${memberVO.user_id}');">
                 <td>${memberVO.user_id}</td>
                 <td>${memberVO.user_name}</td>
                 <td>${memberVO.email}</td>
                 <td>${memberVO.levels}</td>
                 <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss.SSSS" value="${memberVO.reg_date}" /></td>
               </tr>
               </c:forEach>
                
              </tbody>
            </table>
          </div>
          <!-- /.card-body -->
        </div>
    <!-- //콘텐츠 내용 -->
    <!-- 페이징 처리 -->
    <div class="col-12 text-right">
        <a href="/admin/member/member_insert" class="btn btn-primary mb-3">회원등록</a>
        
        <ul class="pagination justify-content-center">
        <li class="paginate_button page-item previous disabled" id="example2_previous">
            <a href="#" aria-controls="example2" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
        </li>
        
        <c:forEach begin="${pageVO.startPage}" end="${pageVO.endPage}" step="1">
        	<!-- jstl c:out value값에 java 삼항연상자 비교값을 구현 
        	c:out을 써서 출력하는 ExressionLanguage(표현언어) ${}
        	c:out은 해킹방지코드가 내장.-->
        	<c:out value="${(idx==pageVO.page)?'active':''}"></c:out>
	        <li class="paginate_button page-item active">
	            <a href="#" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">1</a>
	        </li>
        </c:forEach>
        
        <li class="paginate_button page-item next" id="example2_next">
            <a href="#" aria-controls="example2" data-dt-idx="7" tabindex="0" class="page-link">Next</a>
        </li>
        </ul>
    </div>
    <!-- //페이징 처리 -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<%@ include file="../include/footer.jsp" %>