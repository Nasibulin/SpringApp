
package org.springapp.api.request.model;

public class ListProductModel {
    private long categoryId;
    private long attributeId;
    private long companyId;
    private String searchKey;
    private Double minPrice;
    private Double maxPrice;
    private int minRank;
    private int maxRank;
    private int sortCase;
    private Boolean ascSort;
    private int pageNumber;
    private int pageSize;
}
