package com.qcloud.module.vod;

import java.text.ParseException;

import junit.framework.Assert;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.common.CloudBaseResponse;
import com.qcloud.api.modules.vod.dto.CreateClassResponse;
import com.qcloud.api.modules.vod.dto.CreateScreenShotResponse;
import com.qcloud.api.modules.vod.dto.DescribeAllClassResponse;
import com.qcloud.api.modules.vod.dto.DescribeClassResponse;
import com.qcloud.api.modules.vod.dto.DescribeVodInfoResponse;
import com.qcloud.api.modules.vod.dto.DescribeVodPlayInfoResponse;
import com.qcloud.api.modules.vod.dto.DescribeVodPlayUrlsResponse;
import com.qcloud.api.modules.vod.dto.MultipartUploadVodFileResponse;
import com.qcloud.api.modules.vod.service.ConvertVodFileService;
import com.qcloud.api.modules.vod.service.CreateClassService;
import com.qcloud.api.modules.vod.service.CreateScreenShotService;
import com.qcloud.api.modules.vod.service.DeleteClassService;
import com.qcloud.api.modules.vod.service.DescribeAllClassService;
import com.qcloud.api.modules.vod.service.DescribeClassService;
import com.qcloud.api.modules.vod.service.DescribeVodInfoService;
import com.qcloud.api.modules.vod.service.DescribeVodInfoService.DescribeVodInfoRequest;
import com.qcloud.api.modules.vod.service.DescribeVodPlayInfoService;
import com.qcloud.api.modules.vod.service.DescribeVodPlayUrlsService;
import com.qcloud.api.modules.vod.service.ModifyClassService;
import com.qcloud.api.modules.vod.service.ModifyVodInfoService;
import com.qcloud.api.modules.vod.service.MultipartUploadVodFileService;
import com.qcloud.api.modules.vod.service.SetVodPlayStatusService;
import com.qcloud.module.Config;

/**
 * @author tangqian
 *
 */
public class AllInOneTest {

	private VodModuleCaller caller;

	@Before
	public void init() {
		caller = Config.vodCaller;
	}

	@Test
	public void CreateClassTest() {
		CreateClassResponse response = new CreateClassService(caller).call("2016-06", null);
		Assert.assertFalse(response.isSuccess());
	}

	@Test
	public void DescribeAllClassTest() {
		DescribeAllClassResponse response = new DescribeAllClassService(caller).call();
		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void DescribeClassTest() {
		DescribeClassResponse response = new DescribeClassService(caller).call();
		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void ModifyClassTest() {
		CloudBaseResponse response = new ModifyClassService(caller).call(18061, "2015-01");
		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void DeleteClassTest() {
		CloudBaseResponse response = new DeleteClassService(caller).call(17868);
		Assert.assertFalse(response.isSuccess());
	}

	@Test
	public void DescribeVodPlayInfoTest() {
		DescribeVodPlayInfoResponse response = new DescribeVodPlayInfoService(caller).call("W", null, null);
		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void DescribeVodPlayUrlsTest() {
		DescribeVodPlayUrlsResponse response = new DescribeVodPlayUrlsService(caller).call("14651978969259967260");
		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void ModifyVodInfoTest() {
		CloudBaseResponse response = new ModifyVodInfoService(caller).call("14651978969259967260", null, "WFC1111", "my test intro1111", 18061);
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void SetVodPlayStatusTest() {
		CloudBaseResponse response = new SetVodPlayStatusService(caller).call("14651978969259967260", 1);
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void CreateScreenShotTest() {
		CreateScreenShotResponse response = new CreateScreenShotService(caller).call("14651978969259967260");
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void ConvertVodFileTest() {
		CloudBaseResponse response = new ConvertVodFileService(caller).call("14651978969259967260", null, null, null);
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void DescribeVodInfoTest() throws ParseException {
		DescribeVodInfoRequest request = new DescribeVodInfoRequest();
		request.setFrom(DateUtils.parseDate("2016-06-28 10:27:17", "yyyy-MM-dd HH:mm:ss"));
		//request.setClassId(18061);
		//request.setPageNo(1);
		//request.setPageSize(20);
		request.setOrderBy(0);
		DescribeVodInfoResponse response = new DescribeVodInfoService(caller).call(request);
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void MultipartUploadVodFileTest() throws ParseException {
		
		MultipartUploadVodFileResponse response = new MultipartUploadVodFileService(caller).call("F:\\video\\2016-06\\61400.mp4", null, 17873, null);
		Assert.assertTrue(response.isSuccess());
	}
}
