package com.yiranpay.member.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yiranpay.member.exception.MenberSqeException;
import com.yiranpay.member.mapper.MemberSequenceMapper;
import com.yiranpay.member.service.IMemberSequenceService;
@Service("memberSequenceService")
public class MemberSequenceServiceImpl implements IMemberSequenceService {
	private static final Log LOG = LogFactory.getLog(MemberSequenceServiceImpl.class);
	
	@Autowired
	private MemberSequenceMapper memberSequenceMapper;
	
	@Override
	public String getMenberSequenceNo(String sequenceName) {
		return this.getSeqNextValue(sequenceName);
	}

	/**
	 * 根据序列名称,获取序列值
	 */
	@Transactional(rollbackFor = Exception.class)
	public String getSeqNextValue(String seqName) {
		String seqNextValue = null;
		try {
			seqNextValue = memberSequenceMapper.getSeqNextValue(seqName);
		} catch (Exception e) {
			LOG.error("生成序号异常：" + "seqName=" + seqName, e);
			throw MenberSqeException.DB_GET_SEQ_NEXT_VALUE_ERROR;
		}
		if (StringUtils.isEmpty(seqNextValue)) {
			throw MenberSqeException.DB_GET_SEQ_NEXT_VALUE_ERROR;
		}
		return seqNextValue;
	}
}
