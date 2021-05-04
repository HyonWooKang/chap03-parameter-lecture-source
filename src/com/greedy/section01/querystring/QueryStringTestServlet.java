package com.greedy.section01.querystring;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QueryStringTestServlet
 */
@WebServlet("/querystring")
public class QueryStringTestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryStringTestServlet() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* HttpServlet 클래스의 Service 메소드가 요청 방식에 의해
		 * GET요청은 doGet()메소드로 호출하여 request, response를 전달해준다.
		 * 톰캣 서블릿 컨테이너가 해당 요청 url로 매핑된 Servlet 클래스의 인스턴스를 생성하여 service 메소드를 호출하고
		 * HttpServlet을 상속받아 오버라이딩한 현재 클래스의 doGet() 메소드가 동적바인딩에 의해 호출된다.
		 */
		
		// ** 기억하기 ** 넘어오는 값은 모두 String 타입이다.
		/*
		 * ** service로부터 전달받은 HttpServletRequest는 요청 시 전달한 값을 getParameter()메소드로 추출해올수 있다.
		 * 이때 인자로 input태그에 지정한 name 속성의 값을 문자열 형태로 전댈해주어야 한다.
		 * 하면에서 전달한 form 태그 내의 모든 input 태그 값들은 HashMap우로 관리하고 있는데, 원하는 값을 찾기 위해서는
		 * key 역할을 하는 문자열이 필요하기 때문에 name 속성을 쓴다.
		 */
		String name = request.getParameter("name"); // form태그로 싸서 보내는 key값은 name
		System.out.println("이름 : " + name);
		
		/*
		 * getParameter는 리턴 타입이 문자열 형태이다.
		 * 즉, 숫자로 전달해도 문자열 형태로 전달되는 것이다. 주의!!
		 * --> 숫자로 된 값이 필요하다면 검증 후 parsing 작업한다.
		 */
		int age = Integer.parseInt(request.getParameter("age"));
		System.out.println("나이 : " + age);
		
		/*
		 * java.sql.Date타입으로 저장하고 싶은 경우에는 전달된 parameter를 Date type으로 변환해야한다.
		 * java.sql.Date의 valueOf() 메소드에 전달받은 파라미터를 넘기면 타입을 변환해준다. (java parsing쪽에 나옴)
		 */
		// import 안하면 앞, 뒤로 모두 java....Date를 써줘라 
		java.sql.Date birthday = java.sql.Date.valueOf(request.getParameter("birthday"));
		System.out.println("생일 : " + birthday);
		
		
		/*
		 * radio버튼으로 전달된 값은 여러 값 중 한가지만 전달되기 때문에 parameter로 전달받은 값을 꺼내기만 하면 된다.
		 */
		String gender = request.getParameter("gender");
		System.out.println("성별 : " + gender);
		
		String national = request.getParameter("national");
		System.out.println("국적 : " + national);
		
		/*
		 * checkbox는 다중으로 입력을 할 수 있기 때문에 선택된 값이 문자열이 아닌 문자열 배열로 전달된다.
		 * 이 때, getParameterValues() 메소드를 이용하여 문자열 배열 형태로 값을 반환할 수 있다.
		 */
		String[] hobbies = request.getParameterValues("hobbies");
		for(String hobby : hobbies) {
			System.out.println("취미 : " + hobbies);			
		}
		// 만약 취미를 2개 이상 선택하면 페이지 url에서도 값이 2개 이상 들어간다.
		// 또한, (get방식이라서) 해당 url의 값을 바꾸면(music->sleep), console에 들어오는 값도 변경된다.
		// ==> 따라서 빠른 속도가 필요한 검색 사이트에서만 주로 사용된다. (보안low)
	}

}
