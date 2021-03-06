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
            <h1 class="m-0">게시판 뷰/수정</h1>
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
            <h3 class="card-title">뷰/수정</h3>
          </div>
          <!-- /.card-header -->
          <!-- form start -->
          <form name="form_write" method="post" action="/admin/bbs_type/bbs_type_update" enctype="multipart/form-data">
            <div class="card-body">              
              <div class="form-group">
              <!-- 게시판타입은 PK(고유키)이다. 이것을 수정할 수 있으면 여기에 묶여있는 하위 게시물들은 소속이 사라진다. 그래서 수정불가. readonly를 써놓는다. -->
                <label for="board_type">게시판타입</label>
                <input readonly value="${boardTypeVO.board_type}" name="board_type" type="text" id="board_type" class="form-control" placeholder="게시판 타입을 입력해주세요." required />
              </div>
              <div class="form-group">
                <label for="board_name">게시판 이름</label>
                <input value="${boardTypeVO.board_name}" name="board_name" type="text" id="board_name" class="form-control" placeholder="게시판명을 입력해주세요." required />
              </div>
              <div class="form-group">
                <label for="board_sun">출력순서</label>
                <input value="${boardTypeVO.board_sun}" name="board_sun" type="number" id="board_sun" class="form-control" placeholder="출력순서를 입력해주세요." required />
              </div>
            </div>
            <!-- /.card-body -->

            <div class="card-footer text-right">
              <button type="submit" class="btn btn-primary">수정</button>
              <button type="button" class="btn btn-warning" id="btn_delete">삭제</button>
              <a href="/admin/bbs_type/bbs_type_list" class="btn btn-default">목록</a>
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
<script>
$(document).ready(function(){
	$("#btn_delete").click(function(){
		if(confirm("정말로 삭제하시겠습니까?")) {
			var form_write = $("form[name='form_write']");
			form_write.attr("action","/admin/bbs_type/bbs_type_delete");
			form_write.submit();
		}
	});
});
</script>