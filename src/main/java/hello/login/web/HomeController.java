package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * 로그인에 성공하면 홈화면으로 이동한다.
     * 이때, 기존 홈 화면의 회원가입, 로그인 버튼 -> 상품관리, 로그아웃 버튼으로 변경해야한다.
     * 이를 위해 쿠키에 저장된 memberId가 필요한데 @CookieValue를 이용해서 가져올 수 있다.
     */

//    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false)Long memberId, Model model){
        if(memberId == null){
            return "home";
        }

        //로그인 성공
        Member loginMember = memberRepository.findById(memberId);
        if(loginMember == null){
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model){

        Member member = (Member)sessionManager.getSession(request);

        //로그인
        if(member == null){
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }
}