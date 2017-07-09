package com.connected.mobile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LostItemDailyTask extends TimerTask {
	// 특정 시간이 되면 DB를 비우고 API를 호출해서 파싱 후 DB에 저장하기 위한 클래스

	private static final Logger logger = LoggerFactory.getLogger(LostItemDailyTask.class);
	private LostItemService d_liService;

	public void setLiService(LostItemService liService) {
		this.d_liService = liService;
	}

	public LostItemService getD_liService() {
		return d_liService;
	}

	String apiUrl;
	String KEY = "6578646b5674696d3834616e4c6f63";
	String TYPE = "xml";
	String SERVICE = "ListLostArticleService";
	int START_INDEX;
	int END_INDEX;
	String WB_CODE; // 습득물품코드 : b1 버스, b2 마을버스, s1 지하철(1~4호선), s2 지하철(5~8호선), s3
					// 코레일,
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

	String[] WB_CODE_ARR = { "b1", "b2", "s1", "s2", "s3", "s4", "t1", "t2" };
	String[] totalCount_arr;

	@Override
	public void run() {
		logger.info("LostItemDailyTask");
		// 1. 기존에 저장된 DB 비우기
		try {
			int d_flag = d_liService.li_delete();
			logger.info(d_flag + " 건 delete 완료");

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 2. API 호출해서 습득물품코드별 자료 수를 파악
		START_INDEX = 1;
		END_INDEX = 2;

		totalCount_arr = new String[8];

		for (int i = 0; i < WB_CODE_ARR.length; i++) {
			apiUrl = "http://openAPI.seoul.go.kr:8088/" + KEY + "/" + TYPE + "/" + SERVICE + "/" + START_INDEX + "/"
					+ END_INDEX + "/" + WB_CODE_ARR[i] + "/";
			try {
				// REST API URL을 읽어들여 결과 출력한다
				URL url = new URL(apiUrl);

				InputStream is = url.openStream();
				InputStreamReader isr = new InputStreamReader(is, "utf-8"); // 인코딩
				BufferedReader reader = new BufferedReader(isr);
				StringBuffer buffer = new StringBuffer();
				String line = null;
				String tmpStr = null;
				while ((line = reader.readLine()) != null) {
					tmpStr = line.toString();
					tmpStr = tmpStr.replaceAll(" ", "");

					if (!tmpStr.equals(""))
						buffer.append(line).append("\r\n");
				}
				reader.close();

				// REST API 결과값
				xmlResult = buffer.toString();

				// logger.info("RESULT => "+xmlResult);

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

			xml = xmlResult;

			Document mDocument = getXMLDocument();

			Element mElement = mDocument.getDocumentElement();
			NodeList totalCounts = mElement.getElementsByTagName("list_total_count");
			Node tem_totalCount = totalCounts.item(0);

			try {
				Node n_totlaCount = tem_totalCount.getFirstChild();
				totalCount_arr[i] = n_totlaCount.getNodeValue();

			} catch (NullPointerException e) {
				logger.info("총 조회 수 중 null이 있다");
				// e.printStackTrace();
				totalCount_arr[i] = "0";
			}
			// logger.info(totalCount_arr[i]);
		}

		b1_totalCount = totalCount_arr[0];
		b2_totalCount = totalCount_arr[1];
		s1_totalCount = totalCount_arr[2];
		s2_totalCount = totalCount_arr[3];
		s3_totalCount = totalCount_arr[4];
		s4_totalCount = totalCount_arr[5];
		t1_totalCount = totalCount_arr[6];
		t2_totalCount = totalCount_arr[7];
		logger.info("b1_totalCount : " + b1_totalCount);
		logger.info("b2_totalCount : " + b2_totalCount);
		logger.info("s1_totalCount : " + s1_totalCount);
		logger.info("s2_totalCount : " + s2_totalCount);
		logger.info("s3_totalCount : " + s3_totalCount);
		logger.info("s4_totalCount : " + s4_totalCount);
		logger.info("t1_totalCount : " + t1_totalCount);
		logger.info("t2_totalCount : " + t2_totalCount);

		// 3. API 호출해서 파싱한 후 DB에 저장
		for (int i = 0; i < WB_CODE_ARR.length; i++) {
			int test0 = Integer.parseInt(totalCount_arr[i]);
			logger.info("test total_count : " + test0);

			int numOfRep = 0; // 반복 횟수를 구하기 위한 변수
			if (test0 > 1000) {
				numOfRep = (test0 / 1000) + 1;
			} else {
				numOfRep = 1;
			}

			WB_CODE = WB_CODE_ARR[i];

			for (int j = 1; j <= numOfRep; j++) {
				if (j == 1) {
					START_INDEX = j;
				} else {
					START_INDEX += 1000;
				}
				END_INDEX = 1000 * j;
				// logger.info("START_INDEX : " + String.valueOf(START_INDEX));
				// logger.info("END_INDEX : " + String.valueOf(END_INDEX));

				apiUrl = "http://openAPI.seoul.go.kr:8088/" + KEY + "/" + TYPE + "/" + SERVICE + "/" + START_INDEX + "/"
						+ END_INDEX + "/" + WB_CODE + "/";

				try {
					// REST API URL을 읽어들여 결과 출력한다
					URL url = new URL(apiUrl);

					InputStream is = url.openStream();
					InputStreamReader isr = new InputStreamReader(is, "utf-8"); // 인코딩
					BufferedReader reader = new BufferedReader(isr);
					StringBuffer buffer = new StringBuffer();
					String line = null;
					String tmpStr = null;
					while ((line = reader.readLine()) != null) {
						tmpStr = line.toString();
						tmpStr = tmpStr.replaceAll(" ", "");

						if (!tmpStr.equals(""))
							buffer.append(line).append("\r\n");
					}
					reader.close();

					// REST API 결과값
					xmlResult = buffer.toString();

				} catch (IOException e) {
					e.printStackTrace();
				}
				// logger.info("RESULT => " + xmlResult);

				xml = xmlResult;

				Document mDocument = getXMLDocument();
				logger.info("파싱준비 완료...");

				Element mElement = mDocument.getDocumentElement();
				// System.out.println(mElement.getNodeName());
				NodeList ids = mElement.getElementsByTagName("ID");
				// System.out.println("ids.getLength() : " + ids.getLength());
				NodeList get_names = mElement.getElementsByTagName("GET_NAME");
				NodeList urls = mElement.getElementsByTagName("URL");
				NodeList titles = mElement.getElementsByTagName("TITLE");
				NodeList get_dates = mElement.getElementsByTagName("GET_DATE");
				NodeList take_places = mElement.getElementsByTagName("TAKE_PLACE");
				NodeList contacts = mElement.getElementsByTagName("CONTACT");
				NodeList cates = mElement.getElementsByTagName("CATE");
				NodeList get_positions = mElement.getElementsByTagName("GET_POSITION");
				NodeList get_places = mElement.getElementsByTagName("GET_PLACE");
				NodeList get_things = mElement.getElementsByTagName("GET_THING");
				NodeList statuss = mElement.getElementsByTagName("STATUS");
				NodeList codes = mElement.getElementsByTagName("CODE");
				NodeList image_urls = mElement.getElementsByTagName("IMAGE_URL");

				for (int k = 0; k < ids.getLength(); k++) {
					Node tem_id = ids.item(k);
					Node tem_get_name = get_names.item(k);
					Node tem_url = urls.item(k);
					Node tem_title = titles.item(k);
					Node tem_get_date = get_dates.item(k);
					Node tem_take_place = take_places.item(k);
					Node tem_contact = contacts.item(k);
					Node tem_cate = cates.item(k);
					Node tem_get_position = get_positions.item(k);
					Node tem_get_place = get_places.item(k);
					Node tem_get_thing = get_things.item(k);
					Node tem_status = statuss.item(k);
					Node tem_code = codes.item(k);
					Node tem_image_url = image_urls.item(k);

					Node n_id = tem_id.getFirstChild();
					Node n_get_name = tem_get_name.getFirstChild();
					Node n_url = tem_url.getFirstChild();
					Node n_title = tem_title.getFirstChild();
					Node n_get_date = tem_get_date.getFirstChild();
					Node n_take_place = tem_take_place.getFirstChild();
					Node n_contact = tem_contact.getFirstChild();
					Node n_cate = tem_cate.getFirstChild();
					Node n_get_position = tem_get_position.getFirstChild();
					Node n_get_place = tem_get_place.getFirstChild();
					Node n_get_thing = tem_get_thing.getFirstChild();
					Node n_status = tem_status.getFirstChild();
					Node n_code = tem_code.getFirstChild();
					Node n_image_url = tem_image_url.getFirstChild();

					try {
						id = n_id.getNodeValue();
						get_name = n_get_name.getNodeValue();
						url = n_url.getNodeValue();
						title = n_title.getNodeValue();
						get_date = n_get_date.getNodeValue();
						take_place = n_take_place.getNodeValue();
						contact = n_contact.getNodeValue();
						cate = n_cate.getNodeValue();
						get_position = n_get_position.getNodeValue();
						get_place = n_get_place.getNodeValue();
						get_thing = n_get_thing.getNodeValue();
						status = n_status.getNodeValue();
						code = n_code.getNodeValue();
						image_url = n_image_url.getNodeValue();

					} catch (Exception e) {
						// e.printStackTrace();
					} finally {
						if (n_id != null) {
							id = n_id.getNodeValue();
						}
						if (n_get_name != null) {
							get_name = n_get_name.getNodeValue();
						}
						if (n_url != null) {
							url = n_url.getNodeValue();
						}
						if (n_title != null) {
							title = n_title.getNodeValue();
						}
						if (n_get_date != null) {
							get_date = n_get_date.getNodeValue();
						}
						if (n_take_place != null) {
							take_place = n_take_place.getNodeValue();
						}
						if (n_contact != null) {
							contact = n_contact.getNodeValue();
						}
						if (n_cate != null) {
							cate = n_cate.getNodeValue();
						}
						if (n_get_position != null) {
							get_position = n_get_position.getNodeValue();
						}
						if (n_get_place != null) {
							get_place = n_get_place.getNodeValue();
						}
						if (n_get_thing != null) {
							get_thing = n_get_thing.getNodeValue();
						}
						if (n_status != null) {
							status = n_status.getNodeValue();
						}
						if (n_code != null) {
							code = n_code.getNodeValue();
						}
						if (n_image_url != null) {
							image_url = n_image_url.getNodeValue();
						}

//						System.out.print("id:" + id + " ");
//						System.out.print("get_name:" + get_name + " ");
//						System.out.print("url:" + url + " ");
//						System.out.print("title:" + title + " ");
//						System.out.print("get_date:" + get_date + " ");
//						System.out.print("take_place:" + take_place + " ");
//						System.out.print("contact:" + contact + " ");
//						System.out.print("cate:" + cate + " ");
//						System.out.print("get_position:" + get_position + " ");
//						System.out.print("get_place:" + get_place + " ");
//						System.out.print("get_thing:" + get_thing + " ");
//						System.out.print("status:" + status + " ");
//						System.out.print("code:" + code + " ");
//						System.out.println("iamge_url:" + image_url);

						LostItemVO vo = new LostItemVO();

						vo.setId(id);
						vo.setGet_name(get_name);
						vo.setUrl(url);
						vo.setTitle(title);
						vo.setGet_date(get_date);
						vo.setTake_place(take_place);
						vo.setContact(contact);
						vo.setCate(cate);
						vo.setGet_position(get_position);
						vo.setGet_place(get_place);
						vo.setGet_thing(get_thing);
						vo.setStatus(status);
						vo.setCode(code);
						vo.setImage_url(image_url);
						int i_flag = d_liService.li_insert(vo); // 한번 db에 저장을 하면
																// 기본키 중복으로
						// 에러가 나기때문에 다시 api를 호출 했을때 중복검사해서 추가/삭제를 할 것인지
						// 아님 아예 기본키를 없애고 새롭게 덮어쓸 것인지 생각 두번째의 경우 db를 쓸 필요가 있나?

					}
				}
			}

		}
	}

	private static Document getXMLDocument() {
		DocumentBuilderFactory mDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder mDocumentBuilder = null;
		Document mDocument = null;
		InputStream mInputStream = null;

		try {
			mDocumentBuilder = mDocumentBuilderFactory.newDocumentBuilder();
			mInputStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			mDocument = mDocumentBuilder.parse(mInputStream);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (mInputStream != null) {
				try {
					mInputStream.close();
				} catch (IOException e) {
				}
			}
		}
		return mDocument;
	}

}
