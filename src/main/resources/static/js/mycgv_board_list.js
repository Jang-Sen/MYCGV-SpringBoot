$(document).ready(function(){

	initAjax(1);

	function initAjax(page){
		$.ajax({
			url: "board_list_json_data/" + page ,
			success : function(result){

				//dhtml을 활용하여 테이블로 출력
				let output = "<table class='board_list'>";
				output += "<tr><td colspan='5'>";
				output += "<a href='/board_write'>";
				output += "<button type='button' class='btn_style2'>글쓰기</button>";
				output += "</a></td></tr>";
				output += "<tr><th>번호</th><th>제목</th><th>조회수</th><th>작성자</th><th>작성일자</th></tr>";
				
				for(obj of result.list){
					output += "<tr>";
					output += "<td>"+ obj.rno   +"</td>";
					output += "<td class='btitle' id='"+ obj.bid +"'><a>"+ obj.btitle  +"</a></td>";
					output += "<td>"+ obj.bhits  +"</td>";
					output += "<td>"+ obj.id  +"</td>";
					output += "<td>"+ obj.bdate  +"</td>";
					output += "</tr>";			
				}
				
				output += "<tr>";
				output += "<td colspan='5'><div id='ampaginationsm'></td>";			
				output += "</tr>";
				output += "</table>";
				
				//output을 출력
				$("table.board_list").remove();
				$("h1").after(output);

				$("#list").click(function (){
					$("table.board_content").remove();
					$("id")
				});

				// content(상세보기) 이벤트
				$('.btitle').click(function (){
					// alert($(this).attr("id"));
					contentAjax($(this).attr("id"), page);
				});
				
				//페이징 처리 함수 호출
				pager(result.page.dbCount, result.page.pageCount, result.page.pageSize, result.page.reqPage);
				
				//페이지 번호 클릭 이벤트 처리
				jQuery('#ampaginationsm').on('am.pagination.change',function(e){
			   		jQuery('.showlabelsm').text('The selected page no: '+e.page);
	           		// $(location).attr('href', "http://localhost:9000/board_list_json.do?page="+e.page);
	           	
	           		initAjax(e.page);
				});
				
			}//success
		
		});//ajax
	}//initAjax

	/**
	 * contentAjax : 상세보기
	 */
	function contentAjax(bid, page){
		// alert("bid --> " + bid + " page --> " + page);
		$.ajax({
			url: "board_content_json_data/" + bid,
			success: function (board) {
				// alert(board.bid);

				//dhtml을 활용하여 테이블로 출력
				let output = "<table class='board_list'>";
				output += "<a href='/board_content'></a>"
				output += "<tr>";
				output += "<th>제목</th>";
				output += "<th>" + board.btitle + "</th>";
				output += "</tr>";
				output += "<tr>";
				output += "<th>내용</th>";
				output += "<td>" + board.bcontent + "</td>";
				if (board.bsfile != null){

				}
				output += "</tr>";

				output += "<tr>";
				output += "<th>조회수</th>";
				output += "<td>" + board.bhits + "</td>";
				output += "</tr>";
				output += "<tr>";
				output += "<th>작성자</th>";
				output += "<td>" + board.id + "</td>";
				output += "</tr>";
				output += "<tr>";
				output += "<th>작성일자</th>";
				output += "<td>" + board.bdate + "</td>";
				output += "<tr><td colspan='2'>";
				output += "<a href='/board_update/' + board.bid + page>";
				output += "<button type='button' class='btn_style'>수정하기</button></a>"
				output += "</td>"
				output += "</tr>";

			}

		});
	}
	
	/* 페이징 처리 함수 */
	function pager(totals, maxSize, pageSize, page){
		//alert(totals+","+maxSize+","+pageSize+","+page);
		
		var pager = jQuery('#ampaginationsm').pagination({
		
		    maxSize: maxSize,	    		// max page size
		    totals: totals,	// total pages	
		    page: page,		// initial page		
		    pageSize: pageSize,			// max number items per page
		
		    // custom labels		
		    lastText: '&raquo;&raquo;', 		
		    firstText: '&laquo;&laquo;',		
		    prevText: '&laquo;',		
		    nextText: '&raquo;',
				     
		    btnSize:'sm'	// 'sm'  or 'lg'		
		});
	}
	

}); //ready








