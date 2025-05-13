package com.egeo.components.user.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.FunctionTreeNodeManage;
import com.egeo.components.user.dto.FunctionTreeNodeDTO;
import com.egeo.components.user.dto.UrlDTO;
import com.egeo.components.user.facade.FunctionTreeNodeFacade;
import com.egeo.components.user.facade.FunctionTreeNodeUrlFacade;
import com.egeo.components.user.facade.UrlFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("functionTreeNode")
public class FunctionTreeNodeManageImpl implements FunctionTreeNodeManage{
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="functionTreeNodeFacade")
	private FunctionTreeNodeFacade functionTreeNodeFacade;

	@Resource(name="functionTreeNodeUrlFacade")
	private FunctionTreeNodeUrlFacade functionTreeNodeUrlFacade;

    @Resource(name="urlFacade")
    private UrlFacade urlFacade;

	@Override
	public FunctionTreeNodeDTO findFunctionTreeNodeById(FunctionTreeNodeDTO dto) {
		return functionTreeNodeFacade.findFunctionTreeNodeById(dto);
	}

	@Override
	public PageResult<FunctionTreeNodeDTO> findFunctionTreeNodeOfPage(FunctionTreeNodeDTO dto, Pagination page) {
		return functionTreeNodeFacade.findFunctionTreeNodeOfPage(dto, page);
	}


	@Override
	public List<FunctionTreeNodeDTO> findFunctionTreeNodeAll(FunctionTreeNodeDTO dto) {
		List<FunctionTreeNodeDTO> functionTreeNodeList = functionTreeNodeFacade.findFunctionTreeNodeAll(dto);
		return constructFunctionTree(functionTreeNodeList);
	}


	private List<FunctionTreeNodeDTO> constructFunctionTree(List<FunctionTreeNodeDTO> functionTreeNodeList) {
		Map<Long, List<FunctionTreeNodeDTO>> functionTreeNodeMap = new HashMap<>();
		for (FunctionTreeNodeDTO functionTreeNode : functionTreeNodeList) {
			Long parentId = functionTreeNode.getParentId();
			if (functionTreeNodeMap.containsKey(parentId)) {
				functionTreeNodeMap.get(parentId).add(functionTreeNode);
			} else {
				List<FunctionTreeNodeDTO> childList = new ArrayList<>();
				childList.add(functionTreeNode);
				functionTreeNodeMap.put(parentId, childList);
			}
		}
		List<FunctionTreeNodeDTO> functionTree = new ArrayList<>();
		recursionTreeNode(functionTree, functionTreeNodeMap, -1L);
		return functionTree;
	}
	
	private void recursionTreeNode(List<FunctionTreeNodeDTO> functionTree,
			Map<Long, List<FunctionTreeNodeDTO>> functionTreeNodeMap, Long parentId) {
		if (functionTreeNodeMap.containsKey(parentId)) {
			List<FunctionTreeNodeDTO> childList = functionTreeNodeMap.get(parentId);
			if (parentId.equals(-1L)) {
				functionTree.addAll(childList);
			}
			for (FunctionTreeNodeDTO child : childList) {
				child.setChildren(functionTreeNodeMap.get(child.getId()));
                /*List <FunctionTreeNodeUrlDTO> functionTreeNodeUrlDTOList = functionTreeNodeUrlFacade.findFunctionTreeNodeUrlByFunctionTreeNodeId(child.getId());
                child.setUrls(functionTreeNodeUrlDTOList);*/
				recursionTreeNode(functionTree, functionTreeNodeMap, child.getId());
			}
		}
	}

	@Override
	public Long insertFunctionTreeNodeWithTx(FunctionTreeNodeDTO dto) {
		return functionTreeNodeFacade.insertFunctionTreeNodeWithTx(dto);
	}

	@Override
	public int updateFunctionTreeNodeWithTx(FunctionTreeNodeDTO dto) {
		return functionTreeNodeFacade.updateFunctionTreeNodeWithTx(dto);
	}

	@Override
	public int deleteFunctionTreeNodeWithTx(FunctionTreeNodeDTO dto) {
		return functionTreeNodeFacade.deleteFunctionTreeNodeWithTx(dto);
	}

	@Override
	public List<Map<String, Object>> findFunctionTreeNodeUrlAll(FunctionTreeNodeDTO dto) {
		List<FunctionTreeNodeDTO> functionTreeNodeList = functionTreeNodeFacade.findFunctionTreeNodeAll(dto);
		return constructFunctionTreeNodeUrl(functionTreeNodeList);
	}
	private List<Map<String, Object>> constructFunctionTreeNodeUrl(List<FunctionTreeNodeDTO> functionTreeNodeList) {
		Map<Long, List<FunctionTreeNodeDTO>> functionTreeNodeMap = new HashMap<>();
		for (FunctionTreeNodeDTO functionTreeNode : functionTreeNodeList) {
			Long parentId = functionTreeNode.getParentId();
			if (functionTreeNodeMap.containsKey(parentId)) {
				functionTreeNodeMap.get(parentId).add(functionTreeNode);
			} else {
				List<FunctionTreeNodeDTO> childList = new ArrayList<>();
				childList.add(functionTreeNode);
				functionTreeNodeMap.put(parentId, childList);
			}
		}
		List<Map<String, Object>> functionTree = new ArrayList<>();
		recursionTreeNodeUrl(functionTree, functionTreeNodeMap,null, -1L);

		return functionTree;
	}

	private Map<String, Object> functionTreeNodeToMap(FunctionTreeNodeDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("id",dto.getId());
		map.put("name",dto.getName());
		map.put("type","function");
		return map;
	}

	private Map<String, Object> urlToMap(UrlDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("id",dto.getId());
		map.put("name",dto.getName());
		map.put("type","url");

		return map;
	}

	private List<Map<String, Object>> functionTreeNodeToMap(List<FunctionTreeNodeDTO> dto) {
		List<Map<String, Object>> list = new ArrayList<>();
		for (FunctionTreeNodeDTO treeNode: dto ) {
			list.add(functionTreeNodeToMap(treeNode));
		}
		return list;
	}

	private void recursionTreeNodeUrl(List<Map<String, Object>> functionTree,
								   Map<Long, List<FunctionTreeNodeDTO>> functionTreeNodeMap,List<Map<String, Object>> children, Long parentId) {
        if (children == null) {
            List<FunctionTreeNodeDTO> childList = functionTreeNodeMap.get(parentId);
            children = functionTreeNodeToMap(childList);
        }
        //判断是否存在传过来parentId
        if (EmptyUtil.isNotEmpty(children)) {
            //存在
            if (parentId.equals(-1L)) {
                functionTree.addAll(children);
            }
            for (Map<String, Object> map : children) {
                Long id = Long.valueOf(map.get("id").toString());
                if (functionTreeNodeMap.containsKey(id)) {
                    //有子节点
                    List<Map<String, Object>> childrenTmp = functionTreeNodeToMap(functionTreeNodeMap.get(id));
                    map.put("children", childrenTmp);
                    recursionTreeNodeUrl(functionTree, functionTreeNodeMap, childrenTmp, id);
                } else{
                    //没有子节点，添加url
                    List<UrlDTO> urlList = functionTreeNodeFacade.findUrlList(id);
                    List<Map<String, Object>> urlNodeList =  new ArrayList<>();
                    for ( UrlDTO url: urlList ) {
                        urlNodeList.add(urlToMap(url));
                    }
                    map.put("children", urlNodeList);
                }
            }
        }
	}
}
	