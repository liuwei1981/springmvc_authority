package com.lw.upload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/upload")
public class UploadAction {

	protected static final Logger logger = Logger.getLogger(UploadAction.class);

	private UploadService uploadService;

	@RequestMapping(value = "/watermark_upload", method = RequestMethod.POST)
	public ModelAndView upload(
			@RequestParam("file") CommonsMultipartFile[] files,
			HttpServletRequest request, HttpSession session) {
		ModelAndView ret = new ModelAndView();

		try {
			List<PicInfo> picInfos = uploadService.uploadAndMark(files,
					"/images", session);

			ret.addObject("info", picInfos);
			ret.setViewName("upload_info");

		} catch (Exception e) {
			logger.error("login action error...");
		}

		return ret;
	}

	public UploadService getUploadService() {
		return uploadService;
	}

	@Autowired
	@Qualifier("uploadTextService")
	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}

}
