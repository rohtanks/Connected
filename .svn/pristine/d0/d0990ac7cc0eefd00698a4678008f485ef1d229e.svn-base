package com.connected.mobile;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private LostItemService liService; // Autowired를 필드에 직접 정의하는 경우 별도 setter 메소드는 정의하지 않아도 된다고 한다
	private static LostItemDailyTask lostItemDailyTask;

	String apiUrl;
	String KEY = "6578646b5674696d3834616e4c6f63";
	String TYPE = "xml";
	String SERVICE = "ListLostArticleService";
	int START_INDEX;
	int END_INDEX;
	String WB_CODE; // 습득물품코드 : b1 버스, b2 마을버스, s1 지하철(1~4호선), s2 지하철(5~8호선), s3 코레일,
					// s4 지하철(9호선), t1 법인택시, t2 개인택시
	String xmlResult;
	static String xml;
	
	String b1_totalCount;
	String b2_totalCount;
	String s1_totalCount;
	String s2_totalCount;
	String s3_totalCount;
	String s4_totalCount;
	String t1_totalCount;
	String t2_totalCount;

	String id = "";
	String get_name = "";
	String url = "";
	String title = "";
	String get_date = "";
	String take_place = "";
	String contact = "";
	String cate = "";
	String get_position = "";
	String get_place = "";
	String get_thing = "";
	String status = "";
	String code = "";
	String image_url = "";
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) { // 홈페이지, 습득물품코드별 자료수 조회를 위한 기능
		logger.info("home()");
		
		// Timer클래스가 실제 타이머 기능을 수행하는 클래스
		Timer li_timer = new Timer(true); // true : run background(종료되어도 Timer 객체 유지)
										  // false : 종료시 Timer 객체 free
		// LostItemDailyTask클래스는 Timer클래스가 수행되어야할 내용을 담은 클래스
		lostItemDailyTask = new LostItemDailyTask();
		lostItemDailyTask.setLiService(liService);
		
		// 5초 지연 후 10초에 한번씩 실행
		// 정확하게 특정 시간 마다 실행해야 하는 작업의 경우에는 schedule() 메소드가 아닌 scheduleAtFixedRate() 메소드를 사용하는 것이 좋다
//		li_timer.scheduleAtFixedRate(lostItemDailyTask, TimeUnit.SECONDS.toMillis(5), TimeUnit.SECONDS.toMillis(600));

//		model.addAttribute("test0", b1_totalCount); // 각 코드의 totalCount를 넘겨주는 객체
//		model.addAttribute("test1", b2_totalCount);
		
		return "home";
	}

	@RequestMapping(value = "/pc_gour_home.do", method = RequestMethod.GET)
	public ModelAndView pc_gour_home(HttpServletRequest request, LostItemVO vo) {
		logger.info("pc_gour_home()");
		
//		int test0 = Integer.parseInt(request.getParameter("test0"));
//		logger.info("test total_count : " + test0);
//		
////		int test1 = Integer.parseInt(request.getParameter("test1"));
////		System.out.println(test1);
//		int numOfRep = 0; // 반복 횟수를 구하기 위한 변수
//		if (test0 > 1000) {
//			numOfRep = (test0 / 1000) + 1; 
//		} else {
//			numOfRep = 1;
//		}
//		
//		KEY = "6578646b5674696d3834616e4c6f63";
//		TYPE = "xml";
//		SERVICE = "ListLostArticleService";
//		WB_CODE = "b2";
//
//		for (int i = 1; i <= numOfRep; i++) {
//			if (i == 1) {
//				START_INDEX = i;
//			} else {
//				START_INDEX += 1000;
//			}
//			END_INDEX = 1000 * i;
////			logger.info("START_INDEX : " + String.valueOf(START_INDEX));
////			logger.info("END_INDEX : " + String.valueOf(END_INDEX));
//			
//			apiUrl = "http://openAPI.seoul.go.kr:8088/" + KEY + "/" + TYPE + "/" + SERVICE + "/" + START_INDEX + "/" + END_INDEX + "/" + WB_CODE + "/";
//			
//			try {
//				// REST API URL을 읽어들여 결과 출력한다
//				URL url = new URL(apiUrl);
//				
//				InputStream is = url.openStream();
//				InputStreamReader isr = new InputStreamReader(is, "utf-8"); // 인코딩
//				BufferedReader reader = new BufferedReader(isr);
//				StringBuffer buffer = new StringBuffer();
//				String line = null;
//				String tmpStr = null;
//				while ((line = reader.readLine()) != null) {
//					tmpStr = line.toString();
//					tmpStr = tmpStr.replaceAll(" ", "");
//					
//					if (!tmpStr.equals(""))
//						buffer.append(line).append("\r\n");
//				}
//				reader.close();
//				
//				// REST API 결과값
//				xmlResult = buffer.toString();
//				
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
////		logger.info("RESULT => " + xmlResult);
//			
//			xml = xmlResult;
//			
//			Document mDocument = getXMLDocument();
//			logger.info("파싱준비 완료...");
//			
//			Element mElement = mDocument.getDocumentElement();
////		System.out.println(mElement.getNodeName());
//			NodeList ids = mElement.getElementsByTagName("ID");
////		System.out.println("ids.getLength() : " + ids.getLength());
//			NodeList get_names = mElement.getElementsByTagName("GET_NAME");
//			NodeList urls = mElement.getElementsByTagName("URL");
//			NodeList titles = mElement.getElementsByTagName("TITLE");
//			NodeList get_dates = mElement.getElementsByTagName("GET_DATE");
//			NodeList take_places = mElement.getElementsByTagName("TAKE_PLACE");
//			NodeList contacts = mElement.getElementsByTagName("CONTACT");
//			NodeList cates = mElement.getElementsByTagName("CATE");
//			NodeList get_positions = mElement.getElementsByTagName("GET_POSITION");
//			NodeList get_places = mElement.getElementsByTagName("GET_PLACE");
//			NodeList get_things = mElement.getElementsByTagName("GET_THING");
//			NodeList statuss = mElement.getElementsByTagName("STATUS");
//			NodeList codes = mElement.getElementsByTagName("CODE");
//			NodeList image_urls = mElement.getElementsByTagName("IMAGE_URL");
//			
//			for (int j = 0; j < ids.getLength(); j++) {
//				Node tem_id = ids.item(j);
//				Node tem_get_name = get_names.item(j);
//				Node tem_url = urls.item(j);
//				Node tem_title = titles.item(j);
//				Node tem_get_date = get_dates.item(j);
//				Node tem_take_place = take_places.item(j);
//				Node tem_contact = contacts.item(j);
//				Node tem_cate = cates.item(j);
//				Node tem_get_position = get_positions.item(j);
//				Node tem_get_place = get_places.item(j);
//				Node tem_get_thing = get_things.item(j);
//				Node tem_status = statuss.item(j);
//				Node tem_code = codes.item(j);
//				Node tem_image_url = image_urls.item(j);
//				
//				Node n_id = tem_id.getFirstChild();
//				Node n_get_name = tem_get_name.getFirstChild();
//				Node n_url = tem_url.getFirstChild();
//				Node n_title = tem_title.getFirstChild();
//				Node n_get_date = tem_get_date.getFirstChild();
//				Node n_take_place = tem_take_place.getFirstChild();
//				Node n_contact = tem_contact.getFirstChild();
//				Node n_cate = tem_cate.getFirstChild();
//				Node n_get_position = tem_get_position.getFirstChild();
//				Node n_get_place = tem_get_place.getFirstChild();
//				Node n_get_thing = tem_get_thing.getFirstChild();
//				Node n_status = tem_status.getFirstChild();
//				Node n_code = tem_code.getFirstChild();
//				Node n_image_url = tem_image_url.getFirstChild();
//				
//				try {
//					id = n_id.getNodeValue();
//					get_name = n_get_name.getNodeValue();
//					url = n_url.getNodeValue();
//					title = n_title.getNodeValue();
//					get_date = n_get_date.getNodeValue();
//					take_place = n_take_place.getNodeValue();
//					contact = n_contact.getNodeValue();
//					cate = n_cate.getNodeValue();
//					get_position = n_get_position.getNodeValue();
//					get_place = n_get_place.getNodeValue();
//					get_thing = n_get_thing.getNodeValue();
//					status = n_status.getNodeValue();
//					code = n_code.getNodeValue();
//					image_url = n_image_url.getNodeValue();
//					
//				} catch (Exception e) {
////				e.printStackTrace();
//				} finally {
//					if (n_id != null) {
//						id = n_id.getNodeValue();
//					}
//					if (n_get_name != null) {
//						get_name = n_get_name.getNodeValue();
//					}
//					if (n_url != null) {
//						url = n_url.getNodeValue();
//					}
//					if (n_title != null) {
//						title = n_title.getNodeValue();
//					}
//					if (n_get_date != null) {
//						get_date = n_get_date.getNodeValue();
//					}
//					if (n_take_place != null) {
//						take_place = n_take_place.getNodeValue();
//					}
//					if (n_contact != null) {
//						contact = n_contact.getNodeValue();
//					}
//					if (n_cate != null) {
//						cate = n_cate.getNodeValue();
//					}
//					if (n_get_position != null) {
//						get_position = n_get_position.getNodeValue();
//					}
//					if (n_get_place != null) {
//						get_place = n_get_place.getNodeValue();
//					}
//					if (n_get_thing != null) {
//						get_thing = n_get_thing.getNodeValue();
//					}
//					if (n_status != null) {
//						status = n_status.getNodeValue();
//					}
//					if (n_code != null) {
//						code = n_code.getNodeValue();
//					}
//					if (n_image_url != null) {
//						image_url = n_image_url.getNodeValue();
//					}
//					
//					// System.out.print("id:" + id + " ");
//					// System.out.print("get_name:" + get_name + " ");
//					// System.out.print("url:" + url + " ");
//					// System.out.print("title:" + title + " ");
//					// System.out.print("get_date:" + get_date + " ");
//					// System.out.print("take_place:" + take_place + " ");
//					// System.out.print("contact:" + contact + " ");
//					// System.out.print("cate:" + cate + " ");
//					// System.out.print("get_position:" + get_position + " ");
//					// System.out.print("get_place:" + get_place + " ");
//					// System.out.print("get_thing:" + get_thing + " ");
//					// System.out.print("status:" + status + " ");
//					// System.out.print("code:" + code + " ");
//					// System.out.println("iamge_url:" + image_url);
//					
//					vo.setId(id);
//					vo.setGet_name(get_name);
//					vo.setUrl(url);
//					vo.setTitle(title);
//					vo.setGet_date(get_date);
//					vo.setTake_place(take_place);
//					vo.setContact(contact);
//					vo.setCate(cate);
//					vo.setGet_position(get_position);
//					vo.setGet_place(get_place);
//					vo.setGet_thing(get_thing);
//					vo.setStatus(status);
//					vo.setCode(code);
//					vo.setImage_url(image_url);
//				int flag = liService.li_insert(vo); //한번 db에 저장을 하면 기본키 중복으로
//					// 에러가 나기때문에 다시 api를 호출 했을때 중복검사해서 추가/삭제를 할 것인지
//					// 아님 아예 기본키를 없애고 새롭게 덮어쓸 것인지 생각 두번째의 경우 db를 쓸 필요가 있나?
//					
//				}
//			}
//		}

		return new ModelAndView("gourmet/pc_gourmet");

	}

	@RequestMapping(value = "/gn_gour_home.do", method = RequestMethod.GET)
	public String gn_gour_home(Model model, LostItemVO vo) {
		logger.info("gn_gour_home()");

		return "gourmet/gn_gourmet";
	}

	@RequestMapping(value = "/js_gour_home.do", method = RequestMethod.GET)
	public String js_gour_home(Model model) {

		return "gourmet/js_gourmet";
	}

	@RequestMapping(value = "/search.do", method = RequestMethod.GET)
	public String search(String searchkey, String searchbx, Model model) {
		logger.info("search()");
		logger.info("searchkey : " + searchkey);
		logger.info("searchbx : " + searchbx);
		
		List<LostItemVO> list = liService.search(searchkey, searchbx);
		logger.info("list.size() : "+ list.size());
		model.addAttribute("list", list);
		return "gourmet/js_gourmet";
	}

}
