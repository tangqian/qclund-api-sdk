package com.qcloud.api.modules.vod.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.modules.vod.VodBaseService;
import com.qcloud.api.modules.vod.dto.DescribeVodInfoResponse;

/**
 * @author tangqian
 *
 */
public class DescribeVodInfoService extends VodBaseService<DescribeVodInfoResponse> {

	public DescribeVodInfoService(VodModuleCaller caller) {
		super(caller);
	}

	@Override
	public String getActionName() {
		return "DescribeVodInfo";
	}

	@Override
	protected Class<DescribeVodInfoResponse> getResponseClass() {
		return DescribeVodInfoResponse.class;
	}

	/**
	 * 获取视频信息，可以根据视频ID、时间段或者状态等获得视频信息列表。
	 * 
	 * @param request
	 * @return
	 */
	public DescribeVodInfoResponse call(DescribeVodInfoRequest request) {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		request.fillParam(params);
		DescribeVodInfoResponse result = callVodAction(params, "GET");
		return result;
	}

	public static class DescribeVodInfoRequest {

		private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		/**
		 * 视频ID列表，暂时不支持批量
		 */
		private List<String> fileIds;
		/**
		 * 开始时间，默认为1970-1-1 00:00:00(创建时间)
		 */
		private Date from;
		/**
		 * 结束时间，默认为 2038-1-1 00:00:00(创建时间)
		 */
		private Date to;
		/**
		 * 视频分类ID，过滤使用
		 */
		private Integer classId;
		/**
		 * 视频状态，过滤使用
		 */
		private Integer status;
		/**
		 * 结果排序，默认按时间升序，0：按时间升序 1：按时间降序
		 */
		private Integer orderBy;
		private Integer pageNo;
		/**
		 * 分页大小，范围在10-100之间
		 */
		private Integer pageSize;

		public void fillParam(Map<String, Object> params) {
			if (from != null)
				params.put("from", sdfTime.format(from));
			if (to != null)
				params.put("to", sdfTime.format(to));
			if (classId != null)
				params.put("classId", classId);
			if (status != null)
				params.put("status", status);
			if (orderBy != null)
				params.put("orderby", orderBy);
			if (pageNo != null)
				params.put("pageNo", pageNo);
			if (pageSize != null)
				params.put("pageSize", pageSize);
		}

		public List<String> getFileIds() {
			return fileIds;
		}

		public void setFileIds(List<String> fileIds) {
			this.fileIds = fileIds;
		}

		public Date getFrom() {
			return from;
		}

		public void setFrom(Date from) {
			this.from = from;
		}

		public Date getTo() {
			return to;
		}

		public void setTo(Date to) {
			this.to = to;
		}

		public Integer getClassId() {
			return classId;
		}

		public void setClassId(Integer classId) {
			this.classId = classId;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Integer getOrderBy() {
			return orderBy;
		}

		public void setOrderBy(Integer orderBy) {
			this.orderBy = orderBy;
		}

		public Integer getPageNo() {
			return pageNo;
		}

		public void setPageNo(Integer pageNo) {
			this.pageNo = pageNo;
		}

		public Integer getPageSize() {
			return pageSize;
		}

		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}

	}
}
