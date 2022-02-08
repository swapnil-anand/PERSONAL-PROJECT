package com.nse.listingcompliance.lcdebtannouncementservice.repository.masterdata;


import com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata.Categories;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.masterdata.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
    public String CATEGORY_RESPONSE_BODY = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.masterdata.Category";
    @Query(
            "SELECT DISTINCT " + CATEGORY_RESPONSE_BODY + "( c.id, c.categoryName) FROM Categories c " +
            "INNER JOIN c.subjects s " +
            "INNER JOIN s.mappings m " +
            "INNER JOIN m.companyType a " +
            "WHERE a.id = :companyType "
    )
    List<Category> findByCompanyType(@Param(value = "companyType") Long companyType);

    @Query("SELECT " + CATEGORY_RESPONSE_BODY + " (c.id, c.categoryName) FROM Categories c")
    List<Category> findAllCompanyType();


}