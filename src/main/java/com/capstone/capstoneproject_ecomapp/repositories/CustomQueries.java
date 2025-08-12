package com.capstone.capstoneproject_ecomapp.repositories;

public class CustomQueries {
    public static final String GET_PRODUCTBY_CATEGORY_NAME =
            "select * from Product where category.id in (select category_id from Category where name = :categoryName)";
}
