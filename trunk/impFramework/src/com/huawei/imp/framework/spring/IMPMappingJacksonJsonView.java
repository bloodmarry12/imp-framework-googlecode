package com.huawei.imp.framework.spring;

import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

public class IMPMappingJacksonJsonView extends MappingJacksonJsonView {

	@Override
	protected Object filterModel(Map<String, Object> model) {
		Map<?, ?> result = (Map<?, ?>) super.filterModel(model);
		if (result.size() == 1) {
			return result.values().iterator().next();
		} else {
			return result;
		}

	}

}
