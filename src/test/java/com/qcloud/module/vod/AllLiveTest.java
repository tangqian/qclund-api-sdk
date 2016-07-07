package com.qcloud.module.vod;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.qcloud.api.callers.LiveModuleCaller;
import com.qcloud.api.common.CloudBaseResponse;
import com.qcloud.api.modules.live.dto.CreateLVBChannelResponse;
import com.qcloud.api.modules.live.dto.DescribeLVBChannelListResponse;
import com.qcloud.api.modules.live.dto.DescribeLVBChannelResponse;
import com.qcloud.api.modules.live.service.CreateLVBChannelService;
import com.qcloud.api.modules.live.service.DeleteLVBChannelService;
import com.qcloud.api.modules.live.service.DescribeLVBChannelListService;
import com.qcloud.api.modules.live.service.DescribeLVBChannelService;

/**
 * @author tangqian
 *
 */
public class AllLiveTest {

	private LiveModuleCaller caller;

	@Before
	public void init() {
		caller = Config.liveCaller;
	}

	@Test
	public void CreateLVBChannelTest() {
		CreateLVBChannelResponse response = new CreateLVBChannelService(caller).call("booth-111", "stream-111", 1);
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void DescribeLVBChannelListTest() {
		DescribeLVBChannelListResponse response = new DescribeLVBChannelListService(caller).call(null, 1, 100);
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void DescribeLVBChannelTest() {
		DescribeLVBChannelResponse response = new DescribeLVBChannelService(caller).call("16093425727655441489");
		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void DeleteLVBChannelTest() {
		List<String> lists = new ArrayList<String>();
		lists.add("16093425727655444331");
		lists.add("16093425727655441489");
		CloudBaseResponse response = new DeleteLVBChannelService(caller).call(lists);
		Assert.assertTrue(response.isSuccess());
	}

}
