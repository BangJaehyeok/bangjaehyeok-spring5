<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../include/header.jsp" %>

<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">게시판 생성/수정</h1>
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
        <div class="card card-primary">
          <div class="card-header">
            <h3 class="card-title">등록/수정</h3>
          </div>
          <!-- /.card-header -->
          <!-- form start -->
          <form name="form_write" action="board_write.html" enctype="multipart/form-data">
            <!-- 첨부파일 전송할때는 enctype="multipart/form-data"이게 필수다. 없으면 첨부파일 전송이 안됨.-->
            <!-- 전송할때 name을 지정해줘야함. input name처럼 name지정해줘야함. 안되면 서버로 전송이 안된다.
            POST라고 지정해주지 않으면 자동으로 get방식으로 된다. 그런데 비밀유지를 위해서 POST를 쓴다. 안그러면 비밀번호같은게 유출된다. -->
            <div class="card-body">              
              <div class="form-group">
                <label for="board_type">게시판타입</label>
                <input value="${boardTypeVO.board_type}" name="board_type" type="text" id="board_type" class="form-control" placeholder="게시판 타입을 입력해주세요." required />
              </div>
              <div class="form-group">
                <label for="board_name">게시판 이름</label>
                <input value="${boardTypeVO.board_name}" name="board_name" type="text" id="board_name" class="form-control" placeholder="게시판명을 입력해주세요." required />
              </div>
              <div class="form-group">
                <label for="board_sun">출력순서</label>
                <input value="${boardTypeVO.board_sun}" name="board_sun" type="text" id="board_sun" class="form-control" placeholder="출력순서를 입력해주세요." required />
              </div>
            </div>
            <!-- /.card-body -->

            <div class="card-footer text-right">
              <button type="submit" class="btn btn-primary">등록</button>
              <a href="board_list.html" class="btn btn-default">목록</a>
            </div>
          </form>
        </div>
        <!-- //콘텐츠 내용 -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<%@ include file="../include/footer.jsp" %>