package com.tomatoman.redis.mapper;

import com.tomatoman.redis.entity.UserRedPacket;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedPacketMapper {

	/**
	 * 插入抢红包信息.
	 * @param userRedPacket ——抢红包信息
	 * @return 影响记录数.
	 */
	public int grapRedPacket(UserRedPacket userRedPacket);
}
