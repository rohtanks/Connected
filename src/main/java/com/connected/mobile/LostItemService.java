package com.connected.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LostItemService {
	
	private LostItemDAO lidao; // private으로 클래스에서만 접근이 가능하도록 만들었다
	
	public LostItemDAO getLidao() { // 게터 메소드를 통해 객체의 값을 가져올 수 있다
		return lidao;
	}

	@Autowired
	public void setLidao(LostItemDAO lidao) { // 객체 생성 후 세터 메소드를 통해 멤버에 접근이 가능
		this.lidao = lidao;
	}
	
	public int li_insert(LostItemVO vo) {
		return lidao.li_insert(vo);
	}
	
	public int li_delete() {
		return lidao.li_delete();
	}

	public List<LostItemVO> search(String searchkey, String searchbx) {
		return lidao.search(searchkey, searchbx);
	}
}
