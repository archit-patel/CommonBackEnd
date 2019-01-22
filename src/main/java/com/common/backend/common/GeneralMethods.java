package com.common.backend.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class GeneralMethods {
	
	public static Pageable createPageRequest(int pageNo, int perPage, String sort, String title) {
		
		if(sort == null ||sort.equals(""))
			sort = "desc";

		if(title == null || title.equals(""))
			title = "id";

		Pageable p = PageRequest.of(pageNo-1, perPage,Sort.Direction.fromString(sort),title);
		
		return p;
	}
}
