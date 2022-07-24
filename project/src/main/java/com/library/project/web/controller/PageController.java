package com.library.project.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {
	
	@GetMapping("/book/bookinsert")
	public String bookinsert() {
		return "book/bookinsert";
	}
	@GetMapping("/book/{bookcode}")
	public String bookupdate(@PathVariable String bookcode) {
		return "book/bookupdate";
	}
	@GetMapping("/book/bookdtl/{bookcode}")
	public String bookDtl(@PathVariable String bookcode) {
		return "book/bookdtl";
	}
	@GetMapping("/book/bookselect")
	public String bookselect(){
	return "book/bookselect";
	}
	@GetMapping("/book/bestbook")
	public String bestbook() {
	return  "book/bestbook";
	}
	@GetMapping("/book/newbook")
	public String newBook() {
		return "book/newbook";
	}
	
	//자유게시판
	@GetMapping("/book/bulletin")
	public String bulletin() {
		return "Open/bulletin";
	}
	
	@GetMapping("/book/bulletin/{boardcode}")
	public String updateBulletin() {
		return "Open/bulletinUpdate";
	}
	
	@GetMapping("/book/bulletinBoard/{boardcode}")
	public String bulletinBoard(@PathVariable int boardcode) {
		return "Open/bulletinBoard";
	}
	
	@GetMapping("/book/bulletinWriting")
	public String bulletinWriting() {
		return "Open/bulletinWriting";
	}
	
	//공지사항
	@GetMapping("/book/notice")
	public String notice() {
		return "Open/notice";
	}
	
	@GetMapping("/book/notice/{noticecode}")
	public String updateNotice() {
		return "Open/noticeUpdate";
	}
	
	@GetMapping("/book/noticeBoard/{noticecode}")
	public String noticeBoard() {
		return "Open/noticeBoard";
	}
	
	@GetMapping("/book/noticeWriting")
	public String noticeWriting() {
		return "Open/noticeWriting";
	}
	
	// 질문과 답변
	@GetMapping("/book/qAndA")
	public String qAndA() {
		return "Open/qAndA";
	}
	
	@GetMapping("/book/qAndA/{qnacode}")
	public String updateQnA() {
		return "Open/qAndAUpdate";
	}
	
	@GetMapping("/book/qAndABoard/{qnacode}")
	public String qAndABoard(@PathVariable int qnacode) {
		return "Open/qAndABoard";
	}
	
	@GetMapping("/book/qAndAWriting")
	public String QnAWriting() {
		return "Open/qAndAWriting";
	}
	
	
	//메인페이지
	@GetMapping("/mainpage")
	public String mainPage(Model model) {
		model.addAttribute("logo", "main");
		return "lib/mainPage";
	}
	
	//로그인창
	@GetMapping("/auth/login")
	public String login() {
		return "user/login";
	}
	
	//회원가입창
	@GetMapping("/auth/signup")
	public String signup() {
		return "user/signup";
	}
	
	
	//마이페이지(정보수정)
	@GetMapping("/user/mypage")
	public String mypage() {
		return "user/mypage";
	}
	
	//아이디찾기창
	@GetMapping("/auth/searchId")
	public String seachId() {
		return "user/searchId";
	}
	
	//비밀번호찾기창
	@GetMapping("/auth/searchPassword")
	public String seachPassword() {
		return "user/searchPassword";
	}
	
	//비밀번호찾기 - 새비밀번호 입력창
	@GetMapping("/newPassword")
	public String newPassword() {
		return "user/newPassword";
	}
	
	
	//비밀번호변경창
	@GetMapping("/user/changePassword")
	public String changePassword() {
		return "user/changePassword";
	}
	
	//회원탈퇴창
	@GetMapping("/user/userRemove")
	public String userWithdrawal() {
		return "user/userRemove";
	}
	
	//연체도서창
	@GetMapping("/lib/latebook")
	public String lateBook() {
		return "lib/lateBook";
	}
	
	//대여도서창 
	@GetMapping("/lib/loanbook")
	public String LoanBook() {
		return "lib/LoanBook";
	}
	
	@GetMapping("/lib/totalLoanBook")
	public String totalLoanBook() {
		return "manager/loanBook_manager";
	}
	
	@GetMapping("/lib/totalLateBook")
	public String totalLateBook() {
		return "manager/lateBook_manager";
	}
	
	//찾아 오시는길
	@GetMapping("/giude/libDirection")
	public String libDirection() {
		return "Guide/Lib_direction";
	}
	
	//이용안내
	@GetMapping("/guide/infoUse")
	public String infoUse() {
		return "Guide/Info_Use";
	}
	
	//시설현황
	@GetMapping("/guide/facilityStatus")
	public String facilityStatus() {
		return "Guide/FacilityStatus";
	}
}
