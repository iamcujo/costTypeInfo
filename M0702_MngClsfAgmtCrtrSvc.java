package kr.co.kccworld.kccg.manufacture.service;

import java.util.List;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import kr.co.kccworld.framework.system.KccgMap;
import kr.co.kccworld.kccg.manufacture.dao.M0702_MngClsfAgmtCrtrDAO;

@Service
public class M0702_MngClsfAgmtCrtrSvc {
	
	@Autowired
    private M0702_MngClsfAgmtCrtrDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(M0702_MngClsfAgmtCrtrSvc.class);
	
	public int selectItemListCount(KccgMap map) {
        return executeDaoMethod(() -> dao.selectItemListCount(map));
    }

    public List<KccgMap> selectItemList(KccgMap map) {
        return executeDaoMethod(() -> dao.selectItemList(map));
    }

    public ModelAndView selectPrdtGrpList(KccgMap map) throws Exception {
        return executeServiceMethod(() -> {
            ModelAndView mav = new ModelAndView("jsonView");
            mav.addObject("dsPrdtGrpList", dao.selectPrdtGrpList(map));
            return mav;
        }, "selectPrdtGrpList");
    }

    public ModelAndView selectCostTypeList(KccgMap map) throws Exception {
        return executeServiceMethod(() -> {
            ModelAndView mav = new ModelAndView("jsonView");
            mav.addObject("dsCostTypeList", dao.selectCostTypeList(map));
            return mav;
        }, "selectCostTypeList");
    }
    
    public int selectCostTypePopupListCount(KccgMap map) {
    	return executeDaoMethod(() -> dao.selectCostTypePopupListCount(map));
    }
    
    public List<KccgMap> selectCostTypePopupList(KccgMap map) {
        return executeDaoMethod(() -> dao.selectCostTypePopupList(map));
    }
    
    public ModelAndView selectClassCd1List(KccgMap map) throws Exception {
        return executeServiceMethod(() -> {
            ModelAndView mav = new ModelAndView("jsonView");
            mav.addObject("dsClassCd1List", dao.selectClassCd1List(map));
            return mav;
        }, "selectClassCd1List");
    }
    
    public ModelAndView selectClassCd2List(KccgMap map) throws Exception {
        return executeServiceMethod(() -> {
            ModelAndView mav = new ModelAndView("jsonView");
            mav.addObject("dsClassCd2List", dao.selectClassCd2List(map));
            return mav;
        }, "selectClassCd2List");
    }
    
    public ModelAndView selectClassCd3List(KccgMap map) throws Exception {
        return executeServiceMethod(() -> {
            ModelAndView mav = new ModelAndView("jsonView");
            mav.addObject("dsClassCd3List", dao.selectClassCd3List(map));
            return mav;
        }, "selectClassCd3List");
    }
    
    public ModelAndView selectClassCd4List(KccgMap map) throws Exception {
        return executeServiceMethod(() -> {
            ModelAndView mav = new ModelAndView("jsonView");
            mav.addObject("dsClassCd4List", dao.selectClassCd4List(map));
            return mav;
        }, "selectClassCd4List");
    }
    
    public KccgMap saveCostTypeItem(List<KccgMap> list) {
        KccgMap resultMap = new KccgMap();
        try {
            list.forEach(dao::insertCostTypeItem);
        } catch (Exception e) {
            logger.error("saveCostTypeItem Error: ", e);
            throw new RuntimeException("처리 중 오류가 발생하였습니다.");
        }
        return resultMap;
    }

    private <T> T executeDaoMethod(Supplier<T> daoMethod) {
        return daoMethod.get();
    }

    private ModelAndView executeServiceMethod(Supplier<ModelAndView> serviceMethod, String methodName) {
        try {
            return serviceMethod.get();
        } catch (Exception e) {
            logger.error(methodName + " Error: ", e);
            throw new RuntimeException("처리 중 오류가 발생하였습니다.");
        }
    }

}
