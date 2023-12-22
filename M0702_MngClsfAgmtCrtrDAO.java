package kr.co.kccworld.kccg.manufacture.dao;

import java.util.List;

import kr.co.kccworld.framework.system.KccgMap;

public interface M0702_MngClsfAgmtCrtrDAO {
	
	/** 생산 > 기준정보 > 생산기준정보종류코드별 포장부자재 관리 **/
	
	int selectItemListCount(KccgMap map);
	
	List<KccgMap> selectItemList(KccgMap map);
    
	List<KccgMap> selectPrdtGrpList(KccgMap map);
    
	List<KccgMap> selectCostTypeList(KccgMap map);
	
	int selectCostTypePopupListCount(KccgMap map);
	
	List<KccgMap> selectCostTypePopupList(KccgMap map);
    
	List<KccgMap> selectClassCd1List(KccgMap map);
    
	List<KccgMap> selectClassCd2List(KccgMap map);
    
	List<KccgMap> selectClassCd3List(KccgMap map);
    
	List<KccgMap> selectClassCd4List(KccgMap map);
    
	void insertCostTypeItem(KccgMap kccgMap);

}
