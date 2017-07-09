package com.connected.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LostItemDAOimpl implements LostItemDAO {

	private static final Logger logger = LoggerFactory.getLogger(LostItemDAOimpl.class);
	
	private SqlSession sqlSession;
	
	
	
//	private final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
//	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	private final String USER = "admin0";
//	private final String PASSWORD = "qwerty098";
	
//	private final String SQL_LI_INSERT = "insert into lostitem "
//			+ "(id,get_name,url,title,get_date,take_place,contact,cate,get_position,get_place,"
//			+ "get_thing,status,code,image_url)"
//			+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
//	private Connection conn;
//	private PreparedStatement pstmt;

	public LostItemDAOimpl() {
		logger.info("LostItemDAOimpl()");
//		try {
//			Class.forName(DRIVER_NAME);
//			logger.info("driver loading succeeded");
//		} catch (ClassNotFoundException e) {
//			logger.info("driver loading failed");
//			e.printStackTrace();
//		}
	}
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	
	@Override
	public int li_insert(LostItemVO vo) {
		logger.info("li_insert()");
//		마이바티스를 이용하지 않고 자바코드를 이용한 방법 대용량 자료 처리시 속도면에서 이점이 있다고 한다
//		addBatch는 쿼리 실행을 하지 않고 쿼리 구문을 메모리에 올려두었다가 실행 명령이 있으면 한번에 db쪽으로 쿼리를 날리는 기능
//        try {
//        	conn = DriverManager.getConnection(URL, USER, PASSWORD);
//        	pstmt = conn.prepareStatement(SQL_LI_INSERT);
//        	logger.info("li_insert() conn succeeded");
        	
//        	pstmt.setString(1, vo.getId());
//        	pstmt.setString(2, vo.getGet_name());
//        	pstmt.setString(3, vo.getUrl());
//        	pstmt.setString(4, vo.getTitle());
//        	pstmt.setString(5, vo.getGet_date());
//        	pstmt.setString(6, vo.getTake_place());
//        	pstmt.setString(7, vo.getContact());
//        	pstmt.setString(8, vo.getCate());
//        	pstmt.setString(9, vo.getGet_position());
//        	pstmt.setString(10, vo.getGet_place());
//        	pstmt.setString(11, vo.getGet_thing());
//        	pstmt.setString(12, vo.getStatus());
//        	pstmt.setString(13, vo.getCode());
//        	pstmt.setString(14, vo.getImage_url());
//        	
//        	pstmt.addBatch(); // addBatch에 담기
//        	pstmt.clearParameters(); // 파라미터 clear
//        	
//        	for (int i = 0; i < 1000; i++) {
////        	OutOfMemory를 고려하여 만건 단위로 커밋
////        	100건으로 임의 고정
//        		if((i % 100) == 0){
//        			
//        			// Batch 실행
//        			pstmt.executeBatch() ;
//        			
//        			// Batch 초기화
//        			pstmt.clearBatch();
//        			
//        			// 커밋
//        			conn.commit() ;
//        			
//        		}
//				
//			}
////        	커밋되지 못한 나머지 구문에 대하여 커밋
//        	pstmt.executeBatch(); // Batch 실행
//        	
//        	conn.commit();
//        	
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
////					pstmt = null;
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (conn != null) {
//				try {
//					conn.close();
////					conn = null;
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
        	logger.info(vo.getId());
        	logger.info(vo.getGet_name());
        	logger.info(vo.getUrl());
        	logger.info(vo.getTitle());
        	logger.info(vo.getGet_date());
        	logger.info(vo.getTake_place());
        	logger.info(vo.getContact());
        	logger.info(vo.getCate());
        	logger.info(vo.getGet_position());
        	logger.info(vo.getGet_place());
        	logger.info(vo.getGet_thing());
        	logger.info(vo.getStatus());
        	logger.info(vo.getCode());
        	logger.info(vo.getImage_url());
		

		int flag = sqlSession.insert("li_insert", vo);
		logger.info("li_insert() flag : " + flag);
		return flag;
	}

	@Override
	public int li_delete() {
		logger.info("li_delete()");
		
		int flag = sqlSession.delete("li_delete");
		logger.info("li_delete() flag : " + flag);

		return flag;
	}
	
	@Override
	public List<LostItemVO> search(String searchkey, String searchbx) {
		logger.info("search()");
		logger.info(searchkey);
		logger.info(searchbx);
		Map<String, String> map;
		
		if (searchbx.indexOf("&") != -1) {
			logger.info("검색어에서 & 발견");
			String[] searchbx_test = searchbx.split("&");
			logger.info(searchbx_test[0]);
			logger.info(searchbx_test[1]);
			searchkey = "searchTest_1";
			
			map = new HashMap<String, String>();
			map.put("searchkey", searchkey);
			map.put("searchbx", "%" + searchbx_test[0] + "%");
			map.put("searchbx_1", "%" + searchbx_test[1] + "%");
			
		} else {
			
			map = new HashMap<String, String>();
			map.put("searchkey", searchkey);
			map.put("searchbx", "%" + searchbx + "%");
			
		}
		
		List<LostItemVO> list = sqlSession.selectList("search", map);
		
		return list;
	}
}
