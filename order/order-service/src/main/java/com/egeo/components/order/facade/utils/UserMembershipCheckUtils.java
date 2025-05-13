package com.egeo.components.order.facade.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.client.MembershipAuthorityClient;
import com.egeo.components.product.client.MembershipUserClient;
import com.egeo.components.product.client.StandardUnitMembershipClient;
import com.egeo.components.product.dto.MembershipAuthorityDTO;
import com.egeo.components.product.dto.MembershipUserDTO;
import com.egeo.components.product.dto.StandardUnitMembershipDTO;

/**
 * Created by 0.0 on 2018/9/13.
 */
@Component
public class UserMembershipCheckUtils {

	@Autowired
    private static MembershipUserClient membershipUserReadService;
	@Autowired
    private static StandardUnitMembershipClient standardUnitMembershipReadService;

	@Autowired
    private static MembershipAuthorityClient membershipAuthorityReadService;
    /**
     * 校验用户是否有会籍预售的权限
     * @param
     * @return     0表示失败,1表示成功
     */
    public static Map<Integer,String> checkUserMembershipAuthority(Long userId, Long suId, Integer saleWay, Long platformId){
        Map<Integer, String> res = new HashMap<>();
        if(saleWay!=6){
            return res;

        }
        //判断用户是否有权限购买该商品(如果销售方式是6.会籍预售则校验用户的权限)
        if(saleWay==6){


            //查询该用户的所有会籍
            MembershipUserDTO membershipUserDTO = new MembershipUserDTO();
            membershipUserDTO.setStatusCode(1);//会员状态有效
            membershipUserDTO.setUserId(userId);
            List<MembershipUserDTO> membershipUserAll = membershipUserReadService.findMembershipUserAll(membershipUserDTO);
            if(membershipUserAll==null){
                res.put(1,"会籍预售商品,需要拥有会籍权限");
                return res;

            }
            //获取su的所有关联会籍id
            StandardUnitMembershipDTO suMembershipDTO=new StandardUnitMembershipDTO();
            suMembershipDTO.setPlatformId(platformId);
            suMembershipDTO.setStandardUnitId(suId);
            List<StandardUnitMembershipDTO> standardUnitMembershipAll = standardUnitMembershipReadService.findStandardUnitMembershipAll(suMembershipDTO);

            //判断用户是否拥有的会籍是否存在该商品要求的会籍
            boolean flag = false;
            for(StandardUnitMembershipDTO sumDTO:standardUnitMembershipAll){
                for( MembershipUserDTO userDTO:membershipUserAll){
                    if(userDTO.getMembershipId().equals(sumDTO.getMembershipId())){
                        //拥有关联会籍,校验会籍的权限是否正确
                        MembershipAuthorityDTO authorityDTO = new MembershipAuthorityDTO();
                        authorityDTO.setPlatformId(platformId);
                        authorityDTO.setMembershipId(userDTO.getMembershipId());
                        authorityDTO.setIsStop(0);
                        //设置预售权限
                        authorityDTO.setAuthorityId(1L);
                        List<MembershipAuthorityDTO> membershipAuthorityAll = membershipAuthorityReadService.findMembershipAuthorityAll(authorityDTO);
                        if(membershipAuthorityAll==null||membershipAuthorityAll.size()==0){
                            res.put(0,"商品关联的会籍已无会籍预售的权限,联系客服询问");
                            return res;
                        }else if(membershipAuthorityAll.size()>1){
                            //出现一个会籍对应多个相同权限(正常不会出现)
                            res.put(0,"会籍权限设置有误,请联系客服");
                            return res;

                        }else if(membershipAuthorityAll.size()==1){
                            res.put(1, "校验成功");
                            flag = true;
                            return res;
                        }

                    }
                }
            }
            //用户已拥有会籍拥有会籍预售的权限校验失败(成功则正常进入加入购物车)
            if(!flag){
                res.put(0, "会籍预售商品,您无该权限,请先购买会籍");
                return res;
            }




        }
        return res;
    }

}
