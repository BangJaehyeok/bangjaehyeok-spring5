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
            <h1 class="m-0">${boardVO.board_type} 글쓰기</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">${boardVO.board_type} 게시물관리</li>
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
            <h3 class="card-title">등록</h3>
          </div>
          <!-- /.card-header -->
          <!-- form start -->
          <form name="form_write" method="post" action="/admin/board/board_update" enctype="multipart/form-data">
            <!-- 첨부파일 전송할때는 enctype="multipart/form-data"이게 필수다. 없으면 첨부파일 전송이 안됨.-->
            <!-- 전송할때 name을 지정해줘야함. input name처럼 name지정해줘야함. 안되면 서버로 전송이 안된다.
            POST라고 지정해주지 않으면 자동으로 get방식으로 된다. 그런데 비밀유지를 위해서 POST를 쓴다. 안그러면 비밀번호같은게 유출된다. -->
            <div class="card-body">
              <div class="form-group">
                <label for="board_type">게시판타입</label>
                <select name="board_type" class="form-control">
                 <c:forEach var="boardTypeVO" items="${listBoardTypeVO}">
                  <option ${boardVO.board_type==boardTypeVO.board_type?'selected':''} value="${boardTypeVO.board_type}">${boardTypeVO.board_name}</option>
                 </c:forEach>
                  
                </select>
              </div>
              <div class="form-group">
                <label for="title">제목</label>
                <input value="${boardVO.title}" name="title" id="title" class="form-control" placeholder="제목을 입력해주세요." required></textarea>
              </div>
              <div class="form-group">
                <label for="content">글내용</label>
                <textarea name="content" id="content" class="form-control" placeholder="내용을 입력해주세요.">${boardVO.content}</textarea>
              </div>
              <div class="form-group">
                <label for="writer">작성자</label>
                <input value="${boardVO.writer}" name="writer" id="writer" class="form-control" placeholder="작성자를 입력해주세요." required></textarea>
              </div>
              
              <div class="form-group">
                <label for="exampleInputFile">첨부파일</label>
                <c:forEach var="idx" begin="0" end="1">
                <div class="input-group div_file_delete">
                <!-- 위 div_file_delete 클래스 영역 이름은 업로드된 첨부파일을 개별 Ajax 삭제 할때  필요  -->
                  <div class="custom-file" >
                    <input name="file" type="file" class="custom-file-input" id="file_${idx}"><!-- id는 식별자 0,1,2,3... -->
                    <label class="custom-file-label" for="file_${idx}">파일선택</label>
                  </div>
                  <!-- 기존 업로드된 파일을 수정폼에 보여주기, 삭제버튼 필요(아래) -->
                  <c:if test="${boardVO.save_file_names[idx] != null}">
                  	<p class="text-muted">
                  	<a href="/download?save_file_name=${boardVO.save_file_names[idx]}&real_file_name=${boardVO.real_file_names[idx]}">
                  	${boardVO.real_file_names[idx]}</a>
                  	&nbsp;<button class="btn btn-info btn_file_delete">삭제</button>
                  	<input type="hidden" name="save_file_name" value="${boardVO.save_file_names[idx]}">
                  	</p>
                  </c:if>
                  </div>
                 <div class="mb-2"></div>
              </div>
              </c:forEach>
            
            </div>
            <!-- /.card-body -->
            <div class="card-footer text-right">
              <button type="submit" class="btn btn-primary">수정</button>
              <a href="/admin/board/board_view?bno=${boardVO.bno}&page=${pageVO.page}&search_type=${pageVO.search_type}" class="btn btn-warning">뷰화면</a>
            </div>
            <input name="page" value="${pageVO.page}" type="hidden">
            <input name="search_type" value="${pageVO.search_type}" type="hidden">
            <input name="bno" value="${boardVO.bno}" type="hidden">
          </form>
        </div>
        <!-- //콘텐츠 내용 -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<%@ include file="../include/footer.jsp" %>
<!-- 첨부파일명을 input태그 디자인 안쪽에 집어넣는 확장프로그램 -->
<script src="/resources/admin/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>
<!-- 위 첨부파일 확장프로그램 실행 -->
<script>
    $(document).ready(function(){
        bsCustomFileInput.init();
    });
</script>
<!-- 서머노트 웹에디터 실행(아래-개발자가 처리) -->
<script>
  $(document).ready(function(){
      // $('#content_lbl').summernote();//기본실행을 개발자가 커스터마이징한다.
      $('#content').summernote({
          height:150,
          lang:'ko-KR',
          placeholder:'글 내용을 입력해주세요.',
          toolbar: [
            ['fontname', ['fontname']],
            ['fontsize', ['fontsize']],
            ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
            ['color', ['forecolor','color']],
            ['table', ['table']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['insert',['link','video']],//'picture',
            ['view', ['fullscreen', 'help']]
        ],
    fontNames: ['Arial', 'Arial Black','맑은 고딕','Nanum Gothic','궁서','굴림체','굴림','돋음체'],
    fontSizes: ['8','10','12','14','16','18','20','24','28','30','36'],
          fontNamesIgnoreCheck: ['Nanum Gothic']
  	});
      //서머노트에서 html5의 required 속성 작동이 되지않아 아래코드 추가
      $("form[name='form_write']").on('submit',function(event){
    	  if($('#content').summernote('isEmpty')){
    		  alert('내용은 반드시 입력해주세요');
    		  event.preventDefault();//submit전송기능 사용금지
    	  }
      });
  });
</script>