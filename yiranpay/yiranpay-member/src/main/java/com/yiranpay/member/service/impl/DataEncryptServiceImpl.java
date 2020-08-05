/**
 * 
 */
package com.yiranpay.member.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiranpay.member.constant.MaConstant;
import com.yiranpay.member.domain.MemberTmOperator;
import com.yiranpay.member.domain.MemberTrVerifyEntity;
import com.yiranpay.member.domain.PersonalMember;
import com.yiranpay.member.domain.Verify;
import com.yiranpay.member.enums.VerifyTypeEncryptMappingEnum;
import com.yiranpay.member.service.DataEncryptService;
import com.yiranpay.member.utils.MaPatternUtil;
import com.yiranpay.payorder.enums.EncryptType;
import com.yiranpay.payorder.service.IUesServiceClient;

/**
 * <p>敏感数据加密服务</p>
 */
@Service
public class DataEncryptServiceImpl implements DataEncryptService {
	@Autowired
    private IUesServiceClient uesServiceClient;

    /* (non-Javadoc)
     * @see com.netfinworks.ma.core.service.domainservice.DataEncryptService#encrypt(com.netfinworks.ma.core.domain.PersonalMember, com.netfinworks.ma.core.domain.Operator)
     */
    @Override
    public void encrypt(PersonalMember member, MemberTmOperator operator) throws Exception {
        String password = operator.getPassword();
        List<MemberTrVerifyEntity> verifys = member.getVerifys();
        String trueName = member.getTrueName();
        String trueNameTicket =null;
        String passwordTicket =null;
        //通过查询数据库配置获得需要加密的数据
        //(1.1) 真实姓名等
        if(!StringUtils.isEmpty(trueName)){
        	trueNameTicket = uesServiceClient.encryptData(trueName, EncryptType.AES);
        }
        //(1.2) 登录密码
        if(!StringUtils.isEmpty(password)){
        	passwordTicket = uesServiceClient.encryptData(password, EncryptType.AES);
        }
        //(1.3) 认证信息
        if(verifys != null && verifys.size() > 0){
            VerifyTypeEncryptMappingEnum verifyEnum = null;
            for(MemberTrVerifyEntity item: verifys){
                item.setVerifyEntity(uesServiceClient.encryptData(item.getVerifyEntity(),EncryptType.AES));
            }
        }
        
               
        //(3) 把加密的结果 + 掩码 回填到字段
        if(!StringUtils.isEmpty(trueNameTicket)){
            String summary = MaPatternUtil.getFiledMask(trueName, "[\u4E00-\u9FFF]{1}([\u4E00-\u9FFFa-zA-Z]*)");
            String tmp = trueNameTicket + MaConstant.SECURITY_TICKET_SUMMARY_SPLIT_CHAR + summary;
            member.setTrueName(tmp);
        }
        
        if(!StringUtils.isEmpty(passwordTicket)){
            operator.setPassword(passwordTicket);
        }
        
        //(3.3)认证信息
        if(verifys != null && verifys.size() > 0){
            VerifyTypeEncryptMappingEnum verifyEnum = null;
            for(MemberTrVerifyEntity item: verifys){
                verifyEnum = VerifyTypeEncryptMappingEnum.getByCode(item.getVerifyType());
                if(verifyEnum != null){
                    String ticket = item.getVerifyEntity();
                    if(!StringUtils.isEmpty(ticket)){
                        String summary = "";
                        if(verifyEnum.getFieldName().equals(MaConstant.SECURITY_EMAIL)){
                            summary = MaPatternUtil.getEmailMask(member.getDefaultLoginName(), "[0-9a-z.A-Z]([0-9a-z.A-Z]+)[0-9a-z.A-Z]@[0-9a-z.A-Z]+");
                        }else if(verifyEnum.getFieldName().equals(MaConstant.SECURITY_CELLPHONE)){
                            summary = MaPatternUtil.getFiledMask(member.getDefaultLoginName(), "[0-9]{2}([0-9]*)[0-9]{2}");
                        }else{
                            summary = MaPatternUtil.getFiledMask(member.getDefaultLoginName(), "[0-9]{2}([0-9]*)[0-9]{2}");
                        }
                        item.setVerifyEntity(ticket + MaConstant.SECURITY_TICKET_SUMMARY_SPLIT_CHAR + summary);
                    }
                }
            }
        }
        
        return ;
    }
    
}
