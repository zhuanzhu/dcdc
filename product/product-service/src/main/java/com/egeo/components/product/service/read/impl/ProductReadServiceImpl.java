package com.egeo.components.product.service.read.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.egeo.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.ProductReadService;
import com.egeo.components.product.service.write.ProductWriteService;
import com.egeo.components.product.manage.read.ProductReadManage;
import com.egeo.components.product.condition.ProductCondition;
import com.egeo.components.product.converter.ProductConverter;
import com.egeo.components.product.dto.ProductDTO;
import com.egeo.components.product.po.ProductPO;
import com.egeo.core.Constant.BusinessExceptionConstant;

import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;

@Service("productReadService")
public class ProductReadServiceImpl  implements ProductReadService {
	@Autowired
	private ProductReadManage productReadManage;
	
	@Autowired
    private ProductWriteService productWriteService;

        @Override
        public PageResult<ProductDTO> findPage(Pagination page, ProductDTO dto, List<String> nameList) {
            ProductPO po = ProductConverter.toPO(dto);
            /**
             * 删除所有点击添加规格属性没有点下一步所创建的空spu草稿信息
             */
            //productWriteService.delByNullProductWithTx();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
    		SimpleDateFormat sdf2= new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	        try {
                        if(po.getBeginTime() != null){
                            Date beginTime = format.parse(po.getBeginTime().replace("Z", " UTC"));
                            String format2 = sdf2.format(beginTime);
                            po.setBeginTime(format2.substring(0, 10));
                        }
                        if(po.getFinishTime() != null){
                            Date finishTime = format.parse(po.getFinishTime().replace("Z", " UTC"));
                            String string = sdf2.format(finishTime);
                            po.setFinishTime(string.substring(0, 10));
                        }
                } catch (ParseException e) {
                    throw new BusinessException(BusinessExceptionConstant.STRING_NO_DATE, "类型转换错误");
                }
            PageResult<ProductPO> pageResult = productReadManage.findPage(page, po,nameList);
            for (ProductPO item: pageResult.getList()) {
               List<String> list =  productReadManage.findPrecautiousLines(dto.getPrecautiousLineIds(),item.getId());
                for (int i = 0;i<list.size();i++) {
                    if (EmptyUtil.isEmpty(list.get(i))){
                        list.remove(i--);
                    }
                }
                if (EmptyUtil.isNotEmpty(list)){
                    Collections.sort(list, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {

                            int num1 = Integer.parseInt(o1);
                            int num2 = Integer.parseInt(o2);
                            if (num1 < num2) {
                                return 1;
                            } else if (num1 > num2) {
                                return -1;
                            } else {
                                return 0;
                            }
                        }
                    });
                }
                item.setPrecautiousLineDays(list);
            }

            List<ProductDTO> list = ProductConverter.toDTO(pageResult.getList());
            PageResult<ProductDTO> result = new PageResult<ProductDTO>();
            result.setList(list);
            result.setPageNo(pageResult.getPageNo());
            result.setPageSize(pageResult.getPageSize());
            result.setTotalSize(pageResult.getTotalSize());
            return result;
        }

        @Override
        public ProductDTO findById(ProductDTO dto) {
            ProductPO productPO = productReadManage.findById(ProductConverter.toPO(dto));
            return ProductConverter.toDTO(productPO);
        }

        @Override
        public List<ProductDTO> findAll(ProductDTO dto) {
            List<ProductPO> list = productReadManage.findAll(ProductConverter.toPO(dto));
            return ProductConverter.toDTO(list);
        }

		@Override
		public PageResult<ProductDTO> productByActivityId(Pagination page, ProductDTO productDTO) {
			ProductPO po = ProductConverter.toPO(productDTO);
            PageResult<ProductPO> pageResult = productReadManage.productByActivityId(page, po);
            
            List<ProductDTO> list = new ArrayList<ProductDTO>();
            for (ProductPO tmp : pageResult.getList()) {
                ProductDTO ProductDTO = ProductConverter.toDTO(tmp);
                    list.add(ProductDTO);
            }
            PageResult<ProductDTO> result = new PageResult<ProductDTO>();
            result.setList(list);
            result.setPageNo(pageResult.getPageNo());
            result.setPageSize(pageResult.getPageSize());
            result.setTotalSize(pageResult.getTotalSize());
            return result;
		}
		/**
		 * 根据产品id查询产品信息及产品详情
		 * @return
		 */
		@Override
		public ProductDTO productAndProductDescriptionById(Long productId) {
			ProductCondition productCondition = productReadManage.productAndProductDescriptionById(productId);
			ProductDTO productDTO = ProductConverter.toDTO(productCondition);
			productDTO.setContent(productCondition.getContent());
			return productDTO;
		}
		/**
		 * 根据以通过的spu草稿id集合查询spu信息
		 * @param ids
		 * @return
		 */
		@Override
		public List<ProductDTO> findProductByIds(List<Long> ids) {
			List<ProductPO> list = productReadManage.findProductByIds(ids);
			return ProductConverter.toDTO(list);
		}
		/**
		 * 根据类目节点id查询spu草稿信息、有为true、没有为false
		 * @return
		 */
		@Override
		public boolean findByCategoryTreeNodeId(Long categoryTreeNodeId) {
			// TODO Auto-generated method stub
			return productReadManage.findByCategoryTreeNodeId(categoryTreeNodeId);
		}
        /**
         * 获取product表中所有的id
         * @param dto
         * 参数作为后期扩展，本次未使用（2018.11.7）
         */
		public  List<Long> getProductIdsByCondition(ProductDTO dto){
            ProductPO po = ProductConverter.toPO(dto);
		    return productReadManage.getProductIdsByCondition(po);
        }

    @Override
    public Long findLastId() {
        return productReadManage.findLastId();
    }

    @Override
    public List<Long> findAllCategoryTreeNodeId() {
        return productReadManage.findAllCategoryTreeNodeId();
    }

}
	