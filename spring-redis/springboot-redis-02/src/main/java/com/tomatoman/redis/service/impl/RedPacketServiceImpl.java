package com.tomatoman.redis.service.impl;

import com.tomatoman.redis.entity.RedPacket;
import com.tomatoman.redis.mapper.RedPacketMapper;
import com.tomatoman.redis.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RedPacketServiceImpl implements RedPacketService {
	
	@Autowired
	private RedPacketMapper redPacketMapper = null;

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public RedPacket getRedPacket(Long id) {
		return redPacketMapper.getRedPacket(id);
	}

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public int decreaseRedPacket(Long id) {
		return redPacketMapper.decreaseRedPacket(id);
	}

}