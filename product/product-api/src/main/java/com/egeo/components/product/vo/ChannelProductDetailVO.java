package com.egeo.components.product.vo;

import com.egeo.components.product.dto.channel.*;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductDetailVO implements Serializable {

    private ChannelProductDTO channelProductDTO;

    private List<ChannelProductSkuDTO> skuList;

    private List<ChannelProductBatchDTO>  batchDTOList;

    private ChannelProductDescriptionDTO descriptionDTO;

    private String productImg;

    private List<String> pictureList;

    public ChannelProductDetailVO() {
    }

    public ChannelProductDetailVO(ChannelProductDTO channelProductDTO) {
        this.channelProductDTO = channelProductDTO;
    }

    public ChannelProductDTO getChannelProductDTO() {
        return channelProductDTO;
    }

    public void setChannelProductDTO(ChannelProductDTO channelProductDTO) {
        this.channelProductDTO = channelProductDTO;
    }

    public List<ChannelProductSkuDTO> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<ChannelProductSkuDTO> skuList) {
        this.skuList = skuList;
    }

    public List<ChannelProductBatchDTO> getBatchDTOList() {
        return batchDTOList;
    }

    public void setBatchDTOList(List<ChannelProductBatchDTO> batchDTOList) {
        this.batchDTOList = batchDTOList;
    }

    public ChannelProductDescriptionDTO getDescriptionDTO() {
        return descriptionDTO;
    }

    public void setDescriptionDTO(ChannelProductDescriptionDTO descriptionDTO) {
        this.descriptionDTO = descriptionDTO;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }
}
