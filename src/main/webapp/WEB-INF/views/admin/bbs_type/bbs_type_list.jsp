<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">게시판 리스트</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">게시판 생성관리</li>
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
        
            </div>
          </div>
          <!-- /.card-header -->
          <div class="card-body table-responsive p-0">
            <table class="table table-hover"><!-- text-nowrap 문자줄바꿈안하기 -->
              <thead>
                <tr>
                  <th class="text-center">BOARD_TYPE</th>
                  <th class="text-center">게시판 이름</th>
                  <th class="text-center">출력순서</th>
                </tr>
              </thead>
              <tbody>
                <!-- 아래 location링크주소에 jsp에서 프로그램처리예정 -->
                <tr style="cursor: pointer;" onclick="location.replace('/admin/bbs_type/bbs_type_update?board_type=notice');">
                  <td>NOTICE</td>
                  <td>공지사항</td>
                  <td>1</td>
                </tr>
              </tbody>
            </table>
          </div>
          <!-- /.card-body -->
        </div>
    <!-- //콘텐츠 내용 -->
    <!-- 페이징 처리 -->
    <div class="col-12 text-right">
        <a href="/admin/bbs_type/bbs_type_insert" class="btn btn-primary mb-3">게시판등록</a>
    </div>
    <!-- //페이징 처리 -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<%@ include file="../include/footer.jsp" %>