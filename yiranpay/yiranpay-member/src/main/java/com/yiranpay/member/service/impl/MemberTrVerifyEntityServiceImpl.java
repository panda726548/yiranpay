package com.yiranpay.member.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiranpay.member.mapper.MemberTmMemberMapper;
import com.yiranpay.member.mapper.MemberTrVerifyEntityMapper;
import com.yiranpay.member.mapper.MemberTrVerifyRefMapper;
import com.yiranpay.common.core.text.Convert;
import com.yiranpay.member.constant.MaConstant;
import com.yiranpay.member.domain.MemberIdentity;
import com.yiranpay.member.domain.MemberTmMember;
import com.yiranpay.member.domain.MemberTrVerifyEntity;
import com.yiranpay.member.domain.MemberTrVerifyRef;
import com.yiranpay.member.domain.Verify;
import com.yiranpay.member.enums.ResponseCode;
import com.yiranpay.member.enums.VerifyStatusEnum;
import com.yiranpay.member.enums.VerifyTypeEncryptMappingEnum;
import com.yiranpay.member.exception.MaBizException;
import com.yiranpay.member.exception.MaIllegalArgumentException;
import com.yiranpay.member.service.IMemberTrVerifyEntityService;
import com.yiranpay.member.utils.VerifyDomainUtil;

/**
 * 认证实体 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTrVerifyEntityServiceImpl implements IMemberTrVerifyEntityService 
{
	private final static int MAX_COUNT = -1;
	@Autowired
	private MemberTrVerifyEntityMapper memberTrVerifyEntityMapper;

	@Autowired
	private MemberTrVerifyRefMapper memberTrVerifyRefMapper;
	@Autowired
	private MemberTmMemberMapper memberTmMemberMapper;
	/**
     * 查询认证实体信息
     * 
     * @param verifyEntityId 认证实体ID
     * @return 认证实体信息
     */
    @Override
	public MemberTrVerifyEntity selectMemberTrVerifyEntityById(Integer verifyEntityId)
	{
	    return memberTrVerifyEntityMapper.selectMemberTrVerifyEntityById(verifyEntityId);
	}
	
	/**
     * 查询认证实体列表
     * 
     * @param memberTrVerifyEntity 认证实体信息
     * @return 认证实体集合
     */
	@Override
	public List<MemberTrVerifyEntity> selectMemberTrVerifyEntityList(MemberTrVerifyEntity memberTrVerifyEntity)
	{
	    return memberTrVerifyEntityMapper.selectMemberTrVerifyEntityList(memberTrVerifyEntity);
	}
	
    /**
     * 新增认证实体
     * 
     * @param memberTrVerifyEntity 认证实体信息
     * @return 结果
     */
	@Override
	public int insertMemberTrVerifyEntity(MemberTrVerifyEntity memberTrVerifyEntity)
	{
	    return memberTrVerifyEntityMapper.insertMemberTrVerifyEntity(memberTrVerifyEntity);
	}
	
	/**
     * 修改认证实体
     * 
     * @param memberTrVerifyEntity 认证实体信息
     * @return 结果
     */
	@Override
	public int updateMemberTrVerifyEntity(MemberTrVerifyEntity memberTrVerifyEntity)
	{
	    return memberTrVerifyEntityMapper.updateMemberTrVerifyEntity(memberTrVerifyEntity);
	}

	/**
     * 删除认证实体对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTrVerifyEntityByIds(String ids)
	{
		return memberTrVerifyEntityMapper.deleteMemberTrVerifyEntityByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<MemberTrVerifyEntity> queryByMember(String memberId, Integer verifyType) {
		List<MemberTrVerifyEntity> verifys = new ArrayList<MemberTrVerifyEntity>();
		//根据会员ID获取认证关系 对象集合
		List<MemberTrVerifyRef> verifyRefList = memberTrVerifyRefMapper.selectMemberTrVerifyRefByMemberId(memberId);
		if(verifyRefList !=null && verifyRefList.size() > 0){
			for (MemberTrVerifyRef memberTrVerifyRef : verifyRefList) {
				List<MemberTrVerifyEntity> verifyQuerys = memberTrVerifyEntityMapper.queryByMemberAndVerifyType(
						memberTrVerifyRef.getVerifyEntityId(), verifyType);
			        if (!(verifyQuerys == null || verifyQuerys.isEmpty())) {
			            for (MemberTrVerifyEntity item : verifyQuerys) {
			                verifys.add(item);
			            }
			           
			        }
			}
		}
		
		 return verifys;
	}

	@Override
	public void addVerifys(List<MemberTrVerifyEntity> verifys, String memberId) {
		
		if (verifys == null || verifys.size() == 0) {
            return;
        }
        //手机和邮箱只能绑定一个会员
        for (MemberTrVerifyEntity v : verifys) {
            checkVerify(v,memberId);
            int verifyEntityId = memberTrVerifyEntityMapper.insertMemberTrVerifyEntity(v);
            MemberTrVerifyRef memberTrVerifyRef =new MemberTrVerifyRef();
            memberTrVerifyRef.setMemberId(memberId);
            memberTrVerifyRef.setVerifyEntityId(verifyEntityId);
            memberTrVerifyRef.setExpireTime(v.getExpireTime());
            memberTrVerifyRef.setVerifyImgPath(v.getVerifyImgPath());
            memberTrVerifyRef.setVerifiedTime(v.getVerifiedTime());
            memberTrVerifyRef.setStatus(VerifyStatusEnum.AUTHENTICATED.getCode().intValue());
            memberTrVerifyRefMapper.insertMemberTrVerifyRef(memberTrVerifyRef);
        }
        
		
	}
	
	 /*
     * 手机和邮箱只能绑定一个会员
     */
    private void checkVerify(MemberTrVerifyEntity verify,String memberId) {
        //手机号或者邮箱查询是否已经被绑定
    	MemberTrVerifyEntity v = memberTrVerifyEntityMapper.selectMemberTrVerifyEntityByVerifyEntity(verify.getVerifyEntity());
    	if(v!=null){
    		MemberTrVerifyRef vr = memberTrVerifyRefMapper.selectMemberTrVerifyRefByverifyEntityId(v.getVerifyEntityId());
        	if(memberId.equals(vr.getMemberId())){
        		throw new MaIllegalArgumentException("该会员已经认证");
        	}
    	}
    	
    }

	@Override
	public MemberTrVerifyEntity selectMemberTrVerifyEntityById(Integer verifyEntityId, Integer verifyType) {
		List<MemberTrVerifyEntity> list = memberTrVerifyEntityMapper.queryByMemberAndVerifyType(verifyEntityId,verifyType);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

   
}
