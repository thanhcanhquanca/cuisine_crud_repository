package com.example.cuisine_crud_repository.utility;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtils {
    public static List<Integer> getPageNumbers(int totalPages, int currentPage) {
        List<Integer> pageNumbers = new ArrayList<>();
        int start = Math.max(0, currentPage - 2);
        int end = Math.min(totalPages - 1, currentPage + 2);
        for (int i = start; i <= end; i++) {
            pageNumbers.add(i);
        }
        return pageNumbers;
    }
}
