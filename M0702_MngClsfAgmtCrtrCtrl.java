package kr.co.kccworld.kccg.manufacture.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import kr.co.kccworld.framework.system.KccgMap;
import kr.co.kccworld.kccg.manufacture.service.M0702_MngClsfAgmtCrtrSvc;

@RestController
@RequestMapping(value = "/manufacture/M0702")
public class M0702_MngClsfAgmtCrtrCtrl {

	private final Logger logger = LoggerFactory.getLogger(M0702_MngClsfAgmtCrtrCtrl.class);

    @Autowired
    private M0702_MngClsfAgmtCrtrSvc service;
	
	@RequestMapping(value = "/getItemList.do")
    public ResponseEntity<Map<String, Object>> selectProductItemList(@RequestBody KccgMap paramMap) {
        try {
            KccgMap map = (KccgMap) paramMap.get("dsSearchMap");

            int totalCnt = service.selectItemListCount(map);
            List<KccgMap> itemSearchList = service.selectItemList(map);

            Map<String, Object> response = new HashMap<>();
            response.put("totalCnt", totalCnt);
            response.put("dsClassSortList", itemSearchList);
            response.put("resCode", "success");
            response.put("resMsg", "");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("selectProductItemList : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("처리 중 오류가 발생하였습니다."));
        }
    }
	
	// 제품구분
	@RequestMapping(value="/getPrdtGrpList.do")
    public ModelAndView selectPrdtGrpList(@RequestBody KccgMap paramMap) {
        try {
            KccgMap map = (KccgMap) paramMap.get("dsSearchMap");
            ModelAndView mav = service.selectPrdtGrpList(map);
            addSuccessResponse(mav);
            return mav;
        } catch (Exception e) {
            ModelAndView mav = createJsonModelAndView();
            logger.error("selectPrdtGrpList : " + e.getMessage());
            mav.addObject("resCode", "failure");
            mav.addObject("resMsg", "처리 중 오류가 발생하였습니다.");
            return mav;
        }
    }
	
	// 원가유형
	@RequestMapping(value="/getCostTypeList.do")
    public ModelAndView selectCostTypeList(@RequestBody KccgMap paramMap) {
        try {
            KccgMap map = (KccgMap) paramMap.get("dsSearchMap");
            ModelAndView mav = service.selectCostTypeList(map);
            addSuccessResponse(mav);
            return mav;
        } catch (Exception e) {
            logger.error("selectCostTypeList : " + e.getMessage());
            ModelAndView mav = createJsonModelAndView();
            mav.addObject("resCode", "failure");
            mav.addObject("resMsg", "처리 중 오류가 발생하였습니다.");
            return mav;
        }
    }
	
	// 원가유형 팝업 조회
	@RequestMapping(value = "/getCostTypePopupList.do")
    public ResponseEntity<Map<String, Object>> selectCostTypePopupList(@RequestBody KccgMap paramMap) {
        try {
            KccgMap map = (KccgMap) paramMap.get("dsSearchMap");

            int totalCnt = service.selectCostTypePopupListCount(map);
            List<KccgMap> itemSearchList = service.selectCostTypePopupList(map);

            Map<String, Object> response = new HashMap<>();
            response.put("totalCnt", totalCnt);
            response.put("dsItemSearchList", itemSearchList);
            response.put("resCode", "success");
            response.put("resMsg", "");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("selectCostTypePopupList : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("처리 중 오류가 발생하였습니다."));
        }
    }
	
	// 제품군 추출
	private ModelAndView handleClassCdListRequest(KccgMap paramMap, String methodName) {
        try {
            KccgMap map = (KccgMap) paramMap.get("dsSearchMap");
            ModelAndView mav = null;
	        
	        switch (methodName) {
	            case "selectClassCd1List":
	                mav = service.selectClassCd1List(map);
	                break;
	            case "selectClassCd2List":
	                mav = service.selectClassCd2List(map);
	                break;
	            case "selectClassCd3List":
	                mav = service.selectClassCd3List(map);
	                break;
	            case "selectClassCd4List":
	                mav = service.selectClassCd4List(map);
	                break;
	            default:
                    mav = createJsonModelAndView();
                    mav.addObject("resCode", "failure");
                    mav.addObject("resMsg", "처리 중 오류가 발생하였습니다.");
                    break;
	        }
	        
	        addSuccessResponse(mav);
	        return mav;
        } catch (Exception e) {
            ModelAndView mav = createJsonModelAndView();
            mav.addObject("resCode", "failure");
            mav.addObject("resMsg", "처리 중 오류가 발생하였습니다.");
            logger.error(methodName + " : " + e.getMessage());
            return mav;
        }
	}

	// 제품군1
	@RequestMapping(value = "getClassCd1List")
	public ModelAndView selectClassCd1List(@RequestBody KccgMap paramMap) {
	    return handleClassCdListRequest(paramMap, "selectClassCd1List");
	}

	// 제품군2
	@RequestMapping(value = "getClassCd2List")
	public ModelAndView selectClassCd2List(@RequestBody KccgMap paramMap) {
	    return handleClassCdListRequest(paramMap, "selectClassCd2List");
	}

	// 제품군3
	@RequestMapping(value = "getClassCd3List")
	public ModelAndView selectClassCd3List(@RequestBody KccgMap paramMap) {
	    return handleClassCdListRequest(paramMap, "selectClassCd3List");
	}
	
	// 제품군4
	@RequestMapping(value = "getClassCd4List")
	public ModelAndView selectClassCd4List(@RequestBody KccgMap paramMap) {
	    return handleClassCdListRequest(paramMap, "selectClassCd4List");
	}
	
	@RequestMapping(value = "saveCostTypeItem.do")
	    public ResponseEntity<Object> saveCostTypeItem(@RequestBody KccgMap paramMap, HttpServletRequest request) {
	        HttpSession session = request.getSession();
	        KccgMap userInfo = (KccgMap) session.getAttribute("kccgErpUserMap");
	
	        if (userInfo == null) {
	            return ResponseEntity.badRequest().body(createErrorResponse("사용자 정보를 찾을 수 없습니다."));
	        }
	
	        List<KccgMap> list = (List<KccgMap>) paramMap.get("dsCostTypeList");
	
	        try {
	            KccgMap resultMap = service.saveCostTypeItem(list);
	            return ResponseEntity.ok().body(resultMap);
	        } catch (RuntimeException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(createErrorResponse("처리 중 오류가 발생하였습니다."));
	        }
	    }
	
	private ModelAndView createJsonModelAndView() {
        return new ModelAndView("jsonView");
    }

    private void addSuccessResponse(ModelAndView mav) {
        mav.addObject("resCode", "success");
        mav.addObject("resMsg", "");
    }

    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("resCode", "failure");
        errorResponse.put("resMsg", message);
        return errorResponse;
    }

}
