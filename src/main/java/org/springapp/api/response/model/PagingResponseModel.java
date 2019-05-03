
package org.springapp.api.response.model;

import java.util.List;

public class PagingResponseModel {
    private List<?> data;
    private long totalResult;
    private int totalPage;
    private int currentPage;
}
